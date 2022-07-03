package hr.vuv.health.pageobject.lijecnici;

import hr.vuv.health.pageobject.commonelements.CommonHealthElements;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.Pages;

import java.util.List;

public class LijecniciBezRolePage extends Pages {

    public LijecniciBezRolePage(WebDriver driver) {
        super(driver);
    }

    private CommonHealthElements healthElements = new CommonHealthElements(driver);

    @FindBy(xpath = "//div[@id='accordionExample']//div")
    private WebElement accFilter;

    @FindBy(xpath = "//div[@id='collapseTwo']//div//div//div[2]//span")
    private WebElement ddOdaberiSpecijalizaciju;

    @Step("Otvori filter i odaberi specijalizaciju")
    public void otvoriFilterOdaberiSpacijalzaciju(String sSpecijalzacija) {
        healthElements.waitForElementToBeClickable(accFilter);
        healthElements.waitForElementToBeClickable(ddOdaberiSpecijalizaciju);
        healthElements.selectFromUlLiDropdown(sSpecijalzacija);
        try {
            Thread.sleep(3000);
        }catch (InterruptedException e) {

        }
    }

    @FindBy(css = "div[class='specialist-display']")
    private WebElement cardsLijecnici;

    @FindBy(css = "div[class='specialist-display'] > div[id*='Specialist']")
    private WebElement cardLijecnik;

    @FindBy(css = "div[class='specialist-display'] > div[id*='Specialist'] > div > div:nth-child(2) > div > h5")
    private WebElement txtTitulaImeDoktora;

    @FindBy(xpath = "//div[@class='specialization']//span[@class='specialization-text']")
    private WebElement txtSpecijalizacijaDoktora;

    @FindBy(xpath = "//div[@class='location']//span")
    private WebElement txtAdresaDoktora;

    @Step("Dohvati naziv ljecnika")
    public String vratiNazivLjecnika() {
        healthElements.waitForElementToBeVisible(txtTitulaImeDoktora);
        return txtTitulaImeDoktora.getText();
    }

    @Step("Dohvati specijalizaciju doktora")
    public String vratiSpecijalizacijuDoktora(){
        return txtSpecijalizacijaDoktora.getText();
    }

    @Step("Dohvati adresu doktora")
    public String vratiAdresuDoktora(){
        return txtAdresaDoktora.getText();
    }

    public int vratBrojKarticaDoktora() {
        List<WebElement> lBrojKarticaDoktora = cardsLijecnici.findElements(By.cssSelector("div[id*='Specialist']"));
        return lBrojKarticaDoktora.size();
    }
}
