package hr.vuv.health.pageobject.mojprofil;

import hr.vuv.health.pageobject.commonelements.CommonHealthElements;
import io.qameta.allure.Step;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.Pages;

import java.time.Duration;

public class MojProfilDoktorPage extends Pages {

    public MojProfilDoktorPage(WebDriver driver) {
        super(driver);
    }

    private CommonHealthElements commonHealthElements = new CommonHealthElements(driver);

    //Moj profil - Izbornik
    @FindBy(xpath = "//div[normalize-space()='Opći podaci']")
    private WebElement tabOpciPodaci;

    @FindBy(xpath = "//div[normalize-space()='Djelatnosti']")
    private WebElement tabDjelatnosti;

    @FindBy(xpath = "//div[normalize-space()='Radno vrijeme']")
    private WebElement tabRadnoVrijeme;

    @FindBy(xpath = "//label[normalize-space()='Ime']//following::input")
    private WebElement txtIme;

    @FindBy(xpath = "//label[normalize-space()='Prezime']//following::input")
    private WebElement txtPrezime;

    @FindBy(xpath = "//label[normalize-space()='Titula']//following::input")
    private WebElement txtTitula;

    @FindBy(xpath = "//label[normalize-space()='Ulica']//following::input")
    private WebElement txtUlica;

    @FindBy(xpath = "//label[normalize-space()='Kućni broj']//following::input")
    private WebElement txtKucniBroj;

    @FindBy(xpath = "//label[normalize-space()='Poštanski broj']//following::input")
    private WebElement txtPostanskiBroj;

    @FindBy(xpath = "//label[normalize-space()='Grad/Mjesto']//following::input")
    private WebElement txtGradMjesto;

    @FindBy(xpath = "//label[normalize-space()='Država']//following::input")
    private WebElement txtDrzava;

    @FindBy(xpath = "//label[normalize-space()='Email']//following::input")
    private WebElement txtEmail;

    @Step("Vrati vrijednost polja 'Ime'")
    public String vratiVrijednostPoljaIme() throws InterruptedException {
        try {
            commonHealthElements.waitForElementToBeVisible(txtIme);
        }catch (StaleElementReferenceException e) {
            commonHealthElements.waitForElementToBeVisible(txtIme);
            e.printStackTrace();
        }
        commonHealthElements.waitForElementToBeVisible(txtIme);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until((ExpectedCondition<Boolean>) driver -> (txtIme.getAttribute("value").length() > 0));
        return txtIme.getAttribute("value");
    }

    @Step("Vrati vrijednost polja 'Prezime'")
    public String vratiVrijednostPoljaPrezime() {
        return txtPrezime.getAttribute("value");
    }

    @Step("Vrati vrijednost polja 'Titula'")
    public String vratiVrijednostPoljaTitula() {
        return txtTitula.getAttribute("value");
    }

    @Step("Vrati vrijednost polja 'Ulica'")
    public String vratiVrijednostPoljaUlica() {
        return txtUlica.getAttribute("value");
    }

    @Step("Vrati vrijednost polja 'Kućni broj'")
    public String vratiVrijednostPoljaKucniBroj() {
        return txtKucniBroj.getAttribute("value");
    }

    @Step("Vrati vrijednost polja 'Poštanski broj'")
    public String vratiVrijednostPoljaPostanskiBroj() {
        return txtPostanskiBroj.getAttribute("value");
    }

    @Step("Vrati vrijednost polja 'Država'")
    public String vratiVrijednostPoljaDrzava() {
        return txtDrzava.getAttribute("value");
    }

    @Step("Vrati vrijednost polja 'Grad/Mjesto'")
    public String vratiVrijednostPoljaGradMjesto() {
        return txtGradMjesto.getAttribute("value");
    }

    @Step("Vrati vrijednost polja 'Email'")
    public String vratiVrijednostPoljaEmail() {
        return txtEmail.getAttribute("value");
    }
}
