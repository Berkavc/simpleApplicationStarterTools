package com.example.simpleapplicationstartertools.retrofit

import com.example.simpleapplicationstartertools.Constants
import okhttp3.Interceptor
import okhttp3.Response
/*
 *This is a interceptor class example for retrofit(Mainly used for authentication purposes).
 */
internal class AuthInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .header("Content-Type", "application/x-www-form-urlencoded")
            .header("dummy_api_key", Constants.DUMMY_API_KEY)
            .build()
        return chain.proceed(request)
    }
}