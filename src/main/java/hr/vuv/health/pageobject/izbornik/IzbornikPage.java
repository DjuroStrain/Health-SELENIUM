package hr.vuv.health.pageobject.izbornik;

import hr.vuv.health.base.BaseClass;
import hr.vuv.health.pageobject.commonelements.CommonHealthElements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IzbornikPage extends BaseClass {

    public IzbornikPage(WebDriver driver) {
        super();
        PageFactory.initElements(driver, this);
    }

    private CommonHealthElements healthElements = new CommonHealthElements();

    //Početna stranica

    @FindBy(xpath = "//a[normalize-space()='Početna']")
    private WebElement tabPocetna;

    public void klikniIzbornikPocetna() {
        healthElements.waitForElementToBeClickable(tabPocetna);
    }

    @FindBy(xpath = "//a[normalize-space()='Lječnici']")
    private WebElement tabLijecnici;

    public void klikniIzbornikLijecnici() {
        healthElements.waitForElementToBeClickable(tabLijecnici);
    }

    @FindBy(xpath = "//a[normalize-space()='Prijava']")
    private WebElement tabPrijava;

    public void klikniIzbornikPrijava() {
        healthElements.waitForElementToBeClickable(tabPrijava);
    }

    @FindBy(xpath = "//a[normalize-space()='Registracija']")
    private WebElement tabRegistracija;

    public void klikniIzbornikRegistracija() {
        healthElements.waitForElementToBeClickable(tabRegistracija);
    }

}
