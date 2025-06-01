package com.example.yp.composedemo.more

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScrollableTabRowSimple() {
    val scope = rememberCoroutineScope()
    val titles = remember {
        mutableStateListOf("TAB1", "TAB2")
    }
    val pagerState = rememberPagerState(pageCount = { titles.size })
    Column {
        ScrollableTabRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            selectedTabIndex = pagerState.currentPage,
            contentColor = Color.Black,
            divider = {},
            indicator = {
            }
        ) {
            titles.forEachIndexed { index, data ->
                val selected = pagerState.currentPage == index
                Box(
                    Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                        .clickable {
                            scope.launch {
                                //Tab被点击后让Pager中内容动画形式滑动到目标页
                                pagerState.scrollToPage(index, 0f)
                            }
                        }, contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = data,
                        fontSize = if (selected) 18.sp else 16.sp,
                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                        color = if (selected) Color.Red else Color.Black
                    )
                }
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxHeight(),
            pageNestedScrollConnection = NoOpNestedScrollConnection
        ) { _ ->
            val data = remember {
                mutableStateListOf("Android1", "Compose1", "Android2", "Compose2", "Android3", "Compose3", "Android4")
            }
            val innerScrollableState = rememberPagerState(pageCount = { data.size })
            val coordinatingNestedScroll = remember(innerScrollableState, innerScrollableState) {
                coordinatingPagerNestedScroll(innerScrollableState, innerScrollableState)
            }
            SecondPager(Modifier.nestedScroll(coordinatingNestedScroll), innerScrollableState, data)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SecondPager(modifier: Modifier, pagerState: PagerState, data: MutableList<String>) {
    val scope = rememberCoroutineScope()
    Column(modifier = modifier) {
        ScrollableTabRow(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            selectedTabIndex = pagerState.currentPage,
            contentColor = Color.Black,
            edgePadding = 10.dp,
            divider = {},
        ) {
            data.forEachIndexed { index, data ->
                val selected = pagerState.currentPage == index
                Box(
                    Modifier
                        .height(40.dp)
                        .wrapContentWidth()
                        .clickable {
                            scope.launch {
                                pagerState.scrollToPage(index, 0f)//Tab被点击后让Pager中内容动画形式滑动到目标页
                            }
                        }, contentAlignment = Alignment.Center

                ) {
                    Text(
                        text = data, fontSize = 13.sp,
                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                        color = if (selected) Color.Red else Color.Black
                    )
                }
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxHeight()
        ) { pagePosition ->
            Box(
                Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "页面：：=${pagePosition}")
            }
        }


    }
}

private val NoOpNestedScrollConnection = object : NestedScrollConnection {}

@OptIn(ExperimentalFoundationApi::class)
private fun coordinatingPagerNestedScroll(
    outerPagerState: PagerState,
    innerScrollableState: ScrollableState
) = object : NestedScrollConnection {
    override fun onPreScroll(
        available: Offset,
        source: NestedScrollSource
    ): Offset {
        return if ((available.x > 0 && !innerScrollableState.canScrollForward && outerPagerState.currentPageOffsetFraction != 0f) ||
            (available.x < 0 && !innerScrollableState.canScrollBackward && outerPagerState.currentPageOffsetFraction != 0f)
        ) {
            Offset.Zero.copy(x = -outerPagerState.dispatchRawDelta(-available.x))
        } else {
            super.onPreScroll(available, source)
        }
    }
}