package com.example.provaretrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.provaretrofit.BD.BD
import com.example.provaretrofit.BD.Usuari
import com.example.provaretrofit.databinding.ActivityUsuariBinding
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

        binding.etUsuari.setText(usuari)
        binding.etPass.setText(password)

       // var user = binding.etUsuari.text.toString()

        binding.bLogin.setOnClickListener{
            db = BD.getDatabase(applicationContext)
            var user = Usuari(null, binding.etUsuari.text.toString(),Sha256.calculateSHA(binding.etPass.text.toString()))
            runBlocking {
                val corrutina = launch{
                    userBD = db.daoUsuari().selectUsuari(user.nom)
                }
                corrutina.join()
            }
            if(userBD!!.size == 0){
                Toast.makeText(this,"El usuari no esta registrat", Toast.LENGTH_LONG).show()
            }else{
                if(user.password == userBD!![0].password){
                    val intent = Intent(this,NavigationDrawer::class.java)
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