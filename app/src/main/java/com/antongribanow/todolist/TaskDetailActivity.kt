package com.antongribanow.todolist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TaskDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)
        val editButton = findViewById<Button>(R.id.button_edit)
        editButton.setOnClickListener {
            val intent = Intent(this, EditTaskActivity::class.java)
            startActivity(intent)
        }

        val titleTextView = findViewById<TextView>(R.id.text_view_title)
        val descriptionTextView = findViewById<TextView>(R.id.text_view_description)
        val imageView = findViewById<ImageView>(R.id.image_view)

        titleTextView.text = intent.getStringExtra("task_title")
        descriptionTextView.text = intent.getStringExtra("task_description")

        val editTaskButton = findViewById<Button>(R.id.button_edit)
        editTaskButton.setOnClickListener {
            val intent = Intent(this, EditTaskActivity::class.java)
            intent.putExtra("task_title", titleTextView.text)
            intent.putExtra("task_description", descriptionTextView.text)
            startActivity(intent)
        }
    }
}