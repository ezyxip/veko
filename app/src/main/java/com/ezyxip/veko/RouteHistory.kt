package com.ezyxip.veko

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

class RouteHistory (private val count: Int) {
    val history = mutableListOf<String>()
    val onBackTasks = mutableMapOf<String, ()->Unit>()
    val onCurrentTasks = mutableMapOf<String, ()->Unit>()

    fun put(argBuilder: TaskBuilding.() -> Unit) {
        val args = TaskBuilding()
        args.argBuilder()
        if (args.path == "") return
        if (args.type == TaskType.Back){
            onBackTasks.put(args.path, args.task)
        } else {
            onCurrentTasks.put(args.path, args.task)
        }
    }
    fun MutableList<String>.addWithConstraint(elem: String){
        add(elem)
        if(size > count) removeFirst()
    }
    @Composable
    fun registryHistory(navigator: NavHostController){
        DisposableEffect(navigator.currentBackStackEntryAsState()) {
            val callback = NavController.OnDestinationChangedListener { _, destination, _ ->
                    history.lastOrNull()?.let {
                        onBackTasks[it]?.let { it() }
                    }

                    destination.route?.let { history.addWithConstraint(it) }

                    history.lastOrNull()?.let {
                        onCurrentTasks[it]?.let { it() }
                    }
                }
            navigator.addOnDestinationChangedListener(callback)

            onDispose {
                navigator.removeOnDestinationChangedListener(callback)
            }
        }
    }

    enum class TaskType{Back, Current}
    data class TaskBuilding(
        var type: TaskType = TaskType.Back,
        var path: String = "",
        var task: () -> Unit = {}
    )
}