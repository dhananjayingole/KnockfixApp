package eu.tutorials.knofixapp.Welcome.UI

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.*
import eu.tutorials.knofixapp.Navigation.Routes
import eu.tutorials.knofixapp.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SecondLogScreen(navController: NavController) {
    val pages = listOf(
        OnboardingPage(
            title = "Choose a service",
            description = "Find the right service for your needs easily, with a variety of options available at your fingertips.",
            imageRes = R.drawable.img_2
        ),
        OnboardingPage(
            title = "Get a quote",
            description = "Request price estimates from professionals to help you make informed decisions with ease.",
            imageRes = R.drawable.img_3
        ),
        OnboardingPage(
            title = "Work done",
            description = "Sit back and relax while skilled experts efficiently take care of your tasks, ensuring a job well done.",
            imageRes = R.drawable.img_4
        )
    )

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            HorizontalPager(
                count = pages.size,
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) { page ->
                OnboardingCard(pages[page])
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Dots below cards
            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp),
                activeColor = Color(0xFF7373EF),
                inactiveColor = Color.LightGray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Bottom buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        // Skip to last page or directly navigate
                        navController.navigate(Routes.VerificationOneScreen)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = MaterialTheme.colorScheme.primary
                    ),
                    elevation = ButtonDefaults.buttonElevation(0.dp)
                ) {
                    Text("Skip")
                }

                Button(
                    onClick = {
                        if (pagerState.currentPage == pages.lastIndex) {
                            // Navigate when done
                            navController.navigate(Routes.VerificationOneScreen)
                        } else {
                            // Scroll to next page
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF7373EF),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(if (pagerState.currentPage == pages.lastIndex) "Done" else "Next")
                }
            }
        }
    }
}



data class OnboardingPage(val title: String, val description: String, val imageRes: Int)

@Composable
fun OnboardingCard(page: OnboardingPage) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Spacer(modifier = Modifier.height(60.dp))

        Image(
            painter = painterResource(id = page.imageRes),
            contentDescription = null,
            modifier = Modifier.size(280.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = page.title,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = page.description,
            fontSize = 16.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSecondLogScreen() {
    SecondLogScreen(navController = rememberNavController())
}