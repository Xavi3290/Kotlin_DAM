package com.example.provaexamen

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.provaexamen.BD.Grabacio
import com.example.provaexamen.BD.mediaPlayer
import com.example.provaexamen.databinding.CardviewgrabacioBinding
import kotlinx.coroutines.CoroutineScope
import java.io.IOException
import kotlin.coroutines.CoroutineContext


class AdaptadorGrabacions (var llista: java.util.ArrayList<Grabacio>, override val coroutineContext: CoroutineContext) :
    RecyclerView.Adapter<AdaptadorGrabacions.viewholder>(), CoroutineScope {

    class viewholder (val view: View) : RecyclerView.ViewHolder(view){

        val binding = CardviewgrabacioBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val layout = LayoutInflater.from(parent.context)
        return viewholder(layout.inflate(R.layout.cardviewgrabacio, parent, false))
    }

    override fun getItemCount(): Int {
        return llista.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {

        holder.binding.tvNomCa.setText(llista[position].nom)

        holder.binding.bPlay.setOnClickListener {
            playAudio(holder.binding.bPlay.context,llista[position].direccio)
        }

        holder.binding.bStop.setOnClickListener {
            stopSound()
        }

    }

    private fun playAudio(context: Context, direc:String?) {
        //val direc = context.getExternalFilesDir(null)!!.absolutePath + "/recording.mp3"
        val url = Uri.parse(direc)
        playContentUri(url,context)
    }

    fun playContentUri(uri: Uri, context: Context) {
        try {
            if (mediaPlayer != null){
                mediaPlayer!!.stop()
                mediaPlayer!!.release()
            }
            mediaPlayer = MediaPlayer().apply {
                setDataSource(context, uri)
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build()
                )
                prepare()
                start()
            }
        } catch (exception: IOException) {
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

    fun stopSound() {
        if (mediaPlayer != null) {
            mediaPlayer!!.stop()
            mediaPlayer!!.release()
            mediaPlayer = null
        }
    }

}