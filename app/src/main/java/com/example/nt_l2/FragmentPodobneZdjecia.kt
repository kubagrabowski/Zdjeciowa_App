package com.example.nt_l2

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fraglay_podobnezdjecia.*

class FragmentPodobneZdjecia:Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view:View = inflater.inflate(R.layout.fraglay_podobnezdjecia,container,false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //val ilezdjec = arguments!!.getInt("ILEPOD")
        Log.d("FOTY", "Szuka")
        WybierzNajblizszeZdjecia()
    }

    fun WybierzNajblizszeZdjecia(){ // => do FragmentPodobneZdjecia
        val paka = arguments!!
        val image_url = paka.getStringArrayList("URLe")
        val tagi = paka.getStringArrayList("TAGI")
        val indeks = paka.getInt("INDEKS")
        val mojetagi = tagi[indeks].split("#")

        val ilezdjec = image_url.size
        Log.d("Jest zdjec", image_url.size.toString())
        var ileznalezionych =0
        val znalezioneurl:ArrayList<String> = ArrayList()

        var aktualny =0
        while(aktualny<ilezdjec && ileznalezionych <6){
            if(aktualny == indeks) {
                aktualny++
            }
            else{
                val aktualnegotagi = tagi[aktualny].split("#")
                var szukany = 1
                while(szukany<mojetagi.size) {
                    if (aktualnegotagi.contains(mojetagi[szukany])){
                        szukany = mojetagi.size
                        ileznalezionych++
                        znalezioneurl.add(image_url[aktualny])
                    }
                    szukany++
                }
                aktualny++
            }
        }


        if(ileznalezionych>0){
            DodajZdjecia(znalezioneurl)
        }

        arguments!!.clear()

    }

    fun DodajZdjecia(url: ArrayList<String>){
        Picasso.get().load(url[0]).into(activity!!.imagePodobne1)
        if(url.size > 1){
            Picasso.get().load(url[1]).into(activity!!.imagePodobne2)
            if(url.size > 2){
                Picasso.get().load(url[2]).into(activity!!.imagePodobne3)
                if(url.size > 3){
                    Picasso.get().load(url[3]).into(activity!!.imagePodobne4)
                    if(url.size > 4){
                        Picasso.get().load(url[4]).into(activity!!.imagePodobne5)
                        if(url.size > 5){
                            Picasso.get().load(url[5]).into(activity!!.imagePodobne6)
                        }
                    }
                }
            }
        }
    }
}