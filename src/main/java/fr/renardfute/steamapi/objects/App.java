package fr.renardfute.steamapi.objects;

import com.google.gson.annotations.SerializedName;

/**
 * App class for all the apps (mainly game) of steam.
 * @author renardfute
 * @since 1.0
 */
public class App {

    /**
     * App id of this app. <br>
     *     Serialized as "<i>appid</i>"
     * @since 1.0
     */
    @SerializedName("appid")
    public long appID;
    /**
     * App name.
     * @since 1.0
     */
    public String name;

    /**
     * Main constructor not very useful as the apps are queried from steam.
     * @param appID The app id. {@link App#appID}
     * @param name The app name. {@link App#name}
     * @author renardfute
     * @since 1.0
     */
    public App(long appID, String name) {
        this.appID = appID;
        this.name = name;
    }

    /**
     * This class is mainly used for deserialization.
     * @author renardfute
     * @since 1.0
     */
    public static class Request{

        /**
         * A List object used for deserialization.
         * @since 1.0
         */
        @SerializedName("applist")
        public List appList;

        /**
         * Constructor for deserialization.
         * @param appList a List object.
         * @since 1.0
         * @author renardfute
         */
        public Request(List appList) {
            this.appList = appList;
        }

    }

    /**
     * This class is mainly used for deserialization.
     * @author renardfute
     * @since 1.0
     */
    public static class List{

        /**
         * A List of app used for deserialization.
         * @since 1.0
         */
        public java.util.List<App> apps;

        /**
         * Constructor for deserialization.
         * @param apps a List apps.
         * @since 1.0
         * @author renardfute
         */
        public List(java.util.List<App> apps) {
            this.apps = apps;
        }

    }

    public static class PlayerCountResponse{

        @SerializedName("response")
        public PlayerCount playerCount;

        public PlayerCountResponse(PlayerCount playerCount) {
            this.playerCount = playerCount;
        }
    }

    public static class PlayerCount{

        @SerializedName("player_count")
        public int count;
        public int result;

        public PlayerCount(int count, int result) {
            this.count = count;
            this.result = result;
        }
    }

}
