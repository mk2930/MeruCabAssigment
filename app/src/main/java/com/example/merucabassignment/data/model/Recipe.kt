package com.example.merucabassignment.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Recipe {
    @PrimaryKey
    lateinit var recipe_id: String
    var image_url: String? = null
    var social_rank: Float? = 0F
    var _id: String? = null
    var publisher: String? = null
    var source_url: String? = null
    var publisher_url: String? = null
    var title: String? = null
    var like: Boolean? = false
    var fav: Boolean? = false
    var isDelete: Boolean? = false
    var recipe_type: RecipeType? = null
}
