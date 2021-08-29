package com.example.merucabassignment.utils

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.merucabassignment.data.database.AppDB
import com.example.merucabassignment.ui.adapter.RecipeAdapterViewModel
import com.example.merucabassignment.ui.chicken.ChickenViewModel
import com.example.merucabassignment.ui.fav.FavViewModel
import com.example.merucabassignment.ui.fish.FishViewModel
import com.example.merucabassignment.ui.landing.LandingViewModel
import com.example.merucabassignment.ui.potato.PotatoViewModel

class ViewModelFactory(val db:AppDB): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when(modelClass){

            LandingViewModel::class.java->{
              return  LandingViewModel(db) as T
            }
            PotatoViewModel::class.java->{
                Log.e("adsadsa","ViewModelFactory")
                return  PotatoViewModel(db) as T
            }
            ChickenViewModel::class.java->{
                Log.e("adsadsa","ViewModelFactory")
                return  ChickenViewModel(db) as T
            }
            FishViewModel::class.java->{
                Log.e("adsadsa","ViewModelFactory")
                return  FishViewModel(db) as T
            }
            RecipeAdapterViewModel::class.java->{
                return  RecipeAdapterViewModel(db) as T
            }
            FavViewModel::class.java->{
                return  FavViewModel(db) as T
            }




        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}