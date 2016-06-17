# Weather App
Shows the current weather info of a city fetched from OpenWeatherMap.org
* Requires java 8 and maven 3.3+ to run.
* Ensure there isn't any program running on default app port **9999** else change the port number in mvn command.

## How to run
1. In the console, go to the base source folder and execute: **mvn -Djetty.http.port=9999 jetty:run**
2. Once jetty started, navigate to http://localhost:9999 in your browser and enter the city name to see its weather info


