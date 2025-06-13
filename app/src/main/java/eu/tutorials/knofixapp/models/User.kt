package eu.tutorials.knofixapp.models

import java.util.Date


// models/User.kt
data class User(
    val _id: String,
    val name: String,
    val profileImage: String?,
    val role: String, // "customer" or "provider"
    val status: String,
    val phone: String?,
    val addresses: List<UserAddress> = emptyList(),
    val currentLocation: UserLocation? = null
)

data class UserAddress(
    val street: String?,
    val city: String?,
    val state: String?,
    val pincode: String?,
    val coordinates: Coordinates?,
    val isDefault: Boolean = false,
    val label: String = "Home"
)

data class UserLocation(
    val latitude: Double?,
    val longitude: Double?,
    val lastUpdated: Date?
)

data class Coordinates(
    val latitude: Double,
    val longitude: Double
)