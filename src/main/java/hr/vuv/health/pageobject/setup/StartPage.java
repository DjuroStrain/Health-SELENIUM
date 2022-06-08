package hr.vuv.health.pageobject.setup;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selenium.Pages;

public class StartPage extends Pages {

    public StartPage(final WebDriver driver) {
        super(driver);
    }

    @Step("Pokretanje aplikacije")
    public void startApplication() {
        super.open();
    }

}
