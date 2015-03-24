package com.pavel.bank.parsing.stax;

import com.pavel.bank.entity.Account;
import com.pavel.bank.entity.AccountType;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 23.03.15.
 */
public class StAXBankParser {

    public static List<Account> process(XMLStreamReader reader) throws XMLStreamException{
        List<Account> accounts = new ArrayList<Account>();
        Account account = null;
        BankTagItem elementName = null;
        while (reader.hasNext()){
            int type = reader.next();
            switch (type){
                case XMLStreamConstants.START_ELEMENT:
                    elementName = BankTagItem.getElementTagName(reader.getLocalName());
                    switch (elementName){
                        case ACCOUNT:
                            account = new Account();
                            String IBAN = reader.getAttributeValue(null, "IBAN");
                            account.setIBAN(IBAN);
                            break;
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    String text = reader.getText().trim();
                    if (text.isEmpty()){
                        break;
                    }
                    switch (elementName){
                        case NAME_OF_BANK:
                            account.setBankName(text);
                            break;
                        case COUNTRY:
                            account.setCountry(text);
                            break;
                        case TYPE_OF_ACCOUNT:
                            if ("deposit".equals(text)){
                                account.setTypeOfAccount(AccountType.valueOf(text.toUpperCase()));
                                break;
                            }
                            else if ("demand".equals(text)){
                                account.setTypeOfAccount(AccountType.DEMAND);
                                break;
                            }
                            else if ("checking".equals(text)){
                                account.setTypeOfAccount(AccountType.CHECKING);
                                break;
                            }
                            else if ("saving".equals(text)){
                                account.setTypeOfAccount(AccountType.SAVING);
                                break;
                            }
                            else if ("accumulating".equals(text)){
                                account.setTypeOfAccount(AccountType.ACCUMULATING);
                                break;
                            }
                            else if ("metal".equals(text)){
                                account.setTypeOfAccount(AccountType.METAL);
                            }
                        case DEPOSITOR:
                            account.setDepositor(text);
                            break;
                        case ACCOUNT_ID:
                            account.setAccountID(Long.parseLong(text));
                            break;
                        case AMOUNT:
                            account.setAmount(Double.parseDouble(text));
                            break;
                        case ANNUAL_PROFIT:
                            account.setAnnualProfit(Float.parseFloat(text));
                            break;
                        case TERM:
                            account.setTerm(Float.parseFloat(text));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    elementName = BankTagItem.getElementTagName(reader.getLocalName());
                    switch (elementName){
                        case ACCOUNT:
                            accounts.add(account);
                            break;
                    }
                    break;

            }
        }
        return accounts;
    }

}
