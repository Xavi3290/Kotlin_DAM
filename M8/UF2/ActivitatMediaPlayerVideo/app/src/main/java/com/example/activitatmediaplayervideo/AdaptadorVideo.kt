package com.example.activitatmediaplayervideo

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.example.activitatmediaplayervideo.databinding.CardviewBinding

class AdaptadorVideo(var llista: java.util.ArrayList<Video>, var videoView: VideoView?, var mediaControls: MediaController?) : RecyclerView.Adapter<AdaptadorVideo .viewholder>() {
    class viewholder (val view: View) : RecyclerView.ViewHolder(view){
        val binding = CardviewBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val layout = LayoutInflater.from(parent.context)
        return viewholder(layout.inflate(R.layout.cardview, parent, false))
    }

    override fun getItemCount(): Int {
        return llista.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {

        holder.binding.titolvideo.setText(llista[position].nom)

        videoView = holder.binding.videoView1
        mediaControls = MediaController(holder.view.context)

        mediaControls!!.setAnchorView(videoView)
        videoView!!.setMediaController(mediaControls)

        videoView!!.setVideoURI(
            Uri.parse(llista[position].url))

        videoView!!.requestFocus()

       /* holder.binding.videoView1.setOnPreparedListener {             //Para que haga solo el click en el solo vaya ese video
            videoView!!.start()
        }*/

      /*  holder.binding.videoView1.setOnCompletionListener {  }*/     // otro tipo, para cambiar la cardview sin que se note creo

        videoView!!.start()


    }
}