package net.rorarius.rules;

import java.util.List;

public class SinglePriceRule implements Rule {

    private String sku;

    private int price;

    public SinglePriceRule(String sku, int price) {
        this.sku = sku;
        this.price = price;
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

        currentTotal += getTotal(itemCount);

        return currentTotal;
    }

    protected int getTotal(int singlePriceAmount) {
        return singlePriceAmount * price;
    }
}
