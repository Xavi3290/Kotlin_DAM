package com.example.activitat10

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.activitat10.database.ProducteCarret
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AdapterShoppingCarV2(var llista: ArrayList<ProducteCarret>):RecyclerView.Adapter<AdapterShoppingCarV2.ViewHolder>() {
    class ViewHolder(vista: View): androidx.recyclerview.widget.RecyclerView.ViewHolder(vista) {
        val qtt1 = vista.findViewById<TextView>(R.id.qtt_shop_car_v2)
        val botmes = vista.findViewById<FloatingActionButton>(R.id.fbmes)
        val botmenys = vista.findViewById<FloatingActionButton>(R.id.fbmenys)
        val descripcio = vista.findViewById<TextView>(R.id.desc_shop_car_v2)
        val preu = vista.findViewById<TextView>(R.id.preu_shop_car_v2)
        var importe = vista.findViewById<TextView>(R.id.import_shop_car_v2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
        return ViewHolder(layout.inflate(R.layout.cardview_shopping_car_v2, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.qtt1.setText(llista[position].qtt.toString())

       val prod1 = llistaProductes.filter {
                producte -> producte.codi!!.equals(llista[position].codiproducte)
        } as ArrayList<Producte>

        holder.descripcio.setText(prod1[0].descripcio)
        holder.preu.setText(prod1[0].preu.toString()+" €")
        var importe = llista[position].qtt*prod1[0].preu
        holder.importe.setText(importe.toString()+" €")

        holder.botmes.setOnClickListener {
            llista[position].qtt ++
            holder.qtt1.setText(llista[position].qtt.toString())
        }
        holder.botmenys.setOnClickListener {
            llista[position].qtt --
            holder.qtt1.setText(llista[position].qtt.toString())
        }
    }

    override fun getItemCount() = llista.size

}