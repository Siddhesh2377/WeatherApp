A simple Android weather app that fetches real-time weather data using the **OpenWeatherMap API**. It features smart city search, error handling, and displays weather information cleanly.

## Features
- **Weather Data**: Temperature, Feels-like, Humidity, Wind Speed, Pressure, Visibility, and Sunset.
- **AutoComplete City Search**: Suggests city names while typing.
- **Error Handling**:
    - No internet connection.
    - Invalid city names.
- **Recent Search Navigation**: Previous and Next buttons for recently searched cities.

## Technologies Used
- **Java**
- **OkHttp**: Network requests
- **Gson**: JSON parsing
- **OpenWeatherMap API**

## How to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/WeatherApp.git
   ```
2. Add your **OpenWeatherMap API Key** in `WeatherFetcher.java`:
   ```java
   private static final String API_KEY = "YOUR_API_KEY";
   ```
3. Open in **Android Studio**, build, and run on an emulator or device.

## Screenshots
![Main Screen](screenshot_main.png)

## License
This project is licensed under the **MIT License**.
