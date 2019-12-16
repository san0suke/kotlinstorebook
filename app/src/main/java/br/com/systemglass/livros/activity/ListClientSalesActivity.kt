package br.com.systemglass.livros.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.systemglass.livros.R
import br.com.systemglass.livros.adapter.ListSalesAdapter
import br.com.systemglass.livros.provider.SalesProvider
import kotlinx.android.synthetic.main.activity_list_client_sales.*

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

        recyclerview_sales.layoutManager = LinearLayoutManager(this)

        val clientID = intent.getIntExtra(CLIENT_ID, 0)
        getSalesList(clientID)
    }

    private fun getSalesList(clientID: Int) {
        SalesProvider().getListSales(clientID) {
            runOnUiThread {
                recyclerview_sales.adapter = ListSalesAdapter(it)
            }
        }
    }
}
