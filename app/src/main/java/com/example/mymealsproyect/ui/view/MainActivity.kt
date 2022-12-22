package com.example.mymealsproyect.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.mymeal.R
import com.example.mymeal.databinding.ActivityMainBinding
import com.example.mymeal.ui.view.MainFragment
import com.example.mymeal.ui.view.SearchFragment
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

enum class ProviderType {
    BASIC
}

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkUserLogged()

        val navView: NavigationView = binding.navView

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_view_home -> replaceFragment(MainFragment())
//                R.id.nav_view_favourite -> replaceFragment(FavouriteFragment())
//                R.id.nav_view_myrecipes -> replaceFragment(MyRecipesFragment())
//                R.id.nav_view_calendar -> replaceFragment(CalendarFragment())
//                R.id.nav_view_shoppinglist -> replaceFragment(ShoppingListFragment())
            }
            true
        }


        binding.bnvMainActivity.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.mHome -> replaceFragment(MainFragment())
                R.id.mSearch -> replaceFragment(SearchFragment())

                else -> {}
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.setReorderingAllowed(true)
        fragmentTransaction.commit()
    }

    private fun checkUserLogged() {
        val mAuth = FirebaseAuth.getInstance()

        if (mAuth.currentUser != null) {
            commitFragment()
        } else {
            commitLoginActivity()
        }
    }

    private fun commitFragment() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<MainFragment>(R.id.fragment_container)
        }
    }

    private fun commitLoginActivity() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
    }
}