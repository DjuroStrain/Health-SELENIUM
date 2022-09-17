package hr.vuv.health.testcases.specijalizacije;

import hr.vuv.health.content.SpecijalizacijeContent;
import hr.vuv.health.pageobject.commonelements.CommonHealthElements;
import hr.vuv.health.pageobject.izbornik.IzbornikPage;
import hr.vuv.health.pageobject.prijava.PrijavaPage;
import hr.vuv.health.pageobject.setup.StartPage;
import hr.vuv.health.pageobject.specijalizacije.SpecijalizacijeAdminPage;
import hr.vuv.health.testcases.MyTestWatcher;
import hr.vuv.health.testcases.prijava.PrijavaTests;
import io.qameta.allure.Description;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selenium.Pages;
import selenium.SeleniumTestWrapper;
import utility.LoggerClass;

public class SpecijalizacijeAdminTests extends SeleniumTestWrapper {

    private final static Logger log = LoggerFactory.getLogger(SpecijalizacijeAdminTests.class);

    private StartPage startPage = PageFactory.initElements(getDriver(), StartPage.class);
    private IzbornikPage izbornikPage = PageFactory.initElements(getDriver(), IzbornikPage.class);
    private SpecijalizacijeAdminPage specijalizacijeAdminPage = PageFactory.initElements(getDriver(), SpecijalizacijeAdminPage.class);
    private CommonHealthElements healthElements = new CommonHealthElements(getDriver());
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
        startPage.prijavaAdmina();
        izbornikPage.klikniIzbornikSpecijalizacije();
    }

    @Description("Dodavanje specijalizacije")
    @Test
    public void Dodaj_Specijalizaciju() throws ClassNotFoundException {
        specijalizacijeAdminPage.dodajSpecijalizaciju(SpecijalizacijeContent.NAZIV_SPECIJALIZACIJE, SpecijalizacijeContent.OPIS_SPECIJALIZACIJE);
        softAssertions.assertThat(specijalizacijeAdminPage.vratiOpisSpecijalizacijeIzTablice()).isEqualTo(SpecijalizacijeContent.OPIS_SPECIJALIZACIJE);
        softAssertions.assertThat(specijalizacijeAdminPage.provjeraDodavanjaSpecijalizacijeUBazu()).isEqualTo(1);
        healthElements.obrisiSpecijalizaciju(SpecijalizacijeContent.NAZIV_SPECIJALIZACIJE);
        softAssertions.assertAll();
        log.info("Specijalizacija je uspješno dodana.");
    }

    @Description("Ažuriranje specijalizacije")
    @Test
    public void Azuriraj_Specijalizaciju() throws ClassNotFoundException {
        healthElements.dodajSpecijalizaciju();
        getDriver().navigate().refresh();
        specijalizacijeAdminPage.azurirajSpecijalizaciju(SpecijalizacijeContent.AZURIRANI_NAZIV_SPECIJALIZACIJE, SpecijalizacijeContent.AZURIRANI_OPIS_SPECIJALIZACIJE);
        softAssertions.assertThat(specijalizacijeAdminPage.vratiAzuriraniOpisSpecijalizacijeIzTablice()).isEqualTo(SpecijalizacijeContent.AZURIRANI_OPIS_SPECIJALIZACIJE);
        softAssertions.assertThat(specijalizacijeAdminPage.provjeraAzuriranjaSpecijalizacijeUBazi()).isEqualTo(1);
        healthElements.obrisiSpecijalizaciju(SpecijalizacijeContent.AZURIRANI_NAZIV_SPECIJALIZACIJE);
        softAssertions.assertAll();
        log.info("Specijalizacija je uspješno ažurirana.");
    }

    @Description("Brisanje specijalizacije")
    @Test
    public void Brisanje_Specijalizaciju() throws ClassNotFoundException {
        healthElements.dodajSpecijalizaciju();
        getDriver().navigate().refresh();
        specijalizacijeAdminPage.obrisiSpecijalizaciju();
        softAssertions.assertThat(specijalizacijeAdminPage.provjeraBrisanjauTablici()).isEqualTo(0);
        softAssertions.assertThat(specijalizacijeAdminPage.provjeraDodavanjaSpecijalizacijeUBazu()).isEqualTo(0);
        healthElements.obrisiSpecijalizaciju(SpecijalizacijeContent.AZURIRANI_NAZIV_SPECIJALIZACIJE);
        softAssertions.assertAll();
        log.info("Specijalizacija je uspješno obrisana.");

    }

    @AfterEach
    public void testEnd(TestInfo testInfo) {
        loggerClass.endTestLog(PrijavaTests.class.getSimpleName());
    }
}
