package com.ammar.movieappcompose.util.uiview

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.ammar.movieappcompose.presentation.utils.Screen

data class BottomNavigationView(
    val label : String = "",
    val icon : ImageVector = Icons.Filled.Home,
    val route : String = ""
) {

    fun bottomNavigationItems() : List<BottomNavigationView> {
        return listOf(
            BottomNavigationView(
                label = "Home",
                icon = Icons.Filled.Home,
                route = Screen.List.route
            ),
            BottomNavigationView(
                label = "Search",
                icon = Icons.Filled.Search,
                route = Screen.Search.route
            ) ,
            BottomNavigationView(
                label = "Profile",
                icon = Icons.Filled.Person,
                route = Screen.Profile.route
            )
        )

    }
}
