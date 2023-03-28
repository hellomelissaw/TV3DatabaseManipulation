import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
			 
public class ManipulateTV3 {
    public static void main(String[] args) {
        // Adapt the following variables to your database.
		String host = "localhost"; //host is "localhost" or "127.0.0.1"
		String port = "3306"; //port is where to communicate with the RDBMS
		String database = "TV3Test"; //database containing tables to be queried
		String cp = "utf8"; //Database codepage supporting Danish (i.e. æøåÆØÅ)
		// Set username and password.
		String username = "root";		// Username for connection
        String password = "mypassword";	// Password for username

        String url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?characterEncoding=" + cp;
		try {
			Scanner scanner = new Scanner(System.in, "CP850"); //Western Europe Console CodePage
            System.out.println("Type sql manipulation: ");
            FootagesAndReportersLoader loader = new FootagesAndReportersLoader();
            List<FootageAndReporter> footAndRep = loader.loadFootagesAndReporters("src/main/java/uploads.csv");

            System.out.println(footAndRep.get(0).getFootage().getDate());
            String insertFootage =  "insert Footage values('" + footAndRep.get(0).getFootage().getTitle() + "'," +
                    "'" + footAndRep.get(0).getFootage().getDate() + "',"
                    + "'" + footAndRep.get(0).getFootage().getDuration() + "',"
                    +  "'" + footAndRep.get(0).getReporter().getCPR() + "')";

            scanner.close();
            // Get a connection.
            Connection connection = DriverManager.getConnection(url, username, password);
            // Create and execute Update.
            Statement statement = connection.createStatement();
            statement.executeUpdate(insertFootage);
            // Close connection.
            connection.close();
        } catch (Exception e) {
        	e.printStackTrace(); 
        }
    }
}