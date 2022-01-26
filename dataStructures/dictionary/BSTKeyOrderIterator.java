package dataStructures.dictionary;
import dataStructures.Iterator;
import dataStructures.exceptions.NoSuchElementException;
import dataStructures.stack.Stack;
import dataStructures.stack.StackInList;

/**
 * BSTKeyOrderIterator
 * @author Catarina Bento(57369) and Martim Gouveia(57482)
 * @param <K> Generic Key
 * @param <V> Generic Value 
 */
public class BSTKeyOrderIterator<K, V> implements Iterator<Entry<K, V>>{

	/**
	 * Serial Version UID of the Class
	 */
    private static final long serialVersionUID = 1L;

    /**
     * Stack of BSTNodes
     */
    private Stack<BinarySearchTree.BSTNode<K, V>> stack;
    
    /**
     * Root of the tree
     */
    private BinarySearchTree.BSTNode<K, V> root;

    
    /**
     * Constructor
     * @param root, root of the tree
     */
    public BSTKeyOrderIterator(BinarySearchTree.BSTNode<K, V> root){
       stack = new StackInList<>();
       this.root = root;
       rewind();
    }

    @Override
    public boolean hasNext() {
        return (!stack.isEmpty());
    }

    @Override
    public Entry<K, V> next() throws NoSuchElementException {
    	if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        BinarySearchTree.BSTNode<K, V> node = stack.pop();
        if(node.getRight() != null){
            left(node.getRight());
        }
        return node.getEntry();
    }

    @Override
    public void rewind() {
        while (!stack.isEmpty()){
            stack.pop();
        }

        if(root != null){
            left(root);
        }
    }

    /**
     * Recursive method that inserts the nodes in the stack.
     * @param node, node to begin the method
     */
    private void left(BinarySearchTree.BSTNode<K, V> node){
        stack.push(node);
        if(node.getLeft() != null){
            left(node.getLeft());
        }
    }

}
