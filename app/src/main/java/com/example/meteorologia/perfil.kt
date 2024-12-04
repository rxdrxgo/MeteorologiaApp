package com.example.meteorologia
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.meteorologia.Vistas.HumedadFragment
import com.example.meteorologia.Vistas.PerfilFragment
import com.example.meteorologia.Vistas.PresionFragment
import com.example.meteorologia.Vistas.TemperaturaFragment
import com.example.meteorologia.Vistas.VientoFragment
import com.example.meteorologia.databinding.ActivityPerfilBinding

class perfil : AppCompatActivity() {

    // Configuración de viewBinding
    private lateinit var binding: ActivityPerfilBinding

    // Configuración de Firebase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_perfil, PerfilFragment()).commit()
        }


        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.item_1 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_perfil, PerfilFragment()).commit()
                    true
                }
                R.id.item_2 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_perfil, TemperaturaFragment()).commit()
                    true
                }
                R.id.item_3 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_perfil, HumedadFragment()).commit()
                    true
                }
                R.id.item_4 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_perfil, VientoFragment()).commit()
                    true
                }
                R.id.item_5 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_perfil, PresionFragment()).commit()
                    true
                }


                else -> false
            }
        }

        binding.bottomNavigation.setOnItemReselectedListener {
            when(it.itemId){
                R.id.item_1 -> {
                    true
                }
                R.id.item_2 -> {
                    true
                }
                R.id.item_3 -> {
                    true
                }
                R.id.item_4 -> {
                    true
                }
                R.id.item_5 -> {
                    true
                }
                else -> false
            }
        }
    }

}