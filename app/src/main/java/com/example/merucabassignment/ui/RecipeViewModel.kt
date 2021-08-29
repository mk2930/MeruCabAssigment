package com.example.merucabassignment.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.merucabassignment.base.BaseViewModel
import com.example.merucabassignment.data.model.Recipe

abstract class RecipeViewModel:BaseViewModel() {
    var data= MutableLiveData<List<Recipe>>()
    abstract fun getAllData();

}