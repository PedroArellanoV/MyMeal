package com.example.mymealsproyect.ui.view

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.mymeal.R
import com.example.mymeal.databinding.ActivityMainBinding
import com.example.mymeal.ui.view.MainFragment
import com.example.mymealsproyect.domain.model.UiUserInformation
import com.example.mymealsproyect.ui.view.LoginFragment.Companion.EMAIL
import com.example.mymealsproyect.ui.view.LoginFragment.Companion.FIRSTNAME
import com.example.mymealsproyect.ui.view.LoginFragment.Companion.LASTNAME
import com.example.mymealsproyect.ui.view.LoginFragment.Companion.USERNAME
import com.example.mymealsproyect.ui.viewmodel.MainActivityViewModel
import com.example.mymealsproyect.utils.PreferenceManager
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

enum class ProviderType {
    BASIC
}

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var user: UiUserInformation
    private val viewModel: MainActivityViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkUserLogged()

        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_view_home -> replaceFragment(MainFragment())
//                R.id.nav_view_favourite -> replaceFragment(FavouriteFragment())
//                R.id.nav_view_myrecipes -> replaceFragment(MyRecipesFragment())
//                R.id.nav_view_calendar -> replaceFragment(CalendarFragment())
//                R.id.nav_view_shoppinglist -> replaceFragment(ShoppingListFragment())
//                R.id.nav_view_settings ->
                R.id.nav_view_logout -> logoutRequest()
            }
            true
        }

        binding.bnvMainActivity.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.mHome -> replaceFragment(MainFragment())
                R.id.mSearch -> replaceFragment(SearchFragment())
                R.id.mUser -> replaceFragment(UserFragment())

                else -> {}
            }
            true
        }
    }

    private fun setupUserInformation() {
        val pref = PreferenceManager(this)

        val username = pref.getStringPref(USERNAME)
        val firstname = pref.getStringPref(FIRSTNAME)
        val lastname = pref.getStringPref(LASTNAME)
        val email = pref.getStringPref(EMAIL)

        user = UiUserInformation(firstname, lastname, username, email)

        Log.d("pedro", user.username)
        val view = binding.navView.getHeaderView(0)
        view.findViewById<TextView>(R.id.tvHeaderUserName).text = user.username
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.setReorderingAllowed(true)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun checkUserLogged() {
        val mAuth = FirebaseAuth.getInstance()

        if (mAuth.currentUser != null) {
            commitMainFragment()
            setupUserInformation()
        } else {
            commitAuthActivity()
        }
    }

    private fun commitMainFragment() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<MainFragment>(R.id.fragment_container)
        }
    }

    private fun commitAuthActivity() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
    }

    private fun logoutRequest() {
        val logout = { dialog: DialogInterface, which: Int ->

            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Log out")
        builder.setMessage("Are you sure do you want to log out?")
        builder.setPositiveButton("No", null)
        builder.setNegativeButton("Yes", DialogInterface.OnClickListener(function = logout))
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}