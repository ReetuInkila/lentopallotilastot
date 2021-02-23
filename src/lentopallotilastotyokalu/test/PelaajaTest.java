package lentopallotilastotyokalu.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import lentopallotilastotyokalu.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.02.23 12:59:51 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class PelaajaTest {



  // Generated by ComTest BEGIN
  /** testRekisteroi45 */
  @Test
  public void testRekisteroi45() {    // Pelaaja: 45
    Pelaaja peluri1 = new Pelaaja(); 
    assertEquals("From: Pelaaja line: 47", 0, peluri1.getTunnusNro()); 
    peluri1.rekisteroi(); 
    Pelaaja peluri2 = new Pelaaja(); 
    peluri2.rekisteroi(); 
    int n1 = peluri1.getTunnusNro(); 
    int n2 = peluri2.getTunnusNro(); 
    assertEquals("From: Pelaaja line: 53", n2-1, n1); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetNimi65 */
  @Test
  public void testGetNimi65() {    // Pelaaja: 65
    Pelaaja pete = new Pelaaja(); 
    pete.taytaEsimerkkiTiedoilla(); 
    { String _l_=pete.getNimi(),_r_="Pete Pelaaja .*"; if ( !_l_.matches(_r_) ) fail("From: Pelaaja line: 68" + " does not match: ["+ _l_ + "] != [" + _r_ + "]");}; 
  } // Generated by ComTest END
}