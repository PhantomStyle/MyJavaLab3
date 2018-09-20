package tools.temperature;

public abstract class Temperature {
    protected final int temperature;

    protected Temperature(int temperature) {
        this.temperature = temperature;
    }

    public int getTemperature() {
        return temperature;
    }
}
