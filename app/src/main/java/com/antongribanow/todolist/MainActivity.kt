package com.antongribanow.todolist

import TaskAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    private lateinit var taskAdapter: TaskAdapter
    private val tasks = mutableListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        taskAdapter = TaskAdapter(
            tasks,
            onTaskClickListener = { task -> showTaskDetailsDialog(task) },
            onTaskLongClickListener = { task -> showDeleteTaskDialog(task) },
            onTaskCheckedListener = { task, isChecked -> task.isCompleted = isChecked }
        )
        recyclerView.adapter = taskAdapter

        val fabAddTask = findViewById<FloatingActionButton>(R.id.fab_add_task)

        fabAddTask.setOnClickListener { showAddTaskDialog() }
    }

     fun showAddTaskDialog() {
        val taskDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_task, null)
        val taskTitleEditText = taskDialogView.findViewById<EditText>(R.id.edit_text_title)
        val taskDescriptionEditText = taskDialogView.findViewById<EditText>(R.id.edit_text_description)
        val taskImageView = taskDialogView.findViewById<ImageView>(R.id.image_view)

        // W tym miejscu można dodać obsługę wyboru i przypisywania obrazka do ImageView, jeśli jest to wymagane.

        val dialog = AlertDialog.Builder(this)
            .setTitle("Add Task")
            .setView(taskDialogView)
            .setPositiveButton("Add") { _: DialogInterface, _: Int -> }
            .setNegativeButton("Cancel", null)
            .create()

        dialog.setOnShowListener {
            val addButton = (it as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE)
            addButton.setOnClickListener {
                val title = taskTitleEditText.text.toString().trim()
                val description = taskDescriptionEditText.text.toString().trim()

                if (title.isNotEmpty()) {
                    tasks.add(Task(title = title, description = description))
                    tasks.sortBy { it.title }
                    taskAdapter.notifyDataSetChanged()
                    dialog.dismiss()
                } else {
                    taskTitleEditText.error = "Please enter a title"
                }
            }
        }

        dialog.show()
    }

   private  fun showTaskDetailsDialog(task: Task) {
        // W tym miejscu można zaimplementować obsługę wyświetlania szczegółów zadania
         fun showTaskDetailsDialog(task: Task) {
            val intent = Intent(this, TaskDetailActivity::class.java)
            intent.putExtra("task_title", task.title)
            intent.putExtra("task_description", task.description)
            // Jeśli zadanie ma obraz, dodaj też informację o obrazie.
            startActivity(intent)
        }

    }

    private fun showDeleteTaskDialog(task: Task) {
        val dialog = AlertDialog.Builder(this)
            .setTitle("Delete Task")
            .setMessage("Are you sure you want to delete this task?")
            .setPositiveButton("Yes") { _: DialogInterface, _: Int ->
                tasks.remove(task)
                taskAdapter.notifyDataSetChanged()
            }
            .setNegativeButton("No", null)
            .create()

        dialog.show()
    }
}
