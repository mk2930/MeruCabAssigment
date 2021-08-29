package com.example.merucabassignment.ui.landing

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.merucabassignment.base.BaseViewModel
import com.example.merucabassignment.data.database.AppDB
import com.example.merucabassignment.data.model.Recipe
import com.example.merucabassignment.data.model.RecipeResponse
import com.example.merucabassignment.data.model.RecipeType
import com.example.merucabassignment.data.network.NetWorkErrorCall
import com.example.merucabassignment.ui.RecipeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LandingViewModel(val db:AppDB) : RecipeViewModel(), LandingViewModelListner,
    NetWorkErrorCall {
    private val repo = LandingReposatory(this, this)
    val dao=db.recipeDao()
    var potatoUpdated=MutableLiveData<Boolean>()
    var chickenUpdated=MutableLiveData<Boolean>()
    var fishUpdated=MutableLiveData<Boolean>()


    override fun getAllData() {
        showProgressBar.value=true
        uiScope.launch {
            repo.getData(RecipeType.POTATO)
            repo.getData(RecipeType.CHICKEN)
            repo.getData(RecipeType.FISH)
        }.invokeOnCompletion {
            showProgressBar.postValue(false)
        }
    }

    override fun getPotatoDataApiSuccess(response: RecipeResponse) {
           insertDatainDb(response.recipes!!, RecipeType.POTATO,potatoUpdated)

    }

    private  fun insertDatainDb(recipeList: List<Recipe>,type: RecipeType,updatedStatus:MutableLiveData<Boolean>) {
        for (recipe in recipeList){
            recipe.recipe_type=type
        }
        uiScope.launch {
          //  dao.deleteAll(type)
            dao.insertAll(recipeList)
        }.invokeOnCompletion {
            updatedStatus.postValue(true)
        }
    }

    override fun getChickenDataApiSuccess(response: RecipeResponse) {
        insertDatainDb(response.recipes!!, RecipeType.CHICKEN,chickenUpdated)
    }

    override fun getFishDataApiSuccess(response: RecipeResponse) {
        insertDatainDb(response.recipes!!, RecipeType.FISH,fishUpdated)
    }

    override fun error(errorMessages: String) {

    }


}