package be.cuizon.hoopertracker

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

       val url =
            "https://www.ralphcuizon.be/hoopertracker/get_user.php?email=" + UserInfo.email
        val queue = Volley.newRequestQueue(this)
        val sr =StringRequest(Request.Method.GET, url, { response ->

            val jsonArray = JSONArray(response)
            val jsonObject: JSONObject = jsonArray.getJSONObject(0)
            val email= jsonObject.get("email").toString()
            val firstname= jsonObject.get("firstname").toString()
            val lastname= jsonObject.get("lastname").toString()
            findViewById<TextView>(R.id.tvProfileEmail).text = email
            findViewById<TextView>(R.id.tvProfileFirstName).text = firstname
            findViewById<TextView>(R.id.tvProfileLastName).text = lastname


        }, { error ->
            Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
        })
        queue.add(sr)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.nav_drawer, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_exercises -> {
                val intent = Intent(this,HomeActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_profile  -> {
                return true
            }
            R.id.action_logout  -> {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Log Out")
                builder.setMessage("Are you sure want to logout?")
                builder.setPositiveButton("Yes",
                    DialogInterface.OnClickListener { dialog: DialogInterface, which: Int ->
                    this.finishAffinity()
                    System.exit(0)

                })
                builder.setNegativeButton("No",
                    DialogInterface.OnClickListener { dialog: DialogInterface, which: Int ->

                })
                builder.show()

                return true
            }


        }
        return super.onOptionsItemSelected(item)
    }
}