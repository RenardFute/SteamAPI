package fr.renardfute.steamapi.utils;

/**
 * Enum of all available currency for this api. <br>
 *     With their value (used for steam api) <br>
 *         Their symbol and their name (also used for deserialization)
 * @author RenardFute
 * @since 1.0
 */
public enum Currency {

    EUR(3, '€', "EUR"),
    USD(1, '$', "USD");

    public int value;
    public char symbol;
    public String name;

    /**
     * @param value Value of the currency
     * @param symbol Symbol of the currency
     * @param name Name of the currency
     * @author RenardFute
     * @since 1.0
     */
    Currency(int value, char symbol, String name) {
        this.value = value;
        this.symbol = symbol;
        this.name = name;
    }


    /**
     * Get a currency by it's symbol. <br>
     *     Useful if you only know the suffix of a price.
     * @param symbol the symbol of the possible currency.
     * @return the currency with the right symbol. Caution may be null if no currency is matching this symbol.
     * @author RenardFute
     * @since 1.0
     */
    public static Currency getBySymbol(char symbol){
        for (Currency currency : Currency.values()){
            if(currency.symbol == symbol) return currency;
        }

        return null;
    }


    /**
     * Get a currency when you only know it's name
     * @param name Name of the currency you want to get
     * @return the currency matching this name. Caution may be null
     * @author RenardFute
     * @since 1.0
     */
    public static Currency getByName(String name){
        for (Currency currency : Currency.values()){
            if(currency.name.equals(name)) return currency;
        }

        return null;
    }
}
