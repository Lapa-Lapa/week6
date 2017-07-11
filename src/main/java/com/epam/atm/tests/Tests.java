package com.epam.atm.tests;

import com.epam.atm.dataprovider.TestsDataProvider;
import com.epam.atm.pages.*;
import com.epam.atm.driver.WebDriverSingleton;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Tests extends TestsDataProvider {
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
                .open()
                .afishaOpen();
        FilmsPage filmsPage = new FilmsPage()
                .openFilms()
                .selectDate()
                .popupWindowClose();
        filmsPage.selectTime(TIME)
                .selectFilm();
        int i = filmsPage.allShots();
        Assert.assertEquals(i, 5, "There are five shots to this film");
    }

    //@Parameters({"SUM_OF_CREDIT", "BANK"})
    @Test(description = "Финансы.tut.by", priority = 1, dataProvider = "dataForFinance")
    /**    ----Финансы.tut.by
     1) Пройти в финансы
     2) Выбрать кредит
     3) В дополнительных условиях - Беларусбанк
     4) Сумма кредита 3000
     5) Убедиться, что есть хоть один кредит со ставкой > 15%*/
    public void financeTutByTest(String BANK,int SUM_OF_CREDIT) {
        HomePage homePage = new HomePage()
                .open()
                .financeOpen();
        FinancePage financePage = new FinancePage()
                .pressChoseCreditButton()
                .popupWindowClose()
                .setBankAsOptionForCredit(BANK)
                .setSumOfCredit(SUM_OF_CREDIT);
        Double res = financePage.getResults();
        Assert.assertTrue(res > 15, "Кредиты со ставкой больше 15% есть");
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
                .mobileVersionEnable()
                .autoOpen();
        AutoPage autoPage = new AutoPage()
                .videoSectionOpen()
                .postOpen()
                .videoPlayButtonPressAndFullSize();
        Assert.assertTrue(autoPage.getResultOfScalingToFullScreen());
    }

    @AfterClass(description = "close browser")
    public void kill() {
        WebDriverSingleton.kill();
    }
}