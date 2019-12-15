package br.com.systemglass.livros.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.systemglass.livros.R
import br.com.systemglass.livros.extension.listen
import br.com.systemglass.livros.provider.CartProvider
import kotlinx.android.synthetic.main.cart_row.view.*

class CartAdapter: RecyclerView.Adapter<CartViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.cart_row, parent, false)

        return CartViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return CartProvider.cartItems.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val book = CartProvider.cartItems.get(position)
        holder.view.cartTitleTextView.text = book.title
        holder.view.cartResumeTextView.text = "R$ "+ book.price + " - " + book.author
    }
}

class CartViewHolder(val view: View): RecyclerView.ViewHolder(view) {
}