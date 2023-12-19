package com.example.provaretrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.provaretrofit.BD.BD
import com.example.provaretrofit.BD.Usuari
import com.example.provaretrofit.databinding.ActivityRegistreBinding
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class RegistreActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var binding: ActivityRegistreBinding
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
        binding = ActivityRegistreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bRegister.setOnClickListener {
            if(binding.etPass.text.toString() == binding.etPassConf.text.toString()){
                if(binding.etUsuari.text.isEmpty()){
                    Toast.makeText(this,"Els usuari no pot estar buit", Toast.LENGTH_LONG).show()
                } else{
                    var userBD1: List<Usuari> = arrayListOf()
                    db = BD.getDatabase(applicationContext)
                    runBlocking {
                        val corrutina = launch{
                            userBD1 = db.daoUsuari().selectUsuari(binding.etUsuari.text.toString())
                        }
                        corrutina.join()
                    }

                    if (binding.etPass.text.isEmpty()){
                        Toast.makeText(this,"El password no pot estar buit", Toast.LENGTH_LONG).show()
                    }else if(userBD1.size == 1){
                        Toast.makeText(this,"Cambia de nom d'usuari", Toast.LENGTH_LONG).show()
                    }
                    else{

                        var user = Usuari(null, binding.etUsuari.text.toString(),Sha256.calculateSHA(binding.etPass.text.toString()))
                        db = BD.getDatabase(applicationContext)
                        runBlocking {
                            val corrutina = launch {
                                db.daoUsuari().afegirUsuari(user)
                            }
                            corrutina.join()
                        }
                        val intent = Intent(this,UsuariActivity::class.java)
                        intent.putExtra("usuari",binding.etUsuari.text.toString())
                        intent.putExtra("pass",binding.etPass.text.toString())
                        startActivity(intent)
                    }
                }
            }else{
                Toast.makeText(this,"Els pass no coincideixen", Toast.LENGTH_LONG).show()
            }
        }


    }
}