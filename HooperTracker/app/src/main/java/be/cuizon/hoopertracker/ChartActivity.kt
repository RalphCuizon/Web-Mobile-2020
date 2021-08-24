package be.cuizon.hoopertracker

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate
import org.json.JSONArray
import org.json.JSONObject

class ChartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)

        val url =
            "https://www.ralphcuizon.be/hoopertracker/get_exercises.php?email=" + "ralph@test.com"
        val queue = Volley.newRequestQueue(this)
        val sr = JsonArrayRequest(Request.Method.GET, url,null, { response ->

            val list = ArrayList<String>()
            val stime  = Float
            for (x in 0..response.length()-1) {
                if (response.getJSONObject(x).getString("category") == "shooting") {

                }
            }


        }, { error ->
            Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
        })
        queue.add(sr)

        val pieChart = findViewById<PieChart>(R.id.pieChart)

        val plist =  ArrayList<PieEntry>()
        plist.add(PieEntry( 60f, "Shooting"))
        plist.add(PieEntry(90f,"Defense"))
        plist.add(PieEntry(30f,"System"))

        val colors = ArrayList<Int>()
        colors.add(Color.RED)
        colors.add(Color.BLUE)
        colors.add(Color.GREEN)
        colors.add(Color.YELLOW)
        colors.add(Color.CYAN)


        val piedataset = PieDataSet(plist, "Category")
        piedataset.colors = colors
        piedataset.valueTextSize = 20f

        val piedata = PieData(piedataset)


        pieChart.data = piedata
        pieChart.invalidate()

    }
}