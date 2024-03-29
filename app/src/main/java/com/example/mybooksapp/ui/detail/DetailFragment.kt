package com.example.mybooksapp.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.mybooksapp.LOG_TAG
import com.example.mybooksapp.R
import com.example.mybooksapp.databinding.FragmentDetailBinding
import com.example.mybooksapp.ui.shared.SharedViewModel

class DetailFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

        //init viewModel
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        // Set up observer
        viewModel.selectedBook.observe(viewLifecycleOwner, Observer {
            Log.i(LOG_TAG, "selecred book ${it.title}")
        })

        val binding = FragmentDetailBinding.inflate(
            inflater, container, false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }


    // When item selected from options menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> navController.navigateUp()
            else -> super.onOptionsItemSelected(item)
        }
    }


}