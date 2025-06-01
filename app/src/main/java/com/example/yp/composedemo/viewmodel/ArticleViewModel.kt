package com.example.yp.composedemo.viewmodel

import androidx.lifecycle.ViewModel
import com.example.yp.composedemo.model.ArticleEntity

class ArticleViewModel : ViewModel() {

    //文章列表数据
    var list = listOf(
        ArticleEntity(
            id = 0,
            title = "关于Compose第一阶段学习通知",
            source = "Google",
            timestamp = "2025-02-09"
        ),
        ArticleEntity(
            id = 1,
            title = "关于Compose第一阶段学习通知",
            source = "Google",
            timestamp = "2025-02-09"
        ),
        ArticleEntity(
            id = 2,
            title = "关于Compose第一阶段学习通知",
            source = "Google",
            timestamp = "2025-02-09"
        ),
        ArticleEntity(
            id = 3,
            title = "关于Compose第一阶段学习通知",
            source = "Google",
            timestamp = "2025-02-09"
        ),
        ArticleEntity(
            id = 4,
            title = "关于Compose第一阶段学习通知",
            source = "Google",
            timestamp = "2025-02-09"
        ),
        ArticleEntity(
            id = 5,
            title = "关于Compose第一阶段学习通知",
            source = "Google",
            timestamp = "2025-02-09"
        ),
        ArticleEntity(
            id = 6,
            title = "关于Compose第一阶段学习通知",
            source = "Google",
            timestamp = "2025-02-09"
        ),
        ArticleEntity(
            id = 7,
            title = "关于Compose第一阶段学习通知",
            source = "Google",
            timestamp = "2025-02-09"
        ),
        ArticleEntity(
            id = 8,
            title = "关于Compose第一阶段学习通知",
            source = "Google",
            timestamp = "2025-02-09"
        ),
        ArticleEntity(
            id = 9,
            title = "关于Compose第一阶段学习通知",
            source = "Google",
            timestamp = "2025-02-09"
        ),
        ArticleEntity(
            id = 10,
            title = "关于Compose第一阶段学习通知",
            source = "Google",
            timestamp = "2025-02-09"
        ),
    )
        private set
}