package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for RedBlackTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a red-black tree data structure 
 *
 * @author Dr. King
 *
 */
public class RedBlackTreeMapTest {
	/** binary search tree map used for testing **/
    private BinarySearchTreeMap<Integer, String> tree;
	/** binary search tree map used for testing **/
    private BinarySearchTreeMap<Integer, String> tree2;

    
    /**
     * Create a new instance of a red-black tree-based map before each test case executes
     */  
    @Before
    public void setUp() {
        tree = new RedBlackTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        // You should create test cases to check all the
        // rules for red-black trees. The textbook has examples
        // that you can use to create your test cases

        // You should check the specific keys in each node after adding or
        // removing from the tree. For example, you might use:
        //  assertEquals(4, (int)tree.root().getElement().getKey());
        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
        tree.put(10, "ten");
        assertEquals(1, tree.size());
        assertEquals(10, (int)tree.root().getElement().getKey());
        tree.put(5, "five");
        assertEquals(2, tree.size());
        assertEquals(5, (int)tree.left(tree.root()).getElement().getKey());
        tree.put(7, "seven");
        assertEquals(3, tree.size());
       

        assertEquals(7, (int)tree.root().getElement().getKey()); //restructuring 
        assertEquals(5, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(10, (int)tree.right(tree.root()).getElement().getKey());

        tree2 = new RedBlackTreeMap<Integer, String>(null);
        tree2.put(2, "two");
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
    	tree.put(10, "ten");
        assertEquals(1, tree.size());
        tree.put(5, "five");
        assertEquals(2, tree.size());
        tree.put(4, "four");
        assertEquals(3, tree.size());
        tree.put(14, "fourteen");
        assertEquals(4, tree.size());
        tree.put(12, "twelve");
        assertEquals(5, tree.size());
        tree.put(1, "one");
        assertEquals(6, tree.size());
        
        assertEquals("ten", tree.get(10));
        assertEquals("one", tree.get(1));
        assertEquals("four", tree.get(4));
        assertEquals("twelve", tree.get(12));
        assertEquals("five", tree.get(5));
        assertEquals("fourteen", tree.get(14));
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        // You should create test cases to check all the
        // rules for red-black trees. The textbook has examples
        // that you can use to create your test cases
        
        // You should check the specific keys in each node after adding or
        // removing from the tree. For example, you might use:
        //  assertEquals(4, (int)tree.root().getElement().getKey());
        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());         
        tree.put(10, "ten");
        assertEquals(1, tree.size());
        tree.put(5, "five");
        assertEquals(2, tree.size());
        tree.put(4, "four");
        assertEquals(3, tree.size());
        tree.put(14, "fourteen");
        assertEquals(4, tree.size());
        tree.put(12, "twelve");
        assertEquals(5, tree.size());
        tree.put(1, "one");
        assertEquals(6, tree.size());
        
        assertEquals("five", tree.remove(5));
        assertEquals(5, tree.size());
        assertEquals("fourteen", tree.remove(14));
        assertEquals(4, tree.size());

        assertEquals("four", tree.remove(4));

        assertEquals(3, tree.size());
        assertEquals("one", tree.remove(1));

        assertEquals(2, tree.size());
        assertEquals("twelve", tree.remove(12));
        assertEquals(1, tree.size());
        
        
    }
    
    /**
     * testing for extra coverage on double remedy black
     */
    @Test
    public void testRemedyDoubleBlack() {
        
        tree.put(10, "ten");
        tree.put(5, "five");
        tree.put(15, "fifteen");
        tree.put(3, "three");
        tree.put(7, "seven");
        tree.put(12, "twelve");
        tree.put(18, "eighteen");
        tree.put(2, "two");
        tree.put(4, "four");
        tree.put(6, "six");
        tree.put(8, "eight");
        tree.put(11, "eleven");
        tree.put(13, "thirteen");
        assertEquals(13, tree.size());
        assertEquals("fifteen", tree.remove(15));
        assertEquals("five", tree.remove(5));
        assertEquals("seven", tree.remove(7));

    }
}
