package hr.vuv.health.testcases.odjava;

import hr.vuv.health.content.PrijavaContent;
import hr.vuv.health.pageobject.commonelements.CommonHealthElements;
import hr.vuv.health.pageobject.izbornik.IzbornikPage;
import hr.vuv.health.pageobject.odjava.OdjavaPage;
import hr.vuv.health.pageobject.prijava.PrijavaPage;
import hr.vuv.health.pageobject.setup.StartPage;
import hr.vuv.health.testcases.MyTestWatcher;
import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selenium.SeleniumTestWrapper;
import utility.LoggerClass;

import static org.junit.Assert.assertTrue;

public class OdjavaTests extends SeleniumTestWrapper {

    private final static Logger log = LoggerFactory.getLogger(OdjavaTests.class);

    private StartPage startPage = PageFactory.initElements(getDriver(), StartPage.class);

    private PrijavaPage prijavaPage = PageFactory.initElements(getDriver(), PrijavaPage.class);

    private OdjavaPage odjavaPage = PageFactory.initElements(getDriver(), OdjavaPage.class);
    private IzbornikPage izbornikPage = PageFactory.initElements(getDriver(), IzbornikPage.class);

    private CommonHealthElements healthElements = new CommonHealthElements(getDriver());
    LoggerClass loggerClass = new LoggerClass();

    @RegisterExtension
    MyTestWatcher watcher = new MyTestWatcher(getDriver());
    @BeforeEach
    public void setup(TestInfo testInfo) throws ClassNotFoundException {
        loggerClass.startTestLog(this.getClass().getSimpleName());
        log.info("Naziv testa koji se izvrsava: "+ testInfo.getDisplayName());
        log.info("");
        startPage.startApplication();
        healthElements.obrisiDoktora(PrijavaContent.ID_DOKTOR);
        healthElements.dodajDoktora();
        izbornikPage.klikniIzbornikPrijava();
    }

    @Description("Odjava korisnika iz aplikacije i provjera uspješnosti odjave")
    @Test
    public void Odjava_Korisnika() {
        prijavaPage.prijavaKorisnika(PrijavaContent.KORISNICKO_IME_DOKTOR, PrijavaContent.LOZINKA_DOKTOR);
        assertTrue(odjavaPage.odjavaKorisnikaIzAplikacije());
        log.info("Korisnik se je uspješno odjavio iz aplikacije");
    }

    @AfterEach
    public void testEnd(TestInfo testInfo) {
        loggerClass.endTestLog(this.getClass().getSimpleName());
    }
}
