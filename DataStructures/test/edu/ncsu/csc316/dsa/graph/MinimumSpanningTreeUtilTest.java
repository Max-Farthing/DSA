package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * Test class for MinimumSpanningTreeUtil
 * Checks the expected outputs of Prim-Jarnik's algorithm
 * and Kruskal's algorithm
 *
 * @author Dr. King
 *
 */
public class MinimumSpanningTreeUtilTest {
	/** list for testing */
	private PositionalList<Edge<Highway>> list;
	/** graph for testing */
	Graph<String, Highway> graph;

    /**
     * Test the output of Prim-Jarnik's algorithm
     */ 
    @Test
    public void testPrimJarnik() {
    	graph = new EdgeListGraph<String, Highway>();
    	Vertex<String> v1 = graph.insertVertex("one");
		Vertex<String> v2 = graph.insertVertex("two");
		Vertex<String> v3 = graph.insertVertex("three");
		Edge<Highway> e1 = graph.insertEdge(v1, v2, new Highway("HOne", 1));
		Edge<Highway> e2 = graph.insertEdge(v1, v3, new Highway("HTwo", 5));
		Edge<Highway> e3 = graph.insertEdge(v2, v3, new Highway("HThree", 2));
		
		list = MinimumSpanningTreeUtil.primJarnik(graph);
		Iterator<Edge<Highway>> it = list.iterator();
		assertEquals(e1, it.next());
		assertEquals(e3, it.next());
		assertEquals("HTwo", e2.getElement().getName());

    }
    
    /**
     * Test the output of Kruskal's algorithm
     */ 
    @Test
    public void testKruskal() {
    	graph = new EdgeListGraph<String, Highway>();
    	Vertex<String> v1 = graph.insertVertex("one");
		Vertex<String> v2 = graph.insertVertex("two");
		Vertex<String> v3 = graph.insertVertex("three");
		Edge<Highway> e1 = graph.insertEdge(v1, v2, new Highway("HOne", 1));
		Edge<Highway> e2 = graph.insertEdge(v1, v3, new Highway("HTwo", 5));
		Edge<Highway> e3 = graph.insertEdge(v2, v3, new Highway("HThree", 2));
		
		list = MinimumSpanningTreeUtil.kruskal(graph);
		Iterator<Edge<Highway>> it = list.iterator();
		assertEquals(e1, it.next());
		assertEquals(e3, it.next());
		assertEquals("HTwo", e2.getElement().getName());
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
