public class Launcher {
    private String id;
    private String name;
    private Integer maxPayloadKg;

    public Launcher(String id, String name, Integer maxPayloadKg) {
        this.id = id;
        this.name = name;
        this.maxPayloadKg = maxPayloadKg;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Integer getMaxPayloadKg() {
        return this.maxPayloadKg;
    }

    @Override
    public String toString() {
        return "Launcher{" + id + ", " + name + ", max " + maxPayloadKg + " kg}";
    }
}
