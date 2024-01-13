# Workout Tracker App - README

## Introduction
The Workout Tracker App is an Android application developed in Kotlin, designed to help users track their workouts, exercises, and sets. It utilizes modern Android development practices, including the use of Fragments, ViewModels, LiveData, Room Database, and Kotlin Coroutines.

## Implemented Features

### Base: Project Setup and ViewModels
- **Android Studio Project Configuration:** The project is configured to target SDK version 21 (Android 5.0) and above.
- **Fragment and Navigation:** Utilizes multiple fragments (e.g., `WorkoutListFragment`, `DetailFragment`, `HistoryFragment`) with a navigation component to manage app flow.
- **ViewModels Usage:** Each fragment is associated with a ViewModel (e.g., `DetailViewModel`, `HistoryViewModel`) to handle UI data effectively.
- **SafeArgs for Argument Passing:** Arguments are passed between fragments using SafeArgs, ensuring type-safe access to data in ViewModels.

### Block 1: LiveData and Lifecycle Components
- **LiveData Integration:** `ExerciseRepository` returns LiveData objects. ViewModels then expose these LiveData objects to the fragments.
- **Lifecycle-Aware Data Observing:** Fragments observe LiveData changes using `viewLifecycleOwner` and update UI in response to data changes. For example, `PushWorkoutListFragment` observes a LiveData object from its ViewModel (`PushWorkoutListViewModel`). When the data changes (e.g., new exercises added), the fragment updates its UI accordingly.

### Block 2: SQL with Google Room
- **Room Database Setup:** The app includes a Room database (`AppDatabase`) with entities such as `Exercise` and `Set`, and DAOs (`ExerciseDao`, `SetDao`) for SQL queries.
- **Database Interaction:** The repository handles CRUD operations with the database. For instance, `ExerciseRepository` uses `exerciseDao`.

### Block 6: Concurrency with Coroutines and Flow
- **Coroutines for Asynchronous Tasks:** Kotlin coroutines facilitate long-running operations off the main thread. For example, `ExerciseRepository` contains a suspend function `performLongRunningOperation` for background tasks.
- **MainThread Queries:** Enabled for simplicity in database operations.

## Conclusion
The Workout Tracker app is a comprehensive demonstration of Android development practices, including ViewModel, LiveData, Room database, Kotlin Coroutines, and the Observer pattern, particularly exemplified in the `PushWorkoutListFragment`. The app effectively meets the needs of fitness enthusiasts looking to track their workout progress.
