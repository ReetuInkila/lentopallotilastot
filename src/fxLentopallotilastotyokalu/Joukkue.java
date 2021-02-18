package fxLentopallotilastotyokalu;

/**
 * Luokka joukkueelle
 * @author RInkila
 * @version Feb 18, 2021
 *
 */
public class Joukkue {
    private String nimi;
    
    /** 
     * @param nimi joukkueen nimi
     */
    public Joukkue(String nimi) {
        this.nimi = nimi;
    }

    /** Antaa joukkueen nimen
     * @return joukkueen nimi merkkijonona
     */
    public String getNimi() {
        return this.nimi;
    }
}
