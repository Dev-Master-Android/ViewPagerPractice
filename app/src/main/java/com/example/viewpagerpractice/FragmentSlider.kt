package com.example.viewpagerpractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2

class FragmentSlider : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_slider, container, false)

        val titleTextView = view.findViewById<TextView>(R.id.titleTextView)
        val descriptionTextView = view.findViewById<TextView>(R.id.descriptionTextView)
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val startButton = view.findViewById<Button>(R.id.startButton)

        val title = arguments?.getString("title")
        val description = arguments?.getString("description")
        val imageResId = arguments?.getInt("imageResId")
        val isLastPage = arguments?.getBoolean("isLastPage") ?: false
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)


        titleTextView.text = title
        descriptionTextView.text = description
        imageView.setImageResource(imageResId ?: 0)
        if (isLastPage){
            startButton.visibility = View.VISIBLE
        }
        startButton.setOnClickListener {
            if (isLastPage) {
                findNavController().navigate(R.id.action_slider_to_registration)
                viewPager?.visibility = View.GONE
            } else {
                // Логика для перехода к следующей странице слайдера
            }
        }

        return view
    }

    companion object {
        fun newInstance(title: String, description: String, imageResId: Int, isLastPage: Boolean): FragmentSlider {
            val fragment = FragmentSlider()
            val args = Bundle()
            args.putString("title", title)
            args.putString("description", description)
            args.putInt("imageResId", imageResId)
            args.putBoolean("isLastPage", isLastPage)
            fragment.arguments = args
            return fragment
        }
    }
}

