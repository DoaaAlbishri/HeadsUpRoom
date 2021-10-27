package com.example.headsUpRoom

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.telecom.Call
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddCelebrity : AppCompatActivity() {
    //initialize UI elements
    lateinit var tvCeleb: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_celebrity)
        CelebrityDatabase.getInstance(applicationContext)
        //declare UI elements
        val name = findViewById<View>(R.id.edName) as EditText
        val taboo1 = findViewById<View>(R.id.edTaboo1) as EditText
        val taboo2 = findViewById<View>(R.id.edTaboo2) as EditText
        val taboo3 = findViewById<View>(R.id.edTaboo3) as EditText
        val btAdd = findViewById<Button>(R.id.btAddCel)
        val btBack = findViewById<Button>(R.id.btBack)
        val btchange = findViewById<Button>(R.id.btchange)
        tvCeleb = findViewById<TextView>(R.id.tvCeleb)

        re()

        btBack.setOnClickListener {
            intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

        btchange.setOnClickListener {
            intent = Intent(applicationContext, EditAndDelete::class.java)
            startActivity(intent)
        }

        btAdd.setOnClickListener {
            //db
            var s1 = name.text.toString()
            var s2 = taboo1.text.toString()
            var s3 = taboo2.text.toString()
            var s4 = taboo3.text.toString()
            val s = CelebrityDetails(0,s1,s2,s3,s4)
            CoroutineScope(Dispatchers.IO).launch {
                CelebrityDatabase.getInstance(applicationContext).CelebrityDao().insertCelebrity(s)
            }
            Toast.makeText(this, "Celebrity Added", Toast.LENGTH_SHORT).show()
            re()
            name.setText("")
            taboo1.setText("")
            taboo2.setText("")
            taboo3.setText("")
        }
    }

    fun re() {
        CoroutineScope(Dispatchers.IO).launch {
            var celebrities = CelebrityDatabase.getInstance(applicationContext).CelebrityDao().getCelebrity()
            withContext(Main){
                var str = ""
                for (i in celebrities)
                    str += " id: ${i.ID} - name: ${i.name} - taboo1: ${i.taboo1} - taboo2: ${i.taboo2} - taboo3: ${i.taboo3} \n"
                tvCeleb.text = str
            }
        }
    }
}