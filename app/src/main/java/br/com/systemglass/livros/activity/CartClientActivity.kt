package br.com.systemglass.livros.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.systemglass.livros.R
import br.com.systemglass.livros.dao.Client
import br.com.systemglass.livros.provider.ClientsProvider
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_cart_client.*
import java.util.ArrayList

class CartClientActivity : AppCompatActivity() {

    var selectedClient: Client? = null
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
        clientFinishBuyButtonClick()
    }

    private fun clientFinishBuyButtonClick() {
        clientFinishBuyButton.setOnClickListener {
            if (checkClient()) {
                selectedClient?.let {
                    try {
                        it.name = clientNameEditText.text.toString()
                        it.phone = clientPhoneEditText.text.toString()
                        it.address.street = clientAddressEditText.text.toString()
                        it.address.number = clientNumberEditText.text.toString().toInt()
                        it.address.neighborhood = clientNeighborhoodEditText.text.toString()
                        it.address.city = clientCityEditText.text.toString()
                        it.address.postalCode = clientPostalCodeEditText.text.toString()
                        it.address.provincy = clientProvincySpinner.selectedItem.toString()

                        val clientComplement = clientComplementEditText.text.toString()

                        if (clientComplement != null && clientComplement.trim() != "") {
                            it.address.street += ", $clientComplement"
                        }
                    } catch (e: Exception) {
                        Toast.makeText(this, R.string.fields_incorrect, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }


    private fun prepareProviciesSpinner() {
        val provincies = resources.getStringArray(R.array.provicies)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, provincies)
        clientProvincySpinner.adapter = adapter
    }

    private fun configureOnChangeEmail() {
        clientEmailEditText.setOnFocusChangeListener { view, focusIn ->
            val email = clientEmailEditText.text.toString()
            if (!focusIn && email != "") {
                val client = clientsProvider.searchEmailInList(clientsList, email)
                selectedClient = client
                client?.let {
                    clientInfoToFields(it)
                }
                checkClient()
            }
        }
    }

    private fun checkClient(): Boolean {
        val clientFound = selectedClient != null
        if (!clientFound) {
            Toast.makeText(this, R.string.client_not_found, Toast.LENGTH_LONG).show()
        }
        return clientFound
    }

    private fun clientInfoToFields(client: Client) {
        clientNameEditText.setText(client.name)
        clientPhoneEditText.setText(client.phone)
        clientAddressEditText.setText(client.address.street)
        clientNumberEditText.setText(client.address.number.toString())
        clientNeighborhoodEditText.setText(client.address.neighborhood)
        clientCityEditText.setText(client.address.city)
        clientPostalCodeEditText.setText(client.address.postalCode)

        val provincyIndex =
            resources.getStringArray(R.array.provicies).indexOf(client.address.provincy)
        clientProvincySpinner.setSelection(provincyIndex)
    }
}