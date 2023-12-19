package com.example.activitat6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.activitat6.databinding.ActivityPreguntesBinding

class PreguntesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPreguntesBinding


    var pre = Pregunta("20+20", "5","40","80",2, 0)
    var pre2 = Pregunta("5*5","25","30","20",1,0)
    var pre3 = Pregunta("5/5","10","5","1",3,0)
    var pre4 = Pregunta("3*3","3","6","9",3,0)
    var pre5 = Pregunta("Imagen de Coche","1","2","3",2,0)




    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPreguntesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        preguntes = arrayListOf(pre,pre2,pre3,pre4,pre5)

        var pos = 0

        val nom = intent.getStringExtra("nom").toString()
        binding.tvNom.setText(nom)


        verFragment1()

        binding.fb1.setOnClickListener {
            if(pos<4){
                pos++
            }
            cambiarFb(pos)
        }

        binding.fb2.setOnClickListener {
            if(pos>0){
                pos--
            }
            cambiarFb(pos)
        }

        binding.bAcabar.setOnClickListener {
            val intent = Intent(this, NotaActivity::class.java)
            intent.putExtra("nom",binding.tvNom.text.toString())
            startActivity(intent)
        }

    }

    fun cambiarFb(pos:Int){
        when(pos){
            0 ->{
                verFragment1()
            }
            1 ->{
                verFragment2()
            }
            2 ->{
               verFragment3()
            }
            3 ->{
                verFragment4()
            }
            4 ->{
                verFragment5()
            }
        }
    }

    fun verFragment1(){
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val fragment1 = Pregunta1()
        val args = Bundle()

        //args.putSerializable("pre", pre)
        //args.putString("pregunta",pre.pregunta)
       // args.putString("resposta1",pre.resposta1)
        //args.putString("resposta2",pre.resposta2)
       // args.putString("resposta3",pre.resposta3)
       // args.putInt("correcte",pre.correcta.toString().toInt())
       // args.putInt("respostaUs",pre.respostaUsuari.toString().toInt())
        fragment1.arguments = args
        transaction.replace(R.id.fCont, fragment1)
        transaction.commit()
        preguntes[0].respostaUsuari = fragment1.getRes()





    }

    fun verFragment2(){
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val fragment2 = Pregunta2()
        val args = Bundle()

        //args.putSerializable("pre",pre2)
        fragment2.arguments = args
        transaction.replace(R.id.fCont, fragment2)
        transaction.commit()
        preguntes[1].respostaUsuari = fragment2.getRes()




    }

    fun verFragment3(){
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val fragment3 = Pregunta3()
        val args = Bundle()

        //args.putSerializable("pre",pre3)
        fragment3.arguments = args
        transaction.replace(R.id.fCont, fragment3)
        transaction.commit()
        preguntes[2].respostaUsuari = fragment3.getRes()



    }

    fun verFragment4(){
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val fragment4 = Pregunta4()
        val args = Bundle()

        //args.putSerializable("pre",pre4)
        fragment4.arguments = args
        transaction.replace(R.id.fCont, fragment4)
        transaction.commit()
        preguntes[3].respostaUsuari = fragment4.getRes()



    }

    fun verFragment5(){
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val fragment5 = Pregunta5()
        val args = Bundle()

       // args.putSerializable("pre",pre5)
        fragment5.arguments = args
        transaction.replace(R.id.fCont, fragment5)
        transaction.commit()
        preguntes[4].respostaUsuari = fragment5.getRes()



    }


}