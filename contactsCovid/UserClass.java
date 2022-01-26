package contactsCovid;
import contactsCovid.exceptions.*;
import dataStructures.Iterator;
import dataStructures.dictionary.BinarySearchTree;
import dataStructures.dictionary.Entry;
import dataStructures.dictionary.OrderedDictionary;
import dataStructures.list.DoublyLinkedList;
import dataStructures.list.List;

/**
 * UserClass
 * @author Catarina Bento(57369) and Martim Gouveia(57482)
 */
public class UserClass implements UserSet{
	
	/**
	 * Maximum number of groups
	 */
	private static final int SIZE = 10;

	/**
	 * Login of the user
	 * Name of the user
	 * Address of the user
	 * Profession of the user
	 */
	private String login, name, address, profession;

	/**
	 * Age of the user
	 * Number of groups
	 */
	private int age, groupCounter;

	/**
	 * Ordered dictionary of users - because it needs to be lexicographically ordered and it has better temporal complexity
	 */
	private OrderedDictionary<String, User> contactTree;

	/**
	 * Array of groups - because it has a limit of groups
	 */
	private Group[] userGroups;

	/**
	 * List of messages - because it needs to maintain order of insertion
	 */
	private List<Message> messageList;

	/**
	 * Constructor
	 * @param login - login of the user
	 * @param name - name of the user
	 * @param age - age of the user
	 * @param address - address of the user
	 * @param profession - profession of the user
	 */
	public UserClass(String login, String name, int age, String address, String profession) {
		this.login = login;
		this.name = name;
		this.age = age;
		this.address = address;
		this.profession = profession;
		groupCounter = 0;
		contactTree = new BinarySearchTree<String,User>();
		userGroups = new GroupClass[10];
		messageList = new DoublyLinkedList<Message>();
	}

	@Override
	public String getLogin() {
		return login;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getAge() {
		return age;
	}

	@Override
	public String getAddress() {
		return address;
	}


	@Override
	public String getProfession() {
		return profession;
	}

	@Override
	public void receiveContact(User user) {
		contactTree.insert(user.getLogin(), user);
	}

	@Override
	public boolean hasContact(String login) {
		return (contactTree.find(login) != null);
	}

	@Override
	public void removeContact(String login) {
		contactTree.remove(login);
	}

	@Override
	public Iterator<User> getContacts() throws NoContacts {
		if(contactTree.isEmpty()){
			throw new NoContacts();
		}
		return contactTree.valueIterator();
	}

	@Override
	public void sendMessage(Message message) {
		messageList.addFirst(message);

		Iterator<Entry<String,User>> itC = contactTree.iterator();

		if(!contactTree.isEmpty()){
			while(itC.hasNext()){
				((UserSet) itC.next().getValue()).receiveMessage(message);
			}
		}

		for(int i = 0; i < getNumberOfGroups(); i++){
			((GroupSet) userGroups[i]).receiveMessage(message);
		}

	}

	@Override
	public void receiveMessage(Message message) {
		messageList.addFirst(message);
	}

	@Override
	public Iterator<Message> getMessages() throws NoContactMessages {

		if(messageList.isEmpty()) {
			throw new NoContactMessages();
		}

		return messageList.iterator();
	}

	@Override
	public int compareTo(User other) {
		return (other.getLogin().compareTo(this.login));
	}

	@Override
	public int getNumberOfGroups() {
		return groupCounter;
	}

	@Override
	public void addGroup(Group group) {
		if(userGroups[SIZE-1] == null){
			userGroups[groupCounter++] = group;
		}
	}

	@Override
	public void removeGroup(Group group) {
		for(int i = getGroupIndex(group); i < (groupCounter-1); i++){
			userGroups[i] = userGroups[i+1];
		}
		userGroups[--groupCounter] = null;
	}

	/**
	 * Gets the index of a given group
	 * @param group - group given
	 * @return index
	 */
	private int getGroupIndex(Group group){

		for(int i=0; i<groupCounter; i++){
			if(userGroups[i].getGroupName().equals(group.getGroupName())){
				return i;
			}
		}
		return -1;
	}

}
