package com.example.activitat9

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginEnd
import androidx.core.view.setPadding
import com.example.activitat9.databinding.ActivityShoppingCarBinding

class Shopping_Car : AppCompatActivity() {
    private lateinit var binding : ActivityShoppingCarBinding
    private lateinit var layoutParams: MarginLayoutParams
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingCarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var sumaimports = 0.0
        for (prod in llistaCarret) {
            var tableRow = TableRow(this)
            var tvqtt = TextView(this)
            tvqtt.setWidth(40)
            tvqtt.setGravity(Gravity.CENTER)
            var tvdes = TextView(this)
            tvdes.setWidth(180)
            var tvpreu = TextView(this)
            tvpreu.setGravity(Gravity.RIGHT)
            tvpreu.setWidth(80)
            var tvImport = TextView(this)
            tvImport.gravity = Gravity.RIGHT
            tvImport.setWidth(80)
            tvqtt.setText(prod.qtt.toString())

            val prod1 = llistaProductes.filter {
                    producte -> producte.codi.equals(prod.codiproducte)
            } as ArrayList<producte>


            tvdes.setText(prod1[0].descripcio)
            tvpreu.setText(prod1[0].preu.toString()+"€")
            var importe = prod.qtt * prod1[0].preu
            tvImport.setText(importe.toString()+"€")
            tableRow.addView(tvqtt)
            tableRow.addView(tvdes)
            tableRow.addView(tvpreu)
            tableRow.addView(tvImport)
            tableRow.setPadding(10)
            binding.tableCarret.addView(tableRow)
            sumaimports += importe
        }
        var tableRow = TableRow(this)
        var tv1 = TextView(this)
        tv1.setText(" ")
        var tv2 = TextView(this)
        tv2.setText(" ")
        var tv3 = TextView(this)
        tv3.setText("Total imports:")
        tv3.gravity = Gravity.RIGHT
        tv3.setTextColor(Color.parseColor("#229922"))
        var tv4 = TextView(this)
        tv4.gravity = Gravity.RIGHT
        tv4.setText(sumaimports.toString())
        tableRow.addView(tv1)
        tableRow.addView(tv2)
        tableRow.addView(tv3)
        tableRow.addView(tv4)
        binding.tableCarret.addView(tableRow)
    }
}