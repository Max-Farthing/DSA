package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.sorter.QuickSorter.FirstElementSelector;
import edu.ncsu.csc316.dsa.sorter.QuickSorter.LastElementSelector;
import edu.ncsu.csc316.dsa.sorter.QuickSorter.MiddleElementSelector;

public class QuickSorterTest {

	/** test array for ints **/
	private Integer[] dataAscending = { 1, 2, 3, 4, 5 };
	/** test array for ints **/
	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };
	/** test array for ints **/
	private Integer[] dataRandom = { 4, 1, 5, 3, 2 };
	/** sorter for integers **/
	private QuickSorter<Integer> integerSorter;
	/** sorter for integers **/
	private QuickSorter<Integer> integerSorter2;
	/** sorter for integers **/
	private QuickSorter<Integer> integerSorter3;
	/** sorter for integers **/
	private QuickSorter<Integer> integerSorter4;
	/** sorter using first element **/
	private FirstElementSelector first;
	/** sorter using middle pivot **/
	private MiddleElementSelector middle;
	/** sorter using last pivot **/
	private LastElementSelector last;


	/**
	 * set up method
	 */
	@Before
	public void setUp() {
		integerSorter = new QuickSorter<Integer>();
		integerSorter2 = new QuickSorter<Integer>(first);
		integerSorter3 = new QuickSorter<Integer>(middle);
		integerSorter4 = new QuickSorter<Integer>(last);
		
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
	
	/**
	 * testing sorting integers
	 */
	@Test
	public void testSortIntegersFirstPivot() {
		integerSorter2.sort(dataAscending);
		assertEquals(1, dataAscending[0].intValue());
		assertEquals(2, dataAscending[1].intValue());
		assertEquals(3, dataAscending[2].intValue());
		assertEquals(4, dataAscending[3].intValue());
		assertEquals(5, dataAscending[4].intValue());

		integerSorter2.sort(dataDescending);
		assertEquals(1, dataDescending[0].intValue());
		assertEquals(2, dataDescending[1].intValue());
		assertEquals(3, dataDescending[2].intValue());
		assertEquals(4, dataDescending[3].intValue());
		assertEquals(5, dataDescending[4].intValue());

		integerSorter2.sort(dataRandom);
		assertEquals(1, dataRandom[0].intValue());
		assertEquals(2, dataRandom[1].intValue());
		assertEquals(3, dataRandom[2].intValue());
		assertEquals(4, dataRandom[3].intValue());
		assertEquals(5, dataRandom[4].intValue());
		
	}
	
	/**
	 * testing sorting integers
	 */
	@Test
	public void testSortIntegersMidPivot() {
		integerSorter3.sort(dataAscending);
		assertEquals(1, dataAscending[0].intValue());
		assertEquals(2, dataAscending[1].intValue());
		assertEquals(3, dataAscending[2].intValue());
		assertEquals(4, dataAscending[3].intValue());
		assertEquals(5, dataAscending[4].intValue());

		integerSorter3.sort(dataDescending);
		assertEquals(1, dataDescending[0].intValue());
		assertEquals(2, dataDescending[1].intValue());
		assertEquals(3, dataDescending[2].intValue());
		assertEquals(4, dataDescending[3].intValue());
		assertEquals(5, dataDescending[4].intValue());

		integerSorter3.sort(dataRandom);
		assertEquals(1, dataRandom[0].intValue());
		assertEquals(2, dataRandom[1].intValue());
		assertEquals(3, dataRandom[2].intValue());
		assertEquals(4, dataRandom[3].intValue());
		assertEquals(5, dataRandom[4].intValue());
	}
	
	/**
	 * testing sorting integers
	 */
	@Test
	public void testSortIntegersLastPivot() {
		integerSorter4.sort(dataAscending);
		assertEquals(1, dataAscending[0].intValue());
		assertEquals(2, dataAscending[1].intValue());
		assertEquals(3, dataAscending[2].intValue());
		assertEquals(4, dataAscending[3].intValue());
		assertEquals(5, dataAscending[4].intValue());

		integerSorter4.sort(dataDescending);
		assertEquals(1, dataDescending[0].intValue());
		assertEquals(2, dataDescending[1].intValue());
		assertEquals(3, dataDescending[2].intValue());
		assertEquals(4, dataDescending[3].intValue());
		assertEquals(5, dataDescending[4].intValue());

		integerSorter4.sort(dataRandom);
		assertEquals(1, dataRandom[0].intValue());
		assertEquals(2, dataRandom[1].intValue());
		assertEquals(3, dataRandom[2].intValue());
		assertEquals(4, dataRandom[3].intValue());
		assertEquals(5, dataRandom[4].intValue());
	}
	
	/**
	 * test for element selector first
	 */
	@Test
    public void testFirstElementSelector() {
        QuickSorter<Integer> sorter = new QuickSorter<Integer>(QuickSorter.FIRST_ELEMENT_SELECTOR);
        Integer[] data = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};

        sorter.sort(data);

        Integer[] expected = {1, 1, 2, 3, 3, 4, 5, 5, 5, 6, 9};
        assertArrayEquals(expected, data);
    }
	
	/**
	 * test for element selector middle
	 */
	@Test
    public void testMiddleElementSelector() {
        QuickSorter<Integer> sorter = new QuickSorter<Integer>(QuickSorter.MIDDLE_ELEMENT_SELECTOR);
        Integer[] data = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};

        sorter.sort(data);

        Integer[] expected = {1, 1, 2, 3, 3, 4, 5, 5, 5, 6, 9};
        assertArrayEquals(expected, data);
    }

	/**
	 * test for element selector last
	 */
    @Test
    public void testLastElementSelector() {
        QuickSorter<Integer> sorter = new QuickSorter<Integer>(QuickSorter.LAST_ELEMENT_SELECTOR);
        Integer[] data = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};

        sorter.sort(data);

        Integer[] expected = {1, 1, 2, 3, 3, 4, 5, 5, 5, 6, 9};
        assertArrayEquals(expected, data);
    }

    /**
	 * test for element selector random
	 */
    @Test
    public void testRandomElementSelector() {
        QuickSorter<Integer> sorter = new QuickSorter<Integer>(QuickSorter.RANDOM_ELEMENT_SELECTOR);
        Integer[] data = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};

        sorter.sort(data);

        for (int i = 1; i < data.length; i++) {
            assertTrue(data[i - 1] <= data[i]);
        }
    }
    
    /**
	 * test for constructor using comparator
	 */
    @Test
    public void testConstructorWithComparator() {
        Comparator<Integer> comparator = Integer::compareTo;

        QuickSorter<Integer> sorter = new QuickSorter<>(comparator);

        assertNotNull(sorter);
    }
    
    /**
	 * test for element selector with partitioning
	 */
    @Test
    public void testPartitioning() {
        QuickSorter<Integer> sorter = new QuickSorter<>();
        Integer[] data = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};
        int low = 0;
        int high = data.length - 1;
        int pivot = 5; 

        int partitionResult = sorter.partitionTesting(data, low, high, pivot);

        int expectedPartitionResult = 6; 

        assertEquals(expectedPartitionResult, partitionResult);
    }
    
}
