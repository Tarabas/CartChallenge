package net.rorarius.rules;


import java.util.List;

public interface Rule {

    String getSku();

    int calculateRuleTotal(int currentTotal, List<String> items);
}
