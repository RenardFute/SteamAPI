package fr.renardfute.steamapi.objects;

import com.google.gson.annotations.SerializedName;


/**
 * Screenshot object for game deserialization.
 * @author RenardFute
 * @since 1.0
 */
public class Screenshots {

    /**
     * The id of the screenshot. <br>
     *     Relative to the game.
     * @since 1.0
     */
    public int id;
    /**
     * The thumbnail (reduced image)
     * @since 1.0
     */
    @SerializedName("path_thumbnail")
    public String thumbnail;
    /**
     * The full screenshot path
     * @since 1.0
     */
    @SerializedName("path_full")
    public String full;

}
