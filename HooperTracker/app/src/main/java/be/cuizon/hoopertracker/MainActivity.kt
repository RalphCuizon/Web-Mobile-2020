package be.cuizon.hoopertracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // When Button SignUp is clicked, Sign Up page is opened
    fun onClickBtnLoginSignUp(view: View) {
        val intent = Intent(this,RegisterActivity::class.java)
        startActivity(intent)
    }
}