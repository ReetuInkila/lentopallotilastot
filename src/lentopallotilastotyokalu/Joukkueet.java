/**
 * 
 */
package lentopallotilastotyokalu;

/** Luokka Joukkueet
 * @author RInkila
 * @version Feb 22, 2021
 *
 */
public class Joukkueet {
    
    private static final int Max_JOUKKUEITA = 10;
    private int lkm = 0;
    private Joukkue[] alkiot;
    
    /**
     * Luodaan alustava taulukko
     */
    public Joukkueet() {
        alkiot = new Joukkue[Max_JOUKKUEITA];
    }
    
    /**
     * Lis‰‰ uuden joukkueen tietorakenteeseen.  Ottaa joukkueen omistukseensa.
     * @param joukkue lis‰t‰‰v‰n joukkueen viite.  Huom tietorakenne muuttuu omistajaksi
     * @throws SailoException jos tietorakenne on jo t‰ynn‰
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * Joukkueet joukkueet = new Joukkueet();
     * Joukkue tiimi1 = new Joukkue(), tiimi2 = new Joukkue();
     * joukkueet.getLkm() === 0;
     * joukkueet.lisaa(tiimi1); joukkueet.getLkm() === 1;
     * joukkueet.lisaa(tiimi2); joukkueet.getLkm() === 2;
     * joukkueet.lisaa(tiimi1); joukkueet.getLkm() === 3;
     * joukkueet.anna(0) === tiimi1;
     * joukkueet.anna(1) === tiimi2;
     * joukkueet.anna(2) === tiimi1;
     * joukkueet.anna(1) == tiimi1 === false;
     * joukkueet.anna(1) == tiimi2 === true;
     * joukkueet.anna(3) === tiimi1; #THROWS IndexOutOfBoundsException 
     * joukkueet.lisaa(tiimi1); joukkueet.getLkm() === 4;
     * joukkueet.lisaa(tiimi1); joukkueet.getLkm() === 5;
     * joukkueet.lisaa(tiimi1);  #THROWS SailoException
     * </pre>
     */
    public void lisaa(Joukkue joukkue) throws SailoException {
        if (lkm >= alkiot.length) throw new SailoException("Liikaa alkioita");
        this.alkiot[this.lkm] = joukkue;
        lkm++;
    }
    
    /**
     * Palauttaa viitteen i:teen joukkueeseen.
     * @param i monennenko joukkueen viite halutaan
     * @return viite joukkueeseen, jonka indeksi on i
     * @throws IndexOutOfBoundsException jos i ei ole sallitulla alueella  
     */
    public Joukkue anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || lkm <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return alkiot[i];
    }
    
    /**
     * Palauttaa tilastotyˆkalun joukkueiden lukum‰‰r‰n
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
