package com.example.exemple_permissos

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private val CAMERA_REQUEST_CODE = 0
    private val BLUETOOTH_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.fesfoto).setOnClickListener { checkCameraPermission() }
        findViewById<Button>(R.id.vibra).setOnClickListener { vibra() }
        findViewById<Button>(R.id.blue).setOnClickListener { activaBluetooth() }
    }

    private fun activaBluetooth() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH)
            != PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "El permís de BlueTooth no està acceptat", Toast.LENGTH_LONG).show()
                requestBlueToothPermission()
        }else{
            Toast.makeText(this, "El permís de BlueTooth ja ha estat acceptat amb anterioritat", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "El permís de la càmera encara no ha estat acceptat", Toast.LENGTH_LONG).show()
            requestCameraPermission()
        } else {
            Toast.makeText(this, "El permís de la càmera ja ha estat acceptat amb anterioritat", Toast.LENGTH_LONG).show()
        }
    }
    private fun requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            Toast.makeText(this, "El permís no està disponible. Ha de canviar-ho a ajustaments", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Es solicita el permís de la Càmera", Toast.LENGTH_LONG).show()
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),
                CAMERA_REQUEST_CODE)
        }
    }
    private fun requestBlueToothPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.BLUETOOTH)) {
            Toast.makeText(this, "El permís no està disponible. Ha de canviar-ho a ajustaments", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Es solicita el permís de BlueTooth", Toast.LENGTH_LONG).show()
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.BLUETOOTH),
                BLUETOOTH_REQUEST_CODE)
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    Toast.makeText(this, "L'usuari ha acceptat el permís de la càmera", Toast.LENGTH_LONG).show()

                } else {
                    Toast.makeText(this, "L'usuari no ha acceptat el permís de la càmera", Toast.LENGTH_LONG).show()
                }
                return
            }
            BLUETOOTH_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    Toast.makeText(this, "L'usuari ha acceptat el permís del bluetooth", Toast.LENGTH_LONG).show()

                } else {
                    Toast.makeText(this, "L'usuari no ha acceptat el permís de la bluetooth", Toast.LENGTH_LONG).show()
                }
                return
            }
            else -> {
                Toast.makeText(this, "Permís no reconegut", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun vibra() {
        val v = (getSystemService(Context.VIBRATOR_SERVICE) as Vibrator)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500,
                VibrationEffect.DEFAULT_AMPLITUDE))
        }
        else {
            v.vibrate(500)
        }
    }
}