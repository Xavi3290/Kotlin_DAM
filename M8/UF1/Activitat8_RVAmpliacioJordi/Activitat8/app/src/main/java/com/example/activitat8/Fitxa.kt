package com.example.activitat8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.activitat8.databinding.ActivityFitxaBinding

class Fitxa : AppCompatActivity() {
    private lateinit var binding: ActivityFitxaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFitxaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val hora = intent.getIntExtra("hora", 0)
        binding.HumitatFitxa.setText("Humitat: " + dades[hora].humitat + "%")
        binding.VentFitxa.setText("Velocitat Vent: " + dades[hora].vent + "Km/h")
        binding.PrecipitacioFitxa.setText("Precipitació: " + dades[hora].precipitacio)
        binding.temperaturaFitxa.setText(dades[hora].graus.toString() + "ºC")
        Glide.with(this).load(arrel_url + "256" + dades[hora].url).into(binding.imageViewFitxa)
    }
}


/*
Spinner
    <string-array name="qtt">
        <item>0</item>
        <item>1</item>
        <item>2</item>
        <item>3</item>
        <item>4</item>
        <item>5</item>
    </string-array>


        qtts = resources.getStringArray(R.array.qtt)
        val adapterSpinner = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, qtts)
        binding.spinner.adapter = adapterSpinner
        binding.spinner.onItemSelectedListener = this

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        this.qtt = qtts[position].toInt()
        print("Qtt: "+this.qtt.toString())
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

 */