package pf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pf.pages.AutoPagePf;
import pf.pages.FilmsPagePf;
import pf.pages.FinancePagePf;
import pf.pages.HomePagePf;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TutByTestPf {
    private WebDriver driver;

    @BeforeClass(description = "Start browser")
    private void initBrowser() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        //https://www.youtube.com/watch?v=k4c17X6cXxQ//Но чет не работает
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.block_third_party_cookies", false);
        prefs.put("frames", false);
        prefs.put("cookies", false);
        prefs.put("javascript", false);
        prefs.put("applicationCacheEnabled", true);
        options.setExperimentalOption("prefs", prefs);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities);
        driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
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
    public void afishaTutByTest() throws InterruptedException {
        HomePagePf homePage = new HomePagePf(driver)
                .open()
                .afishaOpen();
        FilmsPagePf filmsPage = new FilmsPagePf(driver)
                .openFilms()
                .moveAvailableDatesToRight()
                .selectDate()
                .popupWindowClose()
                .selectTime()
                .selectFilm();
        int i = filmsPage.allShots();
        Assert.assertEquals(i, 4, "There are four shots to this film");
    }

    @Test(description = "Финансы.tut.by")
    /**    ----Финансы.tut.by
     1) Пройти в финансы
     2) Выбрать кредит
     3) В дополнительных условиях - Беларусбанк
     4) Сумма кредита 3000
     5) Убедиться, что есть хоть один кредит со ставкой > 15%*/
    public void financeTutByTest() throws InterruptedException {
        HomePagePf homePage = new HomePagePf(driver)
                .open()
                .financeOpen();
        FinancePagePf financePage = new FinancePagePf(driver)
                .pressChoseCreditButton()
                .setBelarusBankAsOptionForCredit()
                .setSumOfCredit()
                .getResults();
    }

    @Test(description = "Auto.tut.by")
    /**    ----Авто.tut.by
     1) Активировать версию для смартфонов. (футер)
     2) Пройти в секцию авто
     3) Рубрика видео
     4) Открыть любой пост
     5) Активировать плеер
     6) Подождать 15 сек.
     7) Развернуть плеер на весь экран
     8) Проверить, что развернулся на весь экран.*/
    public void autoTutByTest() throws InterruptedException {
        HomePagePf homePage = new HomePagePf(driver)
                .open()
                .mobileVersionEnable()
                .autoOpen();
        AutoPagePf autoPage = new AutoPagePf(driver)
                .videoSectionOpen()
                .postOpen()
                .videoPlayButtonPressAndFullSize()
                .assertScalingToFullScreen();
    }

    @AfterClass(description = "close browser")
    public void kill() {
        driver.close();
    }
}