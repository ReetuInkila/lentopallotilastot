/**
 * 
 */
package lentopallotilastotyokalu;

import java.util.List;

/**
 * -huolehtii joukkueet, Pelaajat ja Tilastot luokkien v‰lisest‰ yhteistyˆst‰ ja v‰litt‰‰ n‰it‰ tietoja  pyydett‰ess‰                            
 * -lukee ja kirjoittaa joukkueet, pelaajat ja tilastot tiedostoon pyyt‰m‰ll‰ apua avustajiltaan                    
 * @author Reetu Inkil‰
 * @version Feb 23, 2021
 *
 */
public class Lentopallotilastotyokalu {

    private Joukkueet joukkueet = new Joukkueet();
    private Pelaajat pelaajat = new Pelaajat();
    private Tilastot tilastot = new Tilastot();
       
    
    /**
     * Palautaa joukkueiden m‰‰r‰n
     * @return joukkuem‰‰r‰
     */
    public int getJoukkueita() {
        return joukkueet.getLkm();
    }
    
    
    /**
     * Palautaa pelaajien m‰‰r‰n
     * @return pelaaja
     */
    public int getPelaajia() {
        return pelaajat.getLkm();
    }
    
    
    /**
     * Palautaa tilastojen m‰‰r‰n
     * @return tilastoja
     */
    public int getTilastoja() {
        return tilastot.getLkm();
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
     * joukkueet.lisaa(tiimi1); joukkueet.getLkm() === 6;
     * joukkueet.lisaa(tiimi1); joukkueet.getLkm() === 7;
     * joukkueet.lisaa(tiimi1); joukkueet.getLkm() === 8;
     * joukkueet.lisaa(tiimi1); joukkueet.getLkm() === 9;
     * joukkueet.lisaa(tiimi1); joukkueet.getLkm() === 10;
     * </pre>
     */
    public void lisaaJoukkue(Joukkue joukkue) throws SailoException {
        joukkueet.lisaa(joukkue);
    }
    
    
    /**
     * Lis‰‰ uuden pelaajan tietorakenteeseen.  Ottaa pelaajan omistukseensa.
     * @param pelaaja lis‰t‰‰v‰n pelaajan viite.  Huom tietorakenne muuttuu omistajaksi
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
     * pelaajat.anna(3) === pelimies1; #THROWS IndexOutOfBoundsException 
     * </pre>
     */
    public void lisaaPelaaja(Pelaaja pelaaja) throws SailoException {
        pelaajat.lisaa(pelaaja);
    }
    
    
    /**
     * Lis‰‰ uuden tilaston tietorakenteeseen.
     * @param tilasto lis‰t‰‰v‰n tilastoon viite
     * @throws SailoException jos tietorakenne on jo t‰ynn‰
     * @example
     * <pre name="test">
     *  #import java.util.*;
     *  
     *  #THROWS SailoException 
     *  Tilastot tilastot = new Tilastot();
     *  Tilasto suorite1 = new Tilasto(5), suorite2 = new Tilasto(8);
     *  tilastot.getLkm() === 0;
     *  tilastot.lisaa(suorite1); tilastot.getLkm() === 1;
     *  tilastot.lisaa(suorite2); tilastot.getLkm() === 2;
     *  tilastot.lisaa(suorite1); tilastot.getLkm() === 3;
     *  
     *  List<Tilasto> loytyneet;
     *  loytyneet = tilastot.annaTilastot(0);
     *  loytyneet.size() === 0;
     *  loytyneet = tilastot.annaTilastot(5);
     *  loytyneet.size() === 2; 
     *  loytyneet.get(0) == suorite1 === true;
     *  loytyneet.get(1) == suorite1 === true;
     *  loytyneet = tilastot.annaTilastot(8);
     *  loytyneet.size() === 1; 
     *  loytyneet.get(0) == suorite2 === true;
     * </pre>
     */
    public void lisaaTilasto(Tilasto tilasto) throws SailoException {
        tilastot.lisaa(tilasto);
    }
    
    
    /**
     * Palauttaa i:n joukkueen
     * @param i monesko joukkue palautetaan
     * @return viite i:teen j‰seneen
     * @throws IndexOutOfBoundsException jos i v‰‰rin
     */
    public Joukkue annaJoukkue(int i) throws IndexOutOfBoundsException {
        return joukkueet.anna(i);
    }
    
    
    /**
     * Palauttaa i:n pelaajan
     * @param i monesko pelaaja palautetaan
     * @return viite i:teen pelaajaan
     * @throws IndexOutOfBoundsException jos i v‰‰rin
     */
    public Pelaaja annaPelaaja(int i) throws IndexOutOfBoundsException {
        return pelaajat.anna(i);
    }
    
    
    /**
     * Palauttaa id:n omaava pelaajan
     * @param id jonka omaava pelaaja palautetaan
     * @return viite id:n pelaajaan
     */
    public Pelaaja annaIdPelaaja(int id){
        return pelaajat.annaId(id);
    }
    
    
    /** Palautetaan pelaajan tilastot
     * @param pelaaja pelaaja jonka tilastoja haetaan
     * @return pelaajan tilastot listana
     */
    public List<Tilasto> annaTilastot(Pelaaja pelaaja){
        return tilastot.annaTilastot(pelaaja.getTunnusNro());       
    }
    
    
    /**
     * Lukee Joukkueiden ja pelaajien tiedot tiedostosta
     * @throws SailoException jos lukeminen ep‰onnistuu
     */
    public void lueTiedostosta() throws SailoException {
        joukkueet = new Joukkueet();
        pelaajat = new Pelaajat();
        tilastot = new Tilastot();
        joukkueet.lueTiedostosta();
        pelaajat.lueTiedostosta();
        tilastot.lueTiedostosta();
    }
    
    
    /**
     * Tallettaa joukkueiden nimet, pelaajat ja tilastot tiedostoon
     * @throws SailoException jos tallettamisessa ongelmia
     */
    public void tallenna() throws SailoException {
        joukkueet.tallenna();
        pelaajat.tallenna();
        tilastot.tallenna();
    }
    
    
    /**
     * Poistaa pelaajista ja tilastoista ne joilla on pid. Kesken.
     * @param pid viitenumero, jonka mukaan poistetaan
     * @return montako j‰sent‰ poistettiin
     */
    public int poista(@SuppressWarnings("unused") int pid) {
        return 0;
    }

    
    /**
     * @param args ei k‰ytˆss‰
     */
    public static void main(String[] args) {
        Lentopallotilastotyokalu lentopallotilastotyokalu = new Lentopallotilastotyokalu();

        try {
            Joukkue tiimi1 = new Joukkue(), tiimi2 = new Joukkue();
            tiimi1.rekisteroi();
            tiimi1.taytaPuulaakiTiedoilla();
            tiimi2.rekisteroi();
            tiimi2.taytaPuulaakiTiedoilla();

            lentopallotilastotyokalu.lisaaJoukkue(tiimi1);
            lentopallotilastotyokalu.lisaaJoukkue(tiimi2);

            System.out.println("============= Lentopallotilastotyokalun testi =================");

            for (int i = 0; i < lentopallotilastotyokalu.getJoukkueita(); i++) {
                Joukkue joukkue = lentopallotilastotyokalu.annaJoukkue(i);
                System.out.println("Joukkue paikassa: " + i);
                joukkue.tulosta(System.out);
            }

        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
