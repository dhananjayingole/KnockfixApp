package eu.tutorials.knofixapp.D1

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.razorpay.Checkout
import org.json.JSONObject

@Composable
fun PaymentScreen(navController: NavController, presetAmount: Float? = null) {
    var amount by remember { mutableStateOf(presetAmount?.toString() ?: "") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (presetAmount == null) {
            OutlinedTextField(
                value = amount,
                onValueChange = { newValue ->
                    if (newValue.isEmpty() || newValue.toFloatOrNull() != null) {
                        amount = newValue
                    }
                },
                label = { Text("Enter Amount") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        Button(
            onClick = {
                val paymentAmount = amount.toFloatOrNull()
                if (paymentAmount != null && paymentAmount > 0) {
                    startPayment(context as Activity, paymentAmount)
                } else {
                    Toast.makeText(context, "Please enter a valid amount", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = amount.isNotEmpty() && amount.toFloatOrNull()?.let { it > 0 } ?: false
        ) {
            Text("Pay ₹${amount.ifEmpty { "0" }}")
        }
    }
}

private fun razorpayApiKey(): String {
    return "rzp_test_5WgA34F9ljiXAX" // Replace with your actual Razorpay API key
}

private fun startPayment(activity: Activity, amount: Float) {
    try {
        val checkout = Checkout()
        checkout.setKeyID(razorpayApiKey())

        val options = JSONObject().apply {
            put("name", "PDF Former App")
            put("description", "Payment for services")
            put("amount", amount * 100) // Razorpay expects amount in paise
            put("currency", "INR")
            put("theme.color", "#512DA8") // Customize color if needed
            put("prefill.email", "user@example.com") // Prefill if you have user data
            put("prefill.contact", "9876543210") // Prefill if you have user data
        }

        checkout.open(activity, options)
    } catch (e: Exception) {
        Toast.makeText(activity, "Error in payment: ${e.message}", Toast.LENGTH_SHORT).show()
    }
}