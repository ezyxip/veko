package com.ezyxip.veko

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.ezyxip.veko.screens.MainScreen

@Composable
fun App(){
    val navigator = rememberNavController()
    val graph = getGraph(navigator)
    NavHost(navController = navigator, graph = graph)
}

@Composable
fun getGraph(navigator: NavHostController): NavGraph {
    return navigator.createGraph("/main"){
        composable("/main"){ MainScreen() }
    }
}