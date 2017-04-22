package net.rorarius.rules;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ValueDiscountRuleTest {

    @Test
    public void calculateRuleTotalNoDiscount() {
        Rule spr = new ValueDiscountRule("A", 0);

        List<String> itemList = new ArrayList<>();
        itemList.add("A");

        int ruleTotal = spr.calculateRuleTotal(10, itemList);

        assertEquals(10, ruleTotal);
    }

    @Test
    public void calculateRuleTotalDiscountSingleItem() {
        Rule spr = new ValueDiscountRule("A", 10);

        List<String> itemList = new ArrayList<>();
        itemList.add("A");

        int ruleTotal = spr.calculateRuleTotal(15, itemList);

        assertEquals(5, ruleTotal);
    }

    @Test
    public void calculateRuleTotalDiscountNeverNegative() {
        Rule spr = new ValueDiscountRule("A", 10);

        List<String> itemList = new ArrayList<>();
        itemList.add("A");

        int ruleTotal = spr.calculateRuleTotal(5, itemList);

        assertEquals(0, ruleTotal);
    }

    @Test
    public void calculateRuleTotalDiscountNeverNegativeMultipleItems() {
        Rule spr = new ValueDiscountRule("A", 15);

        List<String> itemList = new ArrayList<>();
        itemList.add("A");
        itemList.add("A");

        int ruleTotal = spr.calculateRuleTotal(20, itemList);

        assertEquals(0, ruleTotal);
    }

    @Test
    public void calculateRuleTotalDiscountMultipleItems() {
        Rule spr = new ValueDiscountRule("A",  5);

        List<String> itemList = new ArrayList<>();
        itemList.add("A");
        itemList.add("A");

        int ruleTotal = spr.calculateRuleTotal(20, itemList);

        assertEquals(10, ruleTotal);
    }


    @Test
    public void calculateRuleTotalNoItems() {
        Rule spr = new ValueDiscountRule("A",  5);

        List<String> itemList = new ArrayList<>();
        int ruleTotal = spr.calculateRuleTotal(2, itemList);

        assertEquals(2, ruleTotal);
    }

    @Test
    public void calculateRuleTotalNullItemList() {
        Rule spr = new ValueDiscountRule("A",  5);

        int ruleTotal = spr.calculateRuleTotal(2, null);

        assertEquals(2, ruleTotal);
    }
}
