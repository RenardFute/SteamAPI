package fr.renardfute.steamapi.objects;

import com.google.gson.annotations.SerializedName;

/**
 * Release Date for deserialization
 * @author RenardFute
 * @since 1.0
 */
public class ReleaseDate {

    /**
     * True if the game is not released but will be.
     */
    @SerializedName("coming_soon")
    public boolean comingSoon;
    public String date;

}
