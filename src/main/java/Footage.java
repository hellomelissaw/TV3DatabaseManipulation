import java.text.SimpleDateFormat;
import java.util.Date;
public class Footage {
    private final String title;
    private final String date;
    private final Integer duration;

    public Footage(String title, String date, Integer duration) {
        this.title = title;
        this.date = date;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }
    public String getDate() { return date; }
    public Integer getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        final String D = ";";
        final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");

        return getTitle() +D + dateFormatter.format(getDate()) +D + getDuration();
    }

}