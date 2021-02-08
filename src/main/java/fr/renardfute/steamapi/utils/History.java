package fr.renardfute.steamapi.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * This is like a list but every item is sorted with his date.
 * @param <K> The object contained in the list
 * @author RenardFute
 * @since 1.0
 */
public class History<K>{

    public List<Pair<K>> objects = new ArrayList<>();


    /**
     * Add an item of type {@link K}
     * @param item item to add
     * @param date its date
     * @author RenardFute
     * @since 1.0
     */
    public void add(K item, Date date){
        Pair<K> pair = new Pair<>(item, date);
        objects.add(pair);
        this.sort();
    }


    /**
     * Sort the list with dates
     * @author RenardFute
     * @since 1.0
     */
    public void sort() {
        objects.sort(Comparator.comparing(o -> o.date));
    }

    /**
     * Get the size of the list
     * @return the size as an {@link Integer}
     * @author RenardFute
     * @since 1.0
     */
    public int getSize() {
        return objects.size();
    }


    /**
     * Return the i element in the list
     * @param i index of the element you want to get in the list
     * @return the {@link K} object at index i
     * @author RenardFute
     * @since 1.0
     */
    public K get(int i) {
        return objects.get(i).item;
    }


    /**
     * @return this list as a string.
     * @author RenardFute
     * @since 1.0
     */
    public String toString() {
        return "History{" +
                "objects=" + objects +
                '}';
    }

    /**
     * A class for linking date and items
     * @param <K> the type of items to link with dates
     */
    static class Pair<K> {

        public K item;
        public Date date;

        /**
         * Main constructor
         * @param item the item of type {@link K} to link
         * @param date the {@link Date} to link
         * @author RenardFute
         * @since 1.0
         */
        public Pair(K item, Date date) {
            this.item = item;
            this.date = date;
        }


        /**
         * @return the item of this pair
         * @author RenardFute
         * @since 1.0
         */
        public K getItem() {
            return item;
        }

        /**
         * @param item set the item of this pair
         * @author RenardFute
         * @since 1.0
         */
        public void setItem(K item) {
            this.item = item;
        }


        /**
         * @return the date associated with this pair
         * @author RenardFute
         * @since 1.0
         */
        public Date getDate() {
            return date;
        }

        /**
         * @param date set the date of this pair
         * @author RenardFute
         * @since 1.0
         */
        public void setDate(Date date) {
            this.date = date;
        }

        /**
         * @return this pair as a string.
         * @author RenardFute
         * @since 1.0
         */
        @Override
        public String toString() {
            return "Pair{" +
                    "item=" + item +
                    ", date=" + date +
                    '}';
        }
    }
}
