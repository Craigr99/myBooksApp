package com.example.mybooksapp.ui.shared

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mybooksapp.data.Book
import com.example.mybooksapp.data.BookRepository

class SharedViewModel(app: Application) : AndroidViewModel(app) {

    private val dataRepo = BookRepository(app)
    val bookData = dataRepo.bookData

    val selectedBook = MutableLiveData<Book>()

}