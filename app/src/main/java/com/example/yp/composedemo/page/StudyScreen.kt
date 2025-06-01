package com.example.yp.composedemo.page

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.LeadingIconTab
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.yp.composedemo.component.ArticleItem
import com.example.yp.composedemo.component.NotificationContent
import com.example.yp.composedemo.component.SwiperContent
import com.example.yp.composedemo.component.TopAppBar
import com.example.yp.composedemo.component.VideoItem
import com.example.yp.composedemo.viewmodel.ArticleViewModel
import com.example.yp.composedemo.viewmodel.MainViewModel
import com.example.yp.composedemo.viewmodel.VideoViewModel

/***
 * @param vm MainViewModel
 * @param articleViewModel ArticleViewModel
 * @param videoViewModel VideoViewModel
 * @param onNavigateToArticle Function0<Unit>
 */
@Composable
fun StudyScreen(
    vm: MainViewModel = viewModel(),
    articleViewModel: ArticleViewModel = viewModel(),
    videoViewModel: VideoViewModel = viewModel(),
    onNavigateToArticle: () -> Unit = {}
) {
    Column(modifier = Modifier) {

        //标题栏
        TopAppBar {
            Spacer(modifier = Modifier.width(8.dp))

            //搜索按钮
            Surface(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .weight(1f),
                color = Color(0x33AFFFFF)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        "搜索感兴趣的内容",
                        color = Color.White,
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                }
            }
            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "学习\n内容",
                fontSize = 14.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "26%",
                fontSize = 16.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Filled.Notifications,
                contentDescription = null,
                tint = Color.White
            )
        }

        //分类标签
        TabRow(
            selectedTabIndex = vm.categoryIndex,
            contentColor = Color(0xFF149EE7)
        ) {
            vm.categories.forEachIndexed { index, category ->
                Tab(
                    selected = vm.categoryIndex == index,
                    onClick = {
                        vm.updateCategoryIndex(index)
                    },
                    selectedContentColor = Color(0xFF149EE7),
                    unselectedContentColor = Color(0xFF666666)

                ) {
                    Text(
                        text = category.title,
                        modifier = Modifier.padding(vertical = 8.dp),
                        fontSize = 16.sp
                    )
                }
            }
        }

        TabRow(
            selectedTabIndex = vm.currentTypeIndex,
            contentColor = Color(0xFF149EE7),
            indicator = {},
            divider = {}
        ) {
            vm.types.forEachIndexed { index, dataType ->
                LeadingIconTab(
                    selected = vm.currentTypeIndex == index,
                    onClick = {
                        vm.updateTypeIndex(index)
                    },
                    selectedContentColor = Color(0xFF149EE7),
                    unselectedContentColor = Color(0xFF666666),
                    icon = {
                        Icon(imageVector = dataType.icon, contentDescription = null)
                    },
                    text = {
                        Text(
                            text = dataType.title,
                            modifier = Modifier.padding(vertical = 8.dp),
                            fontSize = 16.sp
                        )
                    }
                )
            }
        }

        LazyColumn() {

            item {
                //轮播图
                SwiperContent(vm)
            }

            item {
                //通知公告
                NotificationContent(vm)
            }


            if (vm.showArticleList) {
                //通知列表
                items(articleViewModel.list,
                    key = { article -> article.id } ) { article ->
                    ArticleItem(
                        article,
                        modifier = Modifier.clickable()
                        {
                            Log.d("yp","ArticleItem click")
                            onNavigateToArticle.invoke()
                        })
                }
            } else {
                //视频列表
                items(
                    videoViewModel.list
                ) { videoEntity ->
                    VideoItem(videoEntity)
                }
            }
        }
    }
}