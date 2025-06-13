package eu.tutorials.knofixapp.Location

import android.content.Context
import android.location.Geocoder
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import eu.tutorials.knofixapp.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationSearchScreen(
    navController: NavController,
    country: String = "India",
    state: String = "Maharashtra",
    city: String = "Nanded"
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    val systemUiController = rememberSystemUiController()

    var selectedLocation by remember { mutableStateOf(LatLng(19.2967, 77.1466)) }
    var addressText by remember { mutableStateOf("") }
    var isSearching by remember { mutableStateOf(false) }
    var searchResults by remember { mutableStateOf<List<String>>(emptyList()) }
    var showSearchResults by remember { mutableStateOf(false) }

    var isMapSelectionAllowed by remember { mutableStateOf(false) }
    var showPermissionDialog by remember { mutableStateOf(false) }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(selectedLocation, 14f)
    }

    LaunchedEffect(Unit) {
        val fullLocation = "$city, $state, $country"
        val initialLatLng = searchAddress(fullLocation, context)
        if (initialLatLng != null) {
            selectedLocation = initialLatLng
            cameraPositionState.position = CameraPosition.fromLatLngZoom(initialLatLng, 14f)
        }
    }

    SideEffect {
        systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = false)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_19),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = addressText,
                    onValueChange = {
                        addressText = it
                        if (it.isNotEmpty()) {
                            showSearchResults = true
                            coroutineScope.launch {
                                searchResults = searchAddresses("$it, $city, $state, $country", context)
                            }
                        } else {
                            showSearchResults = false
                        }
                    },
                    placeholder = { Text("Enter address") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(8.dp)),
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    trailingIcon = {
                        if (addressText.isNotEmpty()) {
                            IconButton(onClick = {
                                addressText = ""
                                showSearchResults = false
                                focusManager.clearFocus()
                            }) {
                                Icon(Icons.Default.Close, contentDescription = null)
                            }
                        }
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = {
                        if (addressText.isNotEmpty()) {
                            focusManager.clearFocus()
                            isSearching = true
                            coroutineScope.launch {
                                val location = searchAddress("$addressText, $city, $state, $country", context)
                                location?.let {
                                    selectedLocation = it
                                    cameraPositionState.position = CameraPosition.fromLatLngZoom(it, 15f)
                                }
                                isSearching = false
                            }
                        }
                    }),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                TextButton(
                    onClick = {
                        focusManager.clearFocus()
                        showSearchResults = false
                        showPermissionDialog = true
                    },
                    colors = ButtonDefaults.textButtonColors(contentColor = Color.White)
                ) {
                    Text("Choose from Map", textAlign = TextAlign.Start)
                }
            }
        }

        if (showPermissionDialog) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Do you want to select location directly from map?")
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(onClick = {
                            isMapSelectionAllowed = false
                            showPermissionDialog = false
                        }) {
                            Text("Yes")
                        }
                        OutlinedButton(onClick = {
                            isMapSelectionAllowed = false
                            showPermissionDialog = false
                        }) {
                            Text("Cancel")
                        }
                    }
                }
            }
        }

        if (showSearchResults && searchResults.isNotEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                LazyColumn(modifier = Modifier.heightIn(max = 200.dp)) {
                    items(searchResults) { result ->
                        Text(
                            text = result,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .clickable {
                                    addressText = result
                                    showSearchResults = false
                                    focusManager.clearFocus()
                                    isSearching = true
                                    coroutineScope.launch {
                                        val location = searchAddress(result, context)
                                        location?.let {
                                            selectedLocation = it
                                            cameraPositionState.position =
                                                CameraPosition.fromLatLngZoom(it, 15f)
                                        }
                                        isSearching = false
                                    }
                                }
                        )
                        Divider()
                    }
                }
            }
        }

        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                onMapClick = { latLng ->
                    if (isMapSelectionAllowed) {
                        selectedLocation = latLng
                        coroutineScope.launch {
                            addressText = getAddressFromLocation(context, latLng)
                        }
                    }
                }
            ) {
                Marker(
                    state = MarkerState(position = selectedLocation),
                    title = "Selected Location",
                    snippet = addressText
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {
                    navController.previousBackStackEntry?.savedStateHandle?.set(
                        "selectedLocation", selectedLocation to addressText
                    )
                    navController.popBackStack()
                },
                enabled = addressText.isNotEmpty(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                contentPadding = PaddingValues(),
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(50.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            if (addressText.isNotEmpty()) {
                                Brush.horizontalGradient(
                                    colors = listOf(Color(0xFF5D50FE), Color(0xFF9D50FF))
                                )
                            } else {
                                SolidColor(Color.Transparent)
                            },
                            shape = RoundedCornerShape(24.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Done", color = Color.White)
                }
            }
        }

        if (isSearching) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}


// 🔍 Helper Functions
private fun searchAddresses(query: String, context: Context): List<String> {
    val geocoder = Geocoder(context)
    return try {
        val addresses = geocoder.getFromLocationName(query, 5)
        addresses?.mapNotNull { it.getAddressLine(0) } ?: emptyList()
    } catch (e: Exception) {
        emptyList()
    }
}

private fun searchAddress(address: String, context: Context): LatLng? {
    val geocoder = Geocoder(context)
    return try {
        val addresses = geocoder.getFromLocationName(address, 1)
        addresses?.firstOrNull()?.let {
            LatLng(it.latitude, it.longitude)
        }
    } catch (e: Exception) {
        null
    }
}

private suspend fun getAddressFromLocation(context: Context, location: LatLng): String {
    val geocoder = Geocoder(context)
    return try {
        val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
        addresses?.firstOrNull()?.getAddressLine(0) ?: "${location.latitude}, ${location.longitude}"
    } catch (e: Exception) {
        "${location.latitude}, ${location.longitude}"
    }
}
