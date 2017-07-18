package com.epam.atm.tests;

import com.epam.atm.dataprovider.BankData;
import com.epam.atm.dataprovider.ParserFromJSON;
import com.epam.atm.driver.WebDriverSingleton;
import com.epam.atm.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Tests {
    @Test(description = "Афиша.tut.by", priority = 0)
    /**    ----Афиша.tut.by
     1) Пройти в афишу
     2) Раздел Кино
     3) Выбрать 16 июля
     4) Установить временной промежуток от 20:00 до 2:00
     5) Выбрать Трансформеры
     6) Убедиться, что кадров из фильма 5 штук*/
    public void afishaTutByTest() {
        final int TIME = 20;
        HomePage homePage = new HomePage()
                .open();
        Assert.assertEquals(homePage.afishaOpen()
                .openFilms()
                .selectDate()
                .popupWindowClose()
                .selectTime(TIME)
                .selectFilm()
                .allShots(), 5, "There are five shots to this film");
    }

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

    @Test(description = "Финансы.tut.by", priority = 1, dataProvider = "dataForFinance")
    /**    ----Финансы.tut.by
     1) Пройти в финансы
     2) Выбрать кредит
     3) В дополнительных условиях - Беларусбанк
     4) Сумма кредита 3000
     5) Убедиться, что есть хоть один кредит со ставкой > 15%*/
    public void financeTutByTest(String BANK, int SUM_OF_CREDIT) {
        HomePage homePage = new HomePage()
                .open();
        Double res = homePage.financeOpen().pressChoseCreditButton()
                .popupWindowClose()
                .setBankAsOptionForCredit(BANK)
                .setSumOfCredit(SUM_OF_CREDIT).getResults();
        Assert.assertTrue(res > 15, "The biggest rate is = " + res + "%");
    }

    @Test(description = "Auto.tut.by", priority = 2)
    /**    ----Авто.tut.by
     1) Активировать версию для смартфонов. (футер)
     2) Пройти в секцию авто
     3) Рубрика видео
     4) Открыть любой пост
     5) Активировать плеер
     6) Подождать 15 сек.
     7) Развернуть плеер на весь экран
     8) Проверить, что развернулся на весь экран.*/
    public void autoTutByTest() {
        HomePage homePage = new HomePage()
                .open()
                .mobileVersionEnable();
        Assert.assertTrue(homePage
                .autoOpen()
                .videoSectionOpen()
                .postOpen()
                .videoPlayButtonPressAndFullSize()
                .getResultOfScalingToFullScreen(), "Full screen player");
    }

    @AfterClass(description = "close browser")
    public void kill() {
        WebDriverSingleton.kill();
    }
}