package contactsCovid;

import contactsCovid.exceptions.*;
import dataStructures.Iterator;

/**
 * User 
 * @author Catarina Bento(57369) and Martim Gouveia(57482)
 */
public interface User extends Comparable<User>{
	
	/**
	 * Gets the login of the user.
	 * @return login of the user
	 */
	String getLogin();
	
	/**
	 * Gets the name of the user.
	 * @return name of the user
	 */
	String getName();
	
	/**
	 * Gets the age of the user.
	 * @return age of the user
	 */
	int getAge();
	
	/**
	 * Gets the address of the user.
	 * @return address of the user
	 */
	String getAddress();
	
	/**
	 * Gets the job of the user.
	 * @return job of the user
	 */
	String getProfession();

	/**
	 * Verifies if the given user is in the contact tree.
	 * @param user - given user
	 * @return true if the given user is in the tree, false otherwise
	 */
	boolean hasContact(String login);
	
	/**
	 * Returns the number of groups.
	 * @return number of groups
	 */
	int getNumberOfGroups();
	
	/**
	 * Returns an iterator of the message list.
	 * @return iterator of the message list
	 * @throws NoContactMessages
	 */
	Iterator<Message> getMessages() throws NoContactMessages;
	
	/**
	 * Returns an iterator of the contact tree.
	 * @return iterator of the contact tree
	 * @throws NoContacts
	 */
	Iterator<User> getContacts() throws NoContacts;

}
