# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:
* Aksel Holm Jensen, S314807, s314807@oslomet.no
* Sara Lund, s364716, s364716@oslomet.no

# Arbeidsfordeling

I oppgaven har vi hatt følgende arbeidsfordeling:
* Aksel har hatt hovedansvar for oppgave 2, 
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
 
