package eu.tutorials.knofixapp.Verification

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import eu.tutorials.knofixapp.Navigation.Routes
import eu.tutorials.knofixapp.R

@Composable
fun VerificationFourScreen(navController: NavController) {
    var selectedGender by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }
    var emailAddress by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header Section
        Box(
            modifier = Modifier.fillMaxWidth().height(180.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_8),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = "Profile",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Profile Image + Buttons
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp).offset(y = (-40).dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_9),
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(100.dp).clip(CircleShape)
            )

            ProfileButton("Edit", R.drawable.img_11) { /* Edit profile action */ }
            ProfileButton("LogOut", R.drawable.img_10) { /* Logout action */ }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Profile Fields
        ProfileField("Full Name", fullName) { fullName = it }
        ProfileField("Email Address", emailAddress) { emailAddress = it }
        ProfileField("Phone Number", phoneNumber) { phoneNumber = it }

        Spacer(modifier = Modifier.height(16.dp))

        // Gender Selection
        Text(
            "Gender",
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            GenderOption("Male", R.drawable.img_12, selectedGender == "Male") {
                selectedGender = "Male"
            }
            GenderOption("Female", R.drawable.img_12, selectedGender == "Female") {
                selectedGender = "Female"
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Continue Button
        ContinueButton()
    }
}

// Profile Button Component
@Composable
fun ProfileButton(label: String, iconRes: Int, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF5D50FE)),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .height(50.dp)
            .focusable()
            .semantics {
                contentDescription = label
            }
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = Color.White
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(label, color = Color.White, fontSize = 16.sp)
    }
}


// Gender Selection Component
@Composable
fun GenderOption(label: String, iconRes: Int, selected: Boolean, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(if (selected) Color(0xFF5D50FE) else Color(0xFFF0F0F0))
            .clickable(onClick = onClick)
            .padding(16.dp)
            .width(120.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            tint = if (selected) Color.White else Color.Black,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            label,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = if (selected) Color.White else Color.Black
        )
    }
}

// Continue Button Component
@Composable
fun ContinueButton() {
    Button(
        onClick = {  },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .height(55.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        contentPadding = PaddingValues()
    ) {
        Box(
            modifier = Modifier.fillMaxSize().background(
                Brush.horizontalGradient(listOf(Color(0xFF5D50FE), Color(0xFF9D50FF))),
                shape = RoundedCornerShape(12.dp)
            ),
            contentAlignment = Alignment.Center
        ) {
            Text("Continue", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}

// TextField-based Profile Field
@Composable
fun ProfileField(label: String, value: String, onValueChange: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 4.dp)
    ) {
        Text(label, fontWeight = FontWeight.SemiBold)
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text("Enter $label") },
            modifier = Modifier.fillMaxWidth().background(Color(0xFFF0F0F0), shape = RoundedCornerShape(10.dp)),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFF0F0F0),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun VerificationFourScreenPreview() {
    VerificationFourScreen(navController = rememberNavController())
}