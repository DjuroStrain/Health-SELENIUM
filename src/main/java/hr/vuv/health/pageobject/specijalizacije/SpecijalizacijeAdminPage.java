package hr.vuv.health.pageobject.specijalizacije;

import hr.vuv.health.content.SpecijalizacijeContent;
import hr.vuv.health.pageobject.commonelements.CommonHealthElements;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.Pages;

import java.util.List;

public class SpecijalizacijeAdminPage extends Pages {

    public SpecijalizacijeAdminPage(WebDriver driver) {
        super(driver);
    }

    private CommonHealthElements healthElements = new CommonHealthElements(driver);

    @FindBy(xpath = "//span[normalize-space()='Add']")
    private WebElement btnDodajSpecijalizaciju;
    @FindBy(xpath = "//span[normalize-space()='Update']")
    private WebElement btnAzuriraj;
    @FindBy(id = "Name")
    private WebElement txtNazivSpecijalizacije;

    @FindBy(id = "Description")
    private WebElement txtOpisSpecijalizacije;

    @FindBy(xpath = "//div[@class='e-gridcontent']//following-sibling::table//tbody//tr[last()]")
    private WebElement zadnjiRedTabliceSpeci;

    @FindBy(xpath = "//div[@class='e-gridcontent']//following-sibling::table//tbody//tr[last()]//td[3]")
    private WebElement tablicaOpisSpecijalizacije;

    @FindBy(xpath = "//td[normalize-space()='Hematologija']")
    private WebElement tablicaNazivSpecijalizacije;

    @Step("Dodaj specijalizaciju")
    public void dodajSpecijalizaciju(String sNazivSpecijalizacije, String sOpisSpecijalizacije) {
        healthElements.waitForElementToBeClickable(btnDodajSpecijalizaciju);
        healthElements.waitForElementToBeVisible(txtNazivSpecijalizacije);
        healthElements.insertText(txtNazivSpecijalizacije, sNazivSpecijalizacije);
        healthElements.insertText(txtOpisSpecijalizacije, sOpisSpecijalizacije);
        healthElements.waitForElementToBeClickable(btnAzuriraj);
    }

    public String vratiOpisSpecijalizacijeIzTablice() {
        healthElements.waitForElementToBeVisible(tablicaNazivSpecijalizacije);
        return tablicaOpisSpecijalizacije.getText();
    }

    public int provjeraDodavanjaSpecijalizacijeUBazu() throws ClassNotFoundException {
        return healthElements.vratiBrojSpecijalizacija(SpecijalizacijeContent.NAZIV_SPECIJALIZACIJE);
    }

    @FindBy(xpath = "//span[normalize-space()='Edit']")
    private WebElement btnAzurirajSpecijalizaciju;

    @FindBy(xpath = "//td[normalize-space()='Neurologija']")
    private WebElement tablicaAzuriraniNazivSpecijalizacije;

    @FindBy(xpath = "//div[@class='e-gridcontent']//following-sibling::table//tbody//tr[last()]//td[3]")
    private WebElement tablicaAzuraniOpisSpecijalizacije;
    @Step("Azuriraj specijalizaciju")
    public void azurirajSpecijalizaciju(String sAzuriraniNazivSpecijalizacije, String sAzuriraniOpisSpecijalizacije) {
        healthElements.waitForElementToBeClickable(zadnjiRedTabliceSpeci);
        healthElements.waitForElementToBeClickable(btnAzurirajSpecijalizaciju);
        healthElements.waitForElementToBeVisible(txtNazivSpecijalizacije);
        healthElements.insertText(txtNazivSpecijalizacije, sAzuriraniNazivSpecijalizacije);
        healthElements.insertText(txtOpisSpecijalizacije, sAzuriraniOpisSpecijalizacije);
        healthElements.waitForElementToBeClickable(btnAzuriraj);
    }

    public String vratiAzuriraniOpisSpecijalizacijeIzTablice() {
        healthElements.waitForElementToBeVisible(tablicaAzuriraniNazivSpecijalizacije);
        return tablicaAzuraniOpisSpecijalizacije.getText();
    }

    public int provjeraAzuriranjaSpecijalizacijeUBazi() throws ClassNotFoundException {
        return healthElements.vratiBrojSpecijalizacija(SpecijalizacijeContent.AZURIRANI_NAZIV_SPECIJALIZACIJE);
    }

    @FindBy(xpath = "//span[normalize-space()='Delete']")
    private WebElement btnObrisiSpecijalizaciju;

    @Step("Obrisi specijalizaciju")
    public void obrisiSpecijalizaciju() {
        healthElements.waitForElementToBeClickable(zadnjiRedTabliceSpeci);
        healthElements.waitForElementToBeClickable(btnObrisiSpecijalizaciju);
    }

    @Step("Provjera uspješnosti brisanja u tablici")
    public int provjeraBrisanjauTablici() {
        List<WebElement> provjeraBrisanja = driver.findElements(By.xpath("//td[normalize-space()='Hematologija']"));
        return provjeraBrisanja.size();
    }

}
