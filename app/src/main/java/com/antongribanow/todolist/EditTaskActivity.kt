package com.antongribanow.todolist

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class EditTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_task)
        val titleEditText = findViewById<EditText>(R.id.edit_text_title)
        val descriptionEditText = findViewById<EditText>(R.id.edit_text_description)
        val imageView = findViewById<ImageView>(R.id.image_view)

        val saveButton = findViewById<Button>(R.id.button_save)
        saveButton.setOnClickListener {
            // Zaktualizuj zadanie i zapisz zmiany
            // Jeśli to nowe zadanie, dodaj je do listy zadań w MainActivity
            finish()
        }
    }
}