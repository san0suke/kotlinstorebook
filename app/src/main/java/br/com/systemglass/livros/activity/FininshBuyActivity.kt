package br.com.systemglass.livros.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.systemglass.livros.R
import br.com.systemglass.livros.dao.Client
import br.com.systemglass.livros.dao.PaymentMethod
import br.com.systemglass.livros.dao.Sale
import br.com.systemglass.livros.dao.SaleJSON
import br.com.systemglass.livros.provider.CartProvider
import br.com.systemglass.livros.provider.SalesProvider
import kotlinx.android.synthetic.main.activity_fininsh_buy.*

class FininshBuyActivity : AppCompatActivity() {

    companion object {
        private val CLIENT_BUYING = "CLIENT_BUYING"

        fun finishBuyingIntent(context: Context, client: Client): Intent {
            val intent = Intent(context, FininshBuyActivity::class.java)
            intent.putExtra(CLIENT_BUYING, client)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fininsh_buy)
        setTitle(R.string.finish_buy_title)

        sendBuyingData()
    }

    private fun sendBuyingData() {
        val client = intent.getSerializableExtra(CLIENT_BUYING) as Client
        val cartItems = CartProvider.cartItems
        val paymentMethod = PaymentMethod(id = 1, description = "Boleto", installments = 1)

        var sales = mutableListOf<Sale>()
        cartItems.forEachIndexed { index, book ->
            var sale = Sale(index, book, 1, book.price, null)
            sales.add(sale)
        }

        var saleJSON = SaleJSON(items = sales, payment = paymentMethod, confirm = false, client = client)

        SalesProvider().sendSale(saleJSON) {
            setShowSalesButtonClick(client)
        }
    }

    private fun setShowSalesButtonClick(client: Client) {
        afterBuyShowSalesButton.setOnClickListener {
            startActivity(ListClientSalesActivity.newIntent(this, client.id))
        }
    }
}
