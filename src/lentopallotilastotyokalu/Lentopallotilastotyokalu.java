/**
 * 
 */
package lentopallotilastotyokalu;

import java.io.File;
import java.util.Collection;
import java.util.List;

/**
 * -huolehtii joukkueet, Pelaajat ja Tilastot luokkien v‰lisest‰ yhteistyˆst‰ ja v‰litt‰‰ n‰it‰ tietoja  pyydett‰ess‰                            
 * -lukee ja kirjoittaa joukkueet, pelaajat ja tilastot tiedostoon pyyt‰m‰ll‰ apua avustajiltaan                    
 * @author Reetu Inkil‰
 * @version Feb 23, 2021
 *
 */
public class Lentopallotilastotyokalu {

    private Joukkueet joukkueet = new Joukkueet();
    private Pelaajat pelaajat = new Pelaajat();
    private Tilastot tilastot = new Tilastot();
       
    
    /**
     * Poistaa pelaajista ja tilastoista pelaajan tiedot 
     * @param poistettava Pelaaja joka poistetaan
     * @return montako pelaajaa poistettiin
     */
    public int poista(Pelaaja poistettava) {
        if ( poistettava == null ) return 0;
        int ret = pelaajat.poista(poistettava.getTunnusNro()); 
        tilastot.poistaPelaajanTilastot(poistettava.getTunnusNro()); 
        return ret; 
    }

    
    
    /**
     * Palautaa joukkueiden m‰‰r‰n
     * @return joukkuem‰‰r‰
     */
    public int getJoukkueita() {
        return joukkueet.getLkm();
    }
    
    
    /**
     * Palautaa pelaajien m‰‰r‰n
     * @return pelaaja
     */
    public int getPelaajia() {
        return pelaajat.getLkm();
    }
    
    
    /**
     * Palautaa tilastojen m‰‰r‰n
     * @return tilastoja
     */
    public int getTilastoja() {
        return tilastot.getLkm();
    }
    
    
    /**
     * Lis‰‰ uuden joukkueen tietorakenteeseen.  Ottaa joukkueen omistukseensa.
     * @param joukkue lis‰t‰‰v‰n joukkueen viite.  Huom tietorakenne muuttuu omistajaksi
     * @throws SailoException jos tietorakenne on jo t‰ynn‰
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * Joukkueet joukkueet = new Joukkueet();
     * Joukkue tiimi1 = new Joukkue(), tiimi2 = new Joukkue();
     * joukkueet.getLkm() === 0;
     * joukkueet.lisaa(tiimi1); joukkueet.getLkm() === 1;
     * joukkueet.lisaa(tiimi2); joukkueet.getLkm() === 2;
     * joukkueet.lisaa(tiimi1); joukkueet.getLkm() === 3;
     * joukkueet.anna(0) === tiimi1;
     * joukkueet.anna(1) === tiimi2;
     * joukkueet.anna(2) === tiimi1;
     * joukkueet.anna(1) == tiimi1 === false;
     * joukkueet.anna(1) == tiimi2 === true;
     * joukkueet.anna(3) === tiimi1; #THROWS IndexOutOfBoundsException 
     * joukkueet.lisaa(tiimi1); joukkueet.getLkm() === 4;
     * joukkueet.lisaa(tiimi1); joukkueet.getLkm() === 5;
     * joukkueet.lisaa(tiimi1); joukkueet.getLkm() === 6;
     * joukkueet.lisaa(tiimi1); joukkueet.getLkm() === 7;
     * joukkueet.lisaa(tiimi1); joukkueet.getLkm() === 8;
     * joukkueet.lisaa(tiimi1); joukkueet.getLkm() === 9;
     * joukkueet.lisaa(tiimi1); joukkueet.getLkm() === 10;
     * </pre>
     */
    public void lisaaJoukkue(Joukkue joukkue) throws SailoException {
        joukkueet.lisaa(joukkue);
    }
    
    
    /**
     * Lis‰‰ uuden pelaajan tietorakenteeseen.  Ottaa pelaajan omistukseensa.
     * @param pelaaja lis‰t‰‰v‰n pelaajan viite.  Huom tietorakenne muuttuu omistajaksi
     * @throws SailoException jos tietorakenne on jo t‰ynn‰
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * Pelaajat pelaajat = new Pelaajat();
     * Pelaaja pelimies1 = new Pelaaja(), pelimies2 = new Pelaaja();
     * pelaajat.getLkm() === 0;
     * pelaajat.lisaa(pelimies1); pelaajat.getLkm() === 1;
     * pelaajat.lisaa(pelimies2); pelaajat.getLkm() === 2;
     * pelaajat.lisaa(pelimies1); pelaajat.getLkm() === 3;
     * pelaajat.anna(0) === pelimies1;
     * pelaajat.anna(1) === pelimies2;
     * pelaajat.anna(2) === pelimies1;
     * pelaajat.anna(1) == pelimies1 === false;
     * pelaajat.anna(1) == pelimies2 === true;
     * pelaajat.anna(3) === pelimies1; #THROWS IndexOutOfBoundsException 
     * </pre>
     */
    public void lisaaPelaaja(Pelaaja pelaaja) throws SailoException {
        pelaajat.lisaa(pelaaja);
    }
    
    
    /**
     * Lis‰‰ uuden tilaston tietorakenteeseen.
     * @param tilasto lis‰t‰‰v‰n tilastoon viite
     * @throws SailoException jos tietorakenne on jo t‰ynn‰
     * @example
     * <pre name="test">
     *  #import java.util.*;
     *  
     *  #THROWS SailoException 
     *  Tilastot tilastot = new Tilastot();
     *  Tilasto suorite1 = new Tilasto(5), suorite2 = new Tilasto(8);
     *  tilastot.getLkm() === 0;
     *  tilastot.lisaa(suorite1); tilastot.getLkm() === 1;
     *  tilastot.lisaa(suorite2); tilastot.getLkm() === 2;
     *  tilastot.lisaa(suorite1); tilastot.getLkm() === 3;
     *  
     *  List<Tilasto> loytyneet;
     *  loytyneet = tilastot.annaTilastot(0);
     *  loytyneet.size() === 0;
     *  loytyneet = tilastot.annaTilastot(5);
     *  loytyneet.size() === 2; 
     *  loytyneet.get(0) == suorite1 === true;
     *  loytyneet.get(1) == suorite1 === true;
     *  loytyneet = tilastot.annaTilastot(8);
     *  loytyneet.size() === 1; 
     *  loytyneet.get(0) == suorite2 === true;
     * </pre>
     */
    public void lisaaTilasto(Tilasto tilasto) throws SailoException {
        tilastot.lisaa(tilasto);
    }
    
    
    /**
     * Palauttaa i:n joukkueen
     * @param i monesko joukkue palautetaan
     * @return viite i:teen j‰seneen
     * @throws IndexOutOfBoundsException jos i v‰‰rin
     */
    public Joukkue annaJoukkue(int i) throws IndexOutOfBoundsException {
        return joukkueet.anna(i);
    }
    
    
    /**
     * Palauttaa i:n pelaajan
     * @param i monesko pelaaja palautetaan
     * @return viite i:teen pelaajaan
     * @throws IndexOutOfBoundsException jos i v‰‰rin
     */
    public Pelaaja annaPelaaja(int i) throws IndexOutOfBoundsException {
        return pelaajat.anna(i);
    }
    
    
    /**
     * Palauttaa id:n omaava pelaajan
     * @param id jonka omaava pelaaja palautetaan
     * @return viite id:n pelaajaan
     */
    public Pelaaja annaIdPelaaja(int id){
        return pelaajat.annaId(id);
    }
    
    
    /** Palautetaan pelaajan tilastot
     * @param pelaaja pelaaja jonka tilastoja haetaan
     * @return pelaajan tilastot listana
     */
    public List<Tilasto> annaTilastot(Pelaaja pelaaja){
        return tilastot.annaTilastot(pelaaja.getTunnusNro());       
    }
    
    
    /**
     * Lukee Joukkueiden ja pelaajien tiedot tiedostosta
     * @param nimi jota k‰yte‰‰n lukemisessa
     * @throws SailoException jos lukeminen ep‰onnistuu
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.*;
     * #import java.util.*;
     * #import lentopallotilastotyokalu.Pelaaja;
     * #import lentopallotilastotyokalu.Tilasto;
     * #import lentopallotilastotyokalu.Joukkue;
     * 
     *  String hakemisto = "testityokalu";
     *  File dir = new File(hakemisto);
     *  File fjtied  = new File(hakemisto+"/joukkueet.dat");
     *  File fptied = new File(hakemisto+"/pelaajat.dat");
     *  File fttied = new File(hakemisto+"/tilastot.dat");
     *  dir.mkdir();  
     *  fjtied.delete();
     *  fptied.delete();
     *  fttied.delete();
     *  Lentopallotilastotyokalu tyokalu = new Lentopallotilastotyokalu();
     *  tyokalu.setTiedosto(hakemisto); // nimi annettava koska uusi poisti sen
     *  Joukkue tiimi = new Joukkue(); tiimi.taytaPuulaakiTiedoilla();
     *  Pelaaja peluri = new Pelaaja(); peluri.rekisteroi();
     *  Tilasto tilasto = new Tilasto(); tilasto.taytaEsimerkkiTiedoilla(peluri.getTunnusNro());
     *  tyokalu.lisaaJoukkue(tiimi); tyokalu.lisaaPelaaja(peluri); tyokalu.lisaaTilasto(tilasto);
     *  tyokalu.tallenna(); 
     *  tyokalu = new Lentopallotilastotyokalu();
     *  tyokalu.setTiedosto(hakemisto); // nimi annettava koska uusi poisti sen
     *  tyokalu.lueTiedostosta(hakemisto);
     *  tyokalu.getJoukkueita() === 1; 
     *  tyokalu.getPelaajia() === 1; 
     *  tyokalu.getTilastoja() === 1;
     *  List<Tilasto> loytyneet = tyokalu.annaTilastot(peluri);
     *  Iterator<Tilasto> ih = loytyneet.iterator();
     *  ih.next().toString() === tilasto.toString();
     *  ih.hasNext() === false;
     *  tyokalu.tallenna();
     *  fjtied.delete()  === true;
     *  fptied.delete() === true;
     *  fttied.delete() === true;
     *  dir.delete() === true;
     * </pre>
     */
    public void lueTiedostosta(String nimi) throws SailoException {
        joukkueet = new Joukkueet();
        pelaajat = new Pelaajat();
        tilastot = new Tilastot();
        setTiedosto(nimi);
        joukkueet.lueTiedostosta();
        pelaajat.lueTiedostosta();
        tilastot.lueTiedostosta();
    }
    
    
    /**
     * Asettaa tiedostojen perusnimet
     * @param nimi uusi nimi
     */
    public void setTiedosto(String nimi) {
        File dir = new File(nimi);
        dir.mkdirs();
        String hakemistonNimi = "";
        if ( !nimi.isEmpty() ) hakemistonNimi = nimi +"/";
        joukkueet.setTiedostonNimi(hakemistonNimi + "joukkueet");
        pelaajat.setTiedostonNimi(hakemistonNimi + "pelaajat");
        tilastot.setTiedostonNimi(hakemistonNimi + "tilastot");
    }

    
    
    /**
     * Tallettaa joukkueiden nimet, pelaajat ja tilastot tiedostoon
     * @throws SailoException jos tallettamisessa ongelmia
     */
    public void tallenna() throws SailoException {
        joukkueet.tallenna();
        pelaajat.tallenna();
        tilastot.tallenna();
    }
    
    
    /**
     * Asetetaan pelaajien muokattu parametri arvoon true 
     */
    public void pelaajiaMuokattu() {
        pelaajat.setMuutettu(true);
    }
    
    
    /**
     * Poistaa viimeisimp‰n‰ talletetun tilaston
     */
    public void poistaViimeisinTilasto() {
        tilastot.poistaViimeisin();
    }
    
    
    /** 
     * Palauttaa "taulukossa" hakuehtoon vastaavien pelaajien viitteet 
     * @param hakuehto hakuehto  
     * @param k etsitt‰v‰n kent‰n indeksi  
     * @return tietorakenteen lˆytyneist‰ pelaajista 
     * @throws SailoException Jos jotakin menee v‰‰rin
     * @example 
     * <pre name="test">
     *   #THROWS CloneNotSupportedException, SailoException
     *   Lentopallotilastotyokalu tyokalu = new Lentopallotilastotyokalu();
     *   Pelaaja pelaaja1 = new Pelaaja(), pelaaja2 = new Pelaaja();
     *   pelaaja1.parse("1   |1     |2          |Eemil Tervaportti |Passari        | ");
     *   pelaaja2.parse("5   |2     |26         |Pete Pelaaja      |Hakkuri        | ");
     *   tyokalu.lisaaPelaaja(pelaaja1); tyokalu.lisaaPelaaja(pelaaja2);
     *   Collection<Pelaaja> loytyneet = tyokalu.etsi("*Eemil*",3);
     *   loytyneet.size() === 1;
     *   Iterator<Pelaaja> it = loytyneet.iterator();
     *   it.next() == pelaaja1 === true; 
     * </pre>
     */ 
    public Collection<Pelaaja> etsi(String hakuehto, int k) throws SailoException { 
        return pelaajat.etsi(hakuehto, k); 
    } 

    
    
    /**
     * @param args ei k‰ytˆss‰
     */
    public static void main(String[] args) {
        Lentopallotilastotyokalu lentopallotilastotyokalu = new Lentopallotilastotyokalu();

        try {
            Joukkue tiimi1 = new Joukkue(), tiimi2 = new Joukkue();
            tiimi1.rekisteroi();
            tiimi1.taytaPuulaakiTiedoilla();
            tiimi2.rekisteroi();
            tiimi2.taytaPuulaakiTiedoilla();

            lentopallotilastotyokalu.lisaaJoukkue(tiimi1);
            lentopallotilastotyokalu.lisaaJoukkue(tiimi2);

            System.out.println("============= Lentopallotilastotyokalun testi =================");

            for (int i = 0; i < lentopallotilastotyokalu.getJoukkueita(); i++) {
                Joukkue joukkue = lentopallotilastotyokalu.annaJoukkue(i);
                System.out.println("Joukkue paikassa: " + i);
                joukkue.tulosta(System.out);
            }

        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
