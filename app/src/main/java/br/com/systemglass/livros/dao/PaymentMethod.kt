package br.com.systemglass.livros.dao

import java.io.Serializable

class PaymentMethod(var id: Int, var description: String, var installments: Int): Serializable {
}