package com.example.mybooksapp.data

data class BooksResponse(
    val status: String, //success, error etc
    val data: List<Book> // book objects

)