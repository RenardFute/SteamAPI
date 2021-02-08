package fr.renardfute.steamapi.objects;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Package class. <br>
 *     This is used if a game is selling package. <br>
 *         Example: A game is vending a package (game + dlc) here subs will be the game and the dlc
 * @author RenardFute
 * @since 1.0
 */
public class Package {

    public String name;
    public String title;
    public String description;
    @SerializedName("selection_text")
    public String selectionText;
    @SerializedName("save_text")
    public String saveText;
    @SerializedName("display_type")
    public int displayType;
    @SerializedName("is_recurring_subscription")
    public String isRecursive;
    public List<Object> subs; // TODO: 03/02/2021 Make new class for subs;
}
