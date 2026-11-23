public class ScoringCheck {
    private static int failures = 0;

    private static void check(String label, int expected, int actual) {
        if (expected != actual) {
            failures++;
            System.out.println("FAIL " + label + ": expected " + expected + " but got " + actual);
        } else {
            System.out.println("ok   " + label + " = " + actual);
        }
    }

    public static void main(String[] args) {
        IScore fingerling = new FingerlingPotato("T1", "F");
        IScore purple = new PurplePotato("T2", "P");
        IScore russet = new RussetPotato("T3", "R");
        IScore yukon = new YukonGoldPotato("T4", "Y");

        check("FINGERLING 6kg rating 5 (3*6+2*5)", 28, fingerling.calculatePoints(6, 5));
        check("PURPLE 7kg rating 5 (4*7+5)", 33, purple.calculatePoints(7, 5));
        check("RUSSET 6kg rating 5 (2*6+5)", 17, russet.calculatePoints(6, 5));
        check("YUKON_GOLD 8kg rating 2 (2*8+3*2)", 22, yukon.calculatePoints(8, 2));

        Launcher launcher = new Launcher("L1", "Test Launcher", 6);
        LaunchAttempt atLimit = new LaunchAttempt("A1", new RussetPotato("T3", "R"), launcher, 6, 5);
        LaunchAttempt over = new LaunchAttempt("A2", new RussetPotato("T3", "R"), launcher, 8, 5);
        check("at-limit payload is allowed (excess)", 0, atLimit.getExcessKg());
        check("over-limit excess", 2, over.getExcessKg());
        check("attempt points via interface", 21, over.getPoints());

        System.out.println(failures == 0 ? "All scoring checks passed" : failures + " check(s) failed");
        if (failures != 0) System.exit(1);
    }
}
