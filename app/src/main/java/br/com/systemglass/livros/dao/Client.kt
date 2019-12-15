package br.com.systemglass.livros.dao

import java.util.ArrayList

class Client(var id: Int, var name: String, var email: String, var phone: String, var address: ClientAddress) {
}

class ClientAddress(var id: Int, var street: String, var number: Int, var neighborhood: String, var city: String, var provincy: String, var postalCode: String) {
}

class ClientJSON(var status: Int, var message: String, var `object`: ArrayList<Client>){
}