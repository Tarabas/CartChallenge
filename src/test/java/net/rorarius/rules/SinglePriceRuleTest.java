package net.rorarius.rules;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SinglePriceRuleTest {

    @Test
    public void calculateRuleTotalSingle() {
        Rule spr = new SinglePriceRule("A", 10);

        List<String> itemList = new ArrayList<>();
        itemList.add("A");

        int ruleTotal = spr.calculateRuleTotal(0, itemList);

        assertEquals(10, ruleTotal);
    }

    @Test
    public void calculateRuleTotalMulti() {
        Rule spr = new SinglePriceRule("A", 10);

        List<String> itemList = new ArrayList<>();
        itemList.add("A");
        itemList.add("A");

        int ruleTotal = spr.calculateRuleTotal(0, itemList);

        assertEquals(20, ruleTotal);
    }


    @Test
    public void calculateRuleTotalNoItems() {
        Rule spr = new SinglePriceRule("A", 10);

        List<String> itemList = new ArrayList<>();
        int ruleTotal = spr.calculateRuleTotal(0, itemList);

        assertEquals(0, ruleTotal);
    }

    @Test
    public void calculateRuleTotalNullItemList() {
        Rule spr = new SinglePriceRule("A", 10);

        int ruleTotal = spr.calculateRuleTotal(0, null);

        assertEquals(0, ruleTotal);
    }
}
