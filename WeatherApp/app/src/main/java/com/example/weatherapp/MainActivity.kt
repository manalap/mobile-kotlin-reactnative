package com.example.weatherapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapp.ui.main.SectionsPagerAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    val API_LINK: String = "https://api.openweathermap.org/data/2.5/weather?q="
    val API_ICON: String = "https://openweathermap.org/img/w/"
    val API_KEY: String = "25b247c1bfa9646c7951b6a6a1f64dac"

    val cities: MutableList<String> = mutableListOf("Jyväskylä","Helsinki","Los Angeles","Tehran")
    var index: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //load weather
        loadWeatherForecast(cities[index])



    }

    companion object{
        var forecasts: MutableList<Forecast> = mutableListOf()
    }

    private fun setUI() {
        // hide progress bar
        progressBar.visibility = View.INVISIBLE
        // add adapber
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        // add fab
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "How you can add and save a new city?", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }


    //load forecast
    private fun loadWeatherForecast(city:String) {
        //url
        val url = "$API_LINK$city&APPID=$API_KEY&units=metric&lang=fi"
        //json object request with volley
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url,null, Response.Listener<JSONObject> {
            response ->
             try {
                 //load ok - parse data from loaded json
                 //add parse code here..

                 val  mainJSONObject = response.getJSONObject("main")
                 val weatherArray = response.getJSONArray("weather")
                 val firstWeatherObject = weatherArray.getJSONObject(0)

                 //city, condition, temperature
                 val city = response.getString("name")
                 val condition = firstWeatherObject.getString("main")
                 val temperature = mainJSONObject.getString("temp") +" C"

                 val weatherTime: String = response.getString("dt")
                 val weatherLong: Long = weatherTime.toLong()
                 val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.YYYY HH:mm:SS")
                 val dt = Instant.ofEpochSecond(weatherLong).atZone(ZoneId.systemDefault()).toLocalDateTime().format(formatter).toString()
                 //icon
                 val weatherIcon = firstWeatherObject.getString("icon")
                 val url = "$API_ICON$weatherIcon.png"
                 //add forecast object to list
                 forecasts.add(Forecast(city,condition,temperature,dt,url))
                 //use logcat window to check ok
                 Log.d("WEATHER","*** weatherCity = "+ forecasts[index].city)
                 if ((++index) < cities.size) loadWeatherForecast(cities[index])
                 else {
                     Log.d("WEATHER","*** ALL LOADED!")
                 }

                 setUI()



             } catch (e: Exception) {
                 e.printStackTrace()
                 Log.d("WEATHER", "**** error: $e")
                 //hide progress bar
                 progressBar.visibility = View.INVISIBLE
                 //show Toast ->
                 Toast.makeText(this, "Erros loading weather forecast",Toast.LENGTH_LONG).show()
             }
        },Response.ErrorListener {  }  )
            val queue = Volley.newRequestQueue(applicationContext)
        queue.add(jsonObjectRequest)



        //start loading data with Volley

    }


}