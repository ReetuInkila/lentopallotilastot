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

/** Luokka Joukkueet
 *  pitää yllä varsinaista joukkuerekisteriä, eli osaa: 
 *  -lisätä ja poistaa joukkueen                
 *  -lukea ja kirjoittaa joukkueiden tiedostoon                              
 * @author Reetu Inkilä
 * @version Feb 22, 2021
 *
 */
public class Joukkueet implements Iterable<Joukkue>{
    
    private static final int Max_JOUKKUEITA = 10;
    private int lkm = 0;
    private String tiedostonNimi = "joukkueet";
    private Joukkue[] alkiot = new Joukkue[Max_JOUKKUEITA];
    private boolean muutettu = false;
 
    
    /**
     * Tyhjä muodostaja
     */
    public Joukkueet() {
        // Atribuutit alustettu
    }
 
    
    /**
     * Lisää uuden joukkueen tietorakenteeseen.  Ottaa joukkueen omistukseensa.
     * @param joukkue lisätäävän joukkueen viite.  Huom tietorakenne muuttuu omistajaksi
     * @throws SailoException jos tietorakenne on jo täynnä
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
    public void lisaa(Joukkue joukkue) throws SailoException {
        if (lkm >= alkiot.length) alkiot = Arrays.copyOf(alkiot, lkm + 10);
        alkiot[lkm] = joukkue;
        lkm++;
        muutettu = true;
    }
    
    
    /** 
     * Poistaa joukkueen jolla on valittu tunnusnumero  
     * @param id poistettavan joukkueen tunnusnumero 
     * @return 1 jos poistettiin, 0 jos ei löydy 
     * @example 
     * <pre name="test"> 
     * #THROWS SailoException  
     *  Joukkueet joukkueet = new Joukkueet();
     *  Joukkue joukkue1 = new Joukkue(), joukkue2 = new Joukkue();
     *  joukkue1.rekisteroi();
     *  joukkue2.rekisteroi(); 
     *  joukkue1.taytaPuulaakiTiedoilla();
     *  joukkue2.taytaPuulaakiTiedoilla();
     *  joukkueet.lisaa(joukkue1); joukkueet.lisaa(joukkue2); 
     *  int id1 = joukkue1.getTunnusNro(); 
     *  joukkueet.poista(id1+1) === 1; 
     *  joukkueet.etsiId(id1+1) === -1; joukkueet.getLkm() === 1; 
     *  joukkueet.poista(id1) === 1; joukkueet.getLkm() === 0;  
     * </pre> 
     *  
     */ 
    public int poista(int id) { 
        int ind = etsiId(id); 
        if (ind < 0) return 0; 
        lkm--; 
        for (int i = ind; i < lkm; i++) 
            alkiot[i] = alkiot[i + 1]; 
        alkiot[lkm] = null; 
        muutettu = true; 
        return 1; 
    }
    
    
    /** 
     * Etsii joukkueen id:n perusteella 
     * @param id tunnusnumero, jonka mukaan etsitään 
     * @return löytyneen joukkueen indeksi tai -1 jos ei löydy 
     * <pre name="test"> 
     * #THROWS SailoException  
     *  Joukkueet joukkueet = new Joukkueet();
     *  Joukkue joukkue1 = new Joukkue(), joukkue2 = new Joukkue();
     *  joukkue1.rekisteroi();
     *  joukkue2.rekisteroi(); 
     *  joukkue1.taytaPuulaakiTiedoilla();
     *  joukkue2.taytaPuulaakiTiedoilla(); 
     *  joukkueet.lisaa(joukkue1); joukkueet.lisaa(joukkue2); 
     *  int id1 = joukkue1.getTunnusNro();  
     *  joukkueet.etsiId(id1+1) === 1; 
     *  joukkueet.etsiId(id1+2) === -1;
     *  joukkueet.etsiId(id1) === 0; 
     * </pre> 
     */ 
    public int etsiId(int id) { 
        for (int i = 0; i < lkm; i++) 
            if (id == alkiot[i].getTunnusNro()) return i; 
        return -1; 
    }
    
    
    /**
     * Palauttaa viitteen i:teen joukkueeseen.
     * @param i monennenko joukkueen viite halutaan
     * @return viite joukkueeseen, jonka indeksi on i
     * @throws IndexOutOfBoundsException jos i ei ole sallitulla alueella  
     */
    public Joukkue anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || lkm <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return alkiot[i];
    }
    
    
    /**
     * Lukee joukkueet tiedostosta. 
     * @throws SailoException jos lukeminen epäonnistuu
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.File;
     * 
     *  Joukkueet joukkueet = new Joukkueet();
     *  Joukkue joukkue1 = new Joukkue(), joukkue2 = new Joukkue();
     *  joukkue1.rekisteroi();
     *  joukkue2.rekisteroi(); 
     *  joukkue1.taytaPuulaakiTiedoilla();
     *  joukkue2.taytaPuulaakiTiedoilla();
     *  String testiTiedosto = "testijoukkueet";
     *  File ftied = new File(testiTiedosto+".dat");
     *  ftied.delete();
     *  joukkueet.lueTiedostosta(testiTiedosto); #THROWS SailoException
     *  joukkueet.lisaa(joukkue1);
     *  joukkueet.lisaa(joukkue2);
     *  joukkueet.tallenna();
     *  joukkueet = new Joukkueet();            // Poistetaan vanhat luomalla uusi
     *  joukkueet.lueTiedostosta(testiTiedosto);  // johon ladataan tiedot tiedostosta.
     *  Iterator<Joukkue> i = joukkueet.iterator();
     *  i.next().toString() === joukkue1.toString();
     *  i.next().toString() === joukkue2.toString();
     *  i.hasNext() === false;
     *  joukkueet.lisaa(joukkue2);
     *  joukkueet.tallenna();
     *  ftied.delete() === true;
     * </pre>
     */
    public void lueTiedostosta() throws SailoException {
        try ( BufferedReader fi = new BufferedReader(new FileReader(getTiedostonNimi())) ) {
            String rivi = "";
            while ( (rivi = fi.readLine()) != null ) {
                rivi = rivi.trim();
                if ( rivi.charAt(0) == ';' ) continue;
                Joukkue joukkue = new Joukkue();
                joukkue.parse(rivi);
                lisaa(joukkue);
            }
            muutettu = false;
        } catch ( FileNotFoundException e ) {
            throw new SailoException("Tiedosto " + tiedostonNimi + " ei aukea");
        } catch ( IOException e ) {
            throw new SailoException("Ongelmia tiedoston kanssa: " + e.getMessage());
        }
    }
    
    
    /**
     * Luetaan aikaisemmin annetun nimisestä tiedostosta
     * @param nimi uusi nimi tiedostolle mitä luetaan
     * @throws SailoException jos tulee poikkeus
     */
    public void lueTiedostosta(String nimi) throws SailoException {
        setTiedostonNimi(nimi);
        lueTiedostosta();
    }


    /**
     * Asettaa tiedoston nimen ilman tarkenninta
     * @param nimi tallennustiedoston perusnimi
     */
    public void setTiedostonNimi(String nimi) {
        tiedostonNimi = nimi;
    }
    
    
    /**
     * Palauttaa tiedoston nimen, jota kytetn tallennukseen
     * @return tallennustiedoston nimi
     */
    public String getTiedostonNimi() {
        return tiedostonNimi + ".dat";
    }

    
    /**
     * Tallentaa joukkueiston tiedostoon.
     * @throws SailoException jos talletus epäonnistuu
     */
    public void tallenna() throws SailoException {
        if ( !muutettu ) return;
        
        File ftied = new File(getTiedostonNimi());

        try ( PrintWriter fo = new PrintWriter(new FileWriter(ftied.getCanonicalPath())) ) {
            for (Joukkue joukkue: this) {
                fo.println(joukkue.toString());
            }
        } catch ( FileNotFoundException ex ) {
            throw new SailoException("Tiedosto " + ftied.getName() + " ei aukea");
        } catch ( IOException ex ) {
            throw new SailoException("Tiedoston " + ftied.getName() + " kirjoittamisessa ongelmia");
        }
        muutettu = false;
    }
    

    /**
     * Palauttaa tilastotyökalun joukkueiden lukumäärän
     * @return joukkueiden lukumäärä
     */
    public int getLkm() {
        return lkm;
    }
   
    
    /**
     * Luokka joukkueiden iteroimiseksi.
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #PACKAGEIMPORT
     * #import java.util.*;
     * 
     * Joukkueet joukkueet = new Joukkueet();
     * Joukkue tiimi1 = new Joukkue(), tiimi2 = new Joukkue();
     * tiimi1.rekisteroi(); tiimi2.rekisteroi();
     *
     * joukkueet.lisaa(tiimi1); 
     * joukkueet.lisaa(tiimi2); 
     * joukkueet.lisaa(tiimi1); 
     * 
     * StringBuffer ids = new StringBuffer(30);
     * for (Joukkue joukkue:joukkueet)   // Kokeillaan for-silmukan toimintaa
     *   ids.append(" "+joukkue.getTunnusNro());           
     * 
     * String tulos = " " + tiimi1.getTunnusNro() + " " + tiimi2.getTunnusNro() + " " + tiimi1.getTunnusNro();
     * 
     * ids.toString() === tulos; 
     * 
     * ids = new StringBuffer(30);
     * for (Iterator<Joukkue>  i=joukkueet.iterator(); i.hasNext(); ) { // ja iteraattorin toimintaa
     *   Joukkue joukkue = i.next();
     *   ids.append(" "+joukkue.getTunnusNro());           
     * }
     * 
     * ids.toString() === tulos;
     * 
     * Iterator<Joukkue>  i=joukkueet.iterator();
     * i.next() == tiimi1  === true;
     * i.next() == tiimi2  === true;
     * i.next() == tiimi1  === true;
     * 
     * i.next();  #THROWS NoSuchElementException
     *  
     * </pre>
     */
    public class JoukkueetIterator implements Iterator<Joukkue> {
        private int kohdalla = 0;


        /**
         * Onko olemassa vielä seuraavaa joukkuetta
         * @see java.util.Iterator#hasNext()
         * @return true jos on vielä joukkueita
         */
        @Override
        public boolean hasNext() {
            return kohdalla < getLkm();
        }


        /**
         * Annetaan seuraava joukkue
         * @return seuraava joukkue
         * @throws NoSuchElementException jos seuraava alkiota ei enää ole
         * @see java.util.Iterator#next()
         */
        @Override
        public Joukkue next() throws NoSuchElementException {
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
    public Iterator<Joukkue> iterator() {
        return new JoukkueetIterator();
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
