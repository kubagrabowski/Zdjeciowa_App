package com.example.nt_l2

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fraglay_megazdjecie.*

class Fragment_zdjecie : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view:View = inflater.inflate(R.layout.fraglay_megazdjecie, container,false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val indeks = arguments!!.getInt("INDEKS")
        val imageurl = arguments!!.getStringArrayList("URLe")
        Picasso.get().load(imageurl[indeks]).into(activity!!.megazdjecie)
    }



}