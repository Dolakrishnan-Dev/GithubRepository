package com.example.githubuserprofile.graph

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.githubuserprofile.presentation.state.UIState
import com.example.githubuserprofile.presentation.ui.screen.DetailScreen
import com.example.githubuserprofile.presentation.ui.screen.MainScreen
import com.example.githubuserprofile.presentation.viewmodel.MainViewModel


@Composable
fun RootNavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = RouteScreen.StartRoute.route) {

        navigation(
            startDestination = RouteScreen.MainRoute.route,
            route = RouteScreen.StartRoute.route
        ) {
            composable(route = RouteScreen.MainRoute.route) {
                val viewModal =
                    it.sharedViewModal<MainViewModel>(navController = navController)
                val uiState by viewModal.githubUsersInfo.collectAsStateWithLifecycle()
                MainScreen(uiState) {

                    viewModal.onUIEvents(
                        UIState.SearchUser(
                            isFromHome = false,
                            userName = it
                        )
                    )

                    viewModal.onUIEvents(
                        UIState.FetchUserRepo(
                            userName = it
                        )
                    )
                    navController.navigate(RouteScreen.DetailScreen.route)
                }
            }

            composable(route = RouteScreen.DetailScreen.route ) { navBackStackEntry ->
                val viewModal =
                    navBackStackEntry.sharedViewModal<MainViewModel>(navController = navController)
                val uiState by viewModal.githubUserDetail.collectAsStateWithLifecycle()
                DetailScreen(uiState)
            }
        }

    }

}


@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModal(navController: NavHostController): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(key1 = this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(parentEntry)
}