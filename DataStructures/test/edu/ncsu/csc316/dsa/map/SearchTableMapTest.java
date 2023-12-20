package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.map.AbstractMap.MapEntry;

/**
 * Test class for SearchTableMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a sorted array-based data structure that uses binary search to locate entries
 * based on the key of the entry
 *
 * @author Dr. King
 *
 */
public class SearchTableMapTest {

	/** test map **/
    private Map<Integer, String> map;
    /** test student map **/
    private Map<Student, Integer> studentMap;
    
    /**
     * Create a new instance of a search table map before each test case executes
     */     
    @Before
    public void setUp() {
        map = new SearchTableMap<Integer, String>();
        studentMap = new SearchTableMap<Student, Integer>();
    }

    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertEquals("SearchTableMap[3]", map.toString());
        assertEquals(1, map.size());
        
        assertNull(map.put(1, "string1"));
        assertEquals(2, map.size());
        assertEquals("SearchTableMap[1, 3]", map.toString());
        
        assertNull(map.put(4, "string4"));
        assertEquals(3, map.size());
        assertEquals("SearchTableMap[1, 3, 4]", map.toString());
        
        assertNull(map.put(2, "string2"));
        assertEquals(4, map.size());
        assertEquals("SearchTableMap[1, 2, 3, 4]", map.toString());

        assertEquals("string3", map.put(3, "test"));
        assertEquals(4, map.size());
        assertEquals("SearchTableMap[1, 2, 3, 4]", map.toString());
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
        assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
        
        assertEquals("string1", map.get(1));
        assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
        
        assertEquals("string2", map.get(2));
        assertEquals("string3", map.get(3));
        assertEquals("string4", map.get(4));
        assertEquals("string5", map.get(5));

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
        assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
        
        assertEquals(map.size(), 5);
        assertEquals(map.remove(1), "string1");
        assertEquals(map.size(), 4);
        assertEquals("SearchTableMap[2, 3, 4, 5]", map.toString());
        
        assertEquals(map.remove(4), "string4");
        assertEquals(map.size(), 3);
        assertEquals("SearchTableMap[2, 3, 5]", map.toString());
        
        assertNull(map.remove(1));
        
        assertEquals(map.remove(3), "string3");
        assertEquals(map.size(), 2);
        assertEquals("SearchTableMap[2, 5]", map.toString());

    }
    
    /**
     * Tests Map abstract data type behaviors to ensure the behaviors work
     * as expected when using arbitrary objects as keys
     */
    @Test
    public void testStudentMap() {
        Student s1 = new Student("J", "K", 1, 0, 0, "jk");
        Student s2 = new Student("J", "S", 2, 0, 0, "js");
        Student s3 = new Student("S", "H", 3, 0, 0, "sh");
        Student s4 = new Student("J", "J", 4, 0, 0, "jj");
        Student s5 = new Student("L", "B", 5, 0, 0, "lb");
        
        assertNull(studentMap.put(s1, 100)); 
        assertNull(studentMap.put(s2, 200)); 
        assertNull(studentMap.put(s3, 300)); 

        assertEquals(3, studentMap.size()); 
        assertEquals(Integer.valueOf(100), studentMap.get(s1)); 
        assertEquals(Integer.valueOf(200), studentMap.get(s2)); 
        assertEquals(Integer.valueOf(300), studentMap.get(s3)); 

        Student s1Equivalent = new Student("J", "K", 1, 0, 0, "jk");
        assertEquals(Integer.valueOf(100), studentMap.get(s1Equivalent)); 

        assertNull(studentMap.put(s4, 400));
        assertNull(studentMap.put(s5, 500));

        assertEquals(5, studentMap.size()); 

        assertEquals(Integer.valueOf(200), studentMap.remove(s2)); 
        assertNull(studentMap.get(s2)); 
        assertEquals(4, studentMap.size());
        
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
        
        Iterator<Integer> it = map.iterator();
        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(1), it.next());
        
        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(2), it.next());
        
        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(3), it.next());
        
        assertThrows(UnsupportedOperationException.class, () -> it.remove());

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
        
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        assertTrue(it.hasNext());
        Map.Entry<Integer, String> entry = it.next();
        assertEquals(1, (int)(entry.getKey()));
        assertEquals("string1", (String)(entry.getValue()));
        
        assertTrue(it.hasNext());
        Map.Entry<Integer, String> entry2 = it.next();
        assertEquals(2, (int)(entry2.getKey()));
        assertEquals("string2", (String)(entry2.getValue()));
        
        assertTrue(it.hasNext());
        Map.Entry<Integer, String> entry3 = it.next();
        assertEquals(3, (int)(entry3.getKey()));
        assertEquals("string3", (String)(entry3.getValue()));

        assertThrows(UnsupportedOperationException.class, () -> it.remove());
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
        
        Iterator<String> it = map.values().iterator();
        assertTrue(it.hasNext());
        assertEquals("string1", it.next());
        
        assertTrue(it.hasNext());
        assertEquals("string2", it.next());
        
        assertTrue(it.hasNext());
        assertEquals("string3", it.next());
        
        assertTrue(it.hasNext());
        assertEquals("string4", it.next());
        assertThrows(UnsupportedOperationException.class, () -> it.remove());

    }
    
    /**
     * testing abstract compareTo method
     */
    @Test
    public void testCompareTo() {
        MapEntry<Integer, String> entry1 = new MapEntry<>(1, "value1");
        MapEntry<Integer, String> entry2 = new MapEntry<>(2, "value2");
        MapEntry<Integer, String> entry3 = new MapEntry<>(3, "value3");

        assertTrue(entry1.compareTo(entry2) < 0); 
        assertTrue(entry2.compareTo(entry1) > 0); 
        assertTrue(entry2.compareTo(entry3) < 0); 
        assertTrue(entry3.compareTo(entry2) > 0); 
        assertEquals(0, entry1.compareTo(entry1));
    }
}
