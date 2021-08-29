package com.example.merucabassignment.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.merucabassignment.data.model.Recipe
import com.example.merucabassignment.data.model.RecipeType
import retrofit2.http.DELETE

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insertAll(recipe: List<Recipe>)

     @Query("select * from recipe where recipe_type=:type")
     fun getReceipe(type:RecipeType): List<Recipe>

    @Query("update recipe set `like`=:like where recipe_id=:id")
    fun setLike(like:Boolean,id:String)

    @Query("update recipe set `Fav`=:isSaved where recipe_id=:id")
    fun saveFav(isSaved:Boolean,id:String)
    @Query("select * from recipe where fav==1")
    fun getsaveFav():List<Recipe>



    @Query("update recipe set `isDelete`=:delete where recipe_id=:id")
    fun delete(delete:Boolean,id:String)

    @Query("delete from recipe where recipe_type=:type")
    fun deleteAll(type:RecipeType)

}