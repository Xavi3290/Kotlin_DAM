package com.example.exaxavierroca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.exaxavierroca.API.CrudApi
import com.example.exaxavierroca.databinding.ActivityUsuariBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class UsuariActivity : AppCompatActivity() {

    private lateinit var binding:ActivityUsuariBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsuariBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var userApi = ResponseModel2("","")

        val usuari = intent.getStringExtra("usuari")
        val password = intent.getStringExtra("pass")

        binding.etUsuari.setText(usuari)
        binding.etPass.setText(password)


        binding.bLogin.setOnClickListener{

            var user = Usuari2(binding.etUsuari.text.toString(),Sha256.calculateSHA(binding.etPass.text.toString()),"login")

            runBlocking {
                val crudApi = CrudApi()
                val corrutina = launch{
                    userApi = crudApi.addLogUsuari(user)
                }
                corrutina.join()
            }
            if(userApi.resposta.equals("Login erroni")){
                Toast.makeText(this,"El usuari no esta registrat", Toast.LENGTH_LONG).show()
            }else{
                val intent = Intent(this,MainActivity::class.java)
                intent.putExtra("img",userApi.imatge)
                intent.putExtra("nom",binding.etUsuari.text.toString())
                startActivity(intent)
            }

          /*  val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("img","https://1000marcas.net/wp-content/uploads/2020/11/Media-Markt-Logo.png")
            intent.putExtra("nom","xavi")
            startActivity(intent)
*/
        }

        binding.bRegister.setOnClickListener {
            val intent = Intent(this,RegistreActivity::class.java)
            startActivity(intent)
        }




    }
}