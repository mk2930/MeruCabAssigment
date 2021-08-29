package com.example.merucabassignment.ui.adapter

import androidx.lifecycle.MutableLiveData
import com.example.merucabassignment.base.BaseViewModel
import com.example.merucabassignment.data.database.AppDB
import com.example.merucabassignment.data.model.Recipe
import kotlinx.coroutines.launch

class RecipeAdapterViewModel(val db: AppDB) : BaseViewModel() {
    val recipe = MutableLiveData<Recipe>()
    val likeData = MutableLiveData<Boolean>()
    val dao = db.recipeDao()
    fun likeAndDislike(recipe: Recipe) {
        uiScope.launch {
            dao.setLike(like = recipe.like!!, recipe.recipe_id!!)
        }
    }

    fun saved(recipe: Recipe) {
        uiScope.launch {
            dao.saveFav(  recipe.fav!!, recipe.recipe_id!!)
        }
    }
    fun deleteItem(recipe: Recipe) {
        uiScope.launch {
            dao.delete(  true, recipe.recipe_id!!)
        }
    }

}