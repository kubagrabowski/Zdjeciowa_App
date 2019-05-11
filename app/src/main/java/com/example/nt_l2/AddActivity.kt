package com.example.nt_l2

import android.app.Activity
import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.hardware.camera2.params.BlackLevelPattern
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add.*
import java.util.*

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
    }


    fun wprowadzDate(view:View){
        val cal:Calendar = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        val DateSetListener:DatePickerDialog.OnDateSetListener = DatePickerDialog.OnDateSetListener{
                datePicker: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->
            val data:String = dayOfMonth.toString() + "." + (month+1).toString() + "." + year.toString()
            add_date_edit.text = data

        }

        val dialog:DatePickerDialog = DatePickerDialog(
            this,
            android.R.style.Theme_Holo_Light_Dialog,
            DateSetListener, year,month,day
            )
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        add_date_edit.setTextColor(resources.getColor(R.color.vulcan))
    }



    fun dodaj(view: View){
        if(add_url_edit.text.isNotBlank() && add_name_edit.text.isNotBlank() && add_date_edit.text != "Twoja data"){
            var obiekt:String = add_url_edit.text.toString()
            obiekt = obiekt + getString(R.string.NT_L2_oddzielenie) + add_name_edit.text.toString()
            obiekt = obiekt + getString(R.string.NT_L2_oddzielenie) + add_date_edit.text.toString()


            val intent = this.intent
            intent.putExtra("NOWY_OB",obiekt)
            setResult(Activity.RESULT_OK,intent)

            finish()
        }
        else{
            Toast.makeText(this,getString(R.string.NT_L2_add_errorBrakDanych),Toast.LENGTH_LONG).show()
        }




    }
}