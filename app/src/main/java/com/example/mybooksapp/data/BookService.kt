package com.example.mybooksapp.data

import retrofit2.Response
import retrofit2.http.GET

interface BookService {
    @GET("/feed/monster_data.json")
    suspend fun getBookData(): Response<List<Book>>
}