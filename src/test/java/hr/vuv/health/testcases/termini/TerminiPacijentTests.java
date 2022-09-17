package hr.vuv.health.testcases.termini;

import hr.vuv.health.content.PacijentContent;
import hr.vuv.health.content.PrijavaContent;
import hr.vuv.health.pageobject.commonelements.CommonHealthElements;
import hr.vuv.health.pageobject.izbornik.IzbornikPage;
import hr.vuv.health.pageobject.lijecnici.LijecniciBezRolePage;
import hr.vuv.health.pageobject.lijecnici.LijecniciPacijentPage;
import hr.vuv.health.pageobject.prijava.PrijavaPage;
import hr.vuv.health.pageobject.setup.StartPage;
import hr.vuv.health.pageobject.termini.TerminiPacijentPage;
import hr.vuv.health.testcases.MyTestWatcher;
import hr.vuv.health.testcases.mojprofil.MojProfilDoktorTests;
import io.qameta.allure.Description;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selenium.SeleniumTestWrapper;
import utility.LoggerClass;

public class TerminiPacijentTests extends SeleniumTestWrapper {

    private final static Logger log = LoggerFactory.getLogger(MojProfilDoktorTests.class);

    private StartPage startPage = PageFactory.initElements(getDriver(), StartPage.class);
    private PrijavaPage prijavaPage = PageFactory.initElements(getDriver(), PrijavaPage.class);
    private IzbornikPage izbornikPage = PageFactory.initElements(getDriver(), IzbornikPage.class);
    private TerminiPacijentPage terminiPage = PageFactory.initElements(getDriver(), TerminiPacijentPage.class);
    private LijecniciBezRolePage ljecniciPage = new LijecniciBezRolePage(getDriver());
    private LijecniciPacijentPage lijecniciPacijentPage = PageFactory.initElements(getDriver(), LijecniciPacijentPage.class);

    private CommonHealthElements healthElements = new CommonHealthElements(getDriver());

    static LoggerClass loggerClass = new LoggerClass();

    SoftAssertions softAssertions = new SoftAssertions();

    @RegisterExtension
    MyTestWatcher watcher = new MyTestWatcher(getDriver());

    @BeforeEach
    public void setup(TestInfo testInfo) throws ClassNotFoundException {
        loggerClass.startTestLog(this.getClass().getSimpleName());
        log.info("Naziv testa koji se izvrsava: "+ testInfo.getDisplayName());
        log.info("");
        healthElements.obrisiDoktora(PrijavaContent.ID_DOKTOR);
        healthElements.dodajDoktoraSaRadnimVremenom();
        healthElements.dodajUslugu();
        startPage.startApplication();
        startPage.prijavaPacijenta();
    }

    @Description("Dodavanje termina i provjera ispravnosti prikaza u tablicama")
    @Test
    public void Dodaj_Termin() throws ClassNotFoundException {
        healthElements.obrisiTermin(PacijentContent.PACIJENT_ID, PacijentContent.DOKTOR_ID);
        healthElements.obrisiObavijestDoktor(PacijentContent.PACIJENT_ID, PacijentContent.DOKTOR_ID);
        int nBrojObavijestiPrije = healthElements.vratiBrojObavijesti(PacijentContent.PACIJENT_ID, PacijentContent.DOKTOR_ID);
        izbornikPage.klikniIzbornikLijecnici();
        lijecniciPacijentPage.otvoriFilterPretraziIOdaberiDoktora();
        terminiPage.odaberiTerminPregleda(PacijentContent.PREDMET_TERMINA, PacijentContent.OPIS_TERMINA);
        softAssertions.assertThat(terminiPage.vratiVrijednostOpisPregledaTablica())
                .isEqualTo(healthElements.vratiOpisPregledaZaTerminRolaDoktor(PacijentContent.DOKTOR_ID));
        softAssertions.assertThat(terminiPage.vratiVrijednostVrijemePregledaTablica())
                .isEqualTo(healthElements.vratiVrijemeTerminaRolaDoktor(PacijentContent.DOKTOR_ID));
        izbornikPage.klikniIzbornikMojiTermini();
        softAssertions.assertThat(terminiPage.terminIsDisplayed()).isEqualTo(1);
        int nBrojObavijestiPoslije = healthElements.vratiBrojObavijesti(PacijentContent.PACIJENT_ID, PacijentContent.DOKTOR_ID);
        softAssertions.assertThat(nBrojObavijestiPoslije - nBrojObavijestiPrije).isEqualTo(1);
        healthElements.obrisiTermin(PacijentContent.PACIJENT_ID, PacijentContent.DOKTOR_ID);
        healthElements.obrisiDoktora(PrijavaContent.ID_DOKTOR);
        softAssertions.assertAll();
        log.info("Termin je uspješno dodan i isparavno se prikazuje u obe tablice");
    }

    @Description("Dodavanje termina i provjera ispravnosti prikaza u tablicama")
    @Test
    public void Dodaj_Termin_Van_Radnog_Vremena() throws ClassNotFoundException {
        healthElements.obrisiTermin(PacijentContent.PACIJENT_ID, PacijentContent.DOKTOR_ID);
        izbornikPage.klikniIzbornikLijecnici();
        lijecniciPacijentPage.otvoriFilterPretraziIOdaberiDoktora();
        terminiPage.odaberiTerminPregledaVanRadnogVremena(PacijentContent.PREDMET_TERMINA, PacijentContent.OPIS_TERMINA);
        softAssertions.assertThat(terminiPage.terminIsDisplayed())
                .isEqualTo(0);
        izbornikPage.klikniIzbornikMojiTermini();
        softAssertions.assertThat(terminiPage.terminIsDisplayed()).isEqualTo(0);
        healthElements.obrisiDoktora(PrijavaContent.ID_DOKTOR);
        softAssertions.assertAll();
        log.info("Termin se ne može dodati van radnog vremena");
    }

    @Description("Uređivanje termina i provjera ispravnosti prikaza u tablicama")
    @Test
    public void Uredi_Termin() throws ClassNotFoundException, InterruptedException {
        healthElements.obrisiTermin(PacijentContent.PACIJENT_ID, PacijentContent.DOKTOR_ID);
        healthElements.dodajTermin();
        izbornikPage.klikniIzbornikLijecnici();
        lijecniciPacijentPage.otvoriFilterPretraziIOdaberiDoktora();
        terminiPage.urediTermin(PacijentContent.AZURIRANI_PREDMET_TERMINA, PacijentContent.AZURIRANI_OPIS_TERMINA);
        softAssertions.assertThat(terminiPage.vratiVrijednostOpisPregledaTablica())
                .isEqualTo(healthElements.vratiOpisPregledaZaTerminRolaDoktor(PacijentContent.DOKTOR_ID));
        softAssertions.assertThat(terminiPage.vratiVrijednostVrijemePregledaTablica())
                .isEqualTo(healthElements.vratiVrijemeTerminaRolaDoktor(PacijentContent.DOKTOR_ID));
        healthElements.obrisiTermin(PacijentContent.PACIJENT_ID, PacijentContent.DOKTOR_ID);
        healthElements.obrisiDoktora(PrijavaContent.ID_DOKTOR);
        softAssertions.assertAll();
        log.info("Termin je uspješno ažuriran i isparavno se prikazuje u obe tablice");
    }

    @Description("Brisanje termina")
    @Test
    public void Obrisi_Termin() throws ClassNotFoundException {
        healthElements.obrisiTermin(PacijentContent.PACIJENT_ID, PacijentContent.DOKTOR_ID);
        healthElements.dodajTermin();
        izbornikPage.klikniIzbornikLijecnici();
        lijecniciPacijentPage.otvoriFilterPretraziIOdaberiDoktora();
        terminiPage.obrisiTerminPregleda();
        softAssertions.assertThat(terminiPage.terminIsDisplayed()).isEqualTo(0);
        softAssertions.assertAll();
        log.info("Termin je uspješno obrisan.");
    }

    @AfterEach
    public void testEnd() {
        loggerClass.endTestLog(this.getClass().getSimpleName());
    }
}
