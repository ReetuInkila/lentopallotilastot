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
 * @version 2021.04.08 19:16:55 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class TilastotTest {



  // Generated by ComTest BEGIN
  /** testPoistaPelaajanTilastot50 */
  @Test
  public void testPoistaPelaajanTilastot50() {    // Tilastot: 50
    Tilastot tilastot = new Tilastot(); 
    Tilasto suorite21 = new Tilasto(); suorite21.taytaEsimerkkiTiedoilla(2); 
    Tilasto suorite11 = new Tilasto(); suorite11.taytaEsimerkkiTiedoilla(1); 
    Tilasto suorite22 = new Tilasto(); suorite22.taytaEsimerkkiTiedoilla(2); 
    Tilasto suorite12 = new Tilasto(); suorite12.taytaEsimerkkiTiedoilla(1); 
    Tilasto suorite23 = new Tilasto(); suorite23.taytaEsimerkkiTiedoilla(2); 
    tilastot.lisaa(suorite21); 
    tilastot.lisaa(suorite11); 
    tilastot.lisaa(suorite22); 
    tilastot.lisaa(suorite12); 
    tilastot.lisaa(suorite23); 
    assertEquals("From: Tilastot line: 62", 3, tilastot.poistaPelaajanTilastot(2)); assertEquals("From: Tilastot line: 62", 2, tilastot.getLkm()); 
    assertEquals("From: Tilastot line: 63", 0, tilastot.poistaPelaajanTilastot(3)); assertEquals("From: Tilastot line: 63", 2, tilastot.getLkm()); 
    List<Tilasto> h = tilastot.annaTilastot(2); 
    assertEquals("From: Tilastot line: 65", 0, h.size()); 
    h = tilastot.annaTilastot(1); 
    assertEquals("From: Tilastot line: 67", suorite11, h.get(0)); 
    assertEquals("From: Tilastot line: 68", suorite12, h.get(1)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testPoistaViimeisin89 */
  @Test
  public void testPoistaViimeisin89() {    // Tilastot: 89
    Tilastot tilastot = new Tilastot(); 
    Tilasto tilasto1 = new Tilasto(), tilasto2 = new Tilasto(), tilasto3 = new Tilasto(); 
    tilasto1.taytaEsimerkkiTiedoilla(1); tilasto2.taytaEsimerkkiTiedoilla(1); tilasto3.taytaEsimerkkiTiedoilla(1); 
    tilastot.lisaa(tilasto1); tilastot.lisaa(tilasto2); tilastot.lisaa(tilasto3); 
    assertEquals("From: Tilastot line: 94", 3, tilastot.getLkm()); 
    tilastot.poistaViimeisin(); assertEquals("From: Tilastot line: 95", 2, tilastot.getLkm()); 
    tilastot.poistaViimeisin(); assertEquals("From: Tilastot line: 96", 1, tilastot.getLkm()); 
    tilastot.poistaViimeisin(); assertEquals("From: Tilastot line: 97", 0, tilastot.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testLueTiedostosta121 
   * @throws SailoException when error
   */
  @Test
  public void testLueTiedostosta121() throws SailoException {    // Tilastot: 121
    Tilastot tilastot = new Tilastot(); 
    Tilasto tilasto1 = new Tilasto(), tilasto2 = new Tilasto(); 
    tilasto1.taytaEsimerkkiTiedoilla(5); 
    tilasto2.taytaEsimerkkiTiedoilla(5); 
    String testiTiedosto = "testitilastot"; 
    File ftied = new File(testiTiedosto+".dat"); 
    ftied.delete(); 
    try {
    tilastot.lueTiedostosta(testiTiedosto); 
    fail("Tilastot: 132 Did not throw SailoException");
    } catch(SailoException _e_){ _e_.getMessage(); }
    tilastot.lisaa(tilasto1); 
    tilastot.lisaa(tilasto2); 
    tilastot.tallenna(); 
    tilastot = new Tilastot();  // Poistetaan vanhat luomalla uusi
    tilastot.lueTiedostosta(testiTiedosto);  // johon ladataan tiedot tiedostosta.
    Iterator<Tilasto> i = tilastot.iterator(); 
    assertEquals("From: Tilastot line: 139", tilasto1.toString(), i.next().toString()); 
    assertEquals("From: Tilastot line: 140", tilasto2.toString(), i.next().toString()); 
    assertEquals("From: Tilastot line: 141", false, i.hasNext()); 
    tilastot.lisaa(tilasto2); 
    tilastot.tallenna(); 
    assertEquals("From: Tilastot line: 144", true, ftied.delete()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testIterator223 */
  @Test
  public void testIterator223() {    // Tilastot: 223
    Tilastot tilastoidut = new Tilastot(); 
    Tilasto tilasto21 = new Tilasto(2); tilastoidut.lisaa(tilasto21); 
    Tilasto tilasto11 = new Tilasto(1); tilastoidut.lisaa(tilasto11); 
    Tilasto tilasto22 = new Tilasto(2); tilastoidut.lisaa(tilasto22); 
    Tilasto tilasto12 = new Tilasto(1); tilastoidut.lisaa(tilasto12); 
    Tilasto tilasto23 = new Tilasto(2); tilastoidut.lisaa(tilasto23); 
    Iterator<Tilasto> i2=tilastoidut.iterator(); 
    assertEquals("From: Tilastot line: 235", tilasto21, i2.next()); 
    assertEquals("From: Tilastot line: 236", tilasto11, i2.next()); 
    assertEquals("From: Tilastot line: 237", tilasto22, i2.next()); 
    assertEquals("From: Tilastot line: 238", tilasto12, i2.next()); 
    assertEquals("From: Tilastot line: 239", tilasto23, i2.next()); 
    try {
    assertEquals("From: Tilastot line: 240", tilasto12, i2.next()); 
    fail("Tilastot: 240 Did not throw NoSuchElementException");
    } catch(NoSuchElementException _e_){ _e_.getMessage(); }
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaTilastot255 */
  @Test
  public void testAnnaTilastot255() {    // Tilastot: 255
    Tilastot tilastoidut = new Tilastot(); 
    Tilasto tilasto21 = new Tilasto(2); tilastoidut.lisaa(tilasto21); 
    Tilasto tilasto11 = new Tilasto(1); tilastoidut.lisaa(tilasto11); 
    Tilasto tilasto22 = new Tilasto(2); tilastoidut.lisaa(tilasto22); 
    Tilasto tilasto12 = new Tilasto(1); tilastoidut.lisaa(tilasto12); 
    Tilasto tilasto23 = new Tilasto(2); tilastoidut.lisaa(tilasto23); 
    Tilasto tilasto51 = new Tilasto(5); tilastoidut.lisaa(tilasto51); 
    List<Tilasto> loytyneet; 
    loytyneet = tilastoidut.annaTilastot(3); 
    assertEquals("From: Tilastot line: 268", 0, loytyneet.size()); 
    loytyneet = tilastoidut.annaTilastot(1); 
    assertEquals("From: Tilastot line: 270", 2, loytyneet.size()); 
    assertEquals("From: Tilastot line: 271", true, loytyneet.get(0) == tilasto11); 
    assertEquals("From: Tilastot line: 272", true, loytyneet.get(1) == tilasto12); 
    loytyneet = tilastoidut.annaTilastot(5); 
    assertEquals("From: Tilastot line: 274", 1, loytyneet.size()); 
    assertEquals("From: Tilastot line: 275", true, loytyneet.get(0) == tilasto51); 
  } // Generated by ComTest END
}