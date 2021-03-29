package lentopallotilastotyokalu.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import lentopallotilastotyokalu.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.03.19 08:52:43 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class LentopallotilastotyokaluTest {



  // Generated by ComTest BEGIN
  /** 
   * testLisaaJoukkue52 
   * @throws SailoException when error
   */
  @Test
  public void testLisaaJoukkue52() throws SailoException {    // Lentopallotilastotyokalu: 52
    Joukkueet joukkueet = new Joukkueet(); 
    Joukkue tiimi1 = new Joukkue(), tiimi2 = new Joukkue(); 
    assertEquals("From: Lentopallotilastotyokalu line: 56", 0, joukkueet.getLkm()); 
    joukkueet.lisaa(tiimi1); assertEquals("From: Lentopallotilastotyokalu line: 57", 1, joukkueet.getLkm()); 
    joukkueet.lisaa(tiimi2); assertEquals("From: Lentopallotilastotyokalu line: 58", 2, joukkueet.getLkm()); 
    joukkueet.lisaa(tiimi1); assertEquals("From: Lentopallotilastotyokalu line: 59", 3, joukkueet.getLkm()); 
    assertEquals("From: Lentopallotilastotyokalu line: 60", tiimi1, joukkueet.anna(0)); 
    assertEquals("From: Lentopallotilastotyokalu line: 61", tiimi2, joukkueet.anna(1)); 
    assertEquals("From: Lentopallotilastotyokalu line: 62", tiimi1, joukkueet.anna(2)); 
    assertEquals("From: Lentopallotilastotyokalu line: 63", false, joukkueet.anna(1) == tiimi1); 
    assertEquals("From: Lentopallotilastotyokalu line: 64", true, joukkueet.anna(1) == tiimi2); 
    try {
    assertEquals("From: Lentopallotilastotyokalu line: 65", tiimi1, joukkueet.anna(3)); 
    fail("Lentopallotilastotyokalu: 65 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    joukkueet.lisaa(tiimi1); assertEquals("From: Lentopallotilastotyokalu line: 66", 4, joukkueet.getLkm()); 
    joukkueet.lisaa(tiimi1); assertEquals("From: Lentopallotilastotyokalu line: 67", 5, joukkueet.getLkm()); 
    joukkueet.lisaa(tiimi1); assertEquals("From: Lentopallotilastotyokalu line: 68", 6, joukkueet.getLkm()); 
    joukkueet.lisaa(tiimi1); assertEquals("From: Lentopallotilastotyokalu line: 69", 7, joukkueet.getLkm()); 
    joukkueet.lisaa(tiimi1); assertEquals("From: Lentopallotilastotyokalu line: 70", 8, joukkueet.getLkm()); 
    joukkueet.lisaa(tiimi1); assertEquals("From: Lentopallotilastotyokalu line: 71", 9, joukkueet.getLkm()); 
    joukkueet.lisaa(tiimi1); assertEquals("From: Lentopallotilastotyokalu line: 72", 10, joukkueet.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testLisaaPelaaja84 
   * @throws SailoException when error
   */
  @Test
  public void testLisaaPelaaja84() throws SailoException {    // Lentopallotilastotyokalu: 84
    Pelaajat pelaajat = new Pelaajat(); 
    Pelaaja pelimies1 = new Pelaaja(), pelimies2 = new Pelaaja(); 
    assertEquals("From: Lentopallotilastotyokalu line: 88", 0, pelaajat.getLkm()); 
    pelaajat.lisaa(pelimies1); assertEquals("From: Lentopallotilastotyokalu line: 89", 1, pelaajat.getLkm()); 
    pelaajat.lisaa(pelimies2); assertEquals("From: Lentopallotilastotyokalu line: 90", 2, pelaajat.getLkm()); 
    pelaajat.lisaa(pelimies1); assertEquals("From: Lentopallotilastotyokalu line: 91", 3, pelaajat.getLkm()); 
    assertEquals("From: Lentopallotilastotyokalu line: 92", pelimies1, pelaajat.anna(0)); 
    assertEquals("From: Lentopallotilastotyokalu line: 93", pelimies2, pelaajat.anna(1)); 
    assertEquals("From: Lentopallotilastotyokalu line: 94", pelimies1, pelaajat.anna(2)); 
    assertEquals("From: Lentopallotilastotyokalu line: 95", false, pelaajat.anna(1) == pelimies1); 
    assertEquals("From: Lentopallotilastotyokalu line: 96", true, pelaajat.anna(1) == pelimies2); 
    try {
    assertEquals("From: Lentopallotilastotyokalu line: 97", pelimies1, pelaajat.anna(3)); 
    fail("Lentopallotilastotyokalu: 97 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testLisaaTilasto109 */
  @Test
  public void testLisaaTilasto109() {    // Lentopallotilastotyokalu: 109
    Tilastot tilastot = new Tilastot(); 
    Tilasto suorite1 = new Tilasto(5), suorite2 = new Tilasto(8); 
    assertEquals("From: Lentopallotilastotyokalu line: 115", 0, tilastot.getLkm()); 
    tilastot.lisaa(suorite1); assertEquals("From: Lentopallotilastotyokalu line: 116", 1, tilastot.getLkm()); 
    tilastot.lisaa(suorite2); assertEquals("From: Lentopallotilastotyokalu line: 117", 2, tilastot.getLkm()); 
    tilastot.lisaa(suorite1); assertEquals("From: Lentopallotilastotyokalu line: 118", 3, tilastot.getLkm()); 
    List<Tilasto> loytyneet; 
    loytyneet = tilastot.annaTilastot(0); 
    assertEquals("From: Lentopallotilastotyokalu line: 122", 0, loytyneet.size()); 
    loytyneet = tilastot.annaTilastot(5); 
    assertEquals("From: Lentopallotilastotyokalu line: 124", 2, loytyneet.size()); 
    assertEquals("From: Lentopallotilastotyokalu line: 125", true, loytyneet.get(0) == suorite1); 
    assertEquals("From: Lentopallotilastotyokalu line: 126", true, loytyneet.get(1) == suorite1); 
    loytyneet = tilastot.annaTilastot(8); 
    assertEquals("From: Lentopallotilastotyokalu line: 128", 1, loytyneet.size()); 
    assertEquals("From: Lentopallotilastotyokalu line: 129", true, loytyneet.get(0) == suorite2); 
  } // Generated by ComTest END
}