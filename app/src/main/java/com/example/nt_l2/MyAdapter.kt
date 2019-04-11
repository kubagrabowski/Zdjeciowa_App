package com.example.nt_l2

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*
import android.graphics.drawable.BitmapDrawable
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import android.graphics.drawable.Drawable
import android.graphics.Bitmap
import com.squareup.picasso.Callback
import java.lang.Exception

//import sun.security.krb5.internal.KDCOptions.with



class MyAdapter
    (val image_url : ArrayList<String>,val image_name : ArrayList<String>,val dates : ArrayList<String>,val tags : ArrayList<String>, val context: Context)
    : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    


    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        val image_list = view.image_listitem
        val imagename_list = view.imagename_listitem
        val date_list = view.date_listitem
        val tags_list = view.tags_listitem
        val layout_list = view.layout_listitem

        //val bitmap = (image_list.drawable.current as BitmapDrawable).bitmap

    }




    override fun getItemCount(): Int {
        return image_url.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder {
        return MyAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val url: String = image_url[position]

        //Picasso.get().load(url).into(holder.image_list)



        holder.imagename_list?.text = image_name[position]
        holder.date_list?.text = dates[position]



        Picasso.get().load(url).into(holder.image_list,object : Callback {


            override fun onSuccess() {

                //holder.imagename_list?.text = "Poszło"
                val bitmap = (holder.image_list.drawable as BitmapDrawable).bitmap

                bitmap?.apply{
                    val vision = FirebaseVisionImage.fromBitmap(this)
                    val labeler = FirebaseVision.getInstance().getOnDeviceImageLabeler()
                    labeler.processImage(vision)
                        .addOnSuccessListener {it ->
                            holder.tags_list?.text = (it.map{ it.text }.joinToString("#","#","",3))
                        }
                        .addOnFailureListener{it->
                            holder.tags_list?.text =""
                        }
                }

            }

            override fun onError(e: Exception?) {
                holder.tags_list?.text =""
            }
        })


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