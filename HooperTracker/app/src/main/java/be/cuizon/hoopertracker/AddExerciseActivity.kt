package be.cuizon.hoopertracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
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