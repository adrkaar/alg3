package no.oslomet.cs.algdat.Oblig3;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.StringJoiner;

public class SBinTre<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public SBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }

    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

        Node<T> p = rot, q = null;               // p starter i roten
        int cmp = 0;                             // hjelpevariabel

        while (p != null)       // fortsetter til p er ute av treet
        {
            q = p;                                 // q er forelder til p
            cmp = comp.compare(verdi,p.verdi);     // bruker komparatoren
            p = cmp < 0 ? p.venstre : p.høyre;     // flytter p
        }

        // p er nå null, dvs. ute av treet, q er den siste vi passerte

        p = new Node<>(verdi, q);                   // oppretter en ny node. Endrer så referansen forelder får riktig verdi

        if (q == null) {
            rot = p;                // p blir rotnode
        }
        else if (cmp < 0) {
            q.venstre = p;          // venstre barn til q
        }
        else {
            q.høyre = p;            // høyre barn til q
        }

        antall++;                                // én verdi mer i treet
        return true;                             // vellykket innlegging
    }

    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int fjernAlle(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int antall(T verdi) {
        Node<T> p = rot; //Init p

        int ant = 0; //Helpevariabel for antall

        while(p != null) {
            int cmp = comp.compare(verdi, p.verdi); //Sammenligner to verdier
            if(cmp < 0) p = p.venstre; //Oppdaterer venstre barn
            else {
                if(cmp == 0) ant++; //Hvis antall like verdier er funnet, så øker antall
                p = p.høyre;        // oppdaterer høyre barn
            }
        }
        return ant;
    }

    public void nullstill() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    private static <T> Node<T> førstePostorden(Node<T> p) {
        // throw new UnsupportedOperationException("Ikke kodet ennå!");
        while(true) {
            if (p.venstre != null) {
                p = p.venstre;             //Venstrebarn til p
            } else if (p.høyre != null) {
                p = p.høyre;              // Høyre barn til p
            } else {
                return p;
            }
        }
    }

    private static <T> Node<T> nestePostorden(Node<T> p) {
        // throw new UnsupportedOperationException("Ikke kodet ennå!");
        Node<T> forelder = p.forelder;              // Init forelder som p.forelder
        if(forelder == null) {                      //Dersom forelderen null, så er det ingen nesteporden
            return null;
        }
        if(forelder.høyre == p || forelder.høyre == null) return forelder;  //Returnerer forelder om høyrebarn er verdien p
        else return førstePostorden(forelder.høyre);                        // Hvis ikke kalles på førsteorden

    }

    public void postorden(Oppgave<? super T> oppgave) {
        // throw new UnsupportedOperationException("Ikke kodet ennå!");
        Node<T> p = rot;                                  //Init p som rot
        Node<T> forste = førstePostorden(p);              //Finner første node av metoden
        oppgave.utførOppgave(forste.verdi);
        while(forste.forelder != null) {                  //While løkke som looper gjennom treet
            forste = nestePostorden(forste);             // Opdaterer neste verdi i postorden
            oppgave.utførOppgave(Objects.requireNonNull(forste).verdi);
        }
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        if(!tom()) postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        if(p == null) return;        //Returnerer hvis p er null

        postordenRecursive(p.venstre, oppgave);   //Kaller rekursivt for p sitt venstrbarn
        postordenRecursive(p.høyre,oppgave);      // Kaller rekursivt for p sitt høyrebarn
        oppgave.utførOppgave(p.verdi);           // Kjører oppgaven
    }

    public ArrayList<T> serialize() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    static <K> SBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }


} // ObligSBinTre
