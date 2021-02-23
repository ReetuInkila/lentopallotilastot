package lentopallotilastotyokalu;

import static kanta.SatunnaisNimi.*;

import java.io.OutputStream;
import java.io.PrintStream;

/** Luokka Pelaaja
 * - tietää pelaajan kentät (joukkue id, nimi, jne.)
 * - osaa tarkistaa tietyn kentän oikeellisuuden (syntaksin)                                    
 * - osaa muuttaa 1|Ankka Aku|..| - merkkijonon pelaajan tiedoiksi                             
 * - osaa antaa merkkijonona i:n kentän tiedot      
 * - osaa laittaa merkkijonon i:neksi kentäksi      
 * @author RInkila
 * @version Feb 23, 2021
 *
 */
public class Pelaaja {
    private int tunnusNro;
    private String nimi = "";
    private String pelipaikka = "";
    private int pelinumero = 0;
    
    private static int seuraavaNro = 1;
    
    /** Tulostetaan pelaajan tiedot
     * @param out tietovirta mihin tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", tunnusNro) + " " + nimi + " " + String.format("%02d", pelinumero) + " " + pelipaikka);
    }
    
    /**
     * Tulostetaan pelaajan tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    /**
     * Antaa pelaajalle seuraavan rekisterinumeron.
     * @return pelaajan uusi tunnusNro
     * @example
     * <pre name="test">
     *   Pelaaja peluri1 = new Pelaaja();
     *   peluri1.getTunnusNro() === 0;
     *   peluri1.rekisteroi();
     *   Pelaaja peluri2 = new Pelaaja();
     *   peluri2.rekisteroi();
     *   int n1 = peluri1.getTunnusNro();
     *   int n2 = peluri2.getTunnusNro();
     *   n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        this.tunnusNro = seuraavaNro;
        seuraavaNro++;
        return this.tunnusNro;
    }
    
    /**
     * Palauttaa pelaajan tunnusnumeron.
     * @return pelaajan tunnusnumero
     */
    public int getTunnusNro() {
        return tunnusNro;
    }
    
    /**
     * @return pelaajan nimi
     * @example
     * <pre name="test">
     *   Pelaaja pete = new Pelaaja();
     *   pete.taytaEsimerkkiTiedoilla();
     *   pete.getNimi() =R= "Pete Pelaaja .*";
     * </pre>
     */
    public String getNimi() {
        return nimi;
    }
    
    /**
     * Palauttaa pelaajan pelinumeron.
     * @return pelaajan pelinumero
     */
    public int getPelinumero() {
        return pelinumero;
    }
    
    /**
     * Palauttaa pelaajan pelipaikan.
     * @return pelaajan pelipaikka
     */
    public String getPelipaikka() {
        return pelipaikka;
    }
    
    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot jäsenelle.
     */
    public void taytaEsimerkkiTiedoilla() {
        nimi = "Pete Pelaaja " + rand(1000, 9999);
        pelipaikka = arvoPeliPaikka();
        pelinumero = rand(1,40);
    }


    

    /** Testataan pelaaja luokkaa
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Pelaaja peluri = new Pelaaja();
        Pelaaja peluri2 = new Pelaaja();
        
        peluri.rekisteroi();
        peluri2.rekisteroi();
        
        peluri.tulosta(System.out);
        peluri.taytaEsimerkkiTiedoilla();
        peluri.tulosta(System.out);
        
        peluri2.tulosta(System.out);
        peluri2.taytaEsimerkkiTiedoilla();
        peluri2.tulosta(System.out);

    }

}
