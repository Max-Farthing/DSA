package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;
import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * An unordered link-based map is an unordered (meaning keys are not used to
 * order entries) linked-memory representation of the Map abstract data type.
 * This link-based map delegates to an existing doubly-linked positional list.
 * To help self-organizing entries to improve efficiency of lookUps, the
 * unordered link-based map implements the move-to-front heuristic: each time an
 * entry is accessed, it is shifted to the front of the internal list.
 * 
 * @author Dr. King
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class UnorderedLinkedMap<K, V> extends AbstractMap<K, V> {

	/** underlying positional list to help implement the underordered linked map **/
    private PositionalList<Entry<K, V>> list;
    
    public UnorderedLinkedMap() {
        this.list = new PositionalLinkedList<Entry<K, V>>();
    }
    
    /**
     * iterates through the positions in list to find the given key and return null if it
     * is not in the list
     * @param key Specified key of Key Value pair in map
     * @return position of entry, returns null if not found in list
     */
    private Position<Entry<K, V>> lookUp(K key)
    {
        for(Position<Entry<K, V>> position : list.positions()) {
        	if(position.getElement().getKey().equals(key)) {
        		return position;
        	}
        }
    	return null;
    }

    @Override
    public V get(K key) {
    	Position<Entry<K, V>> p = lookUp(key);
        if (p != null && p.getElement() != null && p.getElement().getKey() != null) {
            moveToFront(p);
            return p.getElement().getValue();
        }

        return null;
    }
    
    /**
     * helper method that provides the move to front heuristic to help the average run time(not worst case)
     * @param position Position of entry node
     */
    private void moveToFront(Position<Entry<K, V>> position) {
    	list.remove(position);
    	list.addFirst(position.getElement()); //double check if this works
    }

    @Override
    public V put(K key, V value) {
        Position<Entry<K, V>> p = lookUp(key);
        if(p != null) {
            V elem = p.getElement().getValue();
            AbstractMap.MapEntry<K, V> abent = new AbstractMap.MapEntry<K, V>(key, value);
            abent.setValue(value);
            list.remove(p);
            list.addFirst(abent);
            return elem;
        } else {
        	Entry<K, V> ent = new MapEntry<K, V>(key, value);
        	list.addFirst(ent);
        	return null;
        }
        
    }
    
    @Override
    public V remove(K key) {
    	Position<Entry<K, V>> p = lookUp(key);
        if (p != null && p.getElement() != null && p.getElement().getKey() != null) {
            V elem = p.getElement().getValue();
            list.remove(p);
            return elem;
        }
        return null;
       
    }
    
    @Override
    public int size() {
        return list.size();
    }
    
    @Override
    public Iterable<Entry<K, V>> entrySet() {
    	EntryCollection collection = new EntryCollection();
        for(Entry<K, V> entry : list) {
            collection.add(entry);
        }
        return collection;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("UnorderedLinkedMap[");
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
