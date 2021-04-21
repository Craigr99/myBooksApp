package com.example.mybooksapp.ui.main

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.mybooksapp.LOG_TAG
import com.example.mybooksapp.R
import com.example.mybooksapp.data.Book
import com.example.mybooksapp.databinding.MainFragmentBinding
import com.example.mybooksapp.ui.shared.SharedViewModel

class MainFragment : Fragment(),
    BookListAdapter.BookItemListener {

    private lateinit var viewModel: SharedViewModel
    private lateinit var binding: MainFragmentBinding
    private lateinit var adapter: BookListAdapter
    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // turn off back button
        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
        // Use options menu
        setHasOptionsMenu(true)

        // Set app name
        requireActivity().title = getString(R.string.app_name)


        binding = MainFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        navController = Navigation.findNavController(
            requireActivity(), R.id.nav_host
        )

        with(binding.recyclerView)
        {
            setHasFixedSize(true)
        }

        viewModel.bookData?.observe(viewLifecycleOwner, Observer {
            adapter = BookListAdapter(requireContext(), it.data, this)
            binding.recyclerView.adapter = adapter
        })

        // Listener for FAB button
        binding.fab.setOnClickListener {
            addBook()
        }

        return binding.root
    }

    // Options menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    // When item selected from options menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> searchBook()
            R.id.action_add -> addBookForm()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addBookForm(): Boolean {
        navController.navigate(R.id.action_add_form)
        return true
    }

    private fun searchBook(): Boolean {
        navController.navigate(R.id.action_mainFragment_to_searchFragment)
        return true
    }

    override fun onBookItemClick(book: Book) {
        Log.i(LOG_TAG, "selected book: ${book.title}")
        viewModel.selectedBook.value = book
        navController.navigate(R.id.action_nav_detail)
    }

    override fun addBook() {
        navController.navigate(R.id.action_add_form)
    }
}


