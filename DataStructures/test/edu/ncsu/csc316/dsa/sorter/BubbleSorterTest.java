package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * testing class for bubble sort
 * @author max farthing
 *
 */
public class BubbleSorterTest {

	/** test array for ints **/
	private Integer[] dataAscending = { 1, 2, 3, 4, 5 };
	/** test array for ints **/
	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };
	/** test array for ints **/
	private Integer[] dataRandom = { 4, 1, 5, 3, 2 };
	/** sorter for integers **/
	private BubbleSorter<Integer> integerSorter;

	/**
	 * set up method
	 */
	@Before
	public void setUp() {
		integerSorter = new BubbleSorter<Integer>();
	}

	/**
	 * testing sorting integers
	 */
	@Test
	public void testSortIntegers() {
		integerSorter.sort(dataAscending);
		assertEquals(1, dataAscending[0].intValue());
		assertEquals(2, dataAscending[1].intValue());
		assertEquals(3, dataAscending[2].intValue());
		assertEquals(4, dataAscending[3].intValue());
		assertEquals(5, dataAscending[4].intValue());

		integerSorter.sort(dataDescending);
		assertEquals(1, dataDescending[0].intValue());
		assertEquals(2, dataDescending[1].intValue());
		assertEquals(3, dataDescending[2].intValue());
		assertEquals(4, dataDescending[3].intValue());
		assertEquals(5, dataDescending[4].intValue());

		integerSorter.sort(dataRandom);
		assertEquals(1, dataRandom[0].intValue());
		assertEquals(2, dataRandom[1].intValue());
		assertEquals(3, dataRandom[2].intValue());
		assertEquals(4, dataRandom[3].intValue());
		assertEquals(5, dataRandom[4].intValue());
	}

}
