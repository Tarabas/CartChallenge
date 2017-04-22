package net.rorarius.rules;

import java.util.*;

public class RuleEvaluator {

    private Map<String, Set<Rule>> ruleMap;
    private List<String> itemList;

    public RuleEvaluator(Set<Rule> ruleSet, List<String> itemList) {
        this.itemList = itemList;
        saveRulesToMap(ruleSet);
    }

    public int getTotalForItem(String sku) {
        int itemTotal = 0;

        Set<Rule> ruleSet = getRulesForItem(sku);

        if (ruleSet != null) {
            itemTotal = getTotalWithItemRules(itemTotal, ruleSet);
        } else {
            // What if an item does not have a rule that defines the price?!
            // I would expect a default price for an item.
            throw new IllegalArgumentException("No Rule defined for "+sku);
        }

        return itemTotal;
    }

    private int getTotalWithItemRules(int currentTotal, Set<Rule> ruleSet) {
        for (Rule rule : ruleSet) {
            currentTotal = rule.calculateRuleTotal(currentTotal, itemList);
        }

        return currentTotal;
    }

    private Set<Rule> getRulesForItem(String sku) {
        if (ruleMap.containsKey(sku)) {
            return ruleMap.get(sku);
        } else {
            return null;
        }
    }

    private void saveRulesToMap(Set<Rule> rules) {
        ruleMap = new HashMap<>();

        if (rules != null) {
            for (Rule rule : rules) {
                if (ruleMap.containsKey(rule.getSku())) {
                    Set<Rule> skuRuleSet = ruleMap.get(rule.getSku());
                    skuRuleSet.add(rule);
                } else {
                    Set<Rule> skuRuleSet = new LinkedHashSet<>();
                    skuRuleSet.add(rule);
                    ruleMap.put(rule.getSku(), skuRuleSet);
                }
            }
        }
    }
}
