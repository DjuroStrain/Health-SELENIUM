package hr.vuv.health.testcases.prijava;

import hr.vuv.health.content.PrijavaContent;
import hr.vuv.health.pageobject.izbornik.IzbornikPage;
import hr.vuv.health.pageobject.prijava.PrijavaPage;
import hr.vuv.health.pageobject.setup.StartPage;
import hr.vuv.health.testcases.MyTestWatcher;
import hr.vuv.health.testcases.SmokeTest;
import io.qameta.allure.Description;
import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selenium.SeleniumTestWrapper;
import utility.LoggerClass;

import static org.junit.Assert.assertTrue;

public class PrijavaTests extends SeleniumTestWrapper {

    private final static Logger log = LoggerFactory.getLogger(PrijavaTests.class);

    private StartPage startPage = PageFactory.initElements(getDriver(), StartPage.class);
    private PrijavaPage prijavaPage = PageFactory.initElements(getDriver(), PrijavaPage.class);
    private IzbornikPage izbornikPage = PageFactory.initElements(getDriver(), IzbornikPage.class);

    static LoggerClass loggerClass = new LoggerClass();

    @RegisterExtension
    MyTestWatcher watcher = new MyTestWatcher(getDriver());

    @BeforeEach
    public void setup() {
        loggerClass.startTestLog(PrijavaTests.class.getSimpleName());
        startPage.startApplication();
    }

    @Description("Prijava korisnika u aplikaciju")
    @Category(SmokeTest.class)
    @Test
    public void Prijava_Korisnika_Kao_Doktora() {
        izbornikPage.klikniIzbornikPrijava();
        prijavaPage.prijavaKorisnika(PrijavaContent.KORISNICKO_IME_DOKTOR, PrijavaContent.LOZINKA_DOKTOR);
        assertTrue(prijavaPage.tabMojProfilIsDisplayed());
        log.info("Korisnik se je uspjesno prijavio u aplikaciju.");
    }

    @Test
    public void test(){
    }

    @AfterEach
    public void testEnd() {
        loggerClass.endTestLog(PrijavaTests.class.getSimpleName());
    }

}
