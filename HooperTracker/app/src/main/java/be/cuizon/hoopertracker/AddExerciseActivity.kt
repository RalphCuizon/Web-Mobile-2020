package be.cuizon.hoopertracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.wajahatkarim3.easyvalidation.core.view_ktx.nonEmpty
import com.wajahatkarim3.easyvalidation.core.view_ktx.validEmail
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator

class AddExerciseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_exercise)
    }

    fun onClickBtnAddExerciseAdd(view: View) {
        validateForm()

        val etCategory = findViewById<EditText>(R.id.etAddExerciseCategory)
        val etTime = findViewById<EditText>(R.id.etAddExerciseTime)
        val etDescription = findViewById<EditText>(R.id.etAddExerciseDescription)
        if (etCategory.text.toString().isNotEmpty() && etTime.text.toString().isNotEmpty() &&
            etDescription.text.toString().isNotEmpty() && etTime.text.toString().validator().maxLength(3).check()) {
            val url =
                "https://www.ralphcuizon.be/hoopertracker/create_exercise.php?user_email=" + UserInfo.email +
                        "&category=" + etCategory.text.toString() +
                        "&description=" + etDescription.text.toString() +
                        "&time=" + etTime.text.toString()
            val queue = Volley.newRequestQueue(this)
            val stringRequest = StringRequest(Request.Method.GET, url, { response ->



                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
            }, { error ->
                Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
            })
            queue.add(stringRequest)
        } else
            Toast.makeText(this, "Exercise could not been added", Toast.LENGTH_LONG).show()
    }

    /**
     *Function to validate the form such Description, etc
     * When empty, a message will appear as a error
     */
    private fun validateForm() {

        val etCategory = findViewById<EditText>(R.id.etAddExerciseCategory)
        val etTime = findViewById<EditText>(R.id.etAddExerciseTime)
        val etDescription = findViewById<EditText>(R.id.etAddExerciseDescription)


        etTime.validator()
            .nonEmpty()
            .maxLength(3)
            .addErrorCallback {
                etTime.error = it
            }
            .check()


        etCategory.nonEmpty() {
            etCategory.error = it
        }

        etDescription.nonEmpty() {
            etDescription.error = it
        }

    }
}