# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:
* Aksel Holm Jensen, S314807, s314807@oslomet.no
* Sara Lund, s364716, s364716@oslomet.no

# Arbeidsfordeling

I oppgaven har vi hatt følgende arbeidsfordeling:
* Aksel har hatt hovedansvar for oppgave 2, 4, 6 og 8???
* Sara har hatt hovedansvar for oppgave 1, 3, 5, 7 
* Vi har i fellesskap løst oppgave .... 

# Oppgavebeskrivelse

I **oppgave 1** så løste vi antall() og tom() ved å bruke antall til å finne ut hvor mange tall det er i listen og om den er tom eller ikke.
Konstruktøren lager dobbeltlenketlisten ved å først gå igjennom listen vi henter inn som parameter med en for-løkke. Vi sjekker først om 
verdien a[i] er null, og hvis den er det hopper vi til neste verdi. Så sjekker  vi om hode er null, for hvis den er det så betyr det at den første verdien
skal ligge der. Så har vi en else hvor vi da legger inn resten av verdiene. For hver verdi i løkken så settes en node q til noden vi er på sklik at
den siste vi er på blir hale.

I **oppgave 2a** ble StringBuilder brukt for å bygge tegnstrengen i begge deloppgavene(dvs. for "String toString()" og "String omvendtString()").
I **"toString()"** sjekkes det om listen er tom, og i så tilfelle returneres to klamme-parenteser("[]"). Starten for listen er satt til listen sitt "hode". 
Dernest brukes en while-løkke for å legge til verdiene en etter en, og inkludere komma og mellomrom(", ") og traversere videre til neste verdi 
så lenge det ikke pekes på listen sin "hale". Gjøres det, legges den verdien til og StringBuilderen avsluttes med "]". 
StringBuilderen vår kan nå returneres som en String. 
I **"omvendtString()"** benyttes samme konsept, men i motsatt rekkefølge(det startes på "hale" og går baklengs til "hode"). 
 
I **oppgave 2b** ble det først opprettet en ny Node som inneholder "verdi". Objects.requreNonNull benyttes for å forsikre oss om at den nye Noden ikke er null. Først sjekkes det
om hode og hale er null og antall er 0; i så tilfelle peker hode og hale på den nye noden, og både antall og endringer øker med en. Om ikke listen er tom
legges halepekeren inn bakerst i listen og det forsikres at rekkefølgen er listen + node "ny" + node "p"(med verdien)(som er hale). 
Igjen; både antall og endringer øker med en. "true" kan dernest returneres. 

I **oppgave 3a** har vi finnNode() som skal finne noden til indeksen vi tar inn som parameter. Den sjekker først om indeksen er gyldig, og så en if-test
som sjekker om listen ikke er tom. Hvis den ikke er det skal den sjekke om indeksen er over eller under antallet i listen / 2 ved hjelp av en ny if-test.
Så skal den løpe igjennom listen med en for-løkke som sjekker fram til den finder indeksen. Etter det er gjort returnerer metoden p, som er noden den ble satt på.
Hvis listen er tom, skal den returnere en NullPointerException.
Metoden hent() skal bare ta inn en indeks som vi kjører gjennom en indekssjekk, og så returnerer verdien til indeksen som blir kjørt gjennom finnNode();
oppdater() sjekker først om parameterene som blir tatt inn er gyldige, og så finner verdien til indeksen med finnNode() og så bytter om på verdiene.
Den gamle verdien blir returnert.

**Oppgave 3b** blir løst på at i metoden subliste() så blir først parameterene fra og til sjekket med indeksKontroll(). Så vil den sjekke om fra er større en til
og om fra og til ikke er større eller mindre enn det de skal være. Så lages det en subliste av Dobbeltlenketlisten. Hvis parameterene er 0 så returneres listen.
Så går vi gjennom listen med en for-løkke som for hver verdi legger de inn i sublisten med leggInn().

**Oppgave 4** består av en hoveddel og en relativt liten del. I hoveddelen(indeksTil(T verdi)) opprettes en Node "posisjon" som settes på hode, og en teller som gir svar på indeksen som skal returneres hvis verdien finnes i listen. 
En for-løkke brukes for å løpe gjennom listen og .equals()-metode brukes for å sammenligne innholdet(her: om verdien vi leter matcher med verdien på en gitt posisjon). 
Finnes verdien, skal indeks-verdien returneres(om verdien vi søker finnes på hodet dvs. indeks 0 skal 0 returneres). Hvis ikke, flytter posisjonen til neste posisjon i listen og indeksen øker med 1.
Finnes ikke verdien, returneres -1. I den mindre delen (inneholder(T verdi)) brukes en if-test for å finne ut om hoveddelen returnerer "-1" eller et annet tall. Hvis det er et annet tall, inneholder listen verdien og true returneres. 
Hvis det er "-1" fantes ikke verdien og returnerer false. 

**Oppgave 5** Metoden leggInn(int indeks, T verdi) sjekker først om indeksen og verdien er gyldige med indeksKontroll og Objects.requireNotNull.
Hvis listen er tom fra før av blir hale også hode og vi lager en ny node. Så sjekker vi om indeksen er null med en if-test. Da legger vi inn verdien først og den blir da hode.
Hvis indeksen er samme som antall, betyr det at noden skal settes bakerst, og denne verdien blir halen. Hvis vi kommer på else så betyr det at
noden skal settes inn midt i listen et sted, så da bruker vi finnNode() for å finne indeksen, og så setter verdien der mens vi flytter alle de neste nodene ett hakk.

**Oppgave 6**

**Oppgave 7** Måte 1 løste jeg på ved å gå igjennom listen med en while-løkke som sjekker om p (hode.neste) er null, for det betyr at vi har kommet til slutten.
Da blir hode, hale gjort om til null, antall til 0 og endringer blir økt.
Den andre måten er gjort ved at man går igjennom listen -1 for å bruke fjern-metoden på alle nodene unntatt den siste som man bruker når hode == hale.
Da brukes fjern-metoden på den siste og antall blir satt til 0. Så hopper vi ut av løkken.

**Oppgave 8**