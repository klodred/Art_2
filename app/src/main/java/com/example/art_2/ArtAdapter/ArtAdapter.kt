package com.example.art_2.ArtAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.art_2.R
import com.example.art_2.data.ArtObj
import com.example.art_2.databinding.ArtItemBinding
import com.squareup.picasso.Picasso

class ArtAdapter(private val listArt: List<ArtObj>, private val onItemClick: (position: Int) -> Unit): RecyclerView.Adapter<ArtAdapter.ArtHolder>() {

    class ArtHolder(view: View, private val onItemClick: (position: Int) -> Unit): RecyclerView.ViewHolder(view)  {
        val binding = ArtItemBinding.bind(view)

        fun bind(art: ArtObj) = with(binding){
            name.text=art.name
            Picasso.get().load(art.urlSmallImage).into(image)
        }

        init {
            itemView.setOnClickListener({ _ ->
                onItemClick(absoluteAdapterPosition) })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.art_item, parent, false)
        return ArtHolder(itemView, onItemClick)
    }

    override fun onBindViewHolder(holder: ArtHolder, position: Int) {
        holder.bind(listArt[position])
    }

    override fun getItemCount(): Int {
        return listArt.size
    }
}