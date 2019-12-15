package br.com.systemglass.livros.provider

import br.com.systemglass.livros.config.Constants
import br.com.systemglass.livros.dao.Book
import br.com.systemglass.livros.dao.BookJSON
import br.com.systemglass.livros.dao.Client
import br.com.systemglass.livros.dao.ClientJSON
import com.google.gson.GsonBuilder

class ClientsProvider {
    val urlListBooks = Constants.webServiceURL + "/list/clients"

    fun getClientsList(callbackResponse: (ClientJSON) -> Unit) {
        HttpProvider().sendGet(urlListBooks) {
            val gson = GsonBuilder().create()
            val booksJson = gson.fromJson(it, ClientJSON::class.java)
            callbackResponse(booksJson)
        }
    }

    fun searchEmailInList(clientsList: MutableList<Client>, email: String): Client? {
        val client = clientsList.filter { it.email.trim() == email }
        if(client.size > 0) {
            return client.get(0)
        }
        return null
    }
}