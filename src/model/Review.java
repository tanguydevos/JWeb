package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sql.SqlManager;

public class Review {

	private Integer id;
	private String review;
	private String userName;

	public Integer getId() {
		return id;
	}

	public String getReview() {
		return review;
	}

	public String getUserName() {
		return userName;
	}

	/**
	 * @param idProduct
	 * @param idUser
	 * @param review
	 * Create a review in database for a specific product
	 * @return boolean true or false (success or fail)
	 */
	static public boolean createReview(int idProduct, String idUser, String review, String stars) {
		Connection connexion = SqlManager.connectDatabase();
		Statement statement = SqlManager.getConnectStatement(connexion);
		review = review.replaceAll("\r\n", "<br>");
		review = review.replaceAll("'", "''");
		boolean added = false;
		try {	
			String req = "INSERT INTO reviews VALUES (null, '" + idProduct + "', '" + idUser + "', '" + review + "', '" + stars + "', NOW());";
			System.out.println(req);
			int statut = statement.executeUpdate(req);
			System.out.println("statut -> " + statut);
			added = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlManager.closeConnexion(connexion, statement);
		}
		return (added);
	}

	/**
	 * List all reviews in database through a JSONArray convert in a string
	 * @return string with all reviews encoded in json
	 */
	static public String getReviewByProductId(int id) {
		Connection connexion = SqlManager.connectDatabase();
		Statement statement = SqlManager.getConnectStatement(connexion);
		String jsonObject = "[";
		try {
			String req = "SELECT id, idUser, review, stars, date FROM reviews ORDER BY id DESC;";
			System.out.println(req);
			ResultSet resultat = statement.executeQuery(req);
			System.out.println(resultat);
			while (resultat.next()) {
				if (resultat.isFirst() == false)
					jsonObject += ", ";
				jsonObject += "{ ";
				jsonObject += "\"id\": \"" + resultat.getString("id") + "\", ";
				jsonObject += "\"idUser\": \"" + resultat.getInt("idUser")+ "\", ";
				jsonObject += "\"review\": \"" + resultat.getString("review").replaceAll("\"", "\\\\\"") + "\", ";
				jsonObject += "\"stars\": \"" + resultat.getInt("stars")+ "\", ";
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
	
}