package com.example.bitfit1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

const val ENTRY_EXTRA = "ENTRY_EXTRA"
class EntryActivity : AppCompatActivity(){

    private lateinit var nameEntryView: EditText
    private lateinit var amountEntryView: EditText
    private lateinit var submitButtonView: Button

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calories_entry)

        nameEntryView = findViewById<EditText>(R.id.enteredFoodType)
        amountEntryView = findViewById<EditText>(R.id.enteredCaloriesAmt)
        submitButtonView = findViewById<Button>(R.id.newEntryButton)


        submitButtonView.setOnClickListener {
            Log.d("EntryActivity", "submit clicked")
            val intent = Intent(this, MainActivity::class.java)
            val calories = Calories(nameEntryView.text.toString(), amountEntryView.text.toString().toLong())
            intent.putExtra(ENTRY_EXTRA, calories)
            this.startActivity(intent)
        }


    }
}