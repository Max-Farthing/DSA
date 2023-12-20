package edu.ncsu.csc316.dsa.map.hashing;

import edu.ncsu.csc316.dsa.map.Map;

/**
 * The LinearProbingHashMap is implemented as a hash table that uses linear
 * probing for collision resolution.
 * 
 * The hash map uses a multiply-and-divide compression strategy for calculating
 * hash functions. The hash map ensures expected O(1) performance of
 * {@link Map#put}, {@link Map#get}, and {@link Map#remove}.
 * 
 * The hash table resizes if the load factor exceeds 0.5.
 * 
 * The LinearProbingHashMap class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 *
 * @param <K> the type of keys stored in the hash map
 * @param <V> the type of values associated with keys in the hash map
 */
public class LinearProbingHashMap<K, V> extends AbstractHashMap<K, V> {
	/** table used for containing entries of KV pairs **/
    private TableEntry<K, V>[] table;
    /** number of elements in map **/
    private int size;

    /**
     * Constructs a new linear probing hash map that uses natural ordering of keys
     * when performing comparisons. The created hash table uses the
     * {@link AbstractHashMap#DEFAULT_CAPACITY}
     */
    public LinearProbingHashMap() {
        this(AbstractHashMap.DEFAULT_CAPACITY, false);
    }

    /**
     * FOR TESTING PURPOSES ONLY! Constructs a new linear probing hash map that uses
     * natural ordering of keys when performing comparisons. The created hash table
     * uses the {@link AbstractHashMap#DEFAULT_CAPACITY}
     * 
     * @param isTesting if true, the hash table uses a predictable series of random
     *                  values for deterministic and repeatable testing
     */
    public LinearProbingHashMap(boolean isTesting) {
        this(AbstractHashMap.DEFAULT_CAPACITY, isTesting);
    }

    /**
     * Constructs a new linear probing hash map that uses natural ordering of keys
     * when performing comparisons. The created hash table is initialized to have
     * the provided capacity.
     * 
     * @param capacity the initial capacity of the hash table
     */
    public LinearProbingHashMap(int capacity) {
        this(capacity, false);
    }

    /**
     * FOR TESTING PURPOSES ONLY! Constructs a new linear probing hash map that uses
     * natural ordering of keys when performing comparisons. The created hash table
     * is initialized to have the provided capacity.
     * 
     * @param capacity  the initial capacity of the hash table
     * @param isTesting if true, the hash table uses a predictable series of random
     *                  values for deterministic and repeatable testing
     */
    public LinearProbingHashMap(int capacity, boolean isTesting) {
        super(capacity, isTesting);
        size = 0;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
    	EntryCollection collection = new EntryCollection();
    	for(int i = 0; i < table.length; i++) {
    		if(table[i] != null && !table[i].isDeleted()) {
    			collection.add(table[i]);
    		}
    	}
    	return collection;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public void createTable(int capacity) {
        table = (TableEntry<K, V>[]) new TableEntry[capacity];
        size = 0;
    }

    private boolean isAvailable(int index) {
        return (table[index] == null || table[index].isDeleted());
    }

    @Override
    public V bucketGet(int hash, K key) {
    	int i = findBucket(hash, key);
    	
    	if(i < 0) {
    		return null;
    	}
    	
    	V value = table[i].getValue();
    	return value;
    }

    @Override
    public V bucketPut(int hash, K key, V value) {
    	int i = findBucket(hash, key);
    	if(i < 0) {
    		i = Math.abs(i + 1);
        	TableEntry<K, V> insert = new TableEntry<K, V>(key, value);
    		table[i] = insert;
    		size++;
    		return null;
    	}
    	
    	V oldValue = table[i].getValue();
    	table[i].setValue(value);
		
    	return oldValue;
    }

    private int findBucket(int index, K key) {
    	int avail = -1;
    	int j = index;
    	do {
    		if(isAvailable(j)) {
    			if(avail == -1) {
    				avail = j;
    			}
    			if(table[j] == null) {
    				return -(avail + 1);
    			}
    		} else if(table[j].getKey().equals(key)) {
    			return j;
    		}
    		
    		j = (j + 1) % capacity();
    	} while (j != index);
    	return -(avail + 1);
    }

    @Override
    public V bucketRemove(int hash, K key) { //method might need changes
    	int i = findBucket(hash, key);
        // Remember to set the table bucket as DELETED using setDeleted(true)
    	if(i < 0) {
        	return null;
    	}

		V value = table[i].getValue();
    	table[i].setDeleted(true);
    	size--;
    	return value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected int capacity() {
        return table.length;
    }

    private static class TableEntry<K, V> extends MapEntry<K, V> {
    	/** boolean whether entry has been deleted or not **/
        private boolean isDeleted;

        public TableEntry(K key, V value) {
            super(key, value);
            setDeleted(false);
        }

        public boolean isDeleted() {
            return isDeleted;
        }

        public void setDeleted(boolean deleted) {
            isDeleted = deleted;
        }
    }
}
