package br.com.systemglass.livros.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.systemglass.livros.R
import br.com.systemglass.livros.dao.Sale
import br.com.systemglass.livros.dao.SaleJSON
import br.com.systemglass.livros.dao.SaleListJSON
import kotlinx.android.synthetic.main.list_sales_row.view.*

class ListSalesAdapter(val salesListJSON: SaleListJSON) :
    RecyclerView.Adapter<ListSalesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSalesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.list_sales_row, parent, false)

        return ListSalesViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        var totalItems = 0
        salesListJSON.`object`.forEach { saleJSON: SaleJSON ->
            saleJSON.items.forEach {
                totalItems++
            }
        }
        return totalItems
    }

    override fun onBindViewHolder(holder: ListSalesViewHolder, position: Int) {
        var itemIndex = 0
        salesListJSON.`object`.forEach { saleJSON: SaleJSON ->
            saleJSON.items.forEach {
                if (itemIndex == position) {
                    prepareLine(it, holder)
                }
                itemIndex++
            }
        }
    }

    private fun prepareLine(sale: Sale, holder: ListSalesViewHolder) {
        holder.view.saleTitleTextView.text = "${sale.book.title} - Qnt.: ${sale.amount} - R$ ${sale.book.price}"
        holder.view.saleResumeTextView.text = sale.book.resume
    }

}

class ListSalesViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

}