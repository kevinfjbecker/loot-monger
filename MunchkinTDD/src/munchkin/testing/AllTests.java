
package munchkin.testing;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(AllTests.suite());
	}

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for munchkin.testing");
		//$JUnit-BEGIN$
		suite.addTestSuite(TestBigfoot.class);
		suite.addTestSuite(TestBadAssBandana.class);
		suite.addTestSuite(TestAmazon.class);
		suite.addTestSuite(TestBoilAnAntHill.class);
		suite.addTestSuite(TestBootsOfButtKicking.class);
		suite.addTestSuite(TestAncient.class);
		suite.addTestSuite(TestBaby.class);
		suite.addTestSuite(TestOneThousandGoldPieces.class);
		suite.addTestSuite(TestOneThousandEightHundredEtcOrcs.class);
		suite.addTestSuite(TestBootsOfRunningReallyFast.class);
		//$JUnit-END$
		return suite;
	}

}
