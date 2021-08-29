package com.example.merucabassignment.ui.landing

import com.example.merucabassignment.data.network.ApiRequest
import com.example.merucabassignment.data.network.NetWorkErrorCall
import com.example.merucabassignment.data.network.RetrofitClient
import retrofit2.await

class ChickenRequest(val listner: LandingViewModelListner):ApiRequest {
    override suspend fun request(network: NetWorkErrorCall) {
        val response= RetrofitClient.getApiInterface(network).getData("chicken").await()
        if(response.count>0){
            listner.getChickenDataApiSuccess(response)
        }
    }

}