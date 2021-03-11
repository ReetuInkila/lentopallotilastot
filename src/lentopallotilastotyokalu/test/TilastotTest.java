package lentopallotilastotyokalu.test;
// Generated by ComTest BEGIN
import lentopallotilastotyokalu.*;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.03.11 08:46:35 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class TilastotTest {



  // Generated by ComTest BEGIN
  /** testIterator68 */
  @Test
  public void testIterator68() {    // Tilastot: 68
    Tilastot tilastoidut = new Tilastot(); 
    Tilasto tilasto21 = new Tilasto(2); tilastoidut.lisaa(tilasto21); 
    Tilasto tilasto11 = new Tilasto(1); tilastoidut.lisaa(tilasto11); 
    Tilasto tilasto22 = new Tilasto(2); tilastoidut.lisaa(tilasto22); 
    Tilasto tilasto12 = new Tilasto(1); tilastoidut.lisaa(tilasto12); 
    Tilasto tilasto23 = new Tilasto(2); tilastoidut.lisaa(tilasto23); 
    Iterator<Tilasto> i2=tilastoidut.iterator(); 
    assertEquals("From: Tilastot line: 80", tilasto21, i2.next()); 
    assertEquals("From: Tilastot line: 81", tilasto11, i2.next()); 
    assertEquals("From: Tilastot line: 82", tilasto22, i2.next()); 
    assertEquals("From: Tilastot line: 83", tilasto12, i2.next()); 
    assertEquals("From: Tilastot line: 84", tilasto23, i2.next()); 
    try {
    assertEquals("From: Tilastot line: 85", tilasto12, i2.next()); 
    fail("Tilastot: 85 Did not throw NoSuchElementException");
    } catch(NoSuchElementException _e_){ _e_.getMessage(); }
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaTilastot98 */
  @Test
  public void testAnnaTilastot98() {    // Tilastot: 98
    Tilastot tilastoidut = new Tilastot(); 
    Tilasto tilasto21 = new Tilasto(2); tilastoidut.lisaa(tilasto21); 
    Tilasto tilasto11 = new Tilasto(1); tilastoidut.lisaa(tilasto11); 
    Tilasto tilasto22 = new Tilasto(2); tilastoidut.lisaa(tilasto22); 
    Tilasto tilasto12 = new Tilasto(1); tilastoidut.lisaa(tilasto12); 
    Tilasto tilasto23 = new Tilasto(2); tilastoidut.lisaa(tilasto23); 
    Tilasto tilasto51 = new Tilasto(5); tilastoidut.lisaa(tilasto51); 
    List<Tilasto> loytyneet; 
    loytyneet = tilastoidut.annaTilastot(3); 
    assertEquals("From: Tilastot line: 111", 0, loytyneet.size()); 
    loytyneet = tilastoidut.annaTilastot(1); 
    assertEquals("From: Tilastot line: 113", 2, loytyneet.size()); 
    assertEquals("From: Tilastot line: 114", true, loytyneet.get(0) == tilasto11); 
    assertEquals("From: Tilastot line: 115", true, loytyneet.get(1) == tilasto12); 
    loytyneet = tilastoidut.annaTilastot(5); 
    assertEquals("From: Tilastot line: 117", 1, loytyneet.size()); 
    assertEquals("From: Tilastot line: 118", true, loytyneet.get(0) == tilasto51); 
  } // Generated by ComTest END
}