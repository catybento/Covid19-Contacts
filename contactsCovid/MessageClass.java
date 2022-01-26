package contactsCovid;

/**
 * MessageClass
 * @author Catarina Bento(57369) and Martim Gouveia(57482)
 */
public class MessageClass implements Message{
	
	/**
	 * Title of the message
	 * Description of the message
	 * URL of the message
	 */
	private String title, desc, url;

	/**
	 * Constructor
	 * @param title - title of the message
	 * @param desc - description of the message
	 * @param url - URL of the message
	 */
    public MessageClass(String title, String desc, String url){
    	this.title = title;
    	this.desc = desc;
    	this.url = url;
    }

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getDescription() {
		return desc;
	}

	@Override
	public String getURL() {
		return url;
	}

}
