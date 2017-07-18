package com.epam.atm.dataprovider;

import com.epam.atm.utils.Logger;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Scanner;

public class ParserFromJSON {
    private static final String FILE_NAME = "BankData.json";
    private static final String FILE_PATH = "src\\main\\resources\\" + FILE_NAME;
    private static String BANKDATA = "";
    private static BankData list[];

    Type itemsArrType = new TypeToken<BankData[]>() {
    }.getType();

    public BankData[] crateTestData() {
        try {
            Scanner scanner = new Scanner(new FileReader(FILE_PATH));
            while (scanner.hasNextLine()) {
                BANKDATA = BANKDATA + scanner.next();
            }
        } catch (Exception exception) {
            Logger.error("File is not found or it's content can not be read");
        }
        System.out.println(BANKDATA);
        list = new Gson().fromJson(BANKDATA, itemsArrType);
        System.out.println(list[2].getBANK());
        return list;
    }
}