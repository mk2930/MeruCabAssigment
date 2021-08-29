package com.example.merucabassignment.data.network


import androidx.room.Dao
import com.example.merucabassignment.data.model.RecipeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query
@Dao
interface ApiInterface {

@GET("search")
fun getData(@Query("q") q:String):Call<RecipeResponse>

}