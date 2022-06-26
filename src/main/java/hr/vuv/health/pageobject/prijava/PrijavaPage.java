package hr.vuv.health.pageobject.prijava;

import hr.vuv.health.pageobject.commonelements.CommonHealthElements;
import io.qameta.allure.Step;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.Pages;

import java.time.Duration;

public class PrijavaPage extends Pages {
    public PrijavaPage(WebDriver driver){
        super(driver);
    }

    private CommonHealthElements healthElements = new CommonHealthElements(driver);

    @FindBy(id = "username")
    private WebElement txtKorisnickoIme;

    @FindBy(id = "password")
    private WebElement txtLozinka;

    @FindBy(xpath = "//span[contains(text(), 'Prijavi')]//parent::button")
    private WebElement btnPrijaviSe;

    @FindBy(xpath = "//a[normalize-space()='Moj profil']")
    private WebElement tabMojProfil;

    @FindBy(xpath = "//a[normalize-space()='Moj profillll']")
    private WebElement tabMojProfil2;

    @Step("Prijava korisnika u aplikaciju i provjera je li se uspješno prijavio.")
    public void prijavaKorisnika(String sKorisnickoIme, String sLozinka) {
        healthElements.insertText(txtKorisnickoIme, sKorisnickoIme);
        healthElements.insertText(txtLozinka, sLozinka);
        healthElements.waitForElementToBeClickable(btnPrijaviSe);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(btnPrijaviSe));
    }

    public boolean tabMojProfilIsDisplayed() {
        healthElements.waitForElementToBeVisible(tabMojProfil);
        return tabMojProfil2.isDisplayed();
    }
}
