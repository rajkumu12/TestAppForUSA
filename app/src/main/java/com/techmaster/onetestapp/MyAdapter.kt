package com.techmaster.onetestapp

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter


class MyAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val NUM_PAGES = 3 // Number of pages in the ViewPager

    override fun getItemCount(): Int {
        return NUM_PAGES
    }

    override fun createFragment(position: Int): Fragment {
        // Return the fragment for the given position
        return when (position) {
            0 -> FragemntYeartoDate()
            1 -> FragemntYeartoDate()
            2 -> FragemntYeartoDate()
            // Add additional cases for more fragments
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}