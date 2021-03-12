package lentopallotilastotyokalu;

import java.io.OutputStream;
import java.io.PrintStream;

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
     * Alustetaan tietyn pelaajan tilasto.  
     * @param pId pelaajan id numero 
     * @param vastus Vastustaja joukkueen nimi
     * @param suorite 1 jos syöttö, 2 jos ässä, 3 jos nosto, 4 jos piste, 5, jos virhe
     * @example
     * <pre name="test">
     * Tilasto suorite = new Tilasto(1, "Puulaaki", "Ässä");
     * Tilasto suorite2 = new Tilasto(3, "VaLePa", "Piste");
     * suorite.toString() === "1 null Puulaaki 0 1 0 0 0";
     * suorite2.toString() === "3 null VaLePa 0 0 0 1 0";
     * </pre>
     */
    public Tilasto(int pId, String vastus, String suorite) {
        this.pId= pId;
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
    
    /**
     * Muuttaa tilaston tekstimuotoon
     */
    @Override
    public String toString() {
        return pId + " " + paiva + " " + vastustaja + " " + syotto + " " + assa + " " + nosto + " " + piste + " " + virhe;
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
