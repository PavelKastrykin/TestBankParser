package com.pavel.bank.parsing.sax;

import com.pavel.bank.entity.Account;
import com.pavel.bank.entity.AccountType;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 23.03.15.
 */
public class SAXBankParser extends DefaultHandler{
    private List<Account> accounts;
    private String currentElement;
    private Account currentAccount;
    private String currentIBAN;

    public List<Account> getAccounts(){
        return accounts;
    }

    @Override
    public void startDocument() throws SAXException {
        accounts = new ArrayList<Account>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElement = qName;
        if ("account".equals(currentElement)){
            currentIBAN = attributes.getValue("IBAN");
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if ("account".equals(currentElement)){
            currentAccount = new Account();
            currentAccount.setIBAN(currentIBAN);
        }
        if ("nameOfBank".equals(currentElement)){
            currentAccount.setBankName(new String(ch, start, length));
        }
        if ("country".equals(currentElement)){
            currentAccount.setCountry(new String(ch, start, length));
        }
        if ("typeOfAccount".equals(currentElement)){
            String accountTypeValue = new String(ch, start, length);
            if ("deposit".equals(accountTypeValue)){
                currentAccount.setTypeOfAccount(AccountType.DEPOSIT);
            }
            if ("demand".equals(accountTypeValue)){
                currentAccount.setTypeOfAccount(AccountType.DEMAND);
            }
            if ("checking".equals(accountTypeValue)){
                currentAccount.setTypeOfAccount(AccountType.CHECKING);
            }
            if ("saving".equals(accountTypeValue)){
                currentAccount.setTypeOfAccount(AccountType.SAVING);
            }
            if ("accumulating".equals(accountTypeValue)){
                currentAccount.setTypeOfAccount(AccountType.ACCUMULATING);
            }
            if ("metal".equals(accountTypeValue)){
                currentAccount.setTypeOfAccount(AccountType.METAL);
            }
        }
        if ("depositor".equals(currentElement)){
            currentAccount.setDepositor(new String(ch, start, length));
        }
        if ("accountID".equals(currentElement)){
            currentAccount.setAccountID(Long.parseLong(new String(ch, start, length)));
        }
        if ("amount".equals(currentElement)){
            currentAccount.setAmount(Double.parseDouble(new String(ch, start, length)));
        }
        if ("annualProfit".equals(currentElement)){
            currentAccount.setAnnualProfit(Float.parseFloat(new String(ch, start, length)));
        }
        if ("term".equals(currentElement)){
            currentAccount.setTerm(Float.parseFloat(new String(ch, start, length)));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("account".equals(qName)){
            accounts.add(currentAccount);
            currentAccount = null;
        }
        else currentElement = "";
    }
}
