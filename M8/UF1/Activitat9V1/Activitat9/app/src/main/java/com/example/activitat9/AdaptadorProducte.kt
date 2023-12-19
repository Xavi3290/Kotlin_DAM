package com.example.activitat9

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.activitat9.databinding.Cardview2Binding
import com.example.activitat9.databinding.CardviewBinding

class AdaptadorProducte(var llista: java.util.ArrayList<Producte>) :
    RecyclerView.Adapter<AdaptadorProducte.viewholder>() {
    class viewholder(val view: View) : RecyclerView.ViewHolder(view) {

        val binding = CardviewBinding.bind(view)
        //val binding2 = Cardview2Binding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {

        val layout = LayoutInflater.from(parent.context)
        if (viewType == 1)
            return viewholder(layout.inflate(R.layout.cardview, parent, false))
        else
            return viewholder(layout.inflate(R.layout.cardview2, parent, false))
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {

        Glide.with(holder.binding.ivImatge.context).load(llista[position].imatge)
            .into(holder.binding.ivImatge)
        holder.binding.tvDescripcio.setText(llista[position].descripcio)
        holder.binding.tvDescripcioCompleta.setText(llista[position].descripcioCompleta)
        holder.binding.tvPreu.setText(llista[position].preu.toString())


        holder.view.setOnClickListener {
            Toast.makeText(
                holder.view.context,
                "Has fet click a " + llista[position].descripcio,
                Toast.LENGTH_SHORT
            ).show()

            val intent = Intent(holder.view.context, FitxaProducte::class.java)
            intent.putExtra("img", llista[position].imatge)
            intent.putExtra("descripcio", llista[position].descripcio)
            intent.putExtra("descripcioCompleta", llista[position].descripcioCompleta)
            intent.putExtra("preu", llista[position].preu.toString())
            intent.putExtra("codi", llista[position].codi)
            holder.view.context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return llista.size
    }

    override fun getItemViewType(position: Int): Int {
        return llista[position].tipusCardview
    }

    fun updateDades(novallista: java.util.ArrayList<Producte>) {
        llista = novallista
        notifyDataSetChanged()
    }
}