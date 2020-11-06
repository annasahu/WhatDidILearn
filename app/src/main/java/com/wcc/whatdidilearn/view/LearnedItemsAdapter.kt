package com.wcc.whatdidilearn.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wcc.whatdidilearn.R
import com.wcc.whatdidilearn.entities.LearnedItem

class LearnedItemsAdapter: RecyclerView.Adapter<LearnedItemsAdapter.LearnedItemViewHolder>() {
    var data = listOf<LearnedItem>()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    inner class LearnedItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val itemTitle: TextView = itemView.findViewById(R.id.itemTitle)
        val itemDescription: TextView = itemView.findViewById(R.id.itemDescription)
        val itemLevel: View = itemView.findViewById(R.id.itemLevel)

        fun bind(title: String, description: String, color: Int) {
            itemTitle.text = title
            itemDescription.text = description
            itemLevel.setBackgroundResource(color)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LearnedItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.item_learned, parent, false) as View

        return LearnedItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LearnedItemViewHolder, position: Int) {
        val itemLearnedToShow: LearnedItem = data.get(position)
        holder.bind(itemLearnedToShow.title, itemLearnedToShow.description, itemLearnedToShow.understandingLevel.color)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun getRemoveItemPosition() : Int {
        var position = removedPosition
        return position;
    }

}