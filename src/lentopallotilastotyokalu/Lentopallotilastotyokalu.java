/**
 * 
 */
package lentopallotilastotyokalu;

/**
 * -huolehtii joukkueet, Pelaajat ja Tilastot luokkien v‰lisest‰ yhteistyˆst‰ ja v‰litt‰‰ n‰it‰ tietoja  pyydett‰ess‰                            
 * -lukee ja kirjoittaa joukkueet, pelaajat ja tilastot tiedostoon pyyt‰m‰ll‰ apua avustajiltaan                    
 * @author RInkila
 * @version Feb 23, 2021
 *
 */
public class Lentopallotilastotyokalu {

    private final Joukkueet joukkueet = new Joukkueet();
    
    /**
     * Palautaa joukkueiden m‰‰r‰n
     * @return joukkuem‰‰r‰
     */
    public int getJoukkueita() {
        return joukkueet.getLkm();
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
     * joukkueet.lisaa(tiimi1);  #THROWS SailoException
     * </pre>
     */
    public void lisaa(Joukkue joukkue) throws SailoException {
        joukkueet.lisaa(joukkue);
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
     * Lukee Joukkueiden nimet tiedostosta
     * @param nimi jota k‰yte‰‰n lukemisessa
     * @throws SailoException jos lukeminen ep‰onnistuu
     */
    public void lueTiedostosta(String nimi) throws SailoException {
        joukkueet.lueTiedostosta(nimi);
    }
    
    /**
     * Tallettaa joukkueiden nimet tiedostoon
     * @throws SailoException jos tallettamisessa ongelmia
     */
    public void talleta() throws SailoException {
        joukkueet.talleta();
        // TODO: yrit‰ tallettaa toinen vaikka toinen ep‰onnistuisi
    }

    /**
     * @param args ei k‰ytˆss‰
     */
    public static void main(String[] args) {
        Lentopallotilastotyokalu lentopallotilastotyokalu = new Lentopallotilastotyokalu();

        try {
            // kerho.lueTiedostosta("kelmit");

            Joukkue tiimi1 = new Joukkue(), tiimi2 = new Joukkue();
            tiimi1.rekisteroi();
            tiimi1.taytaPuulaakiTiedoilla();
            tiimi2.rekisteroi();
            tiimi2.taytaPuulaakiTiedoilla();

            lentopallotilastotyokalu.lisaa(tiimi1);
            lentopallotilastotyokalu.lisaa(tiimi2);

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
