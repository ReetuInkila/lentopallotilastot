package lentopallotilastotyokalu;

import static kanta.SatunnaisNimi.*;

import java.io.OutputStream;
import java.io.PrintStream;

/** Luokka Pelaaja
 * - tiet�� pelaajan kent�t (joukkue id, nimi, jne.)
 * - osaa tarkistaa tietyn kent�n oikeellisuuden (syntaksin)                                    
 * - osaa muuttaa 1|Ankka Aku|..| - merkkijonon pelaajan tiedoiksi                             
 * - osaa antaa merkkijonona i:n kent�n tiedot      
 * - osaa laittaa merkkijonon i:neksi kent�ksi      
 * @author RInkila
 * @version Feb 23, 2021
 *
 */
public class Pelaaja {
    private int tunnusNro;
    private int jId;
    private int pId;
    private String nimi = "";
    private String pelipaikka = "";
    private int pelinumero = 0;
    
    private static int seuraavaNro = 1;
    private static int seuraavaId = 1;
    
    /** Tulostetaan pelaajan tiedot
     * @param out tietovirta mihin tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", tunnusNro) + " " + String.format("%02d", pId) + " " + String.format("%02d", jId) + " " + nimi + " " + String.format("%02d", pelinumero) + " " + pelipaikka);
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
        this.pId = seuraavaId;
        seuraavaId++;
        return this.tunnusNro;
    }
    
    /** Asetataan pelaajalle joukkeeseen viittaava id
     * @param id pelaajalle asetettava joukkue id numero
     */
    public void asetaJId(int id) {
        this.jId = id; 
    }
    
    /**
     * Palauttaa pelaajan tunnusnumeron.
     * @return pelaajan tunnusnumero
     */
    public int getTunnusNro() {
        return tunnusNro;
    }
    
    /**
     * Palauttaa pelaajan id:n.
     * @return pelaajan jId
     */
    public int getpId() {
        return pId;
    }
    
    /**
     * Palauttaa pelaajan joukkue id:n.
     * @return pelaajan joukkue numero
     */
    public int getJId() {
        return jId;
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
     * Apumetodi, jolla saadaan t�ytetty� testiarvot j�senelle.
     */
    public void taytaEsimerkkiTiedoilla() {
        nimi = "Pete Pelaaja " + rand(1000, 9999);
        pelipaikka = arvoPeliPaikka();
        pelinumero = rand(1,40);
    }


    

    /** Testataan pelaaja luokkaa
     * @param args ei k�yt�ss�
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
