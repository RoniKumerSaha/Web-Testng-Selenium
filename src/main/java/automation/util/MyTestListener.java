package automation.util;

import automation.pages.BasePage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class MyTestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        System.out.println(result.getName());
        getScreenShot(result);

    }

    public void getScreenShot(ITestResult result){
        TakesScreenshot camera = (TakesScreenshot) BasePage.getWebDriver();
        File screenshot = camera.getScreenshotAs(OutputType.FILE);
        try {
            com.google.common.io.Files.move(screenshot, new File("output/"+ result.getName()+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
