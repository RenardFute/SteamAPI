package fr.renardfute.steamapi.objects;

import com.google.gson.annotations.SerializedName;
import fr.renardfute.steamapi.SteamAPI;
import fr.renardfute.steamapi.utils.Currency;

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
    public int appID;
    /**
     * App name.
     * @since 1.0
     */
    public String name;

    public transient Data data;

    /**
     * Main constructor not very useful as the apps are queried from steam.
     * @param appID The app id. {@link App#appID}
     * @param name The app name. {@link App#name}
     * @param data The app date; {@link App#data}
     * @author renardfute
     * @since 1.0
     */
    public App(int appID, String name, Data data) {
        this.appID = appID;
        this.name = name;
        this.data = data;
    }

    /**
     * This method make the process of getting an item easier and faster.
     * @param name the name of the item to get.
     * @param currency the currency you want the item prices to be.
     * @param api An api object for request.
     * @return the matching market item.
     * @author renardfute
     * @since 1.0
     */
    public Item getItem(String name, Currency currency, SteamAPI api){
        return new Item(name, this, api, currency);
    }

    /**
     * Data class for deserialization of a game
     * @author renardfute
     * @since 1.0
     */
    public static class Data {
        public String type;
        public String name;
        @SerializedName("steam_appid")
        public int id;
        @SerializedName("required_age")
        public String age;
        @SerializedName("is_free")
        public boolean isFree;
        @SerializedName("detailed_description")
        public String detailedDescription;
        @SerializedName("about_the_game")
        public String aboutTheGame;
        @SerializedName("short_description")
        public String shortDescription;
        @SerializedName("supported_languages")
        public String supportedLanguages;
        public String reviews;
        @SerializedName("header_image")
        public String headerImageUrl;
        @SerializedName("website")
        public String websiteUrl;
        @SerializedName("pc_requirements")
        public Requirements pc;
        @SerializedName("mac_requirements")
        public Requirements mac;
        @SerializedName("linux_requirements")
        public Requirements linux;
        @SerializedName("legal_notice")
        public String legalNotice;
        public java.util.List<String> developers;
        public java.util.List<String> publishers;
        @SerializedName("packages")
        public java.util.List<Long> packageIDs;
        @SerializedName("package_group")
        public java.util.List<Package> packages;
        public Platforms platforms;
        @SerializedName("metacritic")
        public Info.MetaCritic metaCritic;
        public java.util.List<Categories> categories;
        public java.util.List<Screenshots> screenshots;
        public java.util.List<Movies> movies;
        public Recommendations recommendations;
        public Achievements achievements;
        @SerializedName("release_date")
        public ReleaseDate releaseDate;
        @SerializedName("support_info")
        public Info.Support support;
        public String background;
        @SerializedName("content_descriptors")
        public Info.Content contentDescriptors;
    }

}
