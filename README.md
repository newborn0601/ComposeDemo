# ComposeDemo 学习

# 本项目包含一系列 Jetpack Compose 核心功能的实践案例，涵盖绘图、手势、动画等高级特性,以及首页UI搭建和嵌套水平分页布局。

## 功能模块一览 

[//]: # (示例名称	核心技术点	                可视化类型	关键依赖库	)

[//]: # (主题系统	动态颜色/暗黑模式/状态栏控制	    UI一致性框架	androidx.compose.material3	)

[//]: # (导航框架	类型安全路由/NavHost控制	    应用路由系统	androidx.navigation.compose	)

[//]: # (底部导航	Scaffold集成/沉浸式处理	    主框架导航	androidx.compose.material)

[//]: # (列表优化	懒加载/差分更新	            高性能列表	androidx.compose.foundation)

[//]: # (分页系统	无限轮播/自动播放	            内容浏览	    com.google.accompanist.pager)

[//]: # (折线图	Canvas精确绘制/坐标映射	    数据可视化	-	-)

[//]: # (横向滚动	horizontalScroll布局优化	    交互布局	    -	-)

[//]: # (画板	    Path动态绘制/手势追踪	                    -	-)

[//]: # (拖拽手势	detectDragGestures位移计算	手势交互	    -	-)

[//]: # (变形手势	detectTransformGestures矩阵变换  高级手势	-	-)

[//]: # (状态动画	animate*AsState声明式动画	    现代动画	    androidx.compose.animation	)


[//]: # (主题系统：动态色彩引擎，自动适配Android 12+动态色彩)

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

[//]: # (类型安全路由：通过密封类+扩展函数实现编译期检查)

    sealed class Destinations(val route: String) {
    //首页
    object HomeFrame : Destinations("HomeFrame")
    //文章详情页
    data class ArticleDetail(val id: Int) : Destinations("ArticleDetail/{id}") {
        fun createRoute() = "ArticleDetail/$id"
    }



[//]: # (使用了 Scaffold 和 BottomNavigation 构建了Material 3 风格的底部导航布局。)

    Scaffold(bottomBar = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colorScheme.background,
                modifier = Modifier.navigationBarsPadding()
            )
    ){
              Box(modifier = Modifier.padding(it)) {
                when (currentNavigationIndex) {
                    0 -> StudyScreen(onNavigateToArticle = onNavigateToArticle)
                    1 -> TaskScreen()
                    2 -> MineScreen()
                }
            }
    }

[//]: # (基于 LazyColumn 实现高性能懒加载，仅渲染可视区域内容，内存占用低，流畅支持长列表。列表项使用唯一key避免不必要的重组)

    LazyColumn() {
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
    }

[//]: # (无界列表优化：虚拟页数 + 取模运算避免内存溢出,DisposableEffect 确保资源释放,协程驱动的 animateScrollToPage,不只有水平的轮播图HorizontalPager，还有垂直方向的通知公告VerticalPager)

    //虚拟页数
    val virtualCount = Int.MAX_VALUE
    //实际页数
    val actualCount = vm.swiperData.size
    //初始图片下标
    val initialIndex = virtualCount / 2
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        initialPage = initialIndex
    )
    DisposableEffect(Unit) {
        val timer = Timer()
        timer.schedule(object : TimerTask(){
            override fun run() {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage+1)
                }

            }
        },3000,3000)
        onDispose {
            timer.cancel()
        }
    }
    HorizontalPager(
        count = virtualCount,
        state = pagerState,
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(8.dp))
    ) 
    fun Int.floorMod(other: Int): Int = when (other) {
        0 -> this
        else -> this - floorDiv(other) * other
    }

[//]: # (ScrollableTabRowSimple:双层嵌套的水平分页（Pager）布局，核心通过ScrollableTabRow + HorizontalPager 实现标签与分页的绑定。NestedScrollConnection 解决父子分页的滑动冲突。)

        Column {
        ScrollableTabRow() 
        HorizontalPager() { _ ->
            val data = remember {
                mutableStateListOf("Android1", "Compose1", "Android2", "Compose2", "Android3", "Compose3", "Android4")
            }
            val innerScrollableState = rememberPagerState(pageCount = { data.size })
            val coordinatingNestedScroll = remember(innerScrollableState, innerScrollableState) {
                coordinatingPagerNestedScroll(innerScrollableState, innerScrollableState)
            }
            SecondPager(Modifier.nestedScroll(coordinatingNestedScroll), innerScrollableState, data)
        }
        private fun coordinatingPagerNestedScroll(
            outerPagerState: PagerState,
            innerScrollableState: ScrollableState
        ) = object : NestedScrollConnection {
            override fun onPreScroll(available: Offset,source: NestedScrollSource): Offset {
            
            }
        }

