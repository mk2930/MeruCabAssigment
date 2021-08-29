package com.example.merucabassignment.ui.landing

import com.example.merucabassignment.data.model.RecipeResponse

interface LandingViewModelListner {
     fun  getPotatoDataApiSuccess(response:RecipeResponse)
     fun getChickenDataApiSuccess(response:RecipeResponse);
     fun getFishDataApiSuccess(response:RecipeResponse);



}