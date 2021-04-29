package com.example.mybooksapp.data

import retrofit2.http.GET
import retrofit2.http.Path


interface BookService {
//    @GET("/feed/monster_data.json")
//    suspend fun getBookData(): Response<List<Book>>

    // Get all books
    @GET("books/")
    suspend fun getBookData(): BooksResponse

    // Search for books
    @GET("books/search/{searchString}")
    suspend fun searchBooks(
        @Path("searchString") searchString: String
    ): BooksResponse


    // Create a book
//    @Headers("Content-Type: application/json")
//    @POST("books")
//    fun addBook(@Body bookData: Book): Call<Book>
}