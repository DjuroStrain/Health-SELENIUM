package hr.vuv.health.testcases.prijava;

import hr.vuv.health.base.BaseClass;
import hr.vuv.health.content.RegistracijaContent;
import hr.vuv.health.pageobject.izbornik.IzbornikPage;
import hr.vuv.health.pageobject.prijava.PrijavaPage;
import hr.vuv.health.pageobject.setup.StartPage;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import listeners.TestListener;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;

public class PrijavaTests extends BaseClass {

    private final static Logger log = LoggerFactory.getLogger(PrijavaTests.class);

    private static StartPage startPage = new StartPage();
    private PrijavaPage prijavaPage = new PrijavaPage(getDriver());
    private IzbornikPage izbornikPage = new IzbornikPage(getDriver());

    @Rule
    public TestListener testListener = new TestListener(getDriver());

    @BeforeClass
    public static void setup() {
        startPage.startApplication();
    }

    @Description("Prijava page")
    @Test
    public void Prijava_Korisnika() {
        izbornikPage.klikniIzbornikPrijava();
        prijavaPage.prijavaKorisnika(RegistracijaContent.KORISNICKO_IME, RegistracijaContent.LOZINKA);
        log.info("Korisnik se je uspješno prijavio u aplikaciju.");
    }

    @AfterClass
    public static void testEnd() {
        getDriver().quit();
    }


}
