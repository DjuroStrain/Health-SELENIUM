package hr.vuv.health.testcases.obavijesti;

import hr.vuv.health.content.ObavijestiContent;
import hr.vuv.health.content.PacijentContent;
import hr.vuv.health.content.PrijavaContent;
import hr.vuv.health.pageobject.commonelements.CommonHealthElements;
import hr.vuv.health.pageobject.izbornik.IzbornikPage;
import hr.vuv.health.pageobject.lijecnici.LijecniciBezRolePage;
import hr.vuv.health.pageobject.obavijesti.ObavijestiLjecniciPage;
import hr.vuv.health.pageobject.prijava.PrijavaPage;
import hr.vuv.health.pageobject.setup.StartPage;
import hr.vuv.health.testcases.MyTestWatcher;
import jdk.jfr.Description;
import org.assertj.core.api.SoftAssertions;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selenium.SeleniumTestWrapper;
import utility.LoggerClass;

public class ObavijestiLijecniciTests extends SeleniumTestWrapper {

    private final static Logger log = LoggerFactory.getLogger(ObavijestiLijecniciTests.class);

    private StartPage startPage = PageFactory.initElements(getDriver(), StartPage.class);
    private PrijavaPage prijavaPage = PageFactory.initElements(getDriver(), PrijavaPage.class);
    private IzbornikPage izbornikPage = PageFactory.initElements(getDriver(), IzbornikPage.class);
    private ObavijestiLjecniciPage obavijestiLjecniciPage = PageFactory.initElements(getDriver(), ObavijestiLjecniciPage.class);
    private LijecniciBezRolePage ljecniciPage = new LijecniciBezRolePage(getDriver());

    private CommonHealthElements healthElements = new CommonHealthElements(getDriver());

    static LoggerClass loggerClass = new LoggerClass();

    SoftAssertions softAssertions = new SoftAssertions();

    @RegisterExtension
    MyTestWatcher watcher = new MyTestWatcher(getDriver());

    @BeforeEach
    public void setup(@NotNull TestInfo testInfo) throws ClassNotFoundException, InterruptedException {
        loggerClass.startTestLog(this.getClass().getSimpleName());
        log.info("Naziv testa koji se izvrsava: "+ testInfo.getDisplayName());
        log.info("");
        healthElements.obrisiDoktora(PrijavaContent.ID_DOKTOR);
        healthElements.dodajDoktoraSaRadnimVremenom();
        healthElements.dodajUslugu();
        healthElements.obrisiTermin(PacijentContent.PACIJENT_ID, PacijentContent.DOKTOR_ID);
        healthElements.obrisiObavijestDoktor(PacijentContent.PACIJENT_ID, PacijentContent.DOKTOR_ID);
        healthElements.dodajTermin();
        healthElements.dodajObavijest();
        startPage.startApplication();
        startPage.prijavaDoktora();
        izbornikPage.klikniIzbornikObavijesti();
    }

    @Test
    @Description("Provjera prikazuje li se doktoru obavijest ispravno nakon što pacijent rezervira termin.")
    public void Ispravnost_Prikaza_Obavijesti() throws ClassNotFoundException {
        softAssertions.assertThat(obavijestiLjecniciPage.vratiNaslovObavijesti()).isEqualTo(ObavijestiContent.NASLOV_OBAVIJESTI_DOKTOR);
        softAssertions.assertThat(obavijestiLjecniciPage.vratiSadrzajObavijesti()).isEqualTo(ObavijestiContent.SADRZAJ_OBAVIJESTI_DOKTOR);
        softAssertions.assertAll();
        log.info("Obavijest se prikazuje ispravno");
    }

    @AfterEach
    public void testEnd() {
        loggerClass.endTestLog(this.getClass().getSimpleName());
    }
}
