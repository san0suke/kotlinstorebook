package br.com.systemglass.livros.dao

import java.io.Serializable
import java.util.ArrayList

class Sale(var id: Int, var book: Book, var amount: Int, var total: Double, var venda: Int? = null): Serializable {
}
class SaleJSON(var createDate: String? = null, var finalConclusion: String? = null,
               var items: List<Sale>, var client: Client, var total: Double? = null,
               var payment: PaymentMethod, var confirm: Boolean): Serializable {
}