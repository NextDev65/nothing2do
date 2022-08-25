package com.nextdev65.nothing2do

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter(
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    fun addTodo(todo: Todo)	{
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun deleteCompleteTodos(applicationContext: Context) {
        if (todos.removeAll { todo -> todo.isChecked })
            notifyDataSetChanged()
        else
            Toast.makeText(applicationContext, "Select a Todo first!", Toast.LENGTH_SHORT).show()
    }

    private fun toggleStrikeThrough(tv_ItemTitle: TextView, isChecked: Boolean) {
        if (isChecked)
            tv_ItemTitle.paintFlags = tv_ItemTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        else
            tv_ItemTitle.paintFlags = tv_ItemTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.itemView.apply {
            tv_ItemTitle.text = curTodo.todo_title
            cb_ItemComplete.isChecked = curTodo.isChecked
            toggleStrikeThrough(tv_ItemTitle, curTodo.isChecked)
            cb_ItemComplete.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tv_ItemTitle, isChecked)
                curTodo.isChecked = !curTodo.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}