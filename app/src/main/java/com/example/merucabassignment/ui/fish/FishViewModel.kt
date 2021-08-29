package com.example.merucabassignment.ui.fish

import com.example.merucabassignment.data.database.AppDB
import com.example.merucabassignment.data.model.RecipeType
import com.example.merucabassignment.ui.RecipeViewModel
import kotlinx.coroutines.launch

class FishViewModel(val db: AppDB) : RecipeViewModel() {
    val dao = db.recipeDao()


    override fun getAllData() {
        uiScope.launch {
            dao.getReceipe(RecipeType.FISH).also { data.postValue(it) }

        }
    }
}