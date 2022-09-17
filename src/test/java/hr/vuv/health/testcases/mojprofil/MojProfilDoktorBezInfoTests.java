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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selenium.SeleniumTestWrapper;
import utility.LoggerClass;

public class MojProfilDoktorBezInfoTests extends SeleniumTestWrapper {

    private final static Logger log = LoggerFactory.getLogger(MojProfilDoktorBezInfoTests.class);

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
        startPage.startApplication();
        healthElements.obrisiDoktora(PrijavaContent.ID_DOKTOR_BEZ);
        healthElements.dodajDoktoraBezAdreseISpecijalizacije();
        startPage.prijavaDoktoraBezInformacija();
        izbornikPage.klikniIzbornikMojProfil();
    }

    @Description("Unesi dodatne obavezne podatke (titula, specjalizacija, adresa) doktora")
    @Test
    public void Unesi_Dodatne_Obavezne_Podatke_Doktora() throws ClassNotFoundException{
        mojProfilDoktorPage.unesiDodatneObaveznePodatkeODoktoru(MojProfilContent.PROFIL_TITULA, MojProfilContent.PROFIL_ULICA,
                MojProfilContent.PROFIL_KUCNI_BROJ, MojProfilContent.PROFIL_POSTANSKI_BROJ, MojProfilContent.PROFIL_GRAD_MJESTO,
                MojProfilContent.PROFIL_DRZAVA);
        mojProfilDoktorPage.klikniNaGumbSpremiProfil();
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
        log.info("Podaci o doktoru se uspješno promijenjeni te se ispravno prikazuju.");
    }

    @Description("Pokusaj spremanja podataka doktora bez unosa obaveznih podataka te provjera validacija za obavezna polja.")
    @Test
    public void Provjera_Validacija_Za_Obavezna_Polja_Pri_Pokusaju_Spremanja_Podataka_Doktora() throws InterruptedException, ClassNotFoundException {
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
}
