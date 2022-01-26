package dataStructures.stack;
import dataStructures.exceptions.NoElementException;
import dataStructures.list.DoublyLinkedList;
import dataStructures.list.List;

/**
 * Stack in list Implementation
 * @author AED  Team
 * @version 1.0
 * @param <E> Generic Element
 */
public class StackInList<E> implements Stack<E>{
	
	/**
	 * Serial Version UID of the Class
	 */
    private static final long serialVersionUID = 0L;


    /**
     * Memory of the stack: a list.
     */
    protected List<E> list;                     

    /**
     * Constructor
     */
    public StackInList(){     
        list = new DoublyLinkedList<E>();
    }


    @Override
    public boolean isEmpty(){     
        return list.isEmpty();
    }

    @Override
    public int size(){     
        return list.size();
    }

    @Override
    public E top() throws NoElementException{     
        if (list.isEmpty())
            throw new NoElementException();
        
        return list.getFirst();
    }

    @Override
    public void push(E element){ 
        list.addFirst(element);
    }

    @Override
    public E pop() throws NoElementException{     
        if (list.isEmpty())
            throw new NoElementException();

        return list.removeFirst();
    }

}
