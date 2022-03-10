package automation.pages;


import automation.util.DriverSetUp;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

import static org.openqa.selenium.support.PageFactory.initElements;

public class BasePage extends DriverSetUp {

    public BasePage() {
        initElements(getWebDriver(), this);
    }

    public String getPageTitle(){
        return getWebDriver().getTitle();
    }

    public void waitFor(WebElement element, int howLong){
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(getWebDriver())
                .withTimeout(Duration.ofSeconds(howLong))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class,NullPointerException.class);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void waitFor(WebElement element){
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(getWebDriver())
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class,NullPointerException.class);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToVanish(WebElement element){
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(getWebDriver())
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class,NullPointerException.class);
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitFor(int second){
        try {
            Thread.sleep((long)1000 * second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void acceptAlert(){
        waitFor(1);
        getWebDriver().switchTo().alert().accept();
    }

    public String getAlertText(){
        waitFor(1);
        return getWebDriver().switchTo().alert().getText();
    }
}
