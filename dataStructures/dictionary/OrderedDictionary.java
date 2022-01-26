package dataStructures.dictionary;

import dataStructures.Iterator;
import dataStructures.exceptions.NoElementException;

/**
 * Ordered Dictionary interface
 *
 * @author AED team
 * @version 1.0
 * 
 * @param <K> Generic type Key, must extend comparable
 * @param <V> Generic type Value 
 */
public interface OrderedDictionary<K extends Comparable<K>, V> extends Dictionary<K,V>{                                                                   

    /**
     * Returns the entry with the smallest key in the dictionary.
     * @return entry with the smallest key in the dictionary
     * @throws EmptyDictionaryException
     */
    Entry<K,V> minEntry() throws NoElementException;

    /**
     * Returns the entry with the largest key in the dictionary.
     * @return entry with the largest key in the dictionary
     * @throws EmptyDictionaryException
     */
    Entry<K,V> maxEntry() throws NoElementException;

    /**
     * Returns an iterator of the values in the dictionary.
     * @return iterator of the values in the dictionary
     */
    Iterator<V> valueIterator();  

} 

