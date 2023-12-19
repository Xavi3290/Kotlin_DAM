package com.example.activitatmediarecorderbona

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.activitatmediarecorderbona.BD.BD
import com.example.activitatmediarecorderbona.BD.Grabacio
import com.example.activitatmediarecorderbona.BD.acceptat
import com.example.activitatmediarecorderbona.databinding.FragmentNavGrabarBinding
import kotlinx.coroutines.*
import java.io.IOException
import kotlin.coroutines.CoroutineContext


class nav_grabar : Fragment(), CoroutineScope {

    private lateinit var binding:FragmentNavGrabarBinding
    private var output: String? = null
    private var state: Boolean = false
    private var recordingStopped: Boolean = false
    private var mediaRecorder: MediaRecorder? = null
    private var job: Job = Job()
    private lateinit var db: BD


    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavGrabarBinding.inflate(inflater,container, false)


        binding.bPlay.setOnClickListener {

            var nom = binding.etNom.text.toString()
            comenca(nom)
        }

        binding.bStop.setOnClickListener {
            stopRecording()
        }

        return binding.root
    }

    private fun comenca(nom:String) {
        if (acceptat==1){

            output = binding.tvTitol.context.getExternalFilesDir(null)!!.absolutePath + "/${nom}.mp3"
            // S'emmagatzema a /storage/emulated/0/Android/data/com.example.mediarecorder/files/recording.mp3

            var grabat = Grabacio(nom,output)

            db = BD.getDatabase(binding.tvTitol.context)
            runBlocking {
                val corrutina = launch{
                    db.daoGrabacions().afegirGrabacio(grabat)
                }
                corrutina.join()
            }

            // output = "/sdcard/Download/recording.mp3"
            mediaRecorder = MediaRecorder()
            mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
            mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            mediaRecorder?.setOutputFile(output)

            startRecording()
        }
    }


    private fun startRecording() {
        try {
            mediaRecorder?.prepare()
            mediaRecorder?.start()
            state = true
            Toast.makeText(binding.tvTitol.context, "Gravant audio!", Toast.LENGTH_SHORT).show()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun stopRecording(){
        if(state){
            mediaRecorder?.stop()
            mediaRecorder?.release()
            state = false
        }else{
            Toast.makeText(binding.tvTitol.context, "S'ha aturat la gravació!", Toast.LENGTH_SHORT).show()
        }
    }




}