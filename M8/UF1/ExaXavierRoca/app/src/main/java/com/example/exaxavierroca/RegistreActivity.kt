package com.example.exaxavierroca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.exaxavierroca.API.CrudApi
import com.example.exaxavierroca.databinding.ActivityRegistreBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RegistreActivity : AppCompatActivity() {

    private lateinit var binding:ActivityRegistreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var existeix = ResponseModel1("")
        var afegit = ResponseModel1("")

        binding.bRegister.setOnClickListener {
            if(binding.etPass.text.toString() == binding.etPassConf.text.toString()){
                if(binding.etUsuari.text.isEmpty()){
                    Toast.makeText(this,"Els usuari no pot estar buit", Toast.LENGTH_LONG).show()
                } else{
                    runBlocking {
                        val crudApi = CrudApi()
                        val corrutina = launch{
                            existeix = crudApi.getUsuari(binding.etUsuari.text.toString())
                        }
                        corrutina.join()
                    }

                    if (binding.etPass.text.isEmpty()){
                        Toast.makeText(this,"El password no pot estar buit", Toast.LENGTH_LONG).show()
                    }else if(existeix.equals("existeix")){
                        Toast.makeText(this,"Cambia de nom d'usuari", Toast.LENGTH_LONG).show()
                    }
                    else{

                        var user = Usuari( binding.etUsuari.text.toString(),Sha256.calculateSHA(binding.etPass.text.toString()),"registre",
                        binding.etImg.text.toString())

                        runBlocking {
                            val crudApi = CrudApi()
                            val corrutina = launch {
                                afegit = crudApi.addUsuari(user)
                            }
                            corrutina.join()
                        }

                        if(afegit.equals("usuari afegit")){
                            val notificacio = Notificacio(binding.etImg.context)

                            notificacio.generaNoticacioText("Usuari creat",binding.etUsuari.text.toString(),binding.etUsuari.text.toString())
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