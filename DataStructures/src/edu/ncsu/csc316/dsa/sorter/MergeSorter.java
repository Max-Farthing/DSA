package edu.ncsu.csc316.dsa.sorter;

import java.util.Arrays;
import java.util.Comparator;

/**
 * MergeSorter sorts arrays of comparable elements using the merge sort
 * algorithm. This implementation ensures O(nlogn) worst-case runtime to sort an
 * array of n elements that are comparable.
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements to sort; elements must be {@link Comparable}
 */
public class MergeSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

    /**
     * Constructs a new MergeSorter with a specified custom Comparator
     * 
     * @param comparator a custom Comparator to use when sorting
     */
    public MergeSorter(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * Constructs a new MergeSorter with comparisons based on the element's natural
     * ordering
     */ 
    public MergeSorter() {
        this(null);
    }

	@Override
	public void sort(E[] data) {
		
		if(data.length < 2) {
			data.getClass();
		} else {
			int mid = data.length / 2;
			E[] left = Arrays.copyOfRange(data, 0, mid);
			E[] right = Arrays.copyOfRange(data, mid, data.length);
			
			sort(left);
			sort(right);

			merge(left, right, data);
		}
		
	}
	
	/**
	 * private helper method to merge different arrays together in the sort(merge sort) method
	 * @param s1 left array
	 * @param s2 right array
	 * @param s3 original array
	 */
	private void merge(E[] s1, E[] s2, E[] s3) {
		int left = 0;
		int right = 0;
		while(left + right < s3.length) {
			if(right == s2.length || (left < s1.length) && (compare(s1[left], s2[right]) < 0)) {
				s3[left + right] = s1[left]; 
				left++; 
			} else {
				s3[left + right] = s2[right];
				right++;
			}
		}
	}
    

}


