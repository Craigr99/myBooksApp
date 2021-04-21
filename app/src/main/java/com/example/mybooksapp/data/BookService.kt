package com.example.mybooksapp.data

import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {
//    @GET("/feed/monster_data.json")
//    suspend fun getBookData(): Response<List<Book>>

    // Get all books
    @GET("books/")
    suspend fun getBookData(): BooksResponse

    // Search for books
    @GET("books/search")
    suspend fun searchBooks(
        @Query("q") searchString: String
    ): BooksResponse

}