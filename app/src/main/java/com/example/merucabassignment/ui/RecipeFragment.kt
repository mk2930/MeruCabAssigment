package com.example.merucabassignment.ui

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.merucabassignment.base.BaseFragment
import com.example.merucabassignment.data.model.Recipe
import com.example.merucabassignment.ui.adapter.RecipeAdapter
import com.example.merucabassignment.ui.landing.LandingViewModel

abstract class RecipeFragment:BaseFragment() {
     var list=ArrayList<Recipe>()
     lateinit var adapter: RecipeAdapter
    lateinit var activityVm: LandingViewModel
     fun setRecylerView(view:RecyclerView,context: Context){
         adapter= RecipeAdapter(list)
         view.layoutManager=LinearLayoutManager(context)
         view.setHasFixedSize(true)
         view.adapter=adapter

     }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityVm= activity?.let { ViewModelProvider(it).get(LandingViewModel::class.java) }!!
    }


    fun addList(data:ArrayList<Recipe>){
        list.addAll(data)
        adapter.notifyDataSetChanged()
    }

}