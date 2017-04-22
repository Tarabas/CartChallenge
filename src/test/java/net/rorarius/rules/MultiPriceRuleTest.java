package net.rorarius.rules;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MultiPriceRuleTest {

    @Test
    public void calculateRuleTotalSingle() {
        Rule mpr = new MultiPriceRule("A", 10, 2, 15);

        List<String> itemList = new ArrayList<>();
        itemList.add("A");

        int ruleTotal = mpr.calculateRuleTotal(0, itemList);

        assertEquals(10, ruleTotal);
    }

    @Test
    public void calculateRuleTotalMulti() {
        Rule mpr = new MultiPriceRule("A", 10, 2, 15);

        List<String> itemList = new ArrayList<>();
        itemList.add("A");
        itemList.add("A");

        int ruleTotal = mpr.calculateRuleTotal(0, itemList);

        assertEquals(15, ruleTotal);
    }

    @Test
    public void calculateRuleTotalMultiAndSingle() {
        Rule mpr = new MultiPriceRule("A", 10, 2, 15);

        List<String> itemList = new ArrayList<>();
        itemList.add("A");
        itemList.add("A");
        itemList.add("A");

        int ruleTotal = mpr.calculateRuleTotal(0, itemList);

        assertEquals(25, ruleTotal);
    }

    @Test
    public void calculateRuleTotalNoItems() {
        Rule mpr = new MultiPriceRule("A", 10, 2, 15);

        List<String> itemList = new ArrayList<>();
        int ruleTotal = mpr.calculateRuleTotal(0, itemList);

        assertEquals(0, ruleTotal);
    }

    @Test
    public void calculateRuleTotalNullItemList() {
        Rule mpr = new MultiPriceRule("A", 10, 2, 15);

        int ruleTotal = mpr.calculateRuleTotal(0, null);

        assertEquals(0, ruleTotal);
    }

}
