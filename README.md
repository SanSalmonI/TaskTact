md
# Transfer - Android Task App

A basic Android application for creating and managing simple tasks. This project demonstrates core Android concepts like Activities, UI with XML, and data handling with JSON.

## Core Features

*   **Add Tasks:** Create new tasks with a name and a completed status.
*   **Data Saving:** Tasks (within groups) are saved to a JSON file in the app's local storage.

## How to Run

1.  **Clone the repository.**
2.  **Open the project in Android Studio.**
3.  **Build and run** on an Android emulator or device.

## Main Components

*   **`MainActivity.kt`**: Main screen, launches task creation, and handles saving/loading of task data.
*   **`AddTaskActivity.kt`**: Screen for entering new task details.
*   **`Task.kt` / `Group.kt`**: Data classes for tasks and groups.
*   **Layouts (`res/layout/`)**:
    *   `activity_main.xml`
    *   `activity_add_task.xml`

## Technology Used

*   Kotlin
*   Android SDK
*   XML for layouts
*   Kotlinx Serialization (for JSON)

## Current Status

*   Basic task creation and saving implemented.
*   **TODO:**
    *   Display tasks in `MainActivity`.
    *   Load tasks from storage on app start.
    *   Implement group management.
    *   Task editing and deletion.

---

_This is a learning project to explore Android development._