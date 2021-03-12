package lentopallotilastotyokalu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/** Pit‰‰ yll‰ varsinaista tilastorekisteri‰, eli osaa lis‰t‰ ja poistaa tilaston, lukee ja kirjoittaa tilastot tiedostoon ja osaa etsi‰ ja lajitella
 * @author Reetu Inkila
 * @version Mar 8, 2021
 *
 */
public class Tilastot {
    
    private String tiedostonNimi = "";
    
    /** Taulukko tilastoista */
    private final ArrayList<Tilasto> alkiot = new ArrayList<Tilasto>();
    
    /**
     * Tilastojen alustaminen
     */
    public Tilastot() {
        // toistaiseksi ei tarvitse tehd‰ mit‰‰n
    }

    /**
     * Lis‰‰ uuden tilaston tietorakenteeseen.
     * @param tilasto lis‰tt‰v‰ tilasto.  Huom tietorakenne muuttuu omistajaksi
     */
    public void lisaa(Tilasto tilasto) {
        alkiot.add(tilasto);   
    }
    
    /**
     * Lukee tilastot tiedostosta.  
     * TODO Kesken.
     * @param hakemisto tiedoston hakemisto
     * @throws SailoException jos lukeminen ep‰onnistuu
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        tiedostonNimi = hakemisto + ".har";
        throw new SailoException("Ei osata viel‰ lukea tiedostoa " + tiedostonNimi);
    }
    
    /**
     * Tallentaa tilastot tiedostoon.  
     * TODO Kesken.
     * @throws SailoException jos talletus ep‰onnistuu
     */
    public void talleta() throws SailoException {
        throw new SailoException("Ei osata viel‰ tallettaa tiedostoa " + tiedostonNimi);
    }

    /**
     * Palauttaa lentopallotilastotyokalun tilastojen lukum‰‰r‰n
     * @return tilastojen lukum‰‰r‰
     */
    public int getLkm() {
        return alkiot.size();
    }
    
    /**
     * Iteraattori kaikkien tilastojen l‰pik‰ymiseen
     * @return tilastoiteraattori
     * 
     * @example
     * <pre name="test">
     * #PACKAGEIMPORT
     * #import java.util.*;
     * 
     *  Tilastot tilastoidut = new Tilastot();
     *  Tilasto tilasto21 = new Tilasto(2); tilastoidut.lisaa(tilasto21);
     *  Tilasto tilasto11 = new Tilasto(1); tilastoidut.lisaa(tilasto11);
     *  Tilasto tilasto22 = new Tilasto(2); tilastoidut.lisaa(tilasto22);
     *  Tilasto tilasto12 = new Tilasto(1); tilastoidut.lisaa(tilasto12);
     *  Tilasto tilasto23 = new Tilasto(2); tilastoidut.lisaa(tilasto23);
     * 
     *  Iterator<Tilasto> i2=tilastoidut.iterator();
     *  i2.next() === tilasto21;
     *  i2.next() === tilasto11;
     *  i2.next() === tilasto22;
     *  i2.next() === tilasto12;
     *  i2.next() === tilasto23;
     *  i2.next() === tilasto12;  #THROWS NoSuchElementException  
     *  
     * </pre>
     */
    public Iterator<Tilasto> iterator() {
        return alkiot.iterator();
    }

    /**
     * Haetaan kaikki pelaajan tilastot
     * @param id pelaajan id numero jonka tilastoja haetaan
     * @return tietorakenne jossa viiteet lˆydetteyihin harrastuksiin
     * @example
     * <pre name="test">
     * #import java.util.*;
     * 
     *  Tilastot tilastoidut = new Tilastot();
     *  Tilasto tilasto21 = new Tilasto(2); tilastoidut.lisaa(tilasto21);
     *  Tilasto tilasto11 = new Tilasto(1); tilastoidut.lisaa(tilasto11);
     *  Tilasto tilasto22 = new Tilasto(2); tilastoidut.lisaa(tilasto22);
     *  Tilasto tilasto12 = new Tilasto(1); tilastoidut.lisaa(tilasto12);
     *  Tilasto tilasto23 = new Tilasto(2); tilastoidut.lisaa(tilasto23);
     *  Tilasto tilasto51 = new Tilasto(5); tilastoidut.lisaa(tilasto51);
     *  
     *  List<Tilasto> loytyneet;
     *  loytyneet = tilastoidut.annaTilastot(3);
     *  loytyneet.size() === 0; 
     *  loytyneet = tilastoidut.annaTilastot(1);
     *  loytyneet.size() === 2; 
     *  loytyneet.get(0) == tilasto11 === true;
     *  loytyneet.get(1) == tilasto12 === true;
     *  loytyneet = tilastoidut.annaTilastot(5);
     *  loytyneet.size() === 1; 
     *  loytyneet.get(0) == tilasto51 === true;
     * </pre> 
     */
    public List<Tilasto> annaTilastot(int id) {
        List<Tilasto> loydetyt = new ArrayList<Tilasto>();
        for (Tilasto til : alkiot)
            if (til.getPelaajaId() == id) loydetyt.add(til);
        return loydetyt;
    }

 


    /** Testi p‰‰ohjelma tilastot luokalle
     * @param args ei k‰ytˆss‰
     */
    public static void main(String[] args) {
    Tilastot tilastot = new Tilastot();
    Tilasto tilasto1 = new Tilasto();
    tilasto1.taytaEsimerkkiTiedoilla(2);
    tilastot.lisaa(tilasto1);
    
    System.out.println("============= Tilastot testi =================");

    List<Tilasto> tilastot2 = tilastot.annaTilastot(2);

    for (Tilasto til : tilastot2) {
        System.out.print(til.getPelaajaId() + " ");
        til.tulosta(System.out);
        }
    }


}
