package hr.vuv.health.pageobject.prijava;

import hr.vuv.health.base.BaseClass;
import hr.vuv.health.pageobject.commonelements.CommonHealthElements;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PrijavaPage extends BaseClass {

    public PrijavaPage(WebDriver driver){
        super();
        PageFactory.initElements(driver, this);
    }

    private CommonHealthElements healthElements = new CommonHealthElements();

    @FindBy(id = "username")
    private WebElement txtKorisnickoIme;

    @FindBy(id = "password")
    private WebElement txtLozinka;

    @FindBy(xpath = "//span[contains(text(), 'Prijavi')]//parent::button")
    private WebElement btnPrijaviSe;

    @Step("Prijava page")
    public void prijavaKorisnika(String sKorisnickoIme, String sLozinka) {
        healthElements.insertText(txtKorisnickoIme, sKorisnickoIme);
        healthElements.insertText(txtLozinka, sLozinka);
        healthElements.waitForElementToBeClickable(btnPrijaviSe);

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(btnPrijaviSe));
    }
}
