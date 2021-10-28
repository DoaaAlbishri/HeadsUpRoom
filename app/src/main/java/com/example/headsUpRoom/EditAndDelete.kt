package com.example.headsUpRoom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditAndDelete : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_and_delete)
        CelebrityDatabase.getInstance(applicationContext)
        //declare UI elements
        val id = findViewById<View>(R.id.edID) as EditText
        val Nname = findViewById<View>(R.id.edNameNew) as EditText
        val Ntaboo1 = findViewById<View>(R.id.edTaboo1New) as EditText
        val Ntaboo2 = findViewById<View>(R.id.edTaboo2New) as EditText
        val Ntaboo3 = findViewById<View>(R.id.edTaboo3New) as EditText

        val btedit = findViewById<Button>(R.id.bteditCel)

        val DelID = findViewById<View>(R.id.edID2) as EditText
        val btdel = findViewById<Button>(R.id.btDel)
        val btBack = findViewById<Button>(R.id.btBack1)

        btedit.setOnClickListener {
            if (id.text.isEmpty())
                Toast.makeText(this, "Enter the id please", Toast.LENGTH_SHORT).show()
            else {
                val ID = id.text.toString().toInt()
                if(Nname.text.isEmpty()||Ntaboo1.text.isEmpty()||Ntaboo2.text.isEmpty()||Ntaboo3.text.isEmpty())
                    Toast.makeText(this, "Fill all fields please", Toast.LENGTH_SHORT).show()
                else {
                    val s = CelebrityDetails(ID, Nname.text.toString(), Ntaboo1.text.toString(), Ntaboo2.text.toString(), Ntaboo3.text.toString())
                    CoroutineScope(Dispatchers.IO).launch {
                        CelebrityDatabase.getInstance(applicationContext).CelebrityDao().updateCelebrity(s)
                    }
                    Toast.makeText(applicationContext, "data updated successfully! ", Toast.LENGTH_SHORT)
                            .show()
                    println("updated item")
                    id.setText("")
                    Nname.setText("")
                    Ntaboo1.setText("")
                    Ntaboo2.setText("")
                    Ntaboo3.setText("")
                }
            }
        }

        btdel.setOnClickListener {
            if (DelID.text.isEmpty())
                Toast.makeText(this, "Enter the id please", Toast.LENGTH_SHORT).show()
            else {
                val del = CelebrityDetails(DelID.text.toString().toInt(), "", "", "", "")
                CoroutineScope(Dispatchers.IO).launch {
                    CelebrityDatabase.getInstance(applicationContext).CelebrityDao().deleteCelebrity(del)
                }
                Toast.makeText(applicationContext, "data deleted successfully! ", Toast.LENGTH_SHORT)
                        .show()
                DelID.setText("")
            }
        }
        btBack.setOnClickListener {
            intent = Intent(applicationContext, AddCelebrity::class.java)
            startActivity(intent)
        }
    }
}