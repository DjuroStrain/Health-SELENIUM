package hr.vuv.health.pageobject.lijecnici;

import hr.vuv.health.content.MojProfilContent;
import hr.vuv.health.content.PrijavaContent;
import hr.vuv.health.content.RegistracijaContent;
import hr.vuv.health.pageobject.commonelements.CommonHealthElements;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.Pages;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    /*
     * Filter doktora po imenu
     * */

    @FindBy(css = "input[placeholder='Pretražite doktore...']")
    private WebElement txtPetraziDoktore;

    @FindBy(xpath = "//button[normalize-space()='Traži']")
    private WebElement btnTrazi;

    @Step("Otvori filter i odaberi specijalizaciju")
    public void otvoriFilterPretraziDoktore() {
        healthElements.waitForElementToBeClickable(accFilter);
        healthElements.waitForElementToBeClickable(txtPetraziDoktore);
        healthElements.insertText(txtPetraziDoktore, PrijavaContent.IME_DOKTOR);
        healthElements.waitForElementToBeClickable(btnTrazi);
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

}
