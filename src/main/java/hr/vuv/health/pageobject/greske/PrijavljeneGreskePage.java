package hr.vuv.health.pageobject.greske;

import hr.vuv.health.pageobject.commonelements.CommonHealthElements;
import io.qameta.allure.Step;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.HealthCheckFailedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.Pages;

public class PrijavljeneGreskePage extends Pages {

    public PrijavljeneGreskePage(WebDriver driver) {
        super(driver);
    }

    private CommonHealthElements healthElements = new CommonHealthElements(driver);

    @FindBy(xpath = "//div[contains(text(), 'ivanhorvat')]")
    private WebElement emailKorisnika;

    @FindBy(xpath = "//div[contains(text(), 'ivanhorvat')]//following::div[contains(@class, 'content')]")
    private WebElement opisGreske;

    @Step("Provjera ispravnosti prikaza prijavljene greške")
    public String provjeraIspravnostiPrikazaPrijavljeneGreske() {
        healthElements.waitForElementToBeVisible(emailKorisnika);
        return opisGreske.getText();
    }
}
