package edu.ncsu.csc316.dsa.sorter;

/**
 * Interface that defines the sorting behavior
 * @author Dr. King
 * 
 * @param <E> generic type
 */
public interface Sorter<E> {
	
	/**
	 * Sort method takes integer of arrays and sorts them in ascending order
	 * @param data assorted array of integers
	 */
	public void sort(E[] data);
}
