package br.com.systemglass.livros.dao

import java.io.Serializable
import java.util.ArrayList

class Book(var id: Int, var title: String, var resume: String, var author: String, var year: Int, var publisher: String, var price: Double, var stock: Int):Serializable {
}

class BookJSON(var status: Int, var message: String, var `object`: ArrayList<Book>) {
}