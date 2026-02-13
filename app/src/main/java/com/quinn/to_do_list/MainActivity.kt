package com.quinn.to_do_list

import AppDatabase
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
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
        composable("home") { HomeScreen() }
    }
}