package br.com.systemglass.livros.dao

import java.io.Serializable
import java.util.ArrayList

class Client(var id: Int, var name: String, var email: String, var phone: String, var address: ClientAddress):Serializable {
}

class ClientAddress(var id: Int, var street: String, var number: Int, var neighborhood: String, var city: String, var provincy: String, var postalCode: String): Serializable {
}

class ClientJSON(var status: Int, var message: String, var `object`: ArrayList<Client>){
}