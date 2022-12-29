package com.example.mymealsproyect.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.mymeal.R
import com.example.mymeal.databinding.FragmentRegisterBinding
import com.example.mymeal.databinding.FragmentUserBinding
import com.example.mymealsproyect.ui.viewmodel.UserFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : Fragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UserFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUserInformationInUi()
    }

    private fun setUserInformationInUi(){

        val user = viewModel.getUserInformation(requireContext())

        binding.tvUserFullName.text = "${user.firstname} ${user.lastname}"
        binding.tvUserUsername.text = user.username

    }
}