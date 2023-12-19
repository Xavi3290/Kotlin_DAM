package com.example.exemplebdromm

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.exemplebdromm.bd.BD
import com.example.exemplebdromm.bd.Departament
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AdapterDept(var llista: ArrayList<Departament>,
                  override val coroutineContext: CoroutineContext
):RecyclerView.Adapter<AdapterDept.ViewHolder>(),
    CoroutineScope {
    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        var nomDept = view.findViewById<TextView>(R.id.nomdep)
        var btEditar = view.findViewById<FloatingActionButton>(R.id.bteditar)
        var btEsborrar = view.findViewById<FloatingActionButton>(R.id.btesborrar)
        var etNomDept = view.findViewById<EditText>(R.id.etnomdep)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
        return ViewHolder(layout.inflate(R.layout.cardview_dept, parent, false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nomDept.setText(llista[position].nomDepartament)
        holder.btEditar.setOnClickListener {
            if (holder.nomDept.visibility == View.VISIBLE) {
                holder.etNomDept.setText(llista[position].nomDepartament)
                holder.etNomDept.visibility = View.VISIBLE
                holder.nomDept.visibility = View.INVISIBLE
                holder.btEsborrar.visibility = View.INVISIBLE
            }else{
                holder.etNomDept.visibility = View.INVISIBLE
                holder.nomDept.visibility = View.VISIBLE
                holder.btEsborrar.visibility = View.VISIBLE
                val nouNom = holder.etNomDept.text.toString()
                holder.nomDept.setText(nouNom)
                launch {
                    var db = BD.getDatabase(holder.view.context)
                    val departament = Departament(llista[position].codi, nouNom)
                    db.daoDepartaments().modificaDepartament(departament)
                }
            }

        }
        holder.btEsborrar.setOnClickListener {
            val departament = Departament(llista[position].codi, llista[position].nomDepartament)
            showDialogDeleteDepartament(holder.view.context, departament,"Si","No")

        }
    }

    override fun getItemCount() = llista.size

    fun updateLlista(novaLLista: ArrayList<Departament>){
        llista = novaLLista
        notifyDataSetChanged()
    }

    fun showDialogDeleteDepartament(context: Context, departament: Departament, textPositiu: String, textNegatiu: String){
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Esborrar Departament")
        builder.setMessage("Vols esborrar el departament "+departament.nomDepartament+"?")

        builder.setPositiveButton(textPositiu) { dialog, which ->
            launch {
                var db = BD.getDatabase(context)
                db.daoDepartaments().esborraDepartament(departament)
            }
            Toast.makeText(context, "Departament "+departament.nomDepartament+" esborrat", Toast.LENGTH_SHORT).show()
            llista.remove(departament)
            notifyDataSetChanged()
        }

        builder.setNegativeButton(textNegatiu) { dialog, which ->
            print(textNegatiu)
        }

        builder.show()
    }
}