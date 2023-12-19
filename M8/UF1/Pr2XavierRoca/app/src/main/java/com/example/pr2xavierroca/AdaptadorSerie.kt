package com.example.pr2xavierroca

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.pr2xavierroca.BD.BD
import com.example.pr2xavierroca.BD.Serie
import com.example.pr2xavierroca.databinding.CardviewSerieBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

data class AdaptadorSerie(var llista: java.util.ArrayList<Serie>, override val coroutineContext: CoroutineContext):
    RecyclerView.Adapter<AdaptadorSerie.viewholder>(), CoroutineScope {

    class viewholder(val view: View) : RecyclerView.ViewHolder(view) {
        val binding = CardviewSerieBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val layout = LayoutInflater.from(parent.context)
        return viewholder(layout.inflate(R.layout.cardview_serie, parent, false))
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {

        holder.binding.tvTitol.setText(llista[position].titol)
        holder.binding.tvGenere.setText(llista[position].genere)
        holder.binding.tvNumTemp.setText(llista[position].numTemp.toString())

        var db = BD.getDatabase(holder.view.context)

        holder.binding.bEliminar.setOnClickListener {
            runBlocking {
                val corrutina = launch {
                    db.daoSerie().esborraSerie(llista[position])
                }
            }
        }

        holder.view.setOnClickListener {
            Toast.makeText(
                holder.view.context,
                "Has fet click a " + llista[position].titol,
                Toast.LENGTH_SHORT
            ).show()
            val intent = Intent(holder.view.context,CapitolActivity::class.java)
            intent.putExtra("codi",llista[position].idSerie.toString())
            holder.view.context.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
        return llista.size
    }
}
