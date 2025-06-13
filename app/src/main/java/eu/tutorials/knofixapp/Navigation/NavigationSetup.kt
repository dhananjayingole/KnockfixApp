package eu.tutorials.knofixapp.Navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import eu.tutorials.knofixapp.D1.D1Screen
import eu.tutorials.knofixapp.D1.D2Screen
import eu.tutorials.knofixapp.D1.D3Screen
import eu.tutorials.knofixapp.D1.D4Screen
import eu.tutorials.knofixapp.Location.LocationSearchScreen
import eu.tutorials.knofixapp.Navigation.Routes.D2Screen
import eu.tutorials.knofixapp.Payment.PaymentInformationScreen
import eu.tutorials.knofixapp.Verification.VerificationFourScreen
import eu.tutorials.knofixapp.Verification.VerificationOneScreen
import eu.tutorials.knofixapp.Verification.VerificationThreeScreen
import eu.tutorials.knofixapp.Verification.VerificationTwoScreen
import eu.tutorials.knofixapp.Welcome.UI.FirstLogScreen
import eu.tutorials.knofixapp.Welcome.UI.SecondLogScreen
import eu.tutorials.knofixapp.Welcome.UI.SplashScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationSetup(navController: NavHostController){

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = false // Force light icons for better visibility
        )
    }

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
        composable(Routes.D1Screen) {
            D1Screen(navController)
        }
        composable(Routes.D2Screen) {
            D2Screen(navController)
        }
        composable(Routes.D3Screen) {
            D3Screen(navController)
        }
        composable(Routes.D4Screen) {
            D4Screen(navController)
        }
        composable (Routes.PaymentInformationScreen){
            PaymentInformationScreen(navController)
        }
        composable(Routes.LocationSearchScreen) {
            LocationSearchScreen(
                navController = navController,
                country = "India",
                state = "Maharashtra",
                city = "Nanded"
            )
        }
    }
}