package com.lukic.presentation.screen.userdetails

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.lukic.lungcancerapp.R

@Suppress("LongParameterList", "ComplexMethod", "LongMethod")
@Composable
fun QuestionContent(
    sliderPosition: Float,
    content: Content,
    onSliderValueChange: (Float) -> Unit,
    onGenderClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    when (content.step) {
        UserInputSteps.GENDER -> InputGender(
            onGenderClick = onGenderClick,
            modifier = modifier
        )
        UserInputSteps.AGE -> InputAge(
            sliderPosition = sliderPosition,
            onSliderValueChange = onSliderValueChange,
            modifier = modifier
        )
        UserInputSteps.SMOKING -> YesNoQuestions(
            composition = rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.smoking)).value,
            modifier = modifier
        )
        UserInputSteps.YELLOW_FINGERS -> YesNoQuestions(
            composition = null,
            modifier = modifier
        )
        UserInputSteps.ANXIETY -> YesNoQuestions(
            composition = null,
            modifier = modifier
        )
        UserInputSteps.PEER_PRESSURE -> YesNoQuestions(
            composition = null,
            modifier = modifier
        )
        UserInputSteps.CHRONIC_DISEASE -> YesNoQuestions(
            composition = null,
            modifier = modifier
        )
        UserInputSteps.FATIGUE -> YesNoQuestions(
            composition = null,
            modifier = modifier
        )
        UserInputSteps.ALLERGY -> YesNoQuestions(
            composition = null,
            modifier = modifier
        )
        UserInputSteps.WHEEZING -> YesNoQuestions(
            composition = null,
            modifier = modifier
        )
        UserInputSteps.ALCOHOL -> YesNoQuestions(
            composition = rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.alcohol)).value,
            modifier = modifier
        )
        UserInputSteps.COUGHING -> YesNoQuestions(
            composition = rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.coughing)).value,
            modifier = modifier
        )
        UserInputSteps.SHORTNESS_OF_BREATH -> YesNoQuestions(
            composition = null,
            modifier = modifier
        )
        UserInputSteps.SWALLOWING_DIFFICULTY -> YesNoQuestions(
            composition = null,
            modifier = modifier
        )
        UserInputSteps.CHEST_PAIN -> YesNoQuestions(
            composition = null,
            modifier = modifier
        )
    }
}
