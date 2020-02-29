package com.wzl.bottomnavigationdemo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation

import com.wzl.bottomnavigationdemo.R
import com.wzl.bottomnavigationdemo.ui.home.HomeViewModel

/**
 * A simple [Fragment] subclass.
 */
class HomeFirstFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home_first, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(this, Observer {
            textView.text = it
        })
        val button: Button = root.findViewById(R.id.button)
        button.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.action_homeFirstFragment_to_homeSecondFragment)
        )
        return root
    }

}
