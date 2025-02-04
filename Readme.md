# WeatherApp

A **modern, feature-rich Android weather app** that fetches real-time weather data using the **OpenWeatherMap API**. It provides **hourly and 7-day weather forecasts**, UI customization options, and an intuitive user interface.

## 🌟 Features

### 🏩 **Current Weather**
- Displays **Temperature**, **Feels-like Temperature**, **Humidity**, **Wind Speed**, **Pressure**, **Visibility**, **Sunrise & Sunset**.
- **Rain Probability (% Chance of Rain)**.
- **Weather Conditions** (e.g., Clear, Cloudy, Rainy, etc.).

### ⏳ **Hourly & Weekly Forecast**
- **Hourly Forecast** (next 24 hours) with **Temperature, Rain Chance, and Condition**.
- **7-Day Forecast** with **Min/Max Temperature, Rain Probability, and Weather Conditions**.

### 🎨 **Dynamic UI Customization**
- **System-based Theme**: Supports **Light/Dark Mode** based on device settings.
- **Custom Font Selection**: Change fonts from the **settings screen** (small, medium, large).
- **Color Customization**: Choose a color scheme to personalize the app.

### 📷 **Screenshot**
<img src="https://github.com/user-attachments/assets/3fd36d08-6604-4ddd-998f-d70cda0f805a" width="300" height="700">

### ⚙️ **Settings & Navigation**
- **Navigation Drawer** for quick access to **Settings, Forecast, and Home**.
- **Settings Screen** to change:
    - Temperature unit (Celsius / Fahrenheit).
    - Font and Theme.
    - Enable/Disable Weather Alerts.

## 🛠️ **Technologies Used**
- **Java**: Core programming language.
- **OkHttp**: Network requests to fetch data.
- **Gson**: JSON parsing.
- **OpenWeatherMap API**: Weather data provider.

## 🚀 **How to Run**
1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/WeatherApp.git
   ```
2. **Add your OpenWeatherMap API Key** in `WeatherFetcher.java`:
   ```java
   private static final String API_KEY = "YOUR_API_KEY";
   ```
3. **Build and Run**:
    - Open the project in **Android Studio**.
    - Build the project.
    - Run it on an emulator or physical device.
  

## 💻 Builds
### GitHub Actions
- **[View Build Status](https://github.com/Siddhesh2377/WeatherApp/actions)**

### Latest Release
- **Version**: Stable 1
- **APK Downloads**:
    - [Release APK](https://github.com/Siddhesh2377/WeatherApp/releases/tag/Releasee)

## 🏆 License
This project is licensed under the **MIT License**.

---

MIT License

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
