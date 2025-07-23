package eu.tutorials.knofixapp.D1

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import eu.tutorials.knofixapp.Navigation.Routes
import eu.tutorials.knofixapp.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun D1Screen(navController: NavController) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = false
        )
    }

    val isDrawerOpen = RightSideDrawerState.isDrawerOpen

    Box {
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
                // Top Background Image
                Image(
                    painter = painterResource(id = R.drawable.img_19),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(190.dp)
                        .align(Alignment.TopStart)
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {
                    // Top Bar
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img),
                            contentDescription = "Logo",
                            modifier = Modifier.size(50.dp)
                        )
                        Text(
                            text = "KonckNFix",
                            fontSize = 18.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            imageVector = Icons.Rounded.Menu,
                            contentDescription = "Menu",
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {
                                    RightSideDrawerState.openDrawer()
                                },
                            tint = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                        Spacer(modifier = Modifier.height(24.dp))
                        SearchBar()
                        Spacer(modifier = Modifier.height(16.dp))
                        Image(
                            painter = painterResource(id = R.drawable.img_36),
                            contentDescription = "Banner",
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Text("Services", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        Spacer(modifier = Modifier.height(12.dp))
                        ServiceGrid()
                    }
                }
            }
        }

        // Animated Right Drawer with fade + slide
        AnimatedVisibility(
            visible = isDrawerOpen.value,
            enter = fadeIn(animationSpec = tween(300)) + slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(300)),
            exit = fadeOut(animationSpec = tween(300)) + slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(300))
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.4f))
                        .clickable { RightSideDrawerState.closeDrawer() }
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 90.dp)
                ) {
                    RightSideDrawerContent(navController)
                }
            }
        }
    }
}

@Composable
fun SearchBar() {
    Surface(
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Icon(Icons.Rounded.Search, "Search", tint = Color.Black, modifier = Modifier.size(22.dp))
            Spacer(modifier = Modifier.width(8.dp))
            BasicTextField(
                value = "",
                onValueChange = {},
                singleLine = true,
                textStyle = TextStyle(fontSize = 16.sp),
                decorationBox = { innerTextField ->
                    Text("I want to hire a...", color = Color.Gray)
                    innerTextField()
                }
            )
        }
    }
}

data class ServiceItem(val name: String, val iconRes: Int)

val services = listOf(
    ServiceItem("Cleaning", R.drawable.img_18),
    ServiceItem("Plumber", R.drawable.img_20),
    ServiceItem("Electrician", R.drawable.img_21),
    ServiceItem("Carpenter", R.drawable.img_22),
    ServiceItem("Appliance\nRepair", R.drawable.img_23),
    ServiceItem("Gas Stove", R.drawable.img_24),
    ServiceItem("Men's\nSaloon", R.drawable.img_26)
)

@Composable
fun ServiceGrid() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(services) { service ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { }
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = service.iconRes),
                    contentDescription = service.name,
                    modifier = Modifier.size(100.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(service.name, fontSize = 14.sp, fontWeight = FontWeight.Medium)
            }
        }
    }
}

@Composable
fun RightSideDrawerContent(navController: NavController, drawerWidth: Dp = 220.dp) {
    Box(
        modifier = Modifier
            .width(drawerWidth)
            .wrapContentHeight()
            .background(Color.White, shape = RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp))
            .padding(16.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text("My Account", modifier = Modifier.clickable { RightSideDrawerState.closeDrawer() })
            Text("Orders", modifier = Modifier.clickable {
                navController.navigate("orders")
                RightSideDrawerState.closeDrawer()
            })
            Text("Promotions", modifier = Modifier.clickable {
                navController.navigate("promotion")
                RightSideDrawerState.closeDrawer()
            })
            Text("Notifications", modifier = Modifier.clickable {
                navController.navigate("notification")
                RightSideDrawerState.closeDrawer()
            })
            Text("Logout", modifier = Modifier.clickable {
                RightSideDrawerState.closeDrawer()
            })
        }
    }
}

data class BottomNavigationItem(val title: String, val iconResId: Int)

val items = listOf(
    BottomNavigationItem("Home", R.drawable.img_17),
    BottomNavigationItem("Orders", R.drawable.img_16),
    BottomNavigationItem("Promotion", R.drawable.img_15),
    BottomNavigationItem("Notification", R.drawable.img_14)
)

@Composable
fun BottomNavigationBar(navController: NavController) {
    val selectedIndex = remember { mutableStateOf(0) }
    NavigationBar(containerColor = Color.White, contentColor = Color.Black) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIndex.value == index,
                onClick = {
                    selectedIndex.value = index
                    when (item.title) {
                        "Home" -> navController.navigate(Routes.D1Screen)
                        "Orders" -> navController.navigate(Routes.D3Screen)
                        "Promotion" -> navController.navigate(Routes.D4Screen)
                        "Notification" -> navController.navigate(Routes.D2Screen)
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconResId),
                        contentDescription = item.title,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(text = item.title, fontSize = 12.sp)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarOnePreview() {
    D1Screen(navController = rememberNavController())
}