package hr.vuv.health.testcases.mojprofil;

import hr.vuv.health.content.MojProfilContent;
import hr.vuv.health.content.PrijavaContent;
import hr.vuv.health.pageobject.commonelements.CommonHealthElements;
import hr.vuv.health.pageobject.izbornik.IzbornikPage;
import hr.vuv.health.pageobject.mojprofil.MojProfilDoktorPage;
import hr.vuv.health.pageobject.prijava.PrijavaPage;
import hr.vuv.health.pageobject.setup.StartPage;
import io.qameta.allure.Description;
import org.assertj.core.api.SoftAssertions;
import org.checkerframework.checker.units.qual.C;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selenium.SeleniumTestWrapper;
import utility.LoggerClass;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class MojProfilDoktorTests extends SeleniumTestWrapper {

    private final static Logger log = LoggerFactory.getLogger(MojProfilDoktorTests.class);

    private StartPage startPage = PageFactory.initElements(getDriver(), StartPage.class);
    private PrijavaPage prijavaPage = PageFactory.initElements(getDriver(), PrijavaPage.class);
    private IzbornikPage izbornikPage = PageFactory.initElements(getDriver(), IzbornikPage.class);
    private MojProfilDoktorPage mojProfilDoktorPage = PageFactory.initElements(getDriver(), MojProfilDoktorPage.class);
    private CommonHealthElements healthElements = new CommonHealthElements(getDriver());

    static LoggerClass loggerClass = new LoggerClass();

    SoftAssertions softAssertions = new SoftAssertions();

    @BeforeEach
    public void setup() {
        loggerClass.startTestLog(this.getClass().getSimpleName());
        startPage.startApplication();
        izbornikPage.klikniIzbornikPrijava();
        prijavaPage.prijavaKorisnika(PrijavaContent.KORISNICKO_IME_DOKTOR, PrijavaContent.LOZINKA_DOKTOR);
        //getDriver().get("https://localhost:7037/profile");
        izbornikPage.klikniIzbornikMojProfil();
    }

    @Description("Provjera ispravnosti prikaza podataka o doktoru na njegovom profilu na tabu 'Opći podaci'.")
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

    @Description("Dodaj djelatnost doktora")
    @Test
    public void Dodaj_Djelatnost_Doktora() throws ClassNotFoundException {
        int nBrojRedovaUslugaUBaziPrijeDodavanja = healthElements.dohvatiBrojUsluga();
        mojProfilDoktorPage.dodajUsluguDjelatnostiDoktora(MojProfilContent.NAZIV_USLUGE, MojProfilContent.OPIS_USLUGE, MojProfilContent.CIJENA_USLUGE);
        int nBrojRedovaUslugaUBaziPoslijeDodavanja = healthElements.dohvatiBrojUsluga();

        int nRazlikaBrojaRedova = nBrojRedovaUslugaUBaziPoslijeDodavanja-nBrojRedovaUslugaUBaziPrijeDodavanja;

        healthElements.obrisiUslugu();

        softAssertions.assertThat(nRazlikaBrojaRedova).isEqualTo(1);
        assertArrayEquals(mojProfilDoktorPage.vratiListuVrijednostiUslugeIzSadrzaja().toArray(),
                mojProfilDoktorPage.vratiListuVrijednostiUslugeIzTabliceUsluga().toArray());
        log.info("Usluga je uspješno dodana.");
    }

    @Description("Uredi djelatnost doktora")
    @Test
    public void Uredi_Djelatnost_Doktora() throws ClassNotFoundException {
        int nIDUsluge = healthElements.dodajUslugu();
        int nBrojRedovaUslugaUBaziPrijeUredivanja = healthElements.dohvatiBrojUsluga();
        mojProfilDoktorPage.urediUslugu(MojProfilContent.IZMIJENJEN_NAZIV_USLUGE, MojProfilContent.IZMIJENJEN_OPIS_USLUGE,
                MojProfilContent.IZMIJENJENA_CIJENA_USLUGE);
        int nBrojRedovaUslugaUBaziPoslijeUredivanja = healthElements.dohvatiBrojUsluga();

        int nRazlikaBrojaRedova = nBrojRedovaUslugaUBaziPoslijeUredivanja-nBrojRedovaUslugaUBaziPrijeUredivanja;

        //healthElements.obrisiUsluguParametarId(nIDUsluge);

        softAssertions.assertThat(nRazlikaBrojaRedova).isEqualTo(0);
        assertArrayEquals(mojProfilDoktorPage.vratiListuPromijenjenihVrijednostiUslugeIzSadrzaja().toArray(),
                mojProfilDoktorPage.vratiListuVrijednostiUslugeIzTabliceUsluga().toArray());
        log.info("Usluga je uspješno uređena.");
    }

    @Description("Obrisi djelatnost doktora")
    @Test
    public void Obrisi_Djelatnost_Doktora() throws ClassNotFoundException {
        int nIDUsluge = healthElements.dodajUslugu();
        int nBrojRedovaUslugaUBaziPrijeBrisanja = healthElements.dohvatiBrojUsluga();
        mojProfilDoktorPage.brisanjeUsluge();
        int nBrojRedovaUslugaUBaziPoslijeBrisanja = healthElements.dohvatiBrojUsluga();

        int nRazlikaBrojaRedova = nBrojRedovaUslugaUBaziPoslijeBrisanja-nBrojRedovaUslugaUBaziPrijeBrisanja;

        softAssertions.assertThat(nRazlikaBrojaRedova).isEqualTo(1);
        log.info("Usluga je uspješno obrisana.");
    }

    @AfterEach
    public void testEnd() {
        loggerClass.endTestLog(this.getClass().getSimpleName());
    }
}
