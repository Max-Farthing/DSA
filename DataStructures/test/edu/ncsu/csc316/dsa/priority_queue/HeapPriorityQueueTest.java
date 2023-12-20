package edu.ncsu.csc316.dsa.priority_queue;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;

/**
 * Test class for HeapPriorityQueue
 * Checks the expected outputs of the Priorty Queue abstract data type behaviors when using
 * a min-heap data structure 
 *
 * @author Dr. King
 *
 */
public class HeapPriorityQueueTest {
	/** Heap used for testing */
    private PriorityQueue<Integer, String> heap;
    
    /**
     * Create a new instance of a heap before each test case executes
     */     
    @Before
    public void setUp() {
        heap = new HeapPriorityQueue<Integer, String>();
    }
    
    /**
     * Test the output of the insert(k,v) behavior
     */     
    @Test
    public void testInsert() {
        assertTrue(heap.isEmpty());
        assertTrue(heap.size() == 0);
        
        heap.insert(8, "eight");
        assertEquals(1, heap.size());
        assertFalse(heap.isEmpty());
        assertEquals(8, (int)heap.min().getKey());
        
        heap.insert(4, "four");
        assertEquals(2, heap.size());
        assertEquals(4, (int)heap.min().getKey());
        
        heap.insert(12, "twelve");
        assertEquals(3, heap.size());
        assertEquals(4, (int)heap.min().getKey());
    }
    
    /**
     * Test the output of the min behavior
     */ 
    @Test
    public void testMin() {
        assertTrue(heap.isEmpty());
        assertTrue(heap.size() == 0);
        
        assertNull(heap.min());
        heap.insert(8, "eight");
        assertEquals(1, heap.size());
        assertEquals(8, (int)heap.min().getKey());
        heap.insert(4, "four");
        assertEquals(2, heap.size());
        assertEquals(4, (int)heap.min().getKey());
        heap.insert(12, "twelve");
        assertEquals(3, heap.size());
        assertEquals(4, (int)heap.min().getKey());
    }
    
    /**
     * Test the output of the deleteMin behavior
     */     
    @Test 
    public void deleteMin() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        assertNull(heap.deleteMin());
        heap.insert(8, "eight");
        assertEquals(1, heap.size());
        assertEquals(8, (int)heap.min().getKey());
        assertEquals(8, (int)heap.deleteMin().getKey());
        heap.insert(4, "four");
        assertEquals(1, heap.size());
        assertEquals(4, (int)heap.min().getKey());
        heap.insert(12, "twelve");
        assertEquals(2, heap.size());
        assertEquals(4, (int)heap.min().getKey());
        assertEquals(4, (int)heap.deleteMin().getKey());
    }
    
    /**
     * Test the output of the heap behavior when using arbitrary key objects to
     * represent priorities
     */ 
    @Test
    public void testStudentHeap() {
        PriorityQueue<Student, String> sHeap = new HeapPriorityQueue<Student, String>(new StudentIDComparator());
        Student s1 = new Student("J", "K", 1, 1, 1, "jk1");
        Student s2 = new Student("J", "S", 2, 1, 2, "js2");
        Student s3 = new Student("S", "H", 3, 1, 3, "sh3");
        Student s4 = new Student("J", "J", 4, 1, 4, "jj4");
        Student s5 = new Student("L", "B", 5, 1, 5, "lb5");
        
        assertTrue(sHeap.isEmpty());
        assertEquals(0, sHeap.size());
        
        sHeap.insert(s1, s1.getUnityID());
        assertEquals(1, sHeap.size());
        assertEquals(s1, sHeap.min().getKey());
        assertEquals(s1.getUnityID(), sHeap.min().getValue());

        sHeap.insert(s2, s1.getUnityID());
        assertEquals(2, sHeap.size());
        assertEquals(s1, sHeap.min().getKey());
        
        sHeap.insert(s3, s1.getUnityID());
        assertEquals(3, sHeap.size());
        assertEquals(s1, sHeap.min().getKey());
        
        sHeap.insert(s4, s1.getUnityID());
        assertEquals(4, sHeap.size());
        assertEquals(s1, sHeap.min().getKey());
        
        sHeap.insert(s5, s1.getUnityID());
        assertEquals(5, sHeap.size());
        assertEquals(s1, sHeap.min().getKey());
                
        assertEquals(s1, sHeap.deleteMin().getKey());
        assertEquals(4, sHeap.size());

        assertEquals(s2, sHeap.deleteMin().getKey());
        assertEquals(3, sHeap.size());

        assertEquals(s3, sHeap.deleteMin().getKey());
        assertEquals(2, sHeap.size());

        assertEquals(s4, sHeap.deleteMin().getKey());
        assertEquals(1, sHeap.size());

        assertEquals(s5, sHeap.deleteMin().getKey());
        assertEquals(0, sHeap.size());
    }
    
    /**
     * added tests for hasleft hasright for method coverage
     */
    @Test
    public void testHasLeftAndRight() {
        HeapPriorityQueue<Integer, String> heap2 = new HeapPriorityQueue<Integer, String>();
        assertTrue(heap2.isEmpty());
        assertEquals(0, heap2.size());

        assertFalse(heap2.hasLeft(0)); // The root has no left child
        assertFalse(heap2.hasRight(0)); // The root has no right child
        assertEquals(0, heap2.size());

    }
}
