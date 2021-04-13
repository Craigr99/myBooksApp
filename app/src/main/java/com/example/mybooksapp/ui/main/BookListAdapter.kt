package com.example.mybooksapp.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mybooksapp.R
import com.example.mybooksapp.data.Book
import com.example.mybooksapp.databinding.BookItemBinding

class BookListAdapter(
    val context: Context,
    val booksList: List<Book>,
    val itemListener: BookItemListener
) :
    RecyclerView.Adapter<BookListAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val binding = BookItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.book_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = booksList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = booksList[position]
        with(holder.binding) {
            nameText.setText(book.title)
//            authorText.setText(book.authors)
            yearText.setText(book.publish_date)
            Glide.with(context)
                .load(book.bookImage)
                .into(bookImage)

            card.setOnClickListener {
                itemListener.onBookItemClick(book)
            }
        }

    }

    interface BookItemListener {
        fun onBookItemClick(book: Book)
    }
}
