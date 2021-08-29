package com.example.merucabassignment.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.merucabassignment.data.model.Recipe

@Database(entities = arrayOf(Recipe::class),version = 2)
abstract class AppDB:RoomDatabase() {
    abstract fun recipeDao():RecipeDao

    companion object {
        var instance: AppDB? = null

        @Synchronized
        open fun getIntance(context: Context?): AppDB? {
            if (instance == null) {
                instance = context?.applicationContext?.let {
                    Room.databaseBuilder(
                        it,
                        AppDB::class.java, "database-name"
                    ).build()
                }
            }

            return instance
        }
    }
}