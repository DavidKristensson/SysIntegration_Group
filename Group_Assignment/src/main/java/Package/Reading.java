package Package;

import java.util.Date;

public class Reading {
    private double temperature;
    private double humidity;
    private Date created;
    private int id;

    Reading(){ }


    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public Date getCreated() { return created; }

    public void setCreated(Date created) { this.created = created; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }
}
