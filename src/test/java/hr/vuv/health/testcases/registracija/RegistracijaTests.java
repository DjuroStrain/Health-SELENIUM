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
import selenium.SeleniumTestWrapper;
import utility.LoggerClass;

import java.io.ByteArrayInputStream;

public class RegistracijaTests extends SeleniumTestWrapper {

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
    public void Registriraj_Korisnika_Kao_Pacijenta() throws InterruptedException {
        izbornikPage.klikniIzbornikRegistracija();

        int nBrojKorisnikaPrijeRegistracije = healthElements.vratiBrojRegistriranihKorisnika();
        registracijaPage.registracijaKorisnikaKaoPacijenta(RegistracijaContent.KORISNICKO_IME, RegistracijaContent.IME,
                RegistracijaContent.PREZIME, RegistracijaContent.EMAIL, RegistracijaContent.ADRESA, RegistracijaContent.DATUM_RODENJA,
                RegistracijaContent.O_MENI, RegistracijaContent.LOZINKA);
        Thread.sleep(4000);
        int nBrojKorisnikaNakonRegistracije = healthElements.vratiBrojRegistriranihKorisnika();

        Assert.assertEquals(1, nBrojKorisnikaNakonRegistracije - nBrojKorisnikaPrijeRegistracije);
        //Assert.assertEquals(RegistracijaContent.KORISNICKO_IME, healthElements.vratiPosljedenjeDodanogKorisnika());
        Allure.addAttachment("Snimka zaslona kada test nije prošao:", new ByteArrayInputStream(((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES)));
    }

    @Test
    @Description("Registracija novog korisnika sa rolom - Doktor")
    public void Registriraj_Korisnika_Kao_Doktora() throws InterruptedException {
        izbornikPage.klikniIzbornikRegistracija();

        int nBrojKorisnikaPrijeRegistracije = healthElements.vratiBrojRegistriranihKorisnika();
        registracijaPage.registracijaKorisnikaKaoDoktora("Doctor", RegistracijaContent.KORISNICKO_IME, RegistracijaContent.IME,
                RegistracijaContent.PREZIME, RegistracijaContent.EMAIL, RegistracijaContent.ADRESA, RegistracijaContent.DATUM_RODENJA,
                RegistracijaContent.O_MENI, RegistracijaContent.LOZINKA);
        Thread.sleep(4000);
        int nBrojKorisnikaNakonRegistracije = healthElements.vratiBrojRegistriranihKorisnika();

        Assert.assertEquals(1, nBrojKorisnikaNakonRegistracije - nBrojKorisnikaPrijeRegistracije);
        //Assert.assertEquals(RegistracijaContent.KORISNICKO_IME, healthElements.vratiPosljedenjeDodanogKorisnika());
    }

    @After
    public void testEnd() {
        loggerClass.endTestLog(RegistracijaTests.class.getSimpleName());
    }
}
