package com.example.level3_task2

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    // TODO: Extend the configuration line in onCreate() with a second parameter: rv_parts.adapter = PartAdapter(testData, { partItem : PartData -> partItemClicked(partItem) })
    // see: https://www.andreasjakl.com/recyclerview-kotlin-style-click-listener-android/, ctrl + F = Register the Click Handler Function with the Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        navController = findNavController(R.id.nav_host_fragment)

        fab.setOnClickListener {
            navController.navigate(
                R.id.action_PortalsFragment_to_AddPortalFragment
            )
        }
        fabToggler()
    }

    private fun partItemClicked(portal : Portal) {
        Toast.makeText(this, "Clicked: ${portal.title}", Toast.LENGTH_LONG).show()
    }



    private fun fabToggler() {
        navController.addOnDestinationChangedListener { _,       destination, _ ->
            if (destination.id in arrayOf(R.id.AddPortalFragment)) {
                fab.hide()
            } else {
                fab.show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}