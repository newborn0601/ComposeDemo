package com.example.yp.composedemo.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.SmartDisplay
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.yp.composedemo.model.Category
import com.example.yp.composedemo.model.DataType
import com.example.yp.composedemo.model.SwiperEntity

/***
 *  应该和flow 协程 相结合 去获取数据 给到前端
 * @property categories List<Category>
 * @property categoryIndex Int
 * @property types List<DataType>
 * @property currentTypeIndex Int
 * @property showArticleList Boolean
 * @property swiperData List<SwiperEntity>
 * @property notifications List<String>
 */
class MainViewModel : ViewModel() {


    //分类数据
    val categories by mutableStateOf(
        listOf(
            Category("Compose"),
            Category("MVI"),
            Category("Compose2"),
            Category("Compose3")
        )
    )

    //当前分类下标
    var categoryIndex by mutableStateOf(0)
        private set


    /**
     * 更新分类下标
     *
     * @param index
     */
    fun updateCategoryIndex(index: Int) {
        categoryIndex = index
    }


    //类型数据
    val types by mutableStateOf(
        listOf(
            DataType("Compose资讯", Icons.Default.Description),
            DataType("视频课程", Icons.Default.SmartDisplay)
        )
    )

    //当前类型下标
    var currentTypeIndex by mutableStateOf(0)
        private set

    //是否文章列表
    var showArticleList by mutableStateOf(true)
        private set

    /**
     * 更新类型下标
     *
     * @param index
     */
    fun updateTypeIndex(index: Int) {
        currentTypeIndex = index
        showArticleList = currentTypeIndex == 0
    }

    //轮播图数据
    val swiperData = listOf(
        SwiperEntity("https://img14.360buyimg.com/pop/jfs/t1/280966/26/7919/138992/67e015b0F8211abde/f9cd60b8744b8dcb.jpg"),
        SwiperEntity("https://img14.360buyimg.com/pop/jfs/t1/266385/26/20922/92481/67b30ac7F41558225/68bd8a1229e860a4.jpg"),
        SwiperEntity("https://pic.rmb.bdstatic.com/bjh/events/217c98a7a12f80e3b4db10308991929c506.jpeg@h_1280"),
    )

    //通知数据
    val notifications = listOf(
        "关于Compose第一阶段学习通知",
        "关于Compose第二阶段学习通知",
        "关于Compose第三阶段学习通知",
        "关于Compose第四阶段学习通知",
        "关于Compose第五阶段学习通知",
    )
}