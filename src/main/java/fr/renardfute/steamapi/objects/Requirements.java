package fr.renardfute.steamapi.objects;

/**
 * Pc requirements for game deserialization
 * @author RenardFute
 * @since 1.0
 */
public class Requirements {

    public String minimum;
    public String recommended;

    /**
     * For example of those you can go on steam and look at some game pages.
     * @param minimum The minimum string <br>
     *                Containing infos about the minimum configuration your pc should have.
     * @param recommended The recommended string <br>
     *                    Containing infos about what the devs are recommending to you to play.
     * @author RenardFute
     * @since 1.0
     */
    public Requirements(String minimum, String recommended) {
        this.minimum = minimum;
        this.recommended = recommended;
    }
}
