package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;


/**
 * Test class for UnorderedLinkedMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * an unordered link-based list data structure that uses the move-to-front heuristic for
 * self-organizing entries based on access frequency
 *
 * @author Dr. King
 *
 */
public class UnorderedLinkedMapTest {
	
	/** map used for testing **/
    private Map<Integer, String> map;
    
    /**
     * Create a new instance of an unordered link-based map before each test case executes
     */     
    @Before
    public void setUp() {
        map = new UnorderedLinkedMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertEquals("UnorderedLinkedMap[3]", map.toString());
        assertEquals(1, map.size());

        assertNull(map.put(1, "string1"));
        assertEquals("UnorderedLinkedMap[1, 3]", map.toString());
        assertEquals(2, map.size());

        assertNull(map.put(2, "string2"));
        assertEquals("UnorderedLinkedMap[2, 1, 3]", map.toString());
        assertEquals(3, map.size());
        
        assertNull(map.put(4, "string4"));
        assertEquals("UnorderedLinkedMap[4, 2, 1, 3]", map.toString());
        assertEquals(4, map.size());

        assertEquals(map.put(1, "string10"), "string1");
        assertEquals("UnorderedLinkedMap[1, 4, 2, 3]", map.toString());
        assertEquals(4, map.size());

    }

    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        assertEquals("string1", map.get(1));
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        assertEquals("string3", map.get(3));
        assertEquals("UnorderedLinkedMap[3, 1, 4, 2, 5]", map.toString());

        assertEquals("string4", map.get(4));
        assertEquals("UnorderedLinkedMap[4, 3, 1, 2, 5]", map.toString());

        
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        assertEquals(5, map.size());
        assertEquals(map.remove(2), "string2");
        assertEquals(4, map.size());
        assertEquals("UnorderedLinkedMap[1, 4, 5, 3]", map.toString());
        
        assertEquals(map.remove(1), "string1");
        assertEquals(3, map.size());
        assertEquals("UnorderedLinkedMap[4, 5, 3]", map.toString());
        
        assertEquals(map.remove(3), "string3");
        assertEquals(2, map.size());
        assertEquals("UnorderedLinkedMap[4, 5]", map.toString());
        
        assertEquals(map.remove(4), "string4");
        assertEquals(1, map.size());
        assertEquals("UnorderedLinkedMap[5]", map.toString());

    }

    /**
     * Test the output of the iterator behavior, including expected exceptions
     */     
    @Test
    public void testIterator() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));

        Iterator<Integer> temp = map.iterator();
        
        assertTrue(temp.hasNext());
        assertEquals(Integer.valueOf(1), temp.next());
        
        assertTrue(temp.hasNext());
        assertEquals(Integer.valueOf(4), temp.next());
        
        assertTrue(temp.hasNext());
        assertEquals(Integer.valueOf(2), temp.next());
        
        assertTrue(temp.hasNext());
        assertEquals(Integer.valueOf(5), temp.next());
        
        assertTrue(temp.hasNext());
        assertEquals(Integer.valueOf(3), temp.next());
        
        assertThrows(UnsupportedOperationException.class, () -> temp.remove());
    }

    /**
     * Test the output of the entrySet() behavior, including expected exceptions
     */     
    @Test
    public void testEntrySet() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        
        Iterator<Map.Entry<Integer, String>> temp = map.entrySet().iterator();
        assertTrue(temp.hasNext());
        Map.Entry<Integer, String> entry = temp.next();
        assertEquals(1, (int)(entry.getKey()));
        assertTrue(temp.hasNext());
        Map.Entry<Integer, String> entry2 = temp.next();
        assertEquals(4, (int)(entry2.getKey()));
        assertTrue(temp.hasNext());
        Map.Entry<Integer, String> entry3 = temp.next();
        assertEquals(2, (int)(entry3.getKey()));
        assertTrue(temp.hasNext());
        Map.Entry<Integer, String> entry4 = temp.next();
        assertEquals(5, (int)(entry4.getKey()));
        assertTrue(temp.hasNext());
        Map.Entry<Integer, String> entry5 = temp.next();
        assertEquals(3, (int)(entry5.getKey()));
        
        assertThrows(UnsupportedOperationException.class, () -> temp.remove());    
    }

    /**
     * Test the output of the values() behavior, including expected exceptions
     */     
    @Test
    public void testValues() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        
        Iterator<String> temp = map.values().iterator();
        assertTrue(temp.hasNext());
        assertEquals("string1", temp.next());
        assertTrue(temp.hasNext());
        assertEquals("string4", temp.next());
        assertTrue(temp.hasNext());
        assertEquals("string2", temp.next());
        assertTrue(temp.hasNext());
        assertEquals("string5", temp.next());
        assertTrue(temp.hasNext());
        assertEquals("string3", temp.next());
        
        assertThrows(UnsupportedOperationException.class, () -> temp.remove());
    }
}