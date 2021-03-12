/**
 * 
 */
package lentopallotilastotyokalu;

import java.util.Arrays;

/**
 * @author Reetu Inkil‰
 * @version Feb 23, 2021
 *
 */
public class Pelaajat {

    private static final int Max_PELAAJIA = 20;
    private int lkm = 0;
    private String tiedostonNimi = "pelaajat.dat";
    private Pelaaja[] alkiot = new Pelaaja[Max_PELAAJIA];
    
    /**
     * Tyhj‰ muodostaja
     */
    public Pelaajat() {
        // Atribuutit alustettu
    }
    
    /**
     * Lis‰‰ uuden pelaajan tietorakenteeseen.  Ottaa pelaajan omistukseensa.
     * @param pelaaja lis‰tt‰v‰n pelaajan viite.  Huom tietorakenne muuttuu omistajaksi
     * @throws SailoException jos tietorakenne on jo t‰ynn‰
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
        this.alkiot[this.lkm] = pelaaja;
        lkm++;
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
     * Lukee pelaajiston tiedostosta.  KESKEN
     * @param hakemisto tiedoston hakemisto
     * @throws SailoException jos lukeminen ep‰onnistuu
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        tiedostonNimi = hakemisto + "/pelaajat.dat";
        throw new SailoException("Ei osata viel‰ lukea tiedostoa " + tiedostonNimi);
    }

    
    /**
     * Tallentaa pelaajiston tiedostoon.  KESKEN
     * @throws SailoException jos talletus ep‰onnistuu
     */
    public void talleta() throws SailoException {
        throw new SailoException("Ei osata viel‰ tallettaa tiedostoa " + tiedostonNimi);
    }

    
    /**
     * Palauttaa tilastotyˆkalun pelaajien lukum‰‰r‰n
     * @return j‰senten lukum‰‰r‰
     */
    public int getLkm() {
        return lkm;
    }

    /** Testiohjelma joukkueille
     * @param args ei k‰ytˆss‰
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
