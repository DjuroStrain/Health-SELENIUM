package hr.vuv.health.testcases.mojprofil;

import hr.vuv.health.content.MojProfilContent;
import hr.vuv.health.content.PrijavaContent;
import hr.vuv.health.pageobject.commonelements.CommonHealthElements;
import hr.vuv.health.pageobject.izbornik.IzbornikPage;
import hr.vuv.health.pageobject.lijecnici.LijecniciBezRolePage;
import hr.vuv.health.pageobject.mojprofil.MojProfilDoktorPage;
import hr.vuv.health.pageobject.prijava.PrijavaPage;
import hr.vuv.health.pageobject.setup.StartPage;
import hr.vuv.health.testcases.MyTestWatcher;
import io.qameta.allure.Description;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.RegisterExtension;
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
    private LijecniciBezRolePage ljecniciPage = new LijecniciBezRolePage(getDriver());
    private CommonHealthElements healthElements = new CommonHealthElements(getDriver());

    static LoggerClass loggerClass = new LoggerClass();

    SoftAssertions softAssertions = new SoftAssertions();

    @RegisterExtension
    MyTestWatcher watcher = new MyTestWatcher(getDriver());

    @BeforeEach
    public void setup(TestInfo testInfo) throws ClassNotFoundException {
        loggerClass.startTestLog(this.getClass().getSimpleName());
        log.info("Naziv testa koji se izvrsava: "+ testInfo.getDisplayName());
        log.info("");
        healthElements.obrisiDoktora(PrijavaContent.ID_DOKTOR);
        healthElements.dodajDoktora();
        startPage.startApplication();
    }

    @Description("Unesi dodatne obavezne podatke (titula, specjalizacija, adresa) doktora")
    @Test
    public void Unesi_Dodatne_Obavezne_Podatke_Doktora() throws ClassNotFoundException{
        healthElements.obrisiDoktora(PrijavaContent.ID_DOKTOR_BEZ);
        healthElements.dodajDoktoraBezAdreseISpecijalizacije();
        izbornikPage.klikniIzbornikPrijava();
        prijavaPage.prijavaKorisnika(PrijavaContent.KORISNICKO_IME_DOKTOR_BEZ, PrijavaContent.LOZINKA_DOKTOR_BEZ);
        izbornikPage.klikniIzbornikMojProfil();

        mojProfilDoktorPage.unesiDodatneObaveznePodatkeODoktoru(MojProfilContent.PROFIL_TITULA, MojProfilContent.PROFIL_ULICA,
                MojProfilContent.PROFIL_KUCNI_BROJ, MojProfilContent.PROFIL_POSTANSKI_BROJ, MojProfilContent.PROFIL_GRAD_MJESTO,
                MojProfilContent.PROFIL_DRZAVA);

        izbornikPage.klikniIzbornikKontrolnaPloca();
        izbornikPage.klikniIzbornikMojProfil();

        softAssertions.assertThat(mojProfilDoktorPage.vratiVrijednostPoljaIme()).isEqualTo(MojProfilContent.PROFIL_IME);
        softAssertions.assertThat(mojProfilDoktorPage.vratiVrijednostPoljaPrezime()).isEqualTo(MojProfilContent.PROFIL_PREZIME);
        softAssertions.assertThat(mojProfilDoktorPage.vratiVrijednostPoljaTitula()).isEqualTo(MojProfilContent.PROFIL_TITULA);
        softAssertions.assertThat(mojProfilDoktorPage.vratiVrijednostPoljaUlica()).isEqualTo(MojProfilContent.PROFIL_ULICA);
        softAssertions.assertThat(mojProfilDoktorPage.vratiVrijednostPoljaKucniBroj()).isEqualTo(MojProfilContent.PROFIL_KUCNI_BROJ);
        softAssertions.assertThat(mojProfilDoktorPage.vratiVrijednostPoljaPostanskiBroj()).isEqualTo(MojProfilContent.PROFIL_POSTANSKI_BROJ);
        softAssertions.assertThat(mojProfilDoktorPage.vratiVrijednostPoljaGradMjesto()).isEqualTo(MojProfilContent.PROFIL_GRAD_MJESTO);
        softAssertions.assertThat(mojProfilDoktorPage.vratiVrijednostPoljaDrzava()).isEqualTo(MojProfilContent.PROFIL_DRZAVA);
        softAssertions.assertThat(mojProfilDoktorPage.vratiVrijednostPoljaEmail()).isEqualTo(MojProfilContent.PROFIL_EMAIL);
        softAssertions.assertThat(mojProfilDoktorPage.vratiPrikazTabliceUslugaPrije()).isFalse();
        softAssertions.assertThat(mojProfilDoktorPage.vratiPrikazTabliceRadnoVrijemePrije()).isFalse();
        softAssertions.assertThat(mojProfilDoktorPage.vratiPrikazTabliceUslugaPoslije()).isTrue();
        softAssertions.assertThat(mojProfilDoktorPage.vratiPrikazTabliceRadnoVrijemePoslije()).isTrue();
        healthElements.obrisiDoktora(PrijavaContent.ID_DOKTOR_BEZ);

        softAssertions.assertAll();

        log.info("Podaci o doktoru se prikazuju ispravno.");
    }

    /*@Description("Provjera ispravnosti prikaza podataka o doktoru na njegovom profilu na tabu 'Opći podaci'.")
    @Test
    public void Provjera_Ispravnosti_Prikaza_Podatka_Na_Profilu_Doktora() throws InterruptedException, ClassNotFoundException {
        izbornikPage.klikniIzbornikPrijava();
        prijavaPage.prijavaKorisnika(PrijavaContent.KORISNICKO_IME_DOKTOR, PrijavaContent.LOZINKA_DOKTOR);
        izbornikPage.klikniIzbornikMojProfil();

        softAssertions.assertThat(mojProfilDoktorPage.vratiVrijednostPoljaIme()).isEqualTo(MojProfilContent.PROFIL_IME);
        softAssertions.assertThat(mojProfilDoktorPage.vratiVrijednostPoljaPrezime()).isEqualTo(MojProfilContent.PROFIL_PREZIME);
        softAssertions.assertThat(mojProfilDoktorPage.vratiVrijednostPoljaTitula()).isEqualTo(MojProfilContent.PROFIL_TITULA);
        softAssertions.assertThat(mojProfilDoktorPage.vratiVrijednostPoljaUlica()).isEqualTo(MojProfilContent.PROFIL_ULICA);
        softAssertions.assertThat(mojProfilDoktorPage.vratiVrijednostPoljaKucniBroj()).isEqualTo(MojProfilContent.PROFIL_KUCNI_BROJ);
        softAssertions.assertThat(mojProfilDoktorPage.vratiVrijednostPoljaPostanskiBroj()).isEqualTo(MojProfilContent.PROFIL_POSTANSKI_BROJ);
        softAssertions.assertThat(mojProfilDoktorPage.vratiVrijednostPoljaGradMjesto()).isEqualTo(MojProfilContent.PROFIL_GRAD_MJESTO);
        softAssertions.assertThat(mojProfilDoktorPage.vratiVrijednostPoljaDrzava()).isEqualTo(MojProfilContent.PROFIL_DRZAVA);
        softAssertions.assertThat(mojProfilDoktorPage.vratiVrijednostPoljaEmail()).isEqualTo(MojProfilContent.PROFIL_EMAIL);
        healthElements.obrisiDoktora(PrijavaContent.ID_DOKTOR);

        softAssertions.assertAll();

        log.info("Podaci o doktoru se prikazuju ispravno.");
    }*/

    @Description("Pokusaj spremanja podataka doktora bez unosa obaveznih podataka te provjera validacija za obavezna polja.")
    @Test
    public void Provjera_Validacija_Za_Obavezna_Polja_Pri_Pokusaju_Spremanja_Podataka_Doktora() throws InterruptedException, ClassNotFoundException {
        healthElements.obrisiDoktora(PrijavaContent.ID_DOKTOR_BEZ);
        healthElements.dodajDoktoraBezAdreseISpecijalizacije();
        izbornikPage.klikniIzbornikPrijava();
        prijavaPage.prijavaKorisnika(PrijavaContent.KORISNICKO_IME_DOKTOR_BEZ, PrijavaContent.LOZINKA_DOKTOR_BEZ);
        izbornikPage.klikniIzbornikMojProfil();

        mojProfilDoktorPage.obrisiIspunjenaPoljaNaTabOpciPodaci();
        mojProfilDoktorPage.klikniNaGumbKreirajBezUnosaObaveznihPodataka();

        softAssertions.assertThat(mojProfilDoktorPage.vratiValidacijuZaObaveznoPoljeIme()).isEqualTo(MojProfilContent.VALIDACIJA_IME);
        softAssertions.assertThat(mojProfilDoktorPage.vratiValidacijuZaObaveznoPoljePrezime()).isEqualTo(MojProfilContent.VALIDACIJA_PREZIME);
        softAssertions.assertThat(mojProfilDoktorPage.vratiValidacijuZaObaveznoPoljeTitula()).isEqualTo(MojProfilContent.VALIDACIJA_TITULA);
        softAssertions.assertThat(mojProfilDoktorPage.vratiValidacijuZaObaveznoPoljeSpecijalizacija()).isEqualTo(MojProfilContent.VALIDACIJA_SPECIJALIZACIJA);
        softAssertions.assertThat(mojProfilDoktorPage.vratiValidacijuZaObaveznoPoljeUlica()).isEqualTo(MojProfilContent.VALIDACIJA_ULICA);
        softAssertions.assertThat(mojProfilDoktorPage.vratiValidacijuZaObaveznoPoljeKucniBroj()).isEqualTo(MojProfilContent.VALIDACIJA_KUCNI_BROJ);
        softAssertions.assertThat(mojProfilDoktorPage.vratiValidacijuZaObaveznoPoljePostanskiBroj()).isEqualTo(MojProfilContent.VALIDACIJA_POSTANSKI_BROJ);
        softAssertions.assertThat(mojProfilDoktorPage.vratiValidacijuZaObaveznoPoljeGradMjesto()).isEqualTo(MojProfilContent.VALIDACIJA_GRAD_MJESTO);
        softAssertions.assertThat(mojProfilDoktorPage.vratiValidacijuZaObaveznoPoljeDrzava()).isEqualTo(MojProfilContent.VALIDACIJA_DRZAVA);
        softAssertions.assertThat(mojProfilDoktorPage.vratiValidacijuZaObaveznoPoljeEmail()).isEqualTo(MojProfilContent.VALIDACIJA_EMAIL);

        healthElements.obrisiDoktora(PrijavaContent.ID_DOKTOR_BEZ);

        softAssertions.assertAll();

        log.info("Prikazuju se ispravne validacije za pokušaj spremanja podataka o doktoru bez unosa obaveznih podataka.");
    }

    @Description("Dodaj djelatnost doktora")
    @Test
    public void Dodaj_Djelatnost_Doktora() throws ClassNotFoundException {
        izbornikPage.klikniIzbornikPrijava();
        prijavaPage.prijavaKorisnika(PrijavaContent.KORISNICKO_IME_DOKTOR, PrijavaContent.LOZINKA_DOKTOR);
        izbornikPage.klikniIzbornikMojProfil();

        int nBrojRedovaUslugaUBaziPrijeDodavanja = healthElements.dohvatiBrojUsluga(PrijavaContent.ID_DOKTOR);
        mojProfilDoktorPage.dodajUsluguDjelatnostiDoktora(MojProfilContent.NAZIV_USLUGE, MojProfilContent.OPIS_USLUGE, MojProfilContent.CIJENA_USLUGE);
        int nBrojRedovaUslugaUBaziPoslijeDodavanja = healthElements.dohvatiBrojUsluga(PrijavaContent.ID_DOKTOR);

        int nRazlikaBrojaRedova = nBrojRedovaUslugaUBaziPoslijeDodavanja-nBrojRedovaUslugaUBaziPrijeDodavanja;

        healthElements.obrisiUslugu();

        softAssertions.assertThat(nRazlikaBrojaRedova).isEqualTo(1);

        healthElements.obrisiDoktora(PrijavaContent.ID_DOKTOR);

        assertArrayEquals(mojProfilDoktorPage.vratiListuVrijednostiUslugeIzSadrzaja().toArray(),
                mojProfilDoktorPage.vratiListuVrijednostiUslugeIzTabliceUsluga().toArray());
        log.info("Usluga je uspješno dodana.");
    }

    @Description("Uredi djelatnost doktora")
    @Test
    public void Uredi_Djelatnost_Doktora() throws ClassNotFoundException {
        int nIDUsluge = healthElements.dodajUslugu();
        izbornikPage.klikniIzbornikPrijava();
        prijavaPage.prijavaKorisnika(PrijavaContent.KORISNICKO_IME_DOKTOR, PrijavaContent.LOZINKA_DOKTOR);
        izbornikPage.klikniIzbornikMojProfil();

        int nBrojRedovaUslugaUBaziPrijeUredivanja = healthElements.dohvatiBrojUsluga(PrijavaContent.ID_DOKTOR);
        mojProfilDoktorPage.urediUslugu(MojProfilContent.IZMIJENJEN_NAZIV_USLUGE, MojProfilContent.IZMIJENJEN_OPIS_USLUGE,
                MojProfilContent.IZMIJENJENA_CIJENA_USLUGE);
        int nBrojRedovaUslugaUBaziPoslijeUredivanja = healthElements.dohvatiBrojUsluga(PrijavaContent.ID_DOKTOR);
        int nRazlikaBrojaRedova = nBrojRedovaUslugaUBaziPoslijeUredivanja-nBrojRedovaUslugaUBaziPrijeUredivanja;

        healthElements.obrisiUsluguParametarId(nIDUsluge);

        softAssertions.assertThat(nRazlikaBrojaRedova).isEqualTo(0);

        healthElements.obrisiDoktora(PrijavaContent.ID_DOKTOR);

        assertArrayEquals(mojProfilDoktorPage.vratiListuPromijenjenihVrijednostiUslugeIzSadrzaja().toArray(),
                mojProfilDoktorPage.vratiListuVrijednostiUslugeIzTabliceUsluga().toArray());
        log.info("Usluga je uspješno uređena.");
    }

    @Description("Obrisi djelatnost doktora")
    @Test
    public void Obrisi_Djelatnost_Doktora() throws ClassNotFoundException {
        int nIDUsluge = healthElements.dodajUslugu();
        izbornikPage.klikniIzbornikPrijava();
        prijavaPage.prijavaKorisnika(PrijavaContent.KORISNICKO_IME_DOKTOR, PrijavaContent.LOZINKA_DOKTOR);
        izbornikPage.klikniIzbornikMojProfil();
        mojProfilDoktorPage.klikniNaDjelatnosti();
        int nBrojRedovaUslugaUBaziPrijeBrisanja = healthElements.dohvatiBrojUsluga(PrijavaContent.ID_DOKTOR);
        int nBrojRedovaUslugaUTabliciPrijeBrisanja = mojProfilDoktorPage.vratiBrojRedovaTabliceUsluge();
        mojProfilDoktorPage.brisanjeUsluge();
        int nBrojRedovaUslugaUBaziPoslijeBrisanja = healthElements.dohvatiBrojUsluga(PrijavaContent.ID_DOKTOR);
        int nBrojRedovaUslugaUTabliciPoslijeBrisanja = mojProfilDoktorPage.vratiBrojRedovaTabliceUsluge();

        int nRazlikaBrojaRedovaBaza = nBrojRedovaUslugaUBaziPoslijeBrisanja-nBrojRedovaUslugaUBaziPrijeBrisanja;
        int nRazlikaBrojaRedovaTablica = nBrojRedovaUslugaUTabliciPoslijeBrisanja - nBrojRedovaUslugaUTabliciPrijeBrisanja;

        healthElements.obrisiUsluguParametarId(nIDUsluge);

        softAssertions.assertThat(nRazlikaBrojaRedovaBaza).isEqualTo(1);
        softAssertions.assertThat(nRazlikaBrojaRedovaTablica).isEqualTo(1);
        log.info("Usluga je uspješno obrisana.");
    }

    @Description("Provjera validacija za pokusaj dodavanja usluge bez obaveznih podataka")
    @Test
    public void Provjera_Validacija_Za_Dodavanje_Usluga() throws ClassNotFoundException {
        izbornikPage.klikniIzbornikPrijava();
        prijavaPage.prijavaKorisnika(PrijavaContent.KORISNICKO_IME_DOKTOR, PrijavaContent.LOZINKA_DOKTOR);
        izbornikPage.klikniIzbornikMojProfil();
        mojProfilDoktorPage.klikniNaDjelatnostiIDodajUslugu();
        mojProfilDoktorPage.klikniNaGumbAzuriraj();

        softAssertions.assertThat(mojProfilDoktorPage.vratiValidacijuZaNazivUsluge()).isEqualTo(MojProfilContent.NAZIV_OBAVEZAN);
        softAssertions.assertThat(mojProfilDoktorPage.vratiValidacijuZaOpisUsluge()).isEqualTo(MojProfilContent.OPIS_OBAVEZAN);
        softAssertions.assertThat(mojProfilDoktorPage.vratiValidacijuZaCijenuUsluge()).isEqualTo(MojProfilContent.CIJENA_OBAVEZNA);

        healthElements.obrisiDoktora(PrijavaContent.ID_DOKTOR);

        softAssertions.assertAll();
        log.info("Pokazuju se ispravne validacije pri pokusaju spremanja usluge bez unosa obaveznih podataka");
    }

    @Description("Uredi radno vrijeme doktora")
    @Test
    public void Uredi_Radno_Vrijeme_Doktora() throws ClassNotFoundException {
        izbornikPage.klikniIzbornikPrijava();
        prijavaPage.prijavaKorisnika(PrijavaContent.KORISNICKO_IME_DOKTOR, PrijavaContent.LOZINKA_DOKTOR);
        izbornikPage.klikniIzbornikMojProfil();

        String sRadnoVrijemePrijepodne = MojProfilContent.RADNO_VRIJEME_PRIJEPODNE_OD+"-"+MojProfilContent.RADNO_VRIJEME_PRIJEPODNE_DO;

        mojProfilDoktorPage.unesiRadnoVrijemeDoktoraPonedjeljak(MojProfilContent.RADNO_VRIJEME_PRIJEPODNE_OD, MojProfilContent.RADNO_VRIJEME_PRIJEPODNE_DO);
        softAssertions.assertThat(mojProfilDoktorPage.vratiRadnoVrijemeZaPonedjeljakNakonUnosa()).isEqualTo(sRadnoVrijemePrijepodne);
        softAssertions.assertThat(healthElements.vratiRadnoVrijemeOrdinacijeZaPojediniDan(1, PrijavaContent.ID_DOKTOR)).isEqualTo(sRadnoVrijemePrijepodne);

        mojProfilDoktorPage.unesiRadnoVrijemeDoktoraUtorak(MojProfilContent.RADNO_VRIJEME_PRIJEPODNE_OD, MojProfilContent.RADNO_VRIJEME_PRIJEPODNE_DO);
        softAssertions.assertThat(mojProfilDoktorPage.vratiRadnoVrijemeZaUtorakNakonUnosa()).isEqualTo(sRadnoVrijemePrijepodne);
        softAssertions.assertThat(healthElements.vratiRadnoVrijemeOrdinacijeZaPojediniDan(2, PrijavaContent.ID_DOKTOR)).isEqualTo(sRadnoVrijemePrijepodne);

        String sRadnoVrijemePoslijepodne = MojProfilContent.RADNO_VRIJEME_PRIJEPODNE_DO+"-"+MojProfilContent.RADNO_VRIJEME_POSLIJEPODNE_DO;

        mojProfilDoktorPage.unesiRadnoVrijemeDoktoraSrijeda(MojProfilContent.RADNO_VRIJEME_PRIJEPODNE_OD, MojProfilContent.RADNO_VRIJEME_POSLIJEPODNE_DO);
        softAssertions.assertThat(mojProfilDoktorPage.vratiRadnoVrijemeZaSrijedaNakonUnosa()).isEqualTo(sRadnoVrijemePoslijepodne);
        softAssertions.assertThat(healthElements.vratiRadnoVrijemeOrdinacijeZaPojediniDan(3, PrijavaContent.ID_DOKTOR)).isEqualTo(sRadnoVrijemePoslijepodne);

        mojProfilDoktorPage.unesiRadnoVrijemeDoktoraCetvrtak(MojProfilContent.RADNO_VRIJEME_POSLIJEPODNE_OD, MojProfilContent.RADNO_VRIJEME_POSLIJEPODNE_DO);
        softAssertions.assertThat(mojProfilDoktorPage.vratiRadnoVrijemeZaCetvrtakNakonUnosa()).isEqualTo(sRadnoVrijemePoslijepodne);
        softAssertions.assertThat(healthElements.vratiRadnoVrijemeOrdinacijeZaPojediniDan(4, PrijavaContent.ID_DOKTOR)).isEqualTo(sRadnoVrijemePoslijepodne);

        mojProfilDoktorPage.unesiRadnoVrijemeDoktoraPetak(MojProfilContent.RADNO_VRIJEME_POSLIJEPODNE_OD, MojProfilContent.RADNO_VRIJEME_POSLIJEPODNE_DO);
        softAssertions.assertThat(mojProfilDoktorPage.vratiRadnoVrijemeZaPetakNakonUnosa()).isEqualTo(sRadnoVrijemePoslijepodne);
        softAssertions.assertThat(healthElements.vratiRadnoVrijemeOrdinacijeZaPojediniDan(5, PrijavaContent.ID_DOKTOR)).isEqualTo(sRadnoVrijemePoslijepodne);

        softAssertions.assertThat(mojProfilDoktorPage.vratiBrojRedovaTabliceRadnoVrijeme()).isEqualTo(7);

        healthElements.obrisiDoktora(PrijavaContent.ID_DOKTOR);

        softAssertions.assertAll();

        log.info("Radno vrijeme je uspješno ažurirano te se ispravno prikazuje u tablici 'Radno vrijeme' i bazi podataka.");
    }

    @AfterEach
    public void testEnd(TestInfo testInfo) {
        loggerClass.endTestLog(this.getClass().getSimpleName());
    }
}
