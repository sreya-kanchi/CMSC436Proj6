package com.example.project6

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class InsertActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var priceEditText: EditText
    private lateinit var addButton: Button
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        // No app bar on this screen.
        supportActionBar?.hide()

        nameEditText = findViewById(R.id.candy_name)
        priceEditText = findViewById(R.id.candy_price)
        addButton = findViewById(R.id.add)
        backButton = findViewById(R.id.back)

        addButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val priceText = priceEditText.text.toString().trim()

            if (name.isEmpty()) {
                return@setOnClickListener
            }

            val price = priceText.toDoubleOrNull() ?: return@setOnClickListener


            ServerTaskInsert(this, name, price).start()
        }

        backButton.setOnClickListener {
            finish()
        }
    }
}
