package com.ezyxip.veko

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import androidx.navigation.navArgument
import com.ezyxip.veko.screens.MainScreen
import com.ezyxip.veko.screens.NoteEditorScreen
import com.ezyxip.veko.screens.NoteListScreen

@Composable
fun App(){
    configureApp()
    val navigator = rememberNavController()
    val graph = getGraph(navigator)
    NavHost(navController = navigator, graph = graph)
}

@Composable
fun getGraph(navigator: NavHostController): NavGraph {

    val nav =  { path: String ->
        if (path == "[back]") {
            navigator.navigateUp()
            Unit
        } else {
            navigator.navigate(path)
        }
    }

    return navigator.createGraph("/main"){
        composable("/main"){ MainScreen(navigator = nav) }
        composable("/notes"){ NoteListScreen(navigator = nav) }
        composable("/note_edit/{noteId}", arguments = listOf(
            navArgument("noteId"){ this.type = NavType.IntType }
        )){
            val noteId = it.arguments?.getInt("noteId") ?: throw Exception("Note id not sent")
            NoteEditorScreen(navigator = nav, noteId = noteId)
        }
    }
}