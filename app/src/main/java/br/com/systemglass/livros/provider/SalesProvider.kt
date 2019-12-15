package br.com.systemglass.livros.provider

import br.com.systemglass.livros.config.Constants
import br.com.systemglass.livros.dao.SaleJSON
import br.com.systemglass.livros.dao.SaleListJSON
import com.google.gson.GsonBuilder

class SalesProvider {
    val urlCreateSale = Constants.webServiceURL + "/sale/create"

    fun getUrlListSale(clientID: Int): String {
        return Constants.webServiceURL + "/list/sales/$clientID"
    }

    fun sendSale(saleJSON: SaleJSON, completion: () -> Unit) {
        val gson = GsonBuilder().create()
        val saleJsonString = gson.toJson(saleJSON)
        HttpProvider().sendPost(urlCreateSale, saleJsonString) {
            completion()
        }
    }

    fun getListSales(clientID: Int, callbackResponse: (SaleListJSON) -> Unit) {
        HttpProvider().sendGet(getUrlListSale(clientID)) {
            val gson = GsonBuilder().create()
            val saleListJSON = gson.fromJson(it, SaleListJSON::class.java)
            callbackResponse(saleListJSON)
        }
    }
}