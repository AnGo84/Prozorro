package ua.prozorro.temp;

public class Test {
	static String short_date_format = "yyyy-MM-dd";
	
	public static void main(String[] args) {
		/*try {
			Date pageDate = DateUtils.parseProzorroPageDateFromString("2018-01-31T10:35:16+02:00", "yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX");
			System.out.println("Date: " + pageDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
		long number = (new Double("497.0")).longValue();
		System.out.println("Long: " + number);
	}
	
}
