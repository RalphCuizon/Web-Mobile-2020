package be.cuizon.hoopertracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley

class HomeActivity : AppCompatActivity(), ExerciseAdapter.OnItemClickListener{
    var list= ArrayList<Exercise>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        /**
         *With the php.file and volley library
         *we are able to connect the app with db
         * We retrieve all exercise for the specific user(email as id)
         * Every row is a exercise with parameters category etc
         * it will be stored as a JSON
         * Via the adapter we can show the parameters in the app
         */

        val url =
            "https://www.ralphcuizon.be/hoopertracker/hybrid.php?user_email=" + UserInfo.email
        val queue = Volley.newRequestQueue(this)
        val jar= JsonArrayRequest(Request.Method.GET, url, null, Response.Listener { response ->
            for (x in 0..response.length()-1)
                list.add(Exercise(response.getJSONObject(x).getInt("id"),
                response.getJSONObject(x).getString("user_email"),
                    response.getJSONObject(x).getString("category"),
                    response.getJSONObject(x).getString("description"),
                    response.getJSONObject(x).getString("time")))
            val adp= ExerciseAdapter(this,list,this)
            val exerciseRecyclerView = findViewById<RecyclerView>(R.id.exercises_rv)
            exerciseRecyclerView.layoutManager = LinearLayoutManager(this)
            exerciseRecyclerView.adapter = adp
        }, { error ->
            Toast.makeText(this, error.message, android.widget.Toast.LENGTH_LONG).show()
        })
        queue.add(jar)

    }

    /**
     *     When Button Add Exercise is clicked,
     *     Activity Add Exercise is opened
     */
    fun onClickBtnHomeAddExercise(view: View) {
        val intent = Intent(this,AddExerciseActivity::class.java)
        startActivity(intent)
    }

    override fun onItemClick(position: Int) {
        Log.i("test", list[position].id.toString())
        UserInfo.idExercise= list[position].id
        UserInfo.category= list[position].category
        UserInfo.time= list[position].time
        UserInfo.description = list[position].description
        Log.i("test",UserInfo.description)
        val intent = Intent(this,EditorActivity::class.java)
        startActivity(intent)

    }
}