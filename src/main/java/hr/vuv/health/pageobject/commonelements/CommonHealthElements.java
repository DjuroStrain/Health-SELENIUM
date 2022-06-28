package hr.vuv.health.pageobject.commonelements;

import hr.vuv.health.database.DatabaseCalls;
import io.qameta.allure.Step;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        element.clear();
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

    /*
     *  Cekaj dok WebElement je jos uvijek vidljiv
     *
     * @param
     * WebElement element
     * */
    public void waitForElementToBeInVisible(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(element));
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
        return databaseCalls.dbConnectionDohvatiBrojKorisnika();
    }

    /*
     * Vrati broj registriraih doktora u bazi
     * */
    @Step("Dohvati broj registriranih korisnika u bazi podataka.")
    public int vratiBrojRegistriranihDoktora() throws ClassNotFoundException{
        return databaseCalls.dbConnectionDohvatiBrojDoktora();
    }

    /*
    * Vrati zadnje registriranog korisnika
    * */
    public String vratiPosljedenjeDodanogKorisnika() throws ClassNotFoundException{
        return  databaseCalls.dbConnectionDohvatiPosljednjeRegistriranogKorisnika();
    }

    /*
    * Obrisi korisnika
    * */

    public void obrisiKorisnikaPoKorisnickomImenu(String sUserName) throws ClassNotFoundException {
        databaseCalls.dbConnectionObrisiKorisnikaPoKorisnickomImenu(sUserName);
    }

    /*
    * Dodaj doktora
    * */
    public void dodajDoktora() throws ClassNotFoundException{
        databaseCalls.dbConnectionDodajDoktora();
    }

    /*
    * Obrisi doktora
    * */
    public void obrisiDoktora(String sID) throws ClassNotFoundException {
        databaseCalls.dbConnectionObrisiDoktoraPoId(sID);
    }

    /*
    * Dohvati broj usluga
    * */
    public int dohvatiBrojUsluga() throws ClassNotFoundException {
        return databaseCalls.dbConnectionDohvatiBrojUslugaDoktora();
    }

    /*
    * Obrisi uslugu
    * */
    public void obrisiUslugu() throws ClassNotFoundException{
        databaseCalls.dbConnectionObrisiUsluguPoID();
    }

    public void obrisiUsluguParametarId(int nID) throws ClassNotFoundException{
        databaseCalls.dbConnectionObrisiUsluguPoParametarID(nID);
    }

    /*
    * Dodaj uslugu
    * */
    public int dodajUslugu() throws ClassNotFoundException {
        return databaseCalls.dbConnectionDodajUslugu();
    }
}
