package com.example.project6

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewParent
import android.widget.GridLayout
import android.widget.ScrollView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.project6.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var total: Double = 0.0
    private lateinit var scrollView: ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)

        scrollView = findViewById(R.id.scroll_view)


        binding.appBarMain.root.bringToFront()
    }

    override fun onResume() {
        super.onResume()
        ServerTaskSelect(this).start()
    }


    fun onCandiesLoaded(list: ArrayList<Candy>) {
        candies = list

        val gridLayout = GridLayout(this)
        gridLayout.columnCount = 2

        val density = resources.displayMetrics.density
        val padding = (8 * density).toInt()
        gridLayout.setPadding(padding, padding, padding, padding)

        val fmt = NumberFormat.getCurrencyInstance()

        for (candy in candies) {
            val button = CandyButton(this, candy)
            button.setOnClickListener {
                total += button.getPrice()
                val msg = "Total: ${fmt.format(total)}"
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            }
            gridLayout.addView(button)
        }

        scrollView.removeAllViews()
        scrollView.addView(gridLayout)
    }


    fun findInfo() {
        val parent: ViewParent = scrollView.parent
        Log.w("MainActivity", "parent is $parent") // DrawerLayout

        val drawerLayout: DrawerLayout = parent as DrawerLayout
        Log.w("MainActivity", "drawerLayout is $drawerLayout") // DrawerLayout

        val count: Int = drawerLayout.childCount
        Log.w("MainActivity", "child count is $count") // 2

        for (i in 0 until count) {
            val view: View = drawerLayout.getChildAt(i)
            Log.w("MainActivity", "view at $i is $view")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_reset -> {
                total = 0.0
                val fmt = NumberFormat.getCurrencyInstance()
                Toast.makeText(this, "Total: ${fmt.format(total)}", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.action_insert -> {
                val intent = Intent(this, InsertActivity::class.java)
                startActivity(intent)
                true
            }

            R.id.action_delete -> {
                val intent = Intent(this, DeleteActivity::class.java)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val URL_ALL: String =
            "http://cmsc436-2301.cs.umd.edu/cmsc436/project6/selectAll.php"
        const val URL_DELETE: String =
            "http://cmsc436-2301.cs.umd.edu/cmsc436/project6/delete.php"
        const val URL_INSERT: String =
            "http://cmsc436-2301.cs.umd.edu/cmsc436/project6/insert.php"

        var candies: ArrayList<Candy> = ArrayList()
    }
}
