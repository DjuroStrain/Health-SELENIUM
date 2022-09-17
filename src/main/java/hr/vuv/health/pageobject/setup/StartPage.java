package hr.vuv.health.pageobject.setup;

import hr.vuv.health.content.PrijavaContent;
import hr.vuv.health.pageobject.izbornik.IzbornikPage;
import hr.vuv.health.pageobject.prijava.PrijavaPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selenium.Pages;

public class StartPage extends Pages {

    public StartPage(final WebDriver driver) {
        super(driver);
    }
    public IzbornikPage izbornikPage = PageFactory.initElements(driver, IzbornikPage.class);
    public PrijavaPage prijavaPage = PageFactory.initElements(driver, PrijavaPage.class);

    @Step("Pokretanje aplikacije")
    public void startApplication() {
        super.open();
    }

    @Step("Prijava u aplikaciju")
    public void prijavaDoktoraBezInformacija() {
        izbornikPage.klikniIzbornikPrijava();
        prijavaPage.prijavaKorisnika(PrijavaContent.KORISNICKO_IME_DOKTOR_BEZ, PrijavaContent.LOZINKA_DOKTOR_BEZ);
    }

    @Step("Prijava u aplikaciju")
    public void prijavaDoktora() {
        izbornikPage.klikniIzbornikPrijava();
        prijavaPage.prijavaKorisnika(PrijavaContent.KORISNICKO_IME_DOKTOR, PrijavaContent.LOZINKA_DOKTOR);
    }

    @Step("Prijava u aplikaciju")
    public void prijavaPacijenta() {
        izbornikPage.klikniIzbornikPrijava();
        prijavaPage.prijavaKorisnika(PrijavaContent.KORISNICKO_IME_PACIJENT, PrijavaContent.LOZINKA_PACIJENT);
    }

    @Step("Prijava u aplikaciju")
    public void prijavaAdmina() {
        izbornikPage.klikniIzbornikPrijava();
        prijavaPage.prijavaKorisnika(PrijavaContent.KORISNICKO_IME_ADMIN, PrijavaContent.LOZINKA_ADMIN);
    }
}
