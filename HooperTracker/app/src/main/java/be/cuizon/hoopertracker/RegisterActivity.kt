package be.cuizon.hoopertracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.wajahatkarim3.easyvalidation.core.collection_ktx.nonEmptyList
import com.wajahatkarim3.easyvalidation.core.view_ktx.nonEmpty
import com.wajahatkarim3.easyvalidation.core.view_ktx.validEmail
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator


class RegisterActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

    }

    /**
     * When Button Sign Up is clicked,
     * function validateForm is called
     * check if no texts are empty
     * check if password is equal to confirm password
     * Thanks to Volley library and php-file and db we are able to create a user
     */
    fun onClickBtnRegisterSignUp(view: View) {
        validateForm()

        val etEmail = findViewById<EditText>(R.id.etRegisterEmail)
        val etPassword = findViewById<EditText>(R.id.etRegisterPassword)
        val etConfirmPassword = findViewById<EditText>(R.id.etRegisterConfirmPassword)
        val etFirstName = findViewById<EditText>(R.id.etRegisterFirstName)
        val etLastName = findViewById<EditText>(R.id.etRegisterLastName)
        if (etEmail.text.toString().isNotEmpty() && etPassword.text.toString().isNotEmpty() &&
            etConfirmPassword.text.toString().isNotEmpty() && etFirstName.text.toString().isNotEmpty() &&
            etLastName.text.toString().isNotEmpty() && etEmail.validEmail())
        {
            if (etPassword.text.toString() == etConfirmPassword.text.toString()) {
                val url =
                    "https://www.ralphcuizon.be/hoopertracker/create_user.php?email=" + etEmail.text.toString() +
                            "&password=" + etPassword.text.toString() +
                            "&firstname=" + etFirstName.text.toString() +
                            "&lastname=" + etLastName.text.toString()
                val queue = Volley.newRequestQueue(this)
                val stringRequest = StringRequest(Request.Method.GET, url, { response ->
                    if (response.equals("Email already been used")) {
                        Toast.makeText(this, "Email has already been used", Toast.LENGTH_LONG).show()
                    }
                    else {
                        Log.i("test", response)

                        Log.i("test", etEmail.text.toString())
                        Log.i("test", etPassword.text.toString())
                        UserInfo.email = etEmail.text.toString()
                        Toast.makeText(this, "User has been successfully added", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                    }
                }, { error ->
                    Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
                })
                queue.add(stringRequest)
            } else
                Toast.makeText(this, "Password doesn't match", Toast.LENGTH_LONG).show()

        }
    }

    /**
    *Function to validate the form such Password, etc
     * When empty, a message will appear as a error
    */
    private fun validateForm() {

        val etEmail = findViewById<EditText>(R.id.etRegisterEmail)
        val etPassword = findViewById<EditText>(R.id.etRegisterPassword)
        val etConfirmPassword = findViewById<EditText>(R.id.etRegisterConfirmPassword)
        val etFirstName = findViewById<EditText>(R.id.etRegisterFirstName)
        val etLastName = findViewById<EditText>(R.id.etRegisterLastName)

        etEmail.validEmail() {
            etEmail.error = it
        }

        etEmail.nonEmpty() {
            etEmail.error = it
        }

        etPassword.nonEmpty() {
            etPassword.error = it
        }

        etConfirmPassword.nonEmpty() {
            etConfirmPassword.error = it
        }

        etFirstName.nonEmpty() {
            etFirstName.error = it
        }

        etLastName.nonEmpty() {
            etLastName.error = it
        }

    }

}