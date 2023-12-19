package com.example.activitat10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.activitat10.database.ProducteCarret
import com.example.activitat10.database.database
import com.example.activitat10.databinding.ActivityFitxaProducteBinding
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class FitxaProducte : AppCompatActivity(), AdapterView.OnItemSelectedListener, CoroutineScope {
    private lateinit var binding: ActivityFitxaProducteBinding
    private lateinit var qtts: Array<String>
    var qtt = 0
    private lateinit var db: database
    private var job: Job = Job()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFitxaProducteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val codiProducte = intent.getIntExtra("codiproducte",0)

        val prod = llistaProductes.filter {
                producte -> producte.codi.equals(codiProducte)
        } as ArrayList<producte>

        binding.preuProducte.setText(prod[0].preu.toString()+" €")
        binding.descProducte.setText(prod[0].descripcio)
        binding.DescripcioCompleta.setText(prod[0].descripcioCompleta)
        Glide.with(this).load(prod[0].imatge).into(binding.imatgeProducte)


        qtts = resources.getStringArray(R.array.qtt)
        val adapterSpinner = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, qtts)
        binding.spinner.adapter = adapterSpinner
        binding.spinner.onItemSelectedListener = this

        binding.afegirCarret.setOnClickListener {
            val pc = ProducteCarret(codiProducte, this.qtt, usuariActual)
            llistaCarret.add(
                pc
            )
            launch {
                db = database.getDatabase(applicationContext)
                db.DaoProductes().insertProducteCarret(pc)
            }
            var text = "S'han afegit "+this.qtt+" "
            text += prod[0].descripcio
            Toast.makeText(this, text, Toast.LENGTH_LONG).show()

        }

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        this.qtt = qtts[position].toInt()
        print("Qtt: "+this.qtt.toString())
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
}