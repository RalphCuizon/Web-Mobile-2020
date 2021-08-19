package be.cuizon.hoopertracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val list= ArrayList<Exercise>()
        val url =
            "https://www.ralphcuizon.be/hoopertracker/get_exercises.php?user_email=" + UserInfo.email
        val queue = Volley.newRequestQueue(this)
        val jar= JsonArrayRequest(Request.Method.GET, url, null, Response.Listener { response ->
            for (x in 0..response.length()-1)
                list.add(Exercise(response.getJSONObject(x).getString("user_email"),
                    response.getJSONObject(x).getString("category"),
                    response.getJSONObject(x).getString("description"),
                    response.getJSONObject(x).getString("time")))
            var adp= ExerciseAdapter(this,list)
            var exerciseRecyclerView = findViewById<RecyclerView>(R.id.exercises_rv)
            exerciseRecyclerView.layoutManager = LinearLayoutManager(this)
            exerciseRecyclerView.adapter = adp
        }, { error ->
            Toast.makeText(this, error.message, android.widget.Toast.LENGTH_LONG).show()
        })
        queue.add(jar)
    }
}