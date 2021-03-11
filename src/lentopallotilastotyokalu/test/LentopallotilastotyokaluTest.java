package lentopallotilastotyokalu.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import lentopallotilastotyokalu.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.02.23 09:09:29 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class LentopallotilastotyokaluTest {


  // Generated by ComTest BEGIN
  /** 
   * testLisaa30 
   * @throws SailoException when error
   */
  @Test
  public void testLisaa30() throws SailoException {    // Lentopallotilastotyokalu: 30
    Joukkueet joukkueet = new Joukkueet(); 
    Joukkue tiimi1 = new Joukkue(), tiimi2 = new Joukkue(); 
    assertEquals("From: Lentopallotilastotyokalu line: 34", 0, joukkueet.getLkm()); 
    joukkueet.lisaa(tiimi1); assertEquals("From: Lentopallotilastotyokalu line: 35", 1, joukkueet.getLkm()); 
    joukkueet.lisaa(tiimi2); assertEquals("From: Lentopallotilastotyokalu line: 36", 2, joukkueet.getLkm()); 
    joukkueet.lisaa(tiimi1); assertEquals("From: Lentopallotilastotyokalu line: 37", 3, joukkueet.getLkm()); 
    assertEquals("From: Lentopallotilastotyokalu line: 38", tiimi1, joukkueet.anna(0)); 
    assertEquals("From: Lentopallotilastotyokalu line: 39", tiimi2, joukkueet.anna(1)); 
    assertEquals("From: Lentopallotilastotyokalu line: 40", tiimi1, joukkueet.anna(2)); 
    assertEquals("From: Lentopallotilastotyokalu line: 41", false, joukkueet.anna(1) == tiimi1); 
    assertEquals("From: Lentopallotilastotyokalu line: 42", true, joukkueet.anna(1) == tiimi2); 
    try {
    assertEquals("From: Lentopallotilastotyokalu line: 43", tiimi1, joukkueet.anna(3)); 
    fail("Lentopallotilastotyokalu: 43 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    joukkueet.lisaa(tiimi1); assertEquals("From: Lentopallotilastotyokalu line: 44", 4, joukkueet.getLkm()); 
    joukkueet.lisaa(tiimi1); assertEquals("From: Lentopallotilastotyokalu line: 45", 5, joukkueet.getLkm()); 
    joukkueet.lisaa(tiimi1); assertEquals("From: Lentopallotilastotyokalu line: 46", 6, joukkueet.getLkm()); 
    joukkueet.lisaa(tiimi1); assertEquals("From: Lentopallotilastotyokalu line: 47", 7, joukkueet.getLkm()); 
    joukkueet.lisaa(tiimi1); assertEquals("From: Lentopallotilastotyokalu line: 48", 8, joukkueet.getLkm()); 
    joukkueet.lisaa(tiimi1); assertEquals("From: Lentopallotilastotyokalu line: 49", 9, joukkueet.getLkm()); 
    joukkueet.lisaa(tiimi1); assertEquals("From: Lentopallotilastotyokalu line: 50", 10, joukkueet.getLkm()); 
    try {
    joukkueet.lisaa(tiimi1); 
    fail("Lentopallotilastotyokalu: 51 Did not throw SailoException");
    } catch(SailoException _e_){ _e_.getMessage(); }
  } // Generated by ComTest END
}