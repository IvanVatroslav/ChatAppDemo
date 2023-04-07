package com.example.chatappdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {

    private lateinit var edtName: EditText // moje
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var buttonSignUp: Button

    private lateinit var mAuth: FirebaseAuth // moje
    private lateinit var mDbRef: DatabaseReference // ref na generirani Firebase db

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        supportActionBar?.hide() // sakriva toolbar imena aplikacije

        mAuth = FirebaseAuth.getInstance()

        edtName = findViewById(R.id.edt_name)
        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)
        buttonSignUp = findViewById(R.id.buttonSignUp)


       buttonSignUp.setOnClickListener {
           val name = edtName.text.toString()
           val email = edtEmail.text.toString()
           val password = edtPassword.text.toString()

           signUp(name, email,password)
       }

    }

    private fun signUp(name: String, email: String, password: String){
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    addUserToDb(name, email, mAuth.currentUser?.uid!!)
                    val intent = Intent(this@SignUp, MainActivity::class.java)
                    finish()
                    startActivity(intent)



                } else {
                    Toast.makeText(this@SignUp, "Some error occcured", Toast.LENGTH_SHORT).show()
                }
            }
    }


    private fun addUserToDb(name: String, email: String, uid: String){
        mDbRef = FirebaseDatabase.getInstance().getReference()

        mDbRef.child("user").child(uid).setValue( User(name,email,uid) )
    }







}