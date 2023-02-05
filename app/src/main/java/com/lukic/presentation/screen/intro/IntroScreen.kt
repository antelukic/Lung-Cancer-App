package com.lukic.presentation.screen.intro

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.lukic.lungcancerapp.R
import com.lukic.presentation.screen.intro.IntroScreenTestTags.FUN_FACT
import com.lukic.presentation.screen.intro.IntroScreenTestTags.PAGER
import com.lukic.presentation.screen.intro.IntroScreenTestTags.PAGER_INDICATOR
import com.lukic.presentation.screen.intro.IntroScreenTestTags.START_BUTTON
import com.lukic.presentation.screen.intro.IntroScreenTestTags.TITLE

private const val PAGE_COUNT = 5
private const val INITIAL_PAGE = 0

object IntroScreenTestTags {
    const val TITLE = "Title"
    const val PAGER = "Pager"
    const val PAGER_INDICATOR = "PagerIndicator"
    const val START_BUTTON = "StartButton"
    const val FUN_FACT = "FunFact"
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun IntroScreen(
    pageContent: List<String>,
    onStartClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.intro_title),
            style = MaterialTheme.typography.headlineLarge.copy(textAlign = TextAlign.Center),
            modifier = Modifier
                .testTag(TITLE)
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.title_padding))
        )

        val pagerState = rememberPagerState(INITIAL_PAGE)
        Pager(
            pageContent = pageContent,
            pagerState = pagerState,
            modifier = Modifier
                .testTag(PAGER)
                .weight(1f)
                .padding(horizontal = dimensionResource(id = R.dimen.intro_facts_padding))
        )

        HorizontalPagerIndicator(
            pagerState = pagerState,
            activeColor = MaterialTheme.colorScheme.primary,
            inactiveColor = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .testTag(PAGER_INDICATOR)
                .padding(dimensionResource(id = R.dimen.padding_indicator))
        )

        Button(
            onClick = onStartClick,
            modifier = Modifier
                .testTag(START_BUTTON)
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.start_now_button_padding))
        ) {
            Text(
                text = stringResource(id = R.string.intro_button_text),
                style = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.Center)
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun Pager(
    pageContent: List<String>,
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    HorizontalPager(
        count = PAGE_COUNT,
        state = pagerState,
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
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = pageContent[pagerState.currentPage],
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .testTag(FUN_FACT)
                        .align(Alignment.Center)
                        .padding(dimensionResource(id = R.dimen.intro_text_padding))
                )
            }
        }
    }
}
