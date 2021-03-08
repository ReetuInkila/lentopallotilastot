/**
 * 
 */
package kanta;

/** Luokka satunnaisten numeroiden ja pelipaikkojen luomiseen
 * @author Reetu Inkilä
 * @version Feb 23, 2021
 *
 */
public class SatunnaisNimi {

    
    /**
     * Arvotaan satunnainen kokonaisluku välille [ala,yla]
     * @param ala arvonnan alaraja
     * @param yla arvonnan yläraja
     * @return satunnainen luku väliltä [ala,yla]
     */

    public static int rand(int ala, int yla) {
        double n = (yla-ala)*Math.random() + ala;
        return (int)Math.round(n);
    }
    
    /** arpoo satunnaisesti pelipaikan
     * @return pelipaikan nimi
     */
    public static String arvoPeliPaikka() {
        String[] pelipaikat = {"passari", "libero", "keskitorjuja", "yleispelaaja", "hakkuri"};
        int i = rand(0, 4);
        return pelipaikat[i];
    }
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
    // TODO Auto-generated method stub
    
    }

}
