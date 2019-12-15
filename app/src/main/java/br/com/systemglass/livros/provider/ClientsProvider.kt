package br.com.systemglass.livros.provider

import br.com.systemglass.livros.config.Constants
import br.com.systemglass.livros.dao.Book
import br.com.systemglass.livros.dao.BookJSON
import br.com.systemglass.livros.dao.ClientJSON
import com.google.gson.GsonBuilder

class ClientsProvider {
    val urlListBooks = Constants.webServiceURL + "/list/clients"

    fun getBookList(callbackResponse: (ClientJSON) -> Unit) {
        HttpProvider().sendGet(urlListBooks) {
            val gson = GsonBuilder().create()
            val booksJson = gson.fromJson(it, ClientJSON::class.java)
            callbackResponse(booksJson)
        }
    }
}