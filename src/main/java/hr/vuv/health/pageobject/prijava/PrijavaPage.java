package hr.vuv.health.pageobject.prijava;

import hr.vuv.health.pageobject.commonelements.CommonHealthElements;
import hr.vuv.health.pageobject.izbornik.IzbornikPage;
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

    private IzbornikPage izbornikPage = new IzbornikPage(driver);

    @FindBy(id = "username")
    private WebElement txtKorisnickoIme;

    @FindBy(id = "password")
    private WebElement txtLozinka;

    @FindBy(xpath = "//span[contains(text(), 'Prijavi')]//parent::button")
    private WebElement btnPrijaviSe;

    @FindBy(xpath = "//a[normalize-space()='Moj profil']")
    private WebElement tabMojProfil;

    @Step("Prijava doktora u aplikaciju i provjera je li se uspješno prijavio.")
    public void prijavaKorisnika(String sKorisnickoIme, String sLozinka) {
        healthElements.scrollToInsertText(txtKorisnickoIme, sKorisnickoIme);
        healthElements.scrollToInsertText(txtLozinka, sLozinka);
        healthElements.waitForElementToBeClickable(btnPrijaviSe);
    }

    public boolean tabMojProfilIsDisplayed() {
        healthElements.waitForElementToBeVisible(tabMojProfil);
        return tabMojProfil.isDisplayed();
    }

    @FindBy(xpath = "//a[normalize-space()='Moji termini']")
    private WebElement tabMojiTermini;

    @Step("Prijava pacijenta u aplikaciju i provjera je li se uspješno prijavio.")
    public void prijavaKorisnikaKaoPacijenta(String sKorisnickoIme, String sLozinka) {
        healthElements.insertText(txtKorisnickoIme, sKorisnickoIme);
        healthElements.insertText(txtLozinka, sLozinka);
        healthElements.waitForElementToBeClickable(btnPrijaviSe);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(btnPrijaviSe));
    }

    public boolean tabMojiTerminiIsDisplayed() {
        healthElements.waitForElementToBeVisible(tabMojiTermini);
        return tabMojiTermini.isDisplayed();
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

    //Prijava u aplikaciju
    public void prijavaUAplikaciju(String sKorisnickoIme, String sLozinka) {
        izbornikPage.klikniIzbornikPrijava();
        prijavaKorisnika(sKorisnickoIme, sLozinka);
    }
}
