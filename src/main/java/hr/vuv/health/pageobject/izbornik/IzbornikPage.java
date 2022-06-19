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

    //Početna stranica

    @FindBy(xpath = "//a[normalize-space()='Početna']")
    private WebElement tabPocetna;

    @Step("U izborniku klikni na 'Početna'.")
    public void klikniIzbornikPocetna() {
        healthElements.waitForElementToBeClickable(tabPocetna);
    }

    //Liječnici

    @FindBy(xpath = "//a[normalize-space()='Lječnici']")
    private WebElement tabLijecnici;

    @Step("U izborniku klikni na 'Lječnici'.")
    public void klikniIzbornikLijecnici() {
        healthElements.waitForElementToBeClickable(tabLijecnici);
    }

    //Prijava

    @FindBy(xpath = "//a[normalize-space()='Prijava']")
    private WebElement tabPrijava;

    @FindBy(xpath = "//span[2]")
    private WebElement close;

    @Step("U izborniku klikni na 'Prijava'.")
    public void klikniIzbornikPrijava() {
        healthElements.waitForElementToBeClickable(close);
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
    * Nakon prijava
    * */

    //Kontrolna ploča

    @FindBy(xpath = "//a[normalize-space()='Kontrolna ploča']")
    private WebElement tabKontrolanPloca;

    public void klikniIzbornikKontrolnaPloca() {
        healthElements.waitForElementToBeClickable(tabKontrolanPloca);
    }

    //Moj profil

    @FindBy(xpath = "//a[normalize-space()='Moj profil']")
    private WebElement tabMojProfil;

    public void klikniIzbornikMojProfil() {
        healthElements.waitForElementToBeClickable(close);
        healthElements.waitForElementToBeClickable(tabMojProfil);
    }

    //Odjava

    @FindBy(xpath = "//a[normalize-space()='Odjava']")
    private WebElement tabOdjava;

    public void klikniIzbornikOdjava() {
        healthElements.waitForElementToBeClickable(tabOdjava);
    }

}
