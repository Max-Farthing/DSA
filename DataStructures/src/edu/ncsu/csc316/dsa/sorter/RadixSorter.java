package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * RadixSorter uses the radix sort algorithm to sort data
 * @author Dr. King
 *
 * @param <E> the generic type of data to sort
 */
public class RadixSorter<E extends Identifiable> implements Sorter<E> {
	
	/**
	 * The overridden sort method that finds least significant digit, adds it to a "bucket" and then adds it back to the data array
	 * @param data the array of elements to be sorted
	 */
	@Override
	public void sort(E[] data) {
		int k = 0;
		double x = 0;
		int p = 0;
		//Loops through the data set and finds the max value
		for(int i = 0; i < data.length; i++) {
			k = Math.max(k, data[i].getId());
		}
		//Finds the number of digits in the largest value
		x = Math.ceil(Math.log(k + 1) / Math.log(10));
		p = 1;
		//Loops from 1 to amount of digits in the largest value
		for(int j = 1; j <= x; j++) {
			int[] b = new int[10];
			//Loops through data and sets a value equal to the one in front of it
			for(int i = 0; i < data.length; i++) {
				b[((data[i].getId() / p) % 10)] = b[((data[i].getId() / p) % 10)] + 1;
			}
			//Sets a value in the "bucket" equal to the one behind it plus the one front of it
			for(int i = 1; i < b.length; i++) {
				b[i] = b[i - 1] + b[i];
			}
			@SuppressWarnings("unchecked")
			E[] f = (E[])(new Identifiable[ data.length]);
			//Sets the f value equal to the data value
			for(int i = data.length - 1; i >= 0; i--) {
				f[b[(data[i].getId() / p) % 10] - 1] = data[i];
				b[(data[i].getId() / p) % 10] = b[(data[i].getId() / p) % 10] - 1;
			}
			//Makes data equal to f
			for(int i = 0; i < data.length; i++) {
				data[i] = f[i];
			}
			p *= 10;
		}
	}
}
