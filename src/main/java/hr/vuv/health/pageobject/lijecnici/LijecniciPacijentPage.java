package hr.vuv.health.pageobject.lijecnici;

import hr.vuv.health.content.MojProfilContent;
import hr.vuv.health.content.PrijavaContent;
import hr.vuv.health.content.RegistracijaContent;
import hr.vuv.health.pageobject.commonelements.CommonHealthElements;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import selenium.Pages;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LijecniciPacijentPage extends Pages {

    public LijecniciPacijentPage(WebDriver driver) {
        super(driver);
    }

    private CommonHealthElements healthElements = new CommonHealthElements(driver);


    /*
    * Filter doktora po specijalizaciji
    * */

    @FindBy(xpath = "//label[normalize-space()='Specijalizacija']//following::span")
    private WebElement ddOdaberiSpecijalizaciju;

    @Step("Otvori filter i odaberi specijalizaciju")
    public void otvoriFilterOdaberiSpacijalzaciju(String sSpecijalzacija) {
        healthElements.waitForElementToBeClickable(ddOdaberiSpecijalizaciju);
        try {
            Thread.sleep(3000);
        }catch (InterruptedException e) {

        }
        healthElements.selectFromUlLiDropdown(sSpecijalzacija);
        try {
            Thread.sleep(3000);
        }catch (InterruptedException e) {

        }
    }

    /*
     * Filter doktora po imenu
     * */

    @FindBy(css = "input[placeholder='Pretražite liječnike...']")
    private WebElement txtPetraziDoktore;

    @FindBy(xpath = "//button[normalize-space()='Traži']")
    private WebElement btnTrazi;

    @Step("Otvori filter i pretraži doktora po imenu")
    public void otvoriFilterPretraziDoktore() {
        healthElements.waitForElementToBeClickable(txtPetraziDoktore);
        healthElements.insertText(txtPetraziDoktore, PrijavaContent.IME_DOKTOR);
        healthElements.waitForElementToBeClickable(btnTrazi);
    }

    @FindBy(xpath = "//div[@class='doctor-section']//following-sibling::div")
    private WebElement cardDoktor;

    @Step("Otvori filter i pretraži doktora po imenu")
    public void otvoriFilterPretraziIOdaberiDoktora() {
        healthElements.waitForElementToBeVisible(cardDoktor);
        healthElements.waitForElementToBeClickable(txtPetraziDoktore);
        healthElements.insertText(txtPetraziDoktore, PrijavaContent.IME_DOKTOR);
        healthElements.waitForElementToBeClickable(btnTrazi);
        try{
            Thread.sleep(2000);
        }catch (InterruptedException ie){

        }
        healthElements.waitForElementToBeClickable(cardDoktor);
    }

    @FindBy(xpath = "div[class='specialist-display']")
    private WebElement cardsLijecnici;

    @FindBy(xpath = "//div[@class='card-body']")
    private WebElement cardLijecnik;

    @FindBy(xpath = "//div[contains(@id, 'Specialist')]//following::h5[@class='card-title']")
    private WebElement txtTitulaImeDoktora;

    @FindBy(xpath = "//div[contains(@id, 'Specialist')]//following::div[@class='specialization']//span[contains(@class, 'specialization')]")
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
        List<WebElement> lBrojKarticaDoktora = driver.findElements(By.xpath("//div[@class='card-body']"));
        return lBrojKarticaDoktora.size();
    }

    /*
    *  Opci podaci doktora
    * */

    public void klikniNaKarticuDoktora() {
        healthElements.waitForElementToBeClickable(cardLijecnik);
    }

    @FindBy(xpath = "//div[@id='collapseOne']//h5")
    private WebElement txtSpecijalizacija;

    @FindBy(xpath = "//div[@id='collapseOne']//h3")
    private WebElement txtNazivDoktora;

    @FindBy(xpath = "//div[@id='collapseOne']//h5[2]")
    private WebElement txtAdresa;

    @FindBy(xpath = "//div[@id='collapseOne']//h5[3]")
    private WebElement txtEmail;

    @Step("Dohvati specijalizaciju doktora")
    public String vratiSpecijalizacijuDoktora2() {
        healthElements.waitForElementToBeVisible(txtSpecijalizacija);
        return txtSpecijalizacija.getText();
    }

    @Step("Dohvati naziv doktora")
    public String vratiNazivDoktora() {
        return txtNazivDoktora.getText();
    }

    @Step("Dohvati adresu doktora")
    public String vratiAdresuDoktora2() {
        return txtAdresa.getText();
    }

    @Step("Dohvati email doktora")
    public String vratiEmailDoktora() {
        return txtEmail.getText();
    }

    @FindBy(xpath = "//div[@id='accordionExample']//following-sibling::div[@class='accordion-item'][1]")
    private WebElement accDjelatnosti;

    @Step("Klikni na djelatnosti")
    public void klikniNaDjelatnosti() {
        healthElements.waitForElementToBeClickable(accDjelatnosti);
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
    * Provjera radnog vremena doktora
    * */
    @FindBy(xpath = "//div[@id='accordionExample']//following-sibling::div[@class='accordion-item'][2]")
    private WebElement accRadnoVrijeme;

    @Step("Klikni na radno vrijeme")
    public void klikniNaRadnoVrijeme() {
        healthElements.waitForElementToBeClickable(accRadnoVrijeme);
    }

    @FindBy(xpath = "//table[@class='e-table']//following::table//tbody//tr[2]//td[4]")
    private WebElement tdPonedjeljakRadiOd;

    @FindBy(xpath = "//table[@class='e-table']//following::table//tbody//tr[2]//td[5]")
    private WebElement tdPonedjeljakRadiDo;

    public String vratiRadnoVrijemeZaPonedjeljakNakonUnosa() {
        healthElements.waitForElementToBeVisible(tdCetvrtakRadiOd);
        return tdPonedjeljakRadiOd.getText()+"-"+tdPonedjeljakRadiDo.getText();
    }

    @FindBy(xpath = "//table[@class='e-table']//following::table//tbody//tr[3]//td[4]")
    private WebElement tdUtorakRadiOd;

    @FindBy(xpath = "//table[@class='e-table']//following::table//tbody//tr[3]//td[5]")
    private WebElement tdUtorakRadiDo;

    public String vratiRadnoVrijemeZaUtorakNakonUnosa() {
        return tdUtorakRadiOd.getText()+"-"+tdUtorakRadiDo.getText();
    }

    @FindBy(xpath = "//table[@class='e-table']//following::table//tbody//tr[4]//td[4]")
    private WebElement tdSrijedaRadiOd;

    @FindBy(xpath = "//table[@class='e-table']//following::table//tbody//tr[4]//td[5]")
    private WebElement tdSrijedaRadiDo;

    public String vratiRadnoVrijemeZaSrijedaNakonUnosa() {
        return tdSrijedaRadiOd.getText()+"-"+tdSrijedaRadiDo.getText();
    }

    @FindBy(xpath = "//table[@class='e-table']//following::table//tbody//tr[5]//td[4]")
    private WebElement tdCetvrtakRadiOd;

    @FindBy(xpath = "//table[@class='e-table']//following::table//tbody//tr[5]//td[5]")
    private WebElement tdCetvrtakRadiDo;

    public String vratiRadnoVrijemeZaCetvrtakNakonUnosa() {
        return tdCetvrtakRadiOd.getText()+"-"+tdCetvrtakRadiDo.getText();
    }

    @FindBy(xpath = "//table[@class='e-table']//following::table//tbody//tr[6]//td[4]")
    private WebElement tdPetakRadiOd;

    @FindBy(xpath = "//table[@class='e-table']//following::table//tbody//tr[6]//td[5]")
    private WebElement tdPetakRadiDo;

    public String vratiRadnoVrijemeZaPetakNakonUnosa() {
        return tdPetakRadiOd.getText()+"-"+tdPetakRadiDo.getText();
    }

    //Broj redova u tablici 'Radno vrijeme'

    @FindBy(xpath = "//div[@id = 'collapseTree']//following::table[2]//tbody")
    private WebElement tblRadnoVrijeme;

    public int vratiBrojRedovaTabliceRadnoVrijeme() {
        List<WebElement> lBrojRedovaTabliceRadnoVrijeme = tblRadnoVrijeme.findElements(By.tagName("tr"));

        return  lBrojRedovaTabliceRadnoVrijeme.size();
    }

    /*
    * Rezerviraj termin
    * */

    @FindBy(xpath = "//div[@id='accordionExample']//following-sibling::div[@class='accordion-item'][3]")
    private WebElement accTermini;
    @FindBy(xpath = "//a[normalize-space()='Rezerviraj termin']")
    private WebElement btnRezervirajTermin;

    @FindBy(xpath = "//span[normalize-space()='11:00']//following::table//tbody//tr[23]")
    private WebElement calTermin;

    @FindBy(xpath = "//button[normalize-space()='More Details']")
    private WebElement btnViseDetalja;

    @FindBy(xpath = "//td[normalize-space()='Naslov']//following::td//input")
    private WebElement txtOpisTermina;

    @FindBy(xpath = "//td[normalize-space()='Razlog']//following::td//textarea")
    private WebElement txtRazlogTermina;

    @FindBy(xpath = "//td[normalize-space()='Razlog']//following::button[normalize-space()='Save']")
    private WebElement btnSpremiTermin;

    /*@FindBy(xpath = "//div[@id='accordionExample']//following-sibling::div[@class='accordion-item'][3]")
    private WebElement accTermini;*/

    @Step("Odaberi doktora i stisni na gumb 'Rezerviraj termin'")
    public void odaberiDoktoraZaRezervacijuTermina() {
        healthElements.waitForElementToBeClickable(cardLijecnik);
        healthElements.waitForElementToBeClickable(accTermini);
        healthElements.waitForElementToBeClickable(btnRezervirajTermin);
    }

    @Step("Odaberi termin pregleda")
    public void odaberiTerminPregleda(String sOpisTermina, String sRazlogTermina) {
        healthElements.waitForElementToBeVisible(calTermin);
        Actions actions = new Actions(driver);
        actions.doubleClick(calTermin).perform();
        healthElements.waitForElementToBeVisible(txtOpisTermina);
        healthElements.insertText(txtOpisTermina, sOpisTermina);
        healthElements.insertText(txtRazlogTermina, sRazlogTermina);
        healthElements.waitForElementToBeClickable(btnSpremiTermin);
        healthElements.waitForElementToBeVisible(txtOpisPregledaTablica);
    }

    @FindBy(xpath = "//div[@class='e-appointment-details']//div[@class='e-subject']")
    private WebElement txtOpisPregledaTablica;

    @FindBy(xpath = "//div[@class='e-appointment-details']//div[@class='e-time']")
    private WebElement txtVrijemePregledaTablica;

    public String vratiVrijednostOpisPregledaTablica() {
        healthElements.waitForElementToBeVisible(txtOpisPregledaTablica);
        return txtOpisPregledaTablica.getText();
    }

    public String vratiVrijednostVrijemePregledaTablica() {
        return txtVrijemePregledaTablica.getText();
    }

    /*
    * Ocijeni liječnika
    * */

    @FindBy(xpath = "//div[@id='accordionExample']//following-sibling::div[@class='accordion-item'][4]")
    private WebElement accOsvrti;

    @FindBy(xpath = "//div[@class='e-handle e-handle-first']")
    private WebElement slider;

    @FindBy(xpath = "//button[normalize-space()='Dodaj osvrt']//preceding::textarea")
    private WebElement txtOsvrt;

    @FindBy(xpath = "//button[normalize-space()='Dodaj osvrt']")
    private WebElement btnDodajOsvrt;

    public void setAttribute(WebElement element, String attName, String attValue) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);",
                element, attName, attValue);
    }

    @Step("Ocijeni liječnika")
    public void ocijeniLijecnika(String sOsvrt) {
        healthElements.waitForElementToBeClickable(accOsvrti);
        healthElements.waitForElementToBeVisible(slider);
        for(int i = 0; i < 3; i++){
            slider.sendKeys(Keys.ARROW_RIGHT);
        }
        healthElements.insertText(txtOsvrt, sOsvrt);
        healthElements.waitForElementToBeClickable(btnDodajOsvrt);

    }

    @FindBy(id = "NewIconCard")
    private WebElement areaOsvrt;

    @FindBy(xpath = "//div[@id='NewIconCard']//following-sibling::div[@class='e-card-content']")
    private WebElement sadrzajOsvrta;

    @FindBy(xpath = "//div[@id='NewIconCard']//following-sibling::div[@class='e-card-header']")
    private WebElement headerOsvrta;

    @Step("Provjera ocijene liječnika")
    public int brojZvjezdicaCheck() {
        healthElements.waitForElementToBeVisible(headerOsvrta);
        List<WebElement> brojZvjezdica = headerOsvrta.findElements(By.tagName("span"));
        return brojZvjezdica.size();
    }

    @Step("Provjera prikaza sadržaja dodanog osvrta")
    public String sadrzajOsvrta() {
        return sadrzajOsvrta.getText();
    }
}
