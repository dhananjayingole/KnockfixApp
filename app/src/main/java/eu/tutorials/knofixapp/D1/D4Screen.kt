package eu.tutorials.knofixapp.D1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import eu.tutorials.knofixapp.R

@Composable
fun D4Screen(navController: NavController) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = false // Force light icons for better visibility
        )
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFEFEFEF))
        ) {
            // ✅ Top Background Image (fixed at top start)
            Image(
                painter = painterResource(id = R.drawable.img_19),
                contentDescription = "Top Background",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .align(Alignment.TopStart)
            )

            // ✅ Screen Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 24.dp) // Adds spacing from top background if needed
            ) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Promotion",
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )

                // Centered Content
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = padding.calculateBottomPadding()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_29),
                            contentDescription = "Notification Icon",
                            modifier = Modifier
                                .height(150.dp)
                                .width(150.dp)
                        )

                        Spacer(modifier = Modifier.height(18.dp))

                        Text(
                            text = "No Promotion Yet",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Text(
                            text = " no Promotion available at the moment.",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray
                        )
                        Text(
                            text = "Come back later",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray,
                            textAlign = TextAlign.Center
                        )
                    }
            }
        }
    }
}


