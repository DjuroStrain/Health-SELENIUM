package hr.vuv.health.pageobject.registracija;

import hr.vuv.health.pageobject.commonelements.CommonHealthElements;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.Pages;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RegistracijaPage extends Pages {

    public RegistracijaPage(WebDriver driver) {
        super(driver);
    }

    private CommonHealthElements healthElements = new CommonHealthElements(driver);

    @FindBy(id = "username")
    private WebElement txtKorisnickoIme;

    @FindBy(id = "firstName")
    private WebElement txtIme;

    @FindBy(id = "lastName")
    private WebElement txtPrezime;

    @FindBy(id = "email")
    private WebElement txtEmail;

    @FindBy(id = "address")
    private WebElement txtAdresa;

    @FindBy(id = "dateofbirth")
    private WebElement txtDatumRodenja;

    @FindBy(id = "aboutme")
    private WebElement txtOMeni;

    @FindBy(id = "number")
    private WebElement txtMobitel;

    @FindBy(id = "password")
    private WebElement txtLozinka;

    @FindBy(xpath = "//span[contains(text(), 'Kreiraj')]")
    private WebElement btnKreirajRacun;

    @FindBy(xpath = "//span[contains(text(), 'Prijavi')]//parent::button")
    private WebElement btnPrijaviSe;

    @FindBy(xpath = "//a[@class='text-primary ml-2']")
    private WebElement btnPrijava2;

    @Step("Registracija korisnika kao pacijenta.")
    public void registracijaKorisnikaKaoPacijenta(String sKorisnickoIme, String sIme, String sPrezime, String sEmail,
                                                  String sMobitel, String sLozinka) throws InterruptedException {
        healthElements.insertText(txtKorisnickoIme, sKorisnickoIme);
        healthElements.insertText(txtIme, sIme);
        healthElements.insertText(txtPrezime, sPrezime);
        healthElements.insertText(txtEmail, sEmail);
        healthElements.insertText(txtMobitel, sMobitel);
        healthElements.insertText(txtLozinka, sLozinka);
        healthElements.scrollToElement();
        healthElements.waitForElementToBeVisible(btnPrijava2);
        Thread.sleep(1000);
        healthElements.waitForElementToBeClickable(btnKreirajRacun);
        healthElements.waitForElementToBeVisible(btnPrijaviSe);
    }

    @FindBy(xpath = "//select")
    private WebElement ddVrstaKorisnika;

    @Step("Registracija korisnika kao doktora.")
    public void registracijaKorisnikaKaoDoktora(String sDoktor, String sKorisnickoIme, String sIme, String sPrezime, String sEmail, String sMobitel,
                                                String sLozinka) throws InterruptedException {
        healthElements.selectFromDropdown(ddVrstaKorisnika, sDoktor);
        healthElements.insertText(txtKorisnickoIme, sKorisnickoIme);
        healthElements.insertText(txtIme, sIme);
        healthElements.insertText(txtPrezime, sPrezime);
        healthElements.insertText(txtEmail, sEmail);
        healthElements.insertText(txtMobitel, sMobitel);
        healthElements.insertText(txtLozinka, sLozinka);
        healthElements.scrollToElement();
        healthElements.waitForElementToBeVisible(btnPrijava2);
        Thread.sleep(1000);
        healthElements.waitForElementToBeClickable(btnKreirajRacun);
        healthElements.waitForElementToBeVisible(btnPrijaviSe);
    }

    /*
    * Validacije
    * */

    @Step("Klikni na gumb 'Kreiraj svoj račun' bez unosa obavezih podataka")
    public void klikniNaGumbKreirajBezUnosaObaveznihPodataka() throws InterruptedException {
        healthElements.scrollToElement();
        healthElements.waitForElementToBeVisible(btnPrijava2);
        try{
            healthElements.waitForElementToBeClickable(btnKreirajRacun);
        }catch (ElementClickInterceptedException e) {
            healthElements.waitForElementToBeClickable(btnKreirajRacun);
        }
    }

    @FindBy(xpath = "//select[@id='roleSelect']//following::div[@class='validation-message']")
    private WebElement txtValidacijaZaObaveznoPoljeTitula;

    @Step("Dohvati validaciju za obavezno polje 'Titula'")
    public String vratiValidacijuZaObaveznoPoljeTitula() {
        healthElements.waitForElementToBeVisible(txtValidacijaZaObaveznoPoljeTitula);
        return txtValidacijaZaObaveznoPoljeTitula.getText();
    }

    @FindBy(xpath = "//input[@id='username']//following::div[@class='validation-message']")
    private WebElement txtValidacijaZaObaveznoPoljeKorisnickoIme;

    @Step("Dohvati validaciju za obavezno polje 'KorisnickoIme'")
    public String vratiValidacijuZaObaveznoPoljeKorisnickoIme() {
        return txtValidacijaZaObaveznoPoljeKorisnickoIme.getText();
    }

    @FindBy(xpath = "//input[@id='firstName']//following::div[@class='validation-message']")
    private WebElement txtValidacijaZaObaveznoPoljeIme;

    @Step("Dohvati validaciju za obavezno polje 'Ime'")
    public String vratiValidacijuZaObaveznoPoljeIme() {
        return txtValidacijaZaObaveznoPoljeIme.getText();
    }

    @FindBy(xpath = "//input[@id='lastName']//following::div[@class='validation-message']")
    private WebElement txtValidacijaZaObaveznoPoljePrezime;

    @Step("Dohvati validaciju za obavezno polje 'Prezime'")
    public String vratiValidacijuZaObaveznoPoljePrezime() {
        return txtValidacijaZaObaveznoPoljePrezime.getText();
    }

    @FindBy(xpath = "//input[@id='email']//following::div[@class='validation-message']")
    private WebElement txtValidacijaZaObaveznoPoljeEmail;

    @Step("Dohvati validaciju za obavezno polje 'Email'")
    public String vratiValidacijuZaObaveznoPoljeEmail() {
        return txtValidacijaZaObaveznoPoljeEmail.getText();
    }

    @FindBy(xpath = "//input[@id='number']//following::div[@class='validation-message']")
    private WebElement txtValidacijaZaObaveznoPoljeTelBroj;

    @Step("Dohvati validaciju za obavezno polje 'TelBroj'")
    public String vratiValidacijuZaObaveznoPoljeTelBroj() {
        return txtValidacijaZaObaveznoPoljeTelBroj.getText();
    }

    @FindBy(xpath = "//input[@id='password']//following::div[@class='validation-message']")
    private WebElement txtValidacijaZaObaveznoPoljeLozinka;

    @Step("Dohvati validaciju za obavezno polje 'Lozinka'")
    public String vratiValidacijuZaObaveznoPoljeLozinka() {
        return txtValidacijaZaObaveznoPoljeLozinka.getText();
    }
}
