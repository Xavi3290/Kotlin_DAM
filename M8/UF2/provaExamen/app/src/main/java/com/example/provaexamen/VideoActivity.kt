package com.example.provaexamen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.provaexamen.BD.listVideos
import com.example.provaexamen.BD.omplirVideo
import com.example.provaexamen.databinding.ActivityVideoBinding

class VideoActivity : AppCompatActivity() {

    private lateinit var binding:ActivityVideoBinding
    var videoView: VideoView? = null
    var mediaControls: MediaController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        omplirVideo()

        llencarVideo()

        binding.bMenu.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun llencarVideo() {
        binding.rv1.layoutManager = LinearLayoutManager(binding.rv1.context)
        binding.rv1.adapter = AdaptadorVideo(listVideos,videoView,mediaControls)
    }
}