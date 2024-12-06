package com.example.fragment

import android.content.Context
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import com.example.fragment.databinding.EventItemBinding
import com.example.fragment.password.PassItem

class PassItemViewHolder(
    private val context: Context,
    private val binding: EventItemBinding,
    private val clickListener: PassItemListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bindPassItem(passItem: PassItem){
        binding.eventName.text = passItem.name
        binding.eventTime.text = passItem.password

        //добавить кнопки
        /*
        binding.completeButton.setOnClickListener {
            clickListener.completeTaskItem(taskItem)
        }

        binding.taskCellContainer.setOnClickListener{
            clickListener.editTaskItem(taskItem)
        }
        *
        * */
    }
}