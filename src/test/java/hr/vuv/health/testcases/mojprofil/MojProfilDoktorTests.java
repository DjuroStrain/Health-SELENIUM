package hr.vuv.health.testcases.mojprofil;

import hr.vuv.health.content.MojProfilContent;
import hr.vuv.health.content.PrijavaContent;
import hr.vuv.health.pageobject.izbornik.IzbornikPage;
import hr.vuv.health.pageobject.mojprofil.MojProfilDoktorPage;
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

import static org.junit.Assert.assertEquals;

public class MojProfilDoktorTests extends SeleniumTestWrapper {

    private final static Logger log = LoggerFactory.getLogger(MojProfilDoktorTests.class);

    private StartPage startPage = PageFactory.initElements(getDriver(), StartPage.class);
    private PrijavaPage prijavaPage = PageFactory.initElements(getDriver(), PrijavaPage.class);

    private IzbornikPage izbornikPage = PageFactory.initElements(getDriver(), IzbornikPage.class);

    private MojProfilDoktorPage mojProfilDoktorPage = PageFactory.initElements(getDriver(), MojProfilDoktorPage.class);

    static LoggerClass loggerClass = new LoggerClass();

    @Before
    public void setup() {
        loggerClass.startTestLog(this.getClass().getSimpleName());
        startPage.startApplication();
        izbornikPage.klikniIzbornikPrijava();
        prijavaPage.prijavaKorisnika(PrijavaContent.KORISNICKO_IME_DOKTOR, PrijavaContent.LOZINKA_DOKTOR);
        izbornikPage.klikniIzbornikMojProfil();
    }

    @Description("Provjera ispravnosti prikaza podataka o doktoru na njegovom profilu.")
    @Test
    public void Provjera_Ispravnosti_Prikaza_Podatka_Na_Profilu_Doktora() {
        assertEquals(MojProfilContent.PROFIL_IME, mojProfilDoktorPage.vratiVrijednostPoljaIme());
        log.info("Podatak 'Ime' se ispravno pokazuje.");
        assertEquals(MojProfilContent.PROFIL_PREZIME, mojProfilDoktorPage.vratiVrijednostPoljaPrezime());
        log.info("Podatak 'Prezime' se ispravno pokazuje.");
        assertEquals(MojProfilContent.PROFIL_GRAD_MJESTO, mojProfilDoktorPage.vratiVrijednostPoljaGradMjesto());
        log.info("Podatak 'Grad/Mjesto' se ispravno pokazuje.");
        assertEquals(MojProfilContent.PROFIL_EMAIL, mojProfilDoktorPage.vratiVrijednostPoljaEmail());
        log.info("Podatak 'Email' se ispravno pokazuje.");
    }

    @After
    public void testEnd() {
        loggerClass.endTestLog(this.getClass().getSimpleName());
    }
}
