package com.tubes.hibanyumas.kuliner.adapter5

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tubes.hibanyumas.R
import com.tubes.hibanyumas.hotel.HotelDetailActivity
import com.tubes.hibanyumas.kuliner.MyData
import com.tubes.hibanyumas.kuliner.KulinerDetailActivity
import kotlinx.android.synthetic.main.item_list_recycle_view.view.*


class ListMyDataAdapter5(private val listMyData: ArrayList<MyData>, val context: Context) :
    RecyclerView.Adapter<ListMyDataAdapter5.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_recycle_view, parent, false)
        return ListViewHolder(view)
    }
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listMyData[position])
    }
    override fun getItemCount(): Int = listMyData.size
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(myData: MyData) {
            with(itemView){
                Glide.with(itemView.context)
                    .load(myData.photo)
                    .apply(RequestOptions().override(55, 55))
                    .into(img_item_photo)
                tv_item_name.text = myData.name
                tv_item_description.text = myData.description
            }
            Log.d("lihat",myData.toString())
            itemView.setOnClickListener {
                val moveWithObjectIntent = Intent(context, KulinerDetailActivity::class.java)
                moveWithObjectIntent.putExtra(KulinerDetailActivity.EXTRA_MYDATA, myData)
                context.startActivity(moveWithObjectIntent)
            }
        }
    }
}