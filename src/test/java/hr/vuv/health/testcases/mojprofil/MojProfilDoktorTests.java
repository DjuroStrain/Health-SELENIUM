package hr.vuv.health.testcases.mojprofil;

import hr.vuv.health.content.MojProfilContent;
import hr.vuv.health.content.PrijavaContent;
import hr.vuv.health.pageobject.izbornik.IzbornikPage;
import hr.vuv.health.pageobject.mojprofil.MojProfilDoktorPage;
import hr.vuv.health.pageobject.prijava.PrijavaPage;
import hr.vuv.health.pageobject.setup.StartPage;
import jdk.jfr.Description;
import org.assertj.core.api.SoftAssertions;
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

    SoftAssertions softAssertions = new SoftAssertions();

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
    public void Provjera_Ispravnosti_Prikaza_Podatka_Na_Profilu_Doktora() throws InterruptedException {
        softAssertions.assertThat(mojProfilDoktorPage.vratiVrijednostPoljaIme()).isEqualTo(MojProfilContent.PROFIL_IME);

        softAssertions.assertThat(mojProfilDoktorPage.vratiVrijednostPoljaPrezime()).isEqualTo(MojProfilContent.PROFIL_PREZIME);

        softAssertions.assertThat(mojProfilDoktorPage.vratiVrijednostPoljaTitula()).isEqualTo(MojProfilContent.PROFIL_TITULA);

        softAssertions.assertThat(mojProfilDoktorPage.vratiVrijednostPoljaUlica()).isEqualTo(MojProfilContent.PROFIL_ULICA);

        softAssertions.assertThat(mojProfilDoktorPage.vratiVrijednostPoljaKucniBroj()).isEqualTo(MojProfilContent.PROFIL_KUCNI_BROJ);

        softAssertions.assertThat(mojProfilDoktorPage.vratiVrijednostPoljaPostanskiBroj()).isEqualTo(MojProfilContent.PROFIL_POSTANSKI_BROJ);

        softAssertions.assertThat(mojProfilDoktorPage.vratiVrijednostPoljaGradMjesto()).isEqualTo(MojProfilContent.PROFIL_GRAD_MJESTO);

        softAssertions.assertThat(mojProfilDoktorPage.vratiVrijednostPoljaDrzava()).isEqualTo(MojProfilContent.PROFIL_DRZAVA);

        softAssertions.assertThat(mojProfilDoktorPage.vratiVrijednostPoljaEmail()).isEqualTo(MojProfilContent.PROFIL_EMAIL);

        softAssertions.assertAll();

        log.info("Podaci o doktoru se prikazuju ispravno.");
    }

    @After
    public void testEnd() {
        loggerClass.endTestLog(this.getClass().getSimpleName());
    }
}
