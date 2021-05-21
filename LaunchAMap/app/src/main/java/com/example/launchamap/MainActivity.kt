package com.example.launchamap

import android.content.Intent
import android.content.pm.ResolveInfo
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.List as List

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun showMap(view: View) {
        //get latitude
        val lat = latEditText.text.toString().toDouble()
        val lng = lonEditText.text.toString().toDouble()

        //build the intent
        val location = Uri.parse("geo:$lat,$lng")
        val mapIntent = Intent(Intent.ACTION_VIEW,location)

        //verify
        val activities: List<ResolveInfo>
        activities = packageManager.queryIntentActivities(mapIntent,0)
        val isIntentSafe: Boolean = activities.isNotEmpty()

        if(isIntentSafe) {
            startActivity(mapIntent)
        } else {
            Toast.makeText(this,"There is no activity to handle map internet!",Toast.LENGTH_LONG).show();
        }
    }
}
