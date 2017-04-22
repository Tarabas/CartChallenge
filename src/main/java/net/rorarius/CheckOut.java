package net.rorarius;

import net.rorarius.rules.Rule;
import net.rorarius.rules.RuleEvaluator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CheckOut {

    private List<String> itemList = new ArrayList<>();
    private Set<String> itemSet = new HashSet<>();
    private Set<Rule> rules;

    public CheckOut(Set<Rule> rules) {
        this.rules = rules;
    }

    public void scan(String item) {
        if (!ruleExists(item)) {
            throw new IllegalArgumentException("No Rule defined for "+item);
        }

        itemList.add(item);
        itemSet.add(item);
    }

    public int getTotal() {
        RuleEvaluator ruleEvaluator = new RuleEvaluator(rules, itemList);

        int total = 0;

        for (String sku: itemSet) {
            total += ruleEvaluator.getTotalForItem(sku);
        }

        return total;
    }

    private boolean ruleExists(String sku) {
        if (rules == null) {
            return false;
        }

        if (sku == null) {
            return false;
        }

        return rules.stream().filter(r -> r.getSku().equals(sku)).count() > 0;
    }
}
