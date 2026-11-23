import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CsvReader {

    public static Map<String, Potato> readPotatoes(String fileName) throws FileNotFoundException {
        Map<String, Potato> potatoes = new HashMap<>();

        File file = new File(fileName);
        Scanner s = new Scanner(file);

        if (s.hasNextLine()) s.nextLine();
        while (s.hasNextLine()) {
            String line = s.nextLine();
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(",");
            String id = parts[0].trim();
            String name = parts[1].trim();
            String type = parts[2].trim();

            Potato p = createPotato(id, name, type);
            potatoes.put(id, p);
        }
        s.close();
        return potatoes;
    }

    public static Map<String, Launcher> readLaunchers(String fileName) throws FileNotFoundException {
        Map<String, Launcher> launchers = new HashMap<>();

        File file = new File(fileName);
        Scanner s = new Scanner(file);

        if (s.hasNextLine()) s.nextLine();
        while (s.hasNextLine()) {
            String line = s.nextLine();
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(",");
            String id = parts[0].trim();
            String name = parts[1].trim();
            Integer maxPayloadKg = Integer.parseInt(parts[2].trim());

            launchers.put(id, new Launcher(id, name, maxPayloadKg));
        }
        s.close();
        return launchers;
    }

    public static ArrayList<LaunchAttempt> readAttempts(String fileName,
                                                        Map<String, Potato> potatoes,
                                                        Map<String, Launcher> launchers) throws FileNotFoundException {
        ArrayList<LaunchAttempt> attempts = new ArrayList<>();

        File file = new File(fileName);
        Scanner s = new Scanner(file);

        if (s.hasNextLine()) s.nextLine();
        while (s.hasNextLine()) {
            String line = s.nextLine();
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(",");
            String id = parts[0].trim();
            String potatoId = parts[1].trim();
            String launcherId = parts[2].trim();
            Integer payloadKg = Integer.parseInt(parts[3].trim());
            Integer flightRating = Integer.parseInt(parts[4].trim());

            Potato potato = potatoes.get(potatoId);
            Launcher launcher = launchers.get(launcherId);

            attempts.add(new LaunchAttempt(id, potato, launcher, payloadKg, flightRating));
        }
        s.close();
        return attempts;
    }

    private static Potato createPotato(String id, String name, String type) {
        if (type.equals("FINGERLING")) {
            return new FingerlingPotato(id, name);
        } else if (type.equals("PURPLE")) {
            return new PurplePotato(id, name);
        } else if (type.equals("RUSSET")) {
            return new RussetPotato(id, name);
        } else if (type.equals("YUKON_GOLD")) {
            return new YukonGoldPotato(id, name);
        }
        throw new IllegalArgumentException("Unknown potato type: " + type);
    }
}
