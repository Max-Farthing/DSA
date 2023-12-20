package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for GraphTraversalUtil
 * Checks the expected outputs of depth first search
 * and breadth first search
 *
 * @author Dr. King
 *
 */
public class GraphTraversalUtilTest {
	/** map for testing */
	private Map<Vertex<String>, Edge<Integer>> map;
	/** edgelist graph used for testing */
	private EdgeListGraph<String, Integer> graph;
	
    /**
     * Test the output of depth first search on a graph
     */ 
    @Test
    public void testDepthFirstSearch() {
    	graph = new EdgeListGraph<String, Integer>();
		Vertex<String> v1 = graph.insertVertex("one");
		Vertex<String> v2 = graph.insertVertex("two");
		Vertex<String> v3 = graph.insertVertex("three");
		Vertex<String> v4 = graph.insertVertex("four");
		Vertex<String> v5 = graph.insertVertex("five");
		Edge<Integer> e1 = graph.insertEdge(v1, v2, 1);
		Edge<Integer> e2 = graph.insertEdge(v1, v3, 2);
		Edge<Integer> e3 = graph.insertEdge(v1, v4, 3);
		Edge<Integer> e4 = graph.insertEdge(v2, v3, 4);
		Edge<Integer> e5 = graph.insertEdge(v2, v4, 5);
		Edge<Integer> e6 = graph.insertEdge(v3, v4, 6);
		Edge<Integer> e7 = graph.insertEdge(v4, v5, 7);
		
    	map = GraphTraversalUtil.depthFirstSearch(graph, v1);
    	
    	assertEquals(4, map.size());
    	assertEquals(e1, map.get(v2));
    	assertEquals(e4, map.get(v3));
    	assertEquals(e6, map.get(v4));
    	assertEquals(e7, map.get(v5));
    	assertNotEquals(e2, map.get(v2));
    	assertNotEquals(e3, map.get(v3));
    	assertNotEquals(e5, map.get(v5));
    }
    
    /**
     * Test the output of the breadth first search
     */ 
    @Test
    public void testBreadthFirstSearch() {
    	graph = new EdgeListGraph<String, Integer>();
		Vertex<String> v1 = graph.insertVertex("one");
		Vertex<String> v2 = graph.insertVertex("two");
		Vertex<String> v3 = graph.insertVertex("three");
		Vertex<String> v4 = graph.insertVertex("four");
		Vertex<String> v5 = graph.insertVertex("five");
		Edge<Integer> e1 = graph.insertEdge(v1, v2, 1);
		Edge<Integer> e2 = graph.insertEdge(v1, v3, 2);
		Edge<Integer> e3 = graph.insertEdge(v1, v4, 3);
		Edge<Integer> e4 = graph.insertEdge(v2, v3, 4);
		Edge<Integer> e5 = graph.insertEdge(v2, v4, 5);
		Edge<Integer> e6 = graph.insertEdge(v3, v4, 6);
		Edge<Integer> e7 = graph.insertEdge(v4, v5, 7);
		
    	map = GraphTraversalUtil.breadthFirstSearch(graph, v1);
    	
    	assertEquals(4, map.size());
    	assertEquals(e1, map.get(v2));
    	assertEquals(e2, map.get(v3));
    	assertEquals(e3, map.get(v4));
    	assertEquals(e7, map.get(v5));
    	assertNotEquals(e4, map.get(v2));
    	assertNotEquals(e6, map.get(v3));
    	assertNotEquals(e5, map.get(v5));
    }
    
}
