package com.example.activitat9bona

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.activitat9bona.BD.BD
import com.example.activitat9bona.BD.Usuari
import com.example.activitat9bona.databinding.ActivityUsuariBinding
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class UsuariActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var binding: ActivityUsuariBinding
    private lateinit var db: BD
    private var job: Job = Job()


    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsuariBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val usuari = intent.getStringExtra("usuari")
        val password = intent.getStringExtra("pass")
        val tipus = intent.getStringExtra("tipus")

        binding.etUsuari.setText(usuari)
        binding.etPass.setText(password)

        var user = binding.etUsuari.text.toString()
       // var pass = binding.etPass.text.toString()

        /*runBlocking {
            val corrutina = launch{
                db = BD.getDatabase(applicationContext)
                userBD = db.daoUsuari().selectUsuari(user)
            }
            corrutina.join()
        }*/

        
        binding.bLogin.setOnClickListener{
            db = BD.getDatabase(applicationContext)
            var user = Usuari(null, binding.etUsuari.text.toString(),Sha256.calculateSHA(binding.etPass.text.toString()),null)
             //var userBD: List<Usuari>? = arrayListOf()
            runBlocking {
                val corrutina = launch{
                   // db = BD.getDatabase(applicationContext)
                    userBD = db.daoUsuari().selectUsuari(user.nom)
                }
                corrutina.join()
            }
            if(userBD!!.size == 0){
                Toast.makeText(this,"El usuari no esta registrat", Toast.LENGTH_LONG).show()
            }else{
                if(user.password == userBD!![0].password){
                    val intent = Intent(this,Main2Activity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this,"El pass no coincideix", Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.bRegister.setOnClickListener {
            val intent = Intent(this,RegistreActivity::class.java)
            startActivity(intent)
        }



    }
}