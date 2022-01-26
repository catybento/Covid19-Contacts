package contactsCovid;

/**
 * Message
 * @author Catarina Bento(57369) and Martim Gouveia(57482)
 */
public interface Message {

	/**
	 * Gets the title of the message
	 * @return title - title of the message 
	 */
	String getTitle();

	/**
	 * Gets the description of the message
	 * @return description - description of the message
	 */
	String getDescription();
	
	/**
	 * Gets the URL of the message
	 * @return URL - URL of the message
	 */
	String getURL();

}
