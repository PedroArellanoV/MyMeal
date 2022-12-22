package com.example.mymealsproyect.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.mymeal.R
import com.example.mymeal.databinding.ActivityAuthBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        login()

        binding.tvRegister.setOnClickListener {
            replaceFragment(RegisterFragment())
            binding.btnLogin.isVisible = false
        }
    }

    private fun login() {
        binding.btnLogin.setOnClickListener {
            if (binding.etEmailLogin.text.isNotEmpty() && binding.etPasswordLogin.text.isNotEmpty()) {

                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    binding.etEmailLogin.text.toString(),
                    binding.etPasswordLogin.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showMainActivity(it.result.user?.email ?: "", ProviderType.BASIC)
                    } else showAlert()
                }
            } else showAlert()
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Some parameter does not match")
        builder.setPositiveButton("Ok", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showMainActivity(email: String, provider: ProviderType) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra(EMAIL, email)
            putExtra(PROVIDER, provider.name)
        }
        startActivity(intent)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.setReorderingAllowed(true)
        fragmentTransaction.commit()
    }

    companion object{
        const val EMAIL = "email"
        const val PROVIDER = "provider"
        const val USERNAME = "username"
    }

}