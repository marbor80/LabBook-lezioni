# LabBook Challenge · venerdì 20/11

Oggi niente teoria: ogni squadra sceglie **2 o 3 sfide facili** e le completa. Ogni sfida superata vale punti.

## Come funziona
1. Scegliete una carta tra quelle sotto. Lavorate **a coppie**: due carte insieme vanno bene.
2. Quando avete finito, chiamate il **Tester di un'altra squadra**: prova il **primo caso di prova** della carta.
3. Se il risultato è quello atteso, la docente segna i punti alla lavagna.

| Carta | Progetto | Sfida | Punti |
|---|---|---|---|
| C1 | console | Classe valida | 10 |
| C2 | console | Laboratorio in manutenzione | 10 |
| W1 | web | Piè di pagina | 10 |
| W2 | web | Pagina 404 personalizzata | 10 |
| W3 | web | Totale postazioni | 10 |
| C5 | console | *Bonus:* cancellare una prenotazione | 20 |
| W4 | web | *Bonus:* pagina /orario | 20 |

---

## Carte del progetto console (C1, C2, C5)

**Preparazione, una volta sola:**
1. Scaricate il file **`ProvaSfide.java`** da questo repository: cliccateci sopra, poi sull'icona *Download raw file*.
2. Copiatelo nel package **`labbook`** del progetto console di martedì.
3. Ogni sfida ha già il suo metodo con i casi di prova pronti. Quando avete scritto la classe della carta:
   - togliete il `//` dalle righe che finiscono con **TODO** dentro il metodo della sfida;
   - togliete il `//` dalla chiamata della sfida nel `main` (per esempio `sfidaC1();`);
   - tasto destro su `ProvaSfide` → **Run File**.

### C1 · Classe valida · 10 punti
Create la classe **`RegolaClasseValida`** che `implements Regola`. La classe della prenotazione deve essere **un numero da 1 a 5 seguito da una lettera maiuscola**: 3B va bene, 6B e 4a no.

```java
if (!nuova.getClasse().matches("[1-5][A-Z]")) {
    return "Classe non valida: " + nuova.getClasse();
}
return null;
```
**Risultato atteso:** `C1.1 classe 3B: OK` · `C1.2 classe 6B: RIFIUTATA` · `C1.3 classe 4a: RIFIUTATA`

### C2 · Laboratorio in manutenzione · 10 punti
Create la classe **`RegolaManutenzione`** che `implements Regola`. Riceve **dal costruttore** il laboratorio chiuso e rifiuta le prenotazioni su quel laboratorio.

Suggerimento: copiate lo schema di `RegolaDataConsentita`, che riceveva la data nel costruttore. Attributo `private Laboratorio chiuso;` e confronto `nuova.getLaboratorio().getId() == chiuso.getId()`.

**Risultato atteso:** `C2.1 Reti: RIFIUTATA` · `C2.2 Lab 1: OK`

### C5 · Bonus: cancellare una prenotazione · 20 punti
In **`GestorePrenotazioni`** aggiungete il metodo `public boolean cancella(int id)`: toglie la prenotazione con quel codice e restituisce `true`; se non la trova restituisce `false`.

```java
for (int i = 0; i < prenotazioni.size(); i++) {
    if (prenotazioni.get(i).getId() == id) {
        prenotazioni.remove(i);
        return true;
    }
}
return false;
```
**Risultato atteso:** `C5.2 stesso slot: RIFIUTATA` · `cancella 501: true` · `cancella 999: false` · `C5.5 stesso slot dopo la cancellazione: OK`

---

## Carte del progetto web (W1, W2, W3, W4)

**Preparazione:** aprite il progetto **`tappa2-prima-pagina`** → JDK 21 → Run → `http://localhost:8080/laboratori`.
File `.java` modificato: fermate (quadrato rosso) e rifate Run. File `.html` modificato: basta ricaricare la pagina.

### W1 · Piè di pagina · 10 punti
1. In `templates/fragments.html`, subito dopo `</header>`, aggiungete:
```html
<footer th:fragment="piede" class="piede">
    LabBook · Squadra N · IIS "G. Marconi"
</footer>
```
2. In `templates/laboratori.html`, subito prima di `</body>`, aggiungete:
```html
<footer th:replace="~{fragments :: piede}"></footer>
```
3. In `static/style.css`, in fondo:
```css
.piede { text-align: center; padding: 16px; margin-top: 32px; color: #fff; background: #1D3580; }
```
**Risultato atteso:** `/laboratori` mostra in fondo la barra con il nome della vostra squadra.

### W2 · Pagina 404 personalizzata · 10 punti
1. Nella cartella `templates` create una sottocartella **`error`**.
2. Dentro create il file **`404.html`**:
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head th:replace="~{fragments :: head('Pagina non trovata')}"></head>
<body>
<header th:replace="~{fragments :: menu}"></header>
<main>
    <h1>404 – Questo laboratorio non esiste</h1>
    <p>Scrivete qui il vostro messaggio!</p>
    <p><a th:href="@{/laboratori}">Torna ai laboratori</a></p>
</main>
</body>
</html>
```
Non serve nessun controller: Spring Boot la usa da solo.

**Risultato atteso:** `/pippo` mostra la vostra pagina, non più la *Whitelabel Error Page*.

### W3 · Totale postazioni · 10 punti
1. In `LaboratorioController`, nel metodo che mostra i laboratori, prima del `return`:
```java
int totale = 0;
for (Laboratorio lab : laboratori) {
    totale = totale + lab.getPostiPc();
}
model.addAttribute("totale", totale);
```
2. In `laboratori.html`, sotto la tabella:
```html
<p>Totale postazioni: <b th:text="${totale}">0</b></p>
```
**Risultato atteso:** `Totale postazioni: 80`, oppure 92 se avete aggiunto Robotica martedì.

### W4 · Bonus: pagina /orario · 20 punti
1. Nel package `web` create la classe **`OrarioController`**:
```java
@Controller
public class OrarioController {
    @GetMapping("/orario")
    public String orario(Model model) {
        List<String> ore = Arrays.asList(
            "1ª ora 8:00 – 9:00", "2ª ora 9:00 – 10:00", "3ª ora 10:00 – 11:00",
            "4ª ora 11:00 – 12:00", "5ª ora 12:00 – 13:00", "6ª ora 13:00 – 14:00",
            "7ª ora 14:00 – 15:00");
        model.addAttribute("ore", ore);
        return "orario";
    }
}
```
(Import con **Alt+Invio**.)

2. In `templates` create **`orario.html`**: copiate `laboratori.html` e sostituite la tabella con:
```html
<ul>
    <li th:each="o : ${ore}" th:text="${o}">1ª ora</li>
</ul>
```
3. In `fragments.html` aggiungete il link nel menu: `<a th:href="@{/orario}">Orario</a>`

**Risultato atteso:** `/orario` mostra 7 righe e il menu ha il link *Orario*.

---
Se compare un errore, leggete la prima riga e la riga *Caused by*, poi chiamate la docente.
