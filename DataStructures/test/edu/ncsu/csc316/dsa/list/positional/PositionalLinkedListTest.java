package edu.ncsu.csc316.dsa.list.positional;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for PositionalLinkedList.
 * Checks the expected outputs of the Positional List abstract data type behaviors when using
 * an doubly-linked positional list data structure
 *
 * @author Dr. King
 *
 */
public class PositionalLinkedListTest {

    private PositionalList<String> list;
    
    /**
     * Create a new instance of an positional linked list before each test case executes
     */ 
    @Before
    public void setUp() {
        list = new PositionalLinkedList<String>();
    }
    
    /**
     * Test the output of the first() behavior, including expected exceptions
     */
    @Test
    public void testFirst() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        assertNull(list.first());
        
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertEquals(first, list.first());
        
        Position<String> second = list.addFirst("two");
        assertEquals(2, list.size());
        assertEquals(second, list.first());
        assertEquals(first, list.after(second));
        
        Position<String> third = list.addFirst("three");
        assertEquals(3, list.size());
        assertEquals(third, list.first());
        assertEquals(second, list.after(third));
        assertEquals(first, list.after(second));
        
    }
    
    /**
     * Test the output of the last() behavior, including expected exceptions
     */
    @Test
    public void testLast() {
    	 assertEquals(0, list.size());
         assertTrue(list.isEmpty());
         
         assertNull(list.last());
         
         Position<String> first = list.addLast("one");
         assertEquals(1, list.size());
         assertEquals(first, list.last());
         
         Position<String> second = list.addLast("two");
         assertEquals(2, list.size());
         assertEquals(second, list.last());
         assertEquals(first, list.before(second));
         
         Position<String> third = list.addLast("three");
         assertEquals(3, list.size());
         assertEquals(third, list.last());
         assertEquals(second, list.before(third));
         assertEquals(first, list.before(second));
    }
    
    /**
     * Test the output of the addFirst(element) behavior
     */ 
    @Test
    public void testAddFirst() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertEquals(first, list.first());
        
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        Position<String> second = list.addFirst("two");
        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
        assertEquals(second, list.first());
        assertEquals(second, list.before(first));

    }
    
    /**
     * Test the output of the addLast(element) behavior
     */ 
    @Test
    public void testAddLast() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addLast("one");
        assertEquals(1, list.size());
        assertEquals(first, list.last());
        
        assertFalse(list.isEmpty());
        Position<String> second = list.addLast("two");
        assertEquals(2, list.size());
        assertEquals(second, list.last());
        assertEquals(second, list.after(first));
    }
    
    /**
     * Test the output of the before(position) behavior, including expected exceptions
     */ 
    @Test
    public void testBefore() {
    	
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addLast("one");
    	assertEquals(1, list.size());
        Position<String> second = list.addLast("two");
    	assertEquals(2, list.size());
        Position<String> third = list.addLast("three");
    	assertEquals(3, list.size());
    	
    	assertNull(list.before(first));
    	assertEquals(first, list.before(second));
    	assertEquals(second, list.before(third));
    	
    }
    
    /**
     * Test the output of the after(position) behavior, including expected exceptions
     */     
    @Test
    public void testAfter() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addLast("one");
    	assertEquals(1, list.size());
        Position<String> second = list.addLast("two");
    	assertEquals(2, list.size());
        Position<String> third = list.addLast("three");
    	assertEquals(3, list.size());
    	
    	assertNull(list.after(third));
    	assertEquals(second, list.after(first));
    	assertEquals(third, list.after(second));
    }
    
    /**
     * Test the output of the addBefore(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testAddBefore() {
    	
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addLast("one");
    	assertEquals(1, list.size());
    	
        Position<String> second = list.addBefore(first, "two");
    	assertEquals(2, list.size());
    	assertEquals(second, list.before(first));
    	
        Position<String> third = list.addBefore(second, "three");
    	assertEquals(3, list.size());
    	assertEquals(second, list.before(first));
    	assertEquals(third, list.before(second));
    	
    	//maybe add test for IAE
    }
    
    /**
     * Test the output of the addAfter(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testAddAfter() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addLast("one");
    	assertEquals(1, list.size());
    	
        Position<String> second = list.addAfter(first, "two");
    	assertEquals(2, list.size());
    	assertEquals(second, list.after(first));
    	
        Position<String> third = list.addAfter(second, "three");
    	assertEquals(3, list.size());
    	assertEquals(second, list.after(first));
    	assertEquals(third, list.after(second));
    	
    	//maybe add test for IAE
    }
    
    /**
     * Test the output of the set(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testSet() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addLast("one");
    	assertEquals(1, list.size());
    	
        Position<String> second = list.addAfter(first, "two");
    	assertEquals(2, list.size());
    	
        Position<String> third = list.addAfter(second, "three");
    	assertEquals(3, list.size());
    	
    	list.set(first, "seven");
    	assertEquals("seven", first.getElement());
    	list.set(second, "eight");
    	assertEquals("eight", second.getElement());
    	list.set(third, "nine");
    	assertEquals("nine", third.getElement());
    	list.set(first, "ten");
    	assertEquals("ten", first.getElement());

    }
    
    /**
     * Test the output of the remove(position) behavior, including expected exceptions
     */     
    @Test
    public void testRemove() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addLast("one");
    	assertEquals(1, list.size());
    	
        Position<String> second = list.addAfter(first, "two");
    	assertEquals(2, list.size());
    	
        Position<String> third = list.addAfter(second, "three");
    	assertEquals(3, list.size());
    	
    	list.remove(second);
    	assertEquals(2, list.size());
    	assertEquals(third, list.after(first));
    	assertEquals(first, list.before(third));

    }
    
    /**
     * Test the output of the iterator behavior for elements in the list, 
     * including expected exceptions
     */     
    @Test
    public void testIterator() {
    	assertEquals(0, list.size());
        Position<String> first = list.addFirst("one");
        Position<String> second = list.addLast("two");
        Position<String> third = list.addLast("three");
        assertEquals(3, list.size());
        
        Iterator<String> it = list.iterator();
        assertTrue(it.hasNext());
        assertEquals(first.getElement(), it.next());
        assertEquals(second.getElement(), it.next());
        it.remove();
        assertEquals(2, list.size());
        assertEquals(third.getElement(), it.next());
    }
    
    /**
     * Test the output of the positions() behavior to iterate through positions
     * in the list, including expected exceptions
     */     
    @Test
    public void testPositions() {
        assertEquals(0, list.size());
        Position<String> first = list.addFirst("one");
        Position<String> second = list.addLast("two");
        Position<String> third = list.addLast("three");
        assertEquals(3, list.size());
        
        Iterator<Position<String>> it = list.positions().iterator();
        assertTrue(it.hasNext());
        assertEquals(first, it.next());
        assertEquals(second, it.next());
        it.remove();
        assertEquals(2, list.size());
        assertEquals(third, it.next());
        
    }
    
    /**
     * Test the output of the positions() behavior to iterate through positions
     * in the list, including expected exceptions
     */     
    @Test
    public void testPositions2() {
        assertEquals(0, list.size());
        Position<String> first = list.addFirst("one");
        Position<String> second = list.addLast("two");
        Position<String> third = list.addLast("three");
        assertEquals(3, list.size());
        
        Iterator<Position<String>> it = list.positions().iterator();
        assertTrue(it.hasNext());
        assertEquals(first, it.next());
        assertEquals(3, list.size());
        it.remove();
        assertEquals(2, list.size());
        assertEquals(second, it.next());
        assertEquals(2, list.size());
        it.remove();
        assertEquals(1, list.size());
        assertEquals(third, it.next());
        assertEquals(1, list.size());
//        it.remove();
//        assertEquals(0, list.size());

        
    }
    
    /**
     * Test the output of the iterator behavior for elements in the list, 
     * including expected exceptions
     */     
    @Test
    public void testIterator2() {
    	assertEquals(0, list.size());
        Position<String> first = list.addFirst("one");
        Position<String> second = list.addLast("two");
        Position<String> third = list.addLast("three");
        assertEquals(3, list.size());
        
        Iterator<String> it = list.iterator();
        assertTrue(it.hasNext());
        assertEquals(first.getElement(), it.next());
        assertEquals(3, list.size());
        it.remove();
        assertEquals(2, list.size());
        assertEquals(second.getElement(), it.next());
        assertEquals(2, list.size());
        it.remove();
        assertEquals(1, list.size());
        assertEquals(third.getElement(), it.next());
        assertEquals(1, list.size());
    }

}

