package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlManager {

	/**
	 * Enable JDBC driver to interact with database 
	 */
	public static void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Reset JWeb database and all tables for a new and clean installation
	 */
	public static void resetDatabase() {
		Connection connexion = connectDatabase();
		Statement statement = getConnectStatement(connexion);
		try {
			int status = statement.executeUpdate("DROP TABLE IF EXISTS users");
			status = statement.executeUpdate("DROP TABLE IF EXISTS product");
			status = statement.executeUpdate("DROP TABLE IF EXISTS reviews");
			status = statement.executeUpdate("DROP TABLE IF EXISTS news");
			status = statement.executeUpdate("CREATE TABLE IF NOT EXISTS users ("
							+ "id int(11) NOT NULL auto_increment, "
							+ "firstname varchar(60) NOT NULL, "
							+ "lastname varchar(60) NOT NULL, "
							+ "email varchar(255) NOT NULL, "
							+ "password varchar(60) NOT NULL, "
							+ "newsletter bool DEFAULT false NOT NULL, "
							+ "admin bool DEFAULT false NOT NULL, "
							+ "date_inscription date DEFAULT '00-00-0000' NOT NULL, "
							+ "PRIMARY KEY (id), KEY id (id), "
							+ "UNIQUE id_2 (id) )"
							+ "DEFAULT CHARSET=utf8;");
			System.out.println("statut -> " + status);
			status = statement.executeUpdate("CREATE TABLE IF NOT EXISTS product ("
							+ "id int(11) NOT NULL auto_increment, "
							+ "name varchar(255) NOT NULL, "
							+ "photo varchar(255) NOT NULL, "
							+ "description text NOT NULL, "
							+ "price float NOT NULL, "
							+ "PRIMARY KEY (id), KEY id (id), "
							+ "UNIQUE id_2 (id) )"
							+ "DEFAULT CHARSET=utf8;");
			System.out.println("statut -> " + status);
			status = statement.executeUpdate("CREATE TABLE IF NOT EXISTS reviews ("
							+ "id int(11) NOT NULL auto_increment, "
							+ "idProduct int(11) NOT NULL, "
							+ "idUser int(11) NOT NULL, "
							+ "review text NOT NULL, "
							+ "stars tinyint(1) NOT NULL, "
							+ "date date DEFAULT '00-00-0000' NOT NULL, "
							+ "PRIMARY KEY (id), KEY id (id), "
							+ "UNIQUE id_2 (id) )"
							+ "DEFAULT CHARSET=utf8;");
			System.out.println("statut -> " + status);
			status = statement.executeUpdate("CREATE TABLE IF NOT EXISTS news ("
							+ "id int(11) NOT NULL auto_increment, "
							+ "title varchar(255) NOT NULL, "
							+ "description text NOT NULL, "
							+ "date date DEFAULT '00-00-0000' NOT NULL, "
							+ "PRIMARY KEY (id), KEY id (id), "
							+ "UNIQUE id_2 (id) )"
							+ "DEFAULT CHARSET=utf8;");
			
			// Add default users and data
			System.out.println("statut -> " + status);
			status = statement.executeUpdate("INSERT INTO users (id, firstname, "
					+ "lastname, email, password, newsletter, admin, date_inscription)"
					+ "VALUES (1, 'Administrateur', 'admin', 'admin@jweb.eu', MD5('admin'), 1, 1, NOW());");
			System.out.println("statut -> " + status);
			status = statement.executeUpdate("INSERT INTO users (id, firstname, "
					+ "lastname, email, password, newsletter, admin, date_inscription)"
					+ "VALUES (2, 'Jean', 'DUJARDIN', 'jean.dujardin@nespresso.eu', MD5('ouatelse'), 1, 0, NOW());");
			System.out.println("statut -> " + status);
			status = statement.executeUpdate("INSERT INTO news (id, title, "
					+ "description, date)"
					+ "VALUES (1, 'Jean Dujardin is now our ambassador !', "
					+ "'Hi !<br><br>Jean DUJARDIN: the famous french actor is now our first ambassador ! "
					+ "Please visit our product page to see his review about our fanstastic product !"
					+ "<br><br>Thanks and have a nice day dear fan', NOW());");
			System.out.println("statut -> " + status);
			status = statement.executeUpdate("INSERT INTO product (id, name, "
					+ "photo, description, price)"
					+ "VALUES (1, 'Epluch-O-4000', 'image/product.jpg', "
					+ "'Epluch-O-4000 is a new product developed by JWeb Project and made by two frenchies : "
					+ "<i>Tanguy DEVOS and Maxime DRAY</i>.<br><br><h2>What is Epluch-O-4000 ?</h2><br>"
					+ "It is the only and best way to peel everything you want with no efforts. Epluch is a "
					+ "french word which means peel and it is a good way to remind the quality and the "
					+ "strength of french tools.<br><br><h2>Why use the number 4000 ?</h2><br>It is the "
					+ "price, <b>simple</b>, <b>elegant</b> and <b>accessible</b>.<br><br><h2>Tell me more"
					+ " about Epluch-O-4000 !</h2><br>The best way to convince you is our community "
					+ "of Epluch-O-4000, also our ambassador : Jean Dujardin a famous french actor "
					+ "who loves our products.<br><br>You can see below all the reviews :)'"
					+ ", 4000);");
			System.out.println("statut -> " + status);
			status = statement.executeUpdate("INSERT INTO reviews (id, idProduct, idUser, review, stars, date) "
					+ "VALUES (2, 1, 2, 'Epluch-O-4000 is beautiful, wonderful and so sexy.<br>I think "
					+ "I am going to use it in my next movie : OSS 3 : rafale de pruneaux au congo.', "
					+ "5, NOW());");
			System.out.println("statut -> " + status);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnexion(connexion, statement);
		}
	}


	/**
	 * Connect to database with user and password
	 * @return the connexion if connect to database is a success
	 */
	static public Connection connectDatabase() {
		String url = "jdbc:mysql://localhost/JWeb?useUnicode=true&characterEncoding=utf-8";
		String user = "root";
		String password = "";
		Connection connexion = null;
		try {
			connexion = DriverManager.getConnection(url, user,
					password);
		} catch (SQLException e) {
			e.printStackTrace();
			closeConnexion(connexion, null);
		}
		return connexion;
	}

	/**
	 * Get the statement 
	 * @return the statement if creating statement is a success
	*/
	static public Statement getConnectStatement(Connection connexion) {
		Statement statement = null;
		try {
			statement = connexion.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			closeConnexion(connexion, statement);
		}
		return statement;
	}

	/**
	 * Close opened connexion and created statement if they exist
	*/
	static public void closeConnexion(Connection connexion, Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException ignore) {
			}
		}
		if (connexion != null)
			try {
				connexion.close();
			} catch (SQLException ignore) {
			}
	}
}
