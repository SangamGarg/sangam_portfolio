package com.sangam.sangamportfolio.retrofitUtils

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitUtilClass {
    companion object {
        private lateinit var retrofit3: Retrofit
        private const val EMAIL_URL = "https://www.sangamgarg.in/"

        fun getRetrofitEmail(): Retrofit {
            if (!Companion::retrofit3.isInitialized) {
                retrofit3 = Retrofit.Builder().baseUrl(EMAIL_URL)
                    .addConverterFactory(GsonConverterFactory.create()).client(
                        OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
                            .readTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS)
                            .addInterceptor(HttpLoggingInterceptor().apply {
                                level = HttpLoggingInterceptor.Level.BODY
                            }).build()
                    ).build()
                return retrofit3
            }
            return retrofit3
        }
    }
}
