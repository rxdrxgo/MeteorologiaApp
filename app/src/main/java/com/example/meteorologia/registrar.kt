package com.example.meteorologia
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.meteorologia.databinding.ActivityRegistrarBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class registrar : AppCompatActivity() {

    // Configuración de viewBinding
    private lateinit var binding: ActivityRegistrarBinding

    //Configurar firebase
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inicialización de viewBinding
        binding = ActivityRegistrarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar firebase
        auth = Firebase.auth


        //Boton de registrar
        binding.btnRegistrar.setOnClickListener {
            // Obtener todos las datos del formulario
            val email = binding.etEmail.text.toString()
            val pass1 = binding.etPassword.text.toString()
            val pass2 = binding.etPassword2.text.toString()


            if(email.isEmpty()){
                binding.etEmail.error = "Por favor ingrese un email"
                return@setOnClickListener
            }

            if (pass1.isEmpty()){
                binding.etPassword.error = "Por favor ingrese una contraseña"
                return@setOnClickListener
            }

            if (pass2.isEmpty()){
                binding.etPassword2.error = "Por favor ingrese la contraseña nuevamente"
                return@setOnClickListener
            }

            if (pass1 != pass2){
                binding.etPassword2.error = "Las contraseñas no coinciden"
                return@setOnClickListener
            } else {
                signUp(email, pass1)
            }


        }


    }

    private fun signUp(email: String, pass: String) {
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener{
                if(it.isSuccessful){
                    Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show()
                }
            }
    }
}