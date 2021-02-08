package fr.renardfute.steamapi.objects;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Achievement object for game deserialization
 * @author RenardFute
 * @since 1.0
 */
public class Achievements {

    public int total;
    public List<Success> highlighted;

    /**
     * Success object with name and image link.
     * @author RenardFute
     * @since 1.0
     */
    public static class Success {

        public String name;
        @SerializedName("path")
        public String image;

    }

}
