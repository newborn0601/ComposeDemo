package com.example.yp.composedemo.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

//@Preview
@Composable
fun PointView(points:List<Double>, modifier: Modifier) {
    val canvasWidth = LocalConfiguration.current.screenWidthDp - 16

    val countRow = 6
    val heightForRow = 24
    val circleRadius = 2.5

    val averageOfWidth = canvasWidth/7

    val canvasHeight = heightForRow*countRow + circleRadius*2

        Canvas(modifier = modifier.size(
        width = canvasWidth.dp,
        height = canvasHeight.dp
    )){
        //绘制背景
        for (index in 0..countRow){
          val Y  = (heightForRow*index).dp.toPx()
            drawLine(
                Color.Yellow,
                start = Offset(0f,Y),
                end = Offset(size.width,Y),
                strokeWidth = 2.5f
            )
        }
        //绘制折线、圆圈
        for (index in 0 until points.count()){
            val circleCenter = Offset(
                (averageOfWidth*index+averageOfWidth/2).dp.toPx(),
                (heightForRow*countRow - points[(index)]*8 +circleRadius).dp.toPx()
            )
            drawCircle(
                Color.Blue,
                radius = circleRadius.dp.toPx(),
                center = circleCenter,
                style = Stroke(width = 5f)
            )

            if(index < points.count()-1){
                val nextPoint = Offset(
                    (averageOfWidth*(index+1)+averageOfWidth/2).dp.toPx(),
                    (heightForRow*countRow - points[(index+1)]*8 +circleRadius).dp.toPx()
                )
                drawLine(
                    Color.Red,
                    start = circleCenter,
                    end = nextPoint,
                    strokeWidth =5f
                )
            }
        }



    }
}