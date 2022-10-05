package com.example.composedemo.pagerwithTabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.composedemo.gmailApp.AddPostScreen
import com.example.composedemo.posts.NetworkScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class Page(val index:Int,val title:String)

val pages = (0..2).map { e -> Page(e,"page ${e+1}") }


@OptIn(ExperimentalPagerApi::class)
@Composable
fun PagerWithTabs(scope: CoroutineScope = rememberCoroutineScope()){

    val pagerState = rememberPagerState()


    Column {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            }
        ) {

            pages.forEach { e ->
                Tab(
                    text = { Text(e.title) },
                    selected = pagerState.currentPage == e.index,
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.Gray,
                    modifier = Modifier.background(Color.Black),
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(e.index,0.0f)
                        }
                    },
                )
            }

        }

        HorizontalPager(
            modifier = Modifier.fillMaxWidth(),
            state = pagerState,
            count = 3,
        ) { page ->
            when(page){
                0 -> NetworkScreen()
                1 -> AddPostScreen()
                2 -> NetworkScreen()
            }
        }
    }

}

