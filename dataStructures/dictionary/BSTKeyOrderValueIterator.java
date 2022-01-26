package dataStructures.dictionary;
import dataStructures.Iterator;
import dataStructures.exceptions.NoSuchElementException;

/**
 * BSTKeyOrderValueIterator
 * @author Catarina Bento(57369) and Martim Gouveia(57482)
 * @param <K> Generic Key
 * @param <V> Generic Value 
 */
public class BSTKeyOrderValueIterator<K, V> implements Iterator<V> {

	/**
	 * Serial Version UID of the Class
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Iterator of entries
	 */
	private Iterator<Entry<K, V>> it;

	/**
	 * Constructor
	 * @param root, root of the tree
	 */
	public BSTKeyOrderValueIterator(BinarySearchTree.BSTNode<K, V> root) {
		it = new BSTKeyOrderIterator<>(root);
	}

	@Override
	public boolean hasNext() {
		return it.hasNext();
	}

	@Override
	public V next() throws NoSuchElementException {
		return it.next().getValue();
	}

	@Override
	public void rewind() {
		it.rewind();
	}
}
