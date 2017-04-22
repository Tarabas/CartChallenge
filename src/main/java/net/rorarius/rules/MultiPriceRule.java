package net.rorarius.rules;

import java.util.List;

public class MultiPriceRule implements Rule {

    private String sku;

    private int singlePrice;

    private int multiItems;

    private int multiPrice;

    public MultiPriceRule(String sku, int singlePrice, int multiItems, int multiPrice) {
        this.sku = sku;
        this.singlePrice = singlePrice;
        this.multiItems = multiItems;
        this.multiPrice = multiPrice;
    }

    @Override
    public String getSku() {
        return sku;
    }

    public int calculateRuleTotal(int currentTotal, List<String> items) {
        if (items == null) {
            return currentTotal;
        }

        int itemCount = (int)items.stream().filter(s -> s.equals(sku)).count();

        if (itemCount >= multiItems) {
            int multiPriceAmount = getMultiPriceAmount(itemCount);
            int singlePriceAmount = getSinglePriceAmount(itemCount, multiPriceAmount);

            currentTotal += getSinglePriceTotal(singlePriceAmount) + getMultiPriceTotal(multiPriceAmount);
        } else {
            currentTotal += getSinglePriceTotal(itemCount);
        }

        return currentTotal;
    }

    private int getSinglePriceAmount(int itemCount, int multiPriceAmount) {
        return itemCount-(multiItems * multiPriceAmount);
    }

    private int getMultiPriceAmount(int itemCount) {
        return (int)Math.floor(itemCount / multiItems);
    }

    private int getMultiPriceTotal(int multiPriceAmount) {
        return multiPriceAmount * multiPrice;
    }

    private int getSinglePriceTotal(int singlePriceAmount) {
        return singlePriceAmount * singlePrice;
    }
}
