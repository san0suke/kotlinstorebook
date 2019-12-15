package br.com.systemglass.livros.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.systemglass.livros.R
import br.com.systemglass.livros.dao.Book
import kotlinx.android.synthetic.main.activity_book_details.*


class BookDetailsActivity : AppCompatActivity() {

    private var book: Book? = null

    companion object {

        private val SELECTED_BOOK = "SELECTED_BOOK"

        fun newIntent(context: Context, book: Book): Intent {
            val intent = Intent(context, BookDetailsActivity::class.java)
            intent.putExtra(SELECTED_BOOK, book)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)
        setTitle(R.string.book_details_title)

        book = intent.getSerializableExtra(SELECTED_BOOK) as Book
        book?.let {
            prepareScreen(it)
        }
        buyButtonClick()
    }

    private fun prepareScreen(book: Book) {
        bookDetailTitleTextView.text = book.title
        bookDetailPriceTextView.text = "R$ "+ book.price
        bookDetailAuthorTextView.text = book.author
        bookDetailYearTextView.text = book.year.toString()
        bookDetailPublisherTextView.text = book.publisher
        bookDetailResumeTextView.text = book.resume
    }

    private fun buyButtonClick() {
        bookDetailBuyButton.setOnClickListener {
            book?.let {
                val addItemToCartIntent = CartActivity.addItemToCartIntent(this, it)
                startActivity(addItemToCartIntent)
            }
        }
    }
}
