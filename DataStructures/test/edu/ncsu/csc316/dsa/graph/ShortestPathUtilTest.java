package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for ShortestPathUtil
 * Checks the expected outputs of Dijksra's algorithm
 * and the shortest path tree construction method
 *
 * @author Dr. King
 *
 */
public class ShortestPathUtilTest {
	/** map for testing */
	private Map<Vertex<String>, Integer> map;
	/** graph used for testing */
	private Graph<String, Highway> graph;
	/** map for testing shortest spanning trees */
	private Map<Vertex<String>, Edge<Highway>> map2;

	
    /**
     * Test the output of Dijkstra's algorithm
     */ 
    @Test
    public void testDijkstra() {
    	graph = new EdgeListGraph<String, Highway>();
		Vertex<String> v1 = graph.insertVertex("one");
		Vertex<String> v2 = graph.insertVertex("two");
		Vertex<String> v3 = graph.insertVertex("three");
		Edge<Highway> e1 = graph.insertEdge(v1, v2, new Highway("HOne", 1));
		Edge<Highway> e2 = graph.insertEdge(v1, v3, new Highway("HTwo", 5));
		Edge<Highway> e3 = graph.insertEdge(v2, v3, new Highway("HThree", 2));
    	
		map = ShortestPathUtil.dijkstra(graph, v1);
		
		assertEquals(0, (int)map.get(v1));
		assertEquals(1, (int)map.get(v2));
		assertEquals(3, (int)map.get(v3));
		assertEquals(1, e1.getElement().getWeight());
		assertEquals(5, e2.getElement().getWeight());
		assertEquals(2, e3.getElement().getWeight());
		assertEquals("HOne", e1.getElement().getName());

    }
    
    /**
     * Test the output of the shortest path tree construction method
     */ 
    @Test
    public void testShortestPathTree() {
    	graph = new EdgeListGraph<String, Highway>();
		Vertex<String> v1 = graph.insertVertex("one");
		Vertex<String> v2 = graph.insertVertex("two");
		Vertex<String> v3 = graph.insertVertex("three");
		Edge<Highway> e1 = graph.insertEdge(v1, v2, new Highway("HOne", 1));
		Edge<Highway> e2 = graph.insertEdge(v1, v3, new Highway("HTwo", 5));
		Edge<Highway> e3 = graph.insertEdge(v2, v3, new Highway("HThree", 2));
		
		map = ShortestPathUtil.dijkstra(graph, v1);
		map2 = ShortestPathUtil.shortestPathTree(graph, v1, map);
		
		assertEquals(e1, map2.get(v2));
		assertEquals(e3, map2.get(v3));
		assertNotEquals(e2, map2.get(v3));

    }
    
    /**
     * Inner helper class for testing
     * @author max farthing
     */
    private class Highway implements Weighted {
    	/** name of highway */
        private String name;
        /** length of highway */
        private int length;
        
        /**
         * highway constructor
         * @param name String of highway name
         * @param l length of highway
         */
        public Highway(String name, int l) {
            setName(name);
            setLength(l);
        }

        /**
         * sets highway name
         * @param name name of highway
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * gets highway length
         * @return return int in highway length
         */
        public int getLength() {
            return length;
        }
        
        /**
         * gets highway length
         * @return return int in highway length
         */
        public String getName() {
            return name;
        }

        /**
         * sets highway length
         * @param length length to be set
         */
        public void setLength(int length) {
            this.length = length;
        }

        @Override
        public int getWeight() {
            return getLength();
        }
    }
    
}

