package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sql.SqlManager;

public class News {

	/**
	 * @param  title string
	 * @param  description string
	 * Insert a news in database, convert data to be compatible with SQL field
	 * @return true or false (success or fail)
	 */
	static public boolean createNews(String title, String description) {
		Connection connexion = SqlManager.connectDatabase();
		Statement statement = SqlManager.getConnectStatement(connexion);
		description = description.replaceAll("\r\n", "<br>");
		description = description.replaceAll("'", "''");
		title = title.replaceAll("\r\n", "<br>");
		title = title.replaceAll("'", "''");
		try {
			String req = "INSERT INTO news VALUES (null, '" + title + "', '" + description + "', NOW());";
			System.out.println(req);
			int statut = statement.executeUpdate(req);
			System.out.println("statut -> " + statut);
			return (true);
		} catch (SQLException e) {
			return (false);
		} finally {
			SqlManager.closeConnexion(connexion, statement);
		}
	}

	/**
	 * List all news in database through a JSONArray convert in a string
	 * @return string with all news encoded in json
	 */
	static public String getNews() {
		Connection connexion = SqlManager.connectDatabase();
		Statement statement = SqlManager.getConnectStatement(connexion);
		String jsonObject = "[";
		try {
			String req = "SELECT id, title, description, date FROM news ORDER BY id DESC;";
			System.out.println(req);
			ResultSet resultat = statement.executeQuery(req);
			System.out.println(resultat);
			while (resultat.next()) {
				if (resultat.isFirst() == false)
					jsonObject += ", ";
				jsonObject += "{ ";
				jsonObject += "\"id\": \"" + resultat.getString("id") + "\", ";
				jsonObject += "\"title\": \"" + resultat.getString("title").replaceAll("\"", "\\\\\"") + "\", ";
				jsonObject += "\"description\": \"" + resultat.getString("description").replaceAll("\"", "\\\\\"") + "\", ";
				jsonObject += "\"date\": \"" + resultat.getDate("date") + "\"";
				jsonObject += " }";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlManager.closeConnexion(connexion, statement);
		}
		jsonObject += "]";
		System.out.println(jsonObject);
		return jsonObject;
	}
	
	/**
	 * @param id int
	 * getNewsById select a news by an id and get all data
	 * @return string[] with all data
	 */
	static public String[] getNewsById(int id) {
		Connection connexion = SqlManager.connectDatabase();
		Statement statement = SqlManager.getConnectStatement(connexion);
		String [] newsArray = new String[3];
		try {
			String req = "SELECT title, description FROM news WHERE id = " + id + ";";
			System.out.println(req);
			ResultSet resultat = statement.executeQuery(req);
			System.out.println(resultat);
			while (resultat.next()) {
				newsArray[0] = Integer.toString(id);
				newsArray[1] = resultat.getString("title").replaceAll("<br>", "\r\n");
				newsArray[2] = resultat.getString("description").replaceAll("<br>", "\r\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlManager.closeConnexion(connexion, statement);
		}
		System.out.println(newsArray);
		return newsArray;
	}
	
	/**
	 * @param id int
	 * @param title string
	 * @param description string
	 * Allow to edit a news which exists in database with form data in post method
	 */
	static public void editNews(int id, String title, String description) {
		Connection connexion = SqlManager.connectDatabase();
		Statement statement = SqlManager.getConnectStatement(connexion);
		description = description.replaceAll("\r\n", "<br>");
		description = description.replaceAll("'", "''");
		description = description.replaceAll("\"", "\\\\\"");
		title = title.replaceAll("\r\n", "<br>");
		title = title.replaceAll("'", "''");
		title = title.replaceAll("\"", "\\\\\"");
		try {
			String req = "UPDATE news SET `title` = \"" + title + "\", `description` = \"" + description + "\", `date` = NOW() WHERE `id` = " + id + ";";
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
	 * @param id int
	 * Delete a news with id param
	 * @return true or false (success or fail)
	 */
	static public boolean deleteNews(int id) {
		Connection connexion = SqlManager.connectDatabase();
		Statement statement = SqlManager.getConnectStatement(connexion);
		try {
			String req = "DELETE FROM news WHERE id = " + id + ";";
			System.out.println(req);
			int statut = statement.executeUpdate(req);
			System.out.println("statut -> " + statut);
		} catch (SQLException e) {
			return (false);
		} finally {
			SqlManager.closeConnexion(connexion, statement);
		}
		return (true);
	}
}
