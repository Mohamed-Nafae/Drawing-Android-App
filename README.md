# Draw App BM

A modern, intuitive drawing application for Android built using **Jetpack Compose** and **Material 3**. This app provides a smooth drawing experience with customizable brushes and colors.

## 📸 Screenshots

<p align="center">
  <img src="https://storage.googleapis.com/content-studio-63c6c.appspot.com/users/msi/chats/2860456570535123062/7c97858c-a0e2-45e0-82d3-1b91834278e9.jpg" width="22%" />
  <img src="https://storage.googleapis.com/content-studio-63c6c.appspot.com/users/msi/chats/2860456570535123062/2275eb1e-f3b1-4191-807e-13c51df779f7.jpg" width="22%" />
  <img src="https://storage.googleapis.com/content-studio-63c6c.appspot.com/users/msi/chats/2860456570535123062/cc95b135-c088-4687-b939-da7c8651a141.jpg" width="22%" />
  <img src="https://storage.googleapis.com/content-studio-63c6c.appspot.com/users/msi/chats/2860456570535123062/0f7e4c5b-4395-4608-8e8e-9669e46a782b.jpg" width="22%" />
</p>

## ✨ Features

- **Smooth Freehand Drawing:** High-performance canvas with quadratic curve smoothing for a natural feel.
- **Adjustable Brush Size:** Easily change the thickness of your strokes using a slider with a real-time preview circle.
- **Vibrant Color Palette:** Choose from multiple colors (Black, Red, Blue, Green, Yellow, Gray, Magenta, Cyan) to bring your creations to life.
- **Undo Functionality:** Made a mistake? Quickly undo your last stroke.
- **Clear Canvas:** Reset your workspace instantly with the "Clear Painting" button.
- **Modern UI:** Built with Material 3 components for a clean and responsive look.

## 🛠 Tech Stack

- **Language:** [Kotlin](https://kotlinlang.org/)
- **UI Framework:** [Jetpack Compose](https://developer.android.com/jetpack/compose)
- **Architecture:** MVVM (Model-View-ViewModel)
- **State Management:** StateFlow & Lifecycle-aware components
- **Design System:** Material 3

## 🚀 Getting Started

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/yourusername/DrawAppBM.git
    ```
2.  **Open in Android Studio:**
    - Use Android Studio Ladybug or newer.
3.  **Build and Run:**
    - Connect an Android device or start an emulator.
    - Click the **Run** button in Android Studio.

## 📂 Project Structure

- `MainActivity.kt`: The main entry point and UI layout.
- `DrawingCanvas.kt`: The custom drawing component handling touch events and rendering.
- `DrawingViewModel.kt`: Manages the application state and drawing logic.
- `ui.theme/`: Contains the theme, color, and typography definitions.

---
Developed by BM.
