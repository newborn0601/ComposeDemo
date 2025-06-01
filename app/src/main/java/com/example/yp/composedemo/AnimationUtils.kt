package com.example.yp.composedemo

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.KeyframesSpec
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AnimationBox(){
    val topPadding  =  remember {
        mutableStateOf(10)
    }

    val animator = remember {
        animatorOfInt(topPadding,10,100)
    }

    animator.duration = 1000

    Box(modifier = Modifier
        .padding(start = 10.dp, top = topPadding.value.dp)
        .size(100.dp)
        .background(Color.Blue)
        .clickable {
            animator.start()
        })
}

/***
 * @param state MutableState<Int>
 * @param values IntArray
 * @return ValueAnimator
 */
fun animatorOfInt(state:MutableState<Int>,vararg values:Int):ValueAnimator{
    println("call animatorOfInt")
    val animator = ValueAnimator.ofInt(*values)
    //添加监听
    animator.addUpdateListener {
        state.value = it.animatedValue as Int
    }
    return animator
}


//AnimateXXXASState
@Composable
fun AnimationStateBox(){
    val topPadding  =  remember {
        mutableStateOf(10)
    }

    val animator = remember {
        animatorOfInt(topPadding,10,100)
    }

    animator.duration = 1000

    var flag  by remember {
        mutableStateOf(false)
    }

    //弹簧动画的参数规格,阻尼比、刚度、可见性阈值
    val springSpec = SpringSpec(
        dampingRatio = 0.1f,
        stiffness = 600f,
        visibilityThreshold = 0.01.dp
    )
    //关键帧动画：在指定的时间点（关键帧）定义属性值，系统自动插值计算中间帧。
    val config = KeyframesSpec.KeyframesSpecConfig<Dp>();
    config.durationMillis = 1000
    config.delayMillis = 1000

    val startPadding  = animateDpAsState(
        if(flag)
        100.dp
        else
        10.dp
        , animationSpec = springSpec
    )

    var shown by remember {
        mutableStateOf(true)
    }

    Box{
        AnimatedVisibility(visible = shown, enter = fadeIn(
            animationSpec = tween(1000, easing = CustomerEasing()), initialAlpha = 0.3f
        ) ) {
            Box(modifier = Modifier
                .size(100.dp)
                .background(Color.Green))
        }
        Button(onClick = {shown = !shown}, modifier = Modifier.padding(top = 100.dp)) {
                Text(text = "点我")
        }
    }

    Box(modifier = Modifier
        .padding(start = startPadding.value, top = topPadding.value.dp)
        .size(100.dp)
        .background(Color.Blue)
        .clickable {
            flag = !flag
        })

}