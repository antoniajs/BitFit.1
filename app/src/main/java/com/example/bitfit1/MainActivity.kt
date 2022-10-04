package com.example.bitfit1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val calories = mutableListOf<Calories>()
    private lateinit var rvFeed: RecyclerView

    private lateinit var addCaloriesButtonView: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //grab the recycler view
        rvFeed = findViewById<RecyclerView>(R.id.rvFeed)


        //grab the adapter
        val bitFitAdapter = BitFitAdapter(this, calories)

        //set up the recycler view
        rvFeed.adapter = bitFitAdapter
        rvFeed.layoutManager = LinearLayoutManager(this )

        //using Flow, update the recycler view whenever the db is updated
        //taken from the provided lab 5 code
        lifecycleScope.launch {
            (application as BitFitApplication).db.caloriesDao().getAll().collect{ databaseList ->
                databaseList.map { entity -> 
                    Calories(
                        entity.name,
                        entity.amount
                    )
                }.also {mappedList ->
                    calories.clear()
                    calories.addAll(mappedList)
                    bitFitAdapter.notifyDataSetChanged()
                }
            }
        }
        
        val calorie = intent.getSerializableExtra("ENTRY_EXTRA")
        //check if the EXTRA exists:
        if (calorie != null) {
            Log.d("ListActivity", "got an extra")
            Log.d("ListActivity", (calorie as Calories).toString())
            //since there is an EXTRA, let's add it to the database
            lifecycleScope.launch(Dispatchers.IO) {
                (application as BitFitApplication).db.caloriesDao().insert(
                    CaloriesEntity(
                        name = calorie.name,
                        amount = calorie.amount
                    )
                )
            }
        }
        else{
            //no EXTRA, so we don't need to do anything
            Log.d("ListActivity", "no extra")
        }

        addCaloriesButtonView = findViewById<Button>(R.id.newItem_button)
        addCaloriesButtonView.setOnClickListener {
            Log.d("ListActivity", "add new food type clicked")
            val intent = Intent(this, EntryActivity::class.java)
            this.startActivity(intent)
        }
    }
}