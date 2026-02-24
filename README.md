# Q1 Settings Screen — Jetpack Compose

This project implements a simple Settings screen using Jetpack Compose (Material 3).
The purpose of this assignment is to practice layout structure, state handling, and modifier usage rather than building a complete application.

The UI is designed to look consistent and readable, similar to a real mobile settings page.

---

## Layout Structure

The main layout uses a **Column** as the container.

Each setting item is implemented as a **Row**:

- Left side: a Column containing title and supporting text
- Right side: an interactive control (Switch, Checkbox, Slider, or Button)

`Modifier.weight(1f)` is applied to the text column so that:
- Text is not truncated
- Controls stay aligned
- All rows have consistent spacing

---

## Material 3 Components Used

The screen includes multiple Material 3 components:

- Scaffold
- TopAppBar
- Card
- AssistChip
- Switch
- Checkbox
- Slider
- Button
- Divider
- ListItem
- Snackbar

These components are used to simulate a realistic settings interface.

---

## Modifier Usage

The following modifiers are demonstrated:

- padding
- fillMaxWidth
- weight
- heightIn / sizeIn
- align
- background
- border
- clip
- clickable

They control spacing, alignment, and visual appearance.

---

## Interaction Behavior

Controls update local state using `remember { mutableStateOf(...) }`.

Compose automatically recomposes the UI when state changes.

The Theme button currently shows a Snackbar message.  
The assignment focuses on layout and component usage rather than implementing full functionality.

---

## Screenshots

Top section:

<img width="452" height="902" alt="image" src="https://github.com/user-attachments/assets/1a012f67-ccab-4080-afce-db1299108545" />

<img width="830" height="1051" alt="image" src="https://github.com/user-attachments/assets/32756c4c-7853-4053-8186-417104fba841" />

Bottom section:

<img width="572" height="957" alt="image" src="https://github.com/user-attachments/assets/c0cd9aca-3b59-44b5-88ab-68c8bc53dd2f" />

---

## How to Run

1. Open the project in Android Studio
2. Let Gradle sync
3. Run on emulator or device

---

## AI Disclosure

AI tools were used to help understand Compose layout patterns and check syntax issues during development.
All code was reviewed and tested manually to ensure it follows requirements and runs correctly.



