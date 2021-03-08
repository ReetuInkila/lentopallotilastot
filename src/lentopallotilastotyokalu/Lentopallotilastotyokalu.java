/**
 * 
 */
package lentopallotilastotyokalu;

/**
 * -huolehtii joukkueet, Pelaajat ja Tilastot luokkien v‰lisest‰ yhteistyˆst‰ ja v‰litt‰‰ n‰it‰ tietoja  pyydett‰ess‰                            
 * -lukee ja kirjoittaa joukkueet, pelaajat ja tilastot tiedostoon pyyt‰m‰ll‰ apua avustajiltaan                    
 * @author Reetu Inkil‰
 * @version Feb 23, 2021
 *
 */
public class Lentopallotilastotyokalu {

    private final Joukkueet joukkueet = new Joukkueet();
    private final Pelaajat pelaajat = new Pelaajat();
    
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
    public void lisaaJoukkue(Joukkue joukkue) throws SailoException {
        joukkueet.lisaa(joukkue);
    }
    
    /**
     * Lis‰‰ uuden pelaajan tietorakenteeseen.  Ottaa pelaajan omistukseensa.
     * @param pelaaja lis‰t‰‰v‰n joukkueen viite.  Huom tietorakenne muuttuu omistajaksi
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
    
    /** Hakee joukkueen nimen
     * @param jId joukkueen numero mink‰ nime‰ haetaan
     * @return joukkueen nimi
     */
    public String getJNimi(int jId) {
        Joukkue joukkue = joukkueet.annaId(jId);
        return joukkue.getNimi();
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
