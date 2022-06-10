package hr.vuv.health.testcases.odjava;

import hr.vuv.health.content.PrijavaContent;
import hr.vuv.health.pageobject.izbornik.IzbornikPage;
import hr.vuv.health.pageobject.odjava.OdjavaPage;
import hr.vuv.health.pageobject.prijava.PrijavaPage;
import hr.vuv.health.pageobject.setup.StartPage;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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

    LoggerClass loggerClass = new LoggerClass();

    @Before
    public void setup() {
        loggerClass.startTestLog(this.getClass().getSimpleName());
        startPage.startApplication();
        izbornikPage.klikniIzbornikPrijava();
    }

    @Description("Odjava korisnika iz aplikacije i provjera uspješnosti odjave")
    @Test
    public void Odjava_Korisnika() {
        prijavaPage.prijavaKorisnika(PrijavaContent.KORISNICKO_IME_DOKTOR, PrijavaContent.LOZINKA_DOKTOR);

        assertTrue(odjavaPage.odjavaKorisnikaIzAplikacije());
        log.info("Korisnik se je uspješno odjavio iz aplikacije");
    }

    @After
    public void testEnd() {
        loggerClass.endTestLog(this.getClass().getSimpleName());
    }
}
