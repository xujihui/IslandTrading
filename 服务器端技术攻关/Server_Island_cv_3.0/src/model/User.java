package model;

import com.jfinal.plugin.activerecord.Model;

public class User extends Model<User> {
	/**
	 * User
	 */
	private static final long serialVersionUID = 1L;
	public static final User dao = new User();	
}