package com.example.tasktact

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

data class Task(var name: String, var completed: Boolean)

class MainActivity : AppCompatActivity() {

    private val taskList = mutableListOf<Task>()

    private val addTaskLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data ?: return@registerForActivityResult

            val taskName = data.getStringExtra(AddTaskActivity.EXTRA_TASK_NAME)
            val taskCompleted = data.getBooleanExtra(AddTaskActivity.EXTRA_TASK_COMPLETED, false)
            val editIndex = data.getIntExtra(AddTaskActivity.EXTRA_EDIT_INDEX, -1)

            if (!taskName.isNullOrBlank()) {
                if (editIndex != -1 && editIndex < taskList.size) {
                    // Editing existing task
                    taskList[editIndex] = Task(taskName, taskCompleted)
                    Toast.makeText(this, "Task Updated", Toast.LENGTH_SHORT).show()
                } else {
                    // New task
                    taskList.add(Task(taskName, taskCompleted))
                    Toast.makeText(this, "Task Added", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.TaskListview)
        val addTaskButton = findViewById<Button>(R.id.buttonOpenAddTaskScreen)

        recyclerView.layoutManager = LinearLayoutManager(this)

        addTaskButton.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            addTaskLauncher.launch(intent)
        }
    }

    private fun onTaskClicked(position: Int) {
        val task = taskList[position]
        val intent = Intent(this, AddTaskActivity::class.java).apply {
            putExtra(AddTaskActivity.EXTRA_TASK_NAME, task.name)
            putExtra(AddTaskActivity.EXTRA_TASK_COMPLETED, task.completed)
            putExtra(AddTaskActivity.EXTRA_EDIT_INDEX, position)
        }
        addTaskLauncher.launch(intent)
    }
}
