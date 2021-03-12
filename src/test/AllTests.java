
package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/** Test suite lentopallotilastotyokalu-ohjelmalle
 * @author Reetu Inkilä
 * @version Mar 11, 2021
 */
@RunWith(Suite.class)
@SuiteClasses({
    lentopallotilastotyokalu.test.LentopallotilastotyokaluTest.class,
    lentopallotilastotyokalu.test.JoukkueetTest.class,
    lentopallotilastotyokalu.test.JoukkueTest.class,
    lentopallotilastotyokalu.test.PelaajatTest.class,
    lentopallotilastotyokalu.test.PelaajaTest.class,
    lentopallotilastotyokalu.test.TilastotTest.class,
    lentopallotilastotyokalu.test.TilastoTest.class,
    })

public class AllTests {
    //
}
