package eu.tutorials.knofixapp.D1


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import eu.tutorials.knofixapp.Navigation.Routes
import eu.tutorials.knofixapp.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun D1Screen(navController: NavController) {
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFEFEFEF))
        ) {
            // Gradient-like Background
            Image(
                painter = painterResource(id = R.drawable.img_19),
                contentDescription = "Top Background",
                contentScale = ContentScale.Crop, // Add this to ensure proper scaling
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp)
                    .align(Alignment.TopStart) // Changed from TopCenter to TopStart
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
                        painter = painterResource(id = R.drawable.img), // Logo
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
                        modifier = Modifier.size(24.dp),
                        tint = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Search Bar and Banner
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Spacer(modifier = Modifier.height(24.dp))
                    SearchBar()
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        painter = painterResource(id = R.drawable.img_36),
                        contentDescription = "Men's Fix Saloon Banner",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Services",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    ServiceGrid()
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
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = "Search",
                tint = Color.Black,
                modifier = Modifier.size(22.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            BasicTextField(
                value = "",
                onValueChange = {},
                singleLine = true,
                textStyle = TextStyle(fontSize = 16.sp),
                decorationBox = { innerTextField ->
                    if (true) {
                        Text("I want to hire a...", color = Color.Gray)
                    }
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
    ServiceItem("Men's\nSaloon", R.drawable.img_26) // Fixed label
)

@Composable
fun ServiceGrid() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
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

            }
        }
    }
}



data class BottomNavigationItem(
    val title: String,
    val iconResId: Int // Now only using drawable resources
)

val items = listOf(
    BottomNavigationItem(
        title = "Home",
        iconResId = R.drawable.img_17
    ),
    BottomNavigationItem(
        title = "Orders",
        iconResId = R.drawable.img_16
    ),
    BottomNavigationItem(
        title = "Promotion",
        iconResId = R.drawable.img_15
    ),
    BottomNavigationItem(
        title = "Notification",
        iconResId = R.drawable.img_14
    ),
)

@Composable
fun BottomNavigationBar(navController: NavController) {
    val selectedIndex = remember { mutableStateOf(0) } // Default to Home selected
    NavigationBar(
        containerColor = Color.White,
        contentColor = Color.Black
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIndex.value == index,
                onClick = {
                    selectedIndex.value = index
                    when (item.title) {
                        "Home" -> navController.navigate(Routes.D1Screen)
                        "Orders" -> navController.navigate(Routes.D3Screen)
                        "Promotion" ->navController.navigate(Routes.D4Screen)
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
                    Text(
                        text = item.title,
                        fontSize = 12.sp // Smaller font size
                    )
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