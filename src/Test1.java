import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;

import User.User;

import theater.Theater;

public class Test1 {
	static User user = new User();

	public static void main(String[] args) {
		
		Calendar cal = Calendar.getInstance();
		Date currentDate = new Date();
		currentDate.setMonth(10);
		currentDate.setDate(23);
		currentDate.setHours(5);
		currentDate.setMinutes(30);
		currentDate.setSeconds(1);
		
		int year;
		int month;
		int day;
		int dayofweek;
		int endDate;
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH)+1; // 월
		day = cal.get(Calendar.DATE);// 날짜
		dayofweek = cal.get(Calendar.DAY_OF_WEEK);// 요일
		endDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		System.out.println(year+","+month+","+day+","+dayofweek+","+endDate);
		System.out.println(currentDate);
		
		
		
		/*for (int i = 0; i < dayAndDayofTable.length; i++) {
			weeksName = getDayOfweek(dayofweek);
			dayAndDayofTable[i] = new JKeyButton(day + "*" + weeksName);
			dayAndDayofTable[i].setYear(year);
			dayAndDayofTable[i].setMonth(month);
			dayAndDayofTable[i].setDay(day);
			dayAndDayofTable[i].setDayofweek(dayofweek);
		}*/
		
		
	
		
		
	}
}
