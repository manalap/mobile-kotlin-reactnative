package com.example.buildfuilduiwithlayouteditor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    var firstnames = arrayOf("Rena","Kaisa","Veera","Matti","Aino","Matti")
    var lastnames = arrayOf("Pekkonen","Mikkonen","Hentunen","Lappinen","Vaara","Lehtonen")
    var jobtitles = arrayOf("Distros","Programmer","Engineer","Accounter","Boss","Assistant")

    fun showEmployeeData(index:Int){
        firstNameTextView.text = firstnames[index]
        lastNameTextView.text = lastnames[index]
        jobTitleView.text = jobtitles[index]
        informationTextView.text = lastnames[index] + " " + firstnames[index] + " is ....." + jobtitles[index]

        var id = 0
        if(index==0) id = R.drawable.employee1
        else if (index==1) id = R.drawable.employee2
        else if (index==2) id = R.drawable.employee3
        else if (index==3) id = R.drawable.employee4
        else if (index==4) id = R.drawable.employee5
        imageView2.setImageResource(id)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            showEmployeeData(0)
            textView_1.setOnClickListener{ view -> showEmployeeData(1) }
            textView_2.setOnClickListener{ view -> showEmployeeData(2) }
            textView_3.setOnClickListener{ view -> showEmployeeData(3) }
            textView_4.setOnClickListener{ view -> showEmployeeData(4) }
            textView_5.setOnClickListener{ view -> showEmployeeData(5) }


    }

}
