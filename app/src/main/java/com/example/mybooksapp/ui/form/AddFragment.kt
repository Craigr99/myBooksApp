package com.example.mybooksapp.ui.form

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
import com.example.mybooksapp.R
import com.example.mybooksapp.ui.shared.SharedViewModel
import kotlinx.android.synthetic.main.fragment_add_form.view.*


class AddFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // show back button
        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        setHasOptionsMenu(true)

        requireActivity().title = "Add new book"

        navController = Navigation.findNavController(
            requireActivity(), R.id.nav_host
        )

        val view: View = inflater!!.inflate(R.layout.fragment_add_form, container, false)


        // Listener for FAB button
        val addBtn = view.add_button
        addBtn.setOnClickListener {
            saveBook()
        }


        return view
    }

    private fun saveBook() {
        // Hide keyboard
        val imm =
            requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)

        navController.navigateUp()
    }


    // Handle click on back button
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Hide keyboard
        val imm =
            requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)

        if (item.itemId == android.R.id.home) {
            navController.navigateUp()
        }
        return super.onOptionsItemSelected(item)
    }

}