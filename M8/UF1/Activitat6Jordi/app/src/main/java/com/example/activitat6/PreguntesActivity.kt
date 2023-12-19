package com.example.activitat6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.activitat6.databinding.ActivityPreguntesBinding

class PreguntesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPreguntesBinding
    private var npregunta = 0
    lateinit var fragment : Fragment
    var usuari : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreguntesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        usuari = intent.getStringExtra("nomalumne")

        binding.textView.text = usuari
        omplirPreguntes()
        carregarFragment()

        binding.floatingActionButton.setOnClickListener {
            agafarResposta()
            if (npregunta == 0)
                Toast.makeText(this, "Estàs a la primera pregunta", Toast.LENGTH_LONG).show()
            else{
                npregunta--
                carregarFragment()
            }
        }
        binding.floatingActionButton2.setOnClickListener {
            agafarResposta()
            if (npregunta == preguntes.size-1) {
                val intent = Intent(this, ResumActivity::class.java)
                intent.putExtra("nomUsuari", usuari)
                startActivity(intent)
            }else{
                npregunta++
                carregarFragment()
            }
        }
    }

    private fun agafarResposta() {
        when (preguntes[npregunta].tipus) {
            "directa" -> {
                preguntes[npregunta].resposta = (fragment as Pregunta1).getResposta()
            }
            "opcions"-> {
                preguntes[npregunta].resposta = (fragment as PreguntesOpcions).resposta
            }
            "logica" -> {
                preguntes[npregunta].resposta = (fragment as PreguntesLogiques).resposta
            }
        }
    }

    fun carregarFragment(){
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()

        when (preguntes[npregunta].tipus){
            "directa" -> {
                fragment = Pregunta1()
                val args = Bundle()
                val texte = "Pregunta "+(npregunta+1).toString()+"\n"+preguntes[npregunta].pregunta
                args.putString("pregunta", texte)
                if (preguntes[npregunta].resposta != null)
                    args.putString("resposta", preguntes[npregunta].resposta)
                fragment.arguments=args
                transaction.replace(R.id.fcontainer, fragment)

            }
            "opcions"-> {
                fragment = PreguntesOpcions()
                val args = Bundle()
                val texte = "Pregunta "+(npregunta+1).toString()+"\n"+preguntes[npregunta].pregunta
                args.putString("pregunta", texte)
                args.putString("opcio1", preguntes[npregunta].opcio1)
                args.putString("opcio2", preguntes[npregunta].opcio2)
                args.putString("opcio3", preguntes[npregunta].opcio3)
                if (preguntes[npregunta].resposta != null)
                    args.putString("resposta", preguntes[npregunta].resposta)
                fragment.arguments=args
                transaction.replace(R.id.fcontainer, fragment)
            }
            "logica" -> {
                fragment = PreguntesLogiques()
                val args = Bundle()
                val texte = "Pregunta "+(npregunta+1).toString()+"\n"+preguntes[npregunta].pregunta
                args.putString("pregunta", texte)
                if (preguntes[npregunta].resposta != null)
                    args.putString("resposta", preguntes[npregunta].resposta)
                fragment.arguments=args
                transaction.replace(R.id.fcontainer, fragment)
            }
        }
        transaction.addToBackStack(null)
        transaction.commit()

    }

}