package hr.vuv.health.pageobject.obavijesti;

import hr.vuv.health.pageobject.commonelements.CommonHealthElements;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.Pages;

public class ObavijestiLjecniciPage extends Pages {

    public ObavijestiLjecniciPage(WebDriver driver) {
        super(driver);
    }

    private CommonHealthElements healthElements = new CommonHealthElements(driver);

    @FindBy(xpath = "//div[@id='NewIconCard']//following-sibling::div[contains(@class,'title')]")
    private WebElement naslovObavijesti;

    @FindBy(xpath = "//div[@id='NewIconCard']//following-sibling::div[contains(@class,'content')]")
    private WebElement sadrzajObavijesti;

    @Step("Dohvati naslov obavijesti")
    public String vratiNaslovObavijesti() {
        healthElements.waitForElementToBeVisible(naslovObavijesti);
        return naslovObavijesti.getText();
    }

    @Step("Dohvati sadrzaj obavijesti")
    public String vratiSadrzajObavijesti() {
        healthElements.waitForElementToBeVisible(naslovObavijesti);
        return sadrzajObavijesti.getText();
    }
}
