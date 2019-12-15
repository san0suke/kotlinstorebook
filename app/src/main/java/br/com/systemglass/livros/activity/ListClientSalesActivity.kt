package br.com.systemglass.livros.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.systemglass.livros.R
import br.com.systemglass.livros.dao.Client
import br.com.systemglass.livros.provider.SalesProvider

class ListClientSalesActivity : AppCompatActivity() {

    companion object {
        private val CLIENT_ID = "CLIENT_ID"

        fun newIntent(context: Context, clientID: Int): Intent {
            val intent = Intent(context, ListClientSalesActivity::class.java)
            intent.putExtra(CLIENT_ID, clientID)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_client_sales)
        setTitle(R.string.buyed_items)

        val clientID = intent.getIntExtra(CLIENT_ID, 0)
        getSalesList(clientID)
    }

    private fun getSalesList(clientID: Int) {
        SalesProvider().getListSales(clientID) {
            print(it)
        }
    }
}
