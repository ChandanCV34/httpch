package com.example.food_ordering_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.view.get
import androidx.core.view.isEmpty
import androidx.lifecycle.MutableLiveData
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class User_Login : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)

        val button=findViewById<Button>(R.id.button)
        val email=findViewById<TextInputLayout>(R.id.userEmail)
        val password=findViewById<TextInputLayout>(R.id.userPassword)
        val errorMessage=findViewById<TextView>(R.id.errorView)
        val registernav=findViewById<Button>(R.id.button2)

        val inputemailname = MutableLiveData<String>()

        val inputPassword = MutableLiveData<String>()


        val intent = Intent(this, MainActivity::class.java)
        val intent1 = Intent(this,MainActivity::class.java)
        apiClient = ApiClient()
        sessionManager = SessionManager(this)


       CoroutineScope(Dispatchers.IO).launch {
           withContext(Dispatchers.Main){
               apiClient.getApiService().login(LoginRequest(email = "chan@gmail.com", password = ""))
                   .enqueue(object : Callback<LoginResponse> {
                       override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                           // Error logging in
                       }

                       override fun onResponse(
                           call: Call<LoginResponse>,
                           response: Response<LoginResponse>
                       ) {
                           TODO("Not yet implemented")
                           val loginResponse = response.body()
                           if (loginResponse?.statusCode == 200 && loginResponse.user != null) {
                               sessionManager.saveAuthToken(loginResponse.token)
                           } else {
                               // Error logging in
                           }
                       }

                   })
           }
       }


       /* button.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                var user:List<User>? = FoodDatabase.getDatabase(this@User_Login).UserDao().findByUserEmailPassword(
                    email = email.editText?.text.toString(),
                    password = password.editText?.text.toString()
                )
                withContext(Dispatchers.Main) {
                    if (user != null && user.size>0) {
                        startActivity(intent)
                    }
                }
            }*/

           /* CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main) {
                    if (email == null || password == null) {
                        errorMessage.text = "Required Fields ARE Mandatory"
                    }
                    *//*else if(inputemailname==email && inputPassword==password) {
                        startActivity(intent)
                    }*//*
                    else {
                        var useremail:List<User>? =   FoodDatabase.getDatabase(this@User_Login).UserDao().getUsername(
                                email = email.editText?.text.toString()
                            )

                        val userspassword:List<User> =
                            FoodDatabase.getDatabase(this@User_Login).UserDao().getpassword(
                                password = password.editText?.text.toString()
                            )
                        if (useremail != null && userspassword != null) {
                            if (useremail.get(0).email==email.toString() && userspassword.get(0).password == password.toString()) {
                                *//*inputemailname.value = null
                                inputPassword.value = null*//*
                                startActivity(intent)
                            }
                        }
                    }
                }
            }*/
        }

      /*  registernav.setOnClickListener {
            startActivity(intent1)
        }*/
    }

