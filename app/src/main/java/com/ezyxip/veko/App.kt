package com.ezyxip.veko

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
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
import com.ezyxip.veko.screens.NoteViewModel

@Composable
fun App(){
    configureApp()

    val navigator = rememberNavController()
    val vm: NoteViewModel = viewModel()
    val graph = getGraph(navigator, vm)
    
    val history = RouteHistory(5)

    history.put {
        type = RouteHistory.TaskType.Back
        path = "/note_edit/{noteId}"
        task = vm::exec
    }

    history.registryHistory(navigator = navigator)
    
    NavHost(navController = navigator, graph = graph)
}

@Composable
fun getGraph(
    navigator: NavHostController,
    vm: NoteViewModel
): NavGraph {

    val nav =  { path: String ->
        if (path == "[back]") {
            println("!!!")
            navigator.popBackStack()
            Unit
        } else {
            navigator.navigate(path)
        }
    }

    return navigator.createGraph("/main"){
        composable("/main"){ MainScreen(navigator = nav, vm = vm) }

        composable("/notes"){ NoteListScreen(navigator = nav, vm = vm) }

        composable("/note_edit/{noteId}", arguments = listOf(
            navArgument("noteId"){ this.type = NavType.IntType }
        )){
            val noteId = it.arguments?.getInt("noteId") ?: throw Exception("Note id not sent")
            NoteEditorScreen(navigator = nav, noteId = noteId, vm = vm)
        }

    }
}