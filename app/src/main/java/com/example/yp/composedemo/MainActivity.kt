package com.example.yp.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.yp.composedemo.component.NavHostApp
import com.example.yp.composedemo.ui.theme.ComposeDemoTheme
import com.example.yp.composedemo.component.PointView
import com.example.yp.composedemo.more.ScrollableTabRowSimple

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            ComposeDemoTheme(darkTheme = false) {
//                Surface(modifier = Modifier.fillMaxSize()) {
//                    NavHostApp()
//                }
//            }

//            PointView(listOf(
//                1.0,2.0,3.0,4.0,5.0,6.0
//            ), modifier = Modifier)
//            ScrollDemo()
//            MyCanvas()
//            TransformGestureDemo()
//            TapGestureDemo()
//            DragGestureDemo()
//            AnimationStateBox()
//            AnimationBox()
            ScrollableTabRowSimple()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeDemoTheme(darkTheme = false) {
        Surface(modifier = Modifier.fillMaxSize()) {
            NavHostApp()
        }
    }
}