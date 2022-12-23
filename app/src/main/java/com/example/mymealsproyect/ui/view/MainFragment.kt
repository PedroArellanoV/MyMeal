package com.example.mymeal.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.mymeal.R
import com.example.mymeal.databinding.FragmentMainBinding
import com.example.mymeal.domain.model.RecipeInformation
import com.example.mymeal.ui.view.RecipeInformationFragment.Companion.RECIPE_CREDITS_TEXT
import com.example.mymeal.ui.view.RecipeInformationFragment.Companion.RECIPE_ID
import com.example.mymeal.ui.view.RecipeInformationFragment.Companion.RECIPE_IMAGE
import com.example.mymeal.ui.view.RecipeInformationFragment.Companion.RECIPE_INGREDIENTS
import com.example.mymeal.ui.view.RecipeInformationFragment.Companion.RECIPE_INSTRUCTIONS
import com.example.mymeal.ui.view.RecipeInformationFragment.Companion.RECIPE_SUMMARY
import com.example.mymeal.ui.view.RecipeInformationFragment.Companion.RECIPE_TITLE
import com.example.mymeal.ui.viewmodel.MainFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.callbackFlow


@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainFragmentViewModel by viewModels()

    private lateinit var todayRecommendation: RecipeInformation


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        todayRecommendation()

        binding.cvRecommendation.setOnClickListener {
            goToRecommendationInformation()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun goToRecommendationInformation() {
        val bundle = bundleOf(
            RECIPE_ID to todayRecommendation.id,
            RECIPE_CREDITS_TEXT to todayRecommendation.creditsText,
            RECIPE_IMAGE to todayRecommendation.image,
            RECIPE_TITLE to todayRecommendation.title,
            RECIPE_INSTRUCTIONS to todayRecommendation.instructions,
            RECIPE_INGREDIENTS to todayRecommendation.extendedIngredients,
            RECIPE_SUMMARY to todayRecommendation.summary
        )
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            add<RecipeInformationFragment>(R.id.fragment_container, args = bundle)
        }
    }

    private fun todayRecommendation() {
        viewModel.getRandomRecipe()
        viewModel.todayRecommendation.observe(viewLifecycleOwner) {
            todayRecommendation = it

            val image = it.image
            val title = it.title
            val time = it.readyInMinutes
            val cheap = it.cheap

            Glide.with(this)
                .load(image)
                .transform(MultiTransformation(CenterCrop(), RoundedCorners(16)))
                .into(binding.ivTodaysRecommendations)

            binding.tvRecommendationsRecipeName.text = title
            binding.tvRecommendationsAuthor.text = "Ready in ${time.toString()} min."

            if (cheap == true) {
                binding.tvRecommendationsType.text = "Cheap"
            } else {
                binding.tvRecommendationsType.text = "Expensive"
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.setReorderingAllowed(true)
        fragmentTransaction.commit()
    }
}