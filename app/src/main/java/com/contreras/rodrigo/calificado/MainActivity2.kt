package com.contreras.rodrigo.calificado

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.contreras.rodrigo.calificado.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    private val NAME_KEY = "NAME_KEY"
    private val PHONE_KEY = "PHONE_KEY"
    private val PRODUCT_KEY = "PRODUCT_KEY"
    private val CITY_KEY = "CITY_KEY"
    private val DIRECCION_KEY = "DIRECCION_KEY"

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain2Binding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        observeButtons()

    }

    private fun observeButtons() {
        binding.Registrar.setOnClickListener{
                goDetailActivity()
        }
    }

    private fun goDetailActivity() {

        val name = binding.inputNombreCliente.text.toString()
        val phone = binding.inputNumeroCliente.text.toString()
        val product = binding.inputProductos.text.toString()
        val city = binding.inputCiudad.text.toString()
        val direction = binding.inputDireccion.text.toString()

        val intent = Intent(this, MainActivity3::class.java)

        intent.putExtra(NAME_KEY, name)
        intent.putExtra(PHONE_KEY, phone)
        intent.putExtra(PRODUCT_KEY, product)
        intent.putExtra(CITY_KEY, city)
        intent.putExtra(DIRECCION_KEY,direction)

        startActivity(intent)
    }


}