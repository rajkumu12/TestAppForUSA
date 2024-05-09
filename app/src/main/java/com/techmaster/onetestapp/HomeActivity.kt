package com.techmaster.onetestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.techmaster.onetestapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    lateinit var binding:ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(DummyFragments())
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    loadFragment(DummyFragments())
                    true
                }
                R.id.navigation_booking -> {
                    loadFragment(DummyFragments())
                    true
                }
                R.id.navigation_post -> {
                    loadFragment(DummyFragments())
                    true
                }
                R.id.navigation_inbox -> {
                    loadFragment(DummyFragments())
                    true
                }
                R.id.navigation_account -> {
                    loadFragment(AccountFragments())
                    true
                }

                else -> {
                    true
                }
            }
        }

    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
}