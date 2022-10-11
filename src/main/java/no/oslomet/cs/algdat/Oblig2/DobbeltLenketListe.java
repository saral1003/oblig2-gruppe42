package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;


public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {   // Oppgave 1
        //throw new UnsupportedOperationException();
        hode = null;
        antall = 0;
        endringer = 0;
    }

    public DobbeltLenketListe(T[] a) {  // Oppgave 1
        //throw new UnsupportedOperationException();
        Objects.requireNonNull(a, "Tabellen a er null!"); // Sjekker om tabellen er tom

        Node<T> q = null; // Brukes for å finne verdien til halen
        for (int i = 0; i < a.length; i++) {
            if (a[i] == null) { // Sjekker om verdien i a er null
                continue;
            }
            if (hode == null) { // Setter inn hode
                hode = new Node<>(a[i], null, null);
                hale = hode;
                q = hode;
            } else {
                Node<T> p = new Node<>(a[i]); // Oppretter nye noder og setter inn verdien
                q.neste = p;
                p.forrige = q;
                q = p;
            }
            antall++; // Øker antall for hver verdi vi legger inn
        }
        hale = q; // Setter hale til q
    }

    private Node<T> finnNode(int index) {   // Oppgave 3a
        indeksKontroll(index, true);
        if(!tom()){
            Node<T> p = hode;
            if (index < (antall/2)) {
                for (int i = 0; i <= index; i++) {
                    if (index == i) {
                        return p;
                    } else {
                        p = p.neste;
                    }
                }
            } else {
                p = hale;
                for (int i = antall-1; i >= index; i--) {
                    if (index == i) {
                        return p;
                    } else {
                        p = p.forrige;
                    }
                }
            }
            return p;
        } else if (tom()) {
            throw new NullPointerException("Listen er tom!");
        }
        return null;
    }

    public Liste<T> subliste(int fra, int til) {    // Oppgave 3b
        //throw new UnsupportedOperationException();
        indeksKontroll(fra, true);
        indeksKontroll(til, true);

        if ((fra < 0 ) || (til > antall)) { // Sjekker om indeksene er gyldige
            throw new IndexOutOfBoundsException("Ugyldig indeks!");
        }
        if (fra > til){ // Sjekker om fra er større enn til
            throw new IllegalArgumentException("Input er feil!");
        }

        Liste<T> subliste = new DobbeltLenketListe<>(); // Oppretter sublisten
        if ((fra == 0) && (til == 0)) { // Sjekker om indeksene er 0
            return subliste;
        }

        Node<T> p = finnNode(fra);

        for (int i = fra; i < til; i++) { // Løper igjennom listen
            subliste.leggInn(p.verdi); // Legger inn verdien
            p = p.neste;
        }

        return subliste;
    }

    @Override
    public int antall() {   // Oppgave 1
        //throw new UnsupportedOperationException();
        return antall;
    }

    @Override
    public boolean tom() {  // Oppgave 1
        //throw new UnsupportedOperationException();
        if (antall == 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean leggInn(T verdi) {   // Oppgave 2b
        //throw new UnsupportedOperationException();
        Node<T> p = new Node<>(verdi);                                      // Oppretter ny Node

        Objects.requireNonNull(verdi, "Null-verdier er ikke tillatt");     // Null-verdier er ikke tillatt

        if (hode == null && hale == null && antall == 0){                  // Tilfelle 1(listen er tom på forhånd)
            hode = hale = p;                                               // Både hode og hale peker på den nye noden
            antall += 1;                                                   // antall øker med en
            endringer += 1;                                                // endringer øker med en
        }
        else {                                                             // Tilfelle 2(listen er ikke tom)
            Node ny = hale;                                                // Ny node "ny" er halen
            hale = p;                                                      // halen er nå p
            ny.neste = p;                                                  // "p" skal være etter "ny"
            p.forrige = ny;                                                // "ny" skal være før "p"
            antall += 1;                                                   // antall øker med en
            endringer += 1;                                                // endringer øker med en
        }
        return true;                                                       // returnerer true
    }

    @Override
    public void leggInn(int indeks, T verdi) { // Oppgave 5 - Koden er inspirert av Kompendiet, promgramkode 3.3.2 f
        //throw new UnsupportedOperationException();
        Objects.requireNonNull(verdi, "Ikke gyldig tall.");
        indeksKontroll(indeks, true);
        if (indeks == 0) { // Verdi legges først hvis indeks er null
            //Node<T> tmp = hode;
            hode = new Node<>(verdi, null, hode);
            if (antall == 0) { // Hvis listen er tom
                hale = hode;
            }
        } else if (indeks == antall) { // Hvis indeksen er bakerst
            hale = hale.neste = new Node<>(verdi, hale.forrige, null); // Legger inn noden bakerst
        } else { // Hvis indeksen ikke er først eller sist i listen
            Node<T> p = hode;
            for (int i = 1; i < indeks; i++) { // Leter etter indeksen
                p = p.neste;
            }
            p.neste = new Node<>(verdi, p.forrige, p.neste); // Lager node når indeksen er funne
        }
        antall++;
        endringer++;
    }

    @Override
    public boolean inneholder(T verdi) { // Oppgave 4 - del 2
        //throw new UnsupportedOperationException();
        if (indeksTil(verdi) != -1) {   // hvis metoden indeksTil IKKE er -1, inneholder listen verdien...
            return true;                // ... og true returneres
        }
        else {                          // hvis ikke, betyr det at verdien finnes i listen...
            return false;
        }// ... og false returneres
    }

    @Override
    public T hent(int indeks) { // Oppgave 3a
        //throw new UnsupportedOperationException();
        indeksKontroll(indeks, false); // Sjekker om indeksen er gyldig
        return finnNode(indeks).verdi; // Finner verdien til indeksen
    }

    @Override
    public int indeksTil(T verdi) { // Oppgave 4 - del 1
        //throw new UnsupportedOperationException();
        Node<T> posisjon = hode;                        // Node "posisjon" er starten av listen(hode)
        int indeks = 0;                                 // en teller kalt "indeks" er satt til null
        for (int i = 0; i < antall; i++){               // en for-løkke løper gjennom listen
            if (posisjon.verdi.equals(verdi)) {         // hvis posisjonen i listen sin verdi matcher med verdien vi søker...
                return indeks;                          // returneres indeksen...
                                                        // og det hoppes ut av for-løkka
            }
            else {                                      // hvis ikke posisjonen i listen sin verdi matcher med verdien vi søker...
                posisjon = posisjon.neste;              // ... hopper vi videre til neste posisjon...
                indeks++;                               // ... og indekstallet økes med 1
            }
        }
        return -1;                                      // hvis verdien ikke finnes i listen returneres -1;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {  // Oppgave 3a
        //throw new UnsupportedOperationException();
        Objects.requireNonNull(nyverdi, "Null-verdier kan ikke legges inn."); // Sjekker om den nye verdien er null
        indeksKontroll(indeks, false); // Sjekker om indeksen er gyldig.
        Node<T> p = finnNode(indeks); // Finner noden
        T gammelverdi = p.verdi;
        p.verdi = nyverdi; // Bytter med den nye verdien
        return gammelverdi;
    }

    @Override
    public boolean fjern(T verdi) { // Oppgave 6
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {    // Oppgave 6 - del 1
        //throw new UnsupportedOperationException();
        indeksKontroll(indeks,false);           // Sjekker om verdi er i listen, hvis ikke returneres false

        Node<T> p;                              // Oppretter Node "p"
        if (antall == 1) {                      // Hvis det kun er 1 node i listen...
            p = hode;                           // ...er "p" hode...
            hode = hale = null;                 // ...og hode og hale er begge "null"
        }
        else if (indeks == 0) {                 // Eller hvis indeks er "0" og vi må bytte hode...
            p = hode;                           // ... er "p" det nye hode...
            hode = hode.neste;                  // ...som settes på plassen etter det "gamle" hode...
            hode.forrige = null;                // ...som blir satt til "null" og fjernet
        }
        else if (indeks == antall-1) {          // Eller hvis indeks er lik halen, og vi må bytte hale...
            p = hale;                           // ... er "p" den nye halen...
            hale = hale.forrige;                // ... som settes på plassen før den "gamle" halen
            hale.neste = null;                  // ...som blir satt til "null" og fjernet
        }
        else {                                  // Ellers finnes indeks i listen...
            Node<T> q = finnNode(indeks);       // Node "q" settes til indeks via finnNode-metoden fra oppgave 3
            p = q;                        // "p" settes til indeks
            q.neste = q.neste.neste;            // Pekerne oppdateres slik at verdien på posisjon indeks fjernes...
            q.neste.forrige = q;                // ... ganske enkelt ved å hoppe over den
        }

        antall--;                               // antall minker
        endringer++;                            // endringer øker
        return p.verdi;                               // (verdien til) "p" returneres
    }

    @Override
    public void nullstill() {   // Oppgave 7
        //throw new UnsupportedOperationException();

        /*// 1. måte
        Node<T> p = hode;
        while(p != null) {
            hode = p.neste;
            hode.forrige = null;
            p = hode;
            if (hode == hale) {
                hode = null;
                hale = null;
                antall = 0;
                endringer++;
                break;
            }
        }*/

        // 2. måte
        for (int i = 0; i < antall-1; i++) {
            hode = hode.neste;
            fjern(i);
            endringer++;
            if (hode == hale) {
                fjern(i);
                antall = 0;
                break;
            }
        }
    }

    @Override
    public String toString() {  // Oppgave 2a - del 1
        //throw new UnsupportedOperationException();
        if (antall == 0) return "[]";  // hvis listen er tom, returner "[]"

        StringBuilder tegnstreng = new StringBuilder();         // Oppretter en StringBuilder "tegnstreng"
        Node<T> p = hode;                                       // Node "p" starter på hode(listen sin første verdi)
        tegnstreng.append('[');                                 // "tegnstreng" starter med en "["

        while (p != hale) {                                     // Så lenge "p" ikke er hale(listen sin siste verdi)...
            tegnstreng.append(p.verdi + ", ");                  // ... tar "tegnstreng" med en verdi og legger til et komma...
            p = p.neste;                                        // ... og hopper videre til neste verdi i listen
        }
        tegnstreng.append(p.verdi + "]");                       // Peker "p" på hale, legges verdien til i "tegnstreng" som avsluttes med en "]"
        return tegnstreng.toString();                           // "tegnstreng" konverteres til en String og returneres
    }

    public String omvendtString() { //Oppgave 2a - del 2
        //throw new UnsupportedOperationException();
        StringBuilder omvendtTegnstreng = new StringBuilder();         // Oppretter en StringBuilder "omvendtTegnstreng"
        omvendtTegnstreng.append('[');                                 // "omvendtTegnstreng" starter med en "["
        Node<T> p = hale;                                              // Node "p" starter på hale(listen sin siste verdi)

        while (p != null) {                                            // Så lenge "p" ikke er hode(listen sin første verdi)...
            if (p == hode) {
                omvendtTegnstreng.append(p.verdi);
            } else {
                omvendtTegnstreng.append(p.verdi).append(", ");                  // ... tar "omvendtTegnstreng" med en verdi og legger til et komma...
            }
            p = p.forrige;                                             // ... og hopper videre til forrige verdi i listen
        }
        omvendtTegnstreng.append("]");                                   // Peker "p" på hode, legges verdien til i "tegnstreng" som avsluttes med en "]"
        return omvendtTegnstreng.toString();                           // "omvendtTegnstreng" konverteres til en String og returneres
    }

    @Override
    public Iterator<T> iterator() { // Oppgave 8b
        //throw new UnsupportedOperationException();
        return new DobbeltLenketListeIterator();    // returnerer en instans av iteratorklassen
    }

    public Iterator<T> iterator(int indeks) {   // Oppgave 8d
        //throw new UnsupportedOperationException();
        indeksKontroll(indeks,false);                       // sjekker at indeksen er lovlig
        return new DobbeltLenketListeIterator(indeks);      // returnerer en instans av iteratorklassen med konstruktøren i 8c
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {    // Oppgave 8c
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {   // Oppgave 8a
            throw new UnsupportedOperationException();
        }
        /*
        @Override
        public void remove() {  // Oppgave 9 - Kommenter ut!!!
            throw new UnsupportedOperationException();
        }/*/

    } // class DobbeltLenketListeIterator   - Oppgave 10 - Kommenter ut!!!

    /*public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    } */

} // class DobbeltLenketListe


