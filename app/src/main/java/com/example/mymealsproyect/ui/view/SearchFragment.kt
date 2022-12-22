package com.example.mymeal.ui.view

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymeal.databinding.FragmentSearchBinding
import com.example.mymeal.domain.model.RecipeResult
import com.example.mymeal.domain.model.toDomain
import com.example.mymeal.ui.view.adapter.SearchRecipeAdapter
import com.example.mymeal.ui.viewmodel.SearchFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(), OnQueryTextListener {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: SearchRecipeAdapter
    private var recipesResponse = mutableListOf<RecipeResult>()
    private lateinit var recyclerView: RecyclerView

    private val viewModel: SearchFragmentViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        binding.searchView.setOnQueryTextListener(this)

        viewModel.recipesResponse.observe(requireActivity()){
            recipesResponse.clear()
            recipesResponse.addAll(it.results.map { it.toDomain() })
            adapter.notifyDataSetChanged()
            initRecyclerView()
        }
    }

    private fun initRecyclerView(){
        val layoutManager = LinearLayoutManager(context)
        recyclerView = binding.rvSearchRecipe
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = SearchRecipeAdapter(recipesResponse)
        recyclerView.adapter = adapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            viewModel.searchRecipe(query.toLowerCase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}