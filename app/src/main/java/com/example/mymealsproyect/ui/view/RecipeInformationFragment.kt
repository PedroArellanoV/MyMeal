package com.example.mymeal.ui.view

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.mymeal.data.remote.model.RequestExtendedIngredients
import com.example.mymeal.databinding.FragmentRecipeInformationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeInformationFragment : Fragment() {

    private var _binding: FragmentRecipeInformationBinding? = null
    private val binding get() = _binding!!

    private var recipeId: Int? = null
    private var recipeName: String? = null
    private var recipeAuthor: String? = null
    private var recipeCreditsText: String? = null
    private var recipeImage: String? = null
    private var recipeTitle: String? = null
    private var recipeSummary: String? = null
    private var recipeIngredients: ArrayList<RequestExtendedIngredients>? = null
    private var recipeInstructions: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            recipeId = it.getInt(RECIPE_ID)
            recipeName = it.getString(RECIPE_NAME)
            recipeAuthor = it.getString(RECIPE_AUTHOR)
            recipeCreditsText = it.getString(RECIPE_CREDITS_TEXT)
            recipeImage = it.getString(RECIPE_IMAGE)
            recipeTitle = it.getString(RECIPE_TITLE)
            recipeSummary = it.getString(RECIPE_SUMMARY)
            recipeIngredients = it.getParcelableArrayList(RECIPE_INGREDIENTS)
            recipeInstructions = it.getString(RECIPE_INSTRUCTIONS)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipeInformationBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addInformationToContainers()

    }

    private fun addInformationToContainers() {
        binding.tvRecipeTitle.text = recipeTitle
        binding.tvSummary.text = Html.fromHtml(recipeSummary)
        binding.tvInstructions.text =  Html.fromHtml(recipeInstructions)
        binding.tvCreditsText.text = "Credits to: $recipeCreditsText"

        Glide.with(this)
            .load(recipeImage)
            .centerCrop()
            .into(binding.ivRecipeInformation)
    }

    companion object {
        const val RECIPE_ID = "recipe_id"
        const val RECIPE_NAME = "recipe_name"
        const val RECIPE_AUTHOR = "recipe_author"
        const val RECIPE_CREDITS_TEXT = "recipe_credits_text"
        const val RECIPE_IMAGE = "recipe_image"
        const val RECIPE_TITLE = "recipe_title"
        const val RECIPE_SUMMARY = "recipe_summary"
        const val RECIPE_INGREDIENTS = "recipe_ingredients"
        const val RECIPE_INSTRUCTIONS = "recipe_instructions"

        @JvmStatic
        fun newInstance(
            recipeId: Int,
            recipeName: String,
            recipeAuthor: String,
            recipeCreditsText: String,
            recipeImage: String,
            recipeTitle: String,
            recipeSummary: String,
            recipeInstructions: String
        ) =
            RecipeInformationFragment().apply {
                arguments = Bundle().apply {
                    putInt(RECIPE_ID, recipeId)
                    putString(RECIPE_NAME, recipeName)
                    putString(RECIPE_AUTHOR, recipeAuthor)
                    putString(RECIPE_CREDITS_TEXT, recipeCreditsText)
                    putString(RECIPE_IMAGE, recipeImage)
                    putString(RECIPE_TITLE, recipeTitle)
                    putString(RECIPE_SUMMARY, recipeSummary)
                    putParcelableArrayList(RECIPE_INGREDIENTS, recipeIngredients)
                    putString(RECIPE_INSTRUCTIONS, recipeInstructions)
                }
            }
    }
}