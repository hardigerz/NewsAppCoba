package com.example.newsappcoba.presentation.onboarding.navgraph

sealed class Route(
    val route: String
) {
    object OnBoardingScreen : Route(route = "onBoardingScreen")
    object HomeScreen : Route("homeScreen")
    object DetailsScreen : Route("detailsScreen")

    object AppStartNavigation : Route(route = "appStartNavigation")

    object NewsNavigation : Route(route = "newsNavigation")

    object NewsNavigatorScreen : Route("newsNavigator")


}
