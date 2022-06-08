package hr.vuv.health.pageobject.mojprofil;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.Pages;

public class MojProfilDoktorPage extends Pages {

    public MojProfilDoktorPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//label[normalize-space()='Ime']//following::input")
    private WebElement txtIme;

    @FindBy(xpath = "//label[normalize-space()='Prezime']//following::input")
    private WebElement txtPrezime;

    @FindBy(xpath = "//label[normalize-space()='Grad/Mjesto']//following::input")
    private WebElement txtGradMjesto;

    @FindBy(xpath = "//label[normalize-space()='Email']//following::input")
    private WebElement txtEmail;

    @Step("Vrati vrijednost polja 'Ime'")
    public String vratiVrijednostPoljaIme() {

        try {
            Thread.sleep(2000);
        }catch (Exception e){

        }

        return txtIme.getAttribute("value");
    }

    @Step("Vrati vrijednost polja 'Prezime'")
    public String vratiVrijednostPoljaPrezime() {
        return txtPrezime.getAttribute("value");
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
