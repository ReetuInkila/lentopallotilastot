/**
 * 
 */
package kanta;

/**
 * @author RInkila
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
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
    // TODO Auto-generated method stub
    
    }

}
