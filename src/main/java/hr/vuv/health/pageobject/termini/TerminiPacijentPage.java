package hr.vuv.health.pageobject.termini;

import hr.vuv.health.pageobject.commonelements.CommonHealthElements;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import selenium.Pages;

import java.util.List;

public class TerminiPacijentPage extends Pages {

    public TerminiPacijentPage(WebDriver driver) {
        super(driver);
    }

    private CommonHealthElements healthElements = new CommonHealthElements(driver);

    @FindBy(xpath = "//button[normalize-space()='Termini']")
    private WebElement accTermini;
    @FindBy(xpath = "//a[normalize-space()='Rezerviraj termin']")
    private WebElement btnRezervirajTermin;
    @FindBy(xpath = "//span[normalize-space()='Month']")
    private WebElement btnMjesec;
    @FindBy(xpath = "//div[@class='e-content-wrap']//following-sibling::table//tbody//tr[5]//td[5]")
    private WebElement zadnjiPetakKalendar;
    @FindBy(xpath = "//td[normalize-space()='Predmet']//following::td//input")
    private WebElement txtPredmetTermina;
    @FindBy(xpath = "//td[normalize-space()='Opis']//following::td//textarea")
    private WebElement txtOpisTermina;
    @FindBy(xpath = "//td[normalize-space()='Željeni termin']//following::td//span//span[2]")
    private WebElement btnOdaberiVrijemeTermina;
    @FindBy(xpath = "//div[contains(@class, 'e-control e-datetimepicker')]//following::li[normalize-space()='10:00']")
    private WebElement liVrijemeTermina;
    @FindBy(xpath = "//td[normalize-space()='Predmet']//following::button[normalize-space()='Save']")
    private WebElement btnSpremiTermin;

    /*
    * Odaberi termin
    * */
    @Step("Odaberi termin pregleda")
    public void odaberiTerminPregleda(String sPredmetTermina, String sOpisTermina) {
        healthElements.waitForElementToBeClickable(accTermini);
        healthElements.waitForElementToBeClickable(btnRezervirajTermin);
        healthElements.waitForElementToBeClickable(btnMjesec);
        healthElements.waitForElementToBeVisible(zadnjiPetakKalendar);
        Actions actions = new Actions(driver);
        actions.doubleClick(zadnjiPetakKalendar).perform();
        healthElements.waitForElementToBeVisible(txtOpisTermina);
        healthElements.insertText(txtOpisTermina, sOpisTermina);
        healthElements.insertText(txtPredmetTermina, sPredmetTermina);
        healthElements.waitForElementToBeClickable(btnOdaberiVrijemeTermina);
        healthElements.waitForElementToBeClickable(liVrijemeTermina);
        healthElements.waitForElementToBeClickable(btnSpremiTermin);
        healthElements.waitForElementToBeVisible(txtOpisPregledaTablica);
    }

    /*
     * Odaberi termin
     * */

    @FindBy(xpath = "//div[contains(@class, 'e-control e-datetimepicker')]//following::li[normalize-space()='00:00']")
    private WebElement liVrijemeTerminaVanRadnogVremena;
    @Step("Odaberi termin pregleda van radnog vremena")
    public void odaberiTerminPregledaVanRadnogVremena(String sPredmetTermina, String sOpisTermina) {
        healthElements.waitForElementToBeClickable(accTermini);
        healthElements.waitForElementToBeClickable(btnRezervirajTermin);
        healthElements.waitForElementToBeClickable(btnMjesec);
        healthElements.waitForElementToBeVisible(zadnjiPetakKalendar);
        Actions actions = new Actions(driver);
        actions.doubleClick(zadnjiPetakKalendar).perform();
        healthElements.waitForElementToBeVisible(txtOpisTermina);
        healthElements.insertText(txtOpisTermina, sOpisTermina);
        healthElements.insertText(txtPredmetTermina, sPredmetTermina);
        healthElements.waitForElementToBeClickable(btnOdaberiVrijemeTermina);
        healthElements.waitForElementToBeClickable(liVrijemeTerminaVanRadnogVremena);
        healthElements.waitForElementToBeClickable(btnSpremiTermin);
    }


    @FindBy(xpath = "//div[@class='e-appointment-details']//div[@class='e-subject']")
    private WebElement txtOpisPregledaTablica;

    @FindBy(xpath = "//div[@class='e-appointment-details']//div[@class='e-time']")
    private WebElement txtVrijemePregledaTablica;

    public String vratiVrijednostOpisPregledaTablica() {
        healthElements.waitForElementToBeVisible(txtOpisPregledaTablica);
        return txtOpisPregledaTablica.getText();
    }

    public String vratiVrijednostVrijemePregledaTablica() {
        return txtVrijemePregledaTablica.getText();
    }

    /*
    * Uredi termin
    * */
    @Step("Uredi termin")
    public void urediTermin(String sAzuriraniPredmet, String sAzuriraniOpis) {
        healthElements.waitForElementToBeClickable(accTermini);
        healthElements.waitForElementToBeClickable(btnRezervirajTermin);
        healthElements.waitForElementToBeVisible(cardTermin);
        Actions actions = new Actions(driver);
        actions.doubleClick(cardTermin).perform();
        healthElements.waitForElementToBeVisible(txtOpisTermina);
        healthElements.insertText(txtPredmetTermina, sAzuriraniPredmet);
        healthElements.insertText(txtOpisTermina, sAzuriraniOpis);
        healthElements.waitForElementToBeClickable(btnSpremiTermin);
    }

    @FindBy(className = "e-appointment-details")
    private WebElement cardTermin;

    @FindBy(xpath = "//button[normalize-space()='Delete']")
    private WebElement btnObrisiTermin;

    @FindBy(xpath = "//div[contains(@id, 'dialog')]//following-sibling::button[normalize-space()='Delete']")
    private WebElement btnPotvrdiBrisanjeTermina;

    @Step("Odaberi termin pregleda")
    public void obrisiTerminPregleda() {
        healthElements.waitForElementToBeClickable(accTermini);
        healthElements.waitForElementToBeClickable(btnRezervirajTermin);
        healthElements.waitForElementToBeVisible(cardTermin);
        Actions actions = new Actions(driver);
        actions.doubleClick(cardTermin).perform();
        healthElements.waitForElementToBeClickable(btnObrisiTermin);
        healthElements.waitForElementToBeVisible(btnPotvrdiBrisanjeTermina);
        healthElements.waitForElementToBeClickable(btnPotvrdiBrisanjeTermina);
    }

    @Step("Provjera je li se termin uspjšeno obrisao")
    public int terminIsDisplayed() {
        healthElements.waitForElementToBeClickable(btnMjesec);
        List<WebElement> terminElement = driver.findElements(By.className("e-appointment-details"));
        return terminElement.size();
    }
}
