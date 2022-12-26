package com.example.mymealsproyect.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymeal.R
import com.example.mymeal.databinding.FragmentSearchBinding
import com.example.mymeal.domain.model.RecipeResult
import com.example.mymeal.domain.model.toDomain
import com.example.mymeal.ui.viewmodel.SearchFragmentViewModel
import com.example.mymealsproyect.ui.view.adapter.SearchFragmentAdapter
import com.google.protobuf.Empty
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(), OnQueryTextListener {
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: SearchFragmentAdapter
    private lateinit var recyclerView: RecyclerView
    private var results = mutableListOf<RecipeResult>()

    private val viewModel: SearchFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

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

        val layoutManager = LinearLayoutManager(context)
        recyclerView = binding.rvSearchFragment
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = SearchFragmentAdapter(results)
        recyclerView.adapter = adapter

        viewModel.recipesResponse.observe(viewLifecycleOwner){ it ->
            results.clear()
            results.addAll(it.results.map { it.toDomain() })
            adapter.notifyDataSetChanged()
            Toast.makeText(context, results[0].title, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()){
            viewModel.searchRecipe(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    companion object {

        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}