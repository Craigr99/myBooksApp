package com.example.mybooksapp.data

data class Book(
    val id: String,
    val title: String,
    val description: String,
    val image: String,
    val isbn: String,
    val publish_date: String,
    val page_count: Int,
)