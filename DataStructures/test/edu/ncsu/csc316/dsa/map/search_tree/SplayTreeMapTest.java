package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.map.search_tree.*;

/**
 * Test class for SplayTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a splay tree data structure 
 *
 * @author Dr. King
 *
 */
public class SplayTreeMapTest {
	/** binary search tree map used for testing **/
    private BinarySearchTreeMap<Integer, String> tree;
    /** binary search tree map for testing **/
    private BinarySearchTreeMap<Integer, String> tree2;
    
    /**
     * Create a new instance of a splay tree-based map before each test case executes
     */     
    @Before
    public void setUp() {
        tree = new SplayTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        // You should create test cases to check all the
        // splay scenarios. The textbook has examples
        // that you can use to create your test cases
        
        // You should check the specific keys in each node after adding or
        // removing from the tree. For example, you might use:
        //  assertEquals(4, (int)tree.root().getElement().getKey());
        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
        
        tree.put(1, "one");
        assertEquals(1, tree.size());
        assertEquals(1, (int)tree.root().getElement().getKey());
        
        tree.put(2, "two");
        assertEquals(2, tree.size());
        assertEquals(2, (int)tree.root().getElement().getKey());
        
        tree.put(3, "three");
        assertEquals(3, tree.size());
        assertEquals(3, (int)tree.root().getElement().getKey());
        
        tree.put(7, "seven");
        assertEquals(4, tree.size());
        assertEquals(7, (int)tree.root().getElement().getKey());
        
        tree.put(5, "five");
        assertEquals(5, tree.size());
        
        assertEquals(5, (int)tree.root().getElement().getKey()); //check tree structure
        assertEquals(7, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(3, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(2, (int)tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(1, (int)tree.left(tree.left(tree.left(tree.root()))).getElement().getKey());

        tree2 = new AVLTreeMap<Integer, String>(null);
        tree2.put(2, "two");
    }
    
    /**
     * Test the output of the get(k) behavior
     */ 
    @Test
    public void testGet() {
    	tree.put(1, "one");
        assertEquals(1, tree.size());
        tree.put(2, "two");
        assertEquals(2, tree.size());        
        tree.put(3, "three");
        assertEquals(3, tree.size());
        tree.put(7, "seven");
        assertEquals(4, tree.size());        
        tree.put(5, "five");
        assertEquals(5, tree.size());    
        
        assertEquals("one", tree.get(1));
        assertEquals(1, (int)tree.root().getElement().getKey());
        assertEquals("five", tree.get(5));
        assertEquals(5, (int)tree.root().getElement().getKey());
        assertEquals("two", tree.get(2));
        assertEquals(2, (int)tree.root().getElement().getKey());
        assertEquals("seven", tree.get(7));
        assertEquals(7, (int)tree.root().getElement().getKey());
        assertEquals("three", tree.get(3));
        assertEquals(3, (int)tree.root().getElement().getKey());
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        // You should create test cases to check all the
        // splay scenarios. The textbook has examples
        // that you can use to create your test cases
        
        // You should check the specific keys in each node after adding or
        // removing from the tree. For example, you might use:
        //  assertEquals(4, (int)tree.root().getElement().getKey());
        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey()); 
    	
    	tree.put(1, "one");
        assertEquals(1, tree.size());
        tree.put(2, "two");
        assertEquals(2, tree.size());        
        tree.put(3, "three");
        assertEquals(3, tree.size());
        tree.put(7, "seven");
        assertEquals(4, tree.size());        
        tree.put(5, "five");
        assertEquals(5, tree.size());
        assertEquals(5, (int)tree.root().getElement().getKey()); //check tree structure
        assertEquals(7, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(3, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(2, (int)tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(1, (int)tree.left(tree.left(tree.left(tree.root()))).getElement().getKey());
        
        assertEquals("one", tree.remove(1)); //remove leaf
        assertEquals(2, (int)tree.root().getElement().getKey()); //parent gets splayed
        assertEquals(3, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(5, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        assertEquals(7, (int)tree.right(tree.right(tree.right(tree.root()))).getElement().getKey());

        assertEquals("five", tree.remove(5)); //remove internal node
        assertEquals(3, (int)tree.root().getElement().getKey());
        assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(7, (int)tree.right(tree.root()).getElement().getKey());

        assertEquals("three", tree.remove(3)); //remove root
        assertEquals(7, (int)tree.root().getElement().getKey());
        assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());

    }
    
}