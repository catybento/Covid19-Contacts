package dataStructures.list;
import dataStructures.Iterator;
import dataStructures.exceptions.InvalidPositionException;
import dataStructures.exceptions.NoElementException;

/**
 * Doubly linked list Implementation
 * @author AED  Team
 * @version 1.0
 * @param <E> Generic Element
 */
public class DoublyLinkedList<E> implements List<E>  {

	/**
	 * Serial Version UID of the Class
	 */
	private static final long serialVersionUID = 0L;

	static class DListNode<E>{
		// Element stored in the node.
		protected E element;
		// (Pointer to) the next node.
		protected DListNode<E> next;
		// (Pointer to) the previous node.
		protected DListNode<E> previous;

		public DListNode( E elem, DListNode<E> thePrev, DListNode<E> theNext ){
			element = elem;
			previous = thePrev;
			next = theNext;
		}

		public DListNode( E theElement ){
			this(theElement, null, null);
		}

		public E getElement( ){
			return element;
		}

		public DListNode<E> getNext( ){
			return next;
		}

		public DListNode<E> getPrevious( ){
			return previous;
		}

		public void setElement( E newElement ){
			element = newElement;
		}

		public void setNext( DListNode<E> newNext ){
			next = newNext;
		}

		public void setPrevious( DListNode<E> newPrevious ){
			previous = newPrevious;
		}

	}

	// Node at the head of the list.
	protected DListNode<E> head;

	// Node at the tail of the list.
	protected DListNode<E> tail;

	// Number of elements in the list.
	protected int currentSize;

	/**
	 * Constructor
	 */
	public DoublyLinkedList( ){
		head = null;
		tail = null;
		currentSize = 0;
	}

	@Override
	public boolean isEmpty() {
		return (head == null);
	}

	@Override
	public int size() {
		return currentSize;
	}


	@Override
	public int find(E element) {
		DListNode<E> node = head;
		for(int i = 0; node != null; i++) {
			if(element.equals(node.getElement())) {
				return i;
			}
			else {
				node = node.getNext();
			}
		}
		return -1;
	}


	@Override
	public E getFirst() throws NoElementException {
		if(head == null) {
			throw new NoElementException();
		}
		return head.getElement();
	}

	@Override
	public E getLast() throws NoElementException {
		if(head == null){
			throw new NoElementException();
		}
		return tail.getElement();
	}

	@Override
	public E get(int position) throws InvalidPositionException {
		if (position < 0 || position >= currentSize)
			throw new InvalidPositionException();
		return getNode(position).getElement();
	}

	/**
	 * Gets the node in the position given
	 * @param position - position of the node we want to return 
	 * @return node
	 */
	private DListNode<E> getNode(int position){
		DListNode<E> node;

		if(position < currentSize/2){
			node = head;
			for(int i = 0; i < position; i++){
				node = node.getNext();
			}
		}
		else{
			node = tail;
			for(int i = currentSize-1; i > position; i--){
				node = node.getPrevious();
			}
		}

		return node;
	}


	@Override
	public void addFirst(E element) {
		DListNode<E> node;
		if(head == null) {
			node = new DListNode<E>(element);
			tail = node;
		}
		else {
			node = new DListNode<E>(element, null, head);
			head.setPrevious(node);
		}
		head = node;
		currentSize++;
	}

	@Override
	public void addLast(E element) {
		DListNode<E> node;
		if(head == null) {
			node  = new DListNode<E>(element);
			head = node;
		}
		else{
			node  = new DListNode<E>(element, tail, null);
			tail.setNext(node);
		}
		tail = node;
		currentSize++;
	}

	/**
	 * Adds a node to the list
	 * @param position - position of the node to be added
	 * @param element - element to be added
	 */
	private void addMiddle(int position, E element) {
		DListNode<E> prevNode = getNode(position-1);
		DListNode<E> newNode = new DListNode<E>(element, prevNode, prevNode.getNext());
		prevNode.setNext(newNode);
		newNode.getNext().setPrevious(newNode);
		currentSize++;
	}

	@Override
	public void add(int position, E element) throws InvalidPositionException {
		if (position < 0 || position > currentSize)
			throw new InvalidPositionException();
		if (position == 0)
			addFirst(element);
		else if (position == currentSize)
			addLast(element);
		else {
			addMiddle(position,element);
		}

	}

	/**
	 * Removes the first node in the list.
	 * Pre-condition: the list is not empty.
	 */
	private void removeFirstNode(){
		DListNode<E> nextNode = head.getNext();
		if(nextNode == null) {
			head = null;
			tail = null;
		}
		else {
			nextNode.setPrevious(null);
			head = nextNode;
		}
		currentSize--;
	}


	@Override
	public E removeFirst() throws NoElementException {
		if (head == null) {
			throw new NoElementException();
		}
		E element = head.getElement();
		this.removeFirstNode();
		return element;
	}

	/**
	 * Removes the last node in the list.
	 * Pre-condition: the list is not empty.
	 */
	private void removeLastNode() {
		DListNode<E> prevNode = tail.getPrevious();
		if(prevNode == null) {
			head = null;
		}
		else {
			prevNode.setNext(null);
		}
		tail = prevNode;
		currentSize--;
	}


	@Override
	public E removeLast( ) throws NoElementException {
		if (head == null) {
			throw new NoElementException();
		}
		E element = tail.getElement();
		this.removeLastNode();
		return element;
	}

	/**
	 * Removes the specified node from the list.
	 * Pre-condition: the node is neither the head nor the tail of the list.
	 * @param node - middle node to be removed
	 */
	private void removeMiddleNode(DListNode<E> node) {
		DListNode<E> nextNode = node.getNext();
		DListNode<E> prevNode = node.getPrevious();
		nextNode.setPrevious(prevNode);
		prevNode.setNext(nextNode);
		currentSize--;
	}

	/**
	 * Removes an element from the middle of the list
	 * @param position - position we want to remove
	 * @return element removed
	 */
	private E removeMiddle(int position) {
		DListNode<E> nodeToRemove = this.getNode(position);
		this.removeMiddleNode(nodeToRemove);
		return nodeToRemove.getElement();
	}

	@Override
	public E remove(int position) throws InvalidPositionException {
		if(position < 0 || position >= currentSize)
			throw new InvalidPositionException();
		if (position == 0)
			return removeFirst();
		if (position == currentSize-1)
			return removeLast();
		return removeMiddle(position);
	}

	@Override
	public boolean remove(E element) {
		DListNode<E> node = getElementNode(element);
		if ( node == null )
			return false;
		else {
			if ( node == head )
				this.removeFirstNode();
			else if ( node == tail )
				this.removeLastNode();
			else
				this.removeMiddleNode(node);
			return true;
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new DoublyLLIterator<E>(head,tail);
	}

	/**
	 * Removes all of the elements from the specified list and
	 * inserts them at the end of the list (in proper sequence).
	 * @param list - list to be appended to the end of this
	 */
	public void append(DoublyLinkedList<E> list) {
		if(list.isEmpty()) {
			return;
		}

		if(this.isEmpty()) {
			this.head = list.head;
		}
		else {
			this.tail.setNext(list.head);
			list.head.setPrevious(this.tail);
		}
		this.tail = list.tail;
		this.currentSize = this.currentSize + list.size();

		list.head = null;
		list.tail = null;
		list.currentSize = 0;

	}

	/**
	 * Returns the node that contains the element given
	 * @param element - given element
	 * @return node - node that contains the element
	 */
	private DListNode<E> getElementNode(E element){
		DListNode<E> node = head;

		while(node != null) {
			if(element.equals(node.getElement())) {
				return node;
			}
			else {
				node = node.getNext();
			}
		}
		return null;
	}

}
