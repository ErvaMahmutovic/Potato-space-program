import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        new File("output").mkdirs();

        Map<String, Potato> potatoes = CsvReader.readPotatoes("input/potatoes.csv");
        Map<String, Launcher> launchers = CsvReader.readLaunchers("input/launchers.csv");
        ArrayList<LaunchAttempt> attempts = CsvReader.readAttempts("input/launch_attempts.csv", potatoes, launchers);

        ReportWriter.report1(potatoes, attempts);
        System.out.println("Report1 - DONE");

        ReportWriter.report2(launchers, attempts);
        System.out.println("Report2 - DONE");

        ReportWriter.report3(attempts);
        System.out.println("Report3 - DONE");

        ReportWriter.report4(attempts);
        System.out.println("Report4 - DONE");

        ReportWriter.report5(attempts);
        System.out.println("Report5 - DONE");
    }
}
