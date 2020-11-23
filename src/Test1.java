import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

import Movie.DB_MovieArea;
import Movie.MovieArea;
import User.User;

public class Test1 {
	static User user = new User();

	public static void main(String[] args) {
		DB_MovieArea connect = new DB_MovieArea(); 
		
		MovieArea movieArea = connect.getMovieArea(224);
		
		Thread t1 = new Thread(new Test(user, movieArea));
		t1.start();
	}
}
