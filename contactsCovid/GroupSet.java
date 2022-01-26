package contactsCovid;

/**
 * GroupSet 
 * @author Catarina Bento(57369) and Martim Gouveia(57482)
 */
public interface GroupSet extends Group{

	/**
	 * Adds a new member to the group.
	 * @param user - user that will join the group.
	 */
	void addMember(User user);

	/**
	 * Removes a member from the group.
	 * @param user - user that will leave the group.
	 * Pre-condition: user != null
	 */
	boolean removeMember(String login);

	/**
	 * Receives a message.
	 * @param message - message that will be received.
	 */
	void receiveMessage(Message message);

}
