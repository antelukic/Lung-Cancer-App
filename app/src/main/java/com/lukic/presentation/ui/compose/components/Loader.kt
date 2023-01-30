package com.lukic.presentation.ui.compose.components

import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

private const val SWEEP_ANGLE = 90f
private const val INITIAL_VALUE = 0
private const val TARGET_VALUE = 360
private const val DURATION_MILLIS = 1100
private const val START_ANGLE_SUBTRACT_VALUE = 90
private const val CANVAS_PADDING_DIVIDER = 2

@Composable
fun Loader(
    modifier: Modifier = Modifier,
    strokeWidth: Dp = ProgressIndicatorDefaults.CircularStrokeWidth,
    color: Color = MaterialTheme.colorScheme.primary,
    sweepAngle: Float = SWEEP_ANGLE,
    indicatorPathColor: Color = Color.LightGray
) {
    val transition = rememberInfiniteTransition()

    val currentArcStartAngle by transition.animateValue(
        initialValue = INITIAL_VALUE,
        targetValue = TARGET_VALUE,
        typeConverter = Int.VectorConverter,
        animationSpec = InfiniteRepeatableSpec(
            animation = tween(
                durationMillis = DURATION_MILLIS,
                easing = LinearEasing
            )
        )
    )

    val stroke = with(LocalDensity.current) {
        Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Square)
    }

    Canvas(
        modifier = modifier
            .padding(strokeWidth / CANVAS_PADDING_DIVIDER)
    ) {
        drawCircle(color = indicatorPathColor, style = stroke)

        drawArc(
            color,
            startAngle = currentArcStartAngle.toFloat() - START_ANGLE_SUBTRACT_VALUE,
            sweepAngle = sweepAngle,
            useCenter = false,
            style = stroke
        )
    }
}
