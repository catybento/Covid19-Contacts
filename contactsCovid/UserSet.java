package contactsCovid;

/**
 * UserSet 
 * @author Catarina Bento(57369) and Martim Gouveia(57482)
 */
public interface UserSet extends User{
	
	/**
	 * Inserts an user in the contact tree.
	 * @param user - user to be inserted
	 */
	void receiveContact(User user);
	
	/**
	 * Removes an user from the contact tree.
	 * @param user - user to be removed
	 * Pre-condition: user != null
	 */
	void removeContact(String login);
	
	/**
	 * Sends a message to all the contacts and groups of the user.
	 * @param message - message to be inserted
	 */
	void sendMessage(Message message);
	
	/**
	 * Inserts a message to the message list.
	 * @param message - message to be inserted
	 */
	void receiveMessage(Message message);
	
	/**
	 * Inserts a group to the group array.
	 * @param group - group to the added
	 */
	void addGroup(Group group);
	
	/**
	 * Removes a group from the group array.
	 * @param group - group to be removed
	 * Pre-condition: group != null
	 */
	void removeGroup(Group group);

}
