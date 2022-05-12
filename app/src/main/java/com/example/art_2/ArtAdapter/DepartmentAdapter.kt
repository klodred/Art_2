package com.example.art_2.ArtAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.art_2.R
import com.example.art_2.data.ArtObj
import com.example.art_2.data.Department
import com.example.art_2.data.ListDepartmentObj
import com.example.art_2.databinding.DepartmentItemBinding

class DepartmentAdapter(private val listDepartment: ListDepartmentObj,
                        private val onItemClick: (position: Int) -> Unit): RecyclerView.Adapter<DepartmentAdapter.DepartmentHolder>() {

    class DepartmentHolder(view: View, private val onItemClick: (position: Int) -> Unit): RecyclerView.ViewHolder(view)  {
        val binding = DepartmentItemBinding.bind(view)

        fun bind(art: Department) = with(binding){
            name.text=art.name
        }

        init {
            itemView.setOnClickListener({ _ ->
                onItemClick(absoluteAdapterPosition) })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartmentHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.department_item, parent, false)
        return DepartmentHolder(itemView, onItemClick)
    }

    override fun onBindViewHolder(holder: DepartmentHolder, position: Int) {
        holder.bind(listDepartment.department[position])
    }

    override fun getItemCount(): Int {
        return listDepartment.department.size
    }
}