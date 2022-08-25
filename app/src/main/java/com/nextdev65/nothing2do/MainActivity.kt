package com.nextdev65.nothing2do

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(mutableListOf())

        rv_TodoItems.adapter = todoAdapter
        rv_TodoItems.layoutManager = LinearLayoutManager(this)

        btn_add_Item.setOnClickListener {
            val todoTitle = et_ItemTitle.text.toString()
            if (todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                et_ItemTitle.text.clear()
            }
        }

        btn_del_CompleteItems.setOnClickListener {
            todoAdapter.deleteCompleteTodos(applicationContext)
        }
    }
}