package fr.renardfute.steamapi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fr.renardfute.steamapi.objects.Item;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import fr.renardfute.steamapi.objects.App;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This is the main class of this API. <br>
 *     Init it with the main constructor and your auth token. (ReadTheDocs)
 * @see SteamAPI#SteamAPI(String)
 * @see <a href="https://github.com/RenardFute/SteamAPI/wiki">Docs</a>
 * @since 1.0
 * @author RenardFute
 */
public class  SteamAPI {

    /**
     * Your auth token. <br>
     *     This will be used for some request to Steam that need to be authenticated.
     * @since 1.0
     */
    private final String AUTH_TOKEN;// TODO: 01/02/2021 Make this automatic with steam login integration.

    /**
     * Client for web requesting.
     * @since 1.0
     */
    private static final OkHttpClient client = new OkHttpClient();

    /**
     * Main constructor of this API.
     * @param authToken Your auth token.
     * @see SteamAPI#AUTH_TOKEN
     * @author RenardFute
     * @since 1.0
     */
    public SteamAPI(String authToken){
        this.AUTH_TOKEN = authToken;
    }

    /**
     * Easy way to request to Steam server with {@link SteamAPI#AUTH_TOKEN}. <br>
     *     Can return an empty string if your request is wrong or if the response send by steam is empty.
     * @param url Of the request without the steamLoginSecure cookie.
     * @return The body of the response. <b>/!\ Caution as I said it can be empty.</b>
     * @author RenardFute
     * @since 1.0
     */
    public String request(String url){
        Request request = new Request.Builder()
                .url(url)
                .addHeader("cookie", "steamLoginSecure="+ this.AUTH_TOKEN + ";")
                .build();

        try {
            Response response = SteamAPI.client.newCall(request).execute();
            return Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "EMPTY BODY";
    }

    /**
     * This function is just useful for making request that don't needs the steamLoginSecure cookie.
     * @param url Of the request without the steamLoginSecure cookie.
     * @return The body of the response. <b>/!\ Caution as I said it can be empty.</b>
     * @author RenardFute
     * @since 1.0
     */
    public static String requestStatic(String url){
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = SteamAPI.client.newCall(request).execute();
            return Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "EMPTY BODY";
    }

    /**
     * Search on app in all the available apps.
     * @param appName the app name to search.
     * @return a list of all matching app ordered with player count on each app.
     * @since 1.0
     * @author renardfute
     * @see SteamAPI#reorderApps(List)
     */
    public List<App> searchApp(String appName){
        List<App> apps = this.getAllApps();
        List<App> matching = new ArrayList<>();
        for(App app : apps){
            if(app.name.contains(appName) && app.name.length() < appName.length() + 5){
                matching.add(app);
            }
        }
        matching = this.reorderApps(matching);

        return matching;
    }

    /**
     * Reorder a list of app with their player count.
     * @param toReorder the list to reorder.
     * @return an ordered list.
     * @since 1.0
     * @author renardfute
     */
    public List<App> reorderApps(List<App> toReorder){
        List<App> result = new ArrayList<>(toReorder);
        result.sort((app1, app2) -> {
            int count1 = getAppPlayerCount(app1);
            int count2 = getAppPlayerCount(app2);
            return Integer.compare(count2, count1);
        });

        return result;
    }

    /**
     * Get the player count of an app.
     * @param app the app to check.
     * @return the count of player online on the specified app.
     * @author renardfute
     * @since 1.0
     */
    public int getAppPlayerCount(App app){
        JSONParser parser = new JSONParser();
        String url = "https://api.steampowered.com/ISteamUserStats/GetNumberOfCurrentPlayers/v1/?format=json&appid=";
        url += app.appID;
        String result = request(url);
        JSONObject object = null;
        try {
            object = (JSONObject) parser.parse(result);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert object != null;
        JSONObject response = (JSONObject) object.get("response");
        return (int) response.get("player_count");
    }

    /**
     * Method that returns all the available app on steam.
     * @return a {@link java.util.List} of {@link App}
     * @author RenardFute
     * @since 1.0
     */
    public List<App> getAllApps(){
        JSONParser parser = new JSONParser();
        String sApps = request("http://api.steampowered.com/ISteamApps/GetAppList/v0002/?key=STEAMKEY&format=json");
        JSONObject object = null;
        try {
            object = (JSONObject) parser.parse(sApps);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert object != null;
        JSONObject object_ = (JSONObject) object.get("applist");
        return new Gson().fromJson(object_.get("apps").toString(), new TypeToken<List<App>>(){}.getType());
    }

    /**
     * Return the whole app's information. (Way faster than {@link SteamAPI#searchApp(String)} but you have to know the id)
     * @param id The id of the app you want to get.
     * @return An app object filled with it's information.
     * @author renardfute
     * @since 1.0
     */
    public App getApp(int id){
        JSONParser parser = new JSONParser();
        String sRequest = request("https://store.steampowered.com/api/appdetails?appids="+ id +"&l=french");
        JSONObject object = null;
        try {
            object = (JSONObject) parser.parse(sRequest);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert object != null;
        JSONObject object_ = (JSONObject) object.get(id + "");

        App.Data data = new Gson().fromJson(object_.get("data").toString(), App.Data.class);

        return new App(data.id, data.name, data);
    }


    /**
     * Get the image link to an market item.
     * @param item the item you want to get the image.
     * @return ImageLink as a String
     * @author RenardFute
     * @since 1.0
     */
    public String getItemImage(Item item){
        if(item.imageUrl != null) return item.imageUrl;

        String sRequest = request("https://steamcommunity.com/market/listings/" + item.app.appID + "/" + item.name.replaceAll(" ", "%20") + "/render?format=json&start=0&count=1&norender=1&currency=" + item.median.currency.value);
        JSONParser parser = new JSONParser();

        JSONObject object = null;
        try {
            object = (JSONObject) parser.parse(sRequest);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert object != null;
        JSONObject assets = (JSONObject) object.get("assets");
        JSONObject app = (JSONObject) assets.get("" + item.app.appID);
        JSONObject object_ = (JSONObject) app.get(app.keySet().toArray()[0]);
        JSONObject object__ = (JSONObject) object_.get(object_.keySet().toArray()[0]);
        String url = (String) object__.get("icon_url");

        return "https://community.akamai.steamstatic.com/economy/image/"+ url + "/124fx124f";
    }

}
