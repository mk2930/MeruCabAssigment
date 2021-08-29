package com.example.merucabassignment.ui.fav

import com.example.merucabassignment.base.BaseViewModel
import com.example.merucabassignment.data.database.AppDB
import com.example.merucabassignment.ui.RecipeViewModel
import kotlinx.coroutines.launch

class FavViewModel(val db:AppDB):RecipeViewModel() {

   val dao=db.recipeDao()

    override fun getAllData() {
        uiScope.launch {
            data.postValue(dao.getsaveFav())
        }


    }
}