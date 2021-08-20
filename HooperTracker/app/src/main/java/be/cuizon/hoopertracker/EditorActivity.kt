package be.cuizon.hoopertracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class EditorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)


        findViewById<EditText>(R.id.etEditorCategory).setText(UserInfo.category)
        findViewById<EditText>(R.id.etEditorDescription).setText(UserInfo.description)
        findViewById<EditText>(R.id.etEditorTime).setText(UserInfo.time)


    }

    /**
     *     When Button Update is clicked,
     *     Activity Home is opened
     */
    fun onClickBtnEditorUpdate(view: View) {

    }

    /**
     *     When Button Delete is clicked,
     *     Activity Home is opened
     */
    fun onClickBtnEditorDelete(view: View) {

    }
}