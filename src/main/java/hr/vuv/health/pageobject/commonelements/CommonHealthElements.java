package hr.vuv.health.pageobject.commonelements;

import hr.vuv.health.database.DatabaseCalls;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import selenium.Pages;

import java.time.Duration;
import java.util.function.Function;

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
        try{
            fluentWaitForElement(element);
            waitForElementToBeVisible(element);
            scrollToElement(element);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            try {
                element.click();
            }catch (ElementClickInterceptedException e) {
                element.click();
            }
        }catch (StaleElementReferenceException e) {
            fluentWaitForElement(element);
            waitForElementToBeVisible(element);
            scrollToElement(element);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            try {
                element.click();
            }catch (ElementClickInterceptedException e2) {
                element.click();
            }
        }

    }

    /*
    * Unesi text u polje
    *
    * @param WebElement element, String sText
    * */
    public void insertTextScrollTo(WebElement element, String sText){
        fluentWaitForElement(element);
        scrollToElement(element);
        waitForElementToBeClickable(element);
        element.clear();
        element.sendKeys(sText);
    }
    //bez scrolla
    public void insertText(WebElement element, String sText){
        fluentWaitForElement(element);
        //scrollToElement(element);
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

    public void scrollToElement(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTo(0," + element.getLocation().y + ")");
    }

    public void scroll() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(250,350)");
    }

    /*
    * Fluent wait
    * */
    public void fluentWaitForElement(WebElement element) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
        WebElement finalElement = element;
        element = wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return finalElement;
            }
        });
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

    /*
    * Odaberi iz dropdowna
    *
    * @param String sOdabir
    * */



    public void selectFromUlLiDropdown(String sOdabir) {
        WebElement element = driver.findElement(By.xpath("//div[@class='e-content e-dropdownbase']//following-sibling::li[normalize-space()='"+sOdabir+"']"));
        element.click();
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
     * Dodaj doktora bez adrese i specijalizacije
     * */
    public String dodajDoktoraBezAdreseISpecijalizacije() throws ClassNotFoundException{
        return databaseCalls.dbConnectionDodajDoktoraBezAdreseISpecijalizacije();
    }

    /*
    * Obrisi doktora
    * */
    public void obrisiDoktora(String sID) throws ClassNotFoundException {
        databaseCalls.dbConnectionObrisiDoktoraPoId(sID);
    }

    public void obrisiDoktoraPoEmailu(String sEmail) throws ClassNotFoundException {
        databaseCalls.dbConnectionObrisiDoktoraPoEmailu(sEmail);
    }

    /*
     * Vrati puni naziv doktora
     * */
    public String vratiPuniNazivDoktora(String sDoktorID) throws ClassNotFoundException {
        return databaseCalls.dbConnectionVratiPuniNazivDoktora(sDoktorID);
    }

    /*
     * Vrati specijalizaciju doktora
     * */
    public String vratiSpecijalzacijuDoktora(String sDoktorID) throws ClassNotFoundException {
        return databaseCalls.dbConnectionVratiSpecijalizacijuDoktora(sDoktorID);
    }

    /*
    * Vrati punu adresu doktora
    * */
    public String vratiPunuAdresuDoktora(String sDoktorID) throws ClassNotFoundException {
        return databaseCalls.dbConnectionVratiPunuAdresuDoktora(sDoktorID);
    }

    /*
    * Dohvati broj usluga
    * */
    public int dohvatiBrojUsluga(String sDoktorID) throws ClassNotFoundException {
        return databaseCalls.dbConnectionDohvatiBrojUslugaDoktora(sDoktorID);
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

    /*
    * Vrati radno vrijeme ordinacije za pojedini dan
    * */
    public String vratiRadnoVrijemeOrdinacijeZaPojediniDan(int nDanID, String sDoktorID) throws ClassNotFoundException{
        return databaseCalls.dbConnectionVratiRadnoVrijemeOrdinacijeZaOdredeniDan(nDanID, sDoktorID);
    }

    /*
     * Vrati opis predleda za termin
     * */
    public String vratiOpisPregledaZaTermin(String sPacijentID, String sDoktorID) throws ClassNotFoundException{
        return databaseCalls.dbConnectionVratiOpisPregledaZaTermin(sPacijentID, sDoktorID);
    }

    //Opis pregleda na u tablici termina na tabu MojiTermini
    public String vratiOpisPregledaZaTerminTabMojiTermini(String sPacijentID, String sDoktorID) throws ClassNotFoundException{
        return databaseCalls.dbConnectionVratiOpisPregledaZaTerminTabMojtermini(sPacijentID, sDoktorID);
    }

    /*
     * Vrati vrijeme termina
     * */
    public String vratiVrijemeTermina(String sPacijentID, String sDoktorID) throws ClassNotFoundException{
        return databaseCalls.dbConnectionVratiVrijemeTermina(sPacijentID, sDoktorID);
    }

    /*
    * Obrisi termin
    * */
    public void obrisiTermin(String sPacijentID, String sDoktorID) throws ClassNotFoundException{
        databaseCalls.dbConnectionObrisiTermin(sPacijentID, sDoktorID);
    }
}
