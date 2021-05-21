package com.example.roomshopinglist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.dialog_ask_new_shopping_list_item.view.*
import kotlinx.android.synthetic.main.recyclerview_item.view.*
import kotlinx.android.synthetic.main.recyclerview_item.view.countTextView
import kotlinx.android.synthetic.main.recyclerview_item.view.nameTextView

class ShoppingListAdapter (var shoppingList: MutableList<ShoppingListItem>)
    : RecyclerView.Adapter<ShoppingListAdapter.ViewHolder>() {

    // create ui view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    // return size
    override fun getItemCount(): Int = shoppingList.size

    // bind data
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // item to bind
        val item: ShoppingListItem = shoppingList[position]

        // name, count, price
        holder.nameTextView.text = item.name
        holder.countTextView.text = item.count.toString()
        holder.priceTextView.text = item.price.toString()
    }

    // view holder
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.nameTextView
        val countTextView: TextView = view.countTextView
        val priceTextView: TextView = view.priceTextView
    }

    // update data
    fun update(newList: MutableList<ShoppingListItem>) {
        shoppingList = newList
        notifyDataSetChanged()
    }
}
