package com.example.golfcoursewishlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.row_places.view.*

class GolfCourseWishListAdapter(private val places: ArrayList<Place>)
    : RecyclerView.Adapter<GolfCourseWishListAdapter.ViewHolder>(){

    //View Holder class to hold UI views
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
       val  nameTextView: TextView = view.placeName
        val imageView: ImageView = view.placeImage
    }
    override fun onCreateViewHolder(parent: ViewGroup,viewType:Int) : GolfCourseWishListAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.row_places,parent,false)
        return ViewHolder(view)
    }




    //bind data
    override fun onBindViewHolder(holder:GolfCourseWishListAdapter.ViewHolder,position:Int) {
        //bind UI
        val place: Place = places.get(position)
        //name
        holder.nameTextView.text = place.name
        //image
        Glide.with(holder.imageView.context).load(place.getImageResourceId(holder.imageView.context)).into(holder.imageView)
    }

    //return item count
    override fun getItemCount(): Int {
        return places.size
    }
}