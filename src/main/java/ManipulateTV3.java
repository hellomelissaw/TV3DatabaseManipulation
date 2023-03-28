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
			//Scanner scanner = new Scanner(System.in, "CP850"); //Western Europe Console CodePage
            //String insertManually = scanner.nextLine();
            //System.out.println("Type sql manipulation: ");
            FootagesAndReportersLoader loader = new FootagesAndReportersLoader();
            List<FootageAndReporter> footAndRep = loader.loadFootagesAndReporters("src/main/java/uploads.csv");

                String country = "'Danemark');";
                String insertJournalist = "INSERT Journalist VALUES (" + footAndRep.get(0).getReporter().getCPR()
                        + ", '" + footAndRep.get(0).getReporter().getFirstName() + "'"
                        + ", '" + footAndRep.get(0).getReporter().getLastName() + "'"
                        + ", '" + footAndRep.get(0).getReporter().getStreetName() + "'"
                        + ", '" + footAndRep.get(0).getReporter().getCivicNumber() + "'"
                        + ", '" + footAndRep.get(0).getReporter().getCountry() + "'"
                        + ", '" + footAndRep.get(0).getReporter().getZIPCode() + "'"
                        + ", " + country;

                System.out.println(insertJournalist);

            //System.out.println( footAndRep.get(0).getFootage().getDuration() + "class is: " + footAndRep.get(0).getReporter().getCPR().getClass());
            String insertFootage =  "INSERT Footage VALUES ('" + footAndRep.get(0).getFootage().getTitle() + "'," +
                    " '" + footAndRep.get(0).getFootage().getDate() + "',"
                    + " '" + footAndRep.get(0).getFootage().getDuration() + "', "
                    + footAndRep.get(0).getReporter().getCPR() + ");";
            //System.out.println(insertFootage);
            //scanner.close();


            // Get a connection.
            Connection connection = DriverManager.getConnection(url, username, password);
            // Create and execute Update.
            Statement statementJour = connection.createStatement();

            try {
            statementJour.executeUpdate(insertJournalist);
            } catch (SQLIntegrityConstraintViolationException d) {
                System.out.println("Entry already exists in the database!");
            }
            //Statement statementFoot = connection.createStatement();
            //statementFoot.executeUpdate(insertFootage);
            //statement.executeUpdate(insertManually);
            // Close connection.
            connection.close();
        } catch (Exception e) {
        	e.printStackTrace(); 
        }
    }
}