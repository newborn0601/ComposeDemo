package com.example.yp.composedemo.page


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.School
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 菜单项数据类
data class MenuItem(
    val iconRes: ImageVector,
    val title: String
)

@Composable
fun MineScreen() {
    // 定义菜单项数据
    val menus = listOf(
        MenuItem(Icons.Filled.School, "我的收藏"),
        MenuItem(Icons.Filled.School, "浏览历史"),
        MenuItem(Icons.Filled.School, "设置"),
        MenuItem(Icons.Filled.School, "关于我们"),
        MenuItem(Icons.Filled.School, "退出登录"),
    )

    // 用户信息状态
    var userName by remember { mutableStateOf("用户名") }

    Column(modifier = Modifier.fillMaxSize()) {
        // 顶部标题栏
        TopAppBar(
            title = {
                Text(
                    text = "我的",
                    fontSize = 19.sp,
                    color = Color.White,
                    modifier = Modifier.padding(start = 16.dp)
                )
            },
            backgroundColor = MaterialTheme.colors.primary
        )

        // 可滚动内容区域
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = userName,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Divider(color = Color.LightGray, thickness = 0.5.dp)
                }
            }

            //菜单项列表
            itemsIndexed(menus) { index, item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            when(index) {
                                0 -> { /* 我的收藏 */ }
                                4 -> { /* 退出登录 */ }
                            }
                        }
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = item.title,
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.Default.ArrowForwardIos,
                        contentDescription = "更多",
                        tint = Color.Gray,
                        modifier = Modifier.size(16.dp))
                }

                // 在第三个菜单项后添加分割线
                if (index == 2) {
                    Divider(
                        color = Color.LightGray.copy(alpha = 0.3f),
                        thickness = 8.dp,
                        modifier = Modifier.fillMaxWidth())
                } else {
                    Divider(
                        color = Color.LightGray.copy(alpha = 0.1f),
                        thickness = 0.5.dp,
                        modifier = Modifier.padding(start = 56.dp))
                }
            }
        }
    }
}
