package com.example.mybooksapp.ui.shared

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mybooksapp.data.Book
import com.example.mybooksapp.data.BookRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedViewModel(app: Application) : AndroidViewModel(app) {

    private val dataRepo = BookRepository(app)
    var bookData = dataRepo.bookData

    val selectedBook = MutableLiveData<Book>()

    fun getBooks(searchQuery: String) {
        CoroutineScope(Dispatchers.IO).launch {
            dataRepo.searchBooks(searchQuery)
            bookData = dataRepo.bookData
        }

    }

    fun refreshData() {
        dataRepo.refreshData()
    }
}