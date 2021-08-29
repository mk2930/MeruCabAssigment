package com.example.merucabassignment.ui.adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.merucabassignment.R
import com.example.merucabassignment.data.database.AppDB
import com.example.merucabassignment.data.model.Recipe
import com.example.merucabassignment.databinding.LayoutRecipeBinding
import com.example.merucabassignment.utils.CommonUtils
import com.example.merucabassignment.utils.ViewModelFactory
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso

class RecipeAdapter(val list: ArrayList<Recipe>) :
    RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {
    lateinit var binding: LayoutRecipeBinding
    lateinit var vm: RecipeAdapterViewModel


    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        lateinit var iv_like: ImageView
        lateinit var iv_saved: ImageView
        lateinit var iv_recipe_image: ImageView
        lateinit var ivDelete: ImageView

        lateinit var tv_title: TextView
        lateinit var tv_publisher_name: TextView



        init {
            iv_like = view.findViewById(R.id.iv_like)
            iv_saved = view.findViewById(R.id.iv_saved)
            iv_recipe_image = view.findViewById(R.id.iv_recipe_image)
            tv_title = view.findViewById(R.id.tv_title)
            tv_publisher_name = view.findViewById(R.id.tv_publisher_name)
            ivDelete = view.findViewById(R.id.iv_delete)


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeAdapter.ViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.layout_recipe,
            parent,
            false
        )
        val db = AppDB.getIntance(parent.context as FragmentActivity)
        var factory = ViewModelFactory(db!!)
        vm =
            ViewModelProvider(parent.context as FragmentActivity, factory).get(
                RecipeAdapterViewModel::class.java
            )
        binding.lifecycleOwner = parent.context as FragmentActivity
        binding.vm = vm

        return RecipeAdapter.ViewHolder(binding.root)

    }

    override fun onBindViewHolder(holder: RecipeAdapter.ViewHolder, position: Int) {
        val recipe = list.get(position)
        holder.tv_title.setText(recipe.title)
        holder.tv_publisher_name.setText(recipe.publisher)
        Picasso.get().load(recipe.image_url).placeholder(R.drawable.ic_baseline_broken_image_24)
            .error(R.drawable.ic_baseline_broken_image_24).into(holder.iv_recipe_image)
        toggleLike(holder.iv_like, recipe.like!!)
        togglesaved(holder.iv_saved, recipe.fav!!)

        holder.ivDelete.setOnClickListener {
            AlertDialog.Builder( holder.ivDelete.context)
                .setTitle(CommonUtils.DELETE_ITEM)
                .setMessage(CommonUtils.DELETE_SUB_ITEM) // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(
                    android.R.string.yes
                ) { dialog, which ->
                    list.removeAt(position)
                    recipe.isDelete = true;
                    vm.deleteItem(recipe)
                    notifyDataSetChanged()

                }
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()


        }


        holder.iv_like.setOnClickListener {
            recipe.like = recipe.like?.not()
            vm.likeAndDislike(recipe)
            notifyItemChanged(position)
        }
        holder.iv_saved.setOnClickListener {
            recipe.fav = recipe.fav?.not()
            vm.saved(recipe)
            notifyItemChanged(position)

        }


    }

    private fun togglesaved(ivsave: ImageView, saved: Boolean) {
        if (saved) {
            ivsave.setImageResource(R.drawable.ic_baseline_done_24)
        } else {
            ivsave.setImageResource(R.drawable.ic_baseline_save_alt_24)
        }
    }

    private fun toggleLike(ivLike: ImageView, like: Boolean) {
        if (like) {
            ivLike.setImageResource(R.drawable.ic_baseline_thumb_up_24)
        } else {
            ivLike.setImageResource(R.drawable.ic_outline_thumb_up_24)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


}