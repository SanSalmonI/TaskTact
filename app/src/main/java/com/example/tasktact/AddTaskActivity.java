package com.example.tasktact;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {

    public static final String EXTRA_TASK_NAME = "task";
    public static final String EXTRA_TASK_COMPLETED = "completed";
    public static final String EXTRA_EDIT_INDEX = "editIndex";

    private EditText taskInput;
    private Switch completedSwitch;
    private Button saveTaskButton;

    private int editIndex = -1;  // new task

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        taskInput = findViewById(R.id.editTextTaskName);
        completedSwitch = findViewById(R.id.switchCompleted);
        saveTaskButton = findViewById(R.id.buttonSaveTask);

        // Check if editing existing task
        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra(EXTRA_TASK_NAME);
            boolean completed = intent.getBooleanExtra(EXTRA_TASK_COMPLETED, false);
            editIndex = intent.getIntExtra(EXTRA_EDIT_INDEX, -1);

            if (name != null) {
                taskInput.setText(name);
                completedSwitch.setChecked(completed);
            }
        }

        saveTaskButton.setOnClickListener(v -> {
            String taskText = taskInput.getText().toString().trim();
            boolean isCompleted = completedSwitch.isChecked();

            if (!taskText.isEmpty()) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra(EXTRA_TASK_NAME, taskText);
                resultIntent.putExtra(EXTRA_TASK_COMPLETED, isCompleted);

                if (editIndex != -1) {
                    resultIntent.putExtra(EXTRA_EDIT_INDEX, editIndex);
                }

                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            } else {
                taskInput.setError("Please enter a task");
            }
        });
    }
}
