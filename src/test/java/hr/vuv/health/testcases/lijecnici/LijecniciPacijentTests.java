package hr.vuv.health.testcases.lijecnici;

import hr.vuv.health.content.MojProfilContent;
import hr.vuv.health.content.PacijentContent;
import hr.vuv.health.content.PrijavaContent;
import hr.vuv.health.pageobject.commonelements.CommonHealthElements;
import hr.vuv.health.pageobject.izbornik.IzbornikPage;
import hr.vuv.health.pageobject.lijecnici.LijecniciBezRolePage;
import hr.vuv.health.pageobject.lijecnici.LijecniciPacijentPage;
import hr.vuv.health.pageobject.prijava.PrijavaPage;
import hr.vuv.health.pageobject.setup.StartPage;
import hr.vuv.health.testcases.MyTestWatcher;
import io.qameta.allure.Description;
import org.assertj.core.api.SoftAssertions;
import org.checkerframework.dataflow.qual.TerminatesExecution;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.rules.TestName;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selenium.SeleniumTestWrapper;
import utility.LoggerClass;

import static org.junit.Assert.assertArrayEquals;

public class LijecniciPacijentTests extends SeleniumTestWrapper {

    private final static Logger log = LoggerFactory.getLogger(LijecniciBezRoleTests.class);

    private StartPage startPage = PageFactory.initElements(getDriver(), StartPage.class);
    private IzbornikPage izbornikPage = PageFactory.initElements(getDriver(), IzbornikPage.class);
    private LijecniciPacijentPage lijecniciPage = PageFactory.initElements(getDriver(), LijecniciPacijentPage.class);
    private CommonHealthElements healthElements = new CommonHealthElements(getDriver());
    private PrijavaPage prijavaPage = PageFactory.initElements(getDriver(), PrijavaPage.class);
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
        izbornikPage.klikniIzbornikPrijava();
        prijavaPage.prijavaKorisnikaKaoPacijenta(PrijavaContent.KORISNICKO_IME_PACIJENT, PrijavaContent.LOZINKA_PACIJENT);
    }

    @Description("Pretrazi doktora po specijalizaciji")
    @Test
    public void Pretrazi_Doktora_Po_Specijalizaciji() throws ClassNotFoundException, InterruptedException {
        izbornikPage.klikniIzbornikLijecnici();
        lijecniciPage.otvoriFilterOdaberiSpacijalzaciju(MojProfilContent.PROFIL_SPECIJALIZACIJA);

        softAssertions.assertThat(lijecniciPage.vratiNazivLjecnika()).isEqualTo(healthElements.vratiPuniNazivDoktora(PrijavaContent.ID_DOKTOR));
        softAssertions.assertThat(lijecniciPage.vratiSpecijalizacijuDoktora()).isEqualTo(healthElements.vratiSpecijalzacijuDoktora(PrijavaContent.ID_DOKTOR));
        softAssertions.assertThat(lijecniciPage.vratiAdresuDoktora()).isEqualTo(healthElements.vratiPunuAdresuDoktora(PrijavaContent.ID_DOKTOR));
        softAssertions.assertThat(lijecniciPage.vratBrojKarticaDoktora()).isEqualTo(1);

        healthElements.obrisiDoktora(PrijavaContent.ID_DOKTOR);

        softAssertions.assertAll();
        log.info("Filter doktora po specijalizaciji radi ispravno te se kartica doktora ispravno prikazuje");
    }

    @Description("Pretrazi doktora po imenu")
    @Test
    public void Pretrazi_Doktora_Po_Imenu() throws ClassNotFoundException, InterruptedException {
        izbornikPage.klikniIzbornikLijecnici();
        lijecniciPage.otvoriFilterPretraziDoktore();

        softAssertions.assertThat(lijecniciPage.vratiNazivLjecnika()).isEqualTo(healthElements.vratiPuniNazivDoktora(PrijavaContent.ID_DOKTOR));
        softAssertions.assertThat(lijecniciPage.vratiSpecijalizacijuDoktora()).isEqualTo(healthElements.vratiSpecijalzacijuDoktora(PrijavaContent.ID_DOKTOR));
        softAssertions.assertThat(lijecniciPage.vratiAdresuDoktora()).isEqualTo(healthElements.vratiPunuAdresuDoktora(PrijavaContent.ID_DOKTOR));
        softAssertions.assertThat(lijecniciPage.vratBrojKarticaDoktora()).isEqualTo(1);

        healthElements.obrisiDoktora(PrijavaContent.ID_DOKTOR);

        softAssertions.assertAll();
        log.info("Filter doktora po imenu radi ispravno te se kartica doktora ispravno prikazuje");
    }

    @Description("Provjera općih podataka doktora")
    @Test
    public void Provjera_Opcih_Podataka_Doktora() throws ClassNotFoundException {
        izbornikPage.klikniIzbornikLijecnici();
        lijecniciPage.otvoriFilterOdaberiSpacijalzaciju(MojProfilContent.PROFIL_SPECIJALIZACIJA);
        lijecniciPage.klikniNaKarticuDoktora();

        softAssertions.assertThat(lijecniciPage.vratiNazivDoktora()).isEqualTo(healthElements.vratiPuniNazivDoktora(PrijavaContent.ID_DOKTOR));
        softAssertions.assertThat(lijecniciPage.vratiSpecijalizacijuDoktora2()).isEqualTo(healthElements.vratiSpecijalzacijuDoktora(PrijavaContent.ID_DOKTOR));
        softAssertions.assertThat(lijecniciPage.vratiAdresuDoktora2()).isEqualTo(healthElements.vratiPunuAdresuDoktora(PrijavaContent.ID_DOKTOR));
        softAssertions.assertThat(lijecniciPage.vratiEmailDoktora()).isEqualTo(healthElements.vratiEmailDoktora(PrijavaContent.ID_DOKTOR));

        healthElements.obrisiDoktora(PrijavaContent.ID_DOKTOR);

        softAssertions.assertAll();
        log.info("Ispravno se prikazuju opci podaci doktora");
    }

    @Description("Provjeri ispravnost prikaza djelatnosti doktora")
    @Test
    public void Provjeri_Djelatnosti_Doktora() throws ClassNotFoundException, InterruptedException {
        int nIDUsluge = healthElements.dodajUslugu();
        izbornikPage.klikniIzbornikLijecnici();
        lijecniciPage.otvoriFilterOdaberiSpacijalzaciju(MojProfilContent.PROFIL_SPECIJALIZACIJA);
        lijecniciPage.klikniNaKarticuDoktora();

        lijecniciPage.klikniNaDjelatnosti();

        Thread.sleep(3000);

        healthElements.obrisiUslugu();
        healthElements.obrisiDoktora(PrijavaContent.ID_DOKTOR);

        assertArrayEquals(lijecniciPage.vratiListuVrijednostiUslugeIzSadrzaja().toArray(),
                lijecniciPage.vratiListuVrijednostiUslugeIzTabliceUsluga().toArray());
        log.info("Usluga se ispravno prikazuje.");
    }

    @Description("Provjeri radno vrijeme doktora")
    @Test
    public void Provjeri_Radno_Vrijeme_Doktora() throws ClassNotFoundException {
        healthElements.obrisiDoktora(PrijavaContent.ID_DOKTOR);
        healthElements.dodajDoktoraSaRadnimVremenom();
        izbornikPage.klikniIzbornikLijecnici();
        lijecniciPage.otvoriFilterOdaberiSpacijalzaciju(MojProfilContent.PROFIL_SPECIJALIZACIJA);
        lijecniciPage.klikniNaKarticuDoktora();

        lijecniciPage.klikniNaRadnoVrijeme();

        String sRadnoVrijemePrijepodne = MojProfilContent.RADNO_VRIJEME_PRIJEPODNE_OD_PACIJENT+"-"+MojProfilContent.RADNO_VRIJEME_PRIJEPODNE_DO_PACIJENT;

        softAssertions.assertThat(lijecniciPage.vratiRadnoVrijemeZaPonedjeljakNakonUnosa()).isEqualTo(sRadnoVrijemePrijepodne);

        softAssertions.assertThat(lijecniciPage.vratiRadnoVrijemeZaUtorakNakonUnosa()).isEqualTo(sRadnoVrijemePrijepodne);

        String sRadnoVrijemePoslijepodne = MojProfilContent.RADNO_VRIJEME_PRIJEPODNE_DO_PACIJENT+"-"+MojProfilContent.RADNO_VRIJEME_POSLIJEPODNE_DO_PACIJENT;


        softAssertions.assertThat(lijecniciPage.vratiRadnoVrijemeZaSrijedaNakonUnosa()).isEqualTo(sRadnoVrijemePoslijepodne);

        softAssertions.assertThat(lijecniciPage.vratiRadnoVrijemeZaCetvrtakNakonUnosa()).isEqualTo(sRadnoVrijemePoslijepodne);

        softAssertions.assertThat(lijecniciPage.vratiRadnoVrijemeZaPetakNakonUnosa()).isEqualTo(sRadnoVrijemePoslijepodne);

        softAssertions.assertThat(lijecniciPage.vratiBrojRedovaTabliceRadnoVrijeme()).isEqualTo(7);

        healthElements.obrisiDoktora(PrijavaContent.ID_DOKTOR);

        softAssertions.assertAll();

        log.info("Radno vrijeme se ispravno prikazuje u tablici 'Radno vrijeme'");
    }

    @Description("Dodaj termin i provjeri ispravnost prikaza u tablicama")
    @Test
    public void Dodaj_Termin_Pacijentu() throws ClassNotFoundException {
        healthElements.obrisiTermin(PacijentContent.PACIJENT_ID, PacijentContent.DOKTOR_ID);
        izbornikPage.klikniIzbornikLijecnici();
        lijecniciPage.otvoriFilterOdaberiSpacijalzaciju(MojProfilContent.PROFIL_SPECIJALIZACIJA);
        lijecniciPage.odaberiDoktoraZaRezervacijuTermina();

        lijecniciPage.odaberiTerminPregleda(PacijentContent.OPIS_TERMINA, PacijentContent.RAZLOG_TERMINA);

        softAssertions.assertThat(lijecniciPage.vratiVrijednostOpisPregledaTablica())
                .isEqualTo(healthElements.vratiOpisPregledaZaTermin(PacijentContent.PACIJENT_ID, PacijentContent.DOKTOR_ID));
        softAssertions.assertThat(lijecniciPage.vratiVrijednostVrijemePregledaTablica())
                .isEqualTo(healthElements.vratiVrijemeTermina(PacijentContent.PACIJENT_ID, PacijentContent.DOKTOR_ID));

        izbornikPage.klikniIzbornikMojiTermini();

        softAssertions.assertThat(lijecniciPage.vratiVrijednostOpisPregledaTablica())
                .isEqualTo(healthElements.vratiOpisPregledaZaTerminTabMojiTermini(PacijentContent.PACIJENT_ID, PacijentContent.DOKTOR_ID));
        softAssertions.assertThat(lijecniciPage.vratiVrijednostVrijemePregledaTablica())
                .isEqualTo(healthElements.vratiVrijemeTermina(PacijentContent.PACIJENT_ID, PacijentContent.DOKTOR_ID));

        healthElements.obrisiTermin(PacijentContent.PACIJENT_ID, PacijentContent.DOKTOR_ID);
        healthElements.obrisiDoktora(PrijavaContent.ID_DOKTOR);

        softAssertions.assertAll();

        log.info("Termin je uspješno dodan i isparavno se prikazuje u obe tablice");
    }

    @AfterEach
    public void testEnd() {
        loggerClass.endTestLog(this.getClass().getSimpleName());
    }
}
