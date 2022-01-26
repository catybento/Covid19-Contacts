package contactsCovid;
import contactsCovid.exceptions.*;
import dataStructures.Iterator;
import dataStructures.dictionary.BinarySearchTree;
import dataStructures.dictionary.OrderedDictionary;
import dataStructures.list.DoublyLinkedList;
import dataStructures.list.List;

/**
 * Group 
 * @author Catarina Bento(57369) and Martim Gouveia(57482)
 */
public class GroupClass implements GroupSet{

    /**
    * Name of the group.
    * Description of the group.
    */
    private String name, desc;
    
    /**
     * Ordered dictionary of users - because it needs to be lexicographically ordered and it has better temporal complexity
     */
    private OrderedDictionary<String,User> memberTree;
    
    /**
     * List of messages - because it needs to maintain order of insertion
     */
    private List<Message> msgList;


    /**
     * Constructor
     * @param name - name of the group.
     * @param desc - description of the group.
     */
    public GroupClass(String name, String desc){
    	memberTree = new BinarySearchTree<>();
        msgList = new DoublyLinkedList<>();
        this.name = name;
        this.desc = desc;
    }

    @Override
    public String getGroupName(){
        return name;
    }

	@Override
	public String getDescription() {
		return desc;
	}

    @Override
    public void addMember(User user) {
    	memberTree.insert(user.getLogin(),user);
    }

    @Override
    public boolean removeMember(String login) {
    	return (memberTree.remove(login) != null);
    }

    @Override
    public boolean hasMember(String login) {
    	return (memberTree.find(login) != null);
    }

    @Override
    public boolean isEmpty() {
        return memberTree.isEmpty();
    }
    
    @Override
    public Iterator<User> iterator() throws NoParticipants {
        if(memberTree.isEmpty()){
            throw new NoParticipants();
        }

        return memberTree.valueIterator();
    }

    @Override
    public void receiveMessage(Message message) {
        msgList.addFirst(message);
    }

	@Override
	public Iterator<Message> getGroupMessages() throws NoGroupMessages {
		
		if(msgList.isEmpty()) {
			throw new NoGroupMessages();
		}
		
		return msgList.iterator();
	}

}
