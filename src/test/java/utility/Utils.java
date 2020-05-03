package utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	
	public static String getFormattedDateTime() {
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss z");
		return simpleDateFormat.format(date);
	}

}
