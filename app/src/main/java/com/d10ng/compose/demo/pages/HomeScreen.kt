package com.d10ng.compose.demo.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.d10ng.compose.demo.PageTransitions
import com.d10ng.compose.demo.pages.destinations.ButtonScreenDestination
import com.d10ng.compose.demo.pages.destinations.CellScreenDestination
import com.d10ng.compose.demo.pages.destinations.FieldScreenDestination
import com.d10ng.compose.demo.pages.destinations.NavBarScreenDestination
import com.d10ng.compose.demo.pages.destinations.SwitchScreenDestination
import com.d10ng.compose.demo.pages.destinations.ToastScreenDestination
import com.d10ng.compose.ui.AppColor
import com.d10ng.compose.ui.AppText
import com.d10ng.compose.ui.base.Cell
import com.d10ng.compose.ui.base.CellGroup
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.spec.Direction

/**
 * 首页
 * @Author d10ng
 * @Date 2023/9/4 13:54
 */
@RootNavGraph(start = true)
@Destination(style = PageTransitions::class)
@Composable
fun HomeScreen(
    nav: DestinationsNavigator
) {
    HomeScreenView(
        onClick = {
            nav.navigate(it)
        }
    )
}

@Composable
private fun HomeScreenView(
    onClick: (Direction) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColor.Neutral.bg)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
    ) {
        // 标题
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "DLJetpackComposeUtil", style = AppText.Bold.Title.v24)
            Text(
                text = "Jetpack Compose UI 组件库",
                style = AppText.Normal.Body.v14,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        CellGroup(title = "基础组件", inset = true) {
            Cell(
                title = "Button 按钮",
                link = true,
                onClick = { onClick(ButtonScreenDestination) })
            Cell(
                title = "Cell 单元格",
                link = true,
                onClick = { onClick(CellScreenDestination) })
            Cell(
                title = "Toast 轻提示",
                link = true,
                border = false,
                onClick = { onClick(ToastScreenDestination) })
        }
        CellGroup(title = "表单组件", inset = true) {
            Cell(
                title = "Field 输入框",
                link = true,
                onClick = { onClick(FieldScreenDestination) })
            Cell(
                title = "Switch 开关",
                link = true,
                onClick = { onClick(SwitchScreenDestination) })
        }
        CellGroup(title = "导航组件", inset = true) {
            Cell(
                title = "NavBar 导航栏",
                link = true,
                onClick = { onClick(NavBarScreenDestination) })
        }
    }
}

@Preview
@Composable
private fun HomeScreenViewPreview() {
    HomeScreenView()
}