package edu.ncsu.csc316.dsa.list.positional;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ncsu.csc316.dsa.Position;

/**
 * The Positional Linked List is implemented as a doubly-linked list data
 * structure to support efficient, O(1) worst-case Positional List abstract data
 * type behaviors.
 * 
 * Size is maintained as a global field to ensure O(1) worst-case runtime of
 * size() and isEmpty().
 * 
 * The PositionalLinkedList class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements stored in the positional list
 */
public class PositionalLinkedList<E> implements PositionalList<E> {

    /** A dummy/sentinel node representing at the front of the list **/
    private PositionalNode<E> front;

    /** A dummy/sentinel node representing at the end/tail of the list **/
    private PositionalNode<E> tail;

    /** The number of elements in the list **/
    private int size;

    /**
     * Constructs an empty positional linked list
     */
    public PositionalLinkedList() {
        front = new PositionalNode<E>(null);
        tail = new PositionalNode<E>(null, null, front);
        front.setNext(tail);
        size = 0;
    }

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}

	@Override
	public Position<E> addAfter(Position<E> p, E element) {
		PositionalNode<E> current = validate(p);
		return addBetween(element, current.getNext(), current);
	}

	@Override
	public Position<E> addBefore(Position<E> p, E element) {
		PositionalNode<E> current = validate(p);
		return addBetween(element, current, current.getPrevious());
	}

	@Override
	public Position<E> addFirst(E element) {
		return addBetween(element, front.getNext(), front);
	}

	@Override
	public Position<E> addLast(E element) {
		return addBetween(element, tail, tail.getPrevious());
	}

	@Override
	public Position<E> after(Position<E> p) {
		PositionalNode<E> node = validate(p);
		if(node.next == tail) {
			return null;
		}
		return node.getNext();
	}

	@Override
	public Position<E> before(Position<E> p) {
		PositionalNode<E> node = validate(p);
		if(node.previous == front) {
			return null;
		}
		return node.getPrevious();
	}

	@Override
	public Position<E> first() {
		if(isEmpty()) return null;
		return front.getNext();
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public Position<E> last() {
		if(isEmpty()) return null;
		return tail.getPrevious();
	}

	@Override
	public Iterable<Position<E>> positions() {
		return new PositionIterable();
	}

	@Override
	public E remove(Position<E> p) {
		PositionalNode<E> current = validate(p);
		current.previous.setNext(current.next);
		current.next.setPrevious(current.previous);
		size--;
		return current.getElement();
	}

	@Override
	public E set(Position<E> p, E element) {
		PositionalNode<E> current = validate(p);
		E elem = current.element;
		current.setElement(element);
		return elem;
	}

	@Override
	public int size() {
		return size;
	}
	
	/**
     * Safely casts a Position, p, to be a PositionalNode.
     * 
     * @param p the position to cast to a PositionalNode
     * @return a reference to the PositionalNode
     * @throws IllegalArgumentException if p is null, or if p is not a valid
     *                                  PositionalNode
     */
    private PositionalNode<E> validate(Position<E> p) {
        if (p instanceof PositionalNode) {
            return (PositionalNode<E>) p;
        }
        throw new IllegalArgumentException("Position is not a valid positional list node.");
    }
    
    /**
     * helper method to add in between positional nodes
     * @param element data in node
     * @param next node following
     * @param prev node before
     * @return Position
     */
    private Position<E> addBetween(E element, PositionalNode<E> next, PositionalNode<E> prev) {
    	PositionalNode<E> node = new PositionalNode<E>(element, next, prev);
    	next.setPrevious(node);
    	prev.setNext(node);
    	size++;
		return node;
    }

    /**
     * Inner nodes that comprise of the positional linked lists
     * @author max farthing mafarthi
     *
     * @param <E> generic element
     */
	private static class PositionalNode<E> implements Position<E> {
		/** element **/
        private E element;
        /** next node **/
        private PositionalNode<E> next;
        /** previous node **/
        private PositionalNode<E> previous;

        /**
         * Constructor for node 
         * @param value element of node
         */
        public PositionalNode(E value) {
            this(value, null);
        }

        /**
         * Constructor for node with next node
         * @param value element node
         * @param next node after current
         */
        public PositionalNode(E value, PositionalNode<E> next) {
            this(value, next, null);
        }

        /**
         * Constructor with next and previous node
         * @param value element of node
         * @param next node after
         * @param prev node before
         */
        public PositionalNode(E value, PositionalNode<E> next, PositionalNode<E> prev) {
            setElement(value);
            setNext(next);
            setPrevious(prev);
        }

        /**
         * changes previous node
         * @param prev node before
         */
        public void setPrevious(PositionalNode<E> prev) {
            previous = prev;
        }

        /**
         * getter for previous node
         * @return Node for previous
         */
        public PositionalNode<E> getPrevious() {
            return previous;
        }
        
        /**
         * sets next node 
         * @param next node after current
         */
        public void setNext(PositionalNode<E> next) {
            this.next = next;
        }

        /**
         * returns the next node 
         * @return next node
         */
        public PositionalNode<E> getNext() {
            return next;
        }

        @Override
        public E getElement() {
            return element;
        }
        
        /**
         * sets element inside node
         * @param element value in node
         */
        public void setElement(E element) {
            this.element = element;
        }
    }
	
	/**
	 * Positional iterator iterates through positions and not elements
	 * @author max farthing mafarthi
	 *
	 */
	private class PositionIterator implements Iterator<Position<E>> {

		/** current position **/
        private Position<E> current;
        /** if okay to remove **/
        private boolean removeOK;

        /**
         * constructor for position iterator
         */
        public PositionIterator() {
        	current = front.next;
        	removeOK = false;
        	
        }

        @Override
        public boolean hasNext() {
        	return current != null && current != tail;
        }

        @Override
        public Position<E> next() {
        	if(!hasNext()) throw new NoSuchElementException();
            Position<E> pos = current;
        	current = after(current);
        	removeOK = true;
        	return pos;
        }

        @Override
        public void remove() {
        	if(!removeOK) {
        		throw new IllegalStateException();
        	}
        	PositionalLinkedList.this.remove(current);
        	removeOK = false;
        	
        }
    }
	
	/**
	 * Iterator that iterates through elements 
	 * @author max farthing mafarthi
	 *
	 */
	private class ElementIterator implements Iterator<E> {
		/** iterator object **/
        private Iterator<Position<E>> it;

        /**
         * constructor for element iterator
         */
        public ElementIterator() {
            it = new PositionIterator();
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public E next() {
            return it.next().getElement();
        }

        @Override
        public void remove() {
            it.remove();
        }
    }
	
	/**
	 * position iterator instantiation
	 * @author max farthing mafarthi
	 *
	 */
	private class PositionIterable implements Iterable<Position<E>> {
        
        @Override
        public Iterator<Position<E>> iterator() {
            return new PositionIterator();
        }
    }

}
