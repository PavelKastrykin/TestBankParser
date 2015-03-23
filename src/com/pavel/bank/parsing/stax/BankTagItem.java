package com.pavel.bank.parsing.stax;

public enum BankTagItem {
    ACCOUNT, NAME_OF_BANK, COUNTRY, TYPE_OF_ACCOUNT, DEPOSITOR, ACCOUNT_ID, AMOUNT, ANNUAL_PROFIT, TERM;

    public static BankTagItem getElementTagName (String element){
        if ("account".equals(element)) return ACCOUNT;
        if ("nameOfBank".equals(element)) return NAME_OF_BANK;
        if ("country".equals(element)) return COUNTRY;
        if ("typeOfAccount".equals(element)) return TYPE_OF_ACCOUNT;
        if ("depositor".equals(element)) return DEPOSITOR;
        if ("accountID".equals(element)) return ACCOUNT_ID;
        if ("amount".equals(element)) return AMOUNT;
        if ("annualProfit".equals(element)) return ANNUAL_PROFIT;
        if ("term".equals(element)) return TERM;
        else throw new EnumConstantNotPresentException(BankTagItem.class, element);
    }
}
