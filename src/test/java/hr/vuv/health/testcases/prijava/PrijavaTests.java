package hr.vuv.health.testcases.prijava;

import hr.vuv.health.content.PrijavaContent;
import hr.vuv.health.content.RegistracijaContent;
import hr.vuv.health.pageobject.izbornik.IzbornikPage;
import hr.vuv.health.pageobject.prijava.PrijavaPage;
import hr.vuv.health.pageobject.setup.StartPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import listeners.TestListener;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import selenium.SeleniumTestWrapper;
import utility.LoggerClass;

public class PrijavaTests extends SeleniumTestWrapper {

    private final static Logger log = LoggerFactory.getLogger(PrijavaTests.class);

    private StartPage startPage = PageFactory.initElements(getDriver(), StartPage.class);
    private PrijavaPage prijavaPage = PageFactory.initElements(getDriver(), PrijavaPage.class);
    private IzbornikPage izbornikPage = PageFactory.initElements(getDriver(), IzbornikPage.class);

    @Rule
    public TestListener testListener = new TestListener();

    static LoggerClass loggerClass = new LoggerClass();

    @Before
    public void setup() {
        loggerClass.startTestLog(PrijavaTests.class.getSimpleName());
        startPage.startApplication();
    }

    @Description("Prijava korisnika u aplikaciju.")
    @Test
    public void Prijava_Korisnika() {
        izbornikPage.klikniIzbornikPrijava();
        prijavaPage.prijavaKorisnika(PrijavaContent.KORISNICKO_IME, PrijavaContent.LOZINKA);
        log.info("Korisnik se je uspjesno prijavio u aplikaciju.");
    }

    @Test
    public void test() throws InterruptedException {
        Thread.sleep(4000);
        izbornikPage.klikniIzbornikPrijava();
    }

    @After
    public void testEnd() {
        loggerClass.endTestLog(PrijavaTests.class.getSimpleName());
    }


}
