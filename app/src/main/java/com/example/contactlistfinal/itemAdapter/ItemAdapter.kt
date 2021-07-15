package com.example.contactlistfinal.itemAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactlistfinal.Contact
import com.example.contactlistfinal.databinding.ListitemBinding
import java.util.*

class ItemAdapter(private val dataset: List<Contact>) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    class ItemViewHolder(val binding: ListitemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ListitemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item: Contact = dataset[position]
        holder.binding.Name.text = item.name
        holder.binding.Number.text = item.number.toString()
        val firstLetter = item.name[0].toString().uppercase(Locale.getDefault())
        holder.binding.firstLetter.text = firstLetter
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}