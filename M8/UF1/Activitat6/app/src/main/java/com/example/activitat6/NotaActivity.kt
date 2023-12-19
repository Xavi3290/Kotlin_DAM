package com.example.activitat6

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.activitat6.databinding.ActivityNotaBinding

class NotaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNotaBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val nom = intent.getStringExtra("nom").toString()
        binding.tvNom2.setText(nom)

        //var preguntes = intent.getSerializableExtra("preguntes") as ArrayList<Pregunta>
        var nota1 = 0

        binding.tvPre1.setText(preguntes[0].pregunta)
        binding.tvCor1.setText(preguntes[0].correcta.toString())
        binding.tvRes1.setText(preguntes[0].respostaUsuari.toString())
        if(preguntes[0].correcta == preguntes[0].respostaUsuari){
            nota1++
            binding.tvPunt1.setText("1")
        }else{
            binding.tvPunt1.setText("0")
            binding.tvRes1.setTextColor(Color.parseColor("#FF0000"))
        }

        binding.tvPre2.setText(preguntes[1].pregunta)
        binding.tvCor2.setText(preguntes[1].correcta.toString())
        binding.tvRes2.setText(preguntes[1].respostaUsuari.toString())
        if(preguntes[1].correcta == preguntes[1].respostaUsuari){
            nota1++
            binding.tvPunt2.setText("1")
        }else{
            binding.tvPunt2.setText("0")
        }

        binding.tvPre3.setText(preguntes[2].pregunta)
        binding.tvCor3.setText(preguntes[2].correcta.toString())
        binding.tvRes3.setText(preguntes[2].respostaUsuari.toString())
        if(preguntes[2].correcta == preguntes[2].respostaUsuari){
            nota1++
            binding.tvPunt3.setText("1")
        }else{
            binding.tvPunt3.setText("0")
            binding.tvRes3.setTextColor(Color.parseColor("#FF0000"))
        }

        binding.tvPre4.setText(preguntes[3].pregunta)
        binding.tvCor4.setText(preguntes[3].correcta.toString())
        binding.tvRes4.setText(preguntes[3].respostaUsuari.toString())
        if(preguntes[3].correcta == preguntes[3].respostaUsuari){
            nota1++
            binding.tvPunt4.setText("1")
        }else{
            binding.tvPunt4.setText("0")
            binding.tvRes4.setTextColor(Color.parseColor("#FF0000"))
        }

        binding.tvPre5.setText(preguntes[4].pregunta)
        binding.tvCor5.setText(preguntes[4].correcta.toString())
        binding.tvRes5.setText(preguntes[4].respostaUsuari.toString())
        if(preguntes[4].correcta == preguntes[4].respostaUsuari){
            nota1++
            binding.tvPunt5.setText("1")
        }else{
            binding.tvPunt5.setText("0")
            binding.tvRes5.setTextColor(Color.parseColor("#FF0000"))
        }

        var nota = (nota1.toString())
        binding.tvNota1.setText(nota)



    }
}