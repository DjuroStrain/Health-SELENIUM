package hr.vuv.health.pageobject.mojprofil;

import hr.vuv.health.content.MojProfilContent;
import hr.vuv.health.pageobject.commonelements.CommonHealthElements;
import io.qameta.allure.Step;
import org.bouncycastle.jcajce.provider.symmetric.IDEA;
import org.checkerframework.checker.index.qual.NegativeIndexFor;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    @FindBy(xpath = "//div[normalize-space()='Opći podaci']")
    private WebElement tabOpciPodaci;

    @FindBy(xpath = "//div[normalize-space()='Djelatnosti']")
    private WebElement tabDjelatnosti;

    @FindBy(xpath = "//div[normalize-space()='Radno vrijeme']")
    private WebElement tabRadnoVrijeme;

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

    /*
    *  Polje 'Ime'
    * */
    @Step("Vrati vrijednost polja 'Ime'")
    public String vratiVrijednostPoljaIme() throws InterruptedException {
        try {
            healthElements.waitForElementToBeVisible(txtIme);
        }catch (StaleElementReferenceException e) {
            healthElements.waitForElementToBeVisible(txtIme);
            e.printStackTrace();
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until((ExpectedCondition<Boolean>) driver -> (txtIme.getAttribute("value").length() > 0));
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
    * Tab 'Djelatnosti'
    * */

    //Dodavanje usluge

    @FindBy(xpath = "//span[normalize-space()='Add']")
    private WebElement btnAddService;

    @FindBy(xpath = "//span[normalize-space()='Update']")
    private WebElement btnUpdateService;

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
        healthElements.waitForElementToBeClickable(tabDjelatnosti);
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


    /*
    * Uređivanje
    * */

    @FindBy(xpath = "//span[normalize-space()='Edit']")
    private WebElement btnEditService;

    @Step("Uredi uslugu")
    public void urediUslugu(String sIzmjenjenNazivUsluge, String sIzmijenjenOpisUsluge, String sIzmijenjenaCijena) {
        healthElements.waitForElementToBeClickable(tabDjelatnosti);
        healthElements.waitForElementToBeClickable(tdNazivUsluge);
        healthElements.waitForElementToBeClickable(btnEditService);
        healthElements.insertText(txtNazivUsluge, sIzmjenjenNazivUsluge);
        healthElements.insertText(txtOpisUsluge, sIzmijenjenOpisUsluge);
        healthElements.insertText(txtCijenaUsluge, sIzmijenjenaCijena);
        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e) {

        }
        healthElements.waitForElementToBeClickable(btnUpdateService);
        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e) {

        }
    }

    public List<String> vratiListuPromijenjenihVrijednostiUslugeIzSadrzaja() {
        List<String> lVrijednostiUslugeIzSadrzaja = new ArrayList<>();

        lVrijednostiUslugeIzSadrzaja.add(MojProfilContent.IZMIJENJEN_NAZIV_USLUGE);
        lVrijednostiUslugeIzSadrzaja.add(MojProfilContent.IZMIJENJEN_OPIS_USLUGE);
        lVrijednostiUslugeIzSadrzaja.add(MojProfilContent.IZMIJENJENA_CIJENA_USLUGE);

        lVrijednostiUslugeIzSadrzaja.stream().sorted().collect(Collectors.toList());

        return lVrijednostiUslugeIzSadrzaja;
    }

    @FindBy(xpath = "//span[normalize-space()='Delete']")
    private WebElement btnDeleteService;

    @Step("Brisanje usluge")
    public void brisanjeUsluge() {
        healthElements.waitForElementToBeClickable(tabDjelatnosti);
        healthElements.waitForElementToBeClickable(tdNazivUsluge);
        healthElements.waitForElementToBeClickable(btnDeleteService);
        healthElements.waitForElementToBeVisible(tdNoRecords);
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
        healthElements.waitForElementToBeClickable(tabRadnoVrijeme);
        healthElements.waitForElementToBeClickable(tdPonedjeljak);
        healthElements.waitForElementToBeClickable(btnEditService);
        healthElements.waitForElementToBeClickable(tdPonedjeljakRadiOd);
        healthElements.waitForElementToBeClickable(btnOdaberiVrijemeOdPonedjeljak);
        healthElements.waitForElementToBeClickable(vrijemeOdPonedjeljak);
        healthElements.waitForElementToBeClickable(tdPonedjeljakRadiDo);
        healthElements.waitForElementToBeClickable(btnOdaberiVrijemeDoPonedjeljak);
        healthElements.waitForElementToBeClickable(vrijemeDoPonedjeljak);
        healthElements.waitForElementToBeClickable(btnUpdateService);
    }

    public String vratiVrijednostiPonedjeljakRadiOdNakonUnosa() {
        return tdPonedjeljakRadiOd.getText();
    }

    public String vratiVrijednostPonedjeljakRadiDoNakonUnosa() {
        return tdPonedjeljakRadiDo.getText();
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
        healthElements.waitForElementToBeClickable(tabRadnoVrijeme);
        healthElements.waitForElementToBeClickable(tdUtorak);
        healthElements.waitForElementToBeClickable(btnEditService);
        healthElements.waitForElementToBeClickable(tdUtorakRadiOd);
        healthElements.waitForElementToBeClickable(btnOdaberiVrijemeOdUtorak);
        healthElements.waitForElementToBeClickable(vrijemeOdUtorak);
        healthElements.waitForElementToBeClickable(tdUtorakRadiDo);
        healthElements.waitForElementToBeClickable(btnOdaberiVrijemeDoUtorak);
        healthElements.waitForElementToBeClickable(vrijemeDoUtorak);
        healthElements.waitForElementToBeClickable(btnUpdateService);
    }

    public String vratiVrijednostiUtorakRadiOdNakonUnosa() {
        return tdUtorakRadiOd.getText();
    }

    public String vratiVrijednostUtorakRadiDoNakonUnosa() {
        return tdUtorakRadiDo.getText();
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
        healthElements.waitForElementToBeClickable(tabRadnoVrijeme);
        healthElements.waitForElementToBeClickable(tdSrijeda);
        healthElements.waitForElementToBeClickable(btnEditService);
        healthElements.waitForElementToBeClickable(tdSrijedaRadiOd);
        healthElements.waitForElementToBeClickable(btnOdaberiVrijemeOdSrijeda);
        healthElements.waitForElementToBeClickable(vrijemeOdSrijeda);
        healthElements.waitForElementToBeClickable(tdSrijedaRadiDo);
        healthElements.waitForElementToBeClickable(btnOdaberiVrijemeDoSrijeda);
        healthElements.waitForElementToBeClickable(vrijemeDoSrijeda);
        healthElements.waitForElementToBeClickable(btnUpdateService);
    }

    public String vratiVrijednostiSrijedaRadiOdNakonUnosa() {
        return tdSrijedaRadiOd.getText();
    }

    public String vratiVrijednostSrijedaRadiDoNakonUnosa() {
        return tdSrijedaRadiDo.getText();
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
        healthElements.waitForElementToBeClickable(tabRadnoVrijeme);
        healthElements.waitForElementToBeClickable(tdCetvrtak);
        healthElements.waitForElementToBeClickable(btnEditService);
        healthElements.waitForElementToBeClickable(tdCetvrtakRadiOd);
        healthElements.waitForElementToBeClickable(btnOdaberiVrijemeOdCetvrtak);
        healthElements.waitForElementToBeClickable(vrijemeOdCetvrtak);
        healthElements.waitForElementToBeClickable(tdCetvrtakRadiDo);
        healthElements.waitForElementToBeClickable(btnOdaberiVrijemeDoCetvrtak);
        healthElements.waitForElementToBeClickable(vrijemeDoCetvrtak);
        healthElements.waitForElementToBeClickable(btnUpdateService);
    }

    public String vratiVrijednostiCetvrtakRadiOdNakonUnosa() {
        return tdCetvrtakRadiOd.getText();
    }

    public String vratiVrijednostCetvrtakRadiDoNakonUnosa() {
        return tdCetvrtakRadiDo.getText();
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
        healthElements.waitForElementToBeClickable(tabRadnoVrijeme);
        healthElements.waitForElementToBeClickable(tdPetak);
        healthElements.waitForElementToBeClickable(btnEditService);
        healthElements.waitForElementToBeClickable(tdPetakRadiOd);
        healthElements.waitForElementToBeClickable(btnOdaberiVrijemeOdPetak);
        healthElements.waitForElementToBeClickable(vrijemeOdPetak);
        healthElements.waitForElementToBeClickable(tdPetakRadiDo);
        healthElements.waitForElementToBeClickable(btnOdaberiVrijemeDoPetak);
        healthElements.waitForElementToBeClickable(vrijemeDoPetak);
        healthElements.waitForElementToBeClickable(btnUpdateService);
    }

    public String vratiVrijednostiPetakRadiOdNakonUnosa() {
        return tdPetakRadiOd.getText();
    }

    public String vratiVrijednostPetakRadiDoNakonUnosa() {
        return tdPetakRadiDo.getText();
    }
}
