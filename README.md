# Obligatorisk oppgave 3 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende student:
* Adrian Karlsen, s336133, s336133@oslomet.no


# Oppgavebeskrivelse

### Oppgave 1

I oppgave 1 så gikk jeg frem ved å kopiere koden fra programkode 5.2.3 a) og endret slik at forelder får riktig verdi i 
hver node. I teksten sto det at forelder skal være null i rotnoden, derfor ble "null" satt som parameter.

Update: Måtte endre verdien på p så forelder får riktig verdi når ny node opprettes. 

### Oppgave 2

- Oppretter en ny node og setter den lik rot. Lager også en hjelpe variabel for å telle antall verdier. 
- Sjekker så om noden ikke er null. Hvis ikke så sammenlignes to verdier. Hvis verdien er mindre en null, så oppdateres 
venstre barn. 
- Så kjøres en else som først sjekker antall like verider er funnet, så øker antall. 
- Så oppdateres høyre barn

### Oppgave 3

#### førstePostorden

- Laget metoden førsteporden
- Skjører så en while løkke som sjekker at så lenge p ikke er null, bare satt som true
- Sjekker om p.venstre ikke er null, hvis det ikke er det så settes p som venstrebarn. 
- Sjekker om p.høyre ikke er null, hvis det ikke er det så settes p som høyrebarn
- Så når første postorden er funnet, returnes p. Hvis det ikke gås gjennom noen av de andre if setningene, så er den allerede på først postorden.

#### nestePostrden

- iniatiliserer forelder som p forelder. 
- Sjekker så om forelder er null, hvis ja så er det ingen neste postorden.
- Lager så en if setning som sjekker om høyre forelder er lik p ELLER forelder høyre er lik null, hvis sant så returneres forelder
- Hvis ikke så kalles vi på metoden  førstePostreden med forelder.høyre som parameter

### Oppgave 4

#### postorden

- iniatiliserer p som rot
- inatiliserer første som bruker metoden førstePostorden(p) for å finne første node
- Utfører så oppgave for å sette verdi
- Lager så en while løkke som loopewr gjennom treet så lenge forste.forelder ikke er null
- Oppdaterer så første med neste verdi i posten med meotden nestePostorden(neste) for så å utføre oppggave. 

#### postOrdenRecursive

- Sjekker først om p er lik null, hvis ja så kjøres en return
- Kaller så rekursivt for p sitt venstrebarn med postordenRecursive
- Kaller så rekursvit for p sitt høyrebarn. 
- Til slutt så utføres oppgaven med p.verdi

