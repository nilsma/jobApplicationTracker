package models;

/**
 * A model class to represent a Profile in the application
 * @author nilsma
 */
public class Profile {
	private String name;
	private String path;
	
	/**
	 * A Profile constructor
	 * @param name the short-hand name of the profiles owner
	 * @param path the path to the profiles directory
	 */
	public Profile(String name, String path) {
		this.name = name;
		this.path = path;
	}
	
	/**
	 * A no-param Profile constructor
	 */
	public Profile() {
		this.name = null;
		this.path = null;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
}
