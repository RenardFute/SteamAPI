package fr.renardfute.steamapi.objects;

/**
 * A platform object. <br>
 *     Used to know if a game is available on a platform.
 * @author RenardFute
 * @since 1.0
 */
public class Platforms {

    public boolean windows;
    public boolean mac;
    public boolean linux;

    public Platforms(boolean windows, boolean mac, boolean linux) {
        this.windows = windows;
        this.mac = mac;
        this.linux = linux;
    }
}
