package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * InsertionSorter uses the insertion sort algorithm to sort data.
 * @param <E> the generic element to be sorted
 * @author Dr. King
 */
public class InsertionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	/**
	 * The empty constructor for InsertionSorter
	 */
	public InsertionSorter() {
		this(null);
	}
	/**
	 * The constructor for an InsertionSorter
	 * @param comparator the comparator to be used: descending GPA or ascending id
	 */
	 public InsertionSorter(Comparator<E> comparator) {
       super(comparator);
    }
	 
	/**
	 * The overridden sort method that processes each element in data one by one and shifts left until in correct sorted order
	 * @param data the list of elements
	 */
	@Override
	public void sort(E[] data) {
		E x;
		int j;
		//Loops through the integer array and processes and sorts one by one by shifting left
		for(int i = 1; i < data.length; i++) {
			x = data[i];
			j = i - 1;
			while(j >= 0 && super.compare(data[j], x) > 0) {
				data[j + 1] = data[j];
				j = j - 1;
			}
			data[j + 1] = x;
		}
	}	
}
