package com.example.merucabassignment.ui.chicken

import com.example.merucabassignment.data.database.AppDB
import com.example.merucabassignment.data.model.RecipeType
import com.example.merucabassignment.ui.RecipeViewModel
import kotlinx.coroutines.launch

class ChickenViewModel (val db: AppDB): RecipeViewModel() {
    val dao = db.recipeDao()

    override fun getAllData() {
        uiScope.launch {
            dao.getReceipe(RecipeType.CHICKEN).also { data.postValue(it) }
        }
    }
}