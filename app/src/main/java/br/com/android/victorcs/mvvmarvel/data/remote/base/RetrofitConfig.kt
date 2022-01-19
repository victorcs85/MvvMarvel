package br.com.android.victorcs.mvvmarvel.data.remote.base

import br.com.android.victorcs.mvvmarvel.BuildConfig
import br.com.android.victorcs.mvvmarvel.data.remote.ApiService
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient


object RetrofitConfig {

    private const val TIMEOUT = 30L
    private const val TIMEOUT_DEBUG = 120L
    private const val TAG = ">>> RxJava"

    private const val EA_DURATION = 120L
    private const val FORBIDDEN = 403

    lateinit var api: ApiService

    fun <T> create(
        service: Class<T>,
        baseUrl: String
    ): T {
        val okFullHttpClient = getFullHttpClient().build()
        return buildApi(baseUrl, okFullHttpClient).create(service)
    }

    private fun buildApi(url: String, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(MoshiBuilder.create()))
            .build()

    private fun getFullHttpClient(): OkHttpClient.Builder = getHttpClient()
        .addInterceptor(getHttpLogging())

    private fun getHttpLogging(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else HttpLoggingInterceptor.Level.NONE
        )

    private fun getHttpClient(): OkHttpClient.Builder {
        val timeout = if (BuildConfig.DEBUG) TIMEOUT_DEBUG else TIMEOUT
        return OkHttpClient.Builder()
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .addInterceptor(getBasicInterceptor())
    }


    private fun getBasicInterceptor(): Interceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY.takeIf { BuildConfig.DEBUG }
            ?: HttpLoggingInterceptor.Level.NONE
        return logging
    }
}