package andrespin.presentation

sealed class NavRoutes {

    data object NavigateToSettings : NavRoutes()

    data object NavigateToAboutApp : NavRoutes()

    data object NavigateBack : NavRoutes()

}