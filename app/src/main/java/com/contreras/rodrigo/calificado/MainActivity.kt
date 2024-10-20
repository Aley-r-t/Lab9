package com.contreras.rodrigo.calificado

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.contreras.rodrigo.calificado.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

   private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupButtonListeners()
    }

    private fun setupButtonListeners() {

        binding.button1.setOnClickListener {
            binding.view.visibility = View.VISIBLE
        }

        binding.title.setOnClickListener{
            startNewActivity()
        }


        binding.button2.setOnClickListener {
            binding.view.visibility = View.INVISIBLE
        }
    }

    private fun startNewActivity() {
        val intent  = Intent(this, MainActivity2::class.java)
        startActivity(intent)
    }

}