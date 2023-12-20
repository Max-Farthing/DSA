package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the student class
 * @author max farthing
 *
 */
public class StudentTest {

	/** student object one **/
	private Student sOne;
	/** student object two **/
	private Student sTwo;
	/** student object three **/
	private Student sThree;
	/** student object four **/
	private Student sFour;
	/** student object five **/
	private Student sFive;
	/** student object six **/
	private Student sSix;

	/**
	 * set up test method
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		sSix = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
	}

	/**
	 * tests setting first method
	 */
	@Test
	public void testSetFirst() {
		sOne.setFirst("newOne");
		assertEquals("newOne", sOne.getFirst());
	}

	/**
	 * tests setting last method
	 */
	@Test
	public void testSetLast() {
		sOne.setLast("newOne");
		assertEquals("newOne", sOne.getLast());
	}

	/**
	 * tests setting id method
	 */
	@Test
	public void testSetId() {
		sOne.setId(100);
		assertEquals(100, sOne.getId());
	}

	/**
	 * tests setting GPA
	 */
	@Test
	public void testSetGpa() {
		sOne.setGpa(3.51);
		assertEquals(3.51, sOne.getGpa(), 0.001);
	}
	
	/**
	 * tests setting unity ID
	 */
	@Test
	public void testSetUnityID() {
		sOne.setUnityID("oneUnity");
		assertEquals("oneUnity", sOne.getUnityID());
		assertEquals(sOne.getCreditHours(), sSix.getCreditHours());
	}

	/**
	 * tests compare method
	 */
	@Test
	public void testCompareTo() {
		assertTrue(sOne.compareTo(sTwo) < 0);
		assertTrue(sTwo.compareTo(sOne) > 0);
		assertTrue(sOne.compareTo(sOne) == 0);
		assertTrue(sTwo.compareTo(sTwo) == 0);
	}
	
	/**
	 * tests equals method
	 */
	@Test
	public void testEquals() {
		assertTrue(sOne.equals(sSix));
		assertFalse(sOne.equals(sTwo));
		assertEquals(sOne.hashCode(), sSix.hashCode());
		assertFalse(sFour.equals(sThree));
		assertFalse(sTwo.equals(sFive));
	}
	
	/**
	 * tests toString method
	 */
	@Test
	public void testToString() {
		assertEquals(sOne.toString(), sSix.toString());
		assertNotEquals(sOne.toString(), sTwo.toString());
	}
	// Suggestions:
	// -> Test .equals(), .toString(), etc.
}
