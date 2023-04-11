package com.example.emailauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.emailauth.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.email.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }
//                binding.google.setOnClickListener {
//            FirebaseAuth.getInstance().createUserWithEmailAndPassword("kiran@gmail.com", "123526")
//                .addOnCompleteListener {
//                    if (it.isSuccessful) {
//                        Toast.makeText(this, "user created", Toast.LENGTH_SHORT).show()
//                    }
//                }
//
//        }


    }

    override fun onStart() {
        super.onStart()
        if (Firebase.auth.currentUser != null) {
            startActivity(Intent(this, MainActivity3::class.java))
            finish()
        }
    }
}