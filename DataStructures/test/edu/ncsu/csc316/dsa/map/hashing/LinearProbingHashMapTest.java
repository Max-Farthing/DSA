package edu.ncsu.csc316.dsa.map.hashing;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for LinearProbingHashMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a linear probing hash map data structure 
 *
 * @author Dr. King
 *
 */
public class LinearProbingHashMapTest {
	/** map used for testing **/
    private Map<Integer, String> map;

    /**
     * Create a new instance of a linear probing hash map before each test case executes
     */     
    @Before
    public void setUp() {
        // Use the "true" flag to indicate we are testing.
        // Remember that (when testing) alpha = 1, beta = 1, and prime = 7
        // based on our AbstractHashMap constructor.
        // That means you can draw the hash table by hand
        // if you use integer keys, since Integer.hashCode() = the integer value, itself
        // Finally, apply compression. For example:
        // for key = 1: h(1) = ( (1 * 1 + 1) % 7) % 7 = 2
        // for key = 2: h(2) = ( (1 * 2 + 1) % 7) % 7 = 3
        // for key = 3: h(3) = ( (1 * 3 + 1) % 7) % 7 = 4
        // for key = 4: h(4) = ( (1 * 4 + 1) % 7) % 7 = 5
        // for key = 5: h(5) = ( (1 * 5 + 1) % 7) % 7 = 6
        // for key = 6: h(6) = ( (1 * 6 + 1) % 7) % 7 = 0
        // etc.
        map = new LinearProbingHashMap<Integer, String>(7, true);
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(1, "string1")); //inserting new entry table
        assertEquals(1, map.size());

        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        assertEquals(1, (int)it.next().getKey()); 

        assertNull(map.put(2, "string1")); //inserting new entry table
        assertEquals(2, map.size());
        
        it = map.entrySet().iterator();
        assertEquals(1, (int)it.next().getKey()); 
        assertEquals(2, (int)it.next().getKey()); 

        assertNull(map.put(8, "string8")); //should have collision with 1
        assertEquals(3, map.size());

        it = map.entrySet().iterator();
        assertEquals(1, (int)it.next().getKey()); //1 should be in index 2
        assertEquals(2, (int)it.next().getKey()); //2 should be in index 3
        assertEquals(8, (int)it.next().getKey()); //8 collides with 1 and is put in the next available index aka index 4
        
        assertNull(map.put(3, "string3")); //should have collision with 8
        assertEquals(4, map.size());
        
        it = map.entrySet().iterator();
        assertEquals(1, (int)it.next().getKey()); //1 should be in index 2
        assertEquals(2, (int)it.next().getKey()); //2 should be in index 3
        assertEquals(8, (int)it.next().getKey()); //8 collides with 1 and is put in the next available index aka index 4
        assertEquals(3, (int)it.next().getKey()); //3 collides with 8 since 8 is in 3's spot from its collision with 1
        
        assertEquals("string8", map.put(8, "string80"));

    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
    	assertTrue(map.isEmpty());
        assertNull(map.put(1, "string1")); //inserting new entry table
        assertNull(map.put(2, "string2")); //inserting new entry table
        assertNull(map.put(8, "string8")); //should have collision with 1
        assertNull(map.put(3, "string3")); //should have collision with 8
        assertEquals(4, map.size());

        assertEquals("string1", map.get(1));
        assertEquals("string2", map.get(2));
        assertEquals("string8", map.get(8));
        assertEquals("string3", map.get(3));
        assertNull(map.get(24));

    }
    
    /**
     * Test the output of the remove(k) behavior
     */ 
    @Test
    public void testRemove() {
        assertNull(map.get(1));

        assertTrue(map.isEmpty());
        assertNull(map.put(1, "string1")); //inserting new entry table
        assertNull(map.put(2, "string2")); //inserting new entry table
        assertNull(map.put(8, "string8")); //should have collision with 1
        assertNull(map.put(3, "string3")); //should have collision with 8
        assertEquals(4, map.size());
        
        assertEquals("string8", map.remove(8));
        assertEquals(3, map.size());
        
        assertEquals("string2", map.remove(2));
        assertEquals(2, map.size());
        
        assertEquals("string3", map.remove(3));
        assertEquals(1, map.size());
        
        assertEquals("string1", map.remove(1));
        assertEquals(0, map.size());
        
        assertNull(map.remove(1));
        assertNull(map.remove(24));

        assertNull(map.get(1));
    }
    
    /**
     * Test the output of the iterator() behavior, including expected exceptions
     */     
    @Test
    public void testIterator() {
    	assertTrue(map.isEmpty());
        assertNull(map.put(1, "string1")); //inserting new entry table
        assertNull(map.put(2, "string2")); //inserting new entry table
        assertNull(map.put(8, "string8")); //should have collision with 1
        assertNull(map.put(3, "string3")); //should have collision with 8
        assertEquals(4, map.size());
        
        Iterator<Integer> it = map.iterator();
        assertEquals(1, (int)it.next());
        assertEquals(2, (int)it.next());
        assertEquals(8, (int)it.next());
        assertEquals(3, (int)it.next());

    }
    
    /**
     * Test the output of the entrySet() behavior
     */     
    @Test
    public void testEntrySet() {
    	assertTrue(map.isEmpty());
        assertNull(map.put(1, "string1")); //inserting new entry table
        assertNull(map.put(2, "string2")); //inserting new entry table
        assertNull(map.put(8, "string8")); //should have collision with 1
        assertNull(map.put(3, "string3")); //should have collision with 8
        assertEquals(4, map.size());
        
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();     
        assertEquals(1, (int)it.next().getKey());
        assertEquals(2, (int)it.next().getKey());
        assertEquals(8, (int)it.next().getKey());
        assertEquals(3, (int)it.next().getKey());

        it = map.entrySet().iterator();

        assertEquals("string1", it.next().getValue());
        assertEquals("string2", it.next().getValue());
        assertEquals("string8", it.next().getValue());
        assertEquals("string3", it.next().getValue());
    }
    
    /**
     * Test the output of the values() behavior
     */  
    @Test
    public void testValues() {
    	assertTrue(map.isEmpty());
        assertNull(map.put(1, "string1")); //inserting new entry table
        assertNull(map.put(2, "string2")); //inserting new entry table
        assertNull(map.put(8, "string8")); //should have collision with 1
        assertNull(map.put(3, "string3")); //should have collision with 8
        assertEquals(4, map.size());
        
        Iterator<String> it = map.values().iterator();
        assertEquals("string1", it.next());
        assertEquals("string2", it.next());
        assertEquals("string8", it.next());
        assertEquals("string3", it.next());

    }

    /**
     * testing various constructors for method coverage
     */
    @Test
    public void testConstructors() {
    	map = new LinearProbingHashMap<Integer, String>();
    	map = new LinearProbingHashMap<Integer, String>(false);
    	map = new LinearProbingHashMap<Integer, String>(10);
    	assertTrue(map.isEmpty());
    }
}
