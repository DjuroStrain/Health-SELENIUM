package hr.vuv.health.pageobject.registracija;

import hr.vuv.health.pageobject.commonelements.CommonHealthElements;
import io.qameta.allure.Step;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.Pages;

import java.rmi.Remote;

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
                                                  String sAdresa, String sDatumRodenja, String sOMeni, String sLozinka) throws InterruptedException {
        healthElements.insertText(txtKorisnickoIme, sKorisnickoIme);
        healthElements.insertText(txtIme, sIme);
        healthElements.insertText(txtPrezime, sPrezime);
        healthElements.insertText(txtEmail, sEmail);
        healthElements.insertText(txtAdresa, sAdresa);
        healthElements.insertText(txtDatumRodenja, sDatumRodenja);
        healthElements.insertText(txtOMeni, sOMeni);
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
    public void registracijaKorisnikaKaoDoktora(String sDoktor, String sKorisnickoIme, String sIme, String sPrezime, String sEmail,
                                                  String sAdresa, String sDatumRodenja, String sOMeni,
                                                String sLozinka) throws InterruptedException {
        healthElements.selectFromDropdown(ddVrstaKorisnika, sDoktor);
        healthElements.insertText(txtKorisnickoIme, sKorisnickoIme);
        healthElements.insertText(txtIme, sIme);
        healthElements.insertText(txtPrezime, sPrezime);
        healthElements.insertText(txtEmail, sEmail);
        healthElements.insertText(txtAdresa, sAdresa);
        healthElements.insertText(txtDatumRodenja, sDatumRodenja);
        healthElements.insertText(txtOMeni, sOMeni);
        healthElements.insertText(txtLozinka, sLozinka);
        healthElements.scrollToElement();
        healthElements.waitForElementToBeVisible(btnPrijava2);
        Thread.sleep(1000);
        healthElements.waitForElementToBeClickable(btnKreirajRacun);
        healthElements.waitForElementToBeVisible(btnPrijaviSe);
    }
}
