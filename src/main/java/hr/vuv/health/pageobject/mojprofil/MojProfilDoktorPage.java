package hr.vuv.health.pageobject.mojprofil;

import hr.vuv.health.content.MojProfilContent;
import hr.vuv.health.pageobject.commonelements.CommonHealthElements;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.Pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class MojProfilDoktorPage extends Pages {

    public MojProfilDoktorPage(WebDriver driver) {
        super(driver);
    }

    private CommonHealthElements healthElements = new CommonHealthElements(driver);

    //Moj profil - Izbornik
    @FindBy(xpath = "//button[normalize-space()='Opći podaci']")
    private WebElement accOpciPodaci;

    @FindBy(xpath = "//button[normalize-space()='Djelatnosti']")
    private WebElement accDjelatnosti;

    @FindBy(xpath = "//button[normalize-space()='Radno vrijeme']")
    private WebElement accRadnoVrijeme;

    @FindBy(xpath = "//label[normalize-space()='Ime']//following::input")
    private WebElement txtIme;

    @FindBy(xpath = "//label[normalize-space()='Prezime']//following::input")
    private WebElement txtPrezime;

    @FindBy(xpath = "//label[normalize-space()='Titula']//following::input")
    private WebElement txtTitula;

    @FindBy(xpath = "//label[normalize-space()='Ulica']//following::input")
    private WebElement txtUlica;

    @FindBy(xpath = "//label[normalize-space()='Kućni broj']//following::input")
    private WebElement txtKucniBroj;

    @FindBy(xpath = "//label[normalize-space()='Poštanski broj']//following::input")
    private WebElement txtPostanskiBroj;

    @FindBy(xpath = "//label[normalize-space()='Grad/Mjesto']//following::input")
    private WebElement txtGradMjesto;

    @FindBy(xpath = "//label[normalize-space()='Država']//following::input")
    private WebElement txtDrzava;

    @FindBy(xpath = "//label[normalize-space()='Email']//following::input")
    private WebElement txtEmail;

    @FindBy(xpath = "//button[normalize-space()='Spremi profil']")
    private WebElement btnSpremiProfil;

    /*
    *  Unesi podatke 'Titula', 'Specijalizacija' i 'Adresa' za doktora
    * */

    @FindBy(xpath = "//label[normalize-space()='Specijalizacija']//following-sibling::span")
    private WebElement ddSpecijalizacija;

    @FindBy(xpath = "//div[@class='e-content e-dropdownbase']//following-sibling::li[normalize-space()='Fizioterapija']")
    private WebElement selectFizioterapija;

    List<WebElement> lTablicaUslugaPrije = new ArrayList<>();
    List<WebElement> lTablicaRadnoVrijemePrije = new ArrayList<>();
    List<WebElement> lTablicaUslugaPoslije = new ArrayList<>();
    List<WebElement> lTablicaRadnoVrijemePoslije = new ArrayList<>();



    public void prikazTabliceUslugaPrije() {
        lTablicaUslugaPrije = driver.findElements(By.xpath("//table[@class='e-table']//following::table//tbody//tr"));
    }

    public void prikazTabliceRadnoVrijemePrije() {
        healthElements.waitForElementToBeClickable(accRadnoVrijeme);
        lTablicaUslugaPrije = driver.findElements(By.xpath("//div[@id = 'collapseThree']//following::table[2]//tbody//tr"));
        healthElements.waitForElementToBeClickable(accOpciPodaci);
    }

    public void prikazTabliceUslugaPoslije() {
        lTablicaUslugaPoslije = driver.findElements(By.xpath("//table[@class='e-table']//following::table//tbody//tr"));
    }

    public void prikazTabliceRadnoVrijemePoslije() {
        healthElements.waitForElementToBeClickable(accRadnoVrijeme);
        lTablicaUslugaPrije = driver.findElements(By.xpath("//div[@id = 'collapseThree']//following::table[2]//tbody//tr"));
    }
    /*healthElements.waitForElementToBeClickable(accDjelatnosti);
    prikazTabliceUslugaPrije();
    prikazTabliceRadnoVrijemePrije();
    try {
        Thread.sleep(2000);
    }catch (InterruptedException e){

    }*/
    @Step("Unesi dodatne obavezne podatke o doktoru (titula, specijalizacija i adresa)")
    public void unesiDodatneObaveznePodatkeODoktoru(String sTitula, String sUlica, String sKucniBroj, String sPostanskiBroj,
                                                    String sGradMjesto, String sDrzava) {
        healthElements.scrollToInsertText(txtTitula, sTitula);
        healthElements.waitForElementToBeClickable(ddSpecijalizacija);
        healthElements.waitForElementToBeClickable(selectFizioterapija);
        healthElements.scrollToInsertText(txtUlica, sUlica);
        healthElements.scrollToInsertText(txtKucniBroj, sKucniBroj);
        healthElements.scrollToInsertText(txtPostanskiBroj, sPostanskiBroj);
        healthElements.scrollToInsertText(txtGradMjesto, sGradMjesto);
        healthElements.scrollToInsertText(txtDrzava, sDrzava);
    }

    @Step("Klikni na gumb 'Spremi profil'")
    public void klikniNaGumbSpremiProfil() {
        healthElements.waitForElementToBeClickable(btnSpremiProfil);
    }

    /*
    healthElements.waitForElementToBeClickable(accDjelatnosti);
    prikazTabliceUslugaPoslije();
    prikazTabliceRadnoVrijemePoslije();*/

    public Boolean vratiPrikazTabliceUslugaPrije() {
        return lTablicaUslugaPrije.size() > 0;
    }

    public Boolean vratiPrikazTabliceRadnoVrijemePrije() {
        return lTablicaRadnoVrijemePrije.size() > 0;
    }

    public Boolean vratiPrikazTabliceUslugaPoslije() {
        return lTablicaUslugaPoslije.size() > 0;
    }

    public Boolean vratiPrikazTabliceRadnoVrijemePoslije() {
        return lTablicaRadnoVrijemePoslije.size() > 0;
    }

    /*
    *  Polje 'Ime'
    * */
    @Step("Vrati vrijednost polja 'Ime'")
    public String vratiVrijednostPoljaIme() {
        healthElements.fluentWaitForElement(txtIme);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until((ExpectedCondition<Boolean>) driver -> (txtIme.getAttribute("value").length() > 0));
        }catch (StaleElementReferenceException se){
            wait.until((ExpectedCondition<Boolean>) driver -> (txtIme.getAttribute("value").length() > 0));
        }
        return txtIme.getAttribute("value");
    }

    /*
     *  Polje 'Prezime'
     * */
    @Step("Vrati vrijednost polja 'Prezime'")
    public String vratiVrijednostPoljaPrezime() {
        return txtPrezime.getAttribute("value");
    }

    /*
     *  Polje 'Titula'
     * */
    @Step("Vrati vrijednost polja 'Titula'")
    public String vratiVrijednostPoljaTitula() {
        return txtTitula.getAttribute("value");
    }

    /*
     *  Polje 'Ulica'
     * */
    @Step("Vrati vrijednost polja 'Ulica'")
    public String vratiVrijednostPoljaUlica() {
        return txtUlica.getAttribute("value");
    }

    /*
     *  Polje 'Kućni broj'
     * */
    @Step("Vrati vrijednost polja 'Kućni broj'")
    public String vratiVrijednostPoljaKucniBroj() {
        return txtKucniBroj.getAttribute("value");
    }

    /*
     *  Polje 'Poštanski broj'
     * */
    @Step("Vrati vrijednost polja 'Poštanski broj'")
    public String vratiVrijednostPoljaPostanskiBroj() {
        return txtPostanskiBroj.getAttribute("value");
    }

    /*
     *  Polje 'Država'
     * */
    @Step("Vrati vrijednost polja 'Država'")
    public String vratiVrijednostPoljaDrzava() {
        return txtDrzava.getAttribute("value");
    }

    /*
     *  Polje 'Grad/Mjesto'
     * */
    @Step("Vrati vrijednost polja 'Grad/Mjesto'")
    public String vratiVrijednostPoljaGradMjesto() {
        return txtGradMjesto.getAttribute("value");
    }

    /*
     *  Polje 'Email'
     * */
    @Step("Vrati vrijednost polja 'Email'")
    public String vratiVrijednostPoljaEmail() {
        return txtEmail.getAttribute("value");
    }


    /*
     * Validacije
     * */

    @Step("Obrisi polja 'Ime', 'Prezime' i 'Email'")
    public void obrisiIspunjenaPoljaNaTabOpciPodaci() {
        healthElements.waitForElementToBeClickable(txtIme);
        txtIme.clear();
        txtPrezime.click();
        txtPrezime.clear();
        healthElements.waitForElementToBeClickable(txtEmail);
        txtEmail.clear();
    }

    @Step("Klikni na gumb 'Spremi profil' bez unosa obavezih podataka")
    public void klikniNaGumbKreirajBezUnosaObaveznihPodataka() throws InterruptedException {
        healthElements.waitForElementToBeClickable(btnSpremiProfil);
    }

    @FindBy(xpath = "//label[normalize-space()='Ime']//following::div[@class='validation-message']")
    private WebElement txtValidacijaZaObaveznoPoljeIme;

    @Step("Dohvati validaciju za obavezno polje 'Ime'")
    public String vratiValidacijuZaObaveznoPoljeIme() {
        healthElements.waitForElementToBeVisible(txtValidacijaZaObaveznoPoljeIme);
        return txtValidacijaZaObaveznoPoljeIme.getText();
    }

    @FindBy(xpath = "//label[normalize-space()='Prezime']//following::div[@class='validation-message']")
    private WebElement txtValidacijaZaObaveznoPoljePrezime;

    @Step("Dohvati validaciju za obavezno polje 'Prezime'")
    public String vratiValidacijuZaObaveznoPoljePrezime() {
        healthElements.waitForElementToBeVisible(txtValidacijaZaObaveznoPoljePrezime);
        return txtValidacijaZaObaveznoPoljePrezime.getText();
    }

    @FindBy(xpath = "//label[normalize-space()='Titula']//following::div[@class='validation-message']")
    private WebElement txtValidacijaZaObaveznoPoljeTitula;

    @Step("Dohvati validaciju za obavezno polje 'Titula'")
    public String vratiValidacijuZaObaveznoPoljeTitula() {
        healthElements.waitForElementToBeVisible(txtValidacijaZaObaveznoPoljeTitula);
        return txtValidacijaZaObaveznoPoljeTitula.getText();
    }

    @FindBy(xpath = "//label[normalize-space()='Specijalizacija']//following::div[@class='validation-message']")
    private WebElement txtValidacijaZaObaveznoPoljeSpecijalizacija;

    @Step("Dohvati validaciju za obavezno polje 'Specijalizacija'")
    public String vratiValidacijuZaObaveznoPoljeSpecijalizacija() {
        healthElements.waitForElementToBeVisible(txtValidacijaZaObaveznoPoljeSpecijalizacija);
        return txtValidacijaZaObaveznoPoljeSpecijalizacija.getText();
    }

    @FindBy(xpath = "//label[normalize-space()='Ulica']//following::div[@class='validation-message']")
    private WebElement txtValidacijaZaObaveznoPoljeUlica;

    @Step("Dohvati validaciju za obavezno polje 'Ulica'")
    public String vratiValidacijuZaObaveznoPoljeUlica() {
        healthElements.waitForElementToBeVisible(txtValidacijaZaObaveznoPoljeUlica);
        return txtValidacijaZaObaveznoPoljeUlica.getText();
    }

    @FindBy(xpath = "//label[normalize-space()='Kućni broj']//following::div[@class='validation-message']")
    private WebElement txtValidacijaZaObaveznoPoljeKucniBroj;

    @Step("Dohvati validaciju za obavezno polje 'Kućni broj'")
    public String vratiValidacijuZaObaveznoPoljeKucniBroj() {
        healthElements.waitForElementToBeVisible(txtValidacijaZaObaveznoPoljeKucniBroj);
        return txtValidacijaZaObaveznoPoljeKucniBroj.getText();
    }

    @FindBy(xpath = "//label[normalize-space()='Poštanski broj']//following::div[@class='validation-message']")
    private WebElement txtValidacijaZaObaveznoPoljePostanskiBroj;

    @Step("Dohvati validaciju za obavezno polje 'Poštanski broj'")
    public String vratiValidacijuZaObaveznoPoljePostanskiBroj() {
        healthElements.waitForElementToBeVisible(txtValidacijaZaObaveznoPoljePostanskiBroj);
        return txtValidacijaZaObaveznoPoljePostanskiBroj.getText();
    }

    @FindBy(xpath = "//label[normalize-space()='Grad/Mjesto']//following::div[@class='validation-message']")
    private WebElement txtValidacijaZaObaveznoPoljeGradMjesto;

    @Step("Dohvati validaciju za obavezno polje 'Grad/Mjesto'")
    public String vratiValidacijuZaObaveznoPoljeGradMjesto() {
        healthElements.waitForElementToBeVisible(txtValidacijaZaObaveznoPoljeGradMjesto);
        return txtValidacijaZaObaveznoPoljeGradMjesto.getText();
    }

    @FindBy(xpath = "//label[normalize-space()='Država']//following::div[@class='validation-message']")
    private WebElement txtValidacijaZaObaveznoPoljeDrzava;

    @Step("Dohvati validaciju za obavezno polje 'Država'")
    public String vratiValidacijuZaObaveznoPoljeDrzava() {
        healthElements.waitForElementToBeVisible(txtValidacijaZaObaveznoPoljeDrzava);
        return txtValidacijaZaObaveznoPoljeDrzava.getText();
    }
    @FindBy(xpath = "//label[normalize-space()='Email']//following::div[@class='validation-message']")
    private WebElement txtValidacijaZaObaveznoPoljeEmail;

    @Step("Dohvati validaciju za obavezno polje 'Email'")
    public String vratiValidacijuZaObaveznoPoljeEmail() {
        healthElements.scrollToElement(txtValidacijaZaObaveznoPoljeEmail);
        healthElements.waitForElementToBeVisible(txtValidacijaZaObaveznoPoljeEmail);
        return txtValidacijaZaObaveznoPoljeEmail.getText();
    }

    /*
    * Tab 'Djelatnosti'
    * */

    //Dodavanje usluge

    @FindBy(xpath = "//div[@id='collapseTwo']//following-sibling::span[normalize-space()='Add']")
    private WebElement btnAddService;

    @FindBy(xpath = "//div[@id = 'collapseTwo']//following-sibling::span[normalize-space()='Update']")
    private WebElement btnUpdateService;

    @FindBy(xpath = "//div[@id = 'collapseThree']//following-sibling::span[normalize-space()='Update']")
    private WebElement btnUpdateWorkHours;

    @FindBy(id = "Name")
    private WebElement txtNazivUsluge;

    @FindBy(id = "Description")
    private WebElement txtOpisUsluge;

    @FindBy(id = "Price")
    private WebElement txtCijenaUsluge;

    @FindBy(xpath = "//table//tr//td[contains(text(), 'No records')]")
    private WebElement tdNoRecords;

    @Step("Dodaj uslugu")
    public void dodajUsluguDjelatnostiDoktora(String sNazivUsluge, String sOpisUsluge, String sCijenaUsluge) {
        healthElements.waitForElementToBeClickable(accDjelatnosti);
        healthElements.waitForElementToBeClickable(btnAddService);
        healthElements.insertText(txtNazivUsluge, sNazivUsluge);
        healthElements.insertText(txtOpisUsluge, sOpisUsluge);
        healthElements.insertText(txtCijenaUsluge, sCijenaUsluge);
        healthElements.waitForElementToBeClickable(btnUpdateService);

        healthElements.waitForElementToBeInVisible(tdNoRecords);
    }

    public boolean prikazujeSeDodaniRedak() {
        try{
            return !tdNoRecords.isDisplayed();
        }catch (NoSuchElementException e) {
            return true;
        }
    }

    @FindBy(xpath = "//table//following-sibling::tr//td[2]")
    private WebElement tdNazivUsluge;

    @FindBy(xpath = "//table//following-sibling::tr//td[3]")
    private WebElement tdOpisUsluge;

    @FindBy(xpath = "//table//following-sibling::tr//td[4]")
    private WebElement tdCijenaUsluge;

    public List<String> vratiListuVrijednostiUslugeIzTabliceUsluga() {
        List<String> lVrijednostiUslugeIzTablice = new ArrayList<>();

        healthElements.waitForElementToBeVisible(tdNazivUsluge);

        lVrijednostiUslugeIzTablice.add(tdNazivUsluge.getText());
        lVrijednostiUslugeIzTablice.add(tdOpisUsluge.getText());
        lVrijednostiUslugeIzTablice.add(tdCijenaUsluge.getText());

        lVrijednostiUslugeIzTablice.stream().sorted().collect(Collectors.toList());

        return lVrijednostiUslugeIzTablice;
    }

    public List<String> vratiListuVrijednostiUslugeIzSadrzaja() {
        List<String> lVrijednostiUslugeIzSadrzaja = new ArrayList<>();

        lVrijednostiUslugeIzSadrzaja.add(MojProfilContent.NAZIV_USLUGE);
        lVrijednostiUslugeIzSadrzaja.add(MojProfilContent.OPIS_USLUGE);
        lVrijednostiUslugeIzSadrzaja.add(MojProfilContent.CIJENA_USLUGE);

        lVrijednostiUslugeIzSadrzaja.stream().sorted().collect(Collectors.toList());

        return lVrijednostiUslugeIzSadrzaja;
    }

    /*public int vratiBrojRedovaUslugaIzBaze(String sDoktorID) throws ClassNotFoundException {
        return healthElements.vratiBrojUslugaDoktora(sDoktorID);
    }*/

    /*
    * Uređivanje
    * */

    @FindBy(xpath = "//div[@id = 'collapseTwo']//following-sibling::span[normalize-space()='Edit']")
    private WebElement btnEditService;

    @FindBy(xpath = "//div[@id = 'collapseThree']//following-sibling::span[normalize-space()='Edit']")
    private WebElement btnEditWorkhours;

    @Step("Uredi uslugu")
    public void urediUslugu(String sIzmjenjenNazivUsluge, String sIzmijenjenOpisUsluge, String sIzmijenjenaCijena) {
        healthElements.waitForElementToBeClickable(accDjelatnosti);
        healthElements.waitForElementToBeClickable(tdNazivUsluge);
        healthElements.waitForElementToBeClickable(btnEditService);
        healthElements.insertText(txtNazivUsluge, sIzmjenjenNazivUsluge);
        healthElements.insertText(txtOpisUsluge, sIzmijenjenOpisUsluge);
        healthElements.insertText(txtCijenaUsluge, sIzmijenjenaCijena);
        healthElements.waitForElementToBeClickable(btnUpdateService);
    }

    public List<String> vratiListuPromijenjenihVrijednostiUslugeIzSadrzaja() {
        List<String> lVrijednostiUslugeIzSadrzaja = new ArrayList<>();

        lVrijednostiUslugeIzSadrzaja.add(MojProfilContent.IZMIJENJEN_NAZIV_USLUGE);
        lVrijednostiUslugeIzSadrzaja.add(MojProfilContent.IZMIJENJEN_OPIS_USLUGE);
        lVrijednostiUslugeIzSadrzaja.add(MojProfilContent.IZMIJENJENA_CIJENA_USLUGE);

        lVrijednostiUslugeIzSadrzaja.stream().sorted().collect(Collectors.toList());

        return lVrijednostiUslugeIzSadrzaja;
    }

    @Step("Klikni na 'Djelatnosti'")
    public void klikniNaDjelatnosti(){
        healthElements.waitForElementToBeClickable(accDjelatnosti);
    }

    @Step("Klikni na 'Djelatnosti' i 'Dodaj uslugu'")
    public void klikniNaDjelatnostiIDodajUslugu(){
        healthElements.waitForElementToBeClickable(accDjelatnosti);
        healthElements.waitForElementToBeClickable(btnAddService);
    }

    @FindBy(xpath = "//div[@id = 'collapseTwo']//following-sibling::span[normalize-space()='Delete']")
    private WebElement btnDeleteService;


    @Step("Brisanje usluge")
    public void brisanjeUsluge() {
        healthElements.waitForElementToBeClickable(tdNazivUsluge);
        healthElements.waitForElementToBeClickable(btnDeleteService);
        healthElements.waitForElementToBeVisible(tdNoRecords);
    }

    @FindBy(xpath = "//table[@class='e-table']//following::table//tbody")
    private WebElement tblUsluge;

    public int vratiBrojRedovaTabliceUsluge() {
        healthElements.waitForElementToBeVisible(tblUsluge);
        List<WebElement> lBrojRedovaTabliceUsluga = tblUsluge.findElements(By.tagName("tr"));
        return lBrojRedovaTabliceUsluga.size();
    }


    /*
    * Radno vrijeme
    * */

    @FindBy(xpath = "//table[@class='e-table']//following::table//tbody//tr[2]")
    private WebElement tdPonedjeljak;

    @FindBy(xpath = "//table[@class='e-table']//following::table//tbody//tr[2]//td[4]")
    private WebElement tdPonedjeljakRadiOd;

    @FindBy(xpath = "//table[@class='e-table']//following::table//tbody//tr[2]//td[4]//following::span//span")
    private WebElement btnOdaberiVrijemeOdPonedjeljak;

    @FindBy(xpath = "//div[@class='e-control e-timepicker e-lib e-popup-wrapper e-popup e-popup-open']//li[normalize-space()='08:00']")
    private WebElement vrijemeOdPonedjeljak;

    @FindBy(xpath = "//table[@class='e-table']//following::table//tbody//tr[2]//td[5]")
    private WebElement tdPonedjeljakRadiDo;

    @FindBy(xpath = "//table[@class='e-table']//following::table//tbody//tr[2]//td[5]//following::span//span")
    private WebElement btnOdaberiVrijemeDoPonedjeljak;

    @FindBy(xpath = "//div[@class='e-control e-timepicker e-lib e-popup-wrapper e-popup e-popup-open']//li[normalize-space()='14:00']")
    private WebElement vrijemeDoPonedjeljak;

    public void unesiRadnoVrijemeDoktoraPonedjeljak(String sPonedjeljakRadiOd, String sPonedjeljakRadiDo) {
        healthElements.waitForElementToBeClickable(accRadnoVrijeme);
        healthElements.waitForElementToBeClickable(tdPonedjeljak);
        healthElements.waitForElementToBeClickable(btnEditWorkhours);
        healthElements.waitForElementToBeClickable(tdPonedjeljakRadiOd);
        healthElements.waitForElementToBeClickable(btnOdaberiVrijemeOdPonedjeljak);
        healthElements.waitForElementToBeClickable(vrijemeOdPonedjeljak);
        healthElements.waitForElementToBeClickable(tdPonedjeljakRadiDo);
        healthElements.waitForElementToBeClickable(btnOdaberiVrijemeDoPonedjeljak);
        healthElements.waitForElementToBeClickable(vrijemeDoPonedjeljak);
        healthElements.waitForElementToBeClickable(btnUpdateWorkHours);
    }

    public String vratiRadnoVrijemeZaPonedjeljakNakonUnosa() {
        return tdPonedjeljakRadiOd.getText()+"-"+tdPonedjeljakRadiDo.getText();
    }

    @FindBy(xpath = "//table[@class='e-table']//following::table//tbody//tr[3]")
    private WebElement tdUtorak;

    @FindBy(xpath = "//table[@class='e-table']//following::table//tbody//tr[3]//td[4]")
    private WebElement tdUtorakRadiOd;

    @FindBy(xpath = "//table[@class='e-table']//following::table//tbody//tr[3]//td[4]//following::span//span")
    private WebElement btnOdaberiVrijemeOdUtorak;

    @FindBy(xpath = "//div[@class='e-control e-timepicker e-lib e-popup-wrapper e-popup e-popup-open']//li[normalize-space()='08:00']")
    private WebElement vrijemeOdUtorak;

    @FindBy(xpath = "//table[@class='e-table']//following::table//tbody//tr[3]//td[5]")
    private WebElement tdUtorakRadiDo;

    @FindBy(xpath = "//table[@class='e-table']//following::table//tbody//tr[3]//td[5]//following::span//span")
    private WebElement btnOdaberiVrijemeDoUtorak;

    @FindBy(xpath = "//div[@class='e-control e-timepicker e-lib e-popup-wrapper e-popup e-popup-open']//li[normalize-space()='14:00']")
    private WebElement vrijemeDoUtorak;

    public void unesiRadnoVrijemeDoktoraUtorak(String sUtorakRadiOd, String sUtorakRadiDo) {
        healthElements.waitForElementToBeClickable(tdUtorak);
        healthElements.waitForElementToBeClickable(btnEditWorkhours);
        healthElements.waitForElementToBeClickable(tdUtorakRadiOd);
        healthElements.waitForElementToBeClickable(btnOdaberiVrijemeOdUtorak);
        healthElements.waitForElementToBeClickable(vrijemeOdUtorak);
        healthElements.waitForElementToBeClickable(tdUtorakRadiDo);
        healthElements.waitForElementToBeClickable(btnOdaberiVrijemeDoUtorak);
        healthElements.waitForElementToBeClickable(vrijemeDoUtorak);
        healthElements.waitForElementToBeClickable(btnUpdateWorkHours);
    }

    public String vratiRadnoVrijemeZaUtorakNakonUnosa() {
        return tdUtorakRadiOd.getText()+"-"+tdUtorakRadiDo.getText();
    }

    @FindBy(xpath = "//table[@class='e-table']//following::table//tbody//tr[4]")
    private WebElement tdSrijeda;

    @FindBy(xpath = "//table[@class='e-table']//following::table//tbody//tr[4]//td[4]")
    private WebElement tdSrijedaRadiOd;

    @FindBy(xpath = "//table[@class='e-table']//following::table//tbody//tr[4]//td[4]//following::span//span")
    private WebElement btnOdaberiVrijemeOdSrijeda;

    @FindBy(xpath = "//div[@class='e-control e-timepicker e-lib e-popup-wrapper e-popup e-popup-open']//li[normalize-space()='14:00']")
    private WebElement vrijemeOdSrijeda;

    @FindBy(xpath = "//table[@class='e-table']//following::table//tbody//tr[4]//td[5]")
    private WebElement tdSrijedaRadiDo;

    @FindBy(xpath = "//table[@class='e-table']//following::table//tbody//tr[4]//td[5]//following::span//span")
    private WebElement btnOdaberiVrijemeDoSrijeda;

    @FindBy(xpath = "//div[@class='e-control e-timepicker e-lib e-popup-wrapper e-popup e-popup-open']//li[normalize-space()='20:00']")
    private WebElement vrijemeDoSrijeda;

    public void unesiRadnoVrijemeDoktoraSrijeda(String sSrijedaRadiOd, String sSrijedaRadiDo) {
        healthElements.waitForElementToBeClickable(tdSrijeda);
        healthElements.waitForElementToBeClickable(btnEditWorkhours);
        healthElements.waitForElementToBeClickable(tdSrijedaRadiOd);
        healthElements.waitForElementToBeClickable(btnOdaberiVrijemeOdSrijeda);
        healthElements.waitForElementToBeClickable(vrijemeOdSrijeda);
        healthElements.waitForElementToBeClickable(tdSrijedaRadiDo);
        healthElements.waitForElementToBeClickable(btnOdaberiVrijemeDoSrijeda);
        healthElements.waitForElementToBeClickable(vrijemeDoSrijeda);
        healthElements.waitForElementToBeClickable(btnUpdateWorkHours);
    }

    public String vratiRadnoVrijemeZaSrijedaNakonUnosa() {
        return tdSrijedaRadiOd.getText()+"-"+tdSrijedaRadiDo.getText();
    }

    @FindBy(xpath = "//table[@class='e-table']//following::table//tbody//tr[5]")
    private WebElement tdCetvrtak;

    @FindBy(xpath = "//table[@class='e-table']//following::table//tbody//tr[5]//td[4]")
    private WebElement tdCetvrtakRadiOd;

    @FindBy(xpath = "//table[@class='e-table']//following::table//tbody//tr[5]//td[4]//following::span//span")
    private WebElement btnOdaberiVrijemeOdCetvrtak;

    @FindBy(xpath = "//div[@class='e-control e-timepicker e-lib e-popup-wrapper e-popup e-popup-open']//li[normalize-space()='14:00']")
    private WebElement vrijemeOdCetvrtak;

    @FindBy(xpath = "//table[@class='e-table']//following::table//tbody//tr[5]//td[5]")
    private WebElement tdCetvrtakRadiDo;

    @FindBy(xpath = "//table[@class='e-table']//following::table//tbody//tr[5]//td[5]//following::span//span")
    private WebElement btnOdaberiVrijemeDoCetvrtak;

    @FindBy(xpath = "//div[@class='e-control e-timepicker e-lib e-popup-wrapper e-popup e-popup-open']//li[normalize-space()='20:00']")
    private WebElement vrijemeDoCetvrtak;

    public void unesiRadnoVrijemeDoktoraCetvrtak(String sCetvrtakRadiOd, String sCetvrtakRadiDo) {
        healthElements.waitForElementToBeClickable(tdCetvrtak);
        healthElements.waitForElementToBeClickable(btnEditWorkhours);
        healthElements.waitForElementToBeClickable(tdCetvrtakRadiOd);
        healthElements.waitForElementToBeClickable(btnOdaberiVrijemeOdCetvrtak);
        healthElements.waitForElementToBeClickable(vrijemeOdCetvrtak);
        healthElements.waitForElementToBeClickable(tdCetvrtakRadiDo);
        healthElements.waitForElementToBeClickable(btnOdaberiVrijemeDoCetvrtak);
        healthElements.waitForElementToBeClickable(vrijemeDoCetvrtak);
        healthElements.waitForElementToBeClickable(btnUpdateWorkHours);
    }

    public String vratiRadnoVrijemeZaCetvrtakNakonUnosa() {
        return tdCetvrtakRadiOd.getText()+"-"+tdCetvrtakRadiDo.getText();
    }

    @FindBy(xpath = "//table[@class='e-table']//following::table//tbody//tr[6]")
    private WebElement tdPetak;

    @FindBy(xpath = "//table[@class='e-table']//following::table//tbody//tr[6]//td[4]")
    private WebElement tdPetakRadiOd;

    @FindBy(xpath = "//table[@class='e-table']//following::table//tbody//tr[6]//td[4]//following::span//span")
    private WebElement btnOdaberiVrijemeOdPetak;

    @FindBy(xpath = "//div[@class='e-control e-timepicker e-lib e-popup-wrapper e-popup e-popup-open']//li[normalize-space()='14:00']")
    private WebElement vrijemeOdPetak;

    @FindBy(xpath = "//table[@class='e-table']//following::table//tbody//tr[6]//td[5]")
    private WebElement tdPetakRadiDo;

    @FindBy(xpath = "//table[@class='e-table']//following::table//tbody//tr[6]//td[5]//following::span//span")
    private WebElement btnOdaberiVrijemeDoPetak;

    @FindBy(xpath = "//div[@class='e-control e-timepicker e-lib e-popup-wrapper e-popup e-popup-open']//li[normalize-space()='20:00']")
    private WebElement vrijemeDoPetak;

    public void unesiRadnoVrijemeDoktoraPetak(String sPetakRadiOd, String sPetakRadiDo) {
        healthElements.waitForElementToBeClickable(tdPetak);
        healthElements.waitForElementToBeClickable(btnEditWorkhours);
        healthElements.waitForElementToBeClickable(tdPetakRadiOd);
        healthElements.waitForElementToBeClickable(btnOdaberiVrijemeOdPetak);
        healthElements.waitForElementToBeClickable(vrijemeOdPetak);
        healthElements.waitForElementToBeClickable(tdPetakRadiDo);
        healthElements.waitForElementToBeClickable(btnOdaberiVrijemeDoPetak);
        healthElements.waitForElementToBeClickable(vrijemeDoPetak);
        healthElements.waitForElementToBeClickable(btnUpdateWorkHours);
    }

    @Step("Klikni na gumb azuriraj")
    public void klikniNaGumbAzuriraj() {
        healthElements.waitForElementToBeClickable(btnUpdateService);
    }

    @FindBy(id = "Name-info")
    private WebElement validaacijaNazivUsluge;

    @Step("Vrati validaciju za naziv usluge")
    public String vratiValidacijuZaNazivUsluge() {
        return validaacijaNazivUsluge.getText();
    }

    @FindBy(id = "Description-info")
    private WebElement validacijaOpisUsluge;

    @Step("Vrati validaciju za opis usluge")
    public String vratiValidacijuZaOpisUsluge() {
        return validacijaOpisUsluge.getText();
    }

    @FindBy(id = "Price-info")
    private WebElement validacijaCijenaUsluge;

    @Step("Vrati validaciju za cijenu usluge")
    public String vratiValidacijuZaCijenuUsluge() {
        return validacijaCijenaUsluge   .getText();
    }

    public String vratiRadnoVrijemeZaPetakNakonUnosa() {
        return tdPetakRadiOd.getText()+"-"+tdPetakRadiDo.getText();
    }



    //Broj redova u tablici 'Radno vrijeme'

    @FindBy(xpath = "//div[@id = 'collapseThree']//following::table[2]//tbody")
    private WebElement tblRadnoVrijeme;

    public int vratiBrojRedovaTabliceRadnoVrijeme() {
        List<WebElement> lBrojRedovaTabliceRadnoVrijeme = tblRadnoVrijeme.findElements(By.tagName("tr"));

        return  lBrojRedovaTabliceRadnoVrijeme.size();
    }
}
