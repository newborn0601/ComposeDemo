package com.example.yp.composedemo.navigation

sealed class Destinations(val route: String) {
    //首页
    object HomeFrame : Destinations("HomeFrame")
    //文章详情页
    data class ArticleDetail(val id: Int) : Destinations("ArticleDetail/{id}") {
        fun createRoute() = "ArticleDetail/$id"
    }

}
