package tools.temperature;

public class IronTemperature extends Temperature{

    public IronTemperature(int temperature) {
        super(temperature);
    }

    @Override
    public String toString() {
        return "IronTemperature{" +
                "temperature=" + temperature +
                '}';
    }
}
