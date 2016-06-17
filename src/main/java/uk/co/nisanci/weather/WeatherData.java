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
        sb.append("<b>").append("Date=").append("</b>").append(today).append("<br>");
        sb.append("<b>").append("City=").append("</b>").append(city).append("<br>");
        sb.append("<b>").append("Description=").append("</b>").append(description).append("<br>");;
        sb.append("<b>").append("Temp-Celcius=").append("</b>").append(temperatureCelcius).append("<br>");;
        sb.append("<b>").append("Temp-Fahrenheit=").append("</b>").append(temperatureFahrenheit).append("<br>");;
        sb.append("<b>").append("Sunrise=").append("</b>").append(new SimpleDateFormat("K:ma").format(new Timestamp(sunrise * 1000))).append("<br>");;
        sb.append("<b>").append("Sunset=").append("</b>").append(new SimpleDateFormat("K:ma").format(new Timestamp(sunset * 1000))).append("<br>");;

        return sb.toString();
    }

    public String getError() {
        StringBuffer sb = new StringBuffer();
        sb.append("<b>").append("Cod=").append("</b>").append(cod).append("<br>");;
        sb.append("<b>").append("Message=").append("</b>").append(message).append("<br>");;

        return sb.toString();
    }

    public int getCod() {
        return cod;
    }
}
