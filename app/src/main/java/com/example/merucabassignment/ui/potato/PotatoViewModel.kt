package com.example.merucabassignment.ui.potato

import com.example.merucabassignment.data.database.AppDB
import com.example.merucabassignment.data.model.RecipeType
import com.example.merucabassignment.ui.RecipeViewModel
import kotlinx.coroutines.launch

class PotatoViewModel(val db: AppDB) : RecipeViewModel() {
    val dao = db.recipeDao()
    override fun getAllData() {
        uiScope.launch {
            dao.getReceipe(RecipeType.POTATO).also { data.postValue(it) }

        }
    }

}


