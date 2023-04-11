package com.example.emailauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.emailauth.databinding.ActivityMain3Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity3 : AppCompatActivity() {
    private lateinit var user: user
    var index = 0
    var score = 0
    lateinit var questionList: ArrayList<Question>
    private lateinit var binding: ActivityMain3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        user = user()
        questionList = ArrayList<Question>()
        FirebaseDatabase.getInstance().reference.child("user")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    user = snapshot.getValue(user::class.java)!!
                    binding.textView.text = user.name
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }


            })
        Firebase.firestore.collection("questions").get().addOnSuccessListener { result ->
            var temp = ArrayList<Question>()
            for (res in result.documents) {

                var question: Question? =
                    res.toObject(Question::class.java)//que come from databese 1 by 1
                temp.add(question!!)                                      //que add in array
            }
            questionList.addAll(temp)
            //Toast.makeText(this, questionList[1].question.toString(), Toast.LENGTH_SHORT).show()
            binding.question.text = questionList.get(index).question
            binding.op1.text = questionList.get(index).op1
            binding.op2.text = questionList.get(index).op2
            binding.op3.text = questionList.get(index).op3
            binding.op4.text = questionList.get(index).op4
        }
        binding.op1.setOnClickListener { changeQuestion(binding.op1.text.toString()) }
        binding.op2.setOnClickListener { changeQuestion(binding.op2.text.toString()) }
        binding.op3.setOnClickListener { changeQuestion(binding.op3.text.toString()) }
        binding.op4.setOnClickListener { changeQuestion(binding.op4.text.toString()) }

    }

    private fun changeQuestion(text: String) {

        if (text.equals(questionList.get(index).ans)) {
            score++
        }
        index++
        if (index >= questionList.size) {

            startActivity(Intent(this, MainActivity4::class.java).putExtra("score", score))
            finish()
        } else {
            binding.question.text = questionList.get(index).question
            binding.op1.text = questionList.get(index).op1
            binding.op2.text = questionList.get(index).op2
            binding.op3.text = questionList.get(index).op3
            binding.op4.text = questionList.get(index).op4
        }

    }
}