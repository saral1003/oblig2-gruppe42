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

    public DobbeltLenketListe() {   // ???
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
        Node<T> p = hode;
        if (index < (antall/2)) {
            for (int i = 0; i < index; i++) {
                p = p.neste;
            }
        } else {
            p = hale;
            for (int i = antall; i > index; i--) {
                p = p.forrige;
            }
        }
        return p;
    }

    public Liste<T> subliste(int fra, int til) {    // Oppgave 3b
        throw new UnsupportedOperationException();
        //fratilKontroll()
        //return subliste;
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
        } else {
            return false;
        }
    }

    @Override
    public boolean leggInn(T verdi) {   // Oppgave 2b
        //throw new UnsupportedOperationException();
        Node<T> p = new Node<>(verdi);                                      // Oppretter ny Node

        Objects.requireNonNull(verdi, "Null-verdier er ikke tillatt");     // Null-verdier er ikke tillatt

        if (hode == null && hale == null && antall == 0){                        // Tilfelle 1(listen er tom på forhånd)
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
        indeksKontroll(indeks, true);
        Objects.requireNonNull(verdi, "Ikke gyldig tall.");
        if (indeks == 0) { // Verdi legges først hvis indeks er null
            hode = new Node<>(verdi, null, null);
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
    public boolean inneholder(T verdi) { // Oppgave 4
        throw new UnsupportedOperationException();
    }

    @Override
    public T hent(int indeks) { // Oppgave 3a
        //throw new UnsupportedOperationException();
        indeksKontroll(indeks, false); // Sjekker om indeksen er gyldig
        return finnNode(indeks).verdi; // Finner verdien til indeksen
    }

    @Override
    public int indeksTil(T verdi) { // Oppgave 4
        throw new UnsupportedOperationException();
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
    public T fjern(int indeks) {    // Oppgave 6
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {   // Oppgave 7
        throw new UnsupportedOperationException();
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
        Node<T> p = hale;                                              // Node "p" starter på hale(listen sin siste verdi)
        omvendtTegnstreng.append('[');                                 // "omvendtTegnstreng" starter med en "["

        while (p != hode) {                                            // Så lenge "p" ikke er hode(listen sin første verdi)...
            omvendtTegnstreng.append(p.verdi + ", ");                  // ... tar "omvendtTegnstreng" med en verdi og legger til et komma...
            p = p.forrige;                                             // ... og hopper videre til forrige verdi i listen
        }
        omvendtTegnstreng.append(p.verdi + "]");                       // Peker "p" på hode, legges verdien til i "tegnstreng" som avsluttes med en "]"
        return omvendtTegnstreng.toString();                           // "omvendtTegnstreng" konverteres til en String og returneres
    }

    @Override
    public Iterator<T> iterator() { // Oppgave 8b
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {   // Oppgave 8d
        throw new UnsupportedOperationException();
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


