package com.marjasoft.ejcamerax

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marjasoft.ejcamerax.bd.Media
import com.marjasoft.ejcamerax.bd.database
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LlistaMedia : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_llista_media)

        val rv1 = findViewById<RecyclerView>(R.id.rv1)

        var llista = ArrayList<Media>()
        runBlocking {
            val corrutina = launch {
                llista = database.getDatabase(this@LlistaMedia).DaoMedia().selectAll() as ArrayList<Media>
            }
            corrutina.join()
        }
        rv1.adapter = Adapter(llista)
        rv1.layoutManager = LinearLayoutManager(this)
    }
}