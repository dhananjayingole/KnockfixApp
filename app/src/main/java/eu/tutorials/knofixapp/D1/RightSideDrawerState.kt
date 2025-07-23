package eu.tutorials.knofixapp.D1

import androidx.compose.runtime.mutableStateOf

object RightSideDrawerState {
    val isDrawerOpen = mutableStateOf(false)

    fun openDrawer() {
        isDrawerOpen.value = true
    }

    fun closeDrawer() {
        isDrawerOpen.value = false
    }

}
