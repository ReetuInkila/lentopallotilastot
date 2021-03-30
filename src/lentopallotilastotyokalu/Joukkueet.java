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
 *  pit‰‰ yll‰ varsinaista joukkuerekisteri‰, eli osaa: 
 *  -lis‰t‰ ja poistaa joukkueen                
 *  -lukea ja kirjoittaa joukkueiden tiedostoon      
 *  -osaa etsi‰                        
 * @author Reetu Inkil‰
 * @version Feb 22, 2021
 *
 */
public class Joukkueet implements Iterable<Joukkue>{
    
    private static final int Max_JOUKKUEITA = 10;
    private int lkm = 0;
    private String tiedostonPerusNimi = "joukkueet";
    private Joukkue[] alkiot = new Joukkue[Max_JOUKKUEITA];
    private boolean muutettu = false;
 
    
    /**
     * Tyhj‰ muodostaja
     */
    public Joukkueet() {
        // Atribuutit alustettu
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
    public void lisaa(Joukkue joukkue) throws SailoException {
        if (lkm >= alkiot.length) alkiot = Arrays.copyOf(alkiot, lkm + 10);
        alkiot[lkm] = joukkue;
        lkm++;
        muutettu = true;
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
     * @param tiedosto josta luetaan
     * @throws SailoException jos lukeminen ep‰onnistuu
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
     *  File fbak = new File(testiTiedosto+".bak");
     *  fbak.delete() === true;
     * </pre>
     */
    public void lueTiedostosta(String tiedosto) throws SailoException {
        setTiedostonPerusNimi(tiedosto);
        
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
            throw new SailoException("Tiedosto " + getTiedostonNimi() + " ei aukea");
        } catch ( IOException e ) {
            throw new SailoException("Ongelmia tiedoston kanssa: " + e.getMessage());
        }
    }
    
    
    /**
     * Luetaan aikaisemmin annetun nimisest‰ tiedostosta
     * @throws SailoException jos tulee poikkeus
     */
    public void lueTiedostosta() throws SailoException {
        lueTiedostosta(getTiedostonPerusNimi());
    }

    
    /**
     * Tallentaa joukkueiston tiedostoon.
     * @throws SailoException jos talletus ep‰onnistuu
     */
    public void tallenna() throws SailoException {
        if ( !muutettu ) return;
        
        File fbak = new File(getBakNimi());
        File ftied = new File(getTiedostonNimi());
        fbak.delete();
        ftied.renameTo(fbak);

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
     * Palauttaa tiedoston nimen, jota k‰ytet‰‰n tallennukseen
     * @return tallennustiedoston nimi
     */
    public String getTiedostonPerusNimi() {
        return tiedostonPerusNimi;
    }


    /**
     * Asettaa tiedoston perusnimen ilman tarkenninta
     * @param nimi tallennustiedoston perusnimi
     */
    public void setTiedostonPerusNimi(String nimi) {
        tiedostonPerusNimi = nimi;
    }


    /**
     * Palauttaa tiedoston nimen, jota k‰ytet‰‰n tallennukseen
     * @return tallennustiedoston nimi
     */
    public String getTiedostonNimi() {
        return getTiedostonPerusNimi() + ".dat";
    }


    /**
     * Palauttaa varakopiotiedoston nimen
     * @return varakopiotiedoston nimi
     */
    public String getBakNimi() {
        return tiedostonPerusNimi + ".bak";
    }


    /**
     * Palauttaa tilastotyˆkalun joukkueiden lukum‰‰r‰n
     * @return joukkueiden lukum‰‰r‰
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
         * Onko olemassa viel‰ seuraavaa joukkuetta
         * @see java.util.Iterator#hasNext()
         * @return true jos on viel‰ joukkueita
         */
        @Override
        public boolean hasNext() {
            return kohdalla < getLkm();
        }


        /**
         * Annetaan seuraava joukkue
         * @return seuraava joukkue
         * @throws NoSuchElementException jos seuraava alkiota ei en‰‰ ole
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
     * Palautetaan iteraattori j‰senist‰‰n.
     * @return j‰sen iteraattori
     */
    @Override
    public Iterator<Joukkue> iterator() {
        return new JoukkueetIterator();
    }


    
    /** Testiohjelma joukkueille
     * @param args ei k‰ytˆss‰
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
