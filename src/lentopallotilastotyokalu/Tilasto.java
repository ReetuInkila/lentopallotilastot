package lentopallotilastotyokalu;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * tietää tilaston kentät, osaa tarkistaa tietyn kentän oikeellisuuden, osaa muuttaa 3|26.1.2021|Karvapallot|0|1|... merkkijonon tilaston tiedoiksi, 
 * osaa antaa merkkijonona i:n kentän tiedot ja TODO: osaa laittaa merkkijonon i:neksi kentäksi
 * @author Reetu Inkilä
 * @version Mar 8, 2021
 *
 */
public class Tilasto {
    private int pId;
    private String paiva;
    private String vastustaja;
    private int syotto;
    private int assa;
    private int nosto;
    private int piste;
    private int virhe;
    
    
    /**
     * Alustetaan tilasto ilman parametreja
     */
    public Tilasto() {
        // Vielä ei tarvita mitään
    }


    /**
     * Alustetaan tietyn pelaajan tilasto.  
     * @param pId pelaajan id numero 
     */
    public Tilasto(int pId) {
        this.pId= pId;
    }
    
    
    /** 
     * Antaa k:n kentän sisällön merkkijonona 
     * @param k monenenko kentän sisältö palautetaan 
     * @return kentän sisältö merkkijonona 
     */ 
    public String getKentta(int k) { 
        switch ( k ) { 
        case 0: return "" + pId; 
        case 1: return "" + paiva; 
        case 2: return "" + vastustaja;
        case 3: return "" + syotto;
        case 4: return "" + assa;
        case 5: return "" + nosto;
        case 6: return "" + piste;
        case 7: return "" + virhe;
        default: return "Ei toimi"; 
        } 
    }
    
    
    /**
     * Palauttaa k:tta jäsenen kenttää vastaavan kysymyksen
     * @param k kuinka monennen kentän kysymys palautetaan (0-alkuinen)
     * @return k:netta kenttää vastaava kysymys
     */
    public String getKysymys(int k) {
        switch ( k ) {
        case 0: return "pId";
        case 1: return "Päivä"; 
        case 2: return "Vastustaja";
        case 3: return "Syötöt";
        case 4: return "Ässät";
        case 5: return "Nostot";
        case 6: return "Pisteet";
        case 7: return "Virheet";
        default: return "Ei toimi";
        }
    }
    
    
    /**
     * Palauttaa tilaston kenttien lukumäärän
     * @return kenttien lukumäärä
     */
    public int getKenttia() {
        return 8;
    }


    /**
     * Eka kenttä joka on mielekäs kysyttäväksi
     * @return ekan kentän indeksi
     */
    public int ekaKentta() {
        return 1;
    }
    
    
    /**
     * Alustetaan tietyn pelaajan tilasto.  
     * @param pId pelaajan id numero 
     * @param paiva tilastoon asetettu päivä
     * @param vastus Vastustaja joukkueen nimi
     * @param suorite 1 jos syöttö, 2 jos ässä, 3 jos nosto, 4 jos piste, 5, jos virhe
     * @example
     * <pre name="test">
     * Tilasto suorite = new Tilasto(1, "22.12.2121", "Puulaaki", "Ässä");
     * Tilasto suorite2 = new Tilasto(3, "22.12.2121","VaLePa", "Piste");
     * suorite.toString() === "1|22.12.2121|Puulaaki|0|1|0|0|0|";
     * suorite2.toString() === "3|22.12.2121|VaLePa|0|0|0|1|0|";
     * </pre>
     */
    public Tilasto(int pId, String paiva, String vastus, String suorite) {
        this.pId= pId;
        this.paiva = paiva;
        this.vastustaja = vastus;
        if (suorite.equals("Syöttö")) syotto = 1;
        if (suorite.equals("Ässä")) assa= 1;
        if (suorite.equals("Nosto")) nosto = 1;
        if (suorite.equals("Piste")) piste = 1;
        if (suorite.equals("Virhe")) virhe = 1;   
    }


    /**
     * Täyttää tilastoluokkaan arvotun suoritteen luokan testaus tarpeeseen
     * @param Id pelaajan id jolle tilasto luodaan
     */
    public void taytaEsimerkkiTiedoilla(int Id) {
        pId= Id;
        paiva = "08.03.2021";
        vastustaja = "Karvapallot";
        int suorite = kanta.SatunnaisNimi.rand(0,4);
        if (suorite == 0 ) syotto = 1;
        if (suorite == 1 ) assa   = 1;
        if (suorite == 2 ) nosto  = 1;
        if (suorite == 3 ) piste  = 1;
        if (suorite == 4 ) virhe  = 1;
    }
    
    
    /**
     * Tulostetaan tilaston tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(paiva + " " + vastustaja + " " + syotto + " " + assa + " " + nosto + " " + piste + " " + virhe);
    }

    
    /**
     * Tulostetaan tilaston tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /** Palautetaan mille pelaajalle tilasto kuuluu
     * @return pelaajan id kelle tilasto kuuluu
     */
    public int getPelaajaId() {
        return pId;
    }
    
    
    /** Palauttaa tilastoon talletetun suoritteen merkkijonona
     * @return Suorite kuten Nosto tai Ässä
     */
    public String getSuorite() {
        if (syotto == 1 ) return "Syöttö";
        if (assa == 1 ) return "Ässä";
        if (nosto == 1 ) return "Nosto";
        if (piste == 1 ) return "Piste";
        if (virhe == 1 ) return "Virhe";
        return null;
    }
    
    
    /**
     * Palauttaa tilaston tiedot merkkijonona jonka voi tallettaa tiedostoon
     * @example
     * <pre name="test">
     *  Tilasto tilasto = new Tilasto();
     *  tilasto.parse("4   |26.1.2021    |Karvapallot   |1     |0      |0     |0      |0      |");
     *  tilasto.toString() === "4|26.1.2021|Karvapallot|1|0|0|0|0|";
     * </pre>
     */
    @Override
    public String toString() {
        return pId + "|" + paiva + "|" + vastustaja + "|" + syotto + "|" + assa + "|" + nosto + "|" + piste + "|" + virhe + "|";
      
    }

    
    /** Selvitää tilaston tiedot | erotellusta merkkijonosta
     * @param tiedot tilastojen tiedostosta luettu rivi merkkijonona
     */
    public void parse(String tiedot) {
      StringBuilder sb = new StringBuilder(tiedot);
      pId = Mjonot.erota(sb, '|', pId);
      paiva = Mjonot.erota(sb, '|', paiva);
      vastustaja = Mjonot.erota(sb, '|', vastustaja);
      syotto = Mjonot.erota(sb, '|', syotto);
      assa = Mjonot.erota(sb, '|', assa);
      nosto = Mjonot.erota(sb, '|', nosto);
      piste = Mjonot.erota(sb, '|', piste);
      virhe = Mjonot.erota(sb, '|', virhe);
    }  

    
    /** Testiohjelma tilastolle
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Tilasto til = new Tilasto();
        til.taytaEsimerkkiTiedoilla(2);
        til.tulosta(System.out);
    }
}
