package com.example.viewpagerpractice

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

// SliderAdapter.kt
class SliderAdapter(fragment: MainActivity, private val fragments: List<Fragment>) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = fragments.size
    override fun createFragment(position: Int): Fragment = fragments[position]
}
