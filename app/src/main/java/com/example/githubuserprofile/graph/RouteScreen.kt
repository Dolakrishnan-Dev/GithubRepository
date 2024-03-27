package com.example.githubuserprofile.graph

sealed class RouteScreen(val route: String) {
    data object DetailScreen : RouteScreen("detail_screen")
    data object MainRoute : RouteScreen("main_route")
    data object StartRoute : RouteScreen("start_route")
}