package com.contreras.rodrigo.calificado

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.contreras.rodrigo.calificado.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {

    private lateinit var binding:ActivityMain3Binding

    private val NAME_KEY = "NAME_KEY"
    private val PHONE_KEY = "PHONE_KEY"
    private val PRODUCT_KEY = "PRODUCT_KEY"
    private val CITY_KEY = "CITY_KEY"
    private val DIRECCION_KEY = "DIRECCION_KEY"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain3Binding.inflate(layoutInflater)

        enableEdgeToEdge()


        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        showInformation(intent.extras)
        observeButtons(intent.extras)
    }

    private fun showInformation(bundle: Bundle?) {
        bundle?.let {
            val name = it.getString(NAME_KEY, "Nombre no disponible")
            val phone = it.getString(PHONE_KEY, "Número no disponible")
            val product = it.getString(PRODUCT_KEY, "Producto no disponible")
            val city = it.getString(CITY_KEY, "Ciudad no disponible")
            val direction = it.getString(DIRECCION_KEY, "Dirección no disponible")

            binding.tvName.text = "Nombre completo: $name"
            binding.tvPhone.text = "Número: $phone"
            binding.tvProduct.text = "Producto: $product"
            binding.tvDirection.text = "Ciudad de $city con dirección en $direction"
        }
    }

    // Configura los listeners para los botones
    private fun observeButtons(bundle: Bundle?) {
        binding.imbWsp.setOnClickListener {
            goWsp(bundle)
        }

        binding.imbDial.setOnClickListener {
            goDial(bundle)
        }


    }

    // Llama a WhatsApp con el número del cliente y un mensaje predefinido
    private fun goWsp(bundle: Bundle?) {
        val phone = "+51${bundle?.getString(PHONE_KEY)}"
        val message = "Hola tecsupino(a) te he agregado a mi lista de contactos"
        val intentWsp = Intent(Intent.ACTION_VIEW)

        intentWsp.data = Uri.parse("https://wa.me/$phone?text=$message")
        if (intentWsp.resolveActivity(packageManager) != null) {
            startActivity(intentWsp)
        } else {
            Toast.makeText(this, "WhatsApp no está instalado.", Toast.LENGTH_SHORT).show()
        }
    }

    // Inicia la llamada telefónica
    private fun goDial(bundle: Bundle?) {
        val phone = bundle?.getString(PHONE_KEY)
        if (phone != null) {
            val intentDial = Intent(Intent.ACTION_DIAL)
            intentDial.data = Uri.parse("tel:$phone")
            startActivity(intentDial)
        } else {
            Toast.makeText(this, "Número de teléfono no disponible.", Toast.LENGTH_SHORT).show()
        }
    }

    // Abre la ubicación en Google Maps con la ciudad y dirección
    private fun goMaps(bundle: Bundle?) {
        val city = bundle?.getString(CITY_KEY)
        val direction = bundle?.getString(DIRECCION_KEY)

        if (city != null && direction != null) {
            val location = "$city, $direction"
            val intentMaps = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$location"))
            intentMaps.setPackage("com.google.android.apps.maps")
            if (intentMaps.resolveActivity(packageManager) != null) {
                startActivity(intentMaps)
            } else {
                Toast.makeText(this, "Google Maps no está instalado.", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Ubicación no disponible.", Toast.LENGTH_SHORT).show()
        }
    }


}