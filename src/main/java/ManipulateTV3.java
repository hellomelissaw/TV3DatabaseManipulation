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
            String insertJournalist = null;
            String insertFootage = null;
            for(int i = 0 ; i < footAndRep.size() ; i ++) {
                String country = "'Danemark');";
                insertJournalist = "INSERT Journalist VALUES (" + footAndRep.get(i).getReporter().getCPR()
                        + ", '" + footAndRep.get(i).getReporter().getFirstName() + "'"
                        + ", '" + footAndRep.get(i).getReporter().getLastName() + "'"
                        + ", '" + footAndRep.get(i).getReporter().getStreetName() + "'"
                        + ", '" + footAndRep.get(i).getReporter().getCivicNumber() + "'"
                        + ", '" + footAndRep.get(i).getReporter().getCountry() + "'"
                        + ", '" + footAndRep.get(i).getReporter().getZIPCode() + "'"
                        + ", " + country;

                System.out.println("Journalist #" + i + ": " + insertJournalist);

                //System.out.println( footAndRep.get(0).getFootage().getDuration() + "class is: " + footAndRep.get(0).getReporter().getCPR().getClass());
                insertFootage = "INSERT Footage VALUES ('" + footAndRep.get(i).getFootage().getTitle() + "'," +
                        " '" + footAndRep.get(i).getFootage().getDate() + "',"
                        + " '" + footAndRep.get(i).getFootage().getDuration() + "', "
                        + footAndRep.get(i).getReporter().getCPR() + ");";
                System.out.println("Footage #" + i + ": " + insertFootage);
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

                try {
                    Statement statementFoot = connection.createStatement();
                    statementFoot.executeUpdate(insertFootage);
                } catch (SQLIntegrityConstraintViolationException d) {
                    System.out.println("Entry already exists in the database!");
                }
                //statement.executeUpdate(insertManually);
                // Close connection.
                connection.close();
            }

        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
}