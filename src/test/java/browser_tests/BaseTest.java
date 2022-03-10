package browser_tests;

import automation.helpers.DataHelper;
import automation.pages.BasePage;
import automation.util.DriverSetUp;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class BaseTest {
    @BeforeMethod
    public void startBrowser(){
        DriverSetUp.gotoHomePage();
    }

    @AfterMethod()
    public void stopBrowser(ITestResult result){
        System.out.println(result);
        if(ITestResult.FAILURE == result.getStatus()){
            getScreenShot(result);
        }
        DriverSetUp.quitBrowser();
    }


    @DataProvider
    public Object[] getInvalidLoginData(){
        Map<Integer, List<String>> dataMap = DataHelper.readFile("src/main/resources/testdata.xlsx");
        return DataHelper.formatData(dataMap);
    }

    public void getScreenShot(ITestResult result){
        TakesScreenshot camera = (TakesScreenshot) BasePage.getWebDriver();
        File screenshot = camera.getScreenshotAs(OutputType.FILE);
        try {
            com.google.common.io.Files.move(screenshot, new File("output/"+ result.getName() +".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
