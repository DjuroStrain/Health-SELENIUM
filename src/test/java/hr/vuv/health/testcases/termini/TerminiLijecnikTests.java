package hr.vuv.health.testcases.termini;

import hr.vuv.health.content.PacijentContent;
import hr.vuv.health.content.PrijavaContent;
import hr.vuv.health.pageobject.commonelements.CommonHealthElements;
import hr.vuv.health.pageobject.izbornik.IzbornikPage;
import hr.vuv.health.pageobject.lijecnici.LijecniciBezRolePage;
import hr.vuv.health.pageobject.lijecnici.LijecniciPacijentPage;
import hr.vuv.health.pageobject.prijava.PrijavaPage;
import hr.vuv.health.pageobject.setup.StartPage;
import hr.vuv.health.pageobject.termini.TerminiLijecnikaPage;
import hr.vuv.health.pageobject.termini.TerminiPacijentPage;
import hr.vuv.health.testcases.MyTestWatcher;
import hr.vuv.health.testcases.mojprofil.MojProfilDoktorTests;
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

public class TerminiLijecnikTests extends SeleniumTestWrapper {

    private final static Logger log = LoggerFactory.getLogger(MojProfilDoktorTests.class);

    private StartPage startPage = PageFactory.initElements(getDriver(), StartPage.class);
    private PrijavaPage prijavaPage = PageFactory.initElements(getDriver(), PrijavaPage.class);
    private IzbornikPage izbornikPage = PageFactory.initElements(getDriver(), IzbornikPage.class);
    private TerminiLijecnikaPage terminiPage = PageFactory.initElements(getDriver(), TerminiLijecnikaPage.class);
    private LijecniciBezRolePage ljecniciPage = new LijecniciBezRolePage(getDriver());
    private LijecniciPacijentPage lijecniciPacijentPage = PageFactory.initElements(getDriver(), LijecniciPacijentPage.class);

    private CommonHealthElements healthElements = new CommonHealthElements(getDriver());

    static LoggerClass loggerClass = new LoggerClass();

    SoftAssertions softAssertions = new SoftAssertions();

    @RegisterExtension
    MyTestWatcher watcher = new MyTestWatcher(getDriver());

    @BeforeEach
    public void setup(TestInfo testInfo) throws ClassNotFoundException, InterruptedException {
        loggerClass.startTestLog(this.getClass().getSimpleName());
        log.info("Naziv testa koji se izvrsava: "+ testInfo.getDisplayName());
        log.info("");
        healthElements.obrisiDoktora(PrijavaContent.ID_DOKTOR);
        healthElements.dodajDoktoraSaRadnimVremenom();
        healthElements.dodajUslugu();
        healthElements.obrisiTermin(PacijentContent.PACIJENT_ID, PacijentContent.DOKTOR_ID);
        healthElements.dodajTermin();
        startPage.startApplication();
        startPage.prijavaDoktora();
        Thread.sleep(3000);
    }

    @Test
    public void Prikaz_Termina_U_Listi_Cekanja() throws ClassNotFoundException {
        terminiPage.provjeriTerminUListiCekanja();
        softAssertions.assertThat(terminiPage.listaCekanjaIsDisplayed()).isTrue();
        softAssertions.assertAll();
    }

    @AfterEach
    public void testEnd() {
        loggerClass.endTestLog(this.getClass().getSimpleName());
    }
}
