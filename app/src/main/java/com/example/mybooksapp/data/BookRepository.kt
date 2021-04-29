package com.example.mybooksapp.data

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.example.mybooksapp.WEB_SERVICE_URL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class BookRepository(val app: Application) {

    val bookData = MutableLiveData<BooksResponse>()


    init {
        CoroutineScope(Dispatchers.IO).launch {
            callWebService()
        }
    }

    @WorkerThread
    suspend fun searchBooks(searchQuery: String) {
        if (networkAvailable()) {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)


            // Create retrofit object, link moshi
            val retrofit = Retrofit.Builder()
                .baseUrl(WEB_SERVICE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(httpClient.build())
                .build()

            val service = retrofit.create(BookService::class.java)

            val res = service.searchBooks(searchQuery)
            bookData.postValue(res)
        }
    }

    @WorkerThread
    suspend fun callWebService() {
        if (networkAvailable()) {
            val retrofit = Retrofit.Builder()
                .baseUrl(WEB_SERVICE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
            val service = retrofit.create(BookService::class.java)
            val serviceData = service.getBookData()
            bookData.postValue(serviceData)
        }

    }

    @Suppress("DEPRECATION")
    private fun networkAvailable(): Boolean {
        val connectivityManager = app.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting ?: false
    }

    fun refreshData() {

    }

}