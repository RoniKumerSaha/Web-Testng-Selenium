package browser_tests;

import automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class HomePageTests extends BaseTest{

    @Test(testName = "Checking home page title")
    public void testHomePageTitle(){
        Assert.assertEquals(new HomePage().getPageTitle(), "STORxE", "Title is different");
    }

    @Test(dataProvider = "getInvalidLoginData", testName = "Check login using invalid data")
    public void testLoginWithInvalid(List<String> credentials){
        new HomePage().loginWith(credentials.get(0), credentials.get(1));
        String msg = new HomePage().getAlertText();
        new HomePage().acceptAlert();
        Assert.assertEquals(msg, "User does not exist.");
    }

    @Test(testName = "Check Login with valid data")
    public void testLoginWithValidCredentials(){
        new HomePage().loginWith("test", "test");
        new HomePage().waitFor(new HomePage().userProfileName);
        Assert.assertEquals(new HomePage().getPageTitle(), "STORE", "Title is different");
        Assert.assertTrue(new HomePage().userProfileName.isDisplayed());
    }

}
