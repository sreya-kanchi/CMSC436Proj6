package com.example.project6

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewParent
import android.widget.FrameLayout
import android.widget.GridLayout
import android.widget.RelativeLayout
import android.widget.ScrollView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.project6.databinding.ActivityMainBinding

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var buttonWidth : Int = 0
    private var total : Double = 0.0
    private lateinit var scrollView : ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)


        scrollView = // need to find it

    }

    // Should help figre out how to bring the View containing the menu to the front
    // so that the user can click on the menu and choose a menu item
    fun findInfo( ) {
        // obviously, scrollView should not be null and should have been added to the View
        var parent : ViewParent = scrollView.parent
        Log.w( "MainActivity", "parent is " + parent ) // DrawerLayout

        var drawerLayout : DrawerLayout = parent as DrawerLayout
        Log.w( "MainActivity", "drawerLayout is " + drawerLayout ) // DrawerLayout

        var count : Int = drawerLayout.childCount
        Log.w( "MainActivity", "child count is " + count ) // 2


        for ( i in 0..count - 1) {
            var view : View = drawerLayout.getChildAt( i )
            Log.w( "MainActivity", "view at " + i + " is " + view )
        }
        // Be careful above: Views are not guaranteed to always come out in the same order !!!!!


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {



    }

    companion object {
        const val URL_ALL : String = "http://cmsc436-2301.cs.umd.edu/cmsc436/project6/selectAll.php"
        const val URL_DELETE : String = "http://cmsc436-2301.cs.umd.edu/cmsc436/project6/delete.php"
        const val URL_INSERT : String = "http://cmsc436-2301.cs.umd.edu/cmsc436/project6/insert.php"
        var candies : ArrayList<Candy> = ArrayList<Candy>( )
    }
}