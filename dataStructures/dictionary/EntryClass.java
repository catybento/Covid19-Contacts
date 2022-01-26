package dataStructures.dictionary;

/**
 * EntryClass
 * @author Catarina Bento(57369) and Martim Gouveia(57482)
 * @param <K> Generic Key
 * @param <V> Generic Value 
 */
public class EntryClass<K,V> implements EntrySet<K,V>{

	/**
	 * Key of the entry
	 */
	private K key;
	
	/**
	 * Value of the entry
	 */
	private V value;
	
	/**
	 * Constructor
	 * @param key, key of the entry
	 * @param value, value of the entry
	 */
	public EntryClass(K key, V value){
		this.key = key;
		this.value = value;
	}
	
	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}

	@Override
	public void setValue(V value) {
		this.value = value;
	}
	
}
