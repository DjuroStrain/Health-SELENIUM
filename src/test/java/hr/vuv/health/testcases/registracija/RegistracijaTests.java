package hr.vuv.health.testcases.registracija;

import hr.vuv.health.content.RegistracijaContent;
import hr.vuv.health.pageobject.commonelements.CommonHealthElements;
import hr.vuv.health.pageobject.izbornik.IzbornikPage;
import hr.vuv.health.pageobject.registracija.RegistracijaPage;
import hr.vuv.health.pageobject.setup.StartPage;
import hr.vuv.health.testcases.MyTestWatcher;
import io.qameta.allure.Description;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selenium.SeleniumTestWrapper;
import utility.LoggerClass;

import static org.junit.Assert.assertEquals;

public class RegistracijaTests extends SeleniumTestWrapper {

    private final static Logger log = LoggerFactory.getLogger(RegistracijaTests.class);
    private StartPage startPage = PageFactory.initElements(getDriver(), StartPage.class);
    private RegistracijaPage registracijaPage = PageFactory.initElements(getDriver(), RegistracijaPage.class);
    private CommonHealthElements healthElements = PageFactory.initElements(getDriver(), CommonHealthElements.class);
    private IzbornikPage izbornikPage = PageFactory.initElements(getDriver(), IzbornikPage.class);

    static LoggerClass loggerClass = new LoggerClass();

    @RegisterExtension
    MyTestWatcher watcher = new MyTestWatcher(getDriver());

    SoftAssertions softAssertions = new SoftAssertions();

    @BeforeEach
    public void setup(){
        loggerClass.startTestLog(RegistracijaTests.class.getSimpleName());
        startPage.startApplication();
        izbornikPage.klikniIzbornikRegistracija();
    }

    @Test
    @Description("Registracija novog korisnika sa rolom - Pacijent")
    public void Registriraj_Korisnika_Kao_Pacijenta() throws InterruptedException, ClassNotFoundException{
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

    @Description("Pokusaj registriranja korisnika bez unosa obaveznih podataka te provjera validacija za obavezna polja.")
    @Test
    public void Provjera_Validacija_Za_Obavezna_Polja_Pri_Pokusaju_Registracije() throws InterruptedException{
        registracijaPage.klikniNaGumbKreirajBezUnosaObaveznihPodataka();

        softAssertions.assertThat(registracijaPage.vratiValidacijuZaObaveznoPoljeTitula()).isEqualTo(RegistracijaContent.VALIDACIJA_TITULA);
        softAssertions.assertThat(registracijaPage.vratiValidacijuZaObaveznoPoljeKorisnickoIme()).isEqualTo(RegistracijaContent.VALIDACIJA_KORISNICKA_OZNAKA);
        softAssertions.assertThat(registracijaPage.vratiValidacijuZaObaveznoPoljeIme()).isEqualTo(RegistracijaContent.VALIDACIJA_IME);
        softAssertions.assertThat(registracijaPage.vratiValidacijuZaObaveznoPoljePrezime()).isEqualTo(RegistracijaContent.VALIDACIJA_PREZIME);
        softAssertions.assertThat(registracijaPage.vratiValidacijuZaObaveznoPoljeEmail()).isEqualTo(RegistracijaContent.VALIDACIJA_EMAIL);
        softAssertions.assertThat(registracijaPage.vratiValidacijuZaObaveznoPoljeTelBroj()).isEqualTo(RegistracijaContent.VALIDACIJA_TELEFONSKI_BROJ);
        softAssertions.assertThat(registracijaPage.vratiValidacijuZaObaveznoPoljeLozinka()).isEqualTo(RegistracijaContent.VALIDACIJA_LOZINKA);
        softAssertions.assertAll();

        log.info("Prikazuju se ispravne validacije za pokušaj registracije bez unosa obaveznih podataka.");
    }

    @AfterEach
    public void testEnd() {
        loggerClass.endTestLog(RegistracijaTests.class.getSimpleName());
    }
}
