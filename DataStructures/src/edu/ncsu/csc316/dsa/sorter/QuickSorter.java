package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;
import java.util.Random;

/**
 * QuickSorter sorts arrays of comparable elements using the quicksort
 * algorithm. This implementation allows the client to specify a specific pivot
 * selection strategy: (a) use the first element as the pivot, (b) use the last
 * element as the pivot, (c) use the middle element as the pivot, or (d) use an
 * element at a random index as the pivot.
 * 
 * Using the randomized pivot selection strategy ensures O(nlogn)
 * expected/average case runtime when sorting n elements that are comparable
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements to sort; elements must be {@link Comparable}
 */
public class QuickSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	
	/** the actual selector in use whether it be first, last, middle or random **/
	private PivotSelector selector;
	/**
     * Pivot selection strategy that uses the element at the first index each time a
     * pivot must be selected
     */
    public static final PivotSelector FIRST_ELEMENT_SELECTOR = new FirstElementSelector();
    
    /**
     * Pivot selection strategy that uses the element at the last index each time a
     * pivot must be selected
     */
    public static final PivotSelector LAST_ELEMENT_SELECTOR = new LastElementSelector();
    
    /**
     * Pivot selection strategy that uses the element at the middle index each time
     * a pivot must be selected
     */
    public static final PivotSelector MIDDLE_ELEMENT_SELECTOR = new MiddleElementSelector();
    
    /**
     * Pivot selection strategy that uses the element at a randomly-chosen index
     * each time a pivot must be selected
     */
    public static final PivotSelector RANDOM_ELEMENT_SELECTOR = new RandomElementSelector();
    
    /**
     * Constructs a new QuickSorter with a provided custom Comparator and a
     * specified PivotSelector strategy
     * 
     * @param comparator a custom comparator to use when sorting
     * @param selector   the pivot selection strategy to use when selecting pivots
     */
    public QuickSorter(Comparator<E> comparator, PivotSelector selector) {
        super(comparator);
        setSelector(selector);
    }

    /**
     * Constructs a new QuickSorter using the natural ordering of elements. Pivots
     * are selected using the provided PivotSelector strategy
     * 
     * @param selector the pivot selection strategy to use when selecting pivots
     */
    public QuickSorter(PivotSelector selector) {
        this(null, selector);
    }

    /**
     * Constructs a new QuickSorter with a provided custom Comparator and the
     * default random pivot selection strategy
     * 
     * @param comparator a custom comparator to use when sorting
     */
    public QuickSorter(Comparator<E> comparator) {
        this(comparator, null);
    }

    /**
     * Constructs a new QuickSorter that uses an element's natural ordering and uses
     * the random pivot selection strategy
     */
    public QuickSorter() {
        this(null, null);
    }
    
    /**
     * Sets the selector
     * @param selector the type of pivot selector
     */
    private void setSelector(PivotSelector selector) {
        if(selector == null) {
            this.selector = new RandomElementSelector();
        } else {
            this.selector = selector;
        }
    }

    @Override
	public void sort(E[] data) {
    	int low = 0;
    	int high = data.length - 1;
    	quickSort(data, low, high, 0);
	}
    
    /**
     * helper method in quick sorter algorithm, helps set up algorithm
     * @param data the original array of data
     * @param low lowest index
     * @param high highest index
     * @param pivot element to pivot around
     */
    private void quickSort(E[] data, int low, int high, int pivot) {
    	if(low < high) {
    		pivot = partition(data, low, high);
    		quickSort(data, low, pivot - 1, pivot);
    		quickSort(data, pivot + 1, high, pivot);
    	}
    }
    
    /**
     * Testing of partition in the QuickSorter algorithm
     * @param data array of data being used
     * @param low lowest element index
     * @param high highest element index
     * @param pivot index used as pivot
     * @return int of partition teting result
     */
    public int partitionTesting(E[] data, int low, int high, int pivot) {
    	int separator = low;
        for (int j = low; j <= high; j++) {
            if (j == pivot) {
                // Simulate the pivot element
                continue;
            }
            if (compare(data[j], data[pivot]) <= 0) {
                swap(data, separator, j);
                separator++;
            }
        }

        swap(data, separator, pivot);

        return separator;
    }
    
    /**
     * This method handles partitioning of quicksorter algorithm, the use of the
     * pivot element to help sort data
     * @param data array of data being used
     * @param low lowest element index
     * @param high highest element index
     * @return int of partition result
     */
    private int partition(E[] data, int low, int high) {
    	int pivot = selector.selectPivot(low, high);
    	swap(data, pivot, high);
    	int part = partitionHelper(data, low, high);
    	return part;
    }
    
    /**
     * partition helper method to help with partitioning
     * @param data array of data being used
     * @param low lowest element index
     * @param high highest element index
     * @return int of result of partitioning
     */
    private int partitionHelper(E[] data, int low, int high) {
    	E pivot = data[high];
    	int separator = low;
    	for(int j = low; j < high; j++) {
    		if(compare(data[j], pivot) <= 0) {
    			swap(data, separator, j);
    			separator++;
    		}
    	}
    	swap(data, separator, high);
    	return separator;
    }
    
    /**
     * swap the elements in the array
     * @param data array of data being used
     * @param index1 element 1
     * @param index2 element 2
     */
    private void swap(E[] data, int index1, int index2) {
    	E temp = data[index1];
    	data[index1] = data[index2];
    	data[index2] = temp;
    }
	
	/**
     * Defines the behaviors of a PivotSelector
     * 
     * @author Dr. King
     *
     */
    private interface PivotSelector {
        /**
         * Returns the index of the selected pivot element
         * 
         * @param low  - the lowest index to consider
         * @param high - the highest index to consider
         * @return the index of the selected pivot element
         */
        int selectPivot(int low, int high);
    }
    
    /**
     * FirstElementSelector chooses the first index of the array as the index of the
     * pivot element that should be used when sorting
     * 
     * @author Dr. King
     *
     */
    public static class FirstElementSelector implements PivotSelector {

        @Override
        public int selectPivot(int low, int high) {
            return low;
        }
    }
    
    /**
     * MiddleElementSelector chooses the middle index of the array as the index of the
     * pivot element that should be used when sorting
     * 
     * @author Dr. King
     *
     */
    public static class MiddleElementSelector implements PivotSelector {

        @Override
        public int selectPivot(int low, int high) {
            return (low + high) / 2;
        }
    }
    
    /**
     * LastElementSelector chooses the last index of the array as the index of the
     * pivot element that should be used when sorting
     * 
     * @author Dr. King
     *
     */
    public static class LastElementSelector implements PivotSelector {

        @Override
        public int selectPivot(int low, int high) {
            return high;
        }
    }
    
    /**
     * RandomElementSelector chooses the random index of the array as the index of the
     * pivot element that should be used when sorting
     * 
     * @author Dr. King
     *
     */
    public static class RandomElementSelector implements PivotSelector {

        @Override
        public int selectPivot(int low, int high) {
        	Random rand = new Random();
        	int randomPivot = rand.nextInt((high - low) + 1) + low;
            return randomPivot;
        }
    }

	
}

