package fr.renardfute.steamapi.objects;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import fr.renardfute.steamapi.SteamAPI;
import fr.renardfute.steamapi.utils.Currency;
import fr.renardfute.steamapi.utils.History;
import fr.renardfute.steamapi.utils.Price;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * This is a class for market items.
 * @author RenardFute
 * @since 1.0
 */
public class Item {

    public String name;
    public App app;
    public Price median;
    public Price lowest;
    public int volume;
    public History<Price> history = new History<>();
    public String imageUrl;

    /**
     * This constructor will automatically get the item on the steam market.
     * @param name The item name. (Not encoded w\ %20 etc)
     * @param app The app of this item.
     * @param api A steam api object.
     * @param currency The currency you want the price to be get
     * @author RenardFute
     * @since 1.0
     */
    public Item(String name, App app, SteamAPI api, Currency currency) {
        this.name = name;
        this.app = app;

        DeserializedOverview deserializedOverview = new DeserializedOverview(api, name, app, currency);
        this.decodeOverview(deserializedOverview);
        DeserializedHistory deserializedHistory = new DeserializedHistory(api, name, app, currency);
        this.decodeHistory(deserializedHistory);

        this.imageUrl = api.getItemImage(this);
    }

    /**
     * This will decode a overview object.
     * @param overview the object to decode.
     * @author RenardFute
     * @since 1.0
     */
    public void decodeOverview(DeserializedOverview overview){
        Currency currency = Currency.getBySymbol(overview.lowestPrice.charAt(overview.lowestPrice.length() - 1));
        assert currency != null;
        double medianValue = Double.parseDouble(overview.medianPrice.replaceAll(currency.symbol + "", "")
                .replaceAll(",", "."));
        this.median = new Price(medianValue, currency);
        double lowestValue = Double.parseDouble(overview.lowestPrice.replaceAll(currency.symbol + "", "")
                .replaceAll(",", "."));
        this.lowest = new Price(lowestValue, currency);
        this.volume = Integer.parseInt(overview.volume);
    }

    /**
     * This will decode a history object
     * @param deserializedHistory the object to decode
     * @author RenardFute
     * @since 1.0
     */
    public void decodeHistory(DeserializedHistory deserializedHistory){
        Currency currency = Currency.getBySymbol(deserializedHistory.priceSuffix.charAt(0));
        DateFormat format = new SimpleDateFormat("MMM dd yyyy HH X", Locale.US);

        for(List<Object> object : deserializedHistory.prices){
            int quantity = Integer.parseInt((String) object.get(2));
            Date date = null;
            try {
                date = format.parse(((String) object.get(0)).replace(": +0", " -00"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Price price = new Price((Double) object.get(1), currency, quantity);
            history.add(price, date);
        }
    }

    /**
     * A overview object for deserialization
     * @author RenardFute
     * @since 1.0
     */
    static class DeserializedOverview{

        public boolean success;
        @SerializedName("lowest_price")
        public String lowestPrice;
        @SerializedName("median_price")
        public String medianPrice;
        public String volume;

        /**
         * This method will automatically get the overview of the given item.
         * @param api an api object to make request
         * @param itemName the item name
         * @param app the app of the item
         * @param currency the currency you want the price to be
         */
        public DeserializedOverview(SteamAPI api, String itemName, App app, Currency currency){
            String request = "http://steamcommunity.com/market/priceoverview?appid="+ app.appID +"&market_hash_name="+ itemName.replaceAll(" ", "%20") +"&currency="+ currency.value +"&country=france&format=json";
            DeserializedOverview tmp;
            String result = api.request(request);
            tmp = new Gson().fromJson(result, DeserializedOverview.class);
            this.medianPrice = tmp.medianPrice;
            this.lowestPrice = tmp.lowestPrice;
            this.success = tmp.success;
            this.volume = tmp.volume;
        }
    }

    /**
     * An history object for deserialization
     * @author RenardFute
     * @since 1.0
     */
    static class DeserializedHistory{

        public boolean success;
        @SerializedName("price_suffix")
        public String priceSuffix;
        @SerializedName("price_prefix")
        public String pricePrefix;
        public List<List<Object>> prices;

        /**
         * This method will automatically get the history of the given item.
         * @param api an api object to make request
         * @param itemName the item name
         * @param app the app of the item
         * @param currency the currency you want the price to be
         */
        public DeserializedHistory(SteamAPI api, String itemName, App app, Currency currency){
            String request = "http://steamcommunity.com/market/pricehistory?appid="+ app.appID +"&market_hash_name="+ itemName.replaceAll(" ", "%20") +"&currency="+ currency.value +"&country=france&format=json";
            DeserializedHistory tmp;
            String result = api.request(request);
            tmp = new Gson().fromJson(result, DeserializedHistory.class);
            this.priceSuffix = tmp.priceSuffix;
            this.success = tmp.success;
            this.prices = tmp.prices;
        }
    }

}
