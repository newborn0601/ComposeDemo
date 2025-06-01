package com.example.yp.composedemo.page
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import com.google.accompanist.insets.ProvideWindowInsets
import androidx.compose.material.icons.filled.ManageAccounts
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.yp.composedemo.model.NavigationItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainFrame(onNavigateToArticle: () -> Unit = {}) {

    val navigationItems = listOf(
        NavigationItem(title = "学习", icon = Icons.Filled.School),
        NavigationItem(title = "任务", icon = Icons.Filled.TaskAlt),
        NavigationItem(title = "我的", icon = Icons.Filled.ManageAccounts))

    //业务逻辑 -> state -> Ui更新 -> 刷新tree
    var currentNavigationIndex by remember {
        mutableIntStateOf(0)
    }
    //屏幕适配：刘海屏、沉浸式布局、处理键盘交互
    ProvideWindowInsets {
        Scaffold(bottomBar = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colorScheme.background,
                modifier = Modifier.navigationBarsPadding()
            ) {
                navigationItems.forEachIndexed { index, navigationItem ->
                    BottomNavigationItem(
                        selected = currentNavigationIndex == index,
                        onClick = {
                            currentNavigationIndex = index
                        },
                        icon = {
                            Icon(
                                imageVector = navigationItem.icon,
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(text = navigationItem.title)
                        },
                        selectedContentColor = Color(0xFF149EE7),
                        unselectedContentColor = Color(0xFF999999)
                    )
                }
            }
        }) {

            Box(modifier = Modifier.padding(it)) {
                when (currentNavigationIndex) {
                    0 -> StudyScreen(onNavigateToArticle = onNavigateToArticle)
                    1 -> TaskScreen()
                    2 -> MineScreen()
                }
            }

        }
    }

}