package listeners;

import hr.vuv.health.base.BaseClass;
import io.qameta.allure.Allure;
import junit.runner.TestRunListener;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class TestListener extends TestWatcher {

    public static WebDriver driver;

    public TestListener (WebDriver driver) {
        this.driver = driver;
    }

    public void takeScreenShotWhenTestFails() {
        Allure.addAttachment("Snimka zaslona kada test nije prošao:", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Override
    protected void failed(Throwable e, Description description) {
        //TakesScreenshot takesScreenshot = (TakesScreenshot) driver;

        takeScreenShotWhenTestFails();
    }
}
