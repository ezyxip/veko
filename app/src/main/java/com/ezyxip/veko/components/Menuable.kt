package com.ezyxip.veko.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Menuable(
    modifier: Modifier = Modifier,
    title: String = "",
    actions: @Composable RowScope.() -> Unit = {},
    content: @Composable () -> Unit
){
    val drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerContent =  { MenuableDrawerSheet()},
        drawerState = drawerState,
        gesturesEnabled = true,
    ){
        Scaffold (
            modifier = modifier,
            topBar = {
                    TopAppBar(
                        title = {
                            Text(title)
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = null
                                )
                            }
                        },
                        actions = actions
                    )

            }
        ) {
            Box(modifier = modifier.padding(it)){
                content()
            }
        }
    }
}

@Composable
private fun MenuableDrawerSheet(
    modifier: Modifier = Modifier,
){
    ModalDrawerSheet (
        modifier = modifier
    ) {
        NavigationDrawerItem(label = { Text("События", fontSize = 20.sp) }, selected = false, onClick = { /*TODO*/ })
        NavigationDrawerItem(label = { Text("Заметки", fontSize = 20.sp) }, selected = false, onClick = { /*TODO*/ })
        NavigationDrawerItem(label = { Text("Настройки", fontSize = 20.sp) }, selected = false, onClick = { /*TODO*/ })
        NavigationDrawerItem(label = { Text("О приложении", fontSize = 20.sp) }, selected = false, onClick = { /*TODO*/ })
    }
}

@Composable
@Preview
private fun MenuablePreview(){
    Menuable (title = "Hello world!") {
        Text("World")
    }
}