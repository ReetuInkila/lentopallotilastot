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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import fi.jyu.mit.ohj2.WildChars;


/**
 * @author Reetu Inkil�
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
     * Tyhj� muodostaja
     */
    public Pelaajat() {
        // Atribuutit alustettu
    }
    
    
    /**
     * Lis�� uuden pelaajan tietorakenteeseen.  Ottaa pelaajan omistukseensa.
     * @param pelaaja lis�tt�v�n pelaajan viite.  Huom tietorakenne muuttuu omistajaksi
     * @throws SailoException jos tietorakenne on jo t�ynn�
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
     * Poistaa pelaajan jolla on valittu tunnusnumero  
     * @param id poistettavan pelaajan tunnusnumero 
     * @return 1 jos poistettiin, 0 jos ei l�ydy 
     * @example 
     * <pre name="test"> 
     * #THROWS SailoException  
     * Pelaajat pelaajat = new Pelaajat(); 
     * Pelaaja peluri1 = new Pelaaja(), peluri2 = new Pelaaja(), peluri3 = new Pelaaja(); 
     * peluri1.rekisteroi(); peluri2.rekisteroi(); peluri3.rekisteroi(); 
     * int id1 = peluri1.getTunnusNro(); 
     * pelaajat.lisaa(peluri1); pelaajat.lisaa(peluri2); pelaajat.lisaa(peluri3); 
     * pelaajat.poista(id1+1) === 1; 
     * pelaajat.annaId(id1+1) === null; pelaajat.getLkm() === 2; 
     * pelaajat.poista(id1) === 1; pelaajat.getLkm() === 1; 
     * pelaajat.poista(id1+3) === 0; pelaajat.getLkm() === 1; 
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
     * Etsii pelaajan id:n perusteella 
     * @param id tunnusnumero, jonka mukaan etsit��n 
     * @return l�ytyneen pelaajan indeksi tai -1 jos ei l�ydy 
     * <pre name="test"> 
     * #THROWS SailoException  
     * Pelaajat pelaajat = new Pelaajat(); 
     * Pelaaja peluri1 = new Pelaaja(), peluri2 = new Pelaaja(), peluri3 = new Pelaaja(); 
     * peluri1.rekisteroi(); peluri2.rekisteroi(); peluri3.rekisteroi(); 
     * int id1 = peluri1.getTunnusNro(); 
     * pelaajat.lisaa(peluri1); pelaajat.lisaa(peluri2); pelaajat.lisaa(peluri3); 
     * pelaajat.etsiId(id1+1) === 1; 
     * pelaajat.etsiId(id1+2) === 2; 
     * </pre> 
     */ 
    public int etsiId(int id) { 
        for (int i = 0; i < lkm; i++) 
            if (id == alkiot[i].getTunnusNro()) return i; 
        return -1; 
    }

    
    
    /**
     * Palauttaa viitteen id:n omaavaan pelaajaan.
     * @param id mink� pelaajan viite halutaan
     * @return viite pelaajaan, jonka id on i 
     */
    public Pelaaja annaId(int id) {
        for (Pelaaja pelaaja: this) {
            if (pelaaja.getTunnusNro() == id) return pelaaja;
        }
        return null;       
    }
    
    
    /**
     * Palauttaa tilastoty�kalun pelaajien lukum��r�n
     * @return j�senten lukum��r�
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
     * @throws SailoException jos lukeminen ep�onnistuu
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
     * @throws SailoException jos talletus ep�onnistuu
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
     * Luetaan aikaisemmin annetun nimisest� tiedostosta
     * @param tiedosto luettavalle tiedostolle nimi
     * @throws SailoException jos tulee poikkeus
     */
    public void lueTiedostosta(String tiedosto) throws SailoException {
        setTiedostonNimi(tiedosto);
        lueTiedostosta();
    }
    

    /**
     * Asettaa tiedoston perusnimen ilman tarkenninta
     * @param nimi tallennustiedoston nimi
     */
    public void setTiedostonNimi(String nimi) {
        tiedostonNimi = nimi;
    }


    /**
     * Palauttaa tiedoston nimen, jota k�ytet��n tallennukseen
     * @return tallennustiedoston nimi
     */
    public String getTiedostonNimi() {
        return tiedostonNimi + ".dat";
    }
    
    
    /** 
     * Palauttaa "taulukossa" hakuehtoon vastaavien pelaajien viitteet 
     * @param hakuehto hakuehto 
     * @param k etsitt�v�n kent�n indeksi  
     * @return tietorakenteen l�ytyneist� j�senist� 
     * @example 
     * <pre name="test"> 
     * #THROWS SailoException  
     *   Pelaajat pelaajat = new Pelaajat(); 
     *   Pelaaja pelaaja1 = new Pelaaja(), pelaaja2 = new Pelaaja();
     *   pelaaja1.parse("1   |1     |2          |Eemil Tervaportti |Passari        | ");
     *   pelaaja2.parse("5   |2     |26         |Pete Pelaaja      |Hakkuri        | ");
     *   pelaajat.lisaa(pelaaja1); pelaajat.lisaa(pelaaja2);
     *   List<Pelaaja> loytyneet;  
     *   loytyneet = (List<Pelaaja>)pelaajat.etsi("*s*",4);  
     *   loytyneet.size() === 1;  
     *   loytyneet.get(0) == pelaaja1 === true;   
     *     
     *   loytyneet = (List<Pelaaja>)pelaajat.etsi("*2*",2);  
     *   loytyneet.size() === 2;  
     *   loytyneet.get(0) == pelaaja1 === true;  
     *   loytyneet.get(1) == pelaaja2 === true; 
     * </pre> 
     */ 
    public Collection<Pelaaja> etsi(String hakuehto, int k) { 
        String ehto = "*"; 
        if ( hakuehto != null && hakuehto.length() > 0 ) ehto = hakuehto; 
        int hk = k; 
        if ( hk < 0 ) hk = 0; // jotta etsii id:n mukaan 
        List<Pelaaja> loytyneet = new ArrayList<Pelaaja>(); 
        for (Pelaaja pelaaja : this) { 
            if (WildChars.onkoSamat(pelaaja.getKentta(hk), ehto)) loytyneet.add(pelaaja);   
        } 
        Collections.sort(loytyneet, new Pelaaja.Vertailija(hk)); 
        return loytyneet; 
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
         * Onko olemassa viel� seuraavaa pelaajaa
         * @see java.util.Iterator#hasNext()
         * @return true jos on viel� pelaajia
         */
        @Override
        public boolean hasNext() {
            return kohdalla < getLkm();
        }


        /**
         * Annetaan seuraava pelaaja
         * @return seuraava pelaaja
         * @throws NoSuchElementException jos seuraava alkiota ei en�� ole
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
     * Palautetaan iteraattori j�senist��n.
     * @return j�sen iteraattori
     */
    @Override
    public Iterator<Pelaaja> iterator() {
        return new PelaajatIterator();
    }
    

    /** Testiohjelma joukkueille
     * @param args ei k�yt�ss�
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
