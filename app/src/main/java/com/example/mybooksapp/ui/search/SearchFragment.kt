package com.example.mybooksapp.ui.search

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.mybooksapp.R
import com.example.mybooksapp.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private lateinit var navController: NavController
    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSearchBinding.inflate(inflater, container, false)

        //Get reference to activity that owns this fragment
        (activity as AppCompatActivity).supportActionBar?.let {
            // if supportActionBar not null
            it.setHomeButtonEnabled(true)  // display home button
            it.setDisplayShowHomeEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_back) // set to arrow icon
        }
        // Use options menu
        setHasOptionsMenu(true)

        navController = Navigation.findNavController(
            requireActivity(), R.id.nav_host
        )

        // Click listener for search button
        binding.searchButton.setOnClickListener {
            val action =
                SearchFragmentDirections.actionSearchFragmentToMainFragment(binding.searchInput.text.toString())
            findNavController().navigate(action)

            // Hide keyboard
            val imm =
                requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
        }


        // Inflate the layout for this fragment
        return binding.root
    }


    // When item selected from options menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Hide keyboard
        val imm =
            requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)

        return when (item.itemId) {
            android.R.id.home -> navController.navigateUp()
            else -> super.onOptionsItemSelected(item)
        }
    }
    

}