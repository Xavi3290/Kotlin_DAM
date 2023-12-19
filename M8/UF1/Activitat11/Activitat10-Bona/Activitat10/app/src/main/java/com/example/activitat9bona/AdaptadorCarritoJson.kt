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
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext

data class AdaptadorCarritoJson(
    var llista: java.util.ArrayList<Producte_JsonItem>,
    override val coroutineContext: CoroutineContext
) :
    RecyclerView.Adapter<AdaptadorCarritoJson.viewholder>(), CoroutineScope {
    class viewholder(val view: View) : RecyclerView.ViewHolder(view) {

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

        var db = BD.getDatabase(holder.view.context)

        holder.binding.ibIzquierda.setOnClickListener {

            var quan = holder.binding.tvQuantitat.text.toString().toInt()
            quan -= 1
            holder.binding.tvQuantitat.setText(quan.toString())
            if (quan == 0) {
               /* runBlocking {
                    val corrutina = launch {
                        //var db = BD.getDatabase(holder.view.context)
                        *//*var proCar = db.daoCarrito().selectCarritosProducte(userBD!![0].codi,llista[position].codi)
                        db.daoCarrito().esborrarCarrito(proCar)*//*
                        db.daoCarrito().esborrarCarritoCodis(userBD!![0].codi, llista[position].codi)
                    }
                    corrutina.join()
                }*/
                //listCarrito.remove(llista[position])
                if(userBD!![0].tipus == 0){
                    listCarritoJson.remove(llista[position])
                }

            }
            notifyDataSetChanged()   // recarrega el recircleview
        }

        holder.binding.ibDerecha.setOnClickListener {
            var quan = holder.binding.tvQuantitat.text.toString().toInt()
            quan += 1
            holder.binding.tvQuantitat.setText(quan.toString())
        }


        holder.binding.bEliminar.setOnClickListener {

          /*  runBlocking {
                val corrutina = launch {
                    //var db = BD.getDatabase(holder.view.context)
                    *//*var proCar = db.daoCarrito().selectCarritosProducte(userBD!![0].codi,llista[position].codi)
                    db.daoCarrito().esborrarCarrito(proCar)*//*
                    db.daoCarrito().esborrarCarritoCodis(userBD!![0].codi, llista[position].codi)
                }
                corrutina.join()
            }*/
            //listCarrito.remove(llista[position])

            if(userBD!![0].tipus == 0){
                listCarritoJson.remove(llista[position])
            }

            /*val intent = Intent(holder.view.context, Carrito::class.java)
            holder.view.context.startActivity(intent)*/
            notifyDataSetChanged()   // recarrega el recircleview
        }

    }

    override fun getItemCount(): Int {
        return llista.size
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://private-bb8c36-proves3.apiary-mock.com//")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun getMethod(codi:Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = getRetrofit().create(APIService::class.java).getProducteApi(codi).body()
            val nomPro = response!!.descripcio
           /* runOnUiThread {
                val adaptador = Adaptador(dades)
                binding.rv1.adapter = adaptador
                binding.rv1.layoutManager = LinearLayoutManager(binding.rv1.context)
            }*/
        }

    }


}

