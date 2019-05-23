package com.example.nt_l2

import android.annotation.TargetApi
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent.*
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_fragment.*


class FragmentActivity : AppCompatActivity() {

    //var fragPodAll: FragmentPodobneAll? = null
    //var fragZdjecie: Fragment_zdjecie? = null

    var xstart =  0F
    var ystart = 0F
    var xend = 0F
    var yend = 0F
    var fragPodAll: Fragment? = null
    var fragZdjecie: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)


        fragPodAll = FragmentPodobneAll()
        fragZdjecie = Fragment_zdjecie()
        fragZdjecie!!.arguments = intent.extras
        val transakcja:FragmentTransaction = supportFragmentManager.beginTransaction()
        transakcja.add(R.id.frag_megazdjecie, fragZdjecie!!)
        transakcja.commit()

        val actionbar = supportActionBar
        actionbar!!.title = intent.extras.getString("NAZWA")
        actionbar.run {
            setDisplayHomeAsUpEnabled(true)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        intent.extras.clear()
        onBackPressed()
        return true
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        when(event!!.actionMasked){
            ACTION_DOWN -> {
                Log.d("Act", "down")
                xstart = event.x
                ystart = event.y
                return true
            }
            ACTION_UP -> {
                Log.d("Act", "Up")
                xend = event.x
                yend = event.y

                if(xstart<xend){//left
                    Log.d("Act", "Right")
                    onRightSwipe()

                }
                else{//right
                    Log.d("Act", "Left")
                    onLeftSwipe()

                }
                return false
            }
            ACTION_CANCEL ->{
                return false
            }
            else ->{
                return super.onTouchEvent(event)
            }
        }

    }

    fun onLeftSwipe(){
        val transakcja:FragmentTransaction = supportFragmentManager.beginTransaction()
        transakcja.replace(R.id.frag_megazdjecie, fragPodAll!!)
        transakcja.commit()
    }

    fun onRightSwipe(){
        val transakcja:FragmentTransaction = supportFragmentManager.beginTransaction()
        transakcja.replace(R.id.frag_megazdjecie, fragZdjecie!!)
        transakcja.commit()
    }

    fun UstawPodobne(){

        val FragOpis = FragmentPodobneOpis()
        val FragZdjecia = FragmentPodobneZdjecia()

        FragOpis.arguments = intent.extras
        FragZdjecia.arguments = intent.extras
        val transakcja: FragmentTransaction = supportFragmentManager.beginTransaction()
        transakcja.add(R.id.frag_zdjeciapod, FragZdjecia )
        transakcja.add(R.id.frag_opisu, FragOpis )
        transakcja.commit()
    }

}


