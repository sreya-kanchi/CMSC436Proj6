package com.example.project6

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RelativeLayout
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity

class DeleteActivity : AppCompatActivity() {

    private lateinit var radioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        val rootLayout = RelativeLayout(this)
        setContentView(rootLayout)

        val density = resources.displayMetrics.density
        val topMargin = (40 * density).toInt()
        val bottomMargin = (40 * density).toInt()

        val scrollView = ScrollView(this)
        radioGroup = RadioGroup(this)
        radioGroup.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        scrollView.addView(radioGroup)

        val scrollParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        scrollParams.addRule(RelativeLayout.ALIGN_PARENT_TOP)
        scrollParams.setMargins(0, topMargin, 0, bottomMargin + (60 * density).toInt())
        rootLayout.addView(scrollView, scrollParams)

        val backButton = Button(this)
        backButton.text = "BACK"
        val backParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        backParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        backParams.addRule(RelativeLayout.CENTER_HORIZONTAL)
        backParams.setMargins(0, 0, 0, bottomMargin)
        rootLayout.addView(backButton, backParams)

        backButton.setOnClickListener {
            finish()
        }

        populateRadioButtons()
    }

    private fun populateRadioButtons() {
        radioGroup.removeAllViews()

        for (candy in MainActivity.candies.toList()) {
            val rb = RadioButton(this)
            rb.text = candy.toStringNoId()
            rb.textSize = 18f

            rb.setOnClickListener {

                val task = ServerTaskDelete(this, candy.getId()) {
                    // Remove locally and refresh the UI.
                    MainActivity.candies.remove(candy)
                    populateRadioButtons()
                }
                task.start()
            }

            radioGroup.addView(rb)
        }
    }
}
