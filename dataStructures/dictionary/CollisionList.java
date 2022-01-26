package dataStructures.dictionary;
import dataStructures.Iterator;
import dataStructures.list.DoublyLinkedList;
import dataStructures.list.List;

/**
 * CollisionList
 * @author Catarina Bento(57369) and Martim Gouveia(57482)
 * @param <K> Generic Key
 * @param <V> Generic Value 
 */
public class CollisionList<K,V> implements Dictionary<K,V>{

	/**
	 * List of entries.
	 */
	private List<Entry<K,V>> collisionList;
	
	/**
	 * Constructor
	 */
	public CollisionList(){
		collisionList = new DoublyLinkedList<Entry<K,V>>();
	}
	
	@Override
	public boolean isEmpty() {
		return collisionList.isEmpty();
	}

	@Override
	public int size() {
		return collisionList.size();
	}

	@Override
	public V find(K key) {
		Entry<K,V> entry = getEntry(key);
		if(entry != null) {
			return entry.getValue();
		}
		return null;
	}

	@Override
	public V insert(K key, V value) {
		Entry<K,V> entry = getEntry(key);
		if(entry != null) {
			V oldValue = entry.getValue();
			((EntrySet<K, V>)entry).setValue(value);
			return oldValue;
		}
		else {
			collisionList.addLast(new EntryClass<>(key, value));
			return null;
		}
	}

	@Override
	public V remove(K key) {
		Entry<K,V> entry = getEntry(key);
		if(entry != null) {
			collisionList.remove(entry);
			return entry.getValue();
		}
		return null;
	}

	@Override
	public Iterator<Entry<K,V>> iterator() {
		return collisionList.iterator();
	}
	
	/**
	 * Returns the entry with specified key.
	 * @param key, key of the wanted entry
	 * @return wanted entry
	 */
	private Entry<K,V> getEntry(K key){
		Iterator<Entry<K,V>> it = iterator();
		while(it.hasNext()) {
			Entry<K,V> entry = it.next();
			if(entry.getKey().equals(key)) {
				return entry;
			}
		}
		return null;
	}

}
