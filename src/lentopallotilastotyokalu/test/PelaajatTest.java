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
 * @version 2021.04.08 19:16:50 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class PelaajatTest {



  // Generated by ComTest BEGIN
  /** 
   * testLisaa45 
   * @throws SailoException when error
   */
  @Test
  public void testLisaa45() throws SailoException {    // Pelaajat: 45
    Pelaajat pelaajat = new Pelaajat(); 
    Pelaaja pelimies1 = new Pelaaja(), pelimies2 = new Pelaaja(); 
    assertEquals("From: Pelaajat line: 49", 0, pelaajat.getLkm()); 
    pelaajat.lisaa(pelimies1); assertEquals("From: Pelaajat line: 50", 1, pelaajat.getLkm()); 
    pelaajat.lisaa(pelimies2); assertEquals("From: Pelaajat line: 51", 2, pelaajat.getLkm()); 
    pelaajat.lisaa(pelimies1); assertEquals("From: Pelaajat line: 52", 3, pelaajat.getLkm()); 
    assertEquals("From: Pelaajat line: 53", pelimies1, pelaajat.anna(0)); 
    assertEquals("From: Pelaajat line: 54", pelimies2, pelaajat.anna(1)); 
    assertEquals("From: Pelaajat line: 55", pelimies1, pelaajat.anna(2)); 
    assertEquals("From: Pelaajat line: 56", false, pelaajat.anna(1) == pelimies1); 
    assertEquals("From: Pelaajat line: 57", true, pelaajat.anna(1) == pelimies2); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testPoista73 
   * @throws SailoException when error
   */
  @Test
  public void testPoista73() throws SailoException {    // Pelaajat: 73
    Pelaajat pelaajat = new Pelaajat(); 
    Pelaaja peluri1 = new Pelaaja(), peluri2 = new Pelaaja(), peluri3 = new Pelaaja(); 
    peluri1.rekisteroi(); peluri2.rekisteroi(); peluri3.rekisteroi(); 
    int id1 = peluri1.getTunnusNro(); 
    pelaajat.lisaa(peluri1); pelaajat.lisaa(peluri2); pelaajat.lisaa(peluri3); 
    assertEquals("From: Pelaajat line: 80", 1, pelaajat.poista(id1+1)); 
    assertEquals("From: Pelaajat line: 81", null, pelaajat.annaId(id1+1)); assertEquals("From: Pelaajat line: 81", 2, pelaajat.getLkm()); 
    assertEquals("From: Pelaajat line: 82", 1, pelaajat.poista(id1)); assertEquals("From: Pelaajat line: 82", 1, pelaajat.getLkm()); 
    assertEquals("From: Pelaajat line: 83", 0, pelaajat.poista(id1+3)); assertEquals("From: Pelaajat line: 83", 1, pelaajat.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testEtsiId116 
   * @throws SailoException when error
   */
  @Test
  public void testEtsiId116() throws SailoException {    // Pelaajat: 116
    Pelaajat pelaajat = new Pelaajat(); 
    Pelaaja peluri1 = new Pelaaja(), peluri2 = new Pelaaja(), peluri3 = new Pelaaja(); 
    peluri1.rekisteroi(); peluri2.rekisteroi(); peluri3.rekisteroi(); 
    int id1 = peluri1.getTunnusNro(); 
    pelaajat.lisaa(peluri1); pelaajat.lisaa(peluri2); pelaajat.lisaa(peluri3); 
    assertEquals("From: Pelaajat line: 123", 1, pelaajat.etsiId(id1+1)); 
    assertEquals("From: Pelaajat line: 124", 2, pelaajat.etsiId(id1+2)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testLueTiedostosta169 
   * @throws SailoException when error
   */
  @Test
  public void testLueTiedostosta169() throws SailoException {    // Pelaajat: 169
    Pelaajat pelaajat = new Pelaajat(); 
    Pelaaja pelaaja1 = new Pelaaja(), pelaaja2 = new Pelaaja(); 
    pelaaja1.rekisteroi(); 
    pelaaja2.rekisteroi(); 
    pelaaja1.taytaEsimerkkiTiedoilla(); 
    pelaaja2.taytaEsimerkkiTiedoilla(); 
    String testiTiedosto = "testipelaajat"; 
    File ftied = new File(testiTiedosto+".dat"); 
    ftied.delete(); 
    try {
    pelaajat.lueTiedostosta(testiTiedosto); 
    fail("Pelaajat: 182 Did not throw SailoException");
    } catch(SailoException _e_){ _e_.getMessage(); }
    pelaajat.lisaa(pelaaja1); 
    pelaajat.lisaa(pelaaja2); 
    pelaajat.tallenna(); 
    pelaajat = new Pelaajat();  // Poistetaan vanhat luomalla uusi
    pelaajat.lueTiedostosta(testiTiedosto);  // johon ladataan tiedot tiedostosta.
    Iterator<Pelaaja> i = pelaajat.iterator(); 
    assertEquals("From: Pelaajat line: 189", pelaaja1.toString(), i.next().toString()); 
    assertEquals("From: Pelaajat line: 190", pelaaja2.toString(), i.next().toString()); 
    assertEquals("From: Pelaajat line: 191", false, i.hasNext()); 
    pelaajat.lisaa(pelaaja2); 
    pelaajat.tallenna(); 
    assertEquals("From: Pelaajat line: 194", true, ftied.delete()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testPelaajatIterator272 
   * @throws SailoException when error
   */
  @Test
  public void testPelaajatIterator272() throws SailoException {    // Pelaajat: 272
    Pelaajat pelaajat = new Pelaajat(); 
    Pelaaja pelaaja1 = new Pelaaja(), pelaaja2 = new Pelaaja(); 
    pelaaja1.rekisteroi(); pelaaja2.rekisteroi(); 
    pelaajat.lisaa(pelaaja1); 
    pelaajat.lisaa(pelaaja2); 
    pelaajat.lisaa(pelaaja1); 
    StringBuffer ids = new StringBuffer(30); 
    for (Pelaaja pelaaja:pelaajat) // Kokeillaan for-silmukan toimintaa
    ids.append(" "+pelaaja.getTunnusNro()); 
    String tulos = " " + pelaaja1.getTunnusNro() + " " + pelaaja2.getTunnusNro() + " " + pelaaja1.getTunnusNro(); 
    assertEquals("From: Pelaajat line: 291", tulos, ids.toString()); 
    ids = new StringBuffer(30); 
    for (Iterator<Pelaaja>  i=pelaajat.iterator(); i.hasNext(); ) { // ja iteraattorin toimintaa
    Pelaaja pelaaja = i.next(); 
    ids.append(" "+pelaaja.getTunnusNro()); 
    }
    assertEquals("From: Pelaajat line: 299", tulos, ids.toString()); 
    Iterator<Pelaaja>  i=pelaajat.iterator(); 
    assertEquals("From: Pelaajat line: 302", true, i.next() == pelaaja1); 
    assertEquals("From: Pelaajat line: 303", true, i.next() == pelaaja2); 
    assertEquals("From: Pelaajat line: 304", true, i.next() == pelaaja1); 
    try {
    i.next(); 
    fail("Pelaajat: 306 Did not throw NoSuchElementException");
    } catch(NoSuchElementException _e_){ _e_.getMessage(); }
  } // Generated by ComTest END
}