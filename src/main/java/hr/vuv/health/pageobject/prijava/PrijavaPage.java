package hr.vuv.health.pageobject.prijava;

import hr.vuv.health.pageobject.commonelements.CommonHealthElements;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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

    @FindBy(xpath = "//a[normalize-space()='Moj profil']")
    private WebElement tabMojProfil2;

    @Step("Prijava korisnika u aplikaciju i provjera je li se uspješno prijavio.")
    public void prijavaKorisnika(String sKorisnickoIme, String sLozinka) {
        healthElements.insertTextScrollTo(txtKorisnickoIme, sKorisnickoIme);
        healthElements.insertTextScrollTo(txtLozinka, sLozinka);
        healthElements.waitForElementToBeClickable(btnPrijaviSe);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(btnPrijaviSe));
    }

    public boolean tabMojProfilIsDisplayed() {
        healthElements.waitForElementToBeVisible(tabMojProfil);
        return tabMojProfil2.isDisplayed();
    }

    /*
     * Validacije
     * */

    @Step("Klikni na gumb 'Prijavi se' bez unosa obavezih podataka")
    public void klikniNaGumbPrijavaBezUnosaObaveznihPodataka() throws InterruptedException {
        healthElements.waitForElementToBeClickable(btnPrijaviSe);
    }

    @FindBy(xpath = "//input[@id='username']//following::div[@class='validation-message']")
    private WebElement txtValidacijaZaObaveznoPoljeKorisnickoIme;

    @Step("Dohvati validaciju za obavezno polje 'KorisnickoIme'")
    public String vratiValidacijuZaObaveznoPoljeKorisnickoIme() {
        healthElements.waitForElementToBeVisible(txtValidacijaZaObaveznoPoljeKorisnickoIme);
        return txtValidacijaZaObaveznoPoljeKorisnickoIme.getText();
    }

    @FindBy(xpath = "//input[@id='password']//following::div[@class='validation-message']")
    private WebElement txtValidacijaZaObaveznoPoljeLozinka;

    @Step("Dohvati validaciju za obavezno polje 'Lozinka'")
    public String vratiValidacijuZaObaveznoPoljeLozinka() {
        return txtValidacijaZaObaveznoPoljeLozinka.getText();
    }
}
