package com.example.yp.composedemo.component

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.yp.composedemo.navigation.Destinations
import com.example.yp.composedemo.page.MainFrame
import androidx.navigation.compose.composable
import com.example.yp.composedemo.page.ArticleDetailScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavHostApp() {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destinations.HomeFrame.route
    ) {

        //首页大框架
        composable(Destinations.HomeFrame.route) {
            MainFrame(onNavigateToArticle = {
                Log.i("yp", "onNavigateToArticle")
                navController.navigate(Destinations.ArticleDetail(1).createRoute()){
                    launchSingleTop = true
                    restoreState = true
                }
            })
        }

        //文章详情页
        composable(Destinations.ArticleDetail(1).createRoute()) {
            ArticleDetailScreen()
        }
    }
}