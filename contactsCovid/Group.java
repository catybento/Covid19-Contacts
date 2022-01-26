package contactsCovid;
import contactsCovid.exceptions.*;
import dataStructures.Iterator;

/**
 * Group 
 * @author Catarina Bento(57369) and Martim Gouveia(57482)
 */
public interface Group {
	
	/**
	 * Gets the group name.
	 * @return group name
	 */
	String getGroupName();
	
	/**
	 * Gets the description of the group.
	 * @return description of the group
	 */
	String getDescription();

	/**
	 * Verifies if the given user is in the member dictionary.
	 * @param user - given user 
	 * @return true if the user is in the member dictionary, false otherwise
	 */
	boolean hasMember(String login);

	/**
	 * Verifies if the group is empty.
	 * @return if the group is empty.
	 */
	boolean isEmpty();
	
	/**
	 * Lists the participants of the group.
	 * @return iterator of participants of the group.
	 * @throws NoParticipants
	 */
	Iterator<User> iterator() throws NoParticipants;

	/**
	 * Lists the messages of the group.
	 * @return iterator of the messages of the group.
	 * @throws NoGroupMessages
	 */
	Iterator<Message> getGroupMessages() throws NoGroupMessages;

}