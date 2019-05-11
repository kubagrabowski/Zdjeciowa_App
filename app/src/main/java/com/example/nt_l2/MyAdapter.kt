package com.example.nt_l2

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.util.Log
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.squareup.picasso.Callback
import java.lang.Exception


class MyAdapter
    (val image_url : ArrayList<String>,val image_name : ArrayList<String>,val dates : ArrayList<String>, val tagi : ArrayList<String>, val context: Context)
    : RecyclerView.Adapter<MyAdapter.ViewHolder>() {


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

        holder.imagename_list?.text = image_name[position]
        holder.date_list?.text = dates[position]



        Picasso.get().load(url).into(holder.image_list,object : Callback {

            override fun onSuccess() {


                val bitmap = (holder.image_list.drawable as BitmapDrawable).bitmap

                bitmap?.apply{
                    val vision = FirebaseVisionImage.fromBitmap(this)
                    val labeler = FirebaseVision.getInstance().getOnDeviceImageLabeler()
                    labeler.processImage(vision)
                        .addOnSuccessListener {it ->
                            tagi[position] = (it.map{it.text}.joinToString("#","#",""))
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


        holder.layout_list.setOnClickListener { v ->

            Toast.makeText(context, context.getString(R.string.NT_L2_adap_toastonClick) , Toast.LENGTH_LONG).show()
            val intent = Intent(this.context,FragmentActivity::class.java)
            val paka = Bundle()
            paka.putInt("INDEKS", position)
            paka.putStringArrayList("URLe", image_url)
            paka.putStringArrayList("TAGI", tagi)
            Log.d("ADAP", tagi[position])
            Log.d("ADAP", tagi.size.toString())
            paka.putStringArrayList("DATY", dates)
            paka.putString("NAZWA", image_name[position])
            intent.putExtras(paka)
            startActivity(this.context, intent,null)

        }


    }

    fun deleteItem(position:Int){
        Toast.makeText(context, "Usuniete", Toast.LENGTH_SHORT).show()
        image_name.removeAt(position)
        image_url.removeAt(position)
        dates.removeAt(position)
        tagi.removeAt(position)

        notifyItemRemoved(position)
        notifyItemRangeChanged(position, dates.size)

    }


    

}