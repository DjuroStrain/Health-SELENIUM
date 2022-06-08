package listeners;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import utils2.ScreenshotClass;

public class TestListener extends TestWatcher {
/*
    public static WebDriver driver;

    public ScreenshotClass screenshotClass = new ScreenshotClass(driver);*/

    /*public TestListener (WebDriver driver) {
        this.driver = driver;
    }*/
/*
    @Override
    protected void finished(Description description) {
        super.finished(description);
        if(driver != null){
            screenshotClass.quit();
        }
    }*/
    /*
    @Override
    protected void failed(Throwable e, Description description) {
        screenshotClass.takeScreenShotWhenTestFails();
    }*/
}
