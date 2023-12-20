package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;

/**
 * An AdjacencyMatrixGraph is an implementation of the {@link Graph} abstract
 * data type. AdjacencyMatrixGraph maintains a list of vertices in the graph and
 * a list of edges in the graph. In addition, AdjacencyMatrixGraph maintains a
 * 2-dimensional array to store edges based on the endpoints of the edges
 * 
 * The AdjacencyMatrixGraph class is based on the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 *
 * @param <V> the type of data in the vertices in the graph
 * @param <E> the type of data in the edges in the graph
 */
public class AdjacencyMatrixGraph<V, E> extends EdgeListGraph<V, E> {

    /**
     * Represents a vertex in an AdjacencyMapGraph
     * 
     * @author Dr. King
     *
     */
    private class MatrixVertex extends GraphVertex {

        /** The integer index of the vertex **/
        private int index;

        /**
         * Creates a new adjacency matrix vertex.
         * 
         * @param data       the data to store in the vertex
         */
        public MatrixVertex(V data) {
            super(data);
            index = getVertexIndex();
            System.out.println("Vertex " + data + " has index " + index); 
        }

        /**
         * Returns the row/column index of the vertex in the matrix
         * 
         * @return the index of the vertex in the matrix
         */
        public int getIndex() {
            return index;
        }
    }
    /** 2d array holding all edge information */
    private GraphEdge[][] matrix;
    /** indexer for finding vertexes */
    private int vertexIndexer;

    /**
     * Creates a new undirected adjacency matrix graph
     */
    public AdjacencyMatrixGraph() {
        this(false);
        vertexIndexer = 0;
    }

    /**
     * Creates a new adjacency matrix graph
     * 
     * @param directed if true, the graph is directed; if false, the graph is
     *                 undirected
     */
    @SuppressWarnings("unchecked")
    public AdjacencyMatrixGraph(boolean directed) {
        super(directed);
        matrix = (GraphEdge[][]) (new AbstractGraph.GraphEdge[0][0]);
    }

    protected Vertex<V> createVertex(V vertexData) {
        return new MatrixVertex(vertexData);
    }

    @Override
    public Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2) {
    	MatrixVertex v1 = validate(vertex1);
    	MatrixVertex v2 = validate(vertex2);
    	int i1 = v1.getIndex();
    	int i2 = v2.getIndex();
    	if (isDirected()) {
            return matrix[i1][i2];
        } else {
            Edge<E> edge = matrix[i1][i2];
            if (edge == null) {
                edge = matrix[i2][i1];
            }
            return edge;
        }

    }

    private int getVertexIndex() {
        vertexIndexer++;
        return vertexIndexer - 1;
    }

    @SuppressWarnings("unchecked")
    private void growArray() {
        GraphEdge[][] temp = new AbstractGraph.GraphEdge[matrix.length + 1][matrix.length + 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                temp[i][j] = matrix[i][j];
            }
        }
        matrix = temp;
    }

    private List<Edge<E>> incomingEdgeList(Vertex<V> vertex) {
        MatrixVertex v = validate(vertex);
        List<Edge<E>> list = new SinglyLinkedList<Edge<E>>();
        for (int i = 0; i < matrix.length; i++) {
            GraphEdge e = matrix[i][v.getIndex()];
            if (e != null) {
                list.addLast(e);
            }
        }
        return list;
    }

    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
        return incomingEdgeList(vertex);
    }

    @Override
    public int inDegree(Vertex<V> vertex) {
        return incomingEdgeList(vertex).size();
    }

    @Override
    public Edge<E> insertEdge(Vertex<V> vertex1, Vertex<V> vertex2, E edgeData) {
    	Edge<E> e = super.insertEdge(vertex1, vertex2, edgeData);
    	GraphEdge e1 = validate(e);
    	MatrixVertex v1 = validate(vertex1);
    	MatrixVertex v2 = validate(vertex2);
    	int i1 = v1.getIndex();
    	int i2 = v2.getIndex();
    	matrix[i1][i2] = e1;
    	if(!isDirected()) {
    		matrix[i2][i1] = e1;
    	}
    	return e1;
    }

    @Override
    public Vertex<V> insertVertex(V vertexData) {
        growArray();
        return super.insertVertex(vertexData);
    }

    @Override
    public int outDegree(Vertex<V> vertex) {
        return outgoingEdgeList(vertex).size();
    }

    private List<Edge<E>> outgoingEdgeList(Vertex<V> vertex) {
    	MatrixVertex v1 = validate(vertex);
    	int index = v1.getIndex();
    	List<Edge<E>> l = new ArrayBasedList<Edge<E>>();
    	int count = 0;
    	for(int i = 0; i < matrix.length; i++) {
    		GraphEdge e = matrix[index][i];
    		if(e != null) {
        		l.add(count++, e);
    		}
    	}
    	return l;
    }
    
    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
        return outgoingEdgeList(vertex);
    }

    @Override
    public void removeEdge(Edge<E> edge) {
    	super.removeEdge(edge);
    	GraphEdge e = validate(edge);
    	Vertex<V>[] v = e.getEndpoints();
    	MatrixVertex o = validate(v[0]);
    	MatrixVertex d = validate(v[1]);
    	int i1 = o.getIndex();
    	int i2 = d.getIndex();
    	matrix[i1][i2] = null;
    	if(!isDirected()) {
        	matrix[i2][i1] = null;
    	}
    }
    
    /**
     * Safely casts a Vertex to a graph vertex
     * 
     * @param v Vertex
     * @return a graph vertex representation of the given Vertex
     * @throws IllegalArgumentException if the vertex is not a valid graph vertex
     */
    private MatrixVertex validate(Vertex<V> v) {
        if (!(v instanceof AdjacencyMatrixGraph.MatrixVertex)) {
            throw new IllegalArgumentException("Vertex is not a valid adjacency matrix vertex.");
        }
        return (MatrixVertex) v;
    }
}
