package eu.tutorials.knofixapp.Verification

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import eu.tutorials.knofixapp.Navigation.Routes
import eu.tutorials.knofixapp.R
import kotlinx.coroutines.delay

@Composable
fun VerificationThreeScreen(navController: NavController) {
    var code by remember { mutableStateOf("") }
    var timer by remember { mutableStateOf(59) }

    // Countdown Timer
    LaunchedEffect(key1 = timer) {
        if (timer > 0) {
            delay(1000)
            timer--
        }
    }

    // Scroll state for shifting when keyboard opens
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 24.dp)
            .imePadding(), // shifts up when keyboard opens
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Circle with Image
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(color = Color(0xFFB39DDB), shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_7),
                contentDescription = "Message Icon"
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Title
        Text(
            text = "Enter the Code to Continue",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Subtext
        Text(
            text = "A verification code has been sent\nto +92 3332131399 via SMS.",
            fontSize = 14.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        // OTP Input
        OutlinedTextField(
            value = code,
            onValueChange = { if (it.length <= 6) code = it },
            placeholder = { Text("Enter Code here") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color(0xFFF5F5F5),
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Verify Button
        Button(
            onClick = { navController.navigate(Routes.VerificationFourScreen) },
            enabled = code.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (code.isNotEmpty()) Color(0xFFE1BEE7) else Color(0xFFF1F1F1),
                contentColor = if (code.isNotEmpty()) Color.Black else Color.Gray
            )
        ) {
            Text("Verify")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Resend Code as TextButton
        if (timer > 0) {
            Text(
                text = "Resend code in $timer sec",
                color = Color.Gray,
                fontSize = 14.sp
            )
        } else {
            TextButton(onClick = { /* Handle resend */ }) {
                Text(
                    text = "Resend Code",
                    color = Color(0xFF4F6201),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun VerificationThreeScreenPreview() {
    VerificationThreeScreen(navController = rememberNavController())
}
