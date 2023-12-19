package com.example.activitat6

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.activitat6.databinding.ActivityResumBinding

class ResumActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResumBinding
    var usuari: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResumBinding.inflate(layoutInflater)

        usuari = intent.getStringExtra("nomUsuari")
        omplirResum()

        setContentView(binding.root)
    }
    fun omplirResum() {
        binding.TitolResum.text = "Puntuació de l'alumne "+usuari
        binding.p1.setText(preguntes[0].pregunta)
        binding.p2.setText(preguntes[1].pregunta)
        binding.p3.setText(preguntes[2].pregunta)
        binding.p4.setText(preguntes[3].pregunta)
        binding.p5.setText(preguntes[4].pregunta)

        binding.respostaCorrectaPr1.setText("Resposta correcta: "+preguntes[0].correcta)
        binding.respostaCorrectaPr2.setText("Resposta correcta: "+preguntes[1].correcta)
        binding.respostaCorrectaPr3.setText("Resposta correcta: "+preguntes[2].correcta)
        binding.respostaCorrectaPr4.setText("Resposta correcta: "+preguntes[3].correcta)
        binding.respostaCorrectaPr5.setText("Resposta correcta: "+preguntes[4].correcta)

        binding.respostaUsuariPr1.setText("Resposta alumne: "+preguntes[0].resposta)
        binding.respostaUsuariPr2.setText("Resposta alumne: "+preguntes[1].resposta)
        binding.respostaUsuariPr3.setText("Resposta alumne: "+preguntes[2].resposta)
        binding.respostaUsuariPr4.setText("Resposta alumne: "+preguntes[3].resposta)
        binding.respostaUsuariPr5.setText("Resposta alumne: "+preguntes[4].resposta)

        var puntuacio = 0
        if (preguntes[0].correcta == preguntes[0].resposta)
            puntuacio++
        else
            binding.respostaUsuariPr1.setTextColor(Color.parseColor("#FF0000"))
        if (preguntes[1].correcta == preguntes[1].resposta)
            puntuacio++
        else
            binding.respostaUsuariPr2.setTextColor(Color.parseColor("#FF0000"))
        if (preguntes[2].correcta == preguntes[2].resposta)
            puntuacio++
        else
            binding.respostaUsuariPr3.setTextColor(Color.parseColor("#FF0000"))
        if (preguntes[3].correcta == preguntes[3].resposta)
            puntuacio++
        else
            binding.respostaUsuariPr4.setTextColor(Color.parseColor("#FF0000"))
        if (preguntes[4].correcta == preguntes[4].resposta)
            puntuacio++
        else
            binding.respostaUsuariPr5.setTextColor(Color.parseColor("#FF0000"))

        binding.nota.setText(puntuacio.toString())
    }
}