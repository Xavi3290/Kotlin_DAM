package com.example.mediarecorder

import android.Manifest
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mediarecorder.databinding.ActivityMainBinding
import java.io.IOException

class MainActivity : AppCompatActivity() {
    val record_audio_request_code = 0
    var acceptat = 0
    lateinit var binding: ActivityMainBinding

    private var output: String? = null
    private var mediaRecorder: MediaRecorder? = null
    private var state: Boolean = false
    private var recordingStopped: Boolean = false
    var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.gravar.setOnClickListener {
            startRecording()
        }
        binding.pausar.setOnClickListener {
            pauseRecording()
        }
        binding.stop.setOnClickListener {
            stopRecording()
        }

        binding.play.setOnClickListener {
            playAudio()
        }
        activaPermisos()
   }

    private fun playAudio() {
        val direc = this.getExternalFilesDir(null)!!.absolutePath + "/recording.mp3"
        val url = Uri.parse(direc)
        playContentUri(url)
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
            comenca()
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
                    comenca()

                } else {
                    Toast.makeText(this, "No s'han acceptat els permisos, per poder utilitzar la gravadora canvia-ho als ajustaments", Toast.LENGTH_LONG).show()
                    acceptat = 0
                }
                return
            }
        }
    }

    private fun comenca() {
        if (acceptat==1){
            binding.gravar.isEnabled = true
            binding.pausar.isEnabled = true
            binding.stop.isEnabled = true
            binding.play.isEnabled = true

            output = this.getExternalFilesDir(null)!!.absolutePath + "/recording.mp3"
            // S'emmagatzema a /storage/emulated/0/Android/data/com.example.mediarecorder/files/recording.mp3

            // output = "/sdcard/Download/recording.mp3"
            mediaRecorder = MediaRecorder()
            mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
            mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            mediaRecorder?.setOutputFile(output)
        }else {
            binding.gravar.isEnabled = false
            binding.pausar.isEnabled = false
            binding.stop.isEnabled = false
            binding.play.isEnabled = false
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

    @SuppressLint("RestrictedApi", "SetTextI18n")
    @TargetApi(Build.VERSION_CODES.N)
    private fun pauseRecording() {
        if(state) {
            if(!recordingStopped){
                Toast.makeText(this,"Pausat!", Toast.LENGTH_SHORT).show()
                mediaRecorder?.pause()
                recordingStopped = true
            }else{
                resumeRecording()
            }
        }
    }

    @SuppressLint("RestrictedApi", "SetTextI18n")
    @TargetApi(Build.VERSION_CODES.N)
    private fun resumeRecording() {
        Toast.makeText(this,"Continuant la gravació!", Toast.LENGTH_SHORT).show()
        mediaRecorder?.resume()
        recordingStopped = false
    }

    fun playContentUri(uri: Uri) {
        try {
            if (mediaPlayer != null){
                mediaPlayer!!.stop()
                mediaPlayer!!.release()
            }
            mediaPlayer = MediaPlayer().apply {
                setDataSource(this@MainActivity, uri)
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

}