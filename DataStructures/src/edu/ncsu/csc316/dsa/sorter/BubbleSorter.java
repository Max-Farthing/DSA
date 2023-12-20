package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;
/**
 * BubbleSorted the sorting method based on the bubble sort algorithm
 * @author camer
 *
 * @param <E> the generic elements that will be sorted
 */
public class BubbleSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/**
	 * The constructor for an BubbleSorter
	 * @param comparator the comparator to be used: descending GPA or ascending id
	 */
	public BubbleSorter(Comparator<E> comparator) {
		super(comparator);
	}
	/**
	 * The empty constructor for BubbleSorter
	 */
	public BubbleSorter() {
		this(null);
	}
	
	/**
	 * The overridden sort method that swaps through a list in adjacent pairs until in sorted order
	 * @param data the set of elements to be sorted
	 */
	@Override
	public void sort(E[] data) {
		boolean r = true;
		E x = null;
		//Repeated over and over until true
		while(r) {
			r = false;
		//Loops through the data set and compares adjacent pairs
		for(int i = 1; i < data.length; i++) {
			//Compares the two elements by whichever comparator chosen swaps if the first element is valued as less than the second
			if(super.compare(data[i], data[i - 1]) < 0) {
				x = data[i - 1];
				data[i - 1] = data[i];
				data[i] = x;
				r = true;
			}
		}
		
	}
	}

}
