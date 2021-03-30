package lentopallotilastotyokalu;

import static kanta.SatunnaisNimi.*;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

/** Luokka Pelaaja
 * - tietää pelaajan kentät (joukkue id, nimi, jne.)
 * - osaa tarkistaa tietyn kentän oikeellisuuden (syntaksin)                                    
 * - osaa muuttaa 1|Ankka Aku|..| - merkkijonon pelaajan tiedoiksi                             
 * - osaa antaa merkkijonona i:n kentän tiedot      
 * - osaa laittaa merkkijonon i:neksi kentäksi      
 * @author Reetu Inkilä
 * @version Feb 23, 2021
 *
 */
public class Pelaaja {
    private int tunnusNro;
    private int jId;
    private String nimi = "";
    private String pelipaikka = "";
    private int pelinumero = 0;    
    private static int seuraavaNro = 1;
    
    
    /** Tulostetaan pelaajan tiedot
     * @param out tietovirta mihin tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", tunnusNro) + " " + String.format("%02d", jId));
        out.println();
        out.println(nimi);
        out.println();
        out.println(String.format("%02d", pelinumero));
        out.println();
        out.println(pelipaikka);
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
    
    
    /** Asetataan pelaajalle joukkeeseen viittaava id
     * @param id pelaajalle asetettava joukkue id numero
     */
    public void asetaJId(int id) {
        this.jId = id; 
    }
    
    
    /**
     * Asettaa pelaajalle tunnusnumeron ja varmistaa että seuraava tunnusnumero on isompi
     * @param numero pelaajalle asetettava numero
     */
    private void setTunnusNro(int numero) {
        tunnusNro = numero;
        if (tunnusNro >= seuraavaNro) seuraavaNro = tunnusNro + 1;

    }
    
    
    /**
     * Palauttaa pelaajan tunnusnumeron.
     * @return pelaajan tunnusnumero
     */
    public int getTunnusNro() {
        return tunnusNro;
    }
    
    
    /**
     * Palauttaa pelaajan joukkue id:n.
     * @return pelaajan jId
     */
    public int getJId() {
        return jId;
    }
    
    
    /** Palauttaa pelaajan nimen
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
     * Apumetodi, jolla saadaan täytettyä testiarvot jäsenelle.
     */
    public void taytaEsimerkkiTiedoilla() {
        nimi = "Pete Pelaaja " + rand(1000, 9999);
        pelipaikka = arvoPeliPaikka();
        pelinumero = rand(1,40);
    }
    
    
    /** Selvitää pelaajan tiedot | erotellusta merkkijonosta
     * @param tiedot pelaajien tiedostosta luettu rivi merkkijonona
     * @example
     * <pre name="test">
     *  Pelaaja pelaaja = new Pelaaja();
     *  pelaaja.parse("1   |1     |2          |Eemil Tervaportti |Passari        | ");
     *  pelaaja.toString() === "1|1|2|Eemil Tervaportti|Passari|";
     *
     *  pelaaja.rekisteroi();
     *  int n = pelaaja.getTunnusNro();
     *  pelaaja.parse(""+(n+20));       // Otetaan merkkijonosta vain tunnusnumero
     *  pelaaja.rekisteroi();           // ja tarkistetaan että seuraavalla kertaa tulee yhtä isompi
     *  pelaaja.getTunnusNro() === n+20+1;
     * </pre>
     */
    public void parse(String tiedot) {
      StringBuilder sb = new StringBuilder(tiedot);
      setTunnusNro(Mjonot.erota(sb, '|', getTunnusNro()));
      jId = Mjonot.erota(sb, '|', jId);
      pelinumero = Mjonot.erota(sb, '|', pelinumero);
      nimi = Mjonot.erota(sb, '|', nimi);
      pelipaikka = Mjonot.erota(sb, '|', pelipaikka);
    }
    
    
    /**
     * Palauttaa pelaajan tiedot merkkijonona jonka voi tallettaa tiedostoon
     * @example
     * <pre name="test">
     *  Pelaaja pelaaja = new Pelaaja();
     *  pelaaja.parse("1   |1     |2          |Eemil Tervaportti |Passari        | ");
     *  pelaaja.toString() === "1|1|2|Eemil Tervaportti|Passari|";
     * </pre>
     */
    @Override
    public String toString() {
        return tunnusNro + "|" + jId + "|" + pelinumero + "|" + nimi + "|" + pelipaikka + "|";    
    }
    

    /** Testataan pelaaja luokkaa
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Pelaaja peluri = new Pelaaja();
        Pelaaja peluri2 = new Pelaaja();
        
        peluri.rekisteroi();
        peluri2.rekisteroi();
        
        peluri.taytaEsimerkkiTiedoilla();
        peluri.tulosta(System.out);
        
        peluri2.taytaEsimerkkiTiedoilla();
        peluri2.tulosta(System.out);
    }
}
