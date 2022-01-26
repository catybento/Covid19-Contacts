package contactsCovid;
import contactsCovid.exceptions.*;
import dataStructures.Iterator;
import dataStructures.dictionary.ChainedHashTable;
import dataStructures.dictionary.Dictionary;

/**
 * ContactNetClass 
 * @author Catarina Bento(57369) and Martim Gouveia(57482)
 */
public class ContactNetClass implements ContactNet{

	/**
	 * Dictionary of users with the login as key - because it doesn't need to be ordered and has better temporal complexity 
	 */
    private Dictionary<String, UserSet> userDictionary;
    
    /**
     * Dictionary of groups with the group name as key - because it doesn't need to be ordered and has better temporal complexity
     */
    private Dictionary<String, GroupSet> groupDictionary;

    /**
     * Constructor
     */
    public ContactNetClass(){
    	userDictionary = new ChainedHashTable<>();
        groupDictionary = new ChainedHashTable<>();
    }


    @Override
    public void insertUser(String login, String name, int age, String address, String profession) throws UserExists {
    	UserSet user = new UserClass(login, name, age, address, profession);

        if(userDictionary.find(login) != null){
            throw new UserExists();
        }

        userDictionary.insert(user.getLogin(), user);
    }


    @Override
    public User showUser(String login) throws UserNotExists {
        User user = userDictionary.find(login);
        if(user == null){
            throw new UserNotExists();
        }

        return user;
    }


    @Override
    public void insertContact(String login1, String login2) throws UserNotExists, ContactExists {
        UserSet user1 = userDictionary.find(login1);
        UserSet user2 = userDictionary.find(login2);
        if((user1 == null) || (user2 == null)){
            throw new UserNotExists();
        }

        if(login1.equals(login2) || (user1.hasContact(user2.getLogin())) || (user2.hasContact(user1.getLogin()))){
            throw new ContactExists();
        }

        user1.receiveContact(user2);
        user2.receiveContact(user1);
    }


    @Override
    public void removeContact(String login1, String login2) throws UserNotExists, ContactNotExists, ContactNotRemoved {
        UserSet user1 = userDictionary.find(login1);
        UserSet user2 = userDictionary.find(login2);
        if((user1 == null) || (user2 == null)){
            throw new UserNotExists();
        }

        if(login1.equals(login2)){
            throw new ContactNotRemoved();
        }

        if(!user1.hasContact(user2.getLogin()) || !user2.hasContact(user1.getLogin())){
            throw new ContactNotExists();
        }

        user1.removeContact(user2.getLogin());
        user2.removeContact(user1.getLogin());
    }


    @Override
    public Iterator<User> listContacts(String login) throws UserNotExists, NoContacts {
        User user = userDictionary.find(login);

        if(user == null){
            throw new UserNotExists();
        }

        try{
            return user.getContacts();
        }
        catch (NoContacts e){
            throw new NoContacts();
        }
    }


    @Override
    public void insertGroup(String group, String description) throws GroupExists {
        if(groupDictionary.find(group) != null){
            throw new GroupExists();
        }

        GroupSet newGroup = new GroupClass(group, description);
        groupDictionary.insert(newGroup.getGroupName(), newGroup);
    }


    @Override
    public Group showGroup(String group) throws GroupNotExists {
    	Group group1 = groupDictionary.find(group);
    	
        if(group1 == null){
            throw new GroupNotExists();
        }

        return group1;
    }


    @Override
    public void removeGroup(String group) throws GroupNotExists {
        GroupSet group1 = groupDictionary.find(group);

        if(group1 == null){
            throw new GroupNotExists();
        }

        if(!group1.isEmpty()){
            Iterator<User> it = group1.iterator();

            while (it.hasNext()){
                ((UserSet) it.next()).removeGroup(group1);
            }
        }

        groupDictionary.remove(group1.getGroupName());
    }


    @Override
    public void subscribeGroup(String login, String group) throws UserNotExists, GroupNotExists, SubscriptionExists {
        UserSet user = userDictionary.find(login);

        if(user == null){
            throw new UserNotExists();
        }

        GroupSet group1 = groupDictionary.find(group);
        if(group1 == null){
            throw new GroupNotExists();
        }

        if(group1.hasMember(user.getLogin())){
            throw new SubscriptionExists();
        }

        if(user.getNumberOfGroups() < 10){
            group1.addMember(user);
            user.addGroup(group1);
        }

    }


    @Override
    public void removeSubscription(String login, String group) throws UserNotExists, GroupNotExists, SubscriptionNotExists {
        UserSet user = userDictionary.find(login);

        if(user == null){
            throw new UserNotExists();
        }

        GroupSet group1 = groupDictionary.find(group);
        if(group1 == null){
            throw new GroupNotExists();
        }

        if(!group1.removeMember(user.getLogin())){
            throw new SubscriptionNotExists();
        }

        user.removeGroup(group1);
    }


    @Override
    public Iterator<User> listParticipants(String group) throws GroupNotExists, NoParticipants {
        Group group1 = groupDictionary.find(group);

        if(group1 == null){
            throw new GroupNotExists();
        }

        try{
            return group1.iterator();
        }
        catch (NoParticipants e){
            throw new NoParticipants();
        }

    }


    @Override
    public void insertMessage(String login, String title, String text, String url) throws UserNotExists {
        UserSet user = userDictionary.find(login);

        if(user == null){
            throw new UserNotExists();
        }

        Message message = new MessageClass(title, text, url);
        user.sendMessage(message);
    }


    @Override
    public Iterator<Message> listContactMessages(String login1, String login2) throws UserNotExists, ContactNotExists, NoContactMessages {
    	User user1 = userDictionary.find(login1);
    	User user2 = userDictionary.find(login2);
    	
    	if((user1 == null) || (user2 == null)){
            throw new UserNotExists();
        }
    	
    	if((!(user1.hasContact(user2.getLogin())) || !(user2.hasContact(user1.getLogin()))) && !(login1.equals(login2))) {
    		throw new ContactNotExists();
    	}
    	
    	try {
    		return user1.getMessages();
    	}
    	catch(NoContactMessages e){
    		throw new NoContactMessages();
    	}
    	
    }


    @Override
    public Iterator<Message> listGroupMessages(String group, String login) throws GroupNotExists, UserNotExists, SubscriptionNotExists, NoGroupMessages {
    	User user = userDictionary.find(login);
    	Group group1 = groupDictionary.find(group);
    	
    	if(group1 == null) {
    		throw new GroupNotExists();
    	}
    	
    	if(user == null) {
    		throw new UserNotExists();
    	}
    	
    	if(!(group1.hasMember(user.getLogin()))) {
    		throw new SubscriptionNotExists();
    	}
    	
    	try {
    		return group1.getGroupMessages();
    	}
    	catch(NoGroupMessages e) {
    		throw new NoGroupMessages();
    	}
    	
    }

}
