package com.example.exaxavierroca

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exaxavierroca.BD.BD
import com.example.exaxavierroca.BD.Esdeveniment
import com.example.exaxavierroca.databinding.CardviewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

class AdaptadorEsdeveniments(val llista: java.util.ArrayList<Esdeveniment>,override val coroutineContext: CoroutineContext) :
    RecyclerView.Adapter<AdaptadorEsdeveniments.viewholder>(), CoroutineScope {
    class viewholder(val view: View) : RecyclerView.ViewHolder(view) {
        val binding = CardviewBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val layout = LayoutInflater.from(parent.context)
        return viewholder(layout.inflate(R.layout.cardview, parent, false))
    }

    override fun getItemCount(): Int {
        return llista.size
    }

    //Recarrega el recirleview
    fun updateDades(novallista: java.util.ArrayList<Esdeveniment>) {
        var llista = novallista
        notifyDataSetChanged()   // recarrega el recircleview
    }


    override fun onBindViewHolder(holder: viewholder, position: Int) {

        holder.binding.tvDescripcio.setText(llista[position].descripcio)
        holder.binding.tvData.setText(llista[position].data)

        var db = BD.getDatabase(holder.view.context)

        holder.binding.bEliminar.setOnClickListener {

            runBlocking {
                val corrutina = launch{
                  // var db = BD.getDatabase(holder.binding.bEliminar.context)
                    db.daoEsdeveniment().esborraEsdeveniment(llista[position])
                }
                corrutina.join()
            }
            listEsdeveniments.remove(llista[position])
            notifyDataSetChanged()   // recarrega el recircleview
        }



    }


}