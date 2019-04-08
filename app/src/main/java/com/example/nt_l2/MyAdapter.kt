package com.example.nt_l2

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.*
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*


class MyAdapter
    (val image_url : ArrayList<String>,val image_name : ArrayList<String>,val dates : ArrayList<String>,val tags : ArrayList<String>, val context: Context)
    : RecyclerView.Adapter<MyAdapter.ViewHolder>()  {

    


    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        val image_list = view.image_listitem
        val imagename_list = view.imagename_listitem
        val date_list = view.date_listitem
        val tags_list = view.tags_listitem
        val layout_list = view.layout_listitem


    }




    override fun getItemCount(): Int {
        return image_url.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder {
        return MyAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val url: String = image_url[position]

        Picasso.get().load(url).into(holder.image_list)


        holder.imagename_list?.text = image_name[position]
        holder.date_list?.text = dates[position]
        holder.tags_list?.text = tags[position]

        // holder.layout_list.setOnTouchListener(OnSwipeTouchListener(mainAktivity.this))

        /*holder.layout_list.setOnClickListener { v ->
            Toast.makeText(context, "Usuniete", Toast.LENGTH_SHORT).show()
            image_name.removeAt(position)
            image_url.removeAt(position)
            dates.removeAt(position)
            tags.removeAt(position)
            notifyItemRemoved(position)
        }*/


    }

    fun deleteItem(position:Int){
        Toast.makeText(context, "Usuniete", Toast.LENGTH_SHORT).show()
        image_name.removeAt(position)
        image_url.removeAt(position)
        dates.removeAt(position)
        tags.removeAt(position)
        notifyItemRemoved(position)
    }


    

}