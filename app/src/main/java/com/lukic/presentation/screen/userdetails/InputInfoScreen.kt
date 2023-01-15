package com.lukic.presentation.screen.userdetails

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.lukic.data.userdetails.model.FEMALE_ANSWER_VALUE
import com.lukic.data.userdetails.model.MALE_ANSWER_VALUE
import com.lukic.lungcancerapp.R

private const val PAGE_COUNT = 16
private const val INITIAL_PAGE = 0

@OptIn(ExperimentalPagerApi::class)
@Composable
fun InputInfoScreen(
    content: Content,
    onGenderClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Title(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.input_details_title_padding))
        )

        val pagerState = rememberPagerState(INITIAL_PAGE)
        LaunchedEffect(key1 = content.stepNumber) {
            pagerState.animateScrollToPage(content.stepNumber)
        }

        Pager(
            pageContent = {
                PagerInputContent(
                    onGenderClick = onGenderClick,
                    currentPage = pagerState.currentPage,
                    questionText = content.question,
                    modifier = Modifier.fillMaxSize()
                )
            },
            pagerState = pagerState,
            modifier = Modifier.weight(1f)
        )

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_indicator)),
            activeColor = MaterialTheme.colorScheme.primary,
            inactiveColor = MaterialTheme.colorScheme.secondary
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
            pageContent()
        }
    }
}

@Composable
private fun InputGender(
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
            onGenderClick = { onGenderClick(MALE_ANSWER_VALUE) },
            modifier = Modifier.size(dimensionResource(id = R.dimen.input_gender_card_size))
        )

        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.input_gender_spacer_width)))

        GenderInputCard(
            icon = painterResource(id = R.drawable.female_icon),
            contentDescription = stringResource(id = R.string.input_gender_female),
            onGenderClick = { onGenderClick(FEMALE_ANSWER_VALUE) },
            modifier = Modifier.size(dimensionResource(id = R.dimen.input_gender_card_size))
        )
    }
}

@Suppress("LongParameterList")
@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun PagerInputContent(
    onGenderClick: (String) -> Unit,
    currentPage: Int,
    questionText: String,
    modifier: Modifier
) {
    AnimatedContent(
        targetState = currentPage,
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = questionText,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.intro_pager_input_spacer_height)))

            InputGender(onGenderClick = onGenderClick)
        }
    }
}

@Composable
private fun GenderInputCard(
    icon: Painter,
    contentDescription: String,
    onGenderClick: () -> Unit,
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
        modifier = modifier.clickable(onClick = onGenderClick)
    ) {
        Icon(
            painter = icon,
            contentDescription = contentDescription
        )
    }
}
