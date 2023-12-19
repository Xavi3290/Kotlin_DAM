package com.example.activitat10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.activitat10.database.User
import com.example.activitat10.database.database
import com.example.activitat10.databinding.ActivityLoginBinding
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class Login : AppCompatActivity(), CoroutineScope {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var db: database
    private var job: Job = Job()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (intent.hasExtra("nomusu")){
            binding.etUsuari.setText(intent.getStringExtra("nomusu"))
            binding.etPassword.setText(intent.getStringExtra("contra"))
        }
        binding.BtRegistre.setOnClickListener {
            var intent = Intent(this, Registre::class.java)
            startActivity(intent)
        }
        binding.BtLogin.setOnClickListener {
            db = database.getDatabase(applicationContext)
            var pwbd : String? = null
            var tipus : String? = null
            var llistapw : ArrayList<String> = arrayListOf()
            var llistatipus : ArrayList<String> = arrayListOf()
            runBlocking {
                val corrutina = launch {
                    llistapw = db.DaoUsers().getPw(binding.etUsuari.text.toString()) as ArrayList<String>
                    llistatipus = db.DaoUsers().getTipus(binding.etUsuari.text.toString()) as ArrayList<String>
                }
                corrutina.join()
                if (llistapw.size>0) {
                    pwbd = llistapw[0]
                    tipus = llistatipus[0]
                }
            }
            if (pwbd == Sha.calculateSHA(binding.etPassword.text.toString())){
                Toast.makeText(this, "Login correcte", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity2::class.java)
                intent.putExtra("nomusu",binding.etUsuari.text.toString())
                intent.putExtra("tipus", tipus)
                startActivity(intent)
            }else {
                Toast.makeText(this, "Contrassenya i/o PW erronis", Toast.LENGTH_LONG).show()
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