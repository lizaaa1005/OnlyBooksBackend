package bs.lf10.entity;

public enum Zustand {
    WIE_NEU("Wie neu"),
    LEICHTE_GEBRAUCHSSPUREN("Leichte Gebrauchsspuren"),
    GEBRAUCHT("Gebraucht"),
    KAPUTT("Kaputt");

    private final String label;

    Zustand(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}