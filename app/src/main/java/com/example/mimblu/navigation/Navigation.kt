package com.example.mimblu.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mimblu.screen.PlansScreen
import com.example.mimblu.screen.SymptomsScreen

@RequiresApi(Build.VERSION_CODES.R)
@SuppressLint("SuspiciousIndentation")
@Composable
fun Navigation(){
 val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "symptoms") {
        composable("symptoms") { 
            SymptomsScreen(navController)
        }
        composable("plans/{id}"
            , arguments = listOf(navArgument("id"){type= NavType.StringType})) {
            PlansScreen(navController,it.arguments?.getString("id"))
        }

    }
}