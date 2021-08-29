package com.example.merucabassignment.data.network

import com.example.merucabassignment.utils.CommonUtils
import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor(val error: NetWorkErrorCall) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        val response = chain.proceed(builder.build())

        when (response.code) {

            500 -> {
                error.error(CommonUtils.INTERNAL_SERVER_ERROR)
            }
            404 -> {
                error.error(CommonUtils.SERVER_NOT_FOUND)
            }
            400 -> {
                error.error(CommonUtils.BAD_REQUEST)
            }
            200->{

            }

            else -> {

                error.error(CommonUtils.SOMETHING_WENT_WRONG)
            }

        }
        return response
    }
}