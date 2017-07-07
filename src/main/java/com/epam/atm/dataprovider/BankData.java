package com.epam.atm.dataprovider;

public class BankData {
    String BANK;
    int SUM_OF_CREDIT;

    public BankData() {
    }

    public BankData(String BANK, int SUM_OF_CREDIT) {
        this.BANK = BANK;
        this.SUM_OF_CREDIT = SUM_OF_CREDIT;
    }

    public String getBANK() {
        return BANK;
    }

    public void setBANK(String BANK) {
        this.BANK = BANK;
    }

    public int getSUM_OF_CREDIT() {
        return SUM_OF_CREDIT;
    }

    public void setSUM_OF_CREDIT(int SUM_OF_CREDIT) {
        this.SUM_OF_CREDIT = SUM_OF_CREDIT;
    }
}