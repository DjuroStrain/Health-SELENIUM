package hr.vuv.health.content;

public class MojProfilContent {

    /*
    * Podaci o doktoru
    * */
    /*public final static String PROFIL_IME = "Duro";
    public final static String PROFIL_PREZIME = "Belacic";
    public final static String PROFIL_TITULA = "Doktor";
    public final static String PROFIL_ULICA = "Ulica kralja Tomislava";
    public final static String PROFIL_KUCNI_BROJ = "9";
    public final static String PROFIL_POSTANSKI_BROJ = "21101";
    public final static String PROFIL_DRZAVA = "Hrvatska";
    public final static String PROFIL_GRAD_MJESTO = "Split";
    public final static String PROFIL_EMAIL = "durobelacic@gmail.com";*/

    //Br 2
    public final static String PROFIL_IME = "Duro";
    public final static String PROFIL_PREZIME = "Belacic";
    public final static String PROFIL_TITULA = "dr. mr.";
    public final static String PROFIL_ULICA = "Ul. Fausta Vrančića";
    public final static String PROFIL_KUCNI_BROJ = "2";
    public final static String PROFIL_POSTANSKI_BROJ = "43000";
    public final static String PROFIL_DRZAVA = "Hrvatska";
    public final static String PROFIL_GRAD_MJESTO = "Bjelovar";
    public final static String PROFIL_EMAIL = "durobelacic@gmail.com";

    public final static String PROFIL_SPECIJALIZACIJA = "Stomatologija";

    //Validacije
    public final static String VALIDACIJA_IME = "Ime je obavezno.";
    public final static String VALIDACIJA_PREZIME = "Prezime je obavezno.";
    public final static String VALIDACIJA_TITULA = "Titula je obavezna.";
    public final static String VALIDACIJA_SPECIJALIZACIJA = "Specijalizacija je obavezna.";
    public final static String VALIDACIJA_ULICA = "Ulica je obavezna.";
    public final static String VALIDACIJA_KUCNI_BROJ = "Kućni broj je obavezan.";
    public final static String VALIDACIJA_POSTANSKI_BROJ = "Poštanski broj je obavezan.";
    public final static String VALIDACIJA_GRAD_MJESTO = "Grad/Mjesto je obavezno.";
    public final static String VALIDACIJA_DRZAVA = "Država je obavezna.";
    public final static String VALIDACIJA_EMAIL = "Email je obavezan.";

    /*
    * Podaci za unos nove usluge
    * */
    public final static String NAZIV_USLUGE = "Pregled";
    public final static String OPIS_USLUGE = "Opis pregleda";
    public final static String CIJENA_USLUGE = "GBP52.00";

    /*
    * Podaci za izmijenu uslugu
    * */
    public final static String IZMIJENJEN_NAZIV_USLUGE = "Pregled promijena";
    public final static String IZMIJENJEN_OPIS_USLUGE = "Opis pregleda promijena";
    public final static String IZMIJENJENA_CIJENA_USLUGE = "GBP100.00";


    /*
    * Radno vrijeme - doktor
    * */
    //prijepodne
    public final static String RADNO_VRIJEME_PRIJEPODNE_OD = "08:00:00";
    public final static String RADNO_VRIJEME_PRIJEPODNE_DO = "14:00:00";

    //poslijepodne
    public final static String RADNO_VRIJEME_POSLIJEPODNE_OD = "14:00:00";
    public final static String RADNO_VRIJEME_POSLIJEPODNE_DO = "20:00:00";

    /*
     * Radno vrijeme - kada gleda pacijent
     * */
    //prijepodne
    public final static String RADNO_VRIJEME_PRIJEPODNE_OD_PACIJENT = "08:00";
    public final static String RADNO_VRIJEME_PRIJEPODNE_DO_PACIJENT = "14:00";

    //poslijepodne
    public final static String RADNO_VRIJEME_POSLIJEPODNE_OD_PACIJENT = "14:00";
    public final static String RADNO_VRIJEME_POSLIJEPODNE_DO_PACIJENT = "20:00";

    //ne radi
    public final static String NE_RADI = "00:00:00";

    //Validacije
    public final static String NAZIV_OBAVEZAN = "Naziv je obavezan.";
    public final static String OPIS_OBAVEZAN = "Opis je obavezan.";
    public final static String CIJENA_OBAVEZNA = "Cijena mora iznositi barem 1 HRK";
}
