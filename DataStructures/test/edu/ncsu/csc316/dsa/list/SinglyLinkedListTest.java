package edu.ncsu.csc316.dsa.list;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for SinglyLinkedListTest.
 * Checks the expected outputs of the List abstract data type behaviors when using
 * an array-based list data structure
 *
 * @author Dr. King
 *
 */
public class SinglyLinkedListTest {

    private List<String> list;

    /**
     * Create a new instance of an array-based list before each test case executes
     */
    @Before
    public void setUp() {
        list = new SinglyLinkedList<String>();
    }

    /**
     * Test the output of the add(index, e) behavior, including expected exceptions
     */
    @Test
    public void testAddIndex() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());

        list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());
        
        list.add(1, "two");
        assertEquals(2, list.size());
        assertEquals("two", list.get(1));
        assertFalse(list.isEmpty());
        
        list.add(2, "three");
        assertEquals(3, list.size());
        assertEquals("three", list.get(2));
        assertEquals(3, list.size());
        
        list.add(2, "four");
        assertEquals(4, list.size());
        assertEquals("four", list.get(2));
        assertEquals("three", list.get(3));


        
        // Use the statements above to help guide your test cases
        // for data structures: Start with an empty data structure, then
        // add an element and check the accessor method return values.
        // Then add another element and check again. Continue to keep checking
        // for special cases. For example, for an array-based list, you should
        // continue adding until you trigger a resize operation to make sure
        // the resize operation worked as expected.
        
        try{
            list.add(15,  "fifteen");
            fail("An IndexOutOfBoundsException should have been thrown");
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
    }

    /**
     * Test the output of the addLast behavior
     */
    @Test
    public void testAddLast() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());
        
        list.add(1, "two");
        assertEquals(2, list.size());
        assertEquals("two", list.get(1));
        assertFalse(list.isEmpty());
        
        list.add(2, "three");
        assertEquals(3, list.size());
        assertEquals("three", list.get(2));
        assertFalse(list.isEmpty());
        
        list.addLast("four");
        assertEquals(4, list.size());
        assertEquals("four", list.get(3));
        assertFalse(list.isEmpty());

    }

    /**
     * Test the output of the last() behavior, including expected exceptions
     */
    @Test
    public void testLast() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());
        
        list.add(1, "two");
        assertEquals(2, list.size());
        assertEquals("two", list.get(1));
        assertFalse(list.isEmpty());
        
        list.add(2, "three");
        assertEquals(3, list.size());
        assertEquals("three", list.get(2));
        assertFalse(list.isEmpty());
        
        assertEquals(list.get(2), list.last());
    }

    /**
     * Test the output of the addFirst behavior
     */
    @Test
    public void testAddFirst() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());
        
        list.add(1, "two");
        assertEquals(2, list.size());
        assertEquals("two", list.get(1));
        assertFalse(list.isEmpty());
        
        list.addFirst("three");
        assertEquals(3, list.size());
        assertEquals("three", list.get(0));
        assertEquals("one", list.get(1));
        assertEquals("two", list.get(2));

    }

    /**
     * Test the output of the first() behavior, including expected exceptions
     */
    @Test
    public void testFirst() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());
        
        assertEquals(list.get(0), list.first());
    }

    /**
     * Test the iterator behaviors, including expected exceptions
     */
    @Test
    public void testIterator() {
        // Start with an empty list
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        // Create an iterator for the empty list
        Iterator<String> it = list.iterator();
        
        // Try different operations to make sure they work
        // as expected for an empty list (at this point)
        try{
            it.remove();
            fail("An UnsupportedOperationException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }
        assertFalse(it.hasNext());

        // Now add an element
        list.addLast("one");
        
        // Use accessor methods to check that the list is correct
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertEquals("one", list.get(0));
        
        // Create an iterator for the list that has 1 element
        it = list.iterator();
        
        // Try different iterator operations to make sure they work
        // as expected for a list that contains 1 element (at this point)
        assertTrue(it.hasNext());
        assertEquals("one", it.next());
        assertFalse(it.hasNext());
        try{
            it.next();
            fail("A NoSuchElementException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }

        assertEquals(1, list.size());

    }

    /**
     * Test the output of the remove(index) behavior, including expected exceptions
     */
    @Test
    public void testRemoveIndex() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());
        
        list.add(1, "two");
        assertEquals(2, list.size());
        assertEquals("two", list.get(1));
        assertFalse(list.isEmpty());
        
        list.add(2, "three");
        assertEquals(3, list.size());
        assertEquals("three", list.get(2));
        
        list.add(3, "four");
        assertEquals(4, list.size());
        assertEquals("four", list.get(3));

        list.remove(0);
        assertEquals(3, list.size());
        assertEquals("two", list.get(0));
        assertEquals("three", list.get(1));
        assertEquals("four", list.get(2));

        list.remove(2);
        assertEquals(2, list.size());
        assertEquals("two", list.get(0));
        assertEquals("three", list.get(1));
        
        list.remove(0);
        assertEquals(1, list.size());
        assertEquals("three", list.get(0));

    }

    /**
     * Test the output of the removeFirst() behavior, including expected exceptions
     */
    @Test
    public void testRemoveFirst() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());
        
        list.add(1, "two");
        assertEquals(2, list.size());
        assertEquals("two", list.get(1));
        assertFalse(list.isEmpty());
        
        list.add(2, "three");
        assertEquals(3, list.size());
        assertEquals("three", list.get(2));
        
        list.add(3, "four");
        assertEquals(4, list.size());
        assertEquals("four", list.get(3));

        list.removeFirst();
        assertEquals(3, list.size());
        assertEquals("two", list.get(0));
        assertEquals("three", list.get(1));
        assertEquals("four", list.get(2));
        
        list.removeFirst();
        assertEquals(2, list.size());
        assertEquals("three", list.get(0));
        assertEquals("four", list.get(1));
        
        list.removeFirst();
        assertEquals(1, list.size());
        assertEquals("four", list.get(0));
        
        list.removeFirst();
        assertTrue(list.isEmpty());
    }

    /**
     * Test the output of the removeLast() behavior, including expected exceptions
     */
    @Test
    public void testRemoveLast() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());
        
        list.add(1, "two");
        assertEquals(2, list.size());
        assertEquals("two", list.get(1));
        assertFalse(list.isEmpty());
        
        list.add(2, "three");
        assertEquals(3, list.size());
        assertEquals("three", list.get(2));
        
        list.add(3, "four");
        assertEquals(4, list.size());
        assertEquals("four", list.get(3));

        list.removeLast();
        assertEquals(3, list.size());
        assertEquals("one", list.get(0));
        assertEquals("two", list.get(1));
        assertEquals("three", list.get(2));
        
        list.removeLast();
        assertEquals(2, list.size());
        assertEquals("one", list.get(0));
        assertEquals("two", list.get(1));

        list.removeLast();
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        
        list.removeLast();
        assertTrue(list.isEmpty());
    }
    
    /**
     * Student test for testing Set method
     */
    @Test
    public void testSetElement() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());
        
        list.set(0, "two");
        assertEquals(1, list.size());
        assertEquals("two", list.get(0));
        assertFalse(list.isEmpty());
    }

}

