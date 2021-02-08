package fr.renardfute.steamapi.utils;

import com.google.gson.annotations.SerializedName;

/**
 * A utils class for price
 * @author RenardFute
 * @since 1.0
 */
public class Price {

    public double value;
    public Currency currency;
    public int quantity = 0;


    /**
     * Constructor for simple price (value, currency)
     * @param value a double meaning the value of this price object
     * @param currency the currency {@link Currency}
     * @author RenardFute
     * @since 1.0
     */
    public Price(double value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    /**
     * Constructor for price and stocks.
     * @param value a double meaning the value of this price object
     * @param currency the currency {@link Currency}
     * @param quantity the quantity of item
     * @author RenardFute
     * @since 1.0
     */
    public Price(double value, Currency currency, int quantity) {
        this.value = value;
        this.currency = currency;
        this.quantity = quantity;
    }


    /**
     * @return the price as a string.
     */
    @Override
    public String toString() {
        return value + "" + currency.symbol + " and " + quantity + " pcs";
    }

    /**
     * A object class for deserialization of json
     * @author RenardFute
     * @since 1.0
     */
    public static class Overview {

        public String currency;
        public int initial;
        public int finial;
        @SerializedName("discount_percent")
        public int discount;
        @SerializedName("initial_formatted")
        public String initialFormatted;
        @SerializedName("final_formatted")
        public String finalFormatted;

        /**
         * @return the initial price as a {@link Price} object
         * @author RenardFute
         * @since 1.0
         */
        public Price getInitial(){
            Currency currency = Currency.getByName(this.currency);
            assert currency != null;
            String sInitialValue = initialFormatted.replace(currency.symbol + "", "").replace(",", ".");
            return new Price(Double.parseDouble(sInitialValue), currency);
        }

        /**
         * @param addDiscount True if you want the final price with the discount applied
         * @return the final price as a {@link Price} object
         * @author RenardFute
         * @since 1.0
         */
        public Price getFinal(boolean addDiscount){
            Currency currency = Currency.getByName(this.currency);
            assert currency != null;
            String sFinalValue = finalFormatted.replace(currency.symbol + "", "").replace(",", ".");
            double finalValue = Double.parseDouble(sFinalValue);
            if(addDiscount){
                return new Price(finalValue, currency);
            }else {
                double beforeDiscount = removeDiscount(discount, finalValue);
                return new Price(beforeDiscount, currency);
            }
        }
    }

    /**
     * Just a simple method to get starting value with final and discount values
     * @param discountPercent the discount
     * @param finalPrice the final
     * @return the starting value
     * @author Renardfute
     * @since 1.0
     */
    public static double removeDiscount(int discountPercent, double finalPrice){
        return finalPrice / (1 - (discountPercent/100D));
    }
}
