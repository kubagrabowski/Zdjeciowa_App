package com.example.nt_l2

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fraglay_podobneopis.*

class FragmentPodobneOpis: Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view:View = inflater.inflate(R.layout.fraglay_podobneopis,container,false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //z paczki dane -> dodanie do txt_E


        val indeks = arguments!!.getInt("INDEKS")
        val imagename = arguments!!.getString("NAZWA")
        val tagi = arguments!!.getStringArrayList("TAGI")
        val daty = arguments!!.getStringArrayList("DATY")
        arguments!!.clear()

        activity!!.txtNazwaE.text = imagename
        activity!!.txtTagiE.text = tagi[indeks]
        activity!!.txtDataE.text = daty[indeks]

    }
}