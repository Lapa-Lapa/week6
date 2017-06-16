package pf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pf.pages.FilmsPagePf;
import pf.pages.HomePagePf;

import java.util.concurrent.TimeUnit;

public class TutByTestPf {
    private WebDriver driver;

    @BeforeClass(description = "Start browser")
    private void initBrowser() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test(description = "Афиша.tut.by")
    /**    ----Афиша.tut.by
     1) Пройти в афишу
     2) Раздел Кино
     3) Выбрать 9 июля
     4) Установить временной промежуток от 20:00 до 2:00
     5) Выбрать Трансформеры
     6) Убедиться, что кадров из фильма 4 штуки*/
    public void afishaTutByFilmTest() throws InterruptedException {
        HomePagePf homePage = new HomePagePf(driver);
        homePage.open().afishaOpen();
        FilmsPagePf filmsPage = new FilmsPagePf(driver).openFilms();
        filmsPage.moveAvailableDatesToRight().moveAvailableDatesToRight().moveAvailableDatesToRight().moveAvailableDatesToRight().moveAvailableDatesToRight()
                .moveAvailableDatesToRight().moveAvailableDatesToRight().moveAvailableDatesToRight().moveAvailableDatesToRight().moveAvailableDatesToRight();
                filmsPage.selectDate();
        filmsPage.selectTime().selectFilm();
        int i = filmsPage.allShots();
        Assert.assertEquals(i, 4, "There are four shots to this film");
    }

    @AfterClass(description = "close browser")
    public void kill() {
        driver.close();
    }
}