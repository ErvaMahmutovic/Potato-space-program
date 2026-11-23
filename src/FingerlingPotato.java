public class FingerlingPotato extends Potato {
    public FingerlingPotato(String id, String name) {
        super(id, name, "FINGERLING");
    }

    @Override
    public Integer calculatePoints(Integer payloadKg, Integer flightRating) {
        return 3 * payloadKg + 2 * flightRating;
    }
}
