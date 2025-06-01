package com.example.yp.composedemo

import android.util.Log
import android.view.MotionEvent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Preview
@Composable
fun DragGestureDemo() {
    var boxSize = 100.dp
    var offset by remember { mutableStateOf(Offset.Zero) }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            Modifier
                .size(boxSize)
                .offset {
                    IntOffset(offset.x.roundToInt(), offset.y.roundToInt())
                }
                .background(Color.Magenta)
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragStart = { offset ->
                            // 拖动开始
                            Log.d("yp", offset.toString()) //手指触摸的点左边
                        },
                        onDragEnd = {
                            // 拖动结束
                        },
                        onDragCancel = {
                            // 拖动取消
                        },
                        onDrag = { change: PointerInputChange, dragAmount: Offset ->
                            // 拖动中
                            offset += dragAmount
                            Log.d("yp", dragAmount.toString())
                        }
                    )
                }
        )
    }
}

@Preview
@Composable
fun TapGestureDemo() {
    var boxSize = 100.dp
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Box(
            Modifier
                .size(boxSize)
                .background(Color.Green)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onDoubleTap = { offset: Offset ->
                            // 双击
                            Log.d("yp", "双击")
                        },
                        onLongPress = { offset: Offset ->
                            // 长按
                            Log.d("yp", "长按")
                        },
                        onPress = { offset: Offset ->
                            // 按下
                            Log.d("yp", "按下")
                        },
                        onTap = { offset: Offset ->
                            // 轻触
                            Log.d("yp", "单击")
                        }
                    )
                }
        )
    }
}


@Preview
@Composable
fun TransformGestureDemo() {
    var boxSize = 100.dp
    var offset by remember { mutableStateOf(Offset.Zero) }
    var ratationAngle by remember { mutableStateOf(0f) }
    var scale by remember { mutableStateOf(1f) }
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Box(
            Modifier
                .size(boxSize)
                .rotate(ratationAngle) // 需要注意offset与rotate的调用先后顺序 rotate -> scale -> offset
            .scale(scale)
                .offset {
                    IntOffset(offset.x.roundToInt(), offset.y.roundToInt())
                }
                .background(Color.Green)
                .pointerInput(Unit) {
                    detectTransformGestures(
                        panZoomLock = true, // 平移或放大时是否可以旋转
                        onGesture = { centroid: Offset, pan: Offset, zoom: Float, rotation: Float ->
                            offset += pan
                            scale *= zoom
                            ratationAngle += rotation
                        }
                    )
                }
        )
    }
}


@ExperimentalComposeUiApi
@Composable
fun MyCanvas() {
    var downX by remember {
        mutableStateOf(0f)
    }

    var downY by remember {
        mutableStateOf(0f)
    }

    val path = Path()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInteropFilter { event ->
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> {
                            downX = event.x
                            downY = event.y
                            path.moveTo(downX, downY)
                        }

                        MotionEvent.ACTION_MOVE -> {
                            path.lineTo(downX, downY)
                            downX = event.x
                            downY = event.y
                        }
                    }
                    true
                }, onDraw = {
            downX
            drawPath(path, Color.Blue, style = Stroke(width = 30f))
        })

        Button(onClick = {
            downX = 0f
            path.reset()
        }) {
            Text(text = "CLEAR")
        }
    }
}

@Composable
fun ScrollDemo() {
    Row(
        modifier = Modifier
            .height(144.dp)
            .background(Color.LightGray)
            .horizontalScroll(rememberScrollState())
    ) {
        repeat(12) { index ->
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .padding(8.dp)
                    .background(Color.White, RoundedCornerShape(8.dp))
            ) {
                Text(
                    text = "Item $index",
                    modifier = Modifier.padding(8.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}