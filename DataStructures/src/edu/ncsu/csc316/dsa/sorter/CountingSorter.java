package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * CountingSorter uses the counting sort algorithm to sort data
 * @author Dr. King
 *
 * @param <E> the generic type of data to sort
 */
public class CountingSorter<E extends Identifiable> implements Sorter<E> {
	
	/**
	 * Find's the minimum between two elements using their id
	 * @param e1 the first element to be compared
	 * @param e2 the second element to be compared
	 * @return min the minimum element of the two
	 */
	public E min(E e1, E e2) {
		E min = null;
		if(e1.getId() < e2.getId()) {
			min = e1;
		}
		if(e2.getId() < e1.getId()) {
			min = e2;
		}
		else {
			min = e1;
		}
		
		return min;
		
	}
	/**
	 * Find's the maximum between two elements using their id
	 * @param e1 the first element to be compared
	 * @param e2 the second element to be compared
	 * @return max the maximum element of the two
	 */
	public E max(E e1, E e2) {
		E max = null;
		if(e1.getId() > e2.getId()) {
			max = e1;
		}
		if(e2.getId() > e1.getId()) {
			max = e2;
		}
		else {
			max = e1;
		}
		
		return max;
		
	}
	/**
	 * The overridden sort method that sorts by counting the frequencies of values in the input and sorts them by id
	 * @param data the list of elements
	 */
	@Override
	public void sort(E[] data) {
		
		E min = data[0];
		E max = data[0];
		//Finds the minimum and maximum values of data 
		for(int i = 0; i < data.length; i++) {
			min = min(data[i], min);
			max = max(data[i], max);
		}
		//Calculates the range of values
		int k = (max.getId() - min.getId()) + 1;
		int b[] = new int[k];
		//Makes the "bucket" value equal to the one in front it
		for(int i = 0; i < data.length; i++) {
			b[data[i].getId() - min.getId()] = b[data[i].getId() - min.getId()] + 1;
		}
		//Sets the bucket value to the one behind it added to itself
		for(int i = 1; i < b.length; i++) {
			b[i] = b[i - 1] + b[i];
		}
		@SuppressWarnings("unchecked")
		E[] f = (E[])(new Identifiable[ data.length]);
		//Sets the F value to the data value
		for(int i = data.length - 1; i >= 0; i--) {
			f[b [data[i].getId() - min.getId()] - 1] = data[i];
			b [data[i].getId() - min.getId()] = b[data[i].getId() - min.getId()] - 1;
		}
		//Sets data equal to F
		for(int i = 0; i < data.length; i++) {
			data[i] = f[i];
		}
		
	}	
}
