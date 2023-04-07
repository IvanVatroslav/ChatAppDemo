package com.example.chatappdemo



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.ToggleButton

class Login : AppCompatActivity() {
    //private lateinit var toggleButton: Button
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var buttonSignUp: Button
    //private var zastavica: Boolean = false
    private lateinit var mAuth: FirebaseAuth // moje


    private lateinit var imageView: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide() // sakriva toolbar imena aplikacije

        mAuth = FirebaseAuth.getInstance()

        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)
        buttonLogin = findViewById(R.id.buttonLogIn)
        buttonSignUp = findViewById(R.id.buttonSignUp)



        imageView = findViewById(R.id.imageView_show_hide_pasword)
        imageView.setImageResource(R.drawable.hide_password)



        imageView.setOnClickListener {
            onClick_eyepassword()
        }
        buttonSignUp.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
        buttonLogin.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            login(email,password);
        }
    }

    private fun onClick_eyepassword() {

        if (edtPassword.transformationMethod == HideReturnsTransformationMethod.getInstance()) {
            edtPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            imageView.setImageResource(R.drawable.hide_password)
        } else {
            edtPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            imageView.setImageResource(R.drawable.show_password)
        }
        // Call invalidate() to force the view to redraw
        edtPassword.invalidate()
    }

    private fun login(email: String, password: String){
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this@Login, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this@Login, "User doesn't exist", Toast.LENGTH_SHORT).show()

                }
            }
    }
}