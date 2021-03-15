package lentopallotilastotyokalu;

import java.io.OutputStream;
import java.io.PrintStream;
import static kanta.SatunnaisNimi.*;

/**
 * Luokka joukkueelle
 * -tietää Joukkueen kentät (nimi,id)                
 * -osaa muuttaa 1|Puulaaki|- merkkijonon            
 *  joukkueen tiedoiksi                              
 * -osaa antaa merkkijonona i:n kentän tiedot        
 * -osaa laittaa merkkijonon i:neksi kentäksi        
 * @author Reetu Inkilä
 * @version Feb 18, 2021
 *
 */
public class Joukkue {
    
    private int tunnusNro;
    private int jId;
    private String nimi = "";
    
    private static int seuraavaNro = 1;
    private static int seuraavaid = 1;

    
    /** Alustetaan joukkue jolla on valitu nimi
     * @param joukkueenNimi Alustettavan joukkeen nimi
     */
    public Joukkue(String joukkueenNimi) {
        this.nimi = joukkueenNimi;
    }
    
    /**
     * Parametriton muodostaja
     */
    public Joukkue() {
        //
    }

    /** Tulostetaan joukkueen tiedot
     * @param out tietovirta mihin tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", tunnusNro) + " " + String.format("%03d", jId) + " " + nimi);
    }
    
    /**
     * Tulostetaan Joukkueen tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    /**
     * Antaa joukkueelle seuraavan rekisterinumeron.
     * @return joukkueen uusi tunnusNro
     * @example
     * <pre name="test">
     *   Joukkue tiimi1 = new Joukkue();
     *   tiimi1.getTunnusNro() === 0;
     *   tiimi1.rekisteroi();
     *   Joukkue tiimi2 = new Joukkue();
     *   tiimi2.rekisteroi();
     *   int n1 = tiimi1.getTunnusNro();
     *   int n2 = tiimi2.getTunnusNro();
     *   n1 === n2-1;
     *   tiimi1.getId() === 1;
     *   tiimi2.getId() === 2;
     * </pre>
     */
    public int rekisteroi() {
        this.tunnusNro = seuraavaNro;
        seuraavaNro++;
        this.jId = seuraavaid;
        seuraavaid++;
        return this.tunnusNro;
    }
    
    /**
     * Palauttaa joukkueen tunnusnumeron.
     * @return jäsenen tunnusnumero
     */
    public int getTunnusNro() {
        return tunnusNro;
    }
    
    /**
     * Palauttaa joukkueen id-numeron.
     * @return joukkueen id numero
     */
    public int getId() {
        return jId;
    }
    
    /** Palauttaa joukkeen nimen merkkijonona
     * @return joukkueen nimi
     * @example
     * <pre name="test">
     *   Joukkue tiimi = new Joukkue();
     *   tiimi.taytaPuulaakiTiedoilla();
     *   tiimi.getNimi() =R= "Puulaaki .*";
     * </pre>
     */
    public String getNimi() {
        return nimi;
    }

    /**
     * Apumetodi jolla saadaan täytettyä testausta varten arvot joukkueelle
     */
    public void taytaPuulaakiTiedoilla() {
        nimi = "Puulaaki " + rand(1000, 9999);    
    }

    /** Testataan Joukkue luokkaa
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Joukkue tiimi = new Joukkue();
        Joukkue tiimi2 = new Joukkue();
        
        tiimi.rekisteroi();
        tiimi2.rekisteroi();
        
        tiimi.taytaPuulaakiTiedoilla();
        tiimi.tulosta(System.out);
        
        tiimi2.taytaPuulaakiTiedoilla();
        tiimi2.tulosta(System.out);
    }
}