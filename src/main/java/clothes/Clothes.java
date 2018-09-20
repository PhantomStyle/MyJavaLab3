package clothes;

import tools.Color;
import tools.temperature.IronTemperature;
import tools.temperature.WashingTemperature;

public class Clothes {
    private final WashingTemperature washingTemperature;
    private final IronTemperature ironTemperature;
    private final Color color;

    public Clothes(WashingTemperature washingTemperature, IronTemperature ironTemperature, Color color) {
        this.washingTemperature = washingTemperature;
        this.ironTemperature = ironTemperature;
        this.color = color;
    }

    public WashingTemperature getWashingTemperature() {
        return washingTemperature;
    }

    public IronTemperature getIronTemperature() {
        return ironTemperature;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Clothes{" +
                "washingTemperature=" + washingTemperature +
                ", ironTemperature=" + ironTemperature +
                ", color=" + color +
                '}';
    }
}
