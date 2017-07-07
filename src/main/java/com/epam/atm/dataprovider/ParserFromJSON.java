package com.epam.atm.dataprovider;

import com.epam.atm.dataprovider.BankData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.util.Scanner;

public class ParserFromJSON {
    private static final String FILE_NAME = "BankData.json";
    private static final String FILE_PATH = "src\\main\\resources\\" + FILE_NAME;
    private static String BANKDATA = "";
//http://www.javacreed.com/simple-gson-example/

    public BankData crateTestData() {
        BankData bankData = new BankData();
        try {
            Scanner scanner = new Scanner(new FileReader(FILE_PATH));
            while (scanner.hasNext()) {
                BANKDATA = BANKDATA + scanner.next();
            }
        } catch (Exception exception) {
            System.out.println("File is not found or it's content can not be read");
        }
        System.out.println(BANKDATA);
        Gson gson = new GsonBuilder().create();
        bankData = gson.fromJson(BANKDATA, BankData.class);
        System.out.println(bankData.getBANK());
        System.out.println(bankData.getSUM_OF_CREDIT());
        return bankData;
    }
}