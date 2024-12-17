# WeatherApp

A simple, lightweight Android weather app that fetches real-time weather data using the **OpenWeatherMap API**. It provides accurate weather information with smart search functionality and robust error handling.

## Features
- **Real-Time Weather**: Displays Temperature, Feels-like temperature, Humidity, Wind Speed, Pressure, Visibility, and Sunset time.
- **Smart City Search**: AutoComplete feature suggests city names as you type.
- **Error Handling**:
    - Gracefully handles **no internet connection**.
    - Alerts for **invalid city names**.
- **Recent Search Navigation**: Quickly navigate between previously searched cities using **Previous** and **Next** buttons.

## Technologies Used
- **Java**: Core programming language.
- **OkHttp**: For efficient network requests.
- **Gson**: JSON parsing.
- **OpenWeatherMap API**: Weather data provider.

## How to Run
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

## Builds
### GitHub Actions
- **[View Build Status](https://github.com/Siddhesh2377/WeatherApp/actions)**

### Latest Release
- **Version**: Stable 1
- **APK Downloads**:
    - [Debug APK](https://github.com/Siddhesh2377/WeatherApp/releases/download/Releasee/app-debug.apk)
    - [Release APK](https://github.com/Siddhesh2377/WeatherApp/releases/download/Releasee/app-release.apk)

## Screenshots
![Main Screen](screenshot_main.png)

