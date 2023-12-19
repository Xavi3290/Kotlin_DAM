package com.example.provaexamen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.provaexamen.BD.BD
import com.example.provaexamen.BD.Media
import com.example.provaexamen.databinding.ActivityLlistaMediaBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LlistaMediaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLlistaMediaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLlistaMediaBinding.inflate(layoutInflater)
        setContentView(binding.root)



        var llista = ArrayList<Media>()
        runBlocking {
            val corrutina = launch {
                llista = BD.getDatabase(this@LlistaMediaActivity).daoMedia().selectAll() as ArrayList<Media>
            }
            corrutina.join()
        }
        binding.rv1.adapter = AdaptadorMedia(llista)
        binding.rv1.layoutManager = LinearLayoutManager(this)

        binding.bMenu.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


}