package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;

/**
 * Test class for EdgeListGraph
 * Checks the expected outputs of the Graph abstract data type behaviors when using
 * an edge list graph data structure
 *
 * @author Dr. King
 *
 */
public class AdjacencyMapGraphTest {
	/** undirected graph for testing */
    private Graph<String, Integer> undirectedGraph;
    /** directed graph for testing */
    private Graph<String, Integer> directedGraph;
    
    /**
     * Create a new instance of an edge list graph before each test case executes
     */ 
    @Before
    public void setUp() {
        undirectedGraph = new AdjacencyMapGraph<String, Integer>();
        directedGraph = new AdjacencyMapGraph<String, Integer>(true);
    }
    
    private void buildUndirectedSample() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        
        undirectedGraph.insertEdge(v1, v2, 5);
        undirectedGraph.insertEdge(v1, v3, 10);
        undirectedGraph.insertEdge(v1, v4, 15);
        undirectedGraph.insertEdge(v1, v5, 20);
        undirectedGraph.insertEdge(v2, v3, 25);
        undirectedGraph.insertEdge(v2, v4, 30);
        undirectedGraph.insertEdge(v2, v5, 35);
        undirectedGraph.insertEdge(v3, v4, 40);
        undirectedGraph.insertEdge(v3, v5, 45);
        undirectedGraph.insertEdge(v4, v5, 50);
    }
    
    private void buildDirectedSample() {
        Vertex<String> v1 = directedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = directedGraph.insertVertex("Asheville");
        Vertex<String> v3 = directedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = directedGraph.insertVertex("Durham");
        Vertex<String> v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        
        directedGraph.insertEdge(v1, v2, 5);
        directedGraph.insertEdge(v1, v3, 10);
        directedGraph.insertEdge(v1, v4, 15);
        directedGraph.insertEdge(v1, v5, 20);
        directedGraph.insertEdge(v2, v3, 25);
        directedGraph.insertEdge(v2, v4, 30);
        directedGraph.insertEdge(v2, v5, 35);
        directedGraph.insertEdge(v3, v4, 40);
        directedGraph.insertEdge(v3, v5, 45);
        directedGraph.insertEdge(v4, v5, 50);
        directedGraph.insertEdge(v5, v6, 55);
    }

    /**
     * Test the output of the numVertices() behavior
     */     
    @Test
    public void testNumVertices() {
        buildUndirectedSample();
        assertEquals(5, undirectedGraph.numVertices());
        
        buildDirectedSample();       
        assertEquals(6, directedGraph.numVertices());
    }

    /**
     * Test the output of the vertices() behavior
     */ 
    @Test
    public void testVertices() {
        // We cannot call buildUndirectedSample() because
        // then we would not be able to reference specific edges
        // or vertices when testing
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        undirectedGraph.insertEdge(v1, v2, 5);
        undirectedGraph.insertEdge(v1, v3, 10);
        undirectedGraph.insertEdge(v1, v4, 15);
        undirectedGraph.insertEdge(v1, v5, 20);
        undirectedGraph.insertEdge(v2, v3, 25);
        undirectedGraph.insertEdge(v2, v4, 30);
        undirectedGraph.insertEdge(v2, v5, 35);
        undirectedGraph.insertEdge(v3, v4, 40);
        undirectedGraph.insertEdge(v3, v5, 45);
        undirectedGraph.insertEdge(v4, v5, 50);
        
        Iterator<Vertex<String>> it = undirectedGraph.vertices().iterator();
        assertTrue(it.hasNext());
        assertEquals("Raleigh", it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals("Asheville", it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals("Wilmington", it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals("Durham", it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals("Greenville", it.next().getElement());
        
        // DIRECTED
        // We cannot call buildDirectedSample() because
        // then we would not be able to reference specific edges
        // or vertices when testing     
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        directedGraph.insertEdge(v1, v2, 5);
        directedGraph.insertEdge(v1, v3, 10);
        directedGraph.insertEdge(v1, v4, 15);
        directedGraph.insertEdge(v1, v5, 20);
        directedGraph.insertEdge(v2, v3, 25);
        directedGraph.insertEdge(v2, v4, 30);
        directedGraph.insertEdge(v2, v5, 35);
        directedGraph.insertEdge(v3, v4, 40);
        directedGraph.insertEdge(v3, v5, 45);
        directedGraph.insertEdge(v4, v5, 50);
        directedGraph.insertEdge(v5, v6, 55);
        
        Iterator<Vertex<String>> it2 = directedGraph.vertices().iterator();
        assertTrue(it2.hasNext());
        assertEquals("Raleigh", it2.next().getElement());
        assertTrue(it2.hasNext());
        assertEquals("Asheville", it2.next().getElement());
        assertTrue(it2.hasNext());
        assertEquals("Wilmington", it2.next().getElement());
        assertTrue(it2.hasNext());
        assertEquals("Durham", it2.next().getElement());
        assertTrue(it2.hasNext());
        assertEquals("Greenville", it2.next().getElement());    
        assertTrue(it2.hasNext());
        assertEquals("Boone", it2.next().getElement());
    
    }

    /**
     * Test the output of the numEdges() behavior
     */ 
    @Test
    public void testNumEdges() {
    	buildUndirectedSample();
        assertEquals(10, undirectedGraph.numEdges());

        buildDirectedSample();
        assertEquals(11, directedGraph.numEdges());
    }

    /**
     * Test the output of the edges() behavior
     */ 
    @Test
    public void testEdges() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        Iterator<Edge<Integer>> it = undirectedGraph.edges().iterator();
        assertTrue(it.hasNext());
        Edge<Integer>[] edges = (Edge<Integer>[])(new Edge[10]);
        int count = 0;
        while(it.hasNext()) {
        	edges[count++] = it.next();
        }
        assertTrue(arrayContains(edges, e1));
        assertTrue(arrayContains(edges, e2));
        assertTrue(arrayContains(edges, e3));
        assertTrue(arrayContains(edges, e4));
        assertTrue(arrayContains(edges, e5));
        assertTrue(arrayContains(edges, e6));
        assertTrue(arrayContains(edges, e7));
        assertTrue(arrayContains(edges, e8));
        assertTrue(arrayContains(edges, e9));
        assertTrue(arrayContains(edges, e10));

        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        Iterator<Edge<Integer>> it2 = directedGraph.edges().iterator();
        assertTrue(it2.hasNext());
        assertEquals(e1, it2.next());
        assertTrue(it2.hasNext());
        assertEquals(e2, it2.next());
        assertTrue(it2.hasNext());
        assertEquals(e3, it2.next());
        assertTrue(it2.hasNext());
        assertEquals(e4, it2.next());
        assertTrue(it2.hasNext());
        assertEquals(e5, it2.next());
        assertTrue(it2.hasNext());
        assertEquals(e6, it2.next());
        assertTrue(it2.hasNext());
        assertEquals(e7, it2.next());
        assertTrue(it2.hasNext());
        assertEquals(e8, it2.next());
        assertTrue(it2.hasNext());
        assertEquals(e9, it2.next());
        assertTrue(it2.hasNext());
        assertEquals(e10, it2.next());
        assertTrue(it2.hasNext());
        assertEquals(e11, it2.next());
    }

    /**
     * Test the output of the getEdge(v1,v2) behavior
     */ 
    @Test
    public void testGetEdge() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        assertEquals(e1, undirectedGraph.getEdge(v1, v2));
        assertEquals(e2, undirectedGraph.getEdge(v1, v3));
        assertEquals(e3, undirectedGraph.getEdge(v1, v4));
        assertEquals(e4, undirectedGraph.getEdge(v1, v5));
        assertEquals(e5, undirectedGraph.getEdge(v2, v3));
        assertEquals(e6, undirectedGraph.getEdge(v2, v4));
        assertEquals(e7, undirectedGraph.getEdge(v2, v5));
        assertEquals(e8, undirectedGraph.getEdge(v3, v4));
        assertEquals(e9, undirectedGraph.getEdge(v3, v5));
        assertEquals(e10, undirectedGraph.getEdge(v4, v5));
        
        assertEquals(undirectedGraph.getEdge(v1, v2), undirectedGraph.getEdge(v2, v1)); //testing order does not matter (undirected)
        assertEquals(undirectedGraph.getEdge(v3, v1), undirectedGraph.getEdge(v1, v3)); //testing order does not matter (undirected)
        assertEquals(undirectedGraph.getEdge(v2, v3), undirectedGraph.getEdge(v3, v2)); //testing order does not matter (undirected)
        assertEquals(undirectedGraph.getEdge(v4, v5), undirectedGraph.getEdge(v5, v4)); //testing order does not matter (undirected)

        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        assertEquals(e1, directedGraph.getEdge(v1, v2));
        assertEquals(e2, directedGraph.getEdge(v1, v3));
        assertEquals(e3, directedGraph.getEdge(v1, v4));
        assertEquals(e4, directedGraph.getEdge(v1, v5));
        assertEquals(e5, directedGraph.getEdge(v2, v3));
        assertEquals(e6, directedGraph.getEdge(v2, v4));
        assertEquals(e7, directedGraph.getEdge(v2, v5));
        assertEquals(e8, directedGraph.getEdge(v3, v4));
        assertEquals(e9, directedGraph.getEdge(v3, v5));
        assertEquals(e10, directedGraph.getEdge(v4, v5));
        assertEquals(e11, directedGraph.getEdge(v5, v6));
        
        assertNotEquals(directedGraph.getEdge(v1, v2), directedGraph.getEdge(v2, v1)); //asserting that order matters (directed)
        assertNotEquals(directedGraph.getEdge(v3, v1), directedGraph.getEdge(v1, v3)); 
        assertNotEquals(directedGraph.getEdge(v2, v3), directedGraph.getEdge(v3, v2)); 
        assertNotEquals(directedGraph.getEdge(v4, v5), directedGraph.getEdge(v5, v4));    
        
    }

    /**
     * Test the output of the endVertices(e) behavior
     */ 
    @Test
    public void testEndVertices() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        Vertex<String>[] a1 = undirectedGraph.endVertices(e1);
        assertEquals(2, a1.length);
        assertEquals(v1, a1[0]);
        assertEquals(v2, a1[1]);
        Vertex<String>[] a2 = undirectedGraph.endVertices(e2);
        assertEquals(2, a2.length);
        assertEquals(v1, a2[0]);
        assertEquals(v3, a2[1]);
        Vertex<String>[] a3 = undirectedGraph.endVertices(e3);
        assertEquals(2, a3.length);
        assertEquals(v1, a3[0]);
        assertEquals(v4, a3[1]);
        Vertex<String>[] a4 = undirectedGraph.endVertices(e4);
        assertEquals(2, a4.length);
        assertEquals(v1, a4[0]);
        assertEquals(v5, a4[1]);
        Vertex<String>[] a5 = undirectedGraph.endVertices(e5);
        assertEquals(2, a5.length);
        assertEquals(v2, a5[0]);
        assertEquals(v3, a5[1]);
        Vertex<String>[] a6 = undirectedGraph.endVertices(e6);
        assertEquals(2, a6.length);
        assertEquals(v2, a6[0]);
        assertEquals(v4, a6[1]);
        Vertex<String>[] a7 = undirectedGraph.endVertices(e7);
        assertEquals(2, a7.length);
        assertEquals(v2, a7[0]);
        assertEquals(v5, a7[1]);
        Vertex<String>[] a8 = undirectedGraph.endVertices(e8);
        assertEquals(2, a8.length);
        assertEquals(v3, a8[0]);
        assertEquals(v4, a8[1]);
        Vertex<String>[] a9 = undirectedGraph.endVertices(e9);
        assertEquals(2, a9.length);
        assertEquals(v3, a9[0]);
        assertEquals(v5, a9[1]);
        Vertex<String>[] a10 = undirectedGraph.endVertices(e10);
        assertEquals(2, a10.length);
        assertEquals(v4, a10[0]);
        assertEquals(v5, a10[1]);
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        Vertex<String>[] b1 = directedGraph.endVertices(e1);
        assertEquals(2, b1.length);
        assertEquals(v1, b1[0]);
        assertEquals(v2, b1[1]);
        Vertex<String>[] b2 = directedGraph.endVertices(e2);
        assertEquals(2, b2.length);
        assertEquals(v1, b2[0]);
        assertEquals(v3, b2[1]);
        Vertex<String>[] b3 = directedGraph.endVertices(e3);
        assertEquals(2, b3.length);
        assertEquals(v1, b3[0]);
        assertEquals(v4, b3[1]);
        Vertex<String>[] b4 = directedGraph.endVertices(e4);
        assertEquals(2, b4.length);
        assertEquals(v1, b4[0]);
        assertEquals(v5, b4[1]);
        Vertex<String>[] b5 = directedGraph.endVertices(e5);
        assertEquals(2, b5.length);
        assertEquals(v2, b5[0]);
        assertEquals(v3, b5[1]);
        Vertex<String>[] b6 = directedGraph.endVertices(e6);
        assertEquals(2, b6.length);
        assertEquals(v2, b6[0]);
        assertEquals(v4, b6[1]);
        Vertex<String>[] b7 = directedGraph.endVertices(e7);
        assertEquals(2, b7.length);
        assertEquals(v2, b7[0]);
        assertEquals(v5, b7[1]);
        Vertex<String>[] b8 = directedGraph.endVertices(e8);
        assertEquals(2, b8.length);
        assertEquals(v3, b8[0]);
        assertEquals(v4, b8[1]);
        Vertex<String>[] b9 = directedGraph.endVertices(e9);
        assertEquals(2, b9.length);
        assertEquals(v3, b9[0]);
        assertEquals(v5, b9[1]);
        Vertex<String>[] b10 = directedGraph.endVertices(e10);
        assertEquals(2, b10.length);
        assertEquals(v4, b10[0]);
        assertEquals(v5, b10[1]);    
        Vertex<String>[] b11 = directedGraph.endVertices(e11);
        assertEquals(2, b11.length);
        assertEquals(v5, b11[0]);
        assertEquals(v6, b11[1]);
    
    }

    /**
     * Test the output of the opposite(v, e) behavior
     */ 
    @Test
    public void testOpposite() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        assertEquals(v2, undirectedGraph.opposite(v1, e1));
        assertEquals(v1, undirectedGraph.opposite(v2, e1));
        assertEquals(v1, undirectedGraph.opposite(v3, e2));
        assertEquals(v3, undirectedGraph.opposite(v1, e2));
        assertEquals(v1, undirectedGraph.opposite(v4, e3));
        assertEquals(v4, undirectedGraph.opposite(v1, e3));
        assertEquals(v1, undirectedGraph.opposite(v5, e4));
        assertEquals(v5, undirectedGraph.opposite(v1, e4));
        assertEquals(v2, undirectedGraph.opposite(v3, e5));
        assertEquals(v3, undirectedGraph.opposite(v2, e5));
        assertEquals(v2, undirectedGraph.opposite(v4, e6));
        assertEquals(v4, undirectedGraph.opposite(v2, e6));
        assertEquals(v2, undirectedGraph.opposite(v5, e7));
        assertEquals(v5, undirectedGraph.opposite(v2, e7));
        assertEquals(v3, undirectedGraph.opposite(v4, e8));
        assertEquals(v4, undirectedGraph.opposite(v3, e8));
        assertEquals(v3, undirectedGraph.opposite(v5, e9));
        assertEquals(v5, undirectedGraph.opposite(v3, e9));
        assertEquals(v4, undirectedGraph.opposite(v5, e10));
        assertEquals(v5, undirectedGraph.opposite(v4, e10));

        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        assertEquals(v2, directedGraph.opposite(v1, e1));
        assertEquals(v1, directedGraph.opposite(v2, e1));
        assertEquals(v1, directedGraph.opposite(v3, e2));
        assertEquals(v3, directedGraph.opposite(v1, e2));
        assertEquals(v1, directedGraph.opposite(v4, e3));
        assertEquals(v4, directedGraph.opposite(v1, e3));
        assertEquals(v1, directedGraph.opposite(v5, e4));
        assertEquals(v5, directedGraph.opposite(v1, e4));
        assertEquals(v2, directedGraph.opposite(v3, e5));
        assertEquals(v3, directedGraph.opposite(v2, e5));
        assertEquals(v2, directedGraph.opposite(v4, e6));
        assertEquals(v4, directedGraph.opposite(v2, e6));
        assertEquals(v2, directedGraph.opposite(v5, e7));
        assertEquals(v5, directedGraph.opposite(v2, e7));
        assertEquals(v3, directedGraph.opposite(v4, e8));
        assertEquals(v4, directedGraph.opposite(v3, e8));
        assertEquals(v3, directedGraph.opposite(v5, e9));
        assertEquals(v5, directedGraph.opposite(v3, e9));
        assertEquals(v4, directedGraph.opposite(v5, e10));
        assertEquals(v5, directedGraph.opposite(v4, e10));    
        assertEquals(v5, directedGraph.opposite(v6, e11));    
        assertEquals(v6, directedGraph.opposite(v5, e11));    

    }

    /**
     * Test the output of the outDegree(v) behavior
     */ 
    @Test
    public void testOutDegree() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        assertEquals(4, undirectedGraph.outDegree(v1));
        assertEquals(4, undirectedGraph.outDegree(v2));
        assertEquals(4, undirectedGraph.outDegree(v3));
        assertEquals(4, undirectedGraph.outDegree(v4));
        assertEquals(4, undirectedGraph.outDegree(v1));
        assertEquals(4, undirectedGraph.outDegree(v1));

        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        assertEquals(4, directedGraph.outDegree(v1));
        assertEquals(3, directedGraph.outDegree(v2));
        assertEquals(2, directedGraph.outDegree(v3));
        assertEquals(1, directedGraph.outDegree(v4));
        assertEquals(1, directedGraph.outDegree(v5));
        assertEquals(0, directedGraph.outDegree(v6));
        assertNotEquals(e1, e2);
        assertNotEquals(e3, e4);
        assertNotEquals(e5, e6);
        assertNotEquals(e7, e8);
        assertNotEquals(e9, e10);
        assertNotEquals(e11, e10);

    }

    /**
     * Test the output of the inDegree(v) behavior
     */ 
    @Test
    public void testInDegree() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        assertEquals(4, undirectedGraph.inDegree(v1));
        assertEquals(4, undirectedGraph.inDegree(v2));
        assertEquals(4, undirectedGraph.inDegree(v3));
        assertEquals(4, undirectedGraph.inDegree(v4));
        assertEquals(4, undirectedGraph.inDegree(v5));
        assertEquals(0, undirectedGraph.inDegree(v6));
        assertNotEquals(e1, e2);
        assertNotEquals(e3, e4);
        assertNotEquals(e5, e6);
        assertNotEquals(e7, e8);
        assertNotEquals(e9, e10);


        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        assertEquals(0, directedGraph.inDegree(v1));
        assertEquals(1, directedGraph.inDegree(v2));
        assertEquals(2, directedGraph.inDegree(v3));
        assertEquals(3, directedGraph.inDegree(v4));
        assertEquals(4, directedGraph.inDegree(v5));
        assertEquals(1, directedGraph.inDegree(v6));
        assertNotEquals(e1, e11);
    }

    /**
     * Test the output of the outgoingEdges(v) behavior
     */ 
    @SuppressWarnings("unchecked")
    @Test
    public void testOutgoingEdges() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        // We can use a custom arrayContains() helper method to check that
        // an array *contains* a certain target edge.
        // This is helpful for testing graph ADT behaviors where an order
        // of edges cannot be guaranteed (such as .outgoingEdges or .incomingEdges
        // in adjacencyMaps, etc.)      
        Edge<Integer>[] temp = (Edge<Integer>[])(new Edge[4]);
        int count = 0;
        Iterator<Edge<Integer>> it = undirectedGraph.outgoingEdges(v1).iterator();
        assertTrue(it.hasNext());
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        assertFalse(it.hasNext());
        assertTrue(arrayContains(temp, e1));
        assertTrue(arrayContains(temp, e2));
        assertTrue(arrayContains(temp, e3));
        assertTrue(arrayContains(temp, e4));
        
        count = 0;
        Edge<Integer>[] temp2 = (Edge<Integer>[])(new Edge[4]);
        Iterator<Edge<Integer>> it2 = undirectedGraph.outgoingEdges(v2).iterator();
        assertTrue(it2.hasNext());
        temp2[count] = it2.next();
        count++;
        temp2[count] = it2.next();
        count++;
        temp2[count] = it2.next();
        count++;
        temp2[count] = it2.next();
        count++;

        assertFalse(it2.hasNext());
        assertTrue(arrayContains(temp2, e1));
        assertTrue(arrayContains(temp2, e5));
        assertTrue(arrayContains(temp2, e6));
        assertTrue(arrayContains(temp2, e7));
        
        count = 0;
        Edge<Integer>[] temp3 = (Edge<Integer>[])(new Edge[4]);
        Iterator<Edge<Integer>> it3 = undirectedGraph.outgoingEdges(v3).iterator();
        assertTrue(it3.hasNext());
        temp3[count] = it3.next();
        count++;
        temp3[count] = it3.next();
        count++;
        temp3[count] = it3.next();
        count++;
        temp3[count] = it3.next();
        count++;

        assertFalse(it3.hasNext());
        assertTrue(arrayContains(temp3, e2));
        assertTrue(arrayContains(temp3, e5));
        assertTrue(arrayContains(temp3, e8));
        assertTrue(arrayContains(temp3, e9));

        count = 0;
        Edge<Integer>[] temp4 = (Edge<Integer>[])(new Edge[4]);
        Iterator<Edge<Integer>> it4 = undirectedGraph.outgoingEdges(v4).iterator();
        assertTrue(it4.hasNext());
        temp4[count] = it4.next();
        count++;
        temp4[count] = it4.next();
        count++;
        temp4[count] = it4.next();
        count++;
        temp4[count] = it4.next();
        count++;

        assertFalse(it4.hasNext());
        assertTrue(arrayContains(temp4, e3));
        assertTrue(arrayContains(temp4, e6));
        assertTrue(arrayContains(temp4, e8));
        assertTrue(arrayContains(temp4, e10));
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        
        Edge<Integer>[] temp5 = (Edge<Integer>[])(new Edge[4]);
        count = 0;
        Iterator<Edge<Integer>> it5 = directedGraph.outgoingEdges(v1).iterator();
        assertTrue(it5.hasNext());
        temp5[count] = it5.next();
        count++;
        temp5[count] = it5.next();
        count++;
        temp5[count] = it5.next();
        count++;
        temp5[count] = it5.next();
        count++;
        assertFalse(it5.hasNext());
        assertTrue(arrayContains(temp5, e1));
        assertTrue(arrayContains(temp5, e2));
        assertTrue(arrayContains(temp5, e3));
        assertTrue(arrayContains(temp5, e4));

        Edge<Integer>[] temp6 = (Edge<Integer>[])(new Edge[4]);
        count = 0;
        Iterator<Edge<Integer>> ti2 = directedGraph.outgoingEdges(v2).iterator();
        temp6[count] = ti2.next();
        count++;
        temp6[count] = ti2.next();
        count++;
        temp6[count] = ti2.next();
        count++;
        assertFalse(ti2.hasNext());
        assertTrue(arrayContains(temp6, e5));
        assertTrue(arrayContains(temp6, e6));
        assertTrue(arrayContains(temp6, e7));
        
        Edge<Integer>[] temp7 = (Edge<Integer>[])(new Edge[4]);
        count = 0;
        Iterator<Edge<Integer>> ti3 = directedGraph.outgoingEdges(v3).iterator();
        temp7[count] = ti3.next();
        count++;
        temp7[count] = ti3.next();
        count++;
        assertFalse(ti3.hasNext());
        assertTrue(arrayContains(temp7, e8));
        assertTrue(arrayContains(temp7, e9));

        Edge<Integer>[] temp8 = (Edge<Integer>[])(new Edge[4]);
        count = 0;
        Iterator<Edge<Integer>> ti4 = directedGraph.outgoingEdges(v4).iterator();
        temp8[count] = ti4.next();
        count++;
        assertFalse(ti4.hasNext());
        assertTrue(arrayContains(temp8, e10));

        Edge<Integer>[] temp9 = (Edge<Integer>[])(new Edge[4]);
        count = 0;
        Iterator<Edge<Integer>> ti5 = directedGraph.outgoingEdges(v5).iterator();
        temp9[count] = ti5.next();
        count++;
        assertFalse(ti5.hasNext());
        assertTrue(arrayContains(temp9, e11));
    }
    
    // Helper method to check that an array contains a certain target.
    // This is helpful for testing graph ADT behaviors where an order
    // of edges cannot be guaranteed (such as .outgoingEdges or .incomingEdges)
    private boolean arrayContains(Edge<Integer>[] temp, Edge<Integer> target) {
        for(Edge<Integer> e : temp) {
            if(e == target) {
                return true;
            }
        }
        return false;
    }

    /**
     * Test the output of the incomingEdges(v) behavior
     */ 
    @SuppressWarnings("unchecked")
    @Test
    public void testIncomingEdges() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        Edge<Integer>[] temp = (Edge<Integer>[])(new Edge[4]);
        int count = 0;
        Iterator<Edge<Integer>> it = undirectedGraph.incomingEdges(v1).iterator();
        assertTrue(it.hasNext());
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        assertFalse(it.hasNext());
        assertTrue(arrayContains(temp, e1));
        assertTrue(arrayContains(temp, e2));
        assertTrue(arrayContains(temp, e3));
        assertTrue(arrayContains(temp, e4));
        
        count = 0;
        Edge<Integer>[] temp2 = (Edge<Integer>[])(new Edge[4]);
        Iterator<Edge<Integer>> it2 = undirectedGraph.incomingEdges(v2).iterator();
        assertTrue(it2.hasNext());
        temp2[count] = it2.next();
        count++;
        temp2[count] = it2.next();
        count++;
        temp2[count] = it2.next();
        count++;
        temp2[count] = it2.next();
        count++;

        assertFalse(it2.hasNext());
        assertTrue(arrayContains(temp2, e1));
        assertTrue(arrayContains(temp2, e5));
        assertTrue(arrayContains(temp2, e6));
        assertTrue(arrayContains(temp2, e7));
        
        count = 0;
        Edge<Integer>[] temp3 = (Edge<Integer>[])(new Edge[4]);
        Iterator<Edge<Integer>> it3 = undirectedGraph.incomingEdges(v3).iterator();
        assertTrue(it3.hasNext());
        temp3[count] = it3.next();
        count++;
        temp3[count] = it3.next();
        count++;
        temp3[count] = it3.next();
        count++;
        temp3[count] = it3.next();
        count++;

        assertFalse(it3.hasNext());
        assertTrue(arrayContains(temp3, e2));
        assertTrue(arrayContains(temp3, e5));
        assertTrue(arrayContains(temp3, e8));
        assertTrue(arrayContains(temp3, e9));

        count = 0;
        Edge<Integer>[] temp4 = (Edge<Integer>[])(new Edge[4]);
        Iterator<Edge<Integer>> it4 = undirectedGraph.incomingEdges(v4).iterator();
        assertTrue(it4.hasNext());
        temp4[count] = it4.next();
        count++;
        temp4[count] = it4.next();
        count++;
        temp4[count] = it4.next();
        count++;
        temp4[count] = it4.next();
        count++;

        assertFalse(it4.hasNext());
        assertTrue(arrayContains(temp4, e3));
        assertTrue(arrayContains(temp4, e6));
        assertTrue(arrayContains(temp4, e8));
        assertTrue(arrayContains(temp4, e10));
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        count = 0;
        Iterator<Edge<Integer>> it5 = directedGraph.incomingEdges(v1).iterator();
        assertFalse(it5.hasNext());
      
        Edge<Integer>[] temp6 = (Edge<Integer>[])(new Edge[4]);
        count = 0;
        Iterator<Edge<Integer>> ti2 = directedGraph.incomingEdges(v2).iterator();
        assertTrue(ti2.hasNext());
        temp6[count] = ti2.next();
        count++;
        assertFalse(ti2.hasNext());
        assertTrue(arrayContains(temp6, e1));
        
        Edge<Integer>[] temp7 = (Edge<Integer>[])(new Edge[4]);
        count = 0;
        Iterator<Edge<Integer>> ti3 = directedGraph.incomingEdges(v3).iterator();
        assertTrue(ti3.hasNext());
        temp7[count] = ti3.next();
        count++;
        temp7[count] = ti3.next();
        count++;
        assertFalse(ti3.hasNext());
        assertTrue(arrayContains(temp7, e2));
        assertTrue(arrayContains(temp7, e5));
        assertNotEquals(e1, e11);

        Edge<Integer>[] temp8 = (Edge<Integer>[])(new Edge[4]);
        count = 0;
        Iterator<Edge<Integer>> ti4 = directedGraph.incomingEdges(v4).iterator();
        assertTrue(ti4.hasNext());
        temp8[count] = ti4.next();
        count++;
        temp8[count] = ti4.next();
        count++;
        temp8[count] = ti4.next();
        count++;
        assertFalse(ti4.hasNext());
        assertTrue(arrayContains(temp8, e3));
        assertTrue(arrayContains(temp8, e6));
        assertTrue(arrayContains(temp8, e8));

        Edge<Integer>[] temp9 = (Edge<Integer>[])(new Edge[4]);
        count = 0;
        Iterator<Edge<Integer>> ti5 = directedGraph.incomingEdges(v5).iterator();
        assertTrue(ti5.hasNext());
        temp9[count] = ti5.next();
        count++;
        temp9[count] = ti5.next();
        count++;
        temp9[count] = ti5.next();
        count++;
        temp9[count] = ti5.next();
        count++;
        assertFalse(ti5.hasNext());
        assertTrue(arrayContains(temp9, e4));    
        assertTrue(arrayContains(temp9, e7));    
        assertTrue(arrayContains(temp9, e9));    
        assertTrue(arrayContains(temp9, e10));    

    }

    /**
     * Test the output of the insertVertex(x) behavior
     */ 
    @Test
    public void testInsertVertex() {
        assertEquals(0, undirectedGraph.numVertices());
        Vertex<String> v1 = undirectedGraph.insertVertex("Fayetteville");
        assertEquals(1, undirectedGraph.numVertices());
        
        Iterator<Vertex<String>> it = undirectedGraph.vertices().iterator();
        assertTrue(it.hasNext());
        assertEquals(v1, it.next());
        assertFalse(it.hasNext());      

        assertEquals(1, undirectedGraph.numVertices());
        Vertex<String> v2 = undirectedGraph.insertVertex("Compton");
        assertEquals(2, undirectedGraph.numVertices());
        Vertex<String> v3 = undirectedGraph.insertVertex("Fuquay");
        assertEquals(3, undirectedGraph.numVertices());
        Vertex<String> v4 = undirectedGraph.insertVertex("Holly Springs");
        assertEquals(4, undirectedGraph.numVertices());
        Vertex<String> v5 = undirectedGraph.insertVertex("Raleigh");
        assertEquals(5, undirectedGraph.numVertices());

        Iterator<Vertex<String>> it2 = undirectedGraph.vertices().iterator();
        assertEquals(v1, it2.next());
        assertEquals(v2, it2.next());
        assertEquals(v3, it2.next());
        assertEquals(v4, it2.next());
        assertEquals(v5, it2.next());
        assertFalse(it2.hasNext());      

        assertEquals(0, directedGraph.numVertices());
        Vertex<String> v6 = directedGraph.insertVertex("Fayetteville");
        assertEquals(1, directedGraph.numVertices());
        Vertex<String> v7 = directedGraph.insertVertex("Fuquay");
        assertEquals(2, directedGraph.numVertices());
        Vertex<String> v8 = directedGraph.insertVertex("Holly Springs");
        assertEquals(3, directedGraph.numVertices());
        Vertex<String> v9 = directedGraph.insertVertex("Raleigh");
        assertEquals(4, directedGraph.numVertices());
        Vertex<String> v10 = directedGraph.insertVertex("Compton");
        assertEquals(5, directedGraph.numVertices());

        Iterator<Vertex<String>> it3 = directedGraph.vertices().iterator();
        assertEquals(v6, it3.next());
        assertEquals(v7, it3.next());
        assertEquals(v8, it3.next());
        assertEquals(v9, it3.next());
        assertEquals(v10, it3.next());
        assertFalse(it3.hasNext());
    }

    /**
     * Test the output of the insertEdge(v1, v2, x) behavior
     */ 
    @Test
    public void testInsertEdge() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Fuquay");
        
        assertEquals(0, undirectedGraph.numEdges());
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 99);
        assertEquals(1, undirectedGraph.numEdges());
        Edge<Integer>[] temp = (Edge<Integer>[])(new Edge[1]);
        int count = 0;
        Iterator<Edge<Integer>> it = undirectedGraph.edges().iterator();
        assertTrue(it.hasNext());
        temp[count] = it.next();
        count++;
        assertTrue(arrayContains(temp, e1));    
        
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 75);
        assertEquals(2, undirectedGraph.numEdges());
        Edge<Integer> e3 = undirectedGraph.insertEdge(v2, v3, 50);
        assertEquals(3, undirectedGraph.numEdges());
        Edge<Integer>[] temp2 = (Edge<Integer>[])(new Edge[3]);
        count = 0;
        Iterator<Edge<Integer>> it2 = undirectedGraph.edges().iterator();
        assertTrue(it2.hasNext());
        temp2[count] = it2.next();
        count++;
        temp2[count] = it2.next();
        count++;
        temp2[count] = it2.next();
        count++;
        assertTrue(arrayContains(temp2, e2));
        assertTrue(arrayContains(temp2, e3));


        Vertex<String> dv1 = directedGraph.insertVertex("Raleigh");
        Vertex<String> dv2 = directedGraph.insertVertex("Asheville");
        Vertex<String> dv3 = directedGraph.insertVertex("Fuquay");
        
        assertEquals(0, directedGraph.numEdges());
        Edge<Integer> de1 = directedGraph.insertEdge(dv1, dv2, 99);
        assertEquals(1, directedGraph.numEdges());
        Edge<Integer> de2 = directedGraph.insertEdge(dv1, dv3, 75);
        assertEquals(2, directedGraph.numEdges());
        Edge<Integer> de3 = directedGraph.insertEdge(dv2, dv3, 50);
        assertEquals(3, directedGraph.numEdges());
        Edge<Integer>[] temp3 = (Edge<Integer>[])(new Edge[3]);
        count = 0;
        Iterator<Edge<Integer>> it3 = directedGraph.edges().iterator();
        assertTrue(it3.hasNext());
        temp3[count] = it3.next();
        count++;
        temp3[count] = it3.next();
        count++;
        temp3[count] = it3.next();
        count++;
        assertTrue(arrayContains(temp3, de1));
        assertTrue(arrayContains(temp3, de2));
        assertTrue(arrayContains(temp3, de3));

    }

    /**
     * Test the output of the removeVertex(v) behavior
     */ 
    @Test
    public void testRemoveVertex() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        assertEquals(5, undirectedGraph.numVertices());
        assertEquals(10, undirectedGraph.numEdges());
        undirectedGraph.removeVertex(v5);
        assertEquals(4, undirectedGraph.numVertices());
        assertEquals(6, undirectedGraph.numEdges());
        undirectedGraph.removeVertex(v4);
        assertEquals(3, undirectedGraph.numVertices());
        assertEquals(3, undirectedGraph.numEdges());
        undirectedGraph.removeVertex(v3);
        assertEquals(2, undirectedGraph.numVertices());
        assertEquals(1, undirectedGraph.numEdges());
        assertNotEquals(e1, e2);
        assertNotEquals(e3, e4);
        assertNotEquals(e5, e6);
        assertNotEquals(e7, e8);
        assertNotEquals(e9, e10);
        assertEquals("Edge[element=50]", e10.toString());
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        assertEquals(6, directedGraph.numVertices());
        assertEquals(11, directedGraph.numEdges());
        directedGraph.removeVertex(v6);
        assertEquals(5, directedGraph.numVertices());
        assertEquals(10, directedGraph.numEdges());
        directedGraph.removeVertex(v5);
        assertEquals(4, directedGraph.numVertices());
        assertEquals(6, directedGraph.numEdges());
        directedGraph.removeVertex(v4);
        assertEquals(3, directedGraph.numVertices());
        assertEquals(3, directedGraph.numEdges());
        directedGraph.removeVertex(v3);
        assertEquals(2, directedGraph.numVertices());
        assertEquals(1, directedGraph.numEdges());
        assertNotEquals(e1, e11);
    }

    /**
     * Test the output of the removeEdge(e) behavior
     */ 
    @Test
    public void testRemoveEdge() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        assertEquals("Boone", v6.getElement());
        
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(10, undirectedGraph.numEdges());
        undirectedGraph.removeEdge(e1);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(9, undirectedGraph.numEdges());
        undirectedGraph.removeEdge(e2);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(8, undirectedGraph.numEdges());
        undirectedGraph.removeEdge(e3);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(7, undirectedGraph.numEdges());
        undirectedGraph.removeEdge(e4);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(6, undirectedGraph.numEdges());
        undirectedGraph.removeEdge(e5);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(5, undirectedGraph.numEdges());
        undirectedGraph.removeEdge(e6);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(4, undirectedGraph.numEdges());
        undirectedGraph.removeEdge(e7);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(3, undirectedGraph.numEdges());
        undirectedGraph.removeEdge(e8);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(2, undirectedGraph.numEdges());
        undirectedGraph.removeEdge(e9);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(1, undirectedGraph.numEdges());
        undirectedGraph.removeEdge(e10);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(0, undirectedGraph.numEdges());
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        
        assertEquals(6, directedGraph.numVertices());
        assertEquals(10, directedGraph.numEdges());
        directedGraph.removeEdge(e1);
        assertEquals(6, directedGraph.numVertices());
        assertEquals(9, directedGraph.numEdges());
        directedGraph.removeEdge(e2);
        assertEquals(6, directedGraph.numVertices());
        assertEquals(8, directedGraph.numEdges());
        directedGraph.removeEdge(e3);
        assertEquals(6, directedGraph.numVertices());
        assertEquals(7, directedGraph.numEdges());
        directedGraph.removeEdge(e4);
        assertEquals(6, directedGraph.numVertices());
        assertEquals(6, directedGraph.numEdges());
        directedGraph.removeEdge(e5);
        assertEquals(6, directedGraph.numVertices());
        assertEquals(5, directedGraph.numEdges());
        assertEquals(25, (int)e5.getElement());
    }

}
