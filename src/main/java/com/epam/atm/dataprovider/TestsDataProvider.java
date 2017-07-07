package com.epam.atm.dataprovider;

import org.testng.annotations.DataProvider;

public class TestsDataProvider {

    @DataProvider(name = "dataForFinance")
    public static Object[][] dataForFinance() {
        ParserFromJSON parserFromJSON= new ParserFromJSON();
        BankData bankData = parserFromJSON.crateTestData();
        String BANK = bankData.getBANK();
        int SUM_OF_CREDIT = bankData.getSUM_OF_CREDIT();
        return new Object[][]{
                {BANK, SUM_OF_CREDIT}
        };
    }
}