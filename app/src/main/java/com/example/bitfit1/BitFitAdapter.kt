package com.example.bitfit1

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class BitFitAdapter (private val context: Context, private val calories: MutableList<Calories>): RecyclerView.Adapter<BitFitAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        private val itemType = itemView.findViewById<TextView>(R.id.displayed_item_name)
        private val calories_amount = itemView.findViewById<TextView>(R.id.displayed_calories_amt)

        fun bind(calories: Calories){
            itemType.text = calories.name
            calories_amount.text = calories.amount.toString()
        }

        init{
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            Log.i("BoxClicked", "The onClick works for Box $position")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_calories, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val calories = calories[position]
        holder.bind(calories)
    }

    override fun getItemCount(): Int {
        return calories.size
    }


}