package pf.pages;

public class BankData {
    //http://jsongen.byingtondesign.com/
    private int SUM_OF_CREDIT;
    private String BANK;

    public int getSUM_OF_CREDIT(){
        return SUM_OF_CREDIT;
    }
    public void setSUM_OF_CREDIT(int input){
        this.SUM_OF_CREDIT = input;
    }
    public String getBANK(){
        return BANK;
    }
    public void setBANK(String input){
        this.BANK = input;
    }

}
