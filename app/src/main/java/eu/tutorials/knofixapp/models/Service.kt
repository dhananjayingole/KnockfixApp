package eu.tutorials.knofixapp.models

// models/Service.kt
data class Service(
    val _id: String,
    val name: String,
    val description: String,
    val ratings: Double = 0.0,
    val reviews: Int = 0,
    val img: String,
    val duration: Int = 60,
    val categoryId: String
)