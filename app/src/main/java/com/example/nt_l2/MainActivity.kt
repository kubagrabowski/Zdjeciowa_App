package com.example.nt_l2

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.helper.ItemTouchHelper
import android.support.v7.widget.RecyclerView





class MainActivity : AppCompatActivity() {

    //val elem_kompres:ArrayList<String> = ArrayList()

    val image_list:ArrayList<String> = ArrayList()
    val imagename_list:ArrayList<String> = ArrayList()
    val date_list:ArrayList<String> = ArrayList()
    val tags_list:ArrayList<String> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        my_rec_view.layoutManager = LinearLayoutManager(this)
       val adap = MyAdapter(image_list,imagename_list,date_list,tags_list,this)

        my_rec_view.adapter = adap

        val itemTouchHelper = ItemTouchHelper(SwipeToDeleteCallback(adap))
        itemTouchHelper.attachToRecyclerView(my_rec_view)

    }

    class SwipeToDeleteCallback(val myAdapter: MyAdapter) : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT ) {

        override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            myAdapter.deleteItem(position)
        }

    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){

            R.id.plus ->{
                val intent:Intent = Intent(this,AddActivity::class.java)


                Toast.makeText(this,"PLUSSS",Toast.LENGTH_SHORT).show()

                startActivityForResult(intent,1)
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode==1 && resultCode == Activity.RESULT_OK){
            val obiekt = data!!.getStringExtra("NOWY_OB")
            //elem_kompres.add(obiekt)
            rozdziel(obiekt)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun rozdziel(obiekt:String){
        val list = obiekt.split("?#@?")
        image_list.add(list[0])
        imagename_list.add(list[1])
        date_list.add(list[2])
        tags_list.add(list[3])

        val index = image_list.lastIndex

        my_rec_view.adapter!!.notifyItemInserted(index);

    }




}
