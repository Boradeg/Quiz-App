package com.example.emailauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.emailauth.databinding.ActivityMain2Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            var temp = intent.getIntExtra("MODE", 0)
            if (temp == 1) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    binding.email.text.toString(),
                    binding.pass.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        //push use for create new user.if we not use push then newly data not generated only data updated
                        //uid
                        var user = user(
                            binding.name.text.toString(),
                            binding.email.text.toString(),
                            binding.pass.text.toString()
                        )
                        Firebase.database.reference.child("user")
                            .child(FirebaseAuth.getInstance().currentUser!!.uid).setValue(user)
                            .addOnSuccessListener {
                                Toast.makeText(this, "crete user for app", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        startActivity(Intent(this, MainActivity3::class.java))
//                        Toast.makeText(this, "crete user for app", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            ////login user
            else {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    binding.email.text.toString(),
                    binding.pass.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        startActivity(Intent(this, MainActivity3::class.java))
                    }

                }
            }


        }
        binding.text.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java).putExtra("MODE", 1))
        }

    }

    override fun onStart() {
        super.onStart()
        if (intent.hasExtra("MODE")) {
            var temp = intent.getIntExtra("MODE", 0)
            if (temp == 1) {
                binding.button.text = "Create Account"


            }


        }
    }
}