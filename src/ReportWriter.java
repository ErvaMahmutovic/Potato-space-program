import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class ReportWriter {

    public static void report1(Map<String, Potato> potatoes, ArrayList<LaunchAttempt> attempts) throws IOException {
        Map<String, ArrayList<LaunchAttempt>> attemptsByPotato = new TreeMap<>();

        for (String potatoId : potatoes.keySet()) {
            attemptsByPotato.put(potatoId, new ArrayList<>());
        }
        for (LaunchAttempt a : attempts) {
            attemptsByPotato.get(a.getPotato().getId()).add(a);
        }

        FileWriter fw = new FileWriter("output/01_potato_flight_log.csv");
        fw.write("potatoId,name,type,attemptCount,totalPoints\n");
        for (Map.Entry<String, ArrayList<LaunchAttempt>> entry : attemptsByPotato.entrySet()) {
            Potato p = potatoes.get(entry.getKey());
            Integer totalPoints = 0;
            for (LaunchAttempt a : entry.getValue()) {
                totalPoints += a.getPoints();
            }
            fw.write(p.getId() + "," + p.getName() + "," + p.getType() + "," +
                    entry.getValue().size() + "," + totalPoints + "\n");
        }
        fw.close();
    }

    public static void report2(Map<String, Launcher> launchers, ArrayList<LaunchAttempt> attempts) throws IOException {
        Map<String, ArrayList<LaunchAttempt>> attemptsByLauncher = new TreeMap<>();

        for (String launcherId : launchers.keySet()) {
            attemptsByLauncher.put(launcherId, new ArrayList<>());
        }
        for (LaunchAttempt a : attempts) {
            attemptsByLauncher.get(a.getLauncher().getId()).add(a);
        }

        FileWriter fw = new FileWriter("output/02_launcher_activity.csv");
        fw.write("launcherId,name,attemptCount,totalPayloadKg,overLimitCount\n");
        for (Map.Entry<String, ArrayList<LaunchAttempt>> entry : attemptsByLauncher.entrySet()) {
            Launcher l = launchers.get(entry.getKey());
            Integer totalPayloadKg = 0;
            Integer overLimitCount = 0;
            for (LaunchAttempt a : entry.getValue()) {
                totalPayloadKg += a.getPayloadKg();
                if (a.isOverLimit()) {
                    overLimitCount++;
                }
            }
            fw.write(l.getId() + "," + l.getName() + "," + entry.getValue().size() + "," +
                    totalPayloadKg + "," + overLimitCount + "\n");
        }
        fw.close();
    }

    public static void report3(ArrayList<LaunchAttempt> attempts) throws IOException {
        Map<String, Map<String, ArrayList<LaunchAttempt>>> attemptsByPair = new TreeMap<>();

        for (LaunchAttempt a : attempts) {
            String potatoId = a.getPotato().getId();
            String launcherId = a.getLauncher().getId();

            if (!attemptsByPair.containsKey(potatoId)) {
                attemptsByPair.put(potatoId, new TreeMap<>());
            }
            if (!attemptsByPair.get(potatoId).containsKey(launcherId)) {
                attemptsByPair.get(potatoId).put(launcherId, new ArrayList<>());
            }
            attemptsByPair.get(potatoId).get(launcherId).add(a);
        }

        FileWriter fw = new FileWriter("output/03_launch_results_by_pair.csv");
        fw.write("potatoId,launcherId,attemptCount,totalPoints\n");
        for (Map.Entry<String, Map<String, ArrayList<LaunchAttempt>>> potatoEntry : attemptsByPair.entrySet()) {
            for (Map.Entry<String, ArrayList<LaunchAttempt>> launcherEntry : potatoEntry.getValue().entrySet()) {
                Integer totalPoints = 0;
                for (LaunchAttempt a : launcherEntry.getValue()) {
                    totalPoints += a.getPoints();
                }
                fw.write(potatoEntry.getKey() + "," + launcherEntry.getKey() + "," +
                        launcherEntry.getValue().size() + "," + totalPoints + "\n");
            }
        }
        fw.close();
    }
}
