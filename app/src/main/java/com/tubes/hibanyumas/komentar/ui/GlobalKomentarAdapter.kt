package com.tubes.hibanyumas.komentar.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tubes.hibanyumas.R
import com.tubes.hibanyumas.komentar.model.Quote

class GlobalKomentarAdapter(private val list: List<Quote>, val context: Context) :
    RecyclerView.Adapter<GlobalKomentarAdapter.BookViewHolder>() {
    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val myData = list[position]
        holder.titleQuote.text = myData.title
        holder.created.text = "Dibuat pada :"+myData.created
        holder.updated.text = "Diubah pada :"+myData.updated
        holder.quote.text = myData.description
        holder.studentInfo.text = myData.name + " | " + myData.className
        holder.itemView.setOnClickListener{
            onItemClickCallback?.onItemClicked(myData)
        }
    }
    private var onItemClickCallback: OnItemClickCallback? = null
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: Quote)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_quote_komentar, parent, false)
        return BookViewHolder(view)
    }
    override fun getItemCount(): Int = list.size
    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleQuote: TextView = itemView.findViewById(R.id.tv_title_quote)
        var created: TextView = itemView.findViewById(R.id.tv_created)
        var updated: TextView = itemView.findViewById(R.id.tv_updated)
        var quote: TextView = itemView.findViewById(R.id.tv_quote)
        var studentInfo: TextView = itemView.findViewById(R.id.tv_student_info)
    }
}