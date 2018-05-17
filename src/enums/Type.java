package enums;

public enum Type{
    NUMBER,
    TEXT,
    BOOL,
    GROUP,
    LIST,
    LIGHTBULB,
    MOTOR,
    TEMPERATURESENSOR,
    NONE,
    ERROR;

    @Override
    public String toString() {
        String stringRep = "";
        switch(this) {
            case MOTOR:
                stringRep = "Motor";
                break;
            case TEMPERATURESENSOR:
                stringRep = "TemperatureSensor";
                break;
            case LIGHTBULB:
                stringRep = "Lightbulb";
                break;
            default:
                stringRep = this.name();
                break;
        }
        return stringRep;
    }
}
