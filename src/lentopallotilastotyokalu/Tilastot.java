package lentopallotilastotyokalu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/** Pit‰‰ yll‰ varsinaista tilastorekisteri‰, eli osaa lis‰t‰ ja poistaa tilaston, lukee ja kirjoittaa tilastot tiedostoon ja osaa etsi‰ ja lajitella
 * @author Reetu Inkila
 * @version Mar 8, 2021
 *
 */
public class Tilastot implements Iterable<Tilasto>{
    
    private String tiedostonNimi = "tilastot";
    private boolean muutettu = false;
    private final List<Tilasto> alkiot = new ArrayList<Tilasto>();
    
    
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
        muutettu = true;
    }
    
    
    /**
     * Poistaa kaikki tietyn tietyn pelaajan tilastot
     * @param tunnusNro tunnusnumero jonka tilastot poistetaan
     * @return montako poistettiin 
     * @example
     * <pre name="test">
     *  Tilastot tilastot = new Tilastot();
     *  Tilasto suorite21 = new Tilasto(); suorite21.taytaEsimerkkiTiedoilla(2);
     *  Tilasto suorite11 = new Tilasto(); suorite11.taytaEsimerkkiTiedoilla(1);
     *  Tilasto suorite22 = new Tilasto(); suorite22.taytaEsimerkkiTiedoilla(2); 
     *  Tilasto suorite12 = new Tilasto(); suorite12.taytaEsimerkkiTiedoilla(1); 
     *  Tilasto suorite23 = new Tilasto(); suorite23.taytaEsimerkkiTiedoilla(2); 
     *  tilastot.lisaa(suorite21);
     *  tilastot.lisaa(suorite11);
     *  tilastot.lisaa(suorite22);
     *  tilastot.lisaa(suorite12);
     *  tilastot.lisaa(suorite23);
     *  tilastot.poistaPelaajanTilastot(2) === 3;  tilastot.getLkm() === 2;
     *  tilastot.poistaPelaajanTilastot(3) === 0;  tilastot.getLkm() === 2;
     *  List<Tilasto> h = tilastot.annaTilastot(2);
     *  h.size() === 0; 
     *  h = tilastot.annaTilastot(1);
     *  h.get(0) === suorite11;
     *  h.get(1) === suorite12;
     * </pre>
     */
    public int poistaPelaajanTilastot(int tunnusNro) {
        int n = 0;
        for (Iterator<Tilasto> it = alkiot.iterator(); it.hasNext();) {
            Tilasto tilasto = it.next();
            if ( tilasto.getPelaajaId() == tunnusNro ) {
                it.remove();
                n++;
            }
        }
        if (n > 0) muutettu = true;
        return n;
    }

    
    
    /** 
     * Poistaa tilaston jolla on valittu tunnusnumero  
     * @example 
     * <pre name="test">  
     * Tilastot tilastot = new Tilastot(); 
     * Tilasto tilasto1 = new Tilasto(), tilasto2 = new Tilasto(), tilasto3 = new Tilasto(); 
     * tilasto1.taytaEsimerkkiTiedoilla(1); tilasto2.taytaEsimerkkiTiedoilla(1); tilasto3.taytaEsimerkkiTiedoilla(1); 
     * tilastot.lisaa(tilasto1); tilastot.lisaa(tilasto2); tilastot.lisaa(tilasto3); 
     * tilastot.getLkm() === 3;
     * tilastot.poistaViimeisin(); tilastot.getLkm() === 2;
     * tilastot.poistaViimeisin(); tilastot.getLkm() === 1; 
     * tilastot.poistaViimeisin(); tilastot.getLkm() === 0; 
     * </pre> 
     *  
     */ 
    public void poistaViimeisin() { 
        int viimeinen = getLkm() - 1;
        alkiot.remove(viimeinen);
        muutettu = true;
    } 
    
    
    /** poistaa tilastoista ottelun tilastot
     * @param paiva p‰iv‰ mink‰ mukaan tilastoja poistetaan
     * @param vastustaja mink‰ mukaan tilastoja poistetaan
     * @return poistettujen tilastojen m‰‰r‰
     * @example
     * <pre name="test">
     * Tilastot tilastot = new Tilastot(); 
     * Tilasto suorite1 = new Tilasto(1, "22.12.2121", "Puulaaki", "ƒss‰");
     * Tilasto suorite2 = new Tilasto(3, "22.12.2121","VaLePa", "Piste"); 
     * Tilasto suorite3 = new Tilasto(5, "23.12.2121","Puulaaki", "Piste");
     * Tilasto suorite4 = new Tilasto(2, "23.12.2121","Puulaaki", "Piste");
     * tilastot.lisaa(suorite1); tilastot.lisaa(suorite2); tilastot.lisaa(suorite3); tilastot.lisaa(suorite4);
     * tilastot.getLkm() === 4;
     * tilastot.poistaOttelunTilastot("22.12.2121", "Puulaaki"); tilastot.getLkm() === 3;
     * tilastot.poistaOttelunTilastot("22.12.2121", "VaLePa"); tilastot.getLkm() === 2; 
     * tilastot.poistaOttelunTilastot("23.12.2121", "Puulaaki"); tilastot.getLkm() === 0; 
     * </pre>
     */
    public int poistaOttelunTilastot(String paiva, String vastustaja) {
        int poistettuja = 0;
        for(Iterator<Tilasto> it = alkiot.iterator(); it.hasNext();) {
            Tilasto tilasto = it.next();
            if (tilasto.getKentta(1).contains(paiva) && tilasto.getKentta(2).contains(vastustaja)) {
                it.remove();
                poistettuja++;
            }
        }
        if (poistettuja > 0) muutettu = true;
        return poistettuja;
    }

    
    /**
     * Palauttaa lentopallotilastotyokalun tilastojen lukum‰‰r‰n
     * @return tilastojen lukum‰‰r‰
     */
    public int getLkm() {
        return alkiot.size(); 
    }
    
    
    /**
     * Lukee tilastot tiedostosta. 
     * @throws SailoException jos lukeminen ep‰onnistuu
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.File;
     * 
     *  Tilastot tilastot = new Tilastot();
     *  Tilasto tilasto1 = new Tilasto(), tilasto2 = new Tilasto(); 
     *  tilasto1.taytaEsimerkkiTiedoilla(5);
     *  tilasto2.taytaEsimerkkiTiedoilla(5);
     *  String testiTiedosto = "testitilastot";
     *  File ftied = new File(testiTiedosto+".dat");
     *  ftied.delete();
     *  tilastot.lueTiedostosta(testiTiedosto); #THROWS SailoException
     *  tilastot.lisaa(tilasto1);
     *  tilastot.lisaa(tilasto2);
     *  tilastot.tallenna();
     *  tilastot = new Tilastot();            // Poistetaan vanhat luomalla uusi
     *  tilastot.lueTiedostosta(testiTiedosto);  // johon ladataan tiedot tiedostosta.
     *  Iterator<Tilasto> i = tilastot.iterator();
     *  i.next().toString() === tilasto1.toString();
     *  i.next().toString() === tilasto2.toString();
     *  i.hasNext() === false;
     *  tilastot.lisaa(tilasto2);
     *  tilastot.tallenna();
     *  ftied.delete() === true;
     * </pre>
     */
    public void lueTiedostosta() throws SailoException {
        
        try ( BufferedReader fi = new BufferedReader(new FileReader(getTiedostonNimi())) ) {
            String rivi = "";
            while ( (rivi = fi.readLine()) != null ) {
                rivi = rivi.trim();
                if ( rivi.charAt(0) == ';' ) continue;
                Tilasto tilasto = new Tilasto();
                tilasto.parse(rivi);
                lisaa(tilasto);
            }
            muutettu = false;
        } catch ( FileNotFoundException e ) {
            throw new SailoException("Tiedosto " + getTiedostonNimi() + " ei aukea");
        } catch ( IOException e ) {
            throw new SailoException("Ongelmia tiedoston kanssa: " + e.getMessage());
        }
    }
    
    
    /**
     * Luetaan annetun nimisest‰ tiedostosta
     * @param tiedosto josta luetaan
     * @throws SailoException jos tulee poikkeus
     */
    public void lueTiedostosta(String tiedosto) throws SailoException {
        setTiedostonNimi(tiedosto);
        lueTiedostosta();
    }

    
    /**
     * Tallentaa joukkueiston tiedostoon.
     * @throws SailoException jos talletus ep‰onnistuu
     */
    public void tallenna() throws SailoException {
        if ( !muutettu ) return;
        
        File ftied = new File(getTiedostonNimi());

        try ( PrintWriter fo = new PrintWriter(new FileWriter(ftied.getCanonicalPath())) ) {
            for (Tilasto tilasto: this) {
                fo.println(tilasto.toString());
            }
        } catch ( FileNotFoundException ex ) {
            throw new SailoException("Tiedosto " + ftied.getName() + " ei aukea");
        } catch ( IOException ex ) {
            throw new SailoException("Tiedoston " + ftied.getName() + " kirjoittamisessa ongelmia");
        }
        muutettu = false;
    }
    

    /**
     * Asettaa tiedoston perusnimen ilman tarkenninta
     * @param nimi tallennustiedoston perusnimi
     */
    public void setTiedostonNimi(String nimi) {
        tiedostonNimi = nimi;
    }


    /**
     * Palauttaa tiedoston nimen, jota k‰ytet‰‰n tallennukseen
     * @return tallennustiedoston nimi
     */
    public String getTiedostonNimi() {
        return tiedostonNimi + ".dat";
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
    @Override
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
