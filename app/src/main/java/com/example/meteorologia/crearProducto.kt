package com.example.meteorologia

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.meteorologia.Models.Producto
import com.example.meteorologia.Vistas.PerfilFragment
import com.example.meteorologia.databinding.CrearProductoBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class crearProducto : AppCompatActivity() {

    private lateinit var binding: CrearProductoBinding

    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = CrearProductoBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        database = FirebaseDatabase.getInstance().getReference("productos")
        binding.btnGuardar.setOnClickListener {
            val temperatura = binding.etTemperatura.text.toString()
            val viento = binding.etViento.text.toString()
            val humedad = binding.etHumedad.text.toString()
            val presion = binding.etPresion.text.toString()
            val id = database.child("productos").push().key


            if (temperatura.isEmpty()){
                binding.etTemperatura.error = "Ingrese una temperatura"
                return@setOnClickListener
            }
            if (viento.isEmpty()){
                binding.etViento.error = "Ingrese un viento"
                return@setOnClickListener
            }
            if (humedad.isEmpty()){
                binding.etHumedad.error = "Ingrese una humedad"
                return@setOnClickListener
            }
            if (presion.isEmpty()){
                binding.etPresion.error = "Ingrese una presion"
                return@setOnClickListener
            }
            val producto = Producto(id, temperatura, viento, humedad, presion)

            database.child(id!!).setValue(producto).addOnSuccessListener {
                binding.etTemperatura.setText("")
                binding.etViento.setText("")
                binding.etHumedad.setText("")
                binding.etPresion.setText("")
                Snackbar.make(binding.root, "Guardado", Snackbar.LENGTH_SHORT).show()
                val intent = Intent(this, perfil::class.java)
                startActivity(intent)

            }


        }
        binding.btnVolver.setOnClickListener {
            val intent = Intent(this, perfil::class.java)
            startActivity(intent)
        }
    }
}