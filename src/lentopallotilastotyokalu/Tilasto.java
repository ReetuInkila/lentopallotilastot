package lentopallotilastotyokalu;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * tiet‰‰ tilaston kent‰t, osaa tarkistaa tietyn kent‰n oikeellisuuden, osaa muuttaa 3|26.1.2021|Karvapallot|0|1|... merkkijonon tilaston tiedoiksi, 
 * osaa antaa merkkijonona i:n kent‰n tiedot ja osaa laittaa merkkijonon i:neksi kent‰ksi
 * @author Reetu Inkila
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
        // Viel‰ ei tarvita mit‰‰n
    }


    /**
     * Alustetaan tietyn pelaajan tilasto.  
     * @param pId pelaajan id numero 
     */
    public Tilasto(int pId) {
        this.pId= pId;
    }


    /**
     * T‰ytt‰‰ tilastoluokkaan arvotun suoritteen luokan testaus tarpeeseen
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


    
    /** Testiohjelma tilastolle
     * @param args ei k‰ytˆss‰
     */
    public static void main(String[] args) {
        Tilasto til = new Tilasto();
        til.taytaEsimerkkiTiedoilla(2);
        til.tulosta(System.out);
    }


   
}
