package utility;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selenium.configurations.TypedProperties;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.security.InvalidParameterException;
import java.text.MessageFormat;
import java.util.Properties;

import static java.lang.Boolean.parseBoolean;

public class ScreenshotClass {

    private WebDriver driver;

    static Logger log = LoggerFactory.getLogger(ScreenshotClass.class);

    public ScreenshotClass(WebDriver driver) {
        this.driver = driver;
    }

    public void takeScreenShotWhenTestFails() {
        //Allure.addAttachment("Snimka zaslona kada test nije prošao:", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }
/*
    public void quit() {
        log.info("Zatvaranje preglednika");

        boolean zatvoriPreglednik = new TypedProperties("/config.properties").getBoolean("close_browser_on_test_end");

        if(zatvoriPreglednik){
            driver.quit();
        }else {
            chromedriverKiller();
        }
    }
*/
    public void chromedriverKiller() {
        try{
            /*kill the process*/
            Runtime.getRuntime().exec("taskkill /im chromedriver.exe /f");
            Thread.sleep(10000);
        }catch(Exception e){
            e.printStackTrace();
        }
        /*Exit*/
        System.exit(0);
    }
}
