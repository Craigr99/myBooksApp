package com.example.mybooksapp.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.mybooksapp.data.BookRepository

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val dataRepo = BookRepository(app)
    val bookData = dataRepo.bookData


}