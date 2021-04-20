package lentopallotilastotyokalu;

import java.io.OutputStream;
import fi.jyu.mit.ohj2.Mjonot;
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
    private String nimi = "";
    
    private static int seuraavaNro = 1;

    
    /** Alustetaan joukkue jolla on valitu nimi
     * @param joukkueenNimi Alustettavan joukkeen nimi
     */
    public Joukkue(String joukkueenNimi) {
        nimi = joukkueenNimi;
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
        out.println(String.format("%03d", tunnusNro) + " " + nimi);
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
     * </pre>
     */
    public int rekisteroi() {
        tunnusNro = seuraavaNro;
        seuraavaNro++;
        return tunnusNro;
    }

    
    /**
     * Palauttaa joukkueen tunnusnumeron.
     * @return joukkueen tunnusnumero
     */
    public int getTunnusNro() {
        return tunnusNro;
    }

    
    /**
     * Asettaa joukkueelle tunnusnumeron ja varmistaa että seuraava tunnusnumero on isompi
     * @param numero joukkueelle asetettava numero
     */
    private void setTunnusNro(int numero) {
        tunnusNro = numero;
        if (tunnusNro >= seuraavaNro) seuraavaNro = tunnusNro + 1;

    }

    
    /**
     * Palauttaa joukkueen tiedot merkkijonona jonka voi tallettaa tiedostoon
     * @example
     * <pre name="test">
     *  Joukkue joukkue = new Joukkue();
     *  Joukkue joukkue2 = new Joukkue("Joukkue|viivalla");
     *  joukkue.parse("   8  |  Oulun Etta  |");
     *  joukkue.toString() === "8|Oulun Etta|";
     *  joukkue2.toString() === "0|Joukkue\\|viivalla|";
     * </pre>
     */
    @Override
    public String toString() {
        if (nimi.indexOf('|')>-1) {
            String n = nimi.substring(0, nimi.indexOf('|') )
                    + "\\"
                    + nimi.substring(nimi.indexOf('|') );
            return getTunnusNro() + "|" + n + "|";
        }
        return getTunnusNro() + "|" + nimi + "|";
      
    }

    
    /** Selvitää joukkueen tiedot | erotellusta merkkijonosta
     * @param tiedot joukkueen tiedostosta luettu rivi merkkijonona
     * @example
     * <pre name="test">
     *  Joukkue joukkue = new Joukkue();
     *  joukkue.parse("   8  |  Oulun Etta  |");
     *  joukkue.toString() === "8|Oulun Etta|";
     *  
     *  Joukkue joukkue2 = new Joukkue();
     *  joukkue2.parse("   8  |Joukkue\\|viivalla|");
     *  joukkue2.getNimi() === "Joukkue|viivalla"; 
     *
     *  joukkue.rekisteroi();
     *  int n = joukkue.getTunnusNro();
     *  joukkue.parse(""+(n+20));       // Otetaan merkkijonosta vain tunnusnumero
     *  joukkue.rekisteroi();           // ja tarkistetaan että seuraavalla kertaa tulee yhtä isompi
     *  joukkue.getTunnusNro() === n+20+1;
     * </pre>
     */
    public void parse(String tiedot) {
      StringBuilder sb = new StringBuilder(tiedot);
      setTunnusNro(Mjonot.erota(sb, '|', getTunnusNro()));
      if (sb.indexOf("\\") > -1) {
          nimi = Mjonot.erota(sb, '\\', nimi) + Mjonot.erota(sb, '|', nimi) + "|" + Mjonot.erota(sb, '|', nimi);
          return;
      }
      nimi = Mjonot.erota(sb, '|', nimi);
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