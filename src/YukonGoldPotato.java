public class YukonGoldPotato extends Potato {
    public YukonGoldPotato(String id, String name) {
        super(id, name, "YUKON_GOLD");
    }

    @Override
    public Integer calculatePoints(Integer payloadKg, Integer flightRating) {
        return 2 * payloadKg + 3 * flightRating;
    }
}
