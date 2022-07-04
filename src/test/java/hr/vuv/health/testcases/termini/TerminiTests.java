package hr.vuv.health.testcases.termini;

import hr.vuv.health.content.MojProfilContent;
import hr.vuv.health.content.PacijentContent;
import hr.vuv.health.content.PrijavaContent;
import hr.vuv.health.pageobject.commonelements.CommonHealthElements;
import hr.vuv.health.pageobject.izbornik.IzbornikPage;
import hr.vuv.health.pageobject.lijecnici.LijecniciBezRolePage;
import hr.vuv.health.pageobject.mojprofil.MojProfilDoktorPage;
import hr.vuv.health.pageobject.prijava.PrijavaPage;
import hr.vuv.health.pageobject.setup.StartPage;
import hr.vuv.health.pageobject.termini.TerminiPage;
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

public class TerminiTests extends SeleniumTestWrapper {

    private final static Logger log = LoggerFactory.getLogger(MojProfilDoktorTests.class);

    private StartPage startPage = PageFactory.initElements(getDriver(), StartPage.class);
    private PrijavaPage prijavaPage = PageFactory.initElements(getDriver(), PrijavaPage.class);
    private IzbornikPage izbornikPage = PageFactory.initElements(getDriver(), IzbornikPage.class);
    private TerminiPage terminiPage = PageFactory.initElements(getDriver(), TerminiPage.class);
    private LijecniciBezRolePage ljecniciPage = new LijecniciBezRolePage(getDriver());
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
        healthElements.dodajDoktora();
        startPage.startApplication();
        izbornikPage.klikniIzbornikPrijava();
        prijavaPage.prijavaKorisnika(PrijavaContent.KORISNICKO_IME_DOKTOR, PrijavaContent.LOZINKA_DOKTOR);
    }

    @Description("Dodaj termin i provjeri ispravnost prikaza u tablicama")
    @Test
    public void Dodaj_Termin_Pacijentu() throws ClassNotFoundException {
        healthElements.obrisiTermin(PacijentContent.PACIJENT_ID, PacijentContent.DOKTOR_ID);
        izbornikPage.klikniIzbornikTabTermini();

        terminiPage.odaberiTerminPregleda(PacijentContent.OPIS_TERMINA, PacijentContent.RAZLOG_TERMINA);

        softAssertions.assertThat(terminiPage.vratiVrijednostOpisPregledaTablica())
                .isEqualTo(healthElements.vratiOpisPregledaZaTermin(PacijentContent.PACIJENT_ID, PacijentContent.DOKTOR_ID));
        softAssertions.assertThat(terminiPage.vratiVrijednostVrijemePregledaTablica())
                .isEqualTo(healthElements.vratiVrijemeTermina(PacijentContent.PACIJENT_ID, PacijentContent.DOKTOR_ID));

        //healthElements.obrisiTermin(PacijentContent.PACIJENT_ID, PacijentContent.DOKTOR_ID);
        healthElements.obrisiDoktora(PrijavaContent.ID_DOKTOR);

        softAssertions.assertAll();

        log.info("Termin je uspješno dodan i isparavno se prikazuje u obe tablice");
    }
    /*
    @Description("Uredi termin")
    @Test
    public void Uredi_Termin() throws ClassNotFoundException, InterruptedException {
        healthElements.dodajTermin();
        izbornikPage.klikniIzbornikTabTermini();

        Thread.sleep(4000);

        healthElements.obrisiDoktora(PrijavaContent.ID_DOKTOR);
        healthElements.obrisiTermin(PacijentContent.PACIJENT_ID, PacijentContent.DOKTOR_ID);

    }*/

    @AfterEach
    public void testEnd() {
        loggerClass.endTestLog(this.getClass().getSimpleName());
    }
}
