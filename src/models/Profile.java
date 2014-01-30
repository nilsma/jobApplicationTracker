package models;

public class Profile {
	private String name;
	private String path;
	
	public Profile(String name, String path) {
		this.name = name;
		this.path = path;
	}
	
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
