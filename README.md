# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:
* Aksel Holm Jensen, S314807, s314807@oslomet.no
* Sara Lund, s364716, s364716@oslomet.no

# Arbeidsfordeling

I oppgaven har vi hatt følgende arbeidsfordeling:
* Aksel har hatt hovedansvar for oppgave 2, 4, 6 og 8???
* Sara har hatt hovedansvar for oppgave 1, 3, 5,  
* Vi har i fellesskap løst oppgave .... 

# Oppgavebeskrivelse

I oppgave 1 så gikk vi frem ved å ...

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

I **oppgave 3**...

**Oppgave 4** består av en hoveddel og en relativt liten del. I hoveddelen(indeksTil(T verdi)) opprettes en Node "posisjon" som settes på hode, og en teller som gir svar på indeksen som skal returneres hvis verdien finnes i listen. 
En for-løkke brukes for å løpe gjennom listen og .equals()-metode brukes for å sammenligne innholdet(her: om verdien vi leter matcher med verdien på en gitt posisjon). 
Finnes verdien, skal indeks-verdien returneres(om verdien vi søker finnes på hodet dvs. indeks 0 skal 0 returneres). Hvis ikke, flytter posisjonen til neste posisjon i listen og indeksen øker med 1.
Finnes ikke verdien, returneres -1. I den mindre delen (inneholder(T verdi)) brukes en if-test for å finne ut om hoveddelen returnerer "-1" eller et annet tall. Hvis det er et annet tall, inneholder listen verdien og true returneres. 
Hvis det er "-1" fantes ikke verdien og returnerer false. 