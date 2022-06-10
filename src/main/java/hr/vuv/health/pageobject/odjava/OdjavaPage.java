package hr.vuv.health.pageobject.odjava;

import hr.vuv.health.pageobject.commonelements.CommonHealthElements;
import hr.vuv.health.pageobject.izbornik.IzbornikPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.Pages;

public class OdjavaPage extends Pages {

    public OdjavaPage(WebDriver driver) {
        super(driver);
    }

    private IzbornikPage izbornikPage = PageFactory.initElements(driver, IzbornikPage.class);
    private CommonHealthElements commonHealthElements = new CommonHealthElements(driver);

    @FindBy(xpath = "//a[normalize-space()='Odjava']")
    private WebElement tabOdjava;

    @FindBy(xpath = "//a[normalize-space()='Prijava']")
    private WebElement tabPrijava;

    @Step("Odjava korisnika iz aplikacije")
    public boolean odjavaKorisnikaIzAplikacije() {
        commonHealthElements.waitForElementToBeVisible(tabOdjava);
        commonHealthElements.waitForElementToBeClickable(tabOdjava);
        commonHealthElements.waitForElementToBeVisible(tabPrijava);

        return tabPrijava.isDisplayed();
    }
}
