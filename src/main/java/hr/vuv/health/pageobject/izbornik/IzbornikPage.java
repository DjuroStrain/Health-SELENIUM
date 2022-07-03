package hr.vuv.health.pageobject.izbornik;

import hr.vuv.health.pageobject.commonelements.CommonHealthElements;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.Pages;

public class IzbornikPage extends Pages {

    public IzbornikPage(WebDriver driver) {
        super(driver);
    }

    private CommonHealthElements healthElements = new CommonHealthElements(driver);

    //Pocetna stranica

    @FindBy(xpath = "//a[normalize-space()='Početna']")
    private WebElement tabPocetna;

    @Step("U izborniku klikni na 'Pocetna'.")
    public void klikniIzbornikPocetna() {
        healthElements.waitForElementToBeClickable(tabPocetna);
    }

    //Lijecnici

    @FindBy(xpath = "//a[contains(text(), 'Lječnici')]")
    private WebElement tabLijecnici;

    @Step("U izborniku klikni na 'Lječnici'.")
    public void klikniIzbornikLijecnici() {
        healthElements.waitForElementToBeClickable(tabLijecnici);
        //tabLijecnici.click();
    }

    //Prijava

    @FindBy(xpath = "//a[normalize-space()='Prijava']")
    private WebElement tabPrijava;

    @FindBy(xpath = "//span[2]")
    private WebElement close;

    @Step("U izborniku klikni na 'Prijava'.")
    public void klikniIzbornikPrijava() {
        healthElements.waitForElementToBeClickable(tabPrijava);
    }

    //Registracija

    @FindBy(xpath = "//a[normalize-space()='Registracija']")
    private WebElement tabRegistracija;

    @Step("U izborniku klikni na 'Registracija'.")
    public void klikniIzbornikRegistracija() {
        healthElements.waitForElementToBeClickable(tabRegistracija);
    }


    /*
    * Nakon prijave - doktor
    * */

    //Kontrolna ploca

    @FindBy(xpath = "//a[normalize-space()='Kontrolna ploča']")
    private WebElement tabKontrolnaPloca;

    public void klikniIzbornikKontrolnaPloca() {
        healthElements.waitForElementToBeClickable(tabKontrolnaPloca);
    }

    //Moj profil

    @FindBy(xpath = "//a[normalize-space()='Moj profil']")
    private WebElement tabMojProfil;

    public void klikniIzbornikMojProfil() {
        healthElements.waitForElementToBeClickable(tabMojProfil);
    }

    //Radno vrijeme

    @FindBy(xpath = "//a[normalize-space()='Radno vrijeme']")
    private WebElement tabRadnoVrijeme;

    public void klikniIzbornikRadnoVrijeme() {
        healthElements.waitForElementToBeClickable(tabRadnoVrijeme);
    }

    /*
     * Nakon prijave - pacijent
     * */

    //Moji termini

    @FindBy(xpath = "//a[normalize-space()='Moji termini']")
    private WebElement tabMojiTermini;

    public void klikniIzbornikMojiTermini() {
        healthElements.waitForElementToBeClickable(tabMojiTermini);
    }


    //Odjava

    @FindBy(xpath = "//a[normalize-space()='Odjava']")
    private WebElement tabOdjava;

    public void klikniIzbornikOdjava() {
        healthElements.waitForElementToBeClickable(tabOdjava);
    }

}
