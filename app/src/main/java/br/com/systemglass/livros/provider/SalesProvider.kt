package br.com.systemglass.livros.provider

import br.com.systemglass.livros.config.Constants
import br.com.systemglass.livros.dao.SaleJSON
import com.google.gson.GsonBuilder

class SalesProvider {
    val urlCreateSale = Constants.webServiceURL + "/sale/create"

    fun sendSale(saleJSON: SaleJSON) {
        val gson = GsonBuilder().create()
        val saleJsonString = gson.toJson(saleJSON)
        HttpProvider().sendPost(urlCreateSale, saleJsonString) {
        }
    }
}