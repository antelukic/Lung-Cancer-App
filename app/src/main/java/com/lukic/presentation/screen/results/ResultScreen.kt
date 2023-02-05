package com.lukic.presentation.screen.results

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.lukic.lungcancerapp.R
import com.lukic.presentation.screen.results.ResultScreenTestTags.ANIMATION
import com.lukic.presentation.screen.results.ResultScreenTestTags.BODY
import com.lukic.presentation.screen.results.ResultScreenTestTags.LEARN_MORE_BUTTON
import com.lukic.presentation.screen.results.ResultScreenTestTags.LOADER
import com.lukic.presentation.screen.results.ResultScreenTestTags.TITLE
import com.lukic.presentation.ui.compose.components.Loader

private const val LOADING_ALPHA = 0.5f
private const val NOT_LOADING_ALPHA = 1f

object ResultScreenTestTags {
    const val TITLE = "Title"
    const val ANIMATION = "Animation"
    const val BODY = "Body"
    const val LEARN_MORE_BUTTON = "LearnMoreButton"
    const val LOADER = "Loader"
}

@Composable
fun ResultScreen(
    contentState: Content,
    onLearnMoreClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.alpha(if (contentState.isLoading) LOADING_ALPHA else NOT_LOADING_ALPHA)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.matchParentSize()
        ) {
            Text(
                text = contentState.title,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .testTag(TITLE)
                    .padding(dimensionResource(id = R.dimen.result_title_padding))
            )

            Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.result_title_animation_spacer)))

            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(contentState.animationId))
            val progress by animateLottieCompositionAsState(composition)

            Animation(
                composition = composition,
                progress = progress,
                modifier = Modifier
                    .testTag(ANIMATION)
                    .size(dimensionResource(id = R.dimen.lottie_animation_size))
            )

            Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.result_animation_body_spacer)))

            Text(
                text = contentState.body,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .testTag(BODY)
                    .padding(dimensionResource(id = R.dimen.result_body_padding))
            )
        }

        AnimatedVisibility(
            visible = contentState.isLoading,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Loader(
                modifier = Modifier
                    .testTag(LOADER)
                    .progressSemantics()
                    .size(dimensionResource(id = R.dimen.loader_size))
            )
        }

        Button(
            onClick = { onLearnMoreClick(contentState.link) },
            modifier = Modifier
                .testTag(LEARN_MORE_BUTTON)
                .requiredHeightIn(dimensionResource(id = R.dimen.button_required_height))
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.learn_more_horizontal_padding))
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = stringResource(id = R.string.learn_more),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
private fun Animation(
    composition: LottieComposition?,
    progress: Float,
    modifier: Modifier = Modifier
) {
    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = modifier
    )
}
