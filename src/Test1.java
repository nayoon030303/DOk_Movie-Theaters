import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

import User.User;

public class Test1 {
	static User user = new User();

	public static void main(String[] args) {
		LocalDateTime currentDateTime = LocalDateTime.now();// ���� ��¥�� �ð�
		String str = "2020-11-24 13:00";
		LocalDateTime movieTime = LocalDateTime.parse(str, DateTimeFormatter.ofPattern("yyy-MM-dd H:mm"));
		
		if (movieTime.compareTo(currentDateTime) < 0) {// �󿵿�ȭ�� ���� �ð����� ������
			System.out.println("����2");
		}else {
			System.out.println("����");
		}
		
	}
}
