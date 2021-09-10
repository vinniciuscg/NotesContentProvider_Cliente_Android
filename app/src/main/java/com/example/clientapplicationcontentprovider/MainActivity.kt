package com.example.clientapplicationcontentprovider

import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var recyclerNotes: RecyclerView
    lateinit var buttonSyncNotes: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerNotes = findViewById(R.id.recycler_client)
        buttonSyncNotes = findViewById(R.id.fab_sync)

        getContentProvider()

        buttonSyncNotes.setOnClickListener { getContentProvider() }
    }

    private fun getContentProvider(){

        try{
            val url = "content://com.example.applicationcontentprovider.provider/notes"
            val data = Uri.parse(url)
            val cursor: Cursor? = contentResolver.query(data, null, null, null, "title")

            recyclerNotes.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = ClientAdapter(cursor as Cursor)
            }
        }catch (ex: Exception){
            ex.printStackTrace()
        }
    }
}