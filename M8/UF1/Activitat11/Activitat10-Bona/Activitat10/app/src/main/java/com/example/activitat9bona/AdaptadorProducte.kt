package com.example.activitat9bona

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.activitat9bona.databinding.CardviewBinding

class AdaptadorProducte(var llista: java.util.ArrayList<Producte>) :
    RecyclerView.Adapter<AdaptadorProducte.viewholder>() {

    //Agafa la representacio de cada un del cardview
    class viewholder(val view: View) : RecyclerView.ViewHolder(view) {

        val binding = CardviewBinding.bind(view)
        //val binding2 = Cardview2Binding.bind(view)
    }

    //infla la cardview per cadascun dels elements
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {

        val layout = LayoutInflater.from(parent.context)
        if (viewType == 1)
            return viewholder(layout.inflate(R.layout.cardview, parent, false))
        else
            return viewholder(layout.inflate(R.layout.cardview2, parent, false))

       /* var vh : AdaptadorHome.viewholder? = null
        when(viewType){
            1-> vh = viewholder(layout.inflate(R.layout.cardview_seccion1, parent, false))
            2-> vh = viewholder(layout.inflate(R.layout.cardview_seccion2, parent, false))
            3-> vh = viewholder(layout.inflate(R.layout.cardview_seccion3, parent, false))
            4-> vh = viewholder(layout.inflate(R.layout.cardview_seccion4, parent, false))
        }
        return vh!!
        }*/

    }

    //Enllaça cadaescun dels elements individuals (cadview) amb els elements de la llista
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

    // indica el nombre d'elements que tindra la llista
    override fun getItemCount(): Int {
        return llista.size
    }

    //Enllaça el numoero de tipus de cardview amb la posicio del viewtype per selecciona diferents cardviews
    override fun getItemViewType(position: Int): Int {
        return llista[position].tipusCardview
    }

    //Recarrega el recirleview
    fun updateDades(novallista: java.util.ArrayList<Producte>) {
        llista = novallista
        notifyDataSetChanged()   // recarrega el recircleview
    }
}