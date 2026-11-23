public class RussetPotato extends Potato {
    public RussetPotato(String id, String name) {
        super(id, name, "RUSSET");
    }

    @Override
    public Integer calculatePoints(Integer payloadKg, Integer flightRating) {
        return 2 * payloadKg + flightRating;
    }
}
