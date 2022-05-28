package hr.vuv.health.pageobject.commonelements;

import hr.vuv.health.base.BaseClass;
import hr.vuv.health.database.DatabaseCalls;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonHealthElements extends BaseClass {

    public DatabaseCalls databaseCalls = new DatabaseCalls();

    /*
    * Pricekaj dok se na element moze pritisnuti
    *
    * @param WebElement element
    * */
    public void waitForElementToBeClickable(WebElement element){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
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
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    public void scrollToElement() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
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
    public int vratiBrojRegistriranihKorisnika() {
        return databaseCalls.dohvatiBrojKorisnika();
    }

    /*
    * Vrati zadnje registriranog korisnika
    * */
    public String vratiPosljedenjeDodanogKorisnika() {
        return  databaseCalls.dohvatiPosljednjeRegistriranogKorisnika();
    }
}
