import java.util.Comparator;

public class ViolationComparator implements Comparator<LaunchAttempt> {
    @Override
    public int compare(LaunchAttempt a1, LaunchAttempt a2) {
        if (!a1.getExcessKg().equals(a2.getExcessKg())) {
            return a2.getExcessKg().compareTo(a1.getExcessKg());
        }
        return a1.getId().compareTo(a2.getId());
    }
}
