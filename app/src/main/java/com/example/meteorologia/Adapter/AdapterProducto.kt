package com.example.meteorologia.AdapterProducto
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.meteorologia.Models.Producto
import com.example.meteorologia.R

class AdapterProducto(
    private val productos: List<Producto>,
    private val context: Context
) : RecyclerView.Adapter<AdapterProducto.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val temperatura: TextView = itemView.findViewById(R.id.etTemperatura)
        val viento: TextView = itemView.findViewById(R.id.etViento)
        val humedad: TextView = itemView.findViewById(R.id.etHumedad)
        val presion: TextView = itemView.findViewById(R.id.etPresion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_temperatura, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val producto = productos[position]

        // Mostrar solo la temperatura si es el fragmento de temperatura
        holder.temperatura.text = producto.temperatura ?: "0"
        holder.viento.text = producto.viento ?: "0"
        holder.humedad.text = producto.humedad ?: "0"
        holder.presion.text = producto.presion ?: "0"

        // Hacer que los otros campos sean invisibles si es necesario
        if (producto.temperatura != null) {
            holder.viento.visibility = View.GONE
            holder.humedad.visibility = View.GONE
            holder.presion.visibility = View.GONE
        } else {
            holder.temperatura.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return productos.size
    }
}
