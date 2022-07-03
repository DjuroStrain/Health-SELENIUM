package hr.vuv.health.testcases;

import io.qameta.allure.Allure;
import org.junit.Rule;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.Set;


public class MyTestWatcher implements TestWatcher {

    private final static Logger log = LoggerFactory.getLogger(MyTestWatcher.class);
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


    String testName;
    String className;

    @Rule
    public void starting(Description description) {
        testName = description.getMethodName();
        className = description.getClassName();
        log.info("Starting test " + testName + " in class " + className);
    }

}
