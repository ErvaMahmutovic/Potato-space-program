public class PurplePotato extends Potato {
    public PurplePotato(String id, String name) {
        super(id, name, "PURPLE");
    }

    @Override
    public Integer calculatePoints(Integer payloadKg, Integer flightRating) {
        return 4 * payloadKg + flightRating;
    }
}
