package automation.util;

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
import java.util.logging.Level;

public class DriverSetUp {
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static void setDriver() {
        String driverPath;
        switch (System.getProperty("os.name")){
            case "Linux":
                driverPath = "/src/main/resources/chromedriverLinux";
                break;
            case "Windows":
                driverPath = "/src/main/resources/chromedriver.exe";
            default:
                driverPath = "/src/main/resources/chromedriver";
        }
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ driverPath);

        // getting rid of the logging msg
        System.setProperty("webdriver.chrome.silentOutput", "true");
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);

        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);
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
