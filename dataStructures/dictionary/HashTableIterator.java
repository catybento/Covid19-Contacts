package dataStructures.dictionary;
import dataStructures.Iterator;
import dataStructures.exceptions.NoSuchElementException;

/**
 * HashTableIterator
 * @author Catarina Bento(57369) and Martim Gouveia(57482)
 * @param <K> Generic Key
 * @param <V> Generic Value 
 */
public class HashTableIterator<K,V> implements Iterator<Entry<K,V>>{

	/**
	 * Serial Version UID of the Class
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Array of dictionaries.
	 */
	private Dictionary<K,V>[] table;

	/**
	 * Current index.
	 */
    private int index;

    /**
     * Iterator of entries.
     */
    private Iterator<Entry<K,V>> listIt;

    /**
     * Constructor
     * @param table, array of dictionaries
     */
    public HashTableIterator(Dictionary<K,V>[] table){
        this.table = table;
        this.rewind();
    }

    @Override
    public boolean hasNext() {
        if(listIt.hasNext()){
            return true;
        }

        Iterator<Entry<K,V>> it;
        for(int i = index+1; i<table.length; i++){
            it = table[i].iterator();
            if(it.hasNext()){
                return true;
            }
        }

        return false;
    }

    @Override
    public Entry<K, V> next() throws NoSuchElementException {
    	if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        return getNext();
    }

    @Override
    public void rewind() {
        index = 0;
        listIt = table[index].iterator();
    }

    /**
     * Gets the next entry
     * @return next entry
     */
    private Entry<K,V> getNext(){
        while (!listIt.hasNext()){
            listIt = table[++index].iterator();
        }
        return listIt.next();
    }

}
