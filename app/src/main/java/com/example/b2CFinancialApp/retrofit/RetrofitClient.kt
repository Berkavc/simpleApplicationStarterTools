package com.example.b2CFinancialApp.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/*
 *This is a Retrofit builder class with timeout example(If client doesn't get response in 120 seconds it will timeout and will show you timeout exception in your retrofit onError listener).
 */
class RetrofitClient {
    companion object {
        fun getRetrofitClient(url: String): Retrofit {
            val client = OkHttpClient.Builder()
                .addInterceptor(
                       AuthInterceptor()
                )
                .connectTimeout(120, TimeUnit.SECONDS)
                .callTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
            return Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
    }
}