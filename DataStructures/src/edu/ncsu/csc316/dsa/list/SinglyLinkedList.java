package edu.ncsu.csc316.dsa.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A singly-linked list is a linked-memory representation of the List abstract
 * data type. This list maintains a dummy/sentinel front node in the list to
 * help promote cleaner implementations of the list behaviors. This list also
 * maintains a reference to the tail/last node in the list at all times to
 * ensure O(1) worst-case cost for adding to the end of the list. Size is
 * maintained as a global field to allow for O(1) size() and isEmpty()
 * behaviors.
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements stored in the list
 */
public class SinglyLinkedList<E> extends AbstractList<E> {

    /** A reference to the dummy/sentinel node at the front of the list **/
    private LinkedListNode<E> front;
    
    /** A reference to the last/final node in the list **/
    private LinkedListNode<E> tail;
    
    /** The number of elements stored in the list **/
    private int size;
        
    /**
     * Constructs an empty singly-linked list
     */     
    public SinglyLinkedList() {
        front = new LinkedListNode<E>(null);
        tail = null;
        size = 0;
    }
    
	@Override
	public void add(int index, E element) {
		checkIndexForAdd(index);
		
		LinkedListNode<E> node = new LinkedListNode<E>(element, null);
		if(front.next == null || index == 0) {
			node.next = front.next;
			front.next = node;
			if(isEmpty()) {
				tail = node;
			}
		} else if(index == size) {
			tail.next = node;
			tail = node;
		} else {
		
			LinkedListNode<E> current = front.next;
			for(int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			node.next = current.next;
			current.next = node;
			if(node.next == null) {
				tail = node;
			}
		}
		size++;
	}

	@Override
	public E get(int index) {
		checkIndex(index);
		
		LinkedListNode<E> current = front.next;
		
		for(int i = 0; i < index; i++) {
			current = current.next;
		}
		
		return current.getData();
	}

	@Override
	public E remove(int index) {
		checkIndex(index);
		E element = null;
		if(index == 0) {
			element = front.next.data;
			front.next = front.next.next;
			if(isEmpty()) {
				tail = null;
			}
		} else {
			LinkedListNode<E> current = front.next;
			for(int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			element = current.next.data;
			current.next = current.next.next;
			if(current.next == null) {
				tail = current;
			}
		}
		size--;
		return element;
	}

	@Override
	public E set(int index, E element) {
		checkIndex(index);
		E elem = null;
		if(index == 0) {
			elem = front.next.data;
			front.next.data = element;
		} else {
			LinkedListNode<E> current = front.next;
			for(int i = 0; i < index; i++) {
				current = current.next;
			}
			elem = current.data;
			current.data = element;
		}
		
		return elem;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}
	
	/**
     * {@inheritDoc} For a singly-linked list, this behavior has O(1) worst-case
     * runtime.
     */
    @Override
    public E last() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("The list is empty");
        }
        return tail.data;
    }

    /**
     * {@inheritDoc}
     * For this singly-linked list, addLast(element) behavior has O(1) worst-case runtime.
     */    
    @Override
    public void addLast(E element) {
        add(size, element);
    }
    
    /**
     * List node inner class that link the list together
     * @author max farthing mafarthi
     *
     * @param <E> generic objects 
     */
	private static class LinkedListNode<E> {
        /** data in node **/
        private E data;
        /** next node in list **/
        private LinkedListNode<E> next;

        /**
         * constructor for listnode
         * @param data element inside node
         */
        public LinkedListNode(E data){
        	this.data = data;
        }
        
        /**
         * getter for data inside nodes
         * @return E data inside node
         */
        public E getData() {
			return data;
		}

        /**
         * Constructor for listnode with a next node
         * @param data element inside node
         * @param nextNode the node after current
         */
		public LinkedListNode(E data, LinkedListNode<E> nextNode) {
        	this(data);
        	next = nextNode;
        }
        
    }
	
	/**
	 * Iterator class to iterate through list elements
	 * @author max farthing mafarthi
	 *
	 */
	private class ElementIterator implements Iterator<E> {
	    /**
	     * Keep track of the next node that will be processed
	     */
	    private LinkedListNode<E> current;
	    
	    /**
	     * Construct a new element iterator where the cursor is initialized 
	     * to the beginning of the list.
	     */
	    public ElementIterator() {
	    	current = front.next;
	    }

	    @Override
	    public boolean hasNext() {
	    	return current != null;
	    }

	    @Override
	    public E next() {
	    	if(!hasNext()) throw new NoSuchElementException();
	    	E element = current.data;
	    	current = current.next;
	    	return element;
	    }
	     
	    @Override    
	    public void remove() {
		    // DO NOT CHANGE THIS METHOD
	        throw new UnsupportedOperationException(
	            "This SinglyLinkedList implementation does not currently support removal of elements when using the iterator.");
	    }
	}
    
}
