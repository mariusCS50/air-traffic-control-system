## Proiect: Sistem de Control al Traficului Aerian

### Descriere Generală
Acest proiect implementează un sistem de control al traficului aerian pentru un aeroport, utilizând programarea orientată pe obiecte. Sistemul gestionează pistele de aterizare/decolare și avioanele alocate acestora, cu funcționalități precum adăugarea pistelor, alocarea avioanelor și gestionarea priorităților.

### Structura Proiectului

1. **Clasa `AirTrafficControl`:**
   - Gestionează întregul sistem de control al traficului aerian.
   - Atribute principale:
      - `runways`: O hartă care asociază ID-ul pistei cu obiectul `Runway`.
      - `aiplaneRunways`: O hartă care leagă ID-ul unui avion de pista asociată.
   - Funcționalități:
      - Interpretarea comenzilor din fișiere.
      - Gestionarea pistelor și avioanelor.
      - Tratarea excepțiilor.

2. **Clasa `Airplane`:**
   - Modelează un avion, incluzând informații precum model, ID-ul zborului, locația de plecare, destinația, timpul dorit și starea actuală.
   - Atribute suplimentare:
      - `urgency`: Indicator pentru prioritatea în caz de urgență.
   - Metode:
      - `toString()`: Returnează informații detaliate despre avion.

3. **Clasa `Runway`:**
   - Modelează o pistă de aterizare sau decolare, utilizând genericitate pentru a permite doar avioane de tip `WideBodyAirplane` sau `NarrowBodyAirplane`.
   - Atribute principale:
      - `airplanesData`: O hartă folosită pentru a stoca detaliile fiecărui avion. Am ales să folosesc `HashMap` pentru că permite acces rapid (`O(1)`) la informațiile unui avion pe baza ID-ului său.
      - `airplanesPriority`: O listă prioritară care menține avioanele în ordinea priorității. Am optat pentru o listă sortată (`ArrayList`) deoarece necesită sortare personalizată în funcție de comparator și oferă flexibilitate în gestionarea priorităților.
   - Funcționalități:
      - Adăugarea avioanelor în ordine de prioritate.
      - Gestionarea stării pistei (liberă/ocupată).
      - Extragerea avionului cu cea mai mare prioritate.

4. **Enumerări:**
   - `AirplaneStatus`: Stările posibile ale unui avion (e.g., `WAITING_FOR_TAKEOFF`, `LANDED`).
   - `RunwayStatus`: Stările posibile ale unei piste (`FREE`, `OCCUPIED`).
   - `RunwayType`: Tipurile de piste (`LANDING`, `TAKEOFF`).

### Funcționalități Implementate

1. **Adăugarea unei piste:**
   - Comanda `add_runway_in_use` permite adăugarea unei piste de aterizare/decolare, specificând tipul de avioane acceptate.

2. **Alocarea unui avion către o pistă:**
   - Comanda `allocate_plane` verifică tipul pistei și alocă avionul în funcție de prioritate.

3. **Permisiunea pentru manevră:**
   - Comanda `permission_for_maneuver` actualizează starea avionului și marchează pista ca ocupată pentru un interval specific (5 minute pentru decolare, 10 minute pentru aterizare).

4. **Informații despre piste și avioane:**
   - Comenzile `runway_info` și `flight_info` generează fișiere de ieșire cu detalii despre starea pistelor și avioanelor.

5. **Tratarea excepțiilor:**
   - `IncorrectRunwayException`: Aruncată dacă avionul este alocat unei piste incorecte.
   - `UnavailableRunwayException`: Aruncată dacă pista este ocupată în momentul unei manevre.

### Motivația Alegerii Colecțiilor

1. **`runways`:**
   - Am ales să folosesc o colecție de tip `HashMap` pentru a asocia ID-ul fiecărei piste cu obiectul `Runway`. Această alegere asigură acces rapid la informațiile despre piste, cu o complexitate de `O(1)` pentru operațiile de căutare și inserare.

2. **`aiplaneRunways`:**
   - La fel ca în cazul pistelor, am utilizat o hartă de tip `HashMap` pentru a lega ID-ul fiecărui avion de pista corespunzătoare. Astfel, accesul la pista asociată unui avion este eficient și rapid.

3. **`airplanesPriority`:**
   - Pentru menținerea priorității avioanelor, am optat pentru o listă sortată (`ArrayList`). Această alegere permite sortarea flexibilă pe baza unui comparator specific, ceea ce este esențial pentru gestionarea priorităților la aterizare și decolare.

4. **`airplanesData`:**
   - Am folosit o hartă de tip `HashMap` pentru stocarea detaliilor fiecărui avion, deoarece permite căutarea rapidă a informațiilor în funcție de ID-ul avionului.