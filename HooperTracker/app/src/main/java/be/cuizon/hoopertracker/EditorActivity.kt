package be.cuizon.hoopertracker

import android.content.ClipDescription
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText
import com.wajahatkarim3.easyvalidation.core.view_ktx.nonEmpty
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator

class EditorActivity : AppCompatActivity() {
    private lateinit var etEditorCategory: EditText
    private lateinit var etEditorTime: EditText
    private lateinit var etEditorDescription: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)
        initViews()
        writeData()



    }

    private fun initViews() {
        etEditorCategory = findViewById(R.id.etEditorCategory)
        etEditorDescription = findViewById(R.id.etEditorDescription)
        etEditorTime= findViewById(R.id.etEditorTime)
    }

    /**
     * the variables stored in the UserInfo
     * will be shown in the editTexts²
     */
    private fun writeData() {
        etEditorCategory.setText(UserInfo.category)
        etEditorDescription.setText(UserInfo.description)
        etEditorTime.setText(UserInfo.time)

    }

    /**
     *     When Button Update is clicked,
     *     Activity Home is opened
     */
    fun onClickBtnEditorUpdate(view: View) {
        val id = UserInfo.idExercise.toString()
        val category = etEditorCategory.text.toString()
        val description = etEditorDescription.text.toString()
        val time = etEditorTime.text.toString()

        validateForm()
        updateUser(id,category,description,time)
    }

    private fun updateUser(id:String?,category: String?,description: String?,time:String?) {
        if (category.toString().isNotEmpty() && time.toString().isNotEmpty() &&
            description.toString().isNotEmpty() && time.toString().validator().maxLength(3).check()) {
            val url =
                "https://www.ralphcuizon.be/hoopertracker/update_exercise.php"
            val queue = Volley.newRequestQueue(this)
            val stringRequest = object : StringRequest(Request.Method.POST, url, { response ->
                Toast.makeText(this, "Exercise has been updated", Toast.LENGTH_LONG).show()

                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)

            }, { error ->
                Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
            }) {
                override fun getParams(): HashMap<String, String> {
                    val map = HashMap<String, String>()
                    map["id"] = id!!
                    map["category"] = category!!
                    map["time"] = time!!
                    map["description"] = description!!

                    return map
                }
            }
            queue.add(stringRequest)
        }
    }

    private fun validateForm() {
        val etCategory = findViewById<EditText>(R.id.etEditorCategory)
        val etTime = findViewById<EditText>(R.id.etEditorTime)
        val etDescription = findViewById<EditText>(R.id.etEditorDescription)
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

    /**
     *     When Button Delete is clicked,
     *     Activity Home is opened
     */
    fun onClickBtnEditorDelete(view: View) {

    }
}