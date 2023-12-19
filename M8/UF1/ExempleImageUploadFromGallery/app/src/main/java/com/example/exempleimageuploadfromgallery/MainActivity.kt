package com.example.exempleimageuploadfromgallery

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.exempleimageuploadfromgallery.API.CrudApi
import com.example.exempleimageuploadfromgallery.API.urlapi
import com.example.exempleimageuploadfromgallery.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var imageURI : Uri? = null
    var acceptat : Boolean? = null
    var permisos_request_code = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        activaPermisos()

        binding.obregaleria.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI).also {
                it.type = "image/*"
                val mimeTypes = arrayOf("image/jpeg","image.png")
                it.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            }
            resultLauncher.launch(gallery)
            Log.i("gallery", resultLauncher.toString())
        }

    }

    // Rep la imatge que es selecciona
    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            imageURI = result.data?.data
            binding.IVPreviewImage.setImageURI(imageURI)

            val crudApi = CrudApi()
            val resposta = crudApi.pujaArxiu(imageURI!!.path.toString())
            if (!resposta!!.error) {
                val textresp = "Imatge "+urlapi+resposta.image+" pujada amb exit"
                Toast.makeText(this, textresp, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun activaPermisos() {
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) &&
            (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED)){

        }else{
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    Toast.makeText(this, "El permís WRITE EXTERNAL STORAGE no està disponible. S'ha de canviar als ajustaments", Toast.LENGTH_LONG).show()
                    acceptat = false
                }else
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        Toast.makeText(this, "El permís READ EXTERNAL STORAGE no està disponible. S'ha de canviar als ajustaments", Toast.LENGTH_LONG).show()
                        acceptat = false
                    }else{
                        ActivityCompat.requestPermissions(
                            this,
                            arrayOf(
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE
                            ),
                            permisos_request_code
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
            permisos_request_code -> {
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                            grantResults[1] == PackageManager.PERMISSION_GRANTED )) {
                    acceptat = true

                } else {
                    Toast.makeText(this, "No s'han acceptat els permisos, per poder pujar arxius canvia-ho als ajustaments", Toast.LENGTH_LONG).show()
                    acceptat = false
                }
                return
            }
        }
    }

}