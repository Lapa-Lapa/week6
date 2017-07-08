package com.epam.atm.dataprovider;

import com.epam.atm.dataprovider.BankData;
import com.google.gson.*;

import com.google.gson.reflect.TypeToken;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ParserFromJSON {
    private static final String FILE_NAME = "BankData.json";
    private static final String FILE_PATH = "src\\main\\resources\\" + FILE_NAME;
    private static String BANKDATA = "";
    //private static JsonObject object;
    //private static JsonArray array;
    private static BankData list[];
    Type itemsArrType = new TypeToken<BankData[]>() {
    }.getType();
    //private static JsonParser parser;

    public BankData[] crateTestData() {
        try {
            Scanner scanner = new Scanner(new FileReader(FILE_PATH));
            while (scanner.hasNext()) {
                BANKDATA = BANKDATA + scanner.next();
            }
        } catch (Exception exception) {
            System.out.println("File is not found or it's content can not be read");
        }
        list = new Gson().fromJson(BANKDATA, itemsArrType);
        System.out.println(list[0].getBANK());
        return list;
    }
}