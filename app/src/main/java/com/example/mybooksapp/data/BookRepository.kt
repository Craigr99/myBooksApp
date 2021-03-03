package com.example.mybooksapp.data

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mybooksapp.LOG_TAG
import com.example.mybooksapp.utilities.FileHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class BookRepository(val app: Application) {

    val bookData = MutableLiveData<List<Book>>()

    private val listType = Types.newParameterizedType(
        List::class.java, Book::class.java
    )

    init {
        getBookData()
        Log.i(LOG_TAG, "Network available: ${networkAvailable()}")
    }

    fun getBookData() {
        val text = FileHelper.getTextFromAssets(app, "book_data.json")
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<List<Book>> =
            moshi.adapter(listType)
        bookData.value = adapter.fromJson(text) ?: emptyList()

    }

    @Suppress("DEPRECATION")
    private fun networkAvailable(): Boolean {
        val connectivityManager = app.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting ?: false
    }

}