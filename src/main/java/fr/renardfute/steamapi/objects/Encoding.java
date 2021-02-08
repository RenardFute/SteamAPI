package fr.renardfute.steamapi.objects;

import com.google.gson.annotations.SerializedName;


/**
 * This will stock the link to some videos
 * @author RenardFute
 * @since 1.0
 */
public class Encoding {

    /**
     * This will be the link of the video in 480p
     */
    @SerializedName("480")
    public String normal;
    /**
     * This will be the link of the video in max resolution
     */
    public String max;
}
