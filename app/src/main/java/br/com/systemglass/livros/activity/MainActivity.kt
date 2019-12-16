package br.com.systemglass.livros.activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.systemglass.livros.R
import br.com.systemglass.livros.adapter.BooksAdapter
import br.com.systemglass.livros.dao.Book
import br.com.systemglass.livros.provider.BooksProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview_books.layoutManager = LinearLayoutManager(this)

        startActivity(ListClientSalesActivity.newIntent(this, 1))
//        BooksProvider().getBookList {
//            runOnUiThread {
//                recyclerview_books.adapter = BooksAdapter(it, onItemClick = ::bookClicked)
//            }
//        }
    }

    fun bookClicked(book: Book) {
        startActivity(BookDetailsActivity.newIntent(this, book))
    }
}
