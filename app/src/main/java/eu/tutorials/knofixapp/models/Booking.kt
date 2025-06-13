package eu.tutorials.knofixapp.models

import java.util.Date

// models/Booking.kt
data class Booking(
    val _id: String,
    val customerId: String,
    val providerUserId: String?,
    val serviceId: String,
    val providerId: String,
    val address: String,
    val bookingDate: Date,
    val status: String, // "pending", "confirmed", etc.
    val location: BookingLocation?,
    val notes: String?,
    val totalCost: Double,
    val providerConfirmation: ProviderConfirmation?,
    val advancePayment: PaymentInfo?,
    val finalPayment: PaymentInfo?,
    val paymentStatus: String = "pending",
    val feedback: Feedback?
)

data class BookingLocation(
    val type: String = "Point",
    val coordinates: List<Double> // [lng, lat]
)

data class ProviderConfirmation(
    val status: String, // "pending", "accepted", "rejected"
    val confirmedAt: Date?,
    val rejectionReason: String?
)

data class PaymentInfo(
    val amount: Double?,
    val paymentId: String?,
    val orderId: String?,
    val paid: Boolean = false
)

data class Feedback(
    val rating: Int?,
    val comment: String?,
    val recommend: Boolean?,
    val submittedAt: Date?
)
