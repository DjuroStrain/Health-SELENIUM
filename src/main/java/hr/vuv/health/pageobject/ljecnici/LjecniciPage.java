package hr.vuv.health.pageobject.ljecnici;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.Pages;

public class LjecniciPage extends Pages {

    public LjecniciPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[normalize-space()='Opći podaci']")
    private WebElement tabLjecniciOpciPodaciTab;

    @FindBy(xpath = "//div[normalize-space()='Djelatnosti']")
    private WebElement tabLjecniciDjelatnostiTab;

    @FindBy(xpath = "//div[normalize-space()='Radno vrijeme']")
    private WebElement tabLjecniciRadnoVrijemeTab;

    @FindBy(xpath = "//div[normalize-space()='Termini']")
    private WebElement tabLjecniciTerminiTab;
}
