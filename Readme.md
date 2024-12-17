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
    - [Debug APK](https://github.com/Siddhesh2377/WeatherApp/releases/tag/Releasee)

## Screenshots
<img src="https://github.com/user-attachments/assets/8aa7f346-a7a8-4042-91ed-25da96a65325" alt="Main Screen" width="300" height="600">

## License
This project is licensed under the **MIT License**.

---

MIT License

Copyright (c) 2024 Siddhesh Sonar

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
