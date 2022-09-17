package hr.vuv.health.pageobject.termini;
import hr.vuv.health.content.PacijentContent;
import hr.vuv.health.pageobject.commonelements.CommonHealthElements;
import hr.vuv.health.pageobject.izbornik.IzbornikPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.Pages;

public class TerminiLijecnikaPage extends Pages {

    public TerminiLijecnikaPage(WebDriver driver) {
        super(driver);
    }
    private CommonHealthElements healthElements = new CommonHealthElements(driver);
    private IzbornikPage izbornikPage = PageFactory.initElements(driver, IzbornikPage.class);

    @FindBy(xpath = "//div[@class='row waiting']")
    private WebElement listaCekanja;

    @Step("Provjeri prikaz termina u listi čekanja")
    public void provjeriTerminUListiCekanja() {
        izbornikPage.klikniIzbornikTabTermini();
    }

    public boolean listaCekanjaIsDisplayed() {
        healthElements.waitForElementToBeVisible(listaCekanja);
        return listaCekanja.isDisplayed();
    }
}
