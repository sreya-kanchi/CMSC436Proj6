package com.example.project6

import android.content.Context
import android.view.Gravity
import android.widget.GridLayout
import androidx.appcompat.widget.AppCompatButton
import java.text.NumberFormat

class CandyButton(context: Context, private val candy: Candy) : AppCompatButton(context) {

    init {
        val fmt = NumberFormat.getCurrencyInstance()

        text = "${candy.getName()}\n${fmt.format(candy.getPrice())}"

        textSize = 16f
        setAllCaps(false)
        gravity = Gravity.CENTER

        val params = GridLayout.LayoutParams()
        params.width = 0
        params.height = GridLayout.LayoutParams.WRAP_CONTENT
        params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
        layoutParams = params
    }

    fun getPrice(): Double {
        return candy.getPrice()
    }

    fun getId(): Int {
        return candy.getId()
    }
}
