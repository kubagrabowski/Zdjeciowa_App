package com.example.nt_l2

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.GestureDetector.SimpleOnGestureListener
import kotlinx.android.synthetic.main.activity_fragment.*


class FragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)


        var fragZ = Fragment_zdjecie()
        fragZ.arguments = intent.extras
        val transakcja:FragmentTransaction = supportFragmentManager.beginTransaction()
        transakcja.add(R.id.frag_megazdjecie, fragZ)
        transakcja.commit()

        val actionbar = supportActionBar
        actionbar!!.title = intent.extras.getString("NAZWA")
        actionbar.run {
            setDisplayHomeAsUpEnabled(true)
        }

        frag_megazdjecie.setOnGenericMotionListener{ v:View, event:MotionEvent ->
            GestureDetector(GestureListener(supportFragmentManager)).onTouchEvent(event)
        }


    }

    override fun onSupportNavigateUp(): Boolean {
        intent.extras.clear()
        onBackPressed()
        return true
    }

    private class GestureListener(val v:FragmentManager) : SimpleOnGestureListener() {

        private val SWIPE_DISTANCE_THRESHOLD = 100
        private val SWIPE_VELOCITY_THRESHOLD = 100

        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
            val distanceX = e2.x - e1.x
            val distanceY = e2.y - e1.y
            if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(
                    velocityX
                ) > SWIPE_VELOCITY_THRESHOLD
            ) {
                if (distanceX > 0) {//OnSwipeRight
                    val fragPod = FragmentPodobneAll()
                    val transakcja:FragmentTransaction = v.beginTransaction()
                    transakcja.replace(R.id.frag_megazdjecie, fragPod)
                    transakcja.commit()
                }


                return true
            }
            return false
        }

    }

    fun szczegoly(view: View){

        val fragPod = FragmentPodobneAll()
        val transakcja:FragmentTransaction = supportFragmentManager.beginTransaction()
        transakcja.replace(R.id.frag_megazdjecie, fragPod)
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


