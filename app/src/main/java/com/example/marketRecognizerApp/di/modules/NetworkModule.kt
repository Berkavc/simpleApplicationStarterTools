package com.example.marketRecognizerApp.di.modules

import com.example.marketRecognizerApp.Constants
import com.example.marketRecognizerApp.di.qualifiers.RetrofitClientBuilder
import com.example.marketRecognizerApp.retrofit.RetrofitClient
import com.example.marketRecognizerApp.retrofit.RetrofitInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @RetrofitClientBuilder
    @Provides
    internal fun provideRetrofitClientBuilder(): RetrofitInterface {
        return RetrofitClient.getRetrofitClient(
            Constants.DUMMY_BASE_URL
        )
            .create(RetrofitInterface::class.java)
    }

}
