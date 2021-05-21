package com.example.sumcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var number: Int = 0
    private var calcuted_sum: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun numberInput(view: View){

        var text = (view as TextView).text.toString()
        val int = text.toInt()
        result(int)
    }

    fun operator(view: View) {

        textView.append((view as Button).text.toString())
    }

    fun clear(view: View){
        textView.text = ""
    }

    fun equal(view: View){
        val result = textView.text.split("+")
        val equals = result[0].toInt() + result[1].toInt()
        textView.text = "" + equals
    }

    fun result(number: Int) {
        textView.text = "" + textView.text + number
    }

    fun addOperand(operand: String) {
        textView.text = ""+ textView.text + operand
    }



}
