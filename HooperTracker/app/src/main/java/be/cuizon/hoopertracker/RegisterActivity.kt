package be.cuizon.hoopertracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.wajahatkarim3.easyvalidation.core.collection_ktx.nonEmptyList
import com.wajahatkarim3.easyvalidation.core.view_ktx.nonEmpty
import com.wajahatkarim3.easyvalidation.core.view_ktx.validEmail
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator


class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

    }

    fun onClickBtnRegisterSignUp(view: View) {
        validateForm()
    }

    /**
    *Function to validate the form such Password, etc
    */
    private fun validateForm() {
        var etEmail = findViewById<EditText>(R.id.etRegisterEmail)
        var etPassword = findViewById<EditText>(R.id.etRegisterPassword)
        var etConfirmPassword = findViewById<EditText>(R.id.etRegisterConfirmPassword)
        var etFirstName = findViewById<EditText>(R.id.etRegisterFirstName)
        var etLastName = findViewById<EditText>(R.id.etRegisterLastName)


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