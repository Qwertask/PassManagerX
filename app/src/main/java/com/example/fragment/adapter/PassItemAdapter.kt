package com.example.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fragment.PassItemListener
import com.example.fragment.PassItemViewHolder
import com.example.fragment.databinding.EventItemBinding
import com.example.fragment.password.PassItem

class PassItemAdapter(
    private val passItems:List<PassItem>,
    private val clickListener: PassItemListener
    ) :RecyclerView.Adapter<PassItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PassItemViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = EventItemBinding.inflate(from, parent, false)
        return PassItemViewHolder(parent.context, binding, clickListener)
    }

    override fun getItemCount(): Int = passItems.size

    override fun onBindViewHolder(holder: PassItemViewHolder, position: Int) {
        holder.bindPassItem(passItems[position])
    }


}