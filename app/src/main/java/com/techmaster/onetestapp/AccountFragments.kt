package com.techmaster.onetestapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.techmaster.onetestapp.databinding.FragmentAccountFragmentsBinding

class AccountFragments : Fragment() {
    lateinit var binding: FragmentAccountFragmentsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding=FragmentAccountFragmentsBinding.inflate(layoutInflater)

        binding.apply {
            binding.tabLayout.addTab(tabLayout.newTab().setText("Month To Date"));
            tabLayout.addTab(tabLayout.newTab().setText("Last Month"));
            tabLayout.addTab(tabLayout.newTab().setText("Year To Date"));
            tabLayout.tabGravity = TabLayout.GRAVITY_FILL;
        }

        val activity = requireActivity() as FragmentActivity

        val adapter = MyAdapter(activity)
        var tabTitles= arrayOf("Month To Date","Last Month","Year To Date")
        binding.viewpager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

        return binding.root
    }
}