/**
 * 
 */
package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * Abstract class used for comparison in various sorters, extracts common functionality between sorters
 * @author max farthing
 *
 * @param <E> generic type
 */
public abstract class AbstractComparisonSorter<E extends Comparable<E>> implements Sorter<E> {
	/** comparator used for comparison of objects **/
	private Comparator<E> comparator;
	
	/**
	 * sets the the type of comparator
	 * @param comparator object of comparison
	 */
	public AbstractComparisonSorter(Comparator<E> comparator) {
		setComparator(comparator);
	}
	
	/**
	 * sets the comparator type to the selected
	 * @param comparator object of comparison
	 */
	private void setComparator(Comparator<E> comparator ) {
		if(comparator == null) {
			this.comparator = new NaturalOrder();
		} else {
			this.comparator = comparator;
		}
	}
	
	/**
	 * default comparator if none are chosen
	 * @author max farthing
	 *
	 */
	private class NaturalOrder implements Comparator<E> {
		/**
		 * method to compare objects based on natural ordering
		 * @param first object one
		 * @param second object two
		 * @return int based on comparison
		 */
		public int compare(E first, E second) {
			return ((Comparable<E>) first).compareTo(second);
		}
		
	}
	
	/**
	 * compare method to compare objects based on criteria
	 * @param first object one
	 * @param second object two
	 * @return int based on comparison
	 */
	public int compare(E first, E second) {
		return comparator.compare(first, second);
	}
}
