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

    public DobbeltLenketListe() {
        //throw new UnsupportedOperationException();
    }

    public DobbeltLenketListe(T[] a) {
        //throw new UnsupportedOperationException();
        Objects.requireNonNull(a, "Tabellen a er null!");

        if (antall == 0) {
            hode = hale = null;
        } else if (antall == 1) {
            hale = hode;
            hode.forrige = null;
            hode.neste = null;
        } else {
            Node head = new Node(0);
            hale.neste = new Node(1);
            //head.neste.neste = new Node(1);
        }
    }

    private Node<T> finnNode(int index) {
        Node<T> p = hode;
        if (index < (antall / 2)) {
            for (int i = 0; i < index; i++) {
                p = p.neste;
            }
        }
        return p;
    }

    public Liste<T> subliste(int fra, int til) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int antall() {
        //throw new UnsupportedOperationException();
        /*for (int i = 0; i < liste.length; i++) {
            antall++;
        }*/
        return antall;
    }

    @Override
    public boolean tom() {
        //throw new UnsupportedOperationException();
        /*if (liste.length < 1) {
            return false;
        }*/
        return true;
    }

    @Override
    public boolean leggInn(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void leggInn(int indeks, T verdi) { // Koden er inspirert av Kompendiet, promgramkode 3.3.2 f
        //throw new UnsupportedOperationException();
        if (indeks == 0) { // Verdi legges først hvis indeks er null
            hode = new Node<>(verdi, null, hode.neste);
            if (antall == 0) { // Hvis listen er tom
                hale = hode;
            }
        } else if (indeks == antall) { // Hvis indeksen er bakerst
            hale = hale.neste = new Node<>(verdi, hale.forrige, null);
        }
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T hent(int indeks) {
        //throw new UnsupportedOperationException();
        indeksKontroll(indeks, false);
        return finnNode(indeks).verdi;
    }

    @Override
    public int indeksTil(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
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
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
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

        private DobbeltLenketListeIterator(int indeks) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe


