package com.example.theelektronik.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.theelektronik.ui.add.AddScreen
import com.example.theelektronik.ui.add.DestinasiProduk
import com.example.theelektronik.ui.detail.DetailDestination
import com.example.theelektronik.ui.detail.DetailScreen
import com.example.theelektronik.ui.edit.EditDestination
import com.example.theelektronik.ui.edit.EditScreen
import com.example.theelektronik.ui.home.DestinasiHome
import com.example.theelektronik.ui.home.HomeScreen

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {

    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier
    ) {
        composable(
            DestinasiHome.route
        ) {
            HomeScreen(navigateToItemEntry = {
                navController.navigate(DestinasiProduk.route)
            },
                onDetailClick = { itemId ->
                    navController.navigate("${DetailDestination.route}/$itemId")
                    println("itemId: $itemId")
                })
        }
        composable(DestinasiProduk.route) {
            AddScreen(navigateBack = {
                navController.popBackStack()
            })

        }

        composable(
            route = DetailDestination.routeWithArgs,
            arguments = listOf(navArgument(DetailDestination.produkId) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val produkId = backStackEntry.arguments?.getString(DetailDestination.produkId)
            produkId?.let {
                DetailScreen(
                    navigateBack = { navController.popBackStack() },
                    navigateToEditItem = {
                        navController.navigate("${EditDestination.route}/$produkId")
                        println("produkId: $produkId")
                    }
                )
            }
        }

        composable(
            route = EditDestination.routeWithArgs,
            arguments = listOf(navArgument(EditDestination.produkId) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val produkId = backStackEntry.arguments?.getString(EditDestination.produkId)
            produkId?.let {
                EditScreen(
                    navigateBack = { navController.popBackStack() },
                    onNavigateUp = { navController.navigateUp() }
                )
            }
        }
    }
}