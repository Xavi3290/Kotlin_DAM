package com.example.pr2xavierroca

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pr2xavierroca.BD.Capitol
import com.example.pr2xavierroca.BD.Serie
import com.example.pr2xavierroca.databinding.CardviewCapitol1Binding
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext


data class AdaptadorCapitol (var llista: java.util.ArrayList<Capitol>, override val coroutineContext: CoroutineContext):
    RecyclerView.Adapter<AdaptadorCapitol.viewholder>(), CoroutineScope {
    class viewholder (val view: View) : RecyclerView.ViewHolder(view){
        val binding = CardviewCapitol1Binding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val layout = LayoutInflater.from(parent.context)
        if (viewType == 1)
            return viewholder(layout.inflate(R.layout.cardview_capitol1, parent, false))
        else
            return viewholder(layout.inflate(R.layout.cardview_capitol2, parent, false))
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {

        holder.binding.tvTitol.setText(llista[position].titol)
        holder.binding.tvNumTemp.setText(llista[position].numTemp.toString())
        holder.binding.tvDurada.setText(llista[position].durada)

    }

    override fun getItemCount(): Int {
        return llista.size
    }
    override fun getItemViewType(position: Int): Int {
        return llista[position].important
    }
}