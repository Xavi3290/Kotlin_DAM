package com.example.activitatmediaplayerso

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.activitatmediaplayerso.databinding.CardviewBinding
import java.io.IOException

class AdaptadorEmissora (var llista: java.util.ArrayList<Emisora>, var mMediaPlayer: MediaPlayer?) :
    RecyclerView.Adapter<AdaptadorEmissora.viewholder>() {
    class viewholder(val view: View) : RecyclerView.ViewHolder(view) {
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

        holder.binding.tvNom.setText(llista[position].nom)

        holder.binding.bPlay.setOnClickListener {
            val uri = Uri.parse(llista[position].url)
            playContentUri(uri,holder.binding.bPlay.context)
        }

        holder.binding.bPause.setOnClickListener {
            pauseSound()
        }

        holder.binding.bStop.setOnClickListener {
            stopSound()
        }

    }

    // 1. Play sound with URL
    fun playContentUri(uri: Uri, context: Context) {
        mMediaPlayer = null
        try {
            if(mMediaPlayer != null){               //perque no sonin altres radios a la vegada
                mMediaPlayer!!.stop()
                mMediaPlayer!!.release()
            }

            mMediaPlayer = MediaPlayer().apply {
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
            mMediaPlayer?.release()
            mMediaPlayer = null
        }
    }

    // 2. Pause playback
    fun pauseSound() {
        if (mMediaPlayer?.isPlaying == true) mMediaPlayer?.pause()
    }

    // 3. Stops playback
    fun stopSound() {
        if (mMediaPlayer != null) {
            mMediaPlayer!!.stop()
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
    }
}
