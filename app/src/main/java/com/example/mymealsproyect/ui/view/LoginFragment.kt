package com.example.mymealsproyect.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.mymeal.R
import com.example.mymeal.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        login()

        binding.tvRegister.setOnClickListener {
            goToRegisterFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun login() {
        binding.btnLogin.setOnClickListener {
            if (binding.etEmailLogin.text.isNotEmpty() && binding.etPasswordLogin.text.isNotEmpty()) {

                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    binding.etEmailLogin.text.toString(),
                    binding.etPasswordLogin.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        goToMainActivity(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else showAlert()
                }
            } else showAlert()
        }
    }

    private fun goToRegisterFragment() {
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            addToBackStack(null)
            add<RegisterFragment>(R.id.fragment_container)
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Error")
        builder.setMessage("Some parameter does not match")
        builder.setPositiveButton("Ok", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun goToMainActivity(email: String, provider: ProviderType){

        val intent = Intent(requireActivity(), MainActivity::class.java).apply {
            putExtra(EMAIL, email)
            putExtra(PROVIDER, provider.name)
        }
        startActivity(intent)

    }

    companion object{
        const val EMAIL = "email"
        const val PROVIDER = "provider"
        const val USERNAME = "username"
    }

}