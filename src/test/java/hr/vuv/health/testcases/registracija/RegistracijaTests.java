package hr.vuv.health.testcases.registracija;

import hr.vuv.health.content.RegistracijaContent;
import hr.vuv.health.pageobject.commonelements.CommonHealthElements;
import hr.vuv.health.pageobject.izbornik.IzbornikPage;
import hr.vuv.health.pageobject.registracija.RegistracijaPage;
import hr.vuv.health.pageobject.setup.StartPage;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.junit.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selenium.SeleniumTestWrapper;
import utility.LoggerClass;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;

public class RegistracijaTests extends SeleniumTestWrapper {

    private final static Logger log = LoggerFactory.getLogger(RegistracijaTests.class);
    private StartPage startPage = PageFactory.initElements(getDriver(), StartPage.class);
    private RegistracijaPage registracijaPage = PageFactory.initElements(getDriver(), RegistracijaPage.class);
    private CommonHealthElements healthElements = PageFactory.initElements(getDriver(), CommonHealthElements.class);
    private IzbornikPage izbornikPage = PageFactory.initElements(getDriver(), IzbornikPage.class);

    static LoggerClass loggerClass = new LoggerClass();

    @Before
    public void setup(){
        loggerClass.startTestLog(RegistracijaTests.class.getSimpleName());
        startPage.startApplication();
    }

    @Test
    @Description("Registracija novog korisnika sa rolom - Pacijent")
    public void Registriraj_Korisnika_Kao_Pacijenta() throws InterruptedException, ClassNotFoundException{
        izbornikPage.klikniIzbornikRegistracija();

        int nBrojKorisnikaPrijeRegistracije = healthElements.vratiBrojRegistriranihKorisnika();
        registracijaPage.registracijaKorisnikaKaoPacijenta(RegistracijaContent.KORISNICKO_IME_PACIJENT, RegistracijaContent.IME_PACIJENT,
                RegistracijaContent.PREZIME_PACIJENT, RegistracijaContent.EMAIL_PACIJENT, RegistracijaContent.MOBITEL_PACIJENT, RegistracijaContent.LOZINKA_PACIJENT);
        Thread.sleep(4000);
        int nBrojKorisnikaNakonRegistracije = healthElements.vratiBrojRegistriranihKorisnika();

        healthElements.obrisiKorisnikaPoKorisnickomImenu(RegistracijaContent.KORISNICKO_IME_PACIJENT);

        assertEquals(1, nBrojKorisnikaNakonRegistracije - nBrojKorisnikaPrijeRegistracije);
        //Assert.assertEquals(RegistracijaContent.KORISNICKO_IME, healthElements.vratiPosljedenjeDodanogKorisnika());
        log.info("Korisnik sa rolom 'Pacijent' se je uspješno registrirao u aplikaciji.");
    }

    @Test
    @Description("Registracija novog korisnika sa rolom - Doktor")
    public void Registriraj_Korisnika_Kao_Doktora() throws InterruptedException, ClassNotFoundException {
        izbornikPage.klikniIzbornikRegistracija();

        int nBrojKorisnikaPrijeRegistracije = healthElements.vratiBrojRegistriranihKorisnika();
        int nBrojDoktoraPrijeRegistracije = healthElements.vratiBrojRegistriranihDoktora();
        registracijaPage.registracijaKorisnikaKaoDoktora(RegistracijaContent.DOKTOR, RegistracijaContent.KORISNICKO_IME_DOKTOR, RegistracijaContent.IME_DOKTOR,
                RegistracijaContent.PREZIME_DOKTOR, RegistracijaContent.EMAIL_DOKTOR, RegistracijaContent.MOBITEL_DOKTOR, RegistracijaContent.LOZINKA_DOKTOR);
        Thread.sleep(4000);
        int nBrojKorisnikaNakonRegistracije = healthElements.vratiBrojRegistriranihKorisnika();
        int nBrojDoktoraNakonRegistracije = healthElements.vratiBrojRegistriranihDoktora();

        healthElements.obrisiKorisnikaPoKorisnickomImenu(RegistracijaContent.KORISNICKO_IME_DOKTOR);

        assertEquals(1, nBrojKorisnikaNakonRegistracije - nBrojKorisnikaPrijeRegistracije);
        assertEquals(1, nBrojKorisnikaNakonRegistracije - nBrojDoktoraPrijeRegistracije);
        //Assert.assertEquals(RegistracijaContent.KORISNICKO_IME, healthElements.vratiPosljedenjeDodanogKorisnika());
        log.info("Korisnik sa rolom 'Doktor' se je uspješno registrirao u aplikaciji.");
    }

    @After
    public void testEnd() {
        loggerClass.endTestLog(RegistracijaTests.class.getSimpleName());
    }
}
