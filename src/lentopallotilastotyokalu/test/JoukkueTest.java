package lentopallotilastotyokalu.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import lentopallotilastotyokalu.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.02.22 11:10:39 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class JoukkueTest {



  // Generated by ComTest BEGIN
  /** testRekisteroi39 */
  @Test
  public void testRekisteroi39() {    // Joukkue: 39
    Joukkue tiimi1 = new Joukkue(); 
    assertEquals("From: Joukkue line: 41", 0, tiimi1.getTunnusNro()); 
    tiimi1.rekisteroi(); 
    Joukkue tiimi2 = new Joukkue(); 
    tiimi2.rekisteroi(); 
    int n1 = tiimi1.getTunnusNro(); 
    int n2 = tiimi2.getTunnusNro(); 
    assertEquals("From: Joukkue line: 47", n2-1, n1); 
  } // Generated by ComTest END
}