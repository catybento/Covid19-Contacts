package dataStructures.dictionary;

/**
 * EntrySet
 * @author Catarina Bento(57369) and Martim Gouveia(57482)
 * @param <K> Generic Key
 * @param <V> Generic Value 
 */
public interface EntrySet<K,V> extends Entry<K,V>{
	
	/**
	 * Sets a new value to the entry
	 * @param new value
	 */
	void setValue(V value);
	
}
