package com.example.merucabassignment.ui.landing

import com.example.merucabassignment.data.model.RecipeType
import com.example.merucabassignment.data.network.NetWorkErrorCall

class LandingReposatory(val listner: LandingViewModelListner, val network: NetWorkErrorCall) :
    FunctionsLandingReposatory {


    override suspend fun getData(recipeType: RecipeType) {
        when (recipeType) {
            RecipeType.CHICKEN -> {
                ChickenRequest(listner).request(network)
            }
            RecipeType.FISH -> {
                FishRequest(listner).request(network)

            }
            RecipeType.POTATO -> {
                PotatoRequest(listner).request(network)
            }

        }


    }


}