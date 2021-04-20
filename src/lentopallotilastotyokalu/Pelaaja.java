package lentopallotilastotyokalu;

import static kanta.SatunnaisNimi.*;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Comparator;

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
    private int pelinumero = 0;
    private String nimi = "";
    private String pelipaikka = "";       
    private static int seuraavaNro = 1;
    

    /** 
     * Antaa k:n kentän sisällön merkkijonona 
     * @param k monenenko kentän sisältö palautetaan 
     * @return kentän sisältö merkkijonona 
     */ 
    public String getKentta(int k) { 
        switch ( k ) { 
        case 0: return "" + tunnusNro; 
        case 1: return "" + jId; 
        case 2: return "" + pelinumero;
        case 3: return "" + nimi;
        case 4: return "" + pelipaikka;           
        default: return "Ei toimi"; 
        } 
    }
    
    
    /**
     * Asettaa k:n kentän arvoksi parametrina tuodun merkkijonon arvon
     * @param k kuinka monennen kentän arvo asetetaan
     * @param jono jonoa joka asetetaan kentän arvoksi
     * @return null jos asettaminen onnistuu, muuten vastaava virheilmoitus.
     * @example
     * <pre name="test">
     *   Pelaaja pelaaja = new Pelaaja();
     *   pelaaja.aseta(3,"Ankka Aku") === null;
     *   pelaaja.aseta(1,"5") === null;
     *   pelaaja.aseta(4,"passari") === null; 
     *   pelaaja.aseta(2,"58") === null;
     *   pelaaja.getKentta(1) === "5";
     *   pelaaja.getKentta(3) === "Ankka Aku";
     *   pelaaja.getKentta(4) === "passari";
     *   pelaaja.getKentta(2) === "58"; 
     * </pre>
     */
    public String aseta(int k, String jono) {
        String tjono = jono.trim();
        StringBuffer sb = new StringBuffer(tjono);
        switch ( k ) {
        case 0:
            setTunnusNro(Mjonot.erotaInt(sb, getTunnusNro()));
            return null;
        case 1:
            jId = Integer.valueOf(tjono);
            return null;
        case 2:
            pelinumero = Integer.valueOf(tjono);;
            return null;
        case 3:
            nimi = tjono;
            return null;
        case 4:
            pelipaikka = tjono;
            return null;
        default:
            return "Ei sovi";
        }
    }
    
    
    /**
     * Palauttaa k:tta jäsenen kenttää vastaavan kysymyksen
     * @param k kuinka monennen kentän kysymys palautetaan (0-alkuinen)
     * @return k:netta kenttää vastaava kysymys
     */
    public String getKysymys(int k) {
        switch ( k ) {
        case 0: return "Tunnus nro";
        case 1: return "Joukkue id";
        case 2: return "Pelinumero";
        case 3: return "Nimi";
        case 4: return "Pelipaikka";
        default: return "Ei toimi";
        }
    }
    
    
    /**
     * Palauttaa jäsenen kenttien lukumäärän
     * @return kenttien lukumäärä
     */
    public int getKenttia() {
        return 5;
    }


    /**
     * Ekan kentän numero joka on mielekäs kysyttäväksi
     * @return ekan kentän indeksi
     */
    public int ekaKentta() {
        return 2;
    }

    
    /** 
     * Pelaajien vertailija 
     */ 
    public static class Vertailija implements Comparator<Pelaaja> { 
        private int k;  
         
        @SuppressWarnings("javadoc") 
        public Vertailija(int k) { 
            this.k = k; 
        } 
         
        @Override 
        public int compare(Pelaaja pelaaaja1, Pelaaja pelaaaja2) { 
            return pelaaaja1.getKentta(k).compareToIgnoreCase(pelaaaja2.getKentta(k)); 
        } 
    } 
    
    
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
     * Palauttaa pelaajan pelipaikan.
     * @return pelaajan pelipaikka merkkijonona
     */
    public String getPelipaikka() {
        return pelipaikka;
    }
    
    
    /** Asetetaan pelaajalle uusi pelipaikka
     * @param uusipaikka pelipaikka merkkijonona
     */
    public void setPelipaikka(String uusipaikka) {
        pelipaikka = uusipaikka;
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
     * Asetetaan pelaajalle nimi
     * @param uusinimi asetettava nimi
     */
    public void setNimi(String uusinimi) {
        nimi = uusinimi;
    }
    
    
    /**
     * Palauttaa pelaajan pelinumeron.
     * @return pelaajan pelinumero
     */
    public int getPelinumero() {
        return pelinumero;
    }
    
    
    /**
     * Asetetaan pelaajalle pelinumero
     * @param uusinumero pelaajan uusi numero
     */
    public void setPelinumero(int uusinumero) {
        pelinumero = uusinumero;
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
     *  Pelaaja pelaaja2 = new Pelaaja();
     *  pelaaja2.parse("1   |1     |2          |Eemil\\|Tervaportti |Passari        | ");
     *  pelaaja2.getKentta(3) === "Eemil|Tervaportti"; 
     *  pelaaja2.toString() === "1|1|2|Eemil\\|Tervaportti|Passari|";
     *  
     *  Pelaaja pelaaja3 = new Pelaaja();
     *  pelaaja3.parse("1   |1     |2          |Eemil\\|Tervaportti |Pass\\|ari        | ");
     *  pelaaja3.getKentta(3) === "Eemil|Tervaportti"; 
     *  pelaaja3.getKentta(4) === "Pass|ari";
     *  pelaaja3.toString() === "1|1|2|Eemil\\|Tervaportti|Pass\\|ari|";
     * </pre>
     */
    public void parse(String tiedot) {
      StringBuilder sb = new StringBuilder(tiedot);
      for (int k = 0; k < getKenttia(); k++) {
          if (sb.indexOf("\\") < 0 || sb.indexOf("\\") > sb.indexOf("|")) {
              aseta(k, Mjonot.erota(sb, '|'));
          } else {
              String s = Mjonot.erota(sb, '\\') + "|" + Mjonot.erota(sb, '|') +  Mjonot.erota(sb, '|');
              aseta(k, s);
          }
      }           
    }
    
    
    /**
     * Palauttaa pelaajan tiedot merkkijonona jonka voi tallettaa tiedostoon
     * @example
     * <pre name="test">
     *  Pelaaja pelaaja = new Pelaaja();
     *  pelaaja.parse("1   |1     |2          |Eemil Tervaportti |Passari        | ");
     *  pelaaja.toString() === "1|1|2|Eemil Tervaportti|Passari|";
     *  
     *  Pelaaja pelaaja2 = new Pelaaja();
     *  pelaaja2.aseta(3, "nimi|viivalla"); pelaaja2.aseta(4, "pelipaikka|viivalla");
     *  pelaaja2.toString() === "0|0|0|nimi\\|viivalla|pelipaikka\\|viivalla|";
     *  
     *  Pelaaja pelaaja3 = new Pelaaja();
     *  pelaaja3.aseta(3, "nimi|viivalla"); ;
     *  pelaaja3.toString() === "0|0|0|nimi\\|viivalla||";
     *  
     *  Pelaaja pelaaja4 = new Pelaaja();
     *  pelaaja4.aseta(4, "pelipaikka|viivalla");
     *  pelaaja4.toString() === "0|0|0||pelipaikka\\|viivalla|";
     * </pre>
     */
    @Override
    public String toString() {
        if (nimi.indexOf('|')>-1) {
            String n = nimi.substring(0, nimi.indexOf('|') )
                    + "\\"
                    + nimi.substring(nimi.indexOf('|') );
            if (pelipaikka.indexOf('|')>-1) {
                String p = pelipaikka.substring(0, pelipaikka.indexOf('|') )
                        + "\\"
                        + pelipaikka.substring(pelipaikka.indexOf('|') );
                return tunnusNro + "|" + jId + "|" + pelinumero + "|" + n + "|" + p + "|";
            }
            return tunnusNro + "|" + jId + "|" + pelinumero + "|" + n + "|" + pelipaikka + "|";
        }
        if (pelipaikka.indexOf('|')>-1) {
            String p = pelipaikka.substring(0, pelipaikka.indexOf('|') )
                    + "\\"
                    + pelipaikka.substring(pelipaikka.indexOf('|') );
            return tunnusNro + "|" + jId + "|" + pelinumero + "|" + nimi + "|" + p + "|";
        }

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
