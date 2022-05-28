package hr.vuv.health.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    public static Properties properties;
    //public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    public static WebDriver driver;
    public static WebDriver getDriver() {
        //dohvati WebDriver
        return driver;
    }

    public void loadConfig() {
        //ExtentManager.setExtent();
        //DOMConfigurator.configure("log4j.xml");

        try {
            properties = new Properties();
            FileInputStream ip = new FileInputStream(
                    System.getProperty("user.dir") + "\\config.properties");
            properties.load(ip);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startAppliaction(String browserName) {
        //String browserName = properties.getProperty("browser");

        if(browserName.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            //driver.set(new ChromeDriver());
            driver = new ChromeDriver();
        }

        //Uvecaj prozor
        getDriver().manage().window().maximize();

        //implicit timeouts
        getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(properties.getProperty("implicitWait")), TimeUnit.SECONDS);

        //Vrijeme cekanja za ucitavanje stranice
        getDriver().manage().timeouts().pageLoadTimeout(Integer.parseInt(properties.getProperty("pageLoadTimeOut")), TimeUnit.SECONDS);

        //Pokretanje URL-a
        getDriver().get(properties.getProperty("base_url"));
    }
}
