package com.example.mymealsproyect.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.example.mymeal.R
import com.example.mymeal.databinding.FragmentRegisterBinding
import com.example.mymealsproyect.ui.view.LoginFragment.Companion.EMAIL
import com.example.mymealsproyect.ui.view.LoginFragment.Companion.FIRSTNAME
import com.example.mymealsproyect.ui.view.LoginFragment.Companion.LASTNAME
import com.example.mymealsproyect.ui.view.LoginFragment.Companion.PROVIDER
import com.example.mymealsproyect.ui.view.LoginFragment.Companion.USERNAME
import com.example.mymealsproyect.ui.viewmodel.RegisterFragmentViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RegisterFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnContinue.setOnClickListener {
            onContinueListener()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun onContinueListener() {
        val email = binding.etEmailRegister
        val password = binding.etPassword
        val username = binding.etUsername
        val firstname = binding.etFirstName
        val lastname = binding.etLastName

        if (email.text.isNotEmpty() && password.text.isNotEmpty() && username.text.isNotEmpty() && firstname.text.isNotEmpty() && lastname.text.isNotEmpty()) {
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        viewModel.insertUserInDatabase(it.result.user?.email, requireContext())
                        startActivity(
                            it.result?.user?.email ?: "",
                            ProviderType.BASIC,
                            username.text.toString(),
                            firstname.text.toString(),
                            lastname.text.toString()
                        )
                    } else {
                        showAlert()
                    }
                }
        } else {
            showAlert()
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Error")
        builder.setMessage("Some parameter is incomplete")
        builder.setPositiveButton("Ok", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun startActivity(
        email: String,
        provider: ProviderType,
        username: String,
        firstname: String,
        lastname: String
    ) {
        val firebaseDB = FirebaseFirestore.getInstance()
        val intent = Intent(requireActivity(), MainActivity::class.java).apply {
            putExtra(EMAIL, email)
            putExtra(PROVIDER, provider)
            putExtra(USERNAME, username)
            putExtra(FIRSTNAME, firstname)
            putExtra(LASTNAME, lastname)
        }

        firebaseDB.collection("users").document(email).set(
            hashMapOf(
                "username" to username,
                "firstname" to firstname,
                "lastname" to lastname
            )
        )

        startActivity(intent)
    }
}