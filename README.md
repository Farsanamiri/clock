Here’s a well-structured **README.md** you can use for your project:

---

# 🌍 World Clock Android App

A simple Android application that displays the current **time and date** for different cities worldwide.
The app provides both a **digital clock** and a **custom analog clock** view, allowing users to select cities and view the local time in their timezone.

---

## ✨ Features

* 🕒 **Analog & Digital Clock** – Displays the current time with a custom-drawn analog clock and a digital text clock.
* 🌐 **Worldwide City Support** – Choose from multiple cities across the globe with auto-complete search.
* 📅 **Date Display** – Shows the full formatted date (day, month, year).
* 🎨 **Custom UI** – Rounded backgrounds, styled views, and smooth updates every second.
* ⚡ **Kotlin Coroutines** – Efficient clock updates using coroutines.

---

## 📸 Screenshots

*(Add your app screenshots here)*

---

## 🛠️ Tech Stack

* **Language:** Kotlin
* **UI:** XML with ConstraintLayout & custom `AnalogClockView`
* **Coroutines:** For real-time updates
* **Date & Time API:** `java.time` (ZonedDateTime, ZoneId, DateTimeFormatter)

---

## 📂 Project Structure

```
app/
 ├── java/com/example/clock/
 │    ├── MainActivity.kt        # Main logic, time updates, city selection
 │    ├── AnalogClockView.kt     # Custom analog clock drawing
 │
 ├── res/layout/
 │    ├── activity_main.xml      # UI layout
 │
 ├── res/drawable/
 │    ├── rounded_background.xml # Rounded corner background for views
```

---

## 🚀 Getting Started

### Prerequisites

* Android Studio (latest version)
* Minimum SDK: **API 26 (Android 8.0 Oreo)**
* Kotlin support enabled

### Installation

1. Clone this repository:

   ```bash
   git clone https://github.com/yourusername/world-clock-app.git
   ```
2. Open in **Android Studio**.
3. Build & run the project on an emulator or device.

---

## 🎯 How It Works

1. The user selects a city from the **AutoCompleteTextView**.
2. The app retrieves the **ZoneId** for that city.
3. Both the **analog** and **digital clocks** update every second using coroutines.
4. The **date** and **time zone info** are displayed under the clock.

---

## 📌 Example UI Components

### Rounded Background (`rounded_background.xml`)

```xml
<shape xmlns:android="http://schemas.android.com/apk/res/android" android:shape="rectangle">
    <solid android:color="#E6E6FA"/>
    <corners android:radius="20dp"/>
</shape>
```

### Custom Analog Clock

Draws a clock face, hour marks, and animated hour/minute hands with `Canvas`.

---

## 🧑‍💻 Author

Developed by **\[Your Name]** 🚀

---

## 📄 License

This project is licensed under the MIT License – see the [LICENSE](LICENSE) file for details.

---

Would you like me to also create a **badges section** (e.g., Android, Kotlin, Coroutines) and add a **demo GIF** placeholder in the README for a more professional look?
