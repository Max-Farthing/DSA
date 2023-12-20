package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * SelectionSorter uses the selection sort algorithm to sort data
 * @author Dr. King
 *
 * @param <E> the generic type of data to sort
 */
public class SelectionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/**
	 * The selection sorter constructor, allows to have a custom comparator
	 * @param comparator the comparator to be used: descending GPA or ascending id
	 */
	public SelectionSorter(Comparator<E> comparator) {
        super(comparator);
    }
	/**
	 * The empty constructor for SelectionSorter
	 */
	public SelectionSorter() {
		this(null);
	}
	
	/**
	 * The overridden sort method that sorts by finding the minimum unsorted element and swapping with first unsorted element until sorted
	 * @param data the set of values that can be of any element
	 */
	@Override
	public void sort(E[] data) {
		int min = 0;
		E x = null;
		//Goes through the whole list and finds smallest values and swaps with the first unsorted element over and over till sorted
		for(int i = 0; i < data.length; i++) {
			min = i;
			for(int j = i + 1; j < data.length; j++) {
				if(super.compare(data[j], data[min]) < 0) {
					min = j;
				}
			}
			if(i != min) {
				x = data[i];
				data[i] = data[min];
				data[min] = x;
			}
		}
		
	}	
}
