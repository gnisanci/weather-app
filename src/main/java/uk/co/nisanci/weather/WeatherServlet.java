package uk.co.nisanci.weather;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by gurkann on 17/06/2016.
 */
public class WeatherServlet extends HttpServlet {
    private static final String OPENWEATHERMAP_API_URL = "http://api.openweathermap.org/data/2.5/weather";
    private static final String API_KEY = "741be35b0a1b34937e8ef6fcde57a293";

    public void doPost(HttpServletRequest req,
                       HttpServletResponse res)
            throws IOException, ServletException {

        res.setContentType("text/html");
        if (req.getParameter("city").trim().isEmpty())
            req.setAttribute("error", "City name can not be empty!");
        else {
            String weatherJsonMetric = getWeatherJson(req.getParameter("city").trim(), "metric");
            String weatherJsonImperial = getWeatherJson(req.getParameter("city").trim(), "imperial");
            if (weatherJsonMetric == null || weatherJsonImperial == null)
                req.setAttribute("error", "Exception during weather api call");
            else {
                WeatherData weatherData = parseWeatherJson(weatherJsonMetric, weatherJsonImperial);
                req.setAttribute("weatherData", weatherData);
                req.setAttribute("weatherJsonMetric", weatherJsonMetric);
                req.setAttribute("weatherJsonImperial", weatherJsonImperial);
            }
        }
        req.getRequestDispatcher("/result.jsp").forward(req, res);
    }

    private String getWeatherJson(String city, String unit) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet;

        try {
            URIBuilder uriBuilder = new URIBuilder(OPENWEATHERMAP_API_URL);
            uriBuilder.addParameter("APPID", API_KEY);
            uriBuilder.addParameter("q", city);
            uriBuilder.addParameter("units", unit);
            httpGet = new HttpGet(new URI(uriBuilder.toString()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }

        try (CloseableHttpResponse httpResponse = httpclient.execute(httpGet)) {
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) ;
            return EntityUtils.toString(httpResponse.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private WeatherData parseWeatherJson(String weatherJsonMetric, String weatherJsonImperial) {
        JSONObject objMetric = new JSONObject(weatherJsonMetric);
        JSONObject objImperial = new JSONObject(weatherJsonImperial);

        WeatherData weatherData = new WeatherData();
        weatherData.cod = objMetric.getInt("cod");
        if (weatherData.cod != 200)
            weatherData.message = objMetric.getString("message");
        else {
            weatherData.city = objMetric.getString("name");

            JSONArray arr = objMetric.getJSONArray("weather");
            StringBuffer descriptionBuffer = new StringBuffer();
            for (int i = 0; i < arr.length(); i++) {
                if (i > 0)
                    descriptionBuffer.append(", ");
                descriptionBuffer.append(arr.getJSONObject(i).getString("description"));
            }

            weatherData.description = descriptionBuffer.toString();
            weatherData.temperatureCelcius = objMetric.getJSONObject("main").getDouble("temp");
            weatherData.temperatureFahrenheit = objImperial.getJSONObject("main").getDouble("temp");
            weatherData.sunrise = objMetric.getJSONObject("sys").getLong("sunrise");
            weatherData.sunset = objMetric.getJSONObject("sys").getLong("sunset");
        }

        return weatherData;
    }

}