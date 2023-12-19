package com.example.provaexamen

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.provaexamen.BD.listEmisores
import com.example.provaexamen.BD.omplirEmisores
import com.example.provaexamen.databinding.ActivityEmisoraBinding

class EmisoraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmisoraBinding
    var mMediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmisoraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        omplirEmisores()

        llencarEmisora()

        binding.bMenu.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun llencarEmisora() {
        binding.rv1.layoutManager = LinearLayoutManager(binding.rv1.context)
        binding.rv1.adapter = AdaptadorEmissora(listEmisores,mMediaPlayer)
    }
}