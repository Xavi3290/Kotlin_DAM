package com.example.activitat8

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.activitat8.databinding.CardviewBinding

//Al adaptador le has de meter las funciones abstract
class Adaptador(val llista: java.util.ArrayList<TempsDia>) :
    RecyclerView.Adapter<Adaptador.viewholder>() {

    //Agafa la representacio de cada un del cardview
    class viewholder(val view: View) : RecyclerView.ViewHolder(view) {

        // Fet sense binding es necesita aquesta part
        //  val imatge = view.findViewById<ImageView>(R.id.iv_Imatge)
        //  val hora = view.findViewById<TextView>(R.id.tv_Hora)
        //  val temperatura = view.findViewById<TextView>(R.id.tv_Temp)
        //  val precipitacio = view.findViewById<TextView>(R.id.tv_Precipitacio)
        //  val humitat = view.findViewById<TextView>(R.id.tv_Humitat)
        // val vent = view.findViewById<TextView>(R.id.tv_Vent)
        val binding = CardviewBinding.bind(view)

    }

    //infla la cardview per cadascun dels elements
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val layout = LayoutInflater.from(parent.context)
        return viewholder(layout.inflate(R.layout.cardview, parent, false))
    }

    //Enllaça cadaescun dels elements individuals (cadview) amb els elements de la llista
    override fun onBindViewHolder(holder: viewholder, position: Int) {

        // Fet sense binding
        //  Glide.with(holder.imatge.context).load(llista[position].imatge).into(holder.imatge)
        //  holder.hora.setText(llista[position].hora)
        //  holder.temperatura.setText(llista[position].temperatura)
        //  holder.precipitacio.setText(llista[position].precipitacio)
        //  holder.humitat.setText(llista[position].humitat)
        //  holder.vent.setText(llista[position].vent)


        // Fet amb binding
        Glide.with(holder.binding.ivImatge.context).load(llista[position].imatge)
            .into(holder.binding.ivImatge)
        holder.binding.tvHora.setText(llista[position].hora)
        holder.binding.tvTemp.setText(llista[position].temperatura)
        holder.binding.tvPrecipitacio.setText("Precipitacio: ${llista[position].precipitacio}%")
        holder.binding.tvHumitat.setText("Humitat: ${llista[position].humitat}%")
        holder.binding.tvVent.setText("Vent: ${llista[position].vent}km/h")

        holder.view.setOnClickListener {
            Toast.makeText(
                holder.view.context,
                "Has fet click a " + llista[position].hora,
                Toast.LENGTH_LONG
            ).show()
            val intent = Intent(holder.view.context, tempsDiaActivity::class.java)
            intent.putExtra("img", llista[position].imatge)
            intent.putExtra("hora", llista[position].hora)
            intent.putExtra("temperatura", llista[position].temperatura)
            intent.putExtra("precipitacio", "Precipitacio: ${llista[position].precipitacio}%")
            intent.putExtra("humitat", "Humitat: ${llista[position].humitat}%")
            intent.putExtra("vent", "Vent: ${llista[position].vent}km/h")
            holder.view.context.startActivity(intent)    //para que te coga bien el intent con el context
          //  startActivity(holder.view.context, intent, null) //  otra manera de hacerlo
        }

    }

    // indica el nombre d'elements que tindra la llista
    override fun getItemCount(): Int {
        return llista.size
    }
}

