package net.rorarius;

import net.rorarius.rules.MultiPriceRule;
import net.rorarius.rules.Rule;
import net.rorarius.rules.SinglePriceRule;
import net.rorarius.rules.ValueDiscountRule;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class CartChallengeTest {

    private Set<Rule> rules;

    @Before
    public void setUp() {
        rules = new LinkedHashSet<>();
        rules.add(new MultiPriceRule("A", 40, 3, 100));
        rules.add(new MultiPriceRule("B", 50, 2, 80));
        rules.add(new SinglePriceRule("C", 25));
        rules.add(new SinglePriceRule("D", 20));

        rules.add(new SinglePriceRule("E", 20));
        rules.add(new ValueDiscountRule("E", 10));
    }

    public int calculatePrice(String goods) {
        CheckOut co = new CheckOut(rules);
        for(int i=0; i < goods.length(); i++) {
            co.scan(String.valueOf(goods.charAt(i)));
        }
        return co.getTotal();
    }

    @Test
    public void testEmptyCart() {
        assertEquals(0, calculatePrice(""));
    }

    @Test
    public void testOneMultiRuleItemInCart() {
        assertEquals(40, calculatePrice("A"));
    }

    @Test
    public void testMultipleMultiRuleItemsWithNoDiscountInCart() {
        assertEquals(80, calculatePrice("AA"));
    }

    @Test
    public void testMultipleMultiRuleItemsInCart() {
        assertEquals(90, calculatePrice("AB"));
    }

    @Test
    public void testOneSingleRuleItemInCart() {
        assertEquals(25, calculatePrice("C"));
    }

    @Test
    public void testMultipleSingleRuleItemsInCart() {
        assertEquals(45, calculatePrice("CD"));
    }

    @Test
    public void testOneDiscountedGroupInCart() {
        assertEquals(100, calculatePrice("AAA"));
    }

    @Test
    public void testTwoDiscountedGroupsInCart() {
        assertEquals(200, calculatePrice("AAAAAA"));
    }

    @Test
    public void testDiscountedGroupAndSingleOfSameTypeInCart() {
        assertEquals(140, calculatePrice("AAAA"));
    }

    @Test
    public void testMixedMultiAndSingleRuleItemsInCart() {
        assertEquals(135, calculatePrice("CDBA"));
    }

    @Test
    public void testDiscountedGroupAndMultipleSinglesOfSameTypeInCart() {
        assertEquals(180, calculatePrice("AAAAA"));
    }

    @Test
    public void testDiscountedGroupsAndSingleOfSameTypeInCart() {
        assertEquals(140, calculatePrice("AAAA"));
    }

    @Test
    public void testOneDiscountedGroupAndSingleOfDifferentTypeInCart() {
        assertEquals(150, calculatePrice("AAAB"));
    }

    @Test
    public void testDifferentDiscountedGroupsInCart() {
        assertEquals(180, calculatePrice("AAABB"));
    }

    @Test
    public void testDifferentDiscountedAndSingleGroupsInCart() {
        assertEquals(200, calculatePrice("DABABA"));
        assertEquals(200, calculatePrice("AAABBD"));
    }

    @Test
    public void totals() {
        assertEquals(0, calculatePrice(""));
        assertEquals(40, calculatePrice("A"));
        assertEquals(90, calculatePrice("AB"));
        assertEquals(135, calculatePrice("CDBA"));
        assertEquals(80, calculatePrice("AA"));
        assertEquals(100, calculatePrice("AAA"));
        assertEquals(140, calculatePrice("AAAA"));
        assertEquals(180, calculatePrice("AAAAA"));
        assertEquals(200, calculatePrice("AAAAAA"));
        assertEquals(150, calculatePrice("AAAB"));
        assertEquals(180, calculatePrice("AAABB"));
        assertEquals(200, calculatePrice("AAABBD"));
        assertEquals(200, calculatePrice("DABABA"));
    }

    @Test
    public void incremental() {
        CheckOut co = new CheckOut(rules);
        assertEquals(0, co.getTotal());
        co.scan("A");
        assertEquals(40, co.getTotal());
        co.scan("B");
        assertEquals(90, co.getTotal());
        co.scan("A");
        assertEquals(130, co.getTotal());
        co.scan("A");
        assertEquals(150, co.getTotal());
        co.scan("B");
        assertEquals(180, co.getTotal());
    }

    @Test
    public void multipleRulesWithDiscount() {
        assertEquals(10, calculatePrice("E"));
    }

    @Test
    public void noRuleDefinedOnScan() {
        try {
            CheckOut co = new CheckOut(rules);
            co.scan("F");
            assertEquals("No Rule", "exists for this Item");
        } catch (IllegalArgumentException iax) {
            assertEquals(iax.getMessage(), "No Rule defined for F");
        }
    }
}
