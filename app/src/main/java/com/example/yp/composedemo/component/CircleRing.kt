package com.example.yp.composedemo.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun CircleRing(boxWidthDp:Int) {
    Canvas(modifier = Modifier.size(boxWidthDp.dp)){
        drawArc(
            Color.Blue,
            startAngle = 160f,
            sweepAngle = 220f,
            useCenter = false,
            style = Stroke(30f, cap = StrokeCap.Round)
        )
        drawArc(
            Color.Yellow,
            startAngle = 160f,
            sweepAngle = 190f,
            useCenter = false,
            style = Stroke(30f, cap = StrokeCap.Round)
        )
    }
}