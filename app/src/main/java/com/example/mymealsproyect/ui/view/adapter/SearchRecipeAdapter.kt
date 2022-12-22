package com.example.mymeal.ui.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.mymeal.R
import com.example.mymeal.databinding.ItemRecipeSearchBinding
import com.example.mymeal.domain.model.RecipeResult

class SearchRecipeAdapter(
    private val recipes: List<RecipeResult>
) : RecyclerView.Adapter<SearchRecipeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_recipe_search, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = recipes[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = recipes.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemRecipeSearchBinding.bind(view)

        fun bind(recipe: RecipeResult) {
            val name = recipe.title
            val image = recipe.image

            Glide.with(itemView).load(image)
                .transform(MultiTransformation(CenterCrop(), RoundedCorners(16)))
                .into(binding.ivRecipeSearch)

            binding.tvRecipeName.text = name

        }
    }

}