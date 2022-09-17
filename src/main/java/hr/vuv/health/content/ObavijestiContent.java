package hr.vuv.health.content;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ObavijestiContent {

    public final static String NASLOV_OBAVIJESTI_PACIJENT = "Termin je ažuriran.";
    static DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    static String sSadrzaj = "Termin "+ LocalDate.now().format(formatter2)+" 10:00 - "+ LocalDate.now().format(formatter2) + " 10:30 kod liječnika Duro Belacic je potvrđen";
    public final static String SADRZAJ_OBAVIJESTI_PACIJENT = sSadrzaj;

    public final static String NASLOV_OBAVIJESTI_DOKTOR = "Ivan Horvat dodao novi termin.";
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static String sSadrzaj2 = LocalDate.now().format(formatter)+" - Bolest";
    public final static String SADRZAJ_OBAVIJESTI_DOKTOR = sSadrzaj2;
}
