package com.example.employeesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import kotlinx.android.synthetic.main.employee_item.view.*
import org.json.JSONArray
import org.json.JSONObject

class EmployeesAdapter(private  val employees: JSONArray)
    : RecyclerView.Adapter<EmployeesAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeesAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.employee_item,parent,false)
        return ViewHolder(view)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.nameTextView
        val titleTextView: TextView = view.titleTextView
        val emailTextView: TextView = view.emailTextView
        val phoneTextView: TextView = view.phoneTextView
        val departmentTextView: TextView = view.departmentTextView
        val imageView: ImageView = view.imageView
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //employee to bind
        val employee: JSONObject = employees.getJSONObject(position)
        //employee lastname firsname
        holder.nameTextView.text =
            employee["lastName"].toString()+ " " +employee["firstName"].toString()
        holder.titleTextView.text = employee["title"].toString()
        holder.emailTextView.text = employee["email"].toString()
        holder.phoneTextView.text = employee["phone"].toString()
        holder.departmentTextView.text = employee["department"].toString()

        //glide imageloader
        Glide.with(holder.imageView.context).load("https://randomuser.me/api/portraits/(wo)?{0,1}men/'\'d{1,2}.jpg").into(holder.imageView);

        }

        //return item

    override fun getItemCount(): Int = employees.length()


}