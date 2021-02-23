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
     * Arvotaan satunnainen kokonaisluku v�lille [ala,yla]
     * @param ala arvonnan alaraja
     * @param yla arvonnan yl�raja
     * @return satunnainen luku v�lilt� [ala,yla]
     */

    public static int rand(int ala, int yla) {
        double n = (yla-ala)*Math.random() + ala;
        return (int)Math.round(n);
    }
    
    /**
     * @param args ei k�yt�ss�
     */
    public static void main(String[] args) {
    // TODO Auto-generated method stub
    
    }

}
