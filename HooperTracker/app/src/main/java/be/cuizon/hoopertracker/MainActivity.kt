package be.cuizon.hoopertracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import android.widget.Toolbar
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.wajahatkarim3.easyvalidation.core.view_ktx.nonEmpty
import com.wajahatkarim3.easyvalidation.core.view_ktx.validEmail

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    /**
     *     When Button SignUp is clicked, Sign Up page is opened
     */
    fun onClickBtnLoginSignUp(view: View) {
        val intent = Intent(this,RegisterActivity::class.java)
        startActivity(intent)
    }

    /**
     *     When Button Login is clicked,
     *     check if password and email are not empty and email is valid,
     *     if it is then the user receive a error message
     *     if it isn't the login.php is called (GET)
     *
     *
     */
    fun onClickBtnLoginLogin(view: View) {


        val etEmail = findViewById<EditText>(R.id.etLoginEmail)
        val etPassword = findViewById<EditText>(R.id.etLoginPassword)

        etEmail.validEmail() {
            etEmail.error = it
        }

        etEmail.nonEmpty() {
            etEmail.error = it
        }

        etPassword.nonEmpty() {
            etPassword.error = it
        }
        if (etEmail.text.toString().isNotEmpty() && etPassword.text.toString().isNotEmpty() &&
             etEmail.validEmail())
        {
            val url =
                "https://www.ralphcuizon.be/hoopertracker/login.php?email=" + etEmail.text.toString() +
                        "&password=" + etPassword.text.toString()
            val queue = Volley.newRequestQueue(this)
            val stringRequest = StringRequest(Request.Method.POST, url, { response ->
                if (response.equals("0")) {
                    Toast.makeText(this, "Unsuccessfully logged in", Toast.LENGTH_LONG).show()
                }
                else {
                    Log.i("test", response)
                    Log.i("test", etEmail.text.toString())
                    Log.i("test", etPassword.text.toString())
                    UserInfo.email = etEmail.text.toString()
                    Toast.makeText(this, "Successfully logged in", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }

                }, { error ->
                    Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
                })
                queue.add(stringRequest)
            }

        }
    }


