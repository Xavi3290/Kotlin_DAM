package com.example.activitat10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.activitat10.database.User
import com.example.activitat10.database.database
import com.example.activitat10.databinding.ActivityRegistreBinding
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class Registre : AppCompatActivity(), CoroutineScope {
    private lateinit var binding : ActivityRegistreBinding

    private lateinit var db: database
    private var job: Job = Job()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.BtRegistre2.setOnClickListener {
            if (binding.etPassword.text.toString() == binding.etPasswordConfirm.text.toString()){
                if (binding.etUsuari.text.isEmpty())
                    Toast.makeText(this, "El nom d'usuari no pot estar buit", Toast.LENGTH_LONG).show()
                else {
                    if (binding.etPassword.text.isEmpty())
                        Toast.makeText(this, "La contrassenya no pot estar buida", Toast.LENGTH_LONG).show()
                    else {
                        var tipus : String? = null
                        if (binding.comprador.isChecked)
                            tipus = "Comprador"
                        else
                            tipus = "Venedor"

                        var usu = User(binding.etUsuari.text.toString(), Sha.calculateSHA(binding.etPassword.text.toString()), tipus)
                        db = database.getDatabase(this)
                        launch {
                            db.DaoUsers().insertUser(usu)
                        }
                        var intent = Intent(this, Login::class.java)
                        intent.putExtra("nomusu", binding.etUsuari.text.toString())
                        intent.putExtra("contra", binding.etPassword.text.toString())

                        startActivity(intent)

                    }
                }
            }else {
                Toast.makeText(this, "La contrassenya i la confirmació no coincideixen", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
}