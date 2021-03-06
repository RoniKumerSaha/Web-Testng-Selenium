package automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.locators.RelativeLocator;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class HomePage extends BasePage{
    @FindBy(id = "login2")
    WebElement loginLink;

    @FindBy(id = "loginusername")
    WebElement usernameInput;

    @FindBy(id = "loginpassword")
    WebElement passwordInput;

    @FindBy(xpath = "//button[text()='Log in']")
    WebElement loginBtn;

    @FindBy(id = "nameofuser")
    public WebElement userProfileName;


    public void loginWith(String username, String password){
        waitFor(loginLink);
        loginLink.click();
        waitFor(usernameInput);
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginBtn.click();
    }

    public String getPageHeading(){
        return getWebDriver().findElement(
                with(
                        By.id("nava"))
                        .toLeftOf(By.id("navbarExample"))
                ).getText();
    }

}
