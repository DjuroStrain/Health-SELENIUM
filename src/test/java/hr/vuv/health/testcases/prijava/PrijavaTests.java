package hr.vuv.health.testcases.prijava;

import hr.vuv.health.content.PrijavaContent;
import hr.vuv.health.content.RegistracijaContent;
import hr.vuv.health.pageobject.izbornik.IzbornikPage;
import hr.vuv.health.pageobject.prijava.PrijavaPage;
import hr.vuv.health.pageobject.setup.StartPage;
import hr.vuv.health.testcases.MyTestWatcher;
import hr.vuv.health.testcases.SmokeTest;
import io.qameta.allure.Description;
import org.assertj.core.api.SoftAssertions;
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

    SoftAssertions softAssertions = new SoftAssertions();

    @BeforeEach
    public void setup() {
        loggerClass.startTestLog(PrijavaTests.class.getSimpleName());
        startPage.startApplication();
        izbornikPage.klikniIzbornikPrijava();
    }

    @Description("Prijava korisnika u aplikaciju")
    @Category(SmokeTest.class)
    @Test
    public void Prijava_Korisnika_Kao_Doktora() {
        prijavaPage.prijavaKorisnika(PrijavaContent.KORISNICKO_IME_DOKTOR, PrijavaContent.LOZINKA_DOKTOR);
        assertTrue(prijavaPage.tabMojProfilIsDisplayed());
        log.info("Korisnik se je uspjesno prijavio u aplikaciju.");
    }

    @Description("Pokusaj prijave korisnika bez unosa obaveznih podataka te provjera validacija za obavezna polja.")
    @Test
    public void Provjera_Validacija_Za_Obavezna_Polja_Pri_Pokusaju_Registracije() throws InterruptedException{
        prijavaPage.klikniNaGumbPrijavaBezUnosaObaveznihPodataka();

        softAssertions.assertThat(prijavaPage.vratiValidacijuZaObaveznoPoljeKorisnickoIme()).isEqualTo(PrijavaContent.VALIDACIJA_KORISNICKA_OZNAKA);
        softAssertions.assertThat(prijavaPage.vratiValidacijuZaObaveznoPoljeLozinka()).isEqualTo(PrijavaContent.VALIDACIJA_LOZINKA);
        softAssertions.assertAll();
        
        log.info("Prikazuju se ispravne validacije za pokušaj prijave bez unosa obaveznih podataka.");
    }


    @AfterEach
    public void testEnd() {
        loggerClass.endTestLog(PrijavaTests.class.getSimpleName());
    }

}
