package ru.Norma24.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import ru.Norma24.pages.Norma24;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    WebDriver wd;
    private Norma24 norma24;

    public Norma24 norma24() {
        return norma24;
    }

    public void start(String browser) throws Exception {
        if (browser.equals(BrowserType.CHROME)) {
            File file = new File("C:/webdrivers/chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
            wd = new ChromeDriver();
        } else if (browser.equals(BrowserType.FIREFOX)) {
            wd = new FirefoxDriver();  //Прописать данные для FireFox
        } else if (browser.equals(BrowserType.IE)) {
            wd = new InternetExplorerDriver();  //Здесь прописать данные для IE
        } else System.out.println("Инициализация драйвера не выполнена");

        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wd.manage().window().maximize();
        wd.get("https://test.norma24.ru/");

        norma24 = new Norma24(wd);
    }

    public void stop() {
        wd.quit();
    }

    @Before
    public void beforeTest() throws Exception {
        start(BrowserType.CHROME);
    }

    @After
    public void afterTest() {
        stop();
    }
}
