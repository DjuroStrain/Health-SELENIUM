package hr.vuv.health.pageobject.commonelements;

import hr.vuv.health.database.DatabaseCalls;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.Pages;

import java.time.Duration;

public class CommonHealthElements extends Pages {

    //public WebDriver driver;

    public CommonHealthElements(WebDriver driver){
        super(driver);
    }

    public DatabaseCalls databaseCalls = new DatabaseCalls();

    /*
    * Pricekaj dok se na element moze pritisnuti
    *
    * @param WebElement element
    * */
    public void waitForElementToBeClickable(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    /*
    * Unesi text u polje
    *
    * @param WebElement element, String sText
    * */
    public void insertText(WebElement element, String sText){
        waitForElementToBeClickable(element);
        element.sendKeys(sText);
    }

    /*
    *  Cekaj dok WebElement nije vidljiv
    *
    * @param
    * WebElement element
    * */
    public void waitForElementToBeVisible(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    public void scrollToElement() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(250,350)");
    }

    /*
    *  Odabir vrijednosti iz padajućeg izbornika
    *
    * @param WebElement element, String sOdabir
    * */
    public void selectFromDropdown(WebElement element, String sOdabir)
    {
        Select select = new Select(element);
        select.selectByValue(sOdabir);
    }
    //Povezivanje na bazu podataka

    /*
    * Vrati broj registriraih korisnika u bazi
    * */
    @Step("Dohvati broj registriranih korisnika u bazi podataka.")
    public int vratiBrojRegistriranihKorisnika() throws ClassNotFoundException{
        return databaseCalls.dohvatiBrojKorisnika();
    }

    /*
    * Vrati zadnje registriranog korisnika
    * */
    public String vratiPosljedenjeDodanogKorisnika() throws ClassNotFoundException{
        return  databaseCalls.dohvatiPosljednjeRegistriranogKorisnika();
    }
}
