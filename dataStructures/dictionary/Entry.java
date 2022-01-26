package dataStructures.dictionary;

/**
 * Entry
 * @author Catarina Bento(57369) and Martim Gouveia(57482)
 * @param <K> Generic Key
 * @param <V> Generic Value 
 */
public interface Entry<K,V> {
	
	/**
	 * Returns the key in the entry
	 * @return key
	 */
	K getKey();
	
	/**
	 * Returns the value in the entry
	 * @return value
	 */
	V getValue();
}
