package com.example.emailauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.emailauth.databinding.ActivityMain4Binding


class MainActivity4 : AppCompatActivity() {
    private lateinit var binding: ActivityMain4Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain4Binding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.score.text = intent.getIntExtra("score", 0).toString()

        binding.btn.setOnClickListener {

            startActivity(Intent(this, MainActivity::class.java))
        }
        binding.logout.setOnClickListener {


        }

    }
}