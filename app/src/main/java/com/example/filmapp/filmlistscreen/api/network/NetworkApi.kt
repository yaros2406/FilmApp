package com.example.filmapp.filmlistscreen.api.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

class NetworkApi {

    private val filminterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    private val okhttpclient = OkHttpClient.Builder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .addInterceptor(filminterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okhttpclient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun newFilmApiService() = retrofit.create<FilmApiService>()

    companion object {
        private const val BASE_URL =
            "https://gist.githubusercontent.com/LukyanovAnatoliy/eca5141dedc79751b3d0b339649e06a3/raw/38f9419762adf27c34a3f052733b296385b6aa8d/"
    }
}