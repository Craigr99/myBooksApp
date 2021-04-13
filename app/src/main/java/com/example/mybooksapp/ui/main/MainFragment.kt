package com.example.mybooksapp.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
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
//            val bookNames = StringBuilder()
//            for (book in it) {
//                bookNames.append(book.title)
//                    .append("\n")
//            }
//            binding.title.text = bookNames
        })


//        return inflater.inflate(R.layout.main_fragment, container, false)
        return binding.root
    }

    override fun onBookItemClick(book: Book) {
        Log.i(LOG_TAG, "selected book: ${book.title}")
        viewModel.selectedBook.value = book
        navController.navigate(R.id.action_nav_detail)
    }
}


