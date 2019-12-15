package br.com.systemglass.livros.provider

import br.com.systemglass.livros.dao.Book
import java.util.ArrayList

class CartProvider {

    companion object {
        var cartItems: MutableList<Book> = ArrayList<Book>()
    }
}