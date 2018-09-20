package tools.temperature;

public class WashingTemperature extends Temperature{

    public WashingTemperature(int temperature) {
        super(temperature);
    }

    @Override
    public String toString() {
        return "WashingTemperature{" +
                "temperature=" + temperature +
                '}';
    }
}
