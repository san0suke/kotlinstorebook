package br.com.systemglass.livros.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import br.com.systemglass.livros.R
import br.com.systemglass.livros.dao.Client
import br.com.systemglass.livros.provider.ClientsProvider
import kotlinx.android.synthetic.main.activity_cart_client.*
import java.util.ArrayList

class CartClientActivity : AppCompatActivity() {

    var clientsList: MutableList<Client> = ArrayList<Client>()
    val clientsProvider = ClientsProvider()

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, CartClientActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_client)

        setTitle(R.string.cart_title)

        prepareProviciesSpinner()
        clientsProvider.getClientsList {
            clientsList = it.`object`
        }

        configureOnChangeEmail()
    }

    private fun prepareProviciesSpinner() {
        val provincies = resources.getStringArray(R.array.provicies)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, provincies)
        clientProvincySpinner.adapter = adapter
    }

    private fun configureOnChangeEmail() {
        clientEmailEditText.setOnFocusChangeListener { view, focusIn ->
            val email = clientEmailEditText.text.toString()
            if(!focusIn && email != "") {
                val client = clientsProvider.searchEmailInList(clientsList, email)
                client?.let {
                    clientInfoToFields(it)
                }
            }
        }
    }

    private fun clientInfoToFields(client: Client) {
        clientNameEditText.setText(client.name)
        clientPhoneEditText.setText(client.phone)
        clientAddressEditText.setText(client.address.street)
        clientNumberEditText.setText(client.address.number.toString())
        clientNeighborhoodEditText.setText(client.address.neighborhood)
        clientCityEditText.setText(client.address.city)
        clientPostalCodeEditText.setText(client.address.postalCode.toString())

        val provincyIndex = resources.getStringArray(R.array.provicies).indexOf(client.address.provincy)
        clientProvincySpinner.setSelection(provincyIndex)

        clientComplementEditText.requestFocus()
    }
}
