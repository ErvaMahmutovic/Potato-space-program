import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class LoadCheck {
    private static int failures = 0;

    private static void check(String label, boolean ok) {
        if (!ok) failures++;
        System.out.println((ok ? "ok   " : "FAIL ") + label);
    }

    public static void main(String[] args) throws IOException {
        Map<String, Potato> potatoes = CsvReader.readPotatoes("input/potatoes.csv");
        Map<String, Launcher> launchers = CsvReader.readLaunchers("input/launchers.csv");
        ArrayList<LaunchAttempt> attempts = CsvReader.readAttempts("input/launch_attempts.csv", potatoes, launchers);

        check("6 potatoes loaded", potatoes.size() == 6);
        check("4 launchers loaded", launchers.size() == 4);
        check("12 attempts loaded", attempts.size() == 12);
        check("P01 is a RUSSET potato", potatoes.get("P01") instanceof RussetPotato);
        check("P04 is a PURPLE potato", potatoes.get("P04") instanceof PurplePotato);

        LaunchAttempt a03 = null;
        for (LaunchAttempt a : attempts) {
            if (a.getId().equals("A03")) a03 = a;
        }
        check("A03 is linked to the same P01 object held in the map", a03 != null && a03.getPotato() == potatoes.get("P01"));
        check("A03 is linked to the same L01 object held in the map", a03 != null && a03.getLauncher() == launchers.get("L01"));
        check("A03 payload 6, rating 5, points 17", a03 != null && a03.getPayloadKg() == 6
                && a03.getFlightRating() == 5 && a03.getPoints() == 17);

        System.out.println(failures == 0 ? "All load checks passed" : failures + " check(s) failed");
        if (failures != 0) System.exit(1);
    }
}
