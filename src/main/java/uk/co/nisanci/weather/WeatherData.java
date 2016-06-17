package uk.co.nisanci.weather;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gurkann on 17/06/2016.
 */
public class WeatherData {
    public int cod;
    public String message;
    public String city;
    public String today = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    public String description;
    public double temperatureCelcius;
    public double temperatureFahrenheit;
    public Long sunrise;
    public Long sunset;

    public String getResult() {
        StringBuffer sb = new StringBuffer();
        sb.append("Date=").append(today);
        sb.append(" City=").append(city);
        sb.append(" Description=").append(description);
        sb.append(" Temp-Celcius=").append(temperatureCelcius);
        sb.append(" Temp-Fahrenheit=").append(temperatureFahrenheit);
        sb.append(" Sunrise=").append(new SimpleDateFormat("K:ma").format(new Timestamp(sunrise * 1000)));
        sb.append(" Sunset=").append(new SimpleDateFormat("K:ma").format(new Timestamp(sunset * 1000)));

        return sb.toString();
    }

    public String getError() {
        StringBuffer sb = new StringBuffer();
        sb.append("Cod=").append(cod).append("\n");
        sb.append("Message=").append(message).append("\n");

        return sb.toString();
    }

    public int getCod() {
        return cod;
    }
}
