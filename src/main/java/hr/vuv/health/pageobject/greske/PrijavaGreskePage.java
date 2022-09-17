package hr.vuv.health.pageobject.greske;

import hr.vuv.health.pageobject.commonelements.CommonHealthElements;
import io.qameta.allure.Step;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.Pages;

public class PrijavaGreskePage extends Pages {

    public PrijavaGreskePage(WebDriver driver) {
        super(driver);
    }

    private CommonHealthElements healthElements = new CommonHealthElements(driver);

    @FindBy(xpath = "//input[@type='email']")
    private WebElement txtEmail;

    @FindBy(xpath = "//textarea[@placeholder='Unesite opis greške']")
    private WebElement txtOpisGreske;

    @FindBy(xpath = "//button[normalize-space()='Pošalji']")
    private WebElement btnPosalji;

    @Step("Prijavi grešku")
    public void prijaviGresku(String sEmail, String sOpisGreske) {
        healthElements.waitForElementToBeVisible(txtEmail);
        healthElements.insertText(txtEmail, sEmail);
        healthElements.insertText(txtOpisGreske, sOpisGreske);
        healthElements.waitForElementToBeClickable(btnPosalji);
    }
}
