package com.example.exemplebd

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.example.exemplebd.bd.Pelicula
import com.example.exemplebd.databinding.CardviewBinding
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

class Adaptador (var llista: java.util.ArrayList<Pelicula>, override val coroutineContext: CoroutineContext) :
    RecyclerView.Adapter<Adaptador.viewholder>(), CoroutineScope {
    class viewholder (val view: View) : RecyclerView.ViewHolder(view){
        val binding = CardviewBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val layout = LayoutInflater.from(parent.context)
        return viewholder(layout.inflate(R.layout.cardview, parent, false))
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return llista.size
    }
}