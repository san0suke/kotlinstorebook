package br.com.systemglass.livros.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.systemglass.livros.R
import br.com.systemglass.livros.dao.Book
import br.com.systemglass.livros.dao.BookJSON
import br.com.systemglass.livros.extension.listen
import kotlinx.android.synthetic.main.book_list_row.view.*

class BooksAdapter(val booksJson: BookJSON, val onItemClick: (Book) -> Unit): RecyclerView.Adapter<BooksViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.book_list_row, parent, false)

        return BooksViewHolder(cellForRow).listen { position, type ->
            val book = booksJson.`object`.get(position)
            onItemClick(book)
        }
    }

    override fun getItemCount(): Int {
        return booksJson.`object`.size
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        val book = booksJson.`object`.get(position)
        holder.view.bookTitleTextView.text = book.title
        holder.view.bookResumeTextView.text = book.resume
    }

}

class BooksViewHolder(val view: View): RecyclerView.ViewHolder(view) {

}