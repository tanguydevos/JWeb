package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sql.SqlManager;

public class Product {

	private Integer id;
	private String name;
	private String photo;
	private String description;
	private int price;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPhoto() {
		return photo;
	}

	public String getDescription() {
		return description;
	}

	public int getPrice() {
		return price;
	}

	/**
	 * @param id
	 * @param name
	 * @param photo
	 * @param description
	 * @param price
	 * Retrieve data to initialize new product
	 */
	public Product(Integer id, String name, String photo, String description, int price) {
		this.id = id;
		this.name = name;
		this.photo = photo;
		this.description = description;
		this.price = price;
	}

	/**
	 * @param id
	 * Get the product by id
	 * @return product object or null (success or fail)
	 */
	static public Product getProduct(int id) {
		Connection connexion = SqlManager.connectDatabase();
		Statement statement = SqlManager.getConnectStatement(connexion);
		Product product = null;
		try {
			String req = "SELECT name, photo, description, price FROM product WHERE id = " + id + ";";
			System.out.println(req);
			ResultSet resultat = statement.executeQuery(req);
			System.out.println(resultat);
			resultat.next();
			if (resultat.isFirst() == true) {
				product = new Product(id, resultat.getString("name"), resultat.getString("photo"),
						resultat.getString("description"), resultat.getInt("price"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return (null);
		} finally {
			SqlManager.closeConnexion(connexion, statement);
		}
		return product;
	}
}
