package hr.vuv.health.pageobject.termini;

import hr.vuv.health.pageobject.commonelements.CommonHealthElements;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import selenium.Pages;

public class TerminiPage extends Pages {

    public TerminiPage(WebDriver driver) {
        super(driver);
    }

    private CommonHealthElements healthElements = new CommonHealthElements(driver);

    @FindBy(xpath = "//div[@class='e-content-wrap']//following-sibling::tbody//tr[21]")
    private WebElement calTermin;

    @FindBy(xpath = "//td[normalize-space()='Naslov']//following::td//input")
    private WebElement txtOpisTermina;

    @FindBy(xpath = "//td[normalize-space()='Razlog']//following::td//textarea")
    private WebElement txtRazlogTermina;

    @FindBy(xpath = "//td[normalize-space()='Razlog']//following::button[normalize-space()='Save']")
    private WebElement btnSpremiTermin;

    @Step("Odaberi termin pregleda")
    public void odaberiTerminPregleda(String sOpisTermina, String sRazlogTermina) {
        healthElements.waitForElementToBeVisible(calTermin);
        Actions actions = new Actions(driver);
        actions.doubleClick(calTermin).perform();
        healthElements.waitForElementToBeVisible(txtOpisTermina);
        healthElements.insertText(txtOpisTermina, sOpisTermina);
        healthElements.insertText(txtRazlogTermina, sRazlogTermina);
        healthElements.waitForElementToBeClickable(btnSpremiTermin);
        healthElements.waitForElementToBeVisible(txtOpisPregledaTablica);
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
}
