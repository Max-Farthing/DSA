package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;
import java.util.Iterator;
import edu.ncsu.csc316.dsa.list.ArrayBasedList;

/**
 * A Search Table map is an ordered (meaning entries are stored in a sorted
 * order based on the keys of the entries) contiguous-memory representation of
 * the Map abstract data type. This array-based map delegates to an existing
 * array-based list. To improve efficiency of lookUps, the search table map
 * implements binary search to locate entries in O(logn) worst-case runtime.
 * Insertions and deletions have O(n) worst-case runtime.
 * 
 * @author Dr. King
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class SearchTableMap<K extends Comparable<K>, V> extends AbstractOrderedMap<K, V> {

    private ArrayBasedList<Entry<K, V>> list;

    /**
     * Constructs a new SearchTableMap where keys of entries are compared based on
     * their natural ordering based on {@link Comparable#compareTo}
     */
    public SearchTableMap() {
        this(null);
    }
    
    /**
     * Constructs a new SearchTableMap where keys of entries are compared based on a
     * provided {@link Comparator}
     * 
     * @param compare a Comparator that defines comparisons rules for keys in the
     *                map
     */ 
    public SearchTableMap(Comparator<K> compare) {
        super(compare);
        list = new ArrayBasedList<Entry<K, V>>();
    }

    private int lookUp(K key) {
    	
    	for(int i = 0; i < list.size(); i++) {
    		if(list.get(i).getKey().equals(key)) {
    			return i;
    		}
    	}
    	return -1;
    }

    /**
     * binary search helper, finds midpoint recursively in list and compares given key to the midpoint
     * @param min minimum index
     * @param max maximum index
     * @param key the key we want to find
     * @return int of index of midpoint(negative if key is not in list)
     */
    private int binarySearchHelper(int min, int max, K key) {
    	if(min > max) {
    		return -1 * (min + 1);
    	} 
    	int mid = (max + min) / 2;
    	K k = list.get(mid).getKey();
    	if(k.equals(key)) {
    		return mid;
    	} else if(compare(k, key) > 0) {
    		return binarySearchHelper(min, mid - 1, key);
    	} else {
    		return binarySearchHelper(mid + 1, max, key);
    	}
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public V get(K key) {
        int index = lookUp(key);
        if(index >= 0) {
        	int num = binarySearchHelper(0, list.size() - 1, key);
        	return list.get(num).getValue();
        }    
        
        return null;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        EntryCollection set = new EntryCollection();
        for (Entry<K, V> entry : list) {
            set.add(entry);
        }
        return set;
    }

    @Override
    public V put(K key, V value) {
        int index = lookUp(key);
    	int num = binarySearchHelper(0, list.size() - 1, key);
		Entry<K, V> temp = new MapEntry<K, V>(key, value);
        if(index >= 0) {
            V ret = list.get(index).getValue();
        	if(num >= 0) {
        		list.set(num, temp);
        	} else {
        		list.add(-num - 1, temp);
        	}
    		return ret;
        } 
        
        list.add(-num - 1, temp);
        return null;
    }

    @Override
    public V remove(K key) {
        int index = lookUp(key);
        if(index >= 0) {
        	V temp = list.get(index).getValue();
        	list.remove(index);
        	return temp;
        }
        return null;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SearchTableMap[");
        Iterator<Entry<K, V>> it = list.iterator();
        while(it.hasNext()) {
            sb.append(it.next().getKey());
            if(it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
