package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for AVLTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * an AVL tree data structure 
 *
 * @author Dr. King
 *
 */
public class AVLTreeMapTest {
	/** binary search tree map for testing **/
    private BinarySearchTreeMap<Integer, String> tree;
	/** binary search tree map for testing **/
    private BinarySearchTreeMap<Integer, String> tree2;

    
    /**
     * Create a new instance of an AVL tree-based map before each test case executes
     */     
    @Before
    public void setUp() {
        tree = new AVLTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        
        // You should create test cases to check all the
        // trinode restructuring scenarios. The textbook has visual examples
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
        assertEquals(2, (int)tree.right(tree.root()).getElement().getKey());
        
        tree.put(3, "three"); //trying to add 3 right nodes
        assertEquals(3, tree.size());
        assertEquals(2, (int)tree.root().getElement().getKey()); //two shuffles to be the new root
        
        tree.remove(1);
        tree.remove(2);
        tree.remove(3); //emptying tree
        
        tree.put(3, "three"); // trying 3 left children now
        assertEquals(1, tree.size());
        assertEquals(3, (int)tree.root().getElement().getKey()); //3 is initial root
        tree.put(2, "two");
        assertEquals(2, tree.size());
        assertEquals(2, (int)tree.left(tree.root()).getElement().getKey()); //2 is left child of 3
        tree.put(1, "one");
        assertEquals(3, tree.size());
        assertEquals(2, (int)tree.root().getElement().getKey()); //two shuffles to be the new root
        assertEquals(3, (int)tree.right(tree.root()).getElement().getKey()); //3 shifts to right child of root now
        
        tree.remove(1);
        tree.remove(2);
        tree.remove(3);
        
        tree.put(1, "one"); // trying double rotation from right
        assertEquals(1, tree.size());
        assertEquals(1, (int)tree.root().getElement().getKey()); //1 is initial root
        tree.put(3, "three");
        assertEquals(2, tree.size());
        assertEquals(3, (int)tree.right(tree.root()).getElement().getKey()); //3 is left child of 1
        tree.put(2, "two");
        assertEquals(3, tree.size());
        assertEquals(2, (int)tree.root().getElement().getKey()); //two shuffles to be the new root
        assertEquals(3, (int)tree.right(tree.root()).getElement().getKey()); //3 shifts to right child of root now
        assertEquals(1, (int)tree.left(tree.root()).getElement().getKey()); //3 shifts to right child of root now

        tree.remove(1);
        tree.remove(2);
        tree.remove(3);
        
        tree.put(3, "three"); // trying double rotation from left
        assertEquals(1, tree.size());
        assertEquals(3, (int)tree.root().getElement().getKey()); //3 is initial root
        tree.put(1, "one");
        assertEquals(2, tree.size());
        assertEquals(1, (int)tree.left(tree.root()).getElement().getKey()); //1 is left child of 3
        tree.put(2, "two");
        assertEquals(3, tree.size());
        assertEquals(2, (int)tree.root().getElement().getKey()); //two shuffles to be the new root
        assertEquals(3, (int)tree.right(tree.root()).getElement().getKey()); //3 shifts to right child of root now
        assertEquals(1, (int)tree.left(tree.root()).getElement().getKey()); //3 shifts to right child of root now
        
        tree2 = new AVLTreeMap<Integer, String>(null);
        tree2.put(2, "two");
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(tree.isEmpty());
        tree.put(0, "zero");
        assertEquals(1, tree.size());
        tree.put(1, "one");
        assertEquals(2, tree.size());
        tree.put(2, "two");
        assertEquals(3, tree.size());
        tree.put(3, "three");
        assertEquals(4, tree.size());
        
        assertEquals("zero", tree.get(0));
        assertEquals("one", tree.get(1));
        assertEquals("two", tree.get(2));
        assertEquals("three", tree.get(3));

    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(tree.isEmpty());
        
        // You should create test cases to check all the
        // trinode restructuring scenarios. The textbook has visual examples
        // that you can use to create your test cases
        
        // You should check the specific keys in each node after adding or
        // removing from the tree. For example, you might use:
        //  assertEquals(4, (int)tree.root().getElement().getKey());
        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
        tree.put(5, "five");
        assertEquals(1, tree.size());
        tree.put(1, "one");
        assertEquals(2, tree.size());
        tree.put(9, "nine");
        assertEquals(3, tree.size());
        tree.put(3, "three");
        assertEquals(4, tree.size());
        tree.put(7, "seven");
        assertEquals(5, tree.size());
        tree.put(8, "eight");
        assertEquals(6, tree.size());
        
        assertEquals(5, (int)tree.root().getElement().getKey()); //checking structure of tree
        assertEquals(1, (int)tree.left(tree.root()).getElement().getKey()); //checking structure of tree
        assertEquals(3, (int)tree.right(tree.left(tree.root())).getElement().getKey()); //checking structure of tree
        assertEquals(8, (int)tree.right(tree.root()).getElement().getKey()); //checking structure of tree
        assertEquals(7, (int)tree.left(tree.right(tree.root())).getElement().getKey()); //checking structure of tree
        assertEquals(9, (int)tree.right(tree.right(tree.root())).getElement().getKey()); //checking structure of tree
        
        assertEquals("three", tree.remove(3));
        assertEquals("one", tree.remove(1)); //removed left subtree
        
        assertEquals(8, (int)tree.root().getElement().getKey()); //checking new tree structure
        assertEquals(5, (int)tree.left(tree.root()).getElement().getKey()); 
        assertEquals(7, (int)tree.right(tree.left(tree.root())).getElement().getKey()); 
        assertEquals(9, (int)tree.right(tree.root()).getElement().getKey()); 

        assertEquals("nine", tree.remove(9)); //remove right subtree
        
        assertEquals(7, (int)tree.root().getElement().getKey()); //checking new structure
        assertEquals(8, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(5, (int)tree.left(tree.root()).getElement().getKey());

        assertEquals("seven", tree.remove(7)); //removing root
        
        assertEquals(8, (int)tree.root().getElement().getKey()); //checking new structure
        assertEquals(5, (int)tree.left(tree.root()).getElement().getKey());
        assertNull(tree.right(tree.root()).getElement());
        
    }
}
