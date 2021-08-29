package com.example.merucabassignment.data.network

interface ApiRequest {
    suspend fun  request(listner:NetWorkErrorCall)
}