/**
 * 
 */
package lentopallotilastotyokalu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * @author Reetu Inkilä
 * @version Feb 23, 2021
 *
 */
public class Pelaajat implements Iterable<Pelaaja> {

    private static final int Max_PELAAJIA = 20;
    private int lkm = 0;
    private String tiedostonNimi = "pelaajat";
    private Pelaaja[] alkiot = new Pelaaja[Max_PELAAJIA];
    private boolean muutettu = false;
    
    
    /**
     * Tyhjä muodostaja
     */
    public Pelaajat() {
        // Atribuutit alustettu
    }
    
    
    /**
     * Lisää uuden pelaajan tietorakenteeseen.  Ottaa pelaajan omistukseensa.
     * @param pelaaja lisättävän pelaajan viite.  Huom tietorakenne muuttuu omistajaksi
     * @throws SailoException jos tietorakenne on jo täynnä
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
     * </pre>
     */
    public void lisaa(Pelaaja pelaaja) throws SailoException {
        if (lkm >= alkiot.length) alkiot = Arrays.copyOf(alkiot, lkm + 20);
        alkiot[lkm] = pelaaja;
        lkm++;
        muutettu = true;
    }
    
    
    /**
     * Palauttaa viitteen i:teen pelaajaan.
     * @param i monennenko pelaajan viite halutaan
     * @return viite pelaajaan, jonka indeksi on i
     * @throws IndexOutOfBoundsException jos i ei ole sallitulla alueella  
     */
    public Pelaaja anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || lkm <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return alkiot[i];
    }
    
    /**
     * Palauttaa viitteen id:n omaavaan pelaajaan.
     * @param id minkä pelaajan viite halutaan
     * @return viite pelaajaan, jonka id on i 
     */
    public Pelaaja annaId(int id) {
        for (Pelaaja pelaaja: this) {
            if (pelaaja.getTunnusNro() == id) return pelaaja;
        }
        return null;       
    }
    
    
    /**
     * Palauttaa tilastotyökalun pelaajien lukumäärän
     * @return jäsenten lukumäärä
     */
    public int getLkm() {
        return lkm;
    }
    
    
    /**Asetetaan muutettu haluttuun arvoon
     * @param onkoMuutettu boolean arvo onko muokattu
     */
    public void setMuutettu(boolean onkoMuutettu) {
        muutettu = onkoMuutettu;
    }
    
    
    /**
     * Lukee pelaajat tiedostosta. 
     * @throws SailoException jos lukeminen epäonnistuu
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.File;
     * 
     *  Pelaajat pelaajat = new Pelaajat();
     *  Pelaaja pelaaja1 = new Pelaaja(), pelaaja2 = new Pelaaja();
     *  pelaaja1.rekisteroi();
     *  pelaaja2.rekisteroi(); 
     *  pelaaja1.taytaEsimerkkiTiedoilla();
     *  pelaaja2.taytaEsimerkkiTiedoilla();
     *  String testiTiedosto = "testipelaajat";
     *  File ftied = new File(testiTiedosto+".dat");
     *  ftied.delete();
     *  pelaajat.lueTiedostosta(testiTiedosto); #THROWS SailoException
     *  pelaajat.lisaa(pelaaja1);
     *  pelaajat.lisaa(pelaaja2);
     *  pelaajat.tallenna();
     *  pelaajat = new Pelaajat();            // Poistetaan vanhat luomalla uusi
     *  pelaajat.lueTiedostosta(testiTiedosto);  // johon ladataan tiedot tiedostosta.
     *  Iterator<Pelaaja> i = pelaajat.iterator();
     *  i.next().toString() === pelaaja1.toString();
     *  i.next().toString() === pelaaja2.toString();
     *  i.hasNext() === false;
     *  pelaajat.lisaa(pelaaja2);
     *  pelaajat.tallenna();
     *  ftied.delete() === true;
     * </pre>
     */
    public void lueTiedostosta() throws SailoException {

        
        try ( BufferedReader fi = new BufferedReader(new FileReader(getTiedostonNimi())) ) {
            String rivi = "";
            while ( (rivi = fi.readLine()) != null ) {
                rivi = rivi.trim();
                if ( rivi.charAt(0) == ';' ) continue;
                Pelaaja pelaaja = new Pelaaja();
                pelaaja.parse(rivi);
                lisaa(pelaaja);
            }
            muutettu = false;
        } catch ( FileNotFoundException e ) {
            throw new SailoException("Tiedosto " + getTiedostonNimi() + " ei aukea");
        } catch ( IOException e ) {
            throw new SailoException("Ongelmia tiedoston kanssa: " + e.getMessage());
        }
    }
    
    
    /**
     * Tallentaa pelaajiston tiedostoon.
     * @throws SailoException jos talletus epäonnistuu
     */
    public void tallenna() throws SailoException {
        if ( !muutettu ) return;
        
        File ftied = new File(getTiedostonNimi());

        try ( PrintWriter fo = new PrintWriter(new FileWriter(ftied.getCanonicalPath())) ) {
            for (Pelaaja pelaaja: this) {
                fo.println(pelaaja.toString());
            }
        } catch ( FileNotFoundException ex ) {
            throw new SailoException("Tiedosto " + ftied.getName() + " ei aukea");
        } catch ( IOException ex ) {
            throw new SailoException("Tiedoston " + ftied.getName() + " kirjoittamisessa ongelmia");
        }
        muutettu = false;
    }

    
    /**
     * Luetaan aikaisemmin annetun nimisestä tiedostosta
     * @param tiedosto luettavalle tiedostolle nimi
     * @throws SailoException jos tulee poikkeus
     */
    public void lueTiedostosta(String tiedosto) throws SailoException {
        setTiedostonPerusNimi(tiedosto);
        lueTiedostosta();
    }
    

    /**
     * Asettaa tiedoston perusnimen ilman tarkenninta
     * @param nimi tallennustiedoston nimi
     */
    public void setTiedostonPerusNimi(String nimi) {
        tiedostonNimi = nimi;
    }


    /**
     * Palauttaa tiedoston nimen, jota käytetään tallennukseen
     * @return tallennustiedoston nimi
     */
    public String getTiedostonNimi() {
        return tiedostonNimi + ".dat";
    }

    
    /**
     * Luokka joukkueiden iteroimiseksi.
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #PACKAGEIMPORT
     * #import java.util.*;
     * 
     * Pelaajat pelaajat = new Pelaajat();
     * Pelaaja pelaaja1 = new Pelaaja(), pelaaja2 = new Pelaaja();
     * pelaaja1.rekisteroi(); pelaaja2.rekisteroi();
     *
     * pelaajat.lisaa(pelaaja1); 
     * pelaajat.lisaa(pelaaja2); 
     * pelaajat.lisaa(pelaaja1); 
     * 
     * StringBuffer ids = new StringBuffer(30);
     * for (Pelaaja pelaaja:pelaajat)   // Kokeillaan for-silmukan toimintaa
     *   ids.append(" "+pelaaja.getTunnusNro());           
     * 
     * String tulos = " " + pelaaja1.getTunnusNro() + " " + pelaaja2.getTunnusNro() + " " + pelaaja1.getTunnusNro();
     * 
     * ids.toString() === tulos; 
     * 
     * ids = new StringBuffer(30);
     * for (Iterator<Pelaaja>  i=pelaajat.iterator(); i.hasNext(); ) { // ja iteraattorin toimintaa
     *   Pelaaja pelaaja = i.next();
     *   ids.append(" "+pelaaja.getTunnusNro());           
     * }
     * 
     * ids.toString() === tulos;
     * 
     * Iterator<Pelaaja>  i=pelaajat.iterator();
     * i.next() == pelaaja1  === true;
     * i.next() == pelaaja2  === true;
     * i.next() == pelaaja1  === true;
     * 
     * i.next();  #THROWS NoSuchElementException
     *  
     * </pre>
     */
    public class PelaajatIterator implements Iterator<Pelaaja> {
        private int kohdalla = 0;


        /**
         * Onko olemassa vielä seuraavaa pelaajaa
         * @see java.util.Iterator#hasNext()
         * @return true jos on vielä pelaajia
         */
        @Override
        public boolean hasNext() {
            return kohdalla < getLkm();
        }


        /**
         * Annetaan seuraava pelaaja
         * @return seuraava pelaaja
         * @throws NoSuchElementException jos seuraava alkiota ei enää ole
         * @see java.util.Iterator#next()
         */
        @Override
        public Pelaaja next() throws NoSuchElementException {
            if ( !hasNext() ) throw new NoSuchElementException("Ei seuraavaa");
            return anna(kohdalla++);
        }


        /**
         * Tuhoamista ei ole toteutettu
         * @throws UnsupportedOperationException aina
         * @see java.util.Iterator#remove()
         */
        @Override
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Ei poisteta");
        }
    }


    /**
     * Palautetaan iteraattori jäsenistään.
     * @return jäsen iteraattori
     */
    @Override
    public Iterator<Pelaaja> iterator() {
        return new PelaajatIterator();
    }
    

    /** Testiohjelma joukkueille
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Joukkueet joukkueet = new Joukkueet();
        Joukkue tiimi = new Joukkue();
        Joukkue tiimi2 = new Joukkue();
        
        tiimi.rekisteroi();
        tiimi.taytaPuulaakiTiedoilla();
        tiimi2.rekisteroi();
        tiimi2.taytaPuulaakiTiedoilla();
        try {
            joukkueet.lisaa(tiimi);
            joukkueet.lisaa(tiimi2);
        
            System.out.println("======== Joukkueet testi=========");
        
            for (int i = 0; i < joukkueet.getLkm(); i++) {
                Joukkue joukkue = joukkueet.anna(i);
                System.out.println("Joukkue nro: " + i);
                joukkue.tulosta(System.out);
            }          
         } catch (SailoException ex) {
             System.out.println(ex.getMessage());
         }      
    }
}
