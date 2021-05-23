package com.schaefer.architectureplayground.di

import com.schaefer.architectureplayground.BuildConfig
import com.schaefer.architectureplayground.network.RickAndMortyApi
import com.schaefer.architectureplayground.network.RickAndMortyApiDataSource
import com.schaefer.architectureplayground.network.RickAndMortyApiDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideJsonConfig(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
        BASE_URL: String
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(converterFactory)
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(RickAndMortyApi::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiDataSource: RickAndMortyApiDataSourceImpl): RickAndMortyApiDataSource =
        apiDataSource
}