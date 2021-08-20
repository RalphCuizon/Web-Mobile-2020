package be.cuizon.hoopertracker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ExerciseAdapter(var context:Context,var list: ArrayList<Exercise>,var listener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v:View=LayoutInflater.from(context).inflate(R.layout.exercise_row,parent, false)
        return ExerciseHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ExerciseHolder).bind(list[position].category,list[position].description,list[position].time)

    }

    override fun getItemCount(): Int {
        return list.size
    }

 inner class ExerciseHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView),View.OnClickListener {
     fun bind(category: String, description: String, time: String) {
         itemView.findViewById<TextView>(R.id.tvCategory).text = category
         itemView.findViewById<TextView>(R.id.tvDescription).text = description
         itemView.findViewById<TextView>(R.id.tvTime).text = time
     }
     init {
         itemView.setOnClickListener(this)
     }

     override fun onClick(p0: View?) {
         val position: Int = adapterPosition
         if (position!= RecyclerView.NO_POSITION) {
             listener.onItemClick(position)
         }
     }
 }
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}