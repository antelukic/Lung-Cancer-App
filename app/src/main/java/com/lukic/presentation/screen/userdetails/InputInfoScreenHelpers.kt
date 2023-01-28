package com.lukic.presentation.screen.userdetails

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.lukic.lungcancerapp.R

@Suppress("LongParameterList", "ComplexMethod", "LongMethod")
@Composable
fun QuestionContent(
    content: Content,
    onYesClick: (Int) -> Unit,
    onNoClick: (Int) -> Unit,
    onGenderClick: (String) -> Unit,
    onAgeSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    when (content.step) {
        UserInputSteps.GENDER -> InputGender(onGenderClick = onGenderClick, modifier = modifier)
        UserInputSteps.AGE -> InputAge(onAgeSelected = onAgeSelected, modifier = modifier)
        UserInputSteps.SMOKING -> YesNoQuestions(
            composition = rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.smoking)).value,
            onYesClick = onYesClick,
            onNoClick = onNoClick,
            modifier = modifier
        )
        UserInputSteps.YELLOW_FINGERS -> YesNoQuestions(
            composition = null,
            onYesClick = onYesClick,
            onNoClick = onNoClick,
            modifier = modifier
        )
        UserInputSteps.ANXIETY -> YesNoQuestions(
            composition = null,
            onYesClick = onYesClick,
            onNoClick = onNoClick,
            modifier = modifier
        )
        UserInputSteps.PEER_PRESSURE -> YesNoQuestions(
            composition = null,
            onYesClick = onYesClick,
            onNoClick = onNoClick,
            modifier = modifier
        )
        UserInputSteps.CHRONIC_DISEASE -> YesNoQuestions(
            composition = null,
            onYesClick = onYesClick,
            onNoClick = onNoClick,
            modifier = modifier
        )
        UserInputSteps.FATIGUE -> YesNoQuestions(
            composition = null,
            onYesClick = onYesClick,
            onNoClick = onNoClick,
            modifier = modifier
        )
        UserInputSteps.ALLERGY -> YesNoQuestions(
            composition = null,
            onYesClick = onYesClick,
            onNoClick = onNoClick,
            modifier = modifier
        )
        UserInputSteps.WHEEZING -> YesNoQuestions(
            composition = null,
            onYesClick = onYesClick,
            onNoClick = onNoClick,
            modifier = modifier
        )
        UserInputSteps.ALCOHOL -> YesNoQuestions(
            composition = rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.alcohol)).value,
            onYesClick = onYesClick,
            onNoClick = onNoClick,
            modifier = modifier
        )
        UserInputSteps.COUGHING -> YesNoQuestions(
            composition = rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.coughing)).value,
            onYesClick = onYesClick,
            onNoClick = onNoClick,
            modifier = modifier
        )
        UserInputSteps.SHORTNESS_OF_BREATH -> YesNoQuestions(
            composition = null,
            onYesClick = onYesClick,
            onNoClick = onNoClick,
            modifier = modifier
        )
        UserInputSteps.SWALLOWING_DIFFICULTY -> YesNoQuestions(
            composition = null,
            onYesClick = onYesClick,
            onNoClick = onNoClick,
            modifier = modifier
        )
        UserInputSteps.CHEST_PAIN -> YesNoQuestions(
            composition = null,
            onYesClick = onYesClick,
            onNoClick = onNoClick,
            modifier = modifier
        )
    }
}
