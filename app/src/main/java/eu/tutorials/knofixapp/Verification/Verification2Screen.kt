package eu.tutorials.knofixapp.Verification


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import eu.tutorials.knofixapp.Navigation.Routes
import eu.tutorials.knofixapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerificationTwoScreen(navController: NavController) {
    val phoneNumberState = remember { mutableStateOf(TextFieldValue()) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(180.dp))
        Image(
            painter = painterResource(id = R.drawable.img_5), // Add your phone icon in drawable
            contentDescription = "Phone icon",
            modifier = Modifier
                .size(88.dp)
                .padding(end = 8.dp)
        )
        Text(
            text = "Enter Your Phone Number\n " +
                    "for Verification",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(80.dp))
        // Phone number label
        Text(
            text = "Phone Number",
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp, end = 240.dp),
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Indian flag with +91 code
            Surface(
                modifier = Modifier
                    .height(56.dp) // Match text field height
                    .padding(end = 8.dp),
                shape = MaterialTheme.shapes.small,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_6),
                        contentDescription = "Indian flag",
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "+91",
                        color = Color.Black,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            // Phone number input field
            OutlinedTextField(
                value = phoneNumberState.value,
                onValueChange = { phoneNumberState.value = it },
                modifier = Modifier.weight(1f),
                placeholder = {
                    Text(text = "Enter your mobile number")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                singleLine = true
            )
        }
        // Continue button
        Button(
            onClick = { navController.navigate(Routes.VerificationThreeScreen) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 66.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF7373EF),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Continue", fontSize = 16.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VerificationTwoScreenPreview() {
        VerificationTwoScreen(navController = rememberNavController())
}