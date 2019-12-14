package br.com.systemglass.livros.provider

import br.com.systemglass.livros.config.Constants
import br.com.systemglass.livros.dao.Book
import br.com.systemglass.livros.dao.BookJSON
import com.google.gson.GsonBuilder

class BooksProvider {
    val urlListBooks = Constants.webServiceURL + "/list/books"

    fun getBookList(callbackResponse: (BookJSON) -> Unit) {
        HttpProvider().sendGet(urlListBooks) {
            val gson = GsonBuilder().create()
            val booksJson = gson.fromJson(it, BookJSON::class.java)
            callbackResponse(booksJson)
        }
    }
}