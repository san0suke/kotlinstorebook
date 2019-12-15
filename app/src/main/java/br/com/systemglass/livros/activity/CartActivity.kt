package br.com.systemglass.livros.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.systemglass.livros.R
import br.com.systemglass.livros.adapter.CartAdapter
import br.com.systemglass.livros.dao.Book
import br.com.systemglass.livros.provider.CartProvider
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class CartActivity : AppCompatActivity() {

    companion object {
        private val BOOK_TO_ADD = "BOOK_TO_ADD"

        fun addItemToCartIntent(context: Context, book: Book): Intent {
            val intent = Intent(context, CartActivity::class.java)
            intent.putExtra(BOOK_TO_ADD, book)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        setTitle(R.string.cart_title)

        recyclerview_cart.layoutManager = LinearLayoutManager(this)
        loadBooksRecyclerView()

        val bookToAdd = intent.getSerializableExtra(CartActivity.BOOK_TO_ADD) as Book
        bookToAdd?.let {
            addItemToCart(it)
        }

        finishBuyButtonClick()
    }

    private fun finishBuyButtonClick() {
        finishBuyButton.setOnClickListener {
            startActivity(CartClientActivity.newIntent(this))
        }
    }

    private fun addItemToCart(book: Book) {
        CartProvider.cartItems.add(book)
    }

    private fun loadBooksRecyclerView() {
        runOnUiThread {
            recyclerview_cart.adapter = CartAdapter()
        }
    }
}
