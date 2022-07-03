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
import org.bouncycastle.asn1.tsp.TSTInfo;
import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
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
    public void setup(TestInfo testInfo) {
        loggerClass.startTestLog(PrijavaTests.class.getSimpleName());
        log.info("Naziv testa koji se izvrsava: "+ testInfo.getDisplayName());
        log.info("");
        startPage.startApplication();
        izbornikPage.klikniIzbornikPrijava();
    }

    @Description("Prijava korisnika u aplikaciju sa rolom doktor")
    @Category(SmokeTest.class)
    @Test
    public void Prijava_Korisnika_Kao_Doktora() {
        prijavaPage.prijavaKorisnika(PrijavaContent.KORISNICKO_IME_DOKTOR2, PrijavaContent.LOZINKA_DOKTOR2);
        assertTrue(prijavaPage.tabMojProfilIsDisplayed());
        log.info("Korisnik sa rolom doktor se je uspjesno prijavio u aplikaciju.");
    }

    @Description("Prijava korisnika u aplikaciju sa rolom pacijent")
    @Category(SmokeTest.class)
    @Test
    public void Prijava_Korisnika_Kao_Pacijenta() {
        prijavaPage.prijavaKorisnikaKaoPacijenta(PrijavaContent.KORISNICKO_IME_PACIJENT, PrijavaContent.LOZINKA_PACIJENT);
        assertTrue(prijavaPage.tabMojiTerminiIsDisplayed());
        log.info("Korisnik sa rolom pacijent  se je uspjesno prijavio u aplikaciju.");
    }


    @Description("Pokusaj prijave korisnika bez unosa obaveznih podataka te provjera validacija za obavezna polja.")
    @Test
    public void Provjera_Validacija_Za_Obavezna_Polja_Pri_Pokusaju_Prijave() throws InterruptedException{
        prijavaPage.klikniNaGumbPrijavaBezUnosaObaveznihPodataka();

        softAssertions.assertThat(prijavaPage.vratiValidacijuZaObaveznoPoljeKorisnickoIme()).isEqualTo(PrijavaContent.VALIDACIJA_KORISNICKA_OZNAKA);
        softAssertions.assertThat(prijavaPage.vratiValidacijuZaObaveznoPoljeLozinka()).isEqualTo(PrijavaContent.VALIDACIJA_LOZINKA);
        softAssertions.assertAll();
        //proba
        log.info("Prikazuju se ispravne validacije za pokušaj prijave bez unosa obaveznih podataka.");
    }


    @AfterEach
    public void testEnd(TestInfo testInfo) {
        loggerClass.endTestLog(PrijavaTests.class.getSimpleName());
    }

}
