package lentopallotilastotyokalu.test;
// Generated by ComTest BEGIN
import java.io.File;
import lentopallotilastotyokalu.*;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.04.13 08:03:39 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class JoukkueetTest {



  // Generated by ComTest BEGIN
  /** 
   * testLisaa45 
   * @throws SailoException when error
   */
  @Test
  public void testLisaa45() throws SailoException {    // Joukkueet: 45
    Joukkueet joukkueet = new Joukkueet(); 
    Joukkue tiimi1 = new Joukkue(), tiimi2 = new Joukkue(); 
    assertEquals("From: Joukkueet line: 49", 0, joukkueet.getLkm()); 
    joukkueet.lisaa(tiimi1); assertEquals("From: Joukkueet line: 50", 1, joukkueet.getLkm()); 
    joukkueet.lisaa(tiimi2); assertEquals("From: Joukkueet line: 51", 2, joukkueet.getLkm()); 
    joukkueet.lisaa(tiimi1); assertEquals("From: Joukkueet line: 52", 3, joukkueet.getLkm()); 
    assertEquals("From: Joukkueet line: 53", tiimi1, joukkueet.anna(0)); 
    assertEquals("From: Joukkueet line: 54", tiimi2, joukkueet.anna(1)); 
    assertEquals("From: Joukkueet line: 55", tiimi1, joukkueet.anna(2)); 
    assertEquals("From: Joukkueet line: 56", false, joukkueet.anna(1) == tiimi1); 
    assertEquals("From: Joukkueet line: 57", true, joukkueet.anna(1) == tiimi2); 
    try {
    assertEquals("From: Joukkueet line: 58", tiimi1, joukkueet.anna(3)); 
    fail("Joukkueet: 58 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    joukkueet.lisaa(tiimi1); assertEquals("From: Joukkueet line: 59", 4, joukkueet.getLkm()); 
    joukkueet.lisaa(tiimi1); assertEquals("From: Joukkueet line: 60", 5, joukkueet.getLkm()); 
    joukkueet.lisaa(tiimi1); assertEquals("From: Joukkueet line: 61", 6, joukkueet.getLkm()); 
    joukkueet.lisaa(tiimi1); assertEquals("From: Joukkueet line: 62", 7, joukkueet.getLkm()); 
    joukkueet.lisaa(tiimi1); assertEquals("From: Joukkueet line: 63", 8, joukkueet.getLkm()); 
    joukkueet.lisaa(tiimi1); assertEquals("From: Joukkueet line: 64", 9, joukkueet.getLkm()); 
    joukkueet.lisaa(tiimi1); assertEquals("From: Joukkueet line: 65", 10, joukkueet.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testPoista81 
   * @throws SailoException when error
   */
  @Test
  public void testPoista81() throws SailoException {    // Joukkueet: 81
    Joukkueet joukkueet = new Joukkueet(); 
    Joukkue joukkue1 = new Joukkue(), joukkue2 = new Joukkue(); 
    joukkue1.rekisteroi(); 
    joukkue2.rekisteroi(); 
    joukkue1.taytaPuulaakiTiedoilla(); 
    joukkue2.taytaPuulaakiTiedoilla(); 
    joukkueet.lisaa(joukkue1); joukkueet.lisaa(joukkue2); 
    int id1 = joukkue1.getTunnusNro(); 
    assertEquals("From: Joukkueet line: 91", 1, joukkueet.poista(id1+1)); 
    assertEquals("From: Joukkueet line: 92", -1, joukkueet.etsiId(id1+1)); assertEquals("From: Joukkueet line: 92", 1, joukkueet.getLkm()); 
    assertEquals("From: Joukkueet line: 93", 1, joukkueet.poista(id1)); assertEquals("From: Joukkueet line: 93", 0, joukkueet.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testEtsiId113 
   * @throws SailoException when error
   */
  @Test
  public void testEtsiId113() throws SailoException {    // Joukkueet: 113
    Joukkueet joukkueet = new Joukkueet(); 
    Joukkue joukkue1 = new Joukkue(), joukkue2 = new Joukkue(); 
    joukkue1.rekisteroi(); 
    joukkue2.rekisteroi(); 
    joukkue1.taytaPuulaakiTiedoilla(); 
    joukkue2.taytaPuulaakiTiedoilla(); 
    joukkueet.lisaa(joukkue1); joukkueet.lisaa(joukkue2); 
    int id1 = joukkue1.getTunnusNro(); 
    assertEquals("From: Joukkueet line: 123", 1, joukkueet.etsiId(id1+1)); 
    assertEquals("From: Joukkueet line: 124", -1, joukkueet.etsiId(id1+2)); 
    assertEquals("From: Joukkueet line: 125", 0, joukkueet.etsiId(id1)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testLueTiedostosta152 
   * @throws SailoException when error
   */
  @Test
  public void testLueTiedostosta152() throws SailoException {    // Joukkueet: 152
    Joukkueet joukkueet = new Joukkueet(); 
    Joukkue joukkue1 = new Joukkue(), joukkue2 = new Joukkue(); 
    joukkue1.rekisteroi(); 
    joukkue2.rekisteroi(); 
    joukkue1.taytaPuulaakiTiedoilla(); 
    joukkue2.taytaPuulaakiTiedoilla(); 
    String testiTiedosto = "testijoukkueet"; 
    File ftied = new File(testiTiedosto+".dat"); 
    ftied.delete(); 
    try {
    joukkueet.lueTiedostosta(testiTiedosto); 
    fail("Joukkueet: 165 Did not throw SailoException");
    } catch(SailoException _e_){ _e_.getMessage(); }
    joukkueet.lisaa(joukkue1); 
    joukkueet.lisaa(joukkue2); 
    joukkueet.tallenna(); 
    joukkueet = new Joukkueet();  // Poistetaan vanhat luomalla uusi
    joukkueet.lueTiedostosta(testiTiedosto);  // johon ladataan tiedot tiedostosta.
    Iterator<Joukkue> i = joukkueet.iterator(); 
    assertEquals("From: Joukkueet line: 172", joukkue1.toString(), i.next().toString()); 
    assertEquals("From: Joukkueet line: 173", joukkue2.toString(), i.next().toString()); 
    assertEquals("From: Joukkueet line: 174", false, i.hasNext()); 
    joukkueet.lisaa(joukkue2); 
    joukkueet.tallenna(); 
    assertEquals("From: Joukkueet line: 177", true, ftied.delete()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testJoukkueetIterator262 
   * @throws SailoException when error
   */
  @Test
  public void testJoukkueetIterator262() throws SailoException {    // Joukkueet: 262
    Joukkueet joukkueet = new Joukkueet(); 
    Joukkue tiimi1 = new Joukkue(), tiimi2 = new Joukkue(); 
    tiimi1.rekisteroi(); tiimi2.rekisteroi(); 
    joukkueet.lisaa(tiimi1); 
    joukkueet.lisaa(tiimi2); 
    joukkueet.lisaa(tiimi1); 
    StringBuffer ids = new StringBuffer(30); 
    for (Joukkue joukkue:joukkueet) // Kokeillaan for-silmukan toimintaa
    ids.append(" "+joukkue.getTunnusNro()); 
    String tulos = " " + tiimi1.getTunnusNro() + " " + tiimi2.getTunnusNro() + " " + tiimi1.getTunnusNro(); 
    assertEquals("From: Joukkueet line: 281", tulos, ids.toString()); 
    ids = new StringBuffer(30); 
    for (Iterator<Joukkue>  i=joukkueet.iterator(); i.hasNext(); ) { // ja iteraattorin toimintaa
    Joukkue joukkue = i.next(); 
    ids.append(" "+joukkue.getTunnusNro()); 
    }
    assertEquals("From: Joukkueet line: 289", tulos, ids.toString()); 
    Iterator<Joukkue>  i=joukkueet.iterator(); 
    assertEquals("From: Joukkueet line: 292", true, i.next() == tiimi1); 
    assertEquals("From: Joukkueet line: 293", true, i.next() == tiimi2); 
    assertEquals("From: Joukkueet line: 294", true, i.next() == tiimi1); 
    try {
    i.next(); 
    fail("Joukkueet: 296 Did not throw NoSuchElementException");
    } catch(NoSuchElementException _e_){ _e_.getMessage(); }
  } // Generated by ComTest END
}