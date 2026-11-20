package labbook;

import java.time.LocalDate;
import java.util.List;

// LabBook Challenge - Round 1: ogni sfida ha il suo gestore e i suoi casi di prova.
// Quando avete scritto la classe o il metodo della sfida, togliete il // dalle righe segnate TODO
// e togliete il // dalla chiamata della sfida nel main.
public class ProvaSfide {

    static LocalDate mer = LocalDate.of(2026, 11, 18);
    static Laboratorio info1 = new Laboratorio(1, "Lab Informatica 1", 24, "terra");
    static Laboratorio reti = new Laboratorio(2, "Lab Sistemi e Reti", 16, "primo");

    public static void main(String[] args) {
        // sfidaC1();
        // sfidaC2();
        // sfidaC3();
        // sfidaC4();
        // sfidaC5();
        // sfidaC6();
        // sfidaC7();
        // sfidaC8();
    }

    // C1 - RegolaClasseValida (10 punti)
    static void sfidaC1() {
        System.out.println("--- C1 Classe valida ---");
        GestorePrenotazioni g = new GestorePrenotazioni();
        // g.aggiungiRegola(new RegolaClasseValida());   // TODO
        stampa("C1.1 classe 3B", g.prenota(new Prenotazione(101, info1, mer, 1, "Gallo", "3B", "")));
        stampa("C1.2 classe 6B", g.prenota(new Prenotazione(102, info1, mer, 2, "Gallo", "6B", "")));
        stampa("C1.3 classe 4a", g.prenota(new Prenotazione(103, info1, mer, 3, "Gallo", "4a", "")));
    }

    // C2 - RegolaManutenzione (10 punti)
    static void sfidaC2() {
        System.out.println("--- C2 Manutenzione ---");
        GestorePrenotazioni g = new GestorePrenotazioni();
        // g.aggiungiRegola(new RegolaManutenzione(reti));   // TODO
        stampa("C2.1 Reti", g.prenota(new Prenotazione(201, reti, mer, 1, "Gallo", "3B", "")));
        stampa("C2.2 Lab 1", g.prenota(new Prenotazione(202, info1, mer, 1, "Gallo", "3B", "")));
    }

    // C3 - RegolaFestivi (20 punti)
    static void sfidaC3() {
        System.out.println("--- C3 Festivi ---");
        GestorePrenotazioni g = new GestorePrenotazioni();
        List<LocalDate> festivi = List.of(LocalDate.of(2026, 11, 25));   // festa d'istituto
        // g.aggiungiRegola(new RegolaFestivi(festivi));   // TODO
        stampa("C3.1 25/11", g.prenota(new Prenotazione(301, info1, LocalDate.of(2026, 11, 25), 1, "Gallo", "3B", "")));
        stampa("C3.2 24/11", g.prenota(new Prenotazione(302, info1, LocalDate.of(2026, 11, 24), 1, "Gallo", "3B", "")));
    }

    // C4 - RegolaMaxDocente (20 punti)
    static void sfidaC4() {
        System.out.println("--- C4 Massimo 2 prenotazioni per docente ---");
        GestorePrenotazioni g = new GestorePrenotazioni();
        // g.aggiungiRegola(new RegolaMaxDocente());   // TODO
        stampa("C4.1 Gallo 1a ora", g.prenota(new Prenotazione(401, info1, mer, 1, "Gallo", "3B", "")));
        stampa("C4.2 Gallo 2a ora", g.prenota(new Prenotazione(402, info1, mer, 2, "Gallo", "3B", "")));
        stampa("C4.3 Gallo 3a ora", g.prenota(new Prenotazione(403, reti, mer, 3, "Gallo", "4C", "")));
        stampa("C4.4 Neri 3a ora", g.prenota(new Prenotazione(404, info1, mer, 3, "Neri", "5A", "")));
    }

    // C5 - cancella nel gestore (20 punti)
    static void sfidaC5() {
        System.out.println("--- C5 Cancellazione ---");
        GestorePrenotazioni g = new GestorePrenotazioni();
        g.aggiungiRegola(new RegolaLaboratorioLibero());
        stampa("C5.1 Lab 1, 4a ora", g.prenota(new Prenotazione(501, info1, mer, 4, "Gallo", "3B", "")));
        stampa("C5.2 stesso slot", g.prenota(new Prenotazione(502, info1, mer, 4, "Neri", "5A", "")));
        // System.out.println("C5.3 cancella 501: " + g.cancella(501));   // TODO
        // System.out.println("C5.4 cancella 999: " + g.cancella(999));   // TODO
        stampa("C5.5 stesso slot dopo la cancellazione", g.prenota(new Prenotazione(503, info1, mer, 4, "Neri", "5A", "")));
    }

    // C6 - elencoDelDocente nel gestore (20 punti)
    static void sfidaC6() {
        System.out.println("--- C6 Prenotazioni di un docente ---");
        GestorePrenotazioni g = new GestorePrenotazioni();
        g.prenota(new Prenotazione(601, info1, mer, 1, "Gallo", "3B", ""));
        g.prenota(new Prenotazione(602, reti, mer, 2, "Neri", "5A", ""));
        g.prenota(new Prenotazione(603, reti, mer, 5, "Gallo", "4C", ""));
        // for (Prenotazione p : g.elencoDelDocente("gallo")) {   // TODO
            // System.out.println(p);   // TODO
        // }   // TODO
    }

    // C7 - stampaQuadro nel gestore (30 punti)
    static void sfidaC7() {
        System.out.println("--- C7 Quadro del giorno ---");
        GestorePrenotazioni g = new GestorePrenotazioni();
        g.prenota(new Prenotazione(701, info1, mer, 1, "Gallo", "3B", ""));
        g.prenota(new Prenotazione(702, reti, mer, 1, "Neri", "5A", ""));
        g.prenota(new Prenotazione(703, info1, mer, 3, "Rossi", "4A", ""));
        // g.stampaQuadro(mer, List.of(info1, reti));   // TODO
    }

    // C8 - laboratorioPiuUsato nel gestore (30 punti)
    static void sfidaC8() {
        System.out.println("--- C8 Laboratorio più usato ---");
        GestorePrenotazioni g = new GestorePrenotazioni();
        g.prenota(new Prenotazione(801, info1, mer, 1, "Gallo", "3B", ""));
        g.prenota(new Prenotazione(802, reti, mer, 1, "Neri", "5A", ""));
        g.prenota(new Prenotazione(803, reti, mer, 2, "Rossi", "4A", ""));
        // System.out.println("Più usato: " + g.laboratorioPiuUsato(List.of(info1, reti)));   // TODO
    }

    static void stampa(String caso, String errore) {
        if (errore == null) {
            System.out.println(caso + ": OK");
        } else {
            System.out.println(caso + ": RIFIUTATA - " + errore);
        }
    }
}
