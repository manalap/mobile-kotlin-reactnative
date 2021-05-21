package com.example.employeesapp

import android.app.DownloadManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        //use linearManager as layout
        recyclerView.layoutManager = LinearLayoutManager(this)
        
        //RequestQueue
        val queue = Volley.newRequestQueue(this)
        //url to json data
        val url = "https://ptm.fi/data/android_employees.json"
        //request and listener
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,url,null,
        Response.Listener { response ->
            //Get employees json
            val employees = response.getJSONArray("employees")
            Log.d("JSON", employees.toString())
            recyclerView.adapter = EmployeesAdapter(employees)
        },
        Response.ErrorListener { error ->
            Log.d("JSON",error.toString())
         }
        )
        //add request to RequestQueue
        queue.add(jsonObjectRequest)
    }
}
