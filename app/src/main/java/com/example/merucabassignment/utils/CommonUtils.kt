package com.example.merucabassignment.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

object CommonUtils {
    const val BASE_URL="https://recipesapi.herokuapp.com/api/"
    const val SERVER_NOT_FOUND="Request Not Found"
    const val SOMETHING_WENT_WRONG="Something Went Wrong"
    const val INTERNAL_SERVER_ERROR="Internal Serevr Error"
    const val BAD_REQUEST="Bad Request"
    const val NO_INTERNET="Internet Connection Failed"
    const val DELETE_ITEM="delete ?"
    const val DELETE_SUB_ITEM="Are you sure you want to delete this entry?"




    @Suppress("DEPRECATION")
    fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                }
            }
        } else {
            cm?.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        result = true
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        result = true
                    }
                }
            }
        }
        return result
    }

}