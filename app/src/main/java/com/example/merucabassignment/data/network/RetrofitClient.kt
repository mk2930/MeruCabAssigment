package com.example.merucabassignment.data.network

import com.example.merucabassignment.utils.CommonUtils
import okhttp3.EventListener
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

   fun getClient(listner: NetWorkErrorCall): Retrofit {
        return Retrofit.Builder().baseUrl(CommonUtils.BASE_URL)
            .client(getokHttp(listner))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getokHttp(listner: NetWorkErrorCall): OkHttpClient {
        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor(ApiInterceptor(listner))
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)

        return httpClient.build()
    }
    fun getApiInterface(listner: NetWorkErrorCall): ApiInterface {
        return getClient(listner).create(ApiInterface::class.java)
    }
}