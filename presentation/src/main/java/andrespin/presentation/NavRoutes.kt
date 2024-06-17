package andrespin.presentation

import androidx.navigation.NamedNavArgument

sealed class NavRoutes {

    data object NavigateToSettings : NavRoutes()

    data object NavigateToAboutApp : NavRoutes()

    data object NavigateBack : NavRoutes()

}