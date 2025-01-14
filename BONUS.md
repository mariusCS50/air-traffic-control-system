## Bonus: Funcționalități noi

În această extensie a aplicației, se pot adăuga mai multe funcționalități care implică gestionarea pasagerilor, prioritizarea zborurilor și alocarea pistelor în funcție de urgență. Mai jos sunt detaliile funcționalităților propuse:

### 1. **Adăugarea Clasei `Person`**
Putem introduce o nouă clasă `Person`, care reprezintă o persoană implicată în zboruri (pilot sau pasager). Fiecare obiect de tip `Person` va conține următoarele informații:
- **Nume**: Numele persoanei.
- **Vârstă**: Vârsta persoanei.
- **Tip**: Tipul persoanei (pilot sau pasager).
- **Importanță**: Un coeficient numeric care indică importanța persoanei. Aceasta poate fi setată manual sau poate fi extrasă dintr-un fișier extern.

### 2. **Gestionarea Pasagerilor + Pilot**
Fiecare avion va avea o listă de pasageri și un pilot, iar aceștia vor fi instanțe ale clasei `Person`. Fiecare pasager va avea asociată o valoare a importanței, care poate fi utilizată pentru a determina prioritatea unui zbor.

### 3. **Declarația Zborurilor Urgente**
Putem adăuga un mecanism prin care un zbor să fie declarat "urgent". Dacă un avion include mai mult de 20 de pasageri importanți, zborul va fi considerat urgent. Importanța unui pasager va fi determinată de valoarea atributului `importanță` al fiecărei persoane.

Un zbor va fi considerat urgent dacă numărul total al pasagerilor cu importanță mai mare decât un prag specificat (de exemplu, 20) depășește limita respectivă. În acest caz, avionul va primi prioritate la alocarea pistei și va fi redirecționat către o pistă disponibilă.

### 4. **Gestionarea Pistelor în Funcție de Blocaje**
Putem introduce un mecanism suplimentar prin care pistele pot fi blocate sau disponibile pentru zboruri, și anume o capacitate maximă. Dacă o pistă este ocupată sau plină, avionul curent are posibilitatea de a fi redirecționat pe o altă pistă disponibilă. Pista alternativă va trebui să fie de același tip, să primească acelaș tip de avion,să aibă același oraș de aterizare/decolare pentru a asigura compatibilitatea zborului, și să nu fie ocupată la momentul curent.