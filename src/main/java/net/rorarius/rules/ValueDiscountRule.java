package net.rorarius.rules;

import java.util.List;

public class ValueDiscountRule implements Rule {

    private String sku;

    private int discount;

    public ValueDiscountRule(String sku, int discount) {
        this.sku = sku;
        this.discount = discount;
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
        currentTotal -= getTotalItemDiscount(itemCount);

        if (currentTotal < 0) {
            return 0;
        } else {
            return currentTotal;
        }
    }

    private int getTotalItemDiscount(int itemCount) {
        return itemCount * discount;
    }
}
