package com.example.merucabassignment.ui.landing

import com.example.merucabassignment.data.model.RecipeType

interface FunctionsLandingReposatory {
suspend fun getData(recipeType:RecipeType)

}