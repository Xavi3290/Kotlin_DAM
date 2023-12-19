package com.example.activitat9bona

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.activitat9bona.BD.BD
import com.example.activitat9bona.databinding.CardviewCarritoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

class AdaptadorCarrito (var llista: java.util.ArrayList<Producte>, override val coroutineContext: CoroutineContext) :
    RecyclerView.Adapter<AdaptadorCarrito.viewholder>(), CoroutineScope {
    class viewholder (val view: View) : RecyclerView.ViewHolder(view){

        val binding = CardviewCarritoBinding.bind(view)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val layout = LayoutInflater.from(parent.context)
        return viewholder(layout.inflate(R.layout.cardview_carrito, parent, false))
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {

        Glide.with(holder.binding.ivImatge.context).load(llista[position].imatge)
            .into(holder.binding.ivImatge)
        holder.binding.tvDescripcio.setText(llista[position].descripcio)
        holder.binding.tvPreu.setText(llista[position].preu.toString())

        //var quan:Int

        holder.binding.ibIzquierda.setOnClickListener {

            var quan = holder.binding.tvQuantitat.text.toString().toInt()
            quan -= 1
            holder.binding.tvQuantitat.setText(quan.toString())
            if(quan == 0){
                runBlocking {
                    val corrutina = launch {
                        var db = BD.getDatabase(holder.view.context)
                        var proCar = db.daoCarrito().selectCarritosProducte(userBD!![0].codi,llista[position].codi)
                        db.daoCarrito().esborrarCarrito(proCar)
                    }
                    corrutina.join()
                }
           }
        }

        holder.binding.ibDerecha.setOnClickListener {
            var quan = holder.binding.tvQuantitat.text.toString().toInt()
            quan += 1
            holder.binding.tvQuantitat.setText(quan.toString())
        }


        holder.binding.bEliminar.setOnClickListener {
            listCarrito.remove(llista[position])
            runBlocking {
                val corrutina = launch {
                    var db = BD.getDatabase(holder.view.context)
                    var proCar = db.daoCarrito().selectCarritosProducte(userBD!![0].codi,llista[position].codi)
                    db.daoCarrito().esborrarCarrito(proCar)
                }
                corrutina.join()
            }
        }

    }

    override fun getItemCount(): Int {
        return llista.size
    }
}