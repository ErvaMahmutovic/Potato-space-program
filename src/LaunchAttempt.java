public class LaunchAttempt implements Comparable<LaunchAttempt> {
    private String id;
    private Potato potato;
    private Launcher launcher;
    private Integer payloadKg;
    private Integer flightRating;

    public LaunchAttempt(String id, Potato potato, Launcher launcher, Integer payloadKg, Integer flightRating) {
        this.id = id;
        this.potato = potato;
        this.launcher = launcher;
        this.payloadKg = payloadKg;
        this.flightRating = flightRating;
    }

    public String getId() {
        return this.id;
    }

    public Potato getPotato() {
        return this.potato;
    }

    public Launcher getLauncher() {
        return this.launcher;
    }

    public Integer getPayloadKg() {
        return this.payloadKg;
    }

    public Integer getFlightRating() {
        return this.flightRating;
    }

    public Integer getPoints() {
        IScore score = this.potato;
        return score.calculatePoints(this.payloadKg, this.flightRating);
    }

    public boolean isOverLimit() {
        if (this.payloadKg > this.launcher.getMaxPayloadKg()) {
            return true;
        } else {
            return false;
        }
    }

    public Integer getExcessKg() {
        if (isOverLimit()) {
            return this.payloadKg - this.launcher.getMaxPayloadKg();
        }
        return 0;
    }

    @Override
    public int compareTo(LaunchAttempt o) {
        if (!this.flightRating.equals(o.flightRating)) {
            return o.flightRating.compareTo(this.flightRating);
        }
        if (!this.payloadKg.equals(o.payloadKg)) {
            return o.payloadKg.compareTo(this.payloadKg);
        }
        return this.id.compareTo(o.id);
    }

    @Override
    public String toString() {
        return "LaunchAttempt{" + id + ", " + potato.getName() + ", " + launcher.getName() +
                ", " + payloadKg + " kg, rating " + flightRating + "}";
    }
}
