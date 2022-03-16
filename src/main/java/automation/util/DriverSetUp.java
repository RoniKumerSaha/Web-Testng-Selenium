package automation.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverListener;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class DriverSetUp {
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static void setDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);
        options.addArguments("--silent");
        driver.set(new ChromeDriver(options));
        driver.set(setEventListener(driver.get()));
    }

    public static WebDriver setEventListener(WebDriver driver){
        MyWebDriverEventListener myWebdriverEventListener = new MyWebDriverEventListener();
        return new EventFiringDecorator(myWebdriverEventListener).decorate(driver);
    }

    public static void gotoHomePage(){
        setDriver();
        getWebDriver().get("https://www.demoblaze.com/");
    }

    public static WebDriver getWebDriver(){
        return driver.get();
    }

    public static void quitBrowser(){
        getWebDriver().quit();
    }
}
