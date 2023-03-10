@file:Suppress("TooManyFunctions")

package com.lukic.presentation.screen.userdetails

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.lukic.data.userdetails.model.FEMALE_ANSWER_VALUE
import com.lukic.data.userdetails.model.MALE_ANSWER_VALUE
import com.lukic.data.userdetails.model.NO_ANSWER_VALUE
import com.lukic.data.userdetails.model.YES_ANSWER_VALUE
import com.lukic.lungcancerapp.R
import com.lukic.presentation.screen.userdetails.InputInfoScreenTestTags.ARROW_BACK
import com.lukic.presentation.screen.userdetails.InputInfoScreenTestTags.FEMALE_BUTTON
import com.lukic.presentation.screen.userdetails.InputInfoScreenTestTags.INPUT_AGE
import com.lukic.presentation.screen.userdetails.InputInfoScreenTestTags.MALE_BUTTON

private const val PAGE_COUNT = 15
private const val INITIAL_PAGE = 0
private const val CHILD_LOW_VALUE = 0
private const val CHILD_TOP_VALUE = 15
private const val TEENAGE_LOW_VALUE = 16
private const val TEENAGE_TOP_VALUE = 30
private const val ADULT_LOW_VALUE = 31
private const val ADULT_TOP_VALUE = 50
private const val OLD_PERSON_LOW_VALUE = 51
private const val OLD_PERSON_TOP_VALUE = 100
private const val CHILD_VALUE = 0
private const val TEENAGE_VALUE = 1
private const val ADULT_VALUE = 2
private const val OLD_VALUE = 3
private const val SLIDER_LOW_VALUE = 0f
private const val SLIDER_TOP_VALUE = 100f
private const val STEP_GENDER = 0
private const val STEP_AGE = 1

object InputInfoScreenTestTags {
    const val TITLE = "Title"
    const val PAGER = "Pager"
    const val HORIZONTAL_PAGER_INDICATOR = "HorizontalPagerIndicator"
    const val INPUT_AGE = "InputAge"
    const val MALE_BUTTON = "MaleButton"
    const val FEMALE_BUTTON = "FemaleButton"
    const val ARROW_BACK = "ArrowBack"
}

@Suppress("LongParameterList")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun InputInfoScreen(
    content: Content,
    onGenderClick: (String) -> Unit,
    onYesClick: (Int) -> Unit,
    onNoClick: (Int) -> Unit,
    onAgeSelected: (Int) -> Unit,
    onArrowBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Title(
            modifier = Modifier
                .testTag(InputInfoScreenTestTags.TITLE)
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.input_details_title_padding))
        )

        val pagerState = rememberPagerState(INITIAL_PAGE)
        LaunchedEffect(key1 = content.step) {
            pagerState.animateScrollToPage(content.step.ordinal)
        }

        Pager(
            pageContent = {
                PagerInputContent(
                    content = content,
                    onGenderClick = onGenderClick,
                    currentPage = pagerState.currentPage,
                    questionText = content.question,
                    onYesClick = onYesClick,
                    onNoClick = onNoClick,
                    onAgeSelected = onAgeSelected,
                    modifier = Modifier.fillMaxSize()
                )
            },
            pagerState = pagerState,
            onArrowBack = onArrowBack,
            modifier = Modifier
                .weight(1f)
                .testTag(InputInfoScreenTestTags.PAGER)
        )

        HorizontalPagerIndicator(
            pagerState = pagerState,
            activeColor = MaterialTheme.colorScheme.primary,
            inactiveColor = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_indicator))
                .testTag(InputInfoScreenTestTags.HORIZONTAL_PAGER_INDICATOR)
        )
    }
}

@Composable
private fun Title(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = R.string.input_info_title),
        style = MaterialTheme.typography.headlineLarge.copy(textAlign = TextAlign.Center),
        modifier = modifier
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun Pager(
    pageContent: @Composable () -> Unit,
    pagerState: PagerState,
    onArrowBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    HorizontalPager(
        count = PAGE_COUNT,
        state = pagerState,
        userScrollEnabled = false,
        modifier = modifier
    ) {
        Surface(
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.intro_body_corner_size)),
            border = BorderStroke(
                width = dimensionResource(id = R.dimen.intro_card_border_width),
                color = MaterialTheme.colorScheme.secondary
            ),
            shadowElevation = dimensionResource(id = R.dimen.intro_card_elevation),
            color = MaterialTheme.colorScheme.background,
            contentColor = Color.Black,
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.intro_facts_padding))
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                AnimatedVisibility(
                    visible = pagerState.currentPage != 0,
                    modifier = Modifier.align(Alignment.TopStart)
                ) {
                    IconButton(
                        onClick = onArrowBack,
                        modifier = Modifier.testTag(ARROW_BACK)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.arrow_back_content_description)
                        )
                    }
                }

                pageContent()
            }
        }
    }
}

@Suppress("LongParameterList")
@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun PagerInputContent(
    content: Content,
    onAgeSelected: (Int) -> Unit,
    onYesClick: (Int) -> Unit,
    onNoClick: (Int) -> Unit,
    onGenderClick: (String) -> Unit,
    currentPage: Int,
    questionText: String,
    modifier: Modifier
) {
    Box(modifier = modifier) {
        var sliderPosition by remember { mutableStateOf(0f) }

        AnimatedContent(targetState = currentPage) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = questionText,
                    style = MaterialTheme.typography.headlineMedium.copy(textAlign = TextAlign.Center),
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.question_padding))
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.intro_pager_input_spacer_height)))

                QuestionContent(
                    content = content,
                    sliderPosition = sliderPosition,
                    onGenderClick = onGenderClick,
                    onSliderValueChange = { sliderPosition = it }
                )
            }
        }

        Buttons(
            step = content.step.ordinal,
            sliderPosition = sliderPosition.toInt(),
            onYesClick = onYesClick,
            onNoClick = onNoClick,
            onAgeSelected = onAgeSelected,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Suppress("LongParameterList")
@Composable
private fun Buttons(
    step: Int,
    sliderPosition: Int,
    onYesClick: (Int) -> Unit,
    onNoClick: (Int) -> Unit,
    onAgeSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Crossfade(
        targetState = step,
        modifier = modifier
    ) { inputStep ->
        if (inputStep == STEP_AGE) {
            Button(
                onClick = { onAgeSelected(sliderPosition) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.continue_button_padding))
            ) {
                Text(
                    text = stringResource(id = R.string.continue_button),
                    style = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.Center)
                )
            }
        }
        if (inputStep != STEP_AGE && inputStep != STEP_GENDER) {
            Column {
                YesNoButton(
                    text = stringResource(id = R.string.yes),
                    onClick = { onYesClick(YES_ANSWER_VALUE) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.yes_no_button_padding))
                )

                YesNoButton(
                    text = stringResource(id = R.string.no),
                    onClick = { onNoClick(NO_ANSWER_VALUE) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.yes_no_button_padding))
                )
            }
        }
    }
}

@Composable
fun InputGender(
    onGenderClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        GenderInputCard(
            icon = painterResource(id = R.drawable.male_icon),
            contentDescription = stringResource(id = R.string.input_gender_male),
            modifier = Modifier
                .testTag(FEMALE_BUTTON)
                .size(dimensionResource(id = R.dimen.input_gender_card_size))
                .clip(CircleShape)
                .clickable { onGenderClick(MALE_ANSWER_VALUE) }
        )

        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.input_gender_spacer_width)))

        GenderInputCard(
            icon = painterResource(id = R.drawable.female_icon),
            contentDescription = stringResource(id = R.string.input_gender_female),
            modifier = Modifier
                .testTag(MALE_BUTTON)
                .size(dimensionResource(id = R.dimen.input_gender_card_size))
                .clip(CircleShape)
                .clickable { onGenderClick(FEMALE_ANSWER_VALUE) }
        )
    }
}

@Composable
private fun GenderInputCard(
    icon: Painter,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    Surface(
        shadowElevation = dimensionResource(id = R.dimen.input_gender_card_elevation),
        border = BorderStroke(
            width = dimensionResource(id = R.dimen.intro_card_border_width),
            color = MaterialTheme.colorScheme.secondary
        ),
        shape = CircleShape,
        color = MaterialTheme.colorScheme.background,
        contentColor = Color.Black,
        modifier = modifier
    ) {
        Icon(
            painter = icon,
            contentDescription = contentDescription
        )
    }
}

@Suppress("LongParameterList")
@Composable
fun InputAge(
    sliderPosition: Float,
    onSliderValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.testTag(INPUT_AGE)
    ) {
        InputAgeAnimation(sliderPosition = sliderPosition.toInt())

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = sliderPosition.toInt().toString(),
                style = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.Start),
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.age_text_padding))
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.input_age_spacer_height)))

            Slider(
                value = sliderPosition,
                onValueChange = onSliderValueChange,
                valueRange = SLIDER_LOW_VALUE..SLIDER_TOP_VALUE,
                colors = SliderDefaults.colors(
                    thumbColor = MaterialTheme.colorScheme.primary,
                    activeTrackColor = MaterialTheme.colorScheme.primary,
                    inactiveTrackColor = MaterialTheme.colorScheme.primaryContainer
                ),
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.slider_horizontal_padding))
            )
        }

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.select_age_spacer_height)))
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun InputAgeAnimation(
    sliderPosition: Int,
    modifier: Modifier = Modifier
) {
    val childRange = CHILD_LOW_VALUE..CHILD_TOP_VALUE
    val teenageRange = TEENAGE_LOW_VALUE..TEENAGE_TOP_VALUE
    val adultRange = ADULT_LOW_VALUE..ADULT_TOP_VALUE
    val oldPersonRange = OLD_PERSON_LOW_VALUE..OLD_PERSON_TOP_VALUE

    val animateLottieChange = remember {
        mutableStateOf(
            when (sliderPosition) {
                in childRange -> CHILD_VALUE
                in teenageRange -> TEENAGE_VALUE
                in adultRange -> ADULT_VALUE
                in oldPersonRange -> OLD_VALUE
                else -> CHILD_VALUE
            }
        )
    }

    val composition by rememberLottieComposition(
        when (sliderPosition) {
            in childRange -> LottieCompositionSpec.RawRes(R.raw.child)
            in teenageRange -> LottieCompositionSpec.RawRes(R.raw.teenager)
            in adultRange -> LottieCompositionSpec.RawRes(R.raw.adult)
            in oldPersonRange -> LottieCompositionSpec.RawRes(R.raw.old_person)
            else -> LottieCompositionSpec.RawRes(R.raw.old_person)
        }
    )
    val progress by animateLottieCompositionAsState(composition, iterations = LottieConstants.IterateForever)

    AnimatedContent(
        targetState = animateLottieChange,
        modifier = modifier
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier.size(dimensionResource(id = R.dimen.lottie_animation_size))
        )
    }
}

@Composable
fun YesNoQuestions(
    composition: LottieComposition?,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        AnimatedVisibility(
            visible = composition != null,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            val progress by animateLottieCompositionAsState(composition, iterations = LottieConstants.IterateForever)
            LottieAnimation(
                composition = composition,
                progress = { progress },
                modifier = Modifier.size(dimensionResource(id = R.dimen.lottie_animation_size))
            )
        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.yes_no_questions_spacer_height)))
    }
}

@Composable
private fun YesNoButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.Center)
        )
    }
}
