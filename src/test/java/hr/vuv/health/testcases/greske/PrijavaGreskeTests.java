package hr.vuv.health.testcases.greske;

import hr.vuv.health.content.GreskeContent;
import hr.vuv.health.pageobject.commonelements.CommonHealthElements;
import hr.vuv.health.pageobject.greske.PrijavaGreskePage;
import hr.vuv.health.pageobject.izbornik.IzbornikPage;
import hr.vuv.health.pageobject.setup.StartPage;
import hr.vuv.health.pageobject.specijalizacije.SpecijalizacijeAdminPage;
import hr.vuv.health.testcases.MyTestWatcher;
import hr.vuv.health.testcases.prijava.PrijavaTests;
import hr.vuv.health.testcases.specijalizacije.SpecijalizacijeAdminTests;
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

import static org.junit.Assert.assertEquals;

public class PrijavaGreskeTests extends SeleniumTestWrapper {

    private final static Logger log = LoggerFactory.getLogger(SpecijalizacijeAdminTests.class);

    private StartPage startPage = PageFactory.initElements(getDriver(), StartPage.class);
    private IzbornikPage izbornikPage = PageFactory.initElements(getDriver(), IzbornikPage.class);
    private PrijavaGreskePage prijavaGreskePage = PageFactory.initElements(getDriver(), PrijavaGreskePage.class);
    private CommonHealthElements healthElements = new CommonHealthElements(getDriver());
    static LoggerClass loggerClass = new LoggerClass();

    @RegisterExtension
    MyTestWatcher watcher = new MyTestWatcher(getDriver());

    SoftAssertions softAssertions = new SoftAssertions();

    @BeforeEach
    public void setup(TestInfo testInfo) {
        loggerClass.startTestLog(PrijavaTests.class.getSimpleName());
        log.info("Naziv testa koji se izvrsava: "+ testInfo.getDisplayName());
        log.info("");
        startPage.startApplication();
        startPage.prijavaPacijenta();
        izbornikPage.klikniIzbornikPrijaviGresku();
    }

    @Test
    public void Prijavi_Gresku() throws ClassNotFoundException {
        healthElements.obrisiGresku(GreskeContent.EMAIL_KORISNIKA);
        prijavaGreskePage.prijaviGresku(GreskeContent.EMAIL_KORISNIKA, GreskeContent.OPIS_GRESKE);
        assertEquals(1, healthElements.vratiDodanuGresku(GreskeContent.EMAIL_KORISNIKA));
        log.info("Greška je uspješno prijavljena.");
    }

    @Test
    public void Provjera_Prikaza_Prijavljenih_Greski() throws ClassNotFoundException {
        healthElements.obrisiGresku(GreskeContent.EMAIL_KORISNIKA);
        healthElements.dodajGresku();

    }

    @AfterEach
    public void testEnd(TestInfo testInfo) {
        loggerClass.endTestLog(PrijavaTests.class.getSimpleName());
    }
}
