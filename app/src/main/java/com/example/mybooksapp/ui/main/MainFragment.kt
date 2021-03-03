package com.example.mybooksapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mybooksapp.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding
//    private lateinit var adapter : BookListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = MainFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.bookData?.observe(viewLifecycleOwner, Observer {

            val bookNames = StringBuilder()
            for (book in it) {
                bookNames.append(book.title)
                    .append("\n")
            }
            binding.title.text = bookNames
        })


//        return inflater.inflate(R.layout.main_fragment, container, false)
        return binding.root
    }


}