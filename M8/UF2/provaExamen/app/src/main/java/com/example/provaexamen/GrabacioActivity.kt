package com.example.provaexamen

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaRecorder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.provaexamen.BD.BD
import com.example.provaexamen.BD.Grabacio
import com.example.provaexamen.databinding.ActivityGrabacioBinding
import kotlinx.coroutines.*
import java.io.IOException
import kotlin.coroutines.CoroutineContext

class GrabacioActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var binding:ActivityGrabacioBinding
    private var output: String? = null
    private var state: Boolean = false
    private var recordingStopped: Boolean = false
    private var mediaRecorder: MediaRecorder? = null
    var acceptat = 0
    val record_audio_request_code = 0
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
        binding = ActivityGrabacioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        activaPermisos()

        binding.bPlay.setOnClickListener {

            var nom = binding.etNom.text.toString()
            comenca(nom)
        }

        binding.bStop.setOnClickListener {
            stopRecording()
        }

        binding.bLlista.setOnClickListener {
            val intent = Intent(this, LlistaGrabacioActivity::class.java)
            startActivity(intent)
        }

        binding.bMenu.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun comenca(nom:String) {
        if (acceptat==1){

            output = this.getExternalFilesDir(null)!!.absolutePath + "/${nom}.mp3"
            // S'emmagatzema a /storage/emulated/0/Android/data/com.example.mediarecorder/files/recording.mp3

            var grabat = Grabacio(null,nom,output)

            db = BD.getDatabase(this)
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
            Toast.makeText(this, "Gravant audio!", Toast.LENGTH_SHORT).show()
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
            Toast.makeText(this, "S'ha aturat la gravació!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun activaPermisos() {
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                    == PackageManager.PERMISSION_GRANTED) &&
            (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) &&
            (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED)){
            Toast.makeText(this, "Ja tens els permisos acceptats amb anterioritat", Toast.LENGTH_LONG).show()
            acceptat = 1

        }else{
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO)) {
                Toast.makeText(this, "El permís RECORD AUDIO no està disponible. Ha de canviar-ho als ajustaments", Toast.LENGTH_LONG).show()
                acceptat = 0
            } else
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    Toast.makeText(this, "El permís WRITE EXTERNAL STORAGE no està disponible. S'ha de canviar als ajustaments", Toast.LENGTH_LONG).show()
                    acceptat = 0
                }else
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        Toast.makeText(this, "El permís READ EXTERNAL STORAGE no està disponible. S'ha de canviar als ajustaments", Toast.LENGTH_LONG).show()
                        acceptat = 0
                    }else{
                        ActivityCompat.requestPermissions(
                            this,
                            arrayOf(
                                Manifest.permission.RECORD_AUDIO,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE
                            ),
                            record_audio_request_code
                        )
                    }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            record_audio_request_code -> {
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                            grantResults[1] == PackageManager.PERMISSION_GRANTED &&
                            grantResults[2] == PackageManager.PERMISSION_GRANTED)) {
                    acceptat = 1


                } else {
                    Toast.makeText(this, "No s'han acceptat els permisos, per poder utilitzar la gravadora canvia-ho als ajustaments", Toast.LENGTH_LONG).show()
                    acceptat = 0
                }
                return
            }
        }
    }
}