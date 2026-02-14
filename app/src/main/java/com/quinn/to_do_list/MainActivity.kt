package com.quinn.to_do_list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.quinn.to_do_list.ui.screens.AttributionScreen
import com.quinn.to_do_list.ui.screens.HomeScreen
import com.quinn.to_do_list.ui.theme.ToDoListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoListTheme {
                ToDoApp()
            }
        }
    }
}

@Composable
fun ToDoApp() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        startDestination = "home",
        navController = navController
    ) {
        composable("home") { HomeScreen(navController) }
        composable("attribution") { AttributionScreen(navController) }
    }
}