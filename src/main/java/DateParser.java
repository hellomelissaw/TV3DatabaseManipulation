public class DateParser {

    public String parseDate(String date) {
        String[] dateChars = date.split("");
        StringBuilder dateFormatted = new StringBuilder(10);

        for (int i = 0; i < dateChars.length; i++) {

            dateFormatted.append(dateChars[i]);

            if (i == 3 || i == 5) {
                dateFormatted.append("-");
            }
        }
    return date;
    }
}

