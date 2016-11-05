package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import sql.SqlManager;

public class User {

	private Integer id;
	private String firstname;
	private String lastname;
	private String email;
	private Boolean newsletter;
	private Boolean admin;

	public Integer getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getEmail() {
		return email;
	}

	public Boolean getNewsletter() {
		return newsletter;
	}

	public Boolean getAdmin() {
		return admin;
	}

	/**
	 * @param id
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @param newsletter
	 * @param admin
	 * Initialize the user object
	 */
	public User(Integer id, String firstname, String lastname, String email, Boolean newsletter, Boolean admin) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.newsletter = newsletter;
		this.admin = admin;
	}
	
	/**
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @param password
	 * @param newsletter
	 * Create the user with all form data in database
	 */
	static public void createUser(String firstname, String lastname, String email, String password, String newsletter) {
		Connection connexion = SqlManager.connectDatabase();
		Statement statement = SqlManager.getConnectStatement(connexion);
		try {
			String req = "INSERT INTO users VALUES (null, '" + firstname + "', '" + lastname + "', '" + email
					+ "', MD5('" + password + "'), " + newsletter + ", false , NOW());";
			System.out.println(req);
			int statut = statement.executeUpdate(req);
			System.out.println("statut -> " + statut);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlManager.closeConnexion(connexion, statement);
		}
	}

	/**
	 * @param email
	 * Check if email already exists in database
	 * @return true or false (email exists or not)
	 */
	static public boolean checkUserMail(String email) {
		Connection connexion = SqlManager.connectDatabase();
		Statement statement = SqlManager.getConnectStatement(connexion);
		boolean result = false;
		try {
			String req = "SELECT email FROM users WHERE email = '" + email + "';";
			System.out.println(req);
			ResultSet resultat = statement.executeQuery(req);
			System.out.println(resultat);
			int rowcount = 0;
			if (resultat.last()) {
				rowcount = resultat.getRow();
			}
			if (rowcount == 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlManager.closeConnexion(connexion, statement);
		}
		return result;
	}
	/**
	 * 
	 * @param email
	 * @param password
	 * Check user existence in database and get the id for session variable 
	 * @return the id of connected user, if equals to -1, there is an error
	 */
	static public int connectUser(String email, String password) {
		Connection connexion = SqlManager.connectDatabase();
		Statement statement = SqlManager.getConnectStatement(connexion);
		int id = -1;
		try {
			String req = "SELECT id FROM users WHERE email = '" + email + "' AND password = MD5('" + password + "');";
			System.out.println(req);
			ResultSet resultat = statement.executeQuery(req);
			System.out.println(resultat);
			resultat.next();
			if (resultat.isFirst() == true) {
				id = resultat.getInt("id");
				System.out.println(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlManager.closeConnexion(connexion, statement);
		}
		return id;
	}

	/**
	 * @param id
	 * Try to see if user have admin privileges
	 * @return true or false (yes or no)
	 */
	static public Boolean userIsAdmin(int id) {
		Connection connexion = SqlManager.connectDatabase();
		Statement statement = SqlManager.getConnectStatement(connexion);
		Boolean result = false;
		try {
			String req = "SELECT admin FROM users WHERE id = " + id + ";";
			System.out.println(req);
			ResultSet resultat = statement.executeQuery(req);
			System.out.println(resultat);
			resultat.next();
			if (resultat.isFirst() == true) {
				if (resultat.getBoolean("admin") == true) {
					result = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlManager.closeConnexion(connexion, statement);
		}
		return result;
	}

	/**
	 * List all users in database
	 * @return ArrayList of all users
	 */
	static public ArrayList<User> getUser() {
		Connection connexion = SqlManager.connectDatabase();
		Statement statement = SqlManager.getConnectStatement(connexion);
		ArrayList<User> listUser = new ArrayList<User>();
		try {
			String req = "SELECT id, firstname, lastname, email, newsletter, admin, date_inscription FROM users";
			System.out.println(req);
			ResultSet resultat = statement.executeQuery(req);
			System.out.println(resultat);
			while (resultat.next()) {
				listUser.add(new User(resultat.getInt("id"), resultat.getString("firstname"), resultat
						.getString("lastname"), resultat.getString("email"), resultat.getBoolean("newsletter"),
						resultat.getBoolean("admin")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlManager.closeConnexion(connexion, statement);
		}
		return listUser;
	}

	/**
	 * 
	 * @param id
	 * Set admin privileges for a specific user (by id)
	 */
	static public void userSetAdmin(int id) {
		Connection connexion = SqlManager.connectDatabase();
		Statement statement = SqlManager.getConnectStatement(connexion);
		try {
			String req = "UPDATE users SET admin = true WHERE id = " + id + ";";
			System.out.println(req);
			int statut = statement.executeUpdate(req);
			System.out.println("statut -> " + statut);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlManager.closeConnexion(connexion, statement);
		}
	}

	/**
	 * 
	 * @param id
	 * Set newsletter access for a specific user (by id)
	 */
	static public void userSetNewsletter(int id) {
		Connection connexion = SqlManager.connectDatabase();
		Statement statement = SqlManager.getConnectStatement(connexion);
		try {
			String req = "UPDATE users SET newsletter = true WHERE id = " + id + ";";
			System.out.println(req);
			int statut = statement.executeUpdate(req);
			System.out.println("statut -> " + statut);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlManager.closeConnexion(connexion, statement);
		}
	}

	/**
	 * @param id
	 * Delete an user by id
	 */
	static public void userDelete(int id) {
		Connection connexion = SqlManager.connectDatabase();
		Statement statement = SqlManager.getConnectStatement(connexion);
		try {
			String req = "DELETE FROM users WHERE id = " + id + ";";
			System.out.println(req);
			int statut = statement.executeUpdate(req);
			System.out.println("statut -> " + statut);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlManager.closeConnexion(connexion, statement);
		}
	}
	
	/**
	 * Unset all newsletter members for users in database
	 */
	static public void userUnsetNewsletter() {
		Connection connexion = SqlManager.connectDatabase();
		Statement statement = SqlManager.getConnectStatement(connexion);
		try {
			String req = "UPDATE users SET newsletter = false WHERE newsletter = true;";
			System.out.println(req);
			int statut = statement.executeUpdate(req);
			System.out.println("statut -> " + statut);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlManager.closeConnexion(connexion, statement);
		}
	}
	
	/**
	 * Unset all admin privileges for users in database
	 */
	static public void userUnsetAdmin() {
		Connection connexion = SqlManager.connectDatabase();
		Statement statement = SqlManager.getConnectStatement(connexion);
		try {
			String req = "UPDATE users SET admin = false WHERE admin = true;";
			System.out.println(req);
			int statut = statement.executeUpdate(req);
			System.out.println("statut -> " + statut);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlManager.closeConnexion(connexion, statement);
		}
	}
	
	/**
	 * @param id
	 * Get user firstname and lastname by Id
	 * @return a string with the username or null (success or fail)
	 */
	static public String getUserNameById(int id) {
		Connection connexion = SqlManager.connectDatabase();
		Statement statement = SqlManager.getConnectStatement(connexion);
		String userName = null;
		try {
			String req = "SELECT firstname, lastname FROM users WHERE id = " + id + ";";
			System.out.println(req);
			ResultSet resultat = statement.executeQuery(req);
			System.out.println(resultat);
			resultat.next();
			userName = resultat.getString("firstname") + " " + resultat.getString("lastname");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlManager.closeConnexion(connexion, statement);
		}
		return (userName);
	}


}