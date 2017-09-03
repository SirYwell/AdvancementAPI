package io.chazza.advancementapi.conditionUtils;

/**
 * @author SirYwell
 */
public enum Feature {
    END_CITY("EndCity"),
    FORTRESS("Fortress"),
    MANSION("Mansion"),
    MINESHAFT("Mineshaft"),
    MONUMENT("Monument"),
    STRONGHOLD("Stronghold"),
    TEMPLE("Temple"),
    VILLAGE("Village");

    private String name;

    Feature(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
