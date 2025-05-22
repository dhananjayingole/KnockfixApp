package eu.tutorials.knofixapp.Navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import eu.tutorials.knofixapp.Verification.VerificationFourScreen
import eu.tutorials.knofixapp.Verification.VerificationOneScreen
import eu.tutorials.knofixapp.Verification.VerificationThreeScreen
import eu.tutorials.knofixapp.Verification.VerificationTwoScreen
import eu.tutorials.knofixapp.Welcome.UI.FirstLogScreen
import eu.tutorials.knofixapp.Welcome.UI.SecondLogScreen
import eu.tutorials.knofixapp.Welcome.UI.SplashScreen

@Composable
fun NavigationSetup(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Routes.SplashScreen,
        modifier = Modifier.padding()
    ) {
        composable(Routes.SplashScreen) {
            SplashScreen(modifier = Modifier,navController)
        }

        composable(Routes.FirstLogScreen) {
            FirstLogScreen(navController)
        }

        composable(Routes.SecondLogScreen){
            SecondLogScreen(navController)
        }

        composable(Routes.VerificationOneScreen){
            VerificationOneScreen(navController)
        }

        composable(Routes.VerificationTwoScreen){
            VerificationTwoScreen(navController)
        }

        composable(Routes.VerificationThreeScreen){
            VerificationThreeScreen(navController)
        }

        composable(Routes.VerificationFourScreen){
            VerificationFourScreen(navController)
        }
    }
}