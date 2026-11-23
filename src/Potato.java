public abstract class Potato implements IScore {
    private String id;
    private String name;
    private String type;

    public Potato(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "Potato{" + id + ", " + name + ", " + type + "}";
    }
}
