package com.example.lembretedetarefas.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lembretedetarefas.R
import com.example.lembretedetarefas.databinding.ActivityMainBinding
import com.example.lembretedetarefas.databinding.ItemTaskBinding
import com.example.lembretedetarefas.model.Task

class TaskListAdapter : ListAdapter<Task, TaskListAdapter.TaskViewHolder>(DiffCallback()){

    var listenerEdit:(Task)->Unit={}
    var listenerDelete:(Task)->Unit={}

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int):TaskViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTaskBinding.inflate(inflater, parent, false)
        return TaskViewHolder(binding)
    }
    override fun onBindViewHolder(holder:TaskViewHolder,position:Int){
        holder.bind(getItem(position))
    }

    fun submitList(list: Any) {
        TODO("Not yet implemented")
    }

    inner class TaskViewHolder(private val binding
    : ItemTaskBinding):RecyclerView.ViewHolder(binding.root)
    {
        fun bind(item:Task){
            binding.tvTitle.text = item.title
            binding.tvDate.text="${item.date} ${item.hour}"
            binding.ivMore.setOnClickListener{
                showPopup(item)
            }
        }
        private fun showPopup(){
            val ivMore = binding.ivMore
            val popupMenu = PopupMenu(ivMore.context, ivMore)
            popupMenu.menuInflater.inflate(R.menu.popup_menu,popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.action_edit->listenerEdit(item)
                    R.id.action_delete->listenerDelete(item)

                }

                return@setOnMenuItemClickListener true
            }
            popupMenu.show()
        }
    }
}


class DiffCallback: DiffUtil.ItemCallback<Task>(){
    override fun areItemsTheSame(oldItem: Task, newItem: Task)=oldItem==newItem

    override fun areContentsTheSame(oldItem: Task, newItem: Task)=oldItem.id==newItem.id

}