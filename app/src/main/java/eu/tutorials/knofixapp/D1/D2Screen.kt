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
fun D2Screen(navController: NavController) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = false
        )
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFEFEFEF))
                .padding(bottom = padding.calculateBottomPadding())
        ) {
            // ✅ Top Background Image with Title Inside
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_19),
                    contentDescription = "Top Background",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )

                Text(
                    text = "Notification",
                    fontSize = 22.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 18.dp)
                )
            }

            // ✅ Main Content Area
            if (notifications.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_27),
                        contentDescription = "Notification Icon",
                        modifier = Modifier
                            .height(150.dp)
                            .width(150.dp)
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    Text(
                        text = "No Notifications Yet",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = "You have no notification right now.",
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
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp)
                ) {
                    items(notifications) { notification ->
                        NotificationCard(notification)
                    }
                }
            }
        }
    }
}

data class NotificationItem(
    val icon: Int,
    val title: String,
    val description: String,
    val time: String,
    val color: Color
)

val notifications = listOf(
    NotificationItem(R.drawable.img_35, "Order Accepted", "We have accepted your order. Click to view details.", "2 hrs ago", Color(0xFFFFA726)),
    NotificationItem(R.drawable.img_34, "Confirm Order", "We have added items in your order. Please check and confirm.", "2 hrs ago", Color(0xFF5C6BC0)),
    NotificationItem(R.drawable.img_33, "Order Assigned", "We have assigned your order to a worker. Click to view details.", "2 hrs ago", Color(0xFF42A5F5)),
    NotificationItem(R.drawable.img_32, "Order Completed", "Your order has been completed. Please check the work done.", "2 hrs ago", Color(0xFF66BB6A)),
    NotificationItem(R.drawable.img_31, "Order Cancelled", "Your order has been cancelled. Click to view details.", "2 hrs ago", Color(0xFFEF5350)),
    NotificationItem(R.drawable.img_30, "Announcement", "Our service will be down tomorrow for planned maintenance.", "2 hrs ago", Color(0xFF757575))
)

@Composable
fun NotificationCard(notification: NotificationItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .background(Color.White)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(notification.color, shape = androidx.compose.foundation.shape.CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = notification.icon),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(text = notification.title, fontWeight = FontWeight.Bold, color = Color.Black)
            Text(text = notification.description, color = Color.Gray, fontSize = 14.sp)
        }

        Text(
            text = notification.time,
            color = Color.Gray,
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}