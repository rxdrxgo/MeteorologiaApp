package com.example.meteorologia.Vistas

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.meteorologia.AdapterProducto.AdapterProducto
import com.example.meteorologia.Models.Producto
import com.example.meteorologia.R
import com.example.meteorologia.databinding.FragmentPresionBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PresionFragment : Fragment() {

    private lateinit var binding: FragmentPresionBinding
    private lateinit var database: DatabaseReference
    private lateinit var productosList: ArrayList<Producto>
    private lateinit var adapterProducto: AdapterProducto
    private lateinit var productoRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPresionBinding.inflate(layoutInflater)

        // Inicializa el RecyclerView antes de asignar el adaptador
        productoRecyclerView = binding.num
        productoRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        productoRecyclerView.setHasFixedSize(true)

        // Inicializar la lista de productos
        productosList = arrayListOf()

        // Llamar a la función para obtener los productos de Firebase
        getProductos()

        return binding.root
    }

    private fun getProductos() {
        database = FirebaseDatabase.getInstance().getReference("productos")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (isAdded) {
                    if (snapshot.exists()) {
                        productosList.clear()

                        // Recorrer los datos de Firebase y agregarlos a la lista
                        for (productoSnapshot in snapshot.children) {
                            val producto = productoSnapshot.getValue(Producto::class.java)
                            if (producto != null) {
                                productosList.add(producto)
                            }
                        }

                        // Ahora solo mostramos la temperatura, por lo que filtramos
                        val presionList = productosList.map { Producto(it.id, it.presion) }

                        // Inicializar el adaptador solo con la temperatura
                        adapterProducto = AdapterProducto(presionList, requireContext())
                        productoRecyclerView.adapter = adapterProducto
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejo de errores
                Log.e("Firebase", "Error al obtener productos", error.toException())
            }
        })
    }
}
