import java.io.IOException;
import java.util.List;

public class LoaderTV3 {

	public static void main(String[] args) {
		FootagesAndReportersLoader loader = new FootagesAndReportersLoader();
		try {
			System.out.println("loading from src/main/java/uploads.csv");
			List<FootageAndReporter> footagesAndReporters = loader.loadFootagesAndReporters("src/main/java/uploads.csv");
			for(FootageAndReporter footageAndReporter : footagesAndReporters) {
				System.out.print("\tFootage: " + footageAndReporter.getFootage());
				System.out.println("\tReporter: " + footageAndReporter.getReporter());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


