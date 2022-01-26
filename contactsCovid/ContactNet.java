package contactsCovid;
import contactsCovid.exceptions.*;
import dataStructures.Iterator;


/**
 * ContactNet
 * @author Catarina Bento(57369) and Martim Gouveia(57482)
 */
public interface ContactNet {

	/**
	 * Inserts an user in the user dictionary.
	 * @param login - login of the user.
	 * @param name - name of the user.
	 * @param age - age of the user.
	 * @param address - address of the user.
	 * @param profession - profession of the user.
	 * @throws UserExists
	 */
	void insertUser(String login, String name, int age, String address, String profession) 
			throws UserExists;

	/**
	 * Shows the user with the specified login.
	 * @param login - login of the user.
	 * @return user with the specified login
	 * @throws UserNotExists
	 */
	User showUser(String login) throws UserNotExists;

	/**
	 * Inserts contact in the contact tree of the user with the specified login.
	 * @param login1 - login of the first user.
	 * @param login2 - login of the second user.
	 * @throws UserNotExists
	 * @throws ContactExists
	 */
	void insertContact(String login1, String login2) 
			throws UserNotExists, ContactExists;

	/**
	 * Removes the contact relation between the two users.
	 * @param login1 - login of the first user.
	 * @param login2 - login of the second user.
	 * @throws UserNotExists
	 * @throws ContactNotExists
	 * @throws ContactNotRemoved
	 */
	void removeContact(String login1, String login2) 
			throws UserNotExists, ContactNotExists, ContactNotRemoved;

	/**
	 * Lists the contacts of the user with the specified login.
	 * @param login - login of the user.
	 * @return - iterator of contacts of the user with the specified login.
	 * @throws UserNotExists
	 * @throws NoContacts
	 */
	Iterator<User> listContacts(String login)
			throws UserNotExists, NoContacts;

	/**
	 * Creates a new group.
	 * @param group - name of the group.
	 * @param description - description of the group.
	 * @throws GroupExists
	 */
	void insertGroup(String group, String description) throws GroupExists;

	/**
	 * Shows the group with the specified name.
	 * @param group - name of the group.
	 * @return group with the specified name.
	 * @throws GroupNotExists
	 */
	Group showGroup(String group) throws GroupNotExists;

	/**
	 * Deletes the group with the specified name.
	 * @param group - name of the group.
	 * @throws GroupNotExists
	 */
	void removeGroup(String group) throws GroupNotExists;

	/**
	 * Subscribe inserts the user in the group with the specified name;
	 * @param login - login of the user.
	 * @param group - name of the group.
	 * @throws UserNotExists
	 * @throws GroupNotExists
	 * @throws SubscriptionExists
	 */
	void subscribeGroup(String login, String group) 
			throws UserNotExists, GroupNotExists, SubscriptionExists;

	/**
	 * Removes the user from the group with the specified name;
	 * @param login - login of the user.
	 * @param group - name of the group.
	 * @throws UserNotExists
	 * @throws GroupNotExists
	 * @throws SubscriptionNotExists
	 */
	void removeSubscription(String login, String group)
		throws UserNotExists, GroupNotExists, SubscriptionNotExists;

	/**
	 * Lists the participants of the group with the specified name.
	 * @param group - name of the group.
	 * @return iterator of participants of the group with the specified name.
	 * @throws GroupNotExists
	 * @throws NoParticipants
	 */
	Iterator<User> listParticipants(String group)
			throws GroupNotExists, NoParticipants;

	/**
	 * The user with the specified login sends a message.
	 * @param login - login of the user.
	 * @param title - title of the message.
	 * @param text - content of the message.
	 * @param url - url of the message.
	 * @throws UserNotExists
	 */
	void insertMessage(String login, String title, String text, String url)
		throws UserNotExists;

	/**
	 * Lists the messages of the user with the specified name.
	 * @param login1 - login of the user that shares the messages.
	 * @param login2 - login of the user that asks for the messages.
	 * @return iterator of messages of the user.
	 * @throws UserNotExists
	 * @throws ContactNotExists
	 * @throws NoContactMessages
	 */
	Iterator<Message> listContactMessages(String login1, String login2)
		throws UserNotExists, ContactNotExists, NoContactMessages;

	/**
	 * Lists the messages of the group with the specified name.
	 * @param group - name of the group.
	 * @param login - login of the user.
	 * @return iterator of messages of the specified group.
	 * @throws GroupNotExists
	 * @throws UserNotExists
	 * @throws SubscriptionNotExists
	 * @throws NoGroupMessages
	 */
	Iterator<Message> listGroupMessages(String group, String login)
			throws GroupNotExists, UserNotExists, SubscriptionNotExists, NoGroupMessages;

}
