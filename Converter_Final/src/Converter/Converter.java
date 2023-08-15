package Converter;

import javax.swing.*;
import java.text.*;
import java.util.*;

/**
 * The type Converter.
 */
public class Converter {


    private Map<String, Double> conversionRates;

    /**
     * Get conversion rates map.
     *
     * @return the map
     */
    public Map<String, Double> getConversionRates() {
        return conversionRates;
    }

    /**
     * Instantiates a new Converter.
     */
    Converter() {
        conversionRates = new HashMap<>();
        conversionRates.put("USD", 1.0);
        conversionRates.put("Bolivian peso", 6.95);
        conversionRates.put("Argentinian blue peso", 695.0);
        conversionRates.put("Argentinian official peso", 278.0);
        conversionRates.put("Brazilian Real", 4.72);
        conversionRates.put("Canadian Dollar", 1.342006);
        conversionRates.put("Chinese Yuan Renminbi", 7.193656);
        conversionRates.put("Japanese Yen", 143.31);
        conversionRates.put("Russian Ruble", 97.206267);
        conversionRates.put("Euro", 0.911737);
        conversionRates.put("British Pound", 0.784468);
        conversionRates.put("Indian Rupee", 82.853204);
        conversionRates.put("Swiss Franc", 0.875299);
        conversionRates.put("Chilean Peso", 860.983453);
        conversionRates.put("Hong Kong Dollar", 7.815903);
    }


    /**
     * Validate input double.
     *
     * @param number the number
     * @return the double
     * @throws NumberFormatException the number format exception
     */
    public double validateInput(String number) throws NumberFormatException {
        if (number != null) {
            return Double.parseDouble(number);
        } else {
            throw new NumberFormatException("Invalid input");
        }
    }
    /**
     * Compare options.
     *
     * @param fromCurrency the from currency
     * @param toCurrency   the to currency
     */
    public static void compareOptions(String fromCurrency, String toCurrency) {
        if (Objects.equals(fromCurrency, toCurrency)) {
            JOptionPane.showMessageDialog(null, "Can't perform conversion.");

        }
    }

    /**
     * Convert currency double.
     *
     * @param amount       the amount
     * @param fromCurrency the from currency
     * @param toCurrency   the to currency
     * @return the double
     */
    public double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        double fromRate = conversionRates.get(fromCurrency);
        double toRate = conversionRates.get(toCurrency);
        double convertedAmount = (amount / fromRate) * toRate;

        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String formattedAmount = decimalFormat.format(convertedAmount);
        return Double.parseDouble(formattedAmount);
    }
}



