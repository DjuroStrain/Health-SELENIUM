package hr.vuv.health.testcases.registracija;

import hr.vuv.health.base.BaseClass;
import hr.vuv.health.content.RegistracijaContent;
import hr.vuv.health.pageobject.commonelements.CommonHealthElements;
import hr.vuv.health.pageobject.izbornik.IzbornikPage;
import hr.vuv.health.pageobject.registracija.RegistracijaPage;
import hr.vuv.health.pageobject.setup.StartPage;
import io.qameta.allure.Description;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class RegistracijaTests extends BaseClass {

    private static StartPage startPage = new StartPage();
    private RegistracijaPage registracijaPage = new RegistracijaPage(getDriver());
    private CommonHealthElements healthElements = new CommonHealthElements();
    private IzbornikPage izbornikPage = new IzbornikPage(getDriver());

    @BeforeClass
    public static void setup(){
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
    }

    @Test
    @Description("Registracija novog korisnika sa rolom - Pacijent")
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

    @AfterClass
    public static void testEnd() {
        getDriver().quit();
    }
}
