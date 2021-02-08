package fr.renardfute.steamapi.objects;

import java.util.List;

/**
 * Some classes used for deserialize games
 * @author RenardFute
 * @since 1.0
 */
public class Info {

    /**
     * For support contact of a game
     * @author RenardFute
     * @since 1.0
     */
    public static class Support {

        public String url;
        public String email;

    }


    /**
     *
     */
    public static class Content {

        public List<Integer> ids;
        public Object notes; // This is null in my model JSON.

    }

    /**
     * For games that have a metacritic score
     * @author RenardFute
     * @since 1.0
     */
    public static class MetaCritic {

        public int score;
        public String url;

    }

}
