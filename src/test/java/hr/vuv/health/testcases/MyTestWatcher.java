package hr.vuv.health.testcases;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.util.Optional;


public class MyTestWatcher implements TestWatcher {

    WebDriver driver;

    public MyTestWatcher(WebDriver driver) {
        this.driver = driver;
    }


    @Override
    public void testSuccessful(ExtensionContext context) {
        driver.quit();
    }

    @Override
    public void testFailed(ExtensionContext extensionContext, Throwable throwable) {
        Allure.addAttachment("Snimka zaslona kada test nije prošao:", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        driver.quit();
    }

    @Override
    public void testAborted(ExtensionContext extensionContext, Throwable throwable) {
        driver.quit();
    }

    @Override
    public void testDisabled(ExtensionContext extensionContext, Optional<String> optional) {
        driver.quit();
    }

}
