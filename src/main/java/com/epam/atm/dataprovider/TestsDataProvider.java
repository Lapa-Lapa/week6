package com.epam.atm.dataprovider;

import org.testng.annotations.DataProvider;

public class TestsDataProvider {

    @DataProvider(name = "dataForFinance")
    public static Object[][] dataForFinance() {
        ParserFromJSON parserFromJSON = new ParserFromJSON();
        BankData[] bankData = parserFromJSON.crateTestData();
        String BANK = null;
        int SUM_OF_CREDIT = 0;
        Object obj[][] = new Object[bankData.length][];
        for (int i = 0; i < bankData.length; i++) {
            BANK = bankData[i].getBANK();
            SUM_OF_CREDIT = bankData[i].getSUM_OF_CREDIT();
            obj[i] = new Object[2];
            obj[i][0] = BANK;
            obj[i][1] = SUM_OF_CREDIT;
        }
        return obj;
    }
}