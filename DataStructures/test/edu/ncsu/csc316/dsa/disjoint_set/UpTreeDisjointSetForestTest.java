package edu.ncsu.csc316.dsa.disjoint_set;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for UpTreeDisjointSetForest
 * Checks the expected outputs of the Disjoint Set abstract data type 
 * behaviors when using an up-tree data structure
 *
 * @author Dr. King
 *
 */
public class UpTreeDisjointSetForestTest {
	/** forest used for testing **/
    private DisjointSetForest<String> set;

    /**
     * Create a new instance of a up-tree forest before each test case executes
     */     
    @Before
    public void setUp() {
        set = new UpTreeDisjointSetForest<>();
    }
    
    /**
     * Test the output of the makeSet behavior
     */ 
    @Test
    public void testMakeSet() {
        Position<String> one = set.makeSet("one");
        assertEquals("one", one.getElement());
        Position<String> two = set.makeSet("two");
        assertEquals("two", two.getElement());
        Position<String> three = set.makeSet("three");
        assertEquals("three", three.getElement());
        Position<String> four = set.makeSet("four");
        assertEquals("four", four.getElement());
        
    }

    /**
     * Test the output of the union-find behaviors
     */     
    @Test
    public void testUnionFind() {
        Position<String> one = set.makeSet("one");
        Position<String> two = set.makeSet("two");
        Position<String> three = set.makeSet("three");
        Position<String> four = set.makeSet("four");
        Position<String> five = set.makeSet("five");
        Position<String> six = set.makeSet("six");
        
        assertEquals(one, set.find("one"));
     // Verify find operations
        assertEquals(one, set.find("one"));
        assertEquals(two, set.find("two"));
        assertEquals(three, set.find("three"));
        assertEquals(four, set.find("four"));
        assertEquals(five, set.find("five"));
        
        // Union sets
        set.union(one, two); // Union sets containing "one" and "two"
        set.union(three, four); // Union sets containing "three" and "four"
        set.union(five, six); // Union sets containing "five" and "six"
        // Verify updated find operations
        assertEquals(two, set.find("one")); // "one" and "two" are now in the same set
        assertEquals(two, set.find("two"));
        assertEquals(four, set.find("three")); // "three" and "four" are in the same set
        assertEquals(four, set.find("four"));
        assertEquals(six, set.find("five")); // "five" and "six" are in the same set
        assertEquals(six, set.find("six"));

        
    }
}
